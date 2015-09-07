/*
 * FileName:    MobileAipCaseDataTaskService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年5月28日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.task;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import cn.com.chaochuang.aipcase.bean.AipCasePendingHandleInfo;
import cn.com.chaochuang.aipcase.bean.AipCaseShowData;
import cn.com.chaochuang.aipcase.domain.AipCaseAttach;
import cn.com.chaochuang.aipcase.domain.AipCaseNoteFile;
import cn.com.chaochuang.aipcase.domain.FdFordoAipcase;
import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.aipcase.service.AipCaseApplyService;
import cn.com.chaochuang.aipcase.service.AipCaseAttachService;
import cn.com.chaochuang.aipcase.service.AipCaseNoteFileService;
import cn.com.chaochuang.aipcase.service.AipPunishEntpService;
import cn.com.chaochuang.aipcase.service.FdFordoAipcaseService;
import cn.com.chaochuang.common.util.JsonMapper;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.datacenter.domain.DataUpdate;
import cn.com.chaochuang.datacenter.domain.SysDataChange;
import cn.com.chaochuang.datacenter.reference.ExecuteFlag;
import cn.com.chaochuang.datacenter.reference.WorkType;
import cn.com.chaochuang.datacenter.service.DataUpdateService;
import cn.com.chaochuang.datacenter.service.SysDataChangeService;
import cn.com.chaochuang.docwork.reference.FordoSource;
import cn.com.chaochuang.task.bean.AipCasePendingInfo;
import cn.com.chaochuang.task.bean.AipCaseSubmitInfo;
import cn.com.chaochuang.task.bean.AipLawContentData;
import cn.com.chaochuang.task.bean.AipPunishInfo;
import cn.com.chaochuang.webservice.server.aipcasetransfer.AipCaseWebService;

import com.fasterxml.jackson.databind.JavaType;

/**
 * @author LLM
 *
 */
@Component
public class MobileAipCaseDataTaskService {

    @Autowired
    private AipCaseWebService      transferAipCaseService;
    /** webservice 函数库 */
    @Autowired
    private AipCaseApplyService    aipCaseApplyService;
    /** FdFordoAipcaseService */
    @Autowired
    private FdFordoAipcaseService  fdFordoService;
    @Autowired
    private AipCaseNoteFileService aipCaseNoteFileService;
    @Autowired
    private DataUpdateService      dataUpdateService;
    @Autowired
    private SysDataChangeService   dataChangeService;
    @Autowired
    private AipCaseAttachService   aipCaseAttachService;
    @Autowired
    private AipPunishEntpService   punishEntpService;

    /** 附件存放根路径 */
    @Value("${upload.rootpath}")
    private String                 rootPath;
    /** 办案系统附件路径 */
    @Value("${aipcase.attach.path}")
    private String                 aipcaseAttachPath;
    /** 文书html文件存放路径 */
    @Value("${htmlfile.savepath}")
    private String                 htmlFilePath;

    /** 获取案件办理阻塞标识 */
    private static boolean         isGetAipCaseRunning       = false;
    /** 获取办案系统待办阻塞标识 */
    private static boolean         isAipCaseFordoRunning     = false;
    /** 获取文书信息并转成pdf文件 阻塞标识 */
    private static boolean         isTransferFileRunning     = false;
    /** 提交或退回数据标识 */
    private static boolean         isSubmitDataRunning       = false;
    /** 获取待办阻塞标识 */
    private static boolean         isGetSysDataChangeRunning = false;
    /** 下载公文附件阻塞标识 */
    private static boolean         isDownLoadAttachRunning   = false;
    /** 获取办案系统处罚记录阻塞标识 */
    private static boolean         isAipCasePunishRunning    = false;

    /**
     * 获取案件办理系统的待办记录
     */
//     @Scheduled(cron = "10/30 * * * * ?")
    public void getAipCaseFordo() {
        if (isAipCaseFordoRunning) {
            return;
        }
        isAipCaseFordoRunning = true;
        try {
            AipCasePendingHandleInfo info = this.fdFordoService.selectMaxInputDate();
            // 若lastOutputTime无效则不做下一步
            if (info.getLastSendTime() == null && info.getRmPendingId() == null) {
                return;
            }
            String json = this.transferAipCaseService.selectPendingItemInfo(info.getLastSendTime(),
                            info.getRmPendingId());
            // 将案件办理的待办记录写入待办事宜表
            this.saveFdFordo(json, FordoSource.行政办案);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            isAipCaseFordoRunning = false;
        }
    }

    /**
     * 保存待办记录
     *
     * @param jsonData
     * @param fdSource
     */
    private void saveFdFordo(String jsonData, FordoSource fdSource) {
        if (Tools.isEmptyString(jsonData)) {
            return;
        }
        // 将json字符串还原回PendingCommandInfo对象，再循环将对象插入FdFordo表
        JsonMapper mapper = JsonMapper.getInstance();
        JavaType javaType = mapper.constructParametricType(ArrayList.class, AipCasePendingInfo.class);
        List<AipCasePendingInfo> datas = (List<AipCasePendingInfo>) mapper.readValue(jsonData, javaType);
        this.fdFordoService.insertFdFordos(datas);
    }

