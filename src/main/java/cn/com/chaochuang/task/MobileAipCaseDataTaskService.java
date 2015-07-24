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

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import cn.com.chaochuang.aipcase.bean.AipCasePendingHandleInfo;
import cn.com.chaochuang.aipcase.bean.AipCaseShowData;
import cn.com.chaochuang.aipcase.domain.AipCaseNoteFile;
import cn.com.chaochuang.aipcase.domain.FdFordoAipcase;
import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.aipcase.service.AipCaseApplyService;
import cn.com.chaochuang.aipcase.service.AipCaseNoteFileService;
import cn.com.chaochuang.aipcase.service.FdFordoAipcaseService;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.docwork.reference.FordoSource;
import cn.com.chaochuang.task.bean.AipCasePendingInfo;
import cn.com.chaochuang.task.bean.AipLawContentData;
import cn.com.chaochuang.webservice.server.ITransferOAService;
import cn.com.chaochuang.webservice.server.aipcasetransfer.AipCaseWebService;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    private ITransferOAService     transferOAService;
    @Autowired
    private AipCaseApplyService    aipCaseApplyService;
    /** FdFordoAipcaseService */
    @Autowired
    private FdFordoAipcaseService  fdFordoService;
    @Autowired
    private AipCaseNoteFileService aipCaseNoteFileService;

    /** 附件存放根路径 */
    @Value("${upload.rootpath}")
    private String                 rootPath;
    /** 文书html文件存放路径 */
    @Value("${htmlfile.savepath}")
    private String                 htmlFilePath;

    /** 获取案件办理阻塞标识 */
    private static boolean         isGetAipCaseRunning   = false;
    /** 获取办案系统待办阻塞标识 */
    private static boolean         isAipCaseFordoRunning = false;
    /** 获取文书信息并转成pdf文件 阻塞标识 */
    private static boolean         isTransferFileRunning = false;

    /**
     * 获取案件办理系统的待办记录
     */
    // @Scheduled(cron = "10/30 * * * * ?")
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
        try {
            // 将json字符串还原回PendingCommandInfo对象，再循环将对象插入FdFordo表
            ObjectMapper mapper = new ObjectMapper();
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class,
                            AipCasePendingInfo.class);
            List<AipCasePendingInfo> datas = (List<AipCasePendingInfo>) mapper.readValue(jsonData, javaType);
            this.fdFordoService.insertFdFordos(datas);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
                ObjectMapper mapper = new ObjectMapper();
                JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class,
                                AipCaseShowData.class);
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
                            new PageRequest(0, 5));
            if (noteFileList != null) {
                ObjectMapper mapper = new ObjectMapper();
                for (AipCaseNoteFile noteFile : noteFileList) {
                    String jsonData = transferAipCaseService.getLawContentData(noteFile.getRmNoteFileId(),
                                    noteFile.getMdfCode());
                    if (StringUtils.isNotEmpty(jsonData)) {
                        AipLawContentData data = mapper.readValue(jsonData, AipLawContentData.class);
                        // 根据返回的文件路径获取文件到本地，并修改loaclData
                        downloadHtmlFile(data);
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
        if (contentData == null) {
            return;
        }
        String localFilePath = this.rootPath + this.htmlFilePath + File.separatorChar
                        + Tools.DATE_FORMAT4.format(new Date()) + File.separator + contentData.getRmCaseApplyId();
        File file = new File(localFilePath);
        // 目录不存在则建立新目录
        if (!file.exists()) {
            file.mkdirs();
        }
        BufferedOutputStream bufferedOutputStream = null;
        try {
            String localFileName = localFilePath + File.separatorChar + contentData.getRmNoteFileId() + ".html";
            String remoteFileName = contentData.getRmFilePath();
            file = new File(localFileName);
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file, true));
            Long offset = Long.valueOf(0);
            int uploadBlockSize = 10240;
            byte[] buffer = transferAipCaseService.uploadStreamAttachFile(remoteFileName, offset, uploadBlockSize);
            while (buffer.length > 0) {
                bufferedOutputStream.write(buffer, 0, uploadBlockSize);
                offset += buffer.length;
                buffer = new byte[uploadBlockSize];
                buffer = transferAipCaseService.uploadStreamAttachFile(remoteFileName, offset, uploadBlockSize);
            }
            // 将附件localData标志置为本地数据("1")
            aipCaseNoteFileService.saveNoteFileForLocal(contentData.getRmNoteFileId(), localFileName);
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
}