    /**
     * 获取案件办理数据写入本地表
     */
    // @Scheduled(cron = "20/40 * * * * ?")
    public void getAipCaseDataTask() {
        if (isGetAipCaseRunning) {
            return;
        }
        isGetAipCaseRunning = true;
        try {
            // 获取未下载审批数据的待办事宜，即localData=0的数据
            List<FdFordoAipcase> fordoDatas = this.fdFordoService.selectUnLocalData(new PageRequest(0, 10));
            if (!Tools.isNotEmptyList(fordoDatas)) {
                isGetAipCaseRunning = false;
                return;
            }
            String pendingIds = Tools.changeArrayToString(fordoDatas, "rmPendingId", ",", false);
            // 获取pendingIds指定的审批数据
            String json = this.transferAipCaseService.selectAipCaseApplyDates(pendingIds);
            if (!Tools.isEmptyString(json)) {
                // 将json字符串还原回PendingCommandInfo对象，再循环将对象插入FdFordo表
                JsonMapper mapper = JsonMapper.getInstance();
                JavaType javaType = mapper.constructParametricType(ArrayList.class, AipCaseShowData.class);
                List<AipCaseShowData> datas = (List<AipCaseShowData>) mapper.readValue(json, javaType);
                this.aipCaseApplyService.saveAipCaseApply(datas);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            isGetAipCaseRunning = false;
        }
    }

    /**
     * 根据待办获取文书内容，将远程生成html文件的路径保存
     */
    // @Scheduled(cron = "30/50 * * * * ?")
    public void AipLawContentToPDF() {
        if (isTransferFileRunning) {
            return;
        }
        isTransferFileRunning = true;
        try {
            // 查找AIP_CASE_NOTE_FILE表的非本地数据
            List<AipCaseNoteFile> noteFileList = aipCaseNoteFileService.findByLocalData(LocalData.非本地数据,
                            new PageRequest(0, 10));
            if (noteFileList != null) {
                JsonMapper mapper = JsonMapper.getInstance();
                for (AipCaseNoteFile noteFile : noteFileList) {
                    String jsonData = transferAipCaseService.getLawContentData(noteFile.getRmNoteFileId(),
                                    noteFile.getMdfCode());
                    AipLawContentData data = null;
                    if (StringUtils.isNotEmpty(jsonData)) {
                        data = mapper.readValue(jsonData, AipLawContentData.class);
                    }
                    if (data != null && StringUtils.isNoneEmpty(data.getRmFilePath())) {
                        // 根据返回的文件路径获取文件到本地，并修改loaclData
                        downloadHtmlFile(data);
                    } else {// 返回空说明该文书不存在或已经获取了文书的html文件
                        aipCaseNoteFileService.changeNoteFileLocalData(noteFile, LocalData.有本地数据);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            isTransferFileRunning = false;
        }

    }

    /**
     * 循环获取文件路径并下载到本地
     *
     * @param datas
     */
    private void downloadHtmlFile(AipLawContentData contentData) {
        String localFilePath = this.rootPath + this.htmlFilePath + File.separatorChar
                        + Tools.DATE_FORMAT4.format(new Date()) + File.separator + contentData.getRmCaseApplyId();
        File file = new File(localFilePath);
        // 目录不存在则建立新目录
        if (!file.exists()) {
            file.mkdirs();
        }
        BufferedOutputStream bufferedOutputStream = null;
        try {
            String localFileName = localFilePath + File.separatorChar + contentData.getNoteName() + "(rmid="
                            + contentData.getRmNoteFileId() + ").html";
            String remoteFileName = contentData.getRmFilePath();
            file = new File(localFileName);

            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file, true));
            Long offset = Long.valueOf(0);
            int uploadBlockSize = 10240;
            byte[] buffer = transferAipCaseService.uploadStreamAttachFile(remoteFileName, offset, uploadBlockSize);
            while (buffer.length > 0) {
                bufferedOutputStream.write(buffer, 0, buffer.length);
                offset += buffer.length;
                buffer = new byte[uploadBlockSize];
                buffer = transferAipCaseService.uploadStreamAttachFile(remoteFileName, offset, uploadBlockSize);
            }
            // 将附件localData标志置为本地数据("1")
            aipCaseNoteFileService.saveNoteFileForLocal(contentData, localFileName);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                } catch (IOException exf) {
                    throw new RuntimeException(exf);
                }
            }
        }

    }

    /**
     * 提交或退回案件审批
     */
    // @Scheduled(cron = "10/15 * * * * ?")
    public void commintSuperviseDataTask() {
        if (isSubmitDataRunning) {
            return;
        }
        isSubmitDataRunning = true;
        DataUpdate dataUpdate = null;
        try {
            // 扫描DataUpdate数据列表，条件：workType=03;operationType=update
            List<DataUpdate> datas = this.dataUpdateService.selectDocFileDataUpdate(WorkType.案件办理提交);
            // 每次仅处理列表的第一条记录
            if (!Tools.isNotEmptyList(datas)) {
                return;
            }
            dataUpdate = (DataUpdate) datas.get(0);
            if (dataUpdate == null) {
                return;
            }
            // 获取要提交的json字符串
            JsonMapper mapper = JsonMapper.getInstance();
            AipCaseSubmitInfo nodeInfo = mapper.readValue(dataUpdate.getContent(), AipCaseSubmitInfo.class);
            String backInfo = transferAipCaseService.submitOrRejectAipCaseApply(nodeInfo);
            aipCaseApplyService.deleteDataUpdateAndFordo(dataUpdate, backInfo, nodeInfo);
        } catch (Exception ex) {
            dataUpdate.setExecuteFlag(ExecuteFlag.执行错误);
            dataUpdate.setErrorInfo(ex.getClass().getName());
            this.dataUpdateService.getRepository().save(dataUpdate);
            ex.printStackTrace();
        } finally {
            isSubmitDataRunning = false;
        }
    }

    /**
     * 获取远程系统待办修改记录数据
     */
    // @Scheduled(cron = "15/6 * * * * ?")
    public void getOADataChange() {
        if (isGetSysDataChangeRunning) {
            return;
        }
        isGetSysDataChangeRunning = true;
        try {
            String json = this.transferAipCaseService.getDataChange();
            if (Tools.isEmptyString(json)) {
                return;
            }
            JsonMapper mapper = JsonMapper.getInstance();
            JavaType javaType = mapper.constructParametricType(ArrayList.class, SysDataChange.class);
            List<SysDataChange> datas = (List<SysDataChange>) mapper.readValue(json, javaType);
            this.dataChangeService.saveSysDataChange(datas);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            isGetSysDataChangeRunning = false;
        }
    }

    /**
     * 获取公文的附件，拉到本地存储
     */
    // @Scheduled(cron = "15/20 * * * * ?")
    public void getDocFileAttachTask() {
        if (isDownLoadAttachRunning) {
            return;
        }
        isDownLoadAttachRunning = true;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            String localFilePath = this.rootPath + this.aipcaseAttachPath + Tools.DATE_FORMAT4.format(new Date());
            // 获取公文OA的附件 查询DocFileAttach中localData为非本地数据("0")的数据，一次处理一个文件
            List<AipCaseAttach> datas = this.aipCaseAttachService.selectUnLocalAttach();
            if (!Tools.isNotEmptyList(datas)) {
                return;
            }
            AipCaseAttach attach = datas.get(0);
            File file = new File(localFilePath);
            // 目录不存在则建立新目录
            if (!file.exists()) {
                file.mkdirs();
            }
            String localFileName = localFilePath + "/" + attach.getSaveName();
            String remoteFileName = attach.getSavePath() + attach.getSaveName();
            file = new File(localFileName);
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file, true));
            // 调用ITransferOAService的setDocTransactInfo的uploadStreamAttachFile方法
            Long offset = Long.valueOf(0);
            int uploadBlockSize = 10240;
            byte[] buffer = this.transferAipCaseService.uploadStreamAttachFile(remoteFileName, offset, uploadBlockSize);
            while (buffer.length > 0) {
                bufferedOutputStream.write(buffer, 0, buffer.length);
                offset += buffer.length;
                buffer = new byte[uploadBlockSize];
                buffer = this.transferAipCaseService.uploadStreamAttachFile(remoteFileName, offset, uploadBlockSize);
            }
            // 将附件localData标志置为本地数据("1")
            this.aipCaseAttachService.saveDocFileAttachForLocal(attach, localFileName);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                } catch (IOException exf) {
                    throw new RuntimeException(exf);
                }
            }
            isDownLoadAttachRunning = false;
        }
    }

    /**
     * 获取企业处罚记录
     */
    // @Scheduled(cron = "20/10 * * * * ?")
    public void getAipCasePunishDataTask() {
        if (isAipCasePunishRunning) {
            return;
        }
        isAipCasePunishRunning = true;
        try {
            AipPunishInfo info = this.punishEntpService.selectMaxInputDate();
            // 若lastOutputTime无效则不做下一步
            if (info.getPunishTime() == null && info.getRmPunishEntpId() == null) {
                return;
            }
            String json = this.transferAipCaseService.selectPunishInfo(info.getPunishTime(), info.getRmPunishEntpId());
            // 将案件办理的待办记录写入待办事宜表
            if (Tools.isEmptyString(json)) {
                return;
            }
            // 将json字符串还原回PendingCommandInfo对象，再循环将对象插入FdFordo表
            JsonMapper mapper = JsonMapper.getInstance();
            JavaType javaType = mapper.constructParametricType(ArrayList.class, AipPunishInfo.class);
            List<AipPunishInfo> datas = (List<AipPunishInfo>) mapper.readValue(json, javaType);
            this.punishEntpService.savePunishInfo(datas);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            isAipCasePunishRunning = false;
        }
    }
}
