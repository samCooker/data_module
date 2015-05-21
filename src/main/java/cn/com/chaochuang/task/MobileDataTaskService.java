/*
 * FileName:    MobileDataTaskService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月21日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.task;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.com.chaochuang.aipcase.domain.AipCaseApply;
import cn.com.chaochuang.aipcase.service.AipCaseApplyService;
import cn.com.chaochuang.common.user.service.SysDepartmentService;
import cn.com.chaochuang.common.user.service.SysUserService;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.commoninfo.service.DepLinkmanService;
import cn.com.chaochuang.commoninfo.service.PubInfoService;
import cn.com.chaochuang.datacenter.domain.DataUpdate;
import cn.com.chaochuang.datacenter.domain.SysDataChange;
import cn.com.chaochuang.datacenter.reference.DataChangeTable;
import cn.com.chaochuang.datacenter.reference.ExecuteFlag;
import cn.com.chaochuang.datacenter.service.DataUpdateService;
import cn.com.chaochuang.datacenter.service.SysDataChangeService;
import cn.com.chaochuang.docwork.domain.DocFileAttach;
import cn.com.chaochuang.docwork.reference.FordoSource;
import cn.com.chaochuang.docwork.service.DocFileAttachService;
import cn.com.chaochuang.docwork.service.DocFileService;
import cn.com.chaochuang.docwork.service.FdFordoService;
import cn.com.chaochuang.docwork.service.FlowNodeInfoService;
import cn.com.chaochuang.task.bean.DocFileInfo;
import cn.com.chaochuang.task.bean.FlowNodeOpinionsInfo;
import cn.com.chaochuang.task.bean.PendingCommandInfo;
import cn.com.chaochuang.task.bean.PubInfoBean;
import cn.com.chaochuang.webservice.server.ITransferOAService;
import cn.com.chaochuang.webservice.server.aipcasetransfer.AipCaseWebService;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author LLM
 *
 */
@Component
public class MobileDataTaskService {

    /** webservice 函数库 */
    @Autowired
    private ITransferOAService   transferOAService;

    @Autowired
    private AipCaseWebService    transferAipCaseService;

    /** fdFordoService */
    @Autowired
    private FdFordoService       fdFordoService;

    @Autowired
    private PubInfoService       pubInfoService;

    @Autowired
    private DocFileService       fileService;

    @Autowired
    private DataUpdateService    dataUpdateService;

    @Autowired
    private DocFileAttachService docFileAttachService;

    @Autowired
    private FlowNodeInfoService  flowNodeInfoService;

    @Autowired
    private SysDataChangeService dataChangeService;

    @Autowired
    private SysDepartmentService departmentService;

    @Autowired
    private SysUserService       userService;

    @Autowired
    private AipCaseApplyService  aipCaseApplyService;

    @Autowired
    private DepLinkmanService    depLinkmanService;

    /** 附件存放根路径 */
    @Value("${upload.rootpath}")
    private String               rootPath;

    /** 公文附件存放相对路径 */
    @Value("${docfile.attach.path}")
    private String               docFileAttachPath;

    /** 获取待办阻塞标识 */
    private static boolean       isFordoRunning             = false;
    /** 获取公文阻塞标识 */
    private static boolean       isGetDocFileRunning        = false;
    /** 获取案件办理阻塞标识 */
    private static boolean       isGetAipCaseRunning        = false;
    /** 提交公文阻塞标识 */
    private static boolean       isCommitDocFileRunning     = false;
    /** 下载公文附件阻塞标识 */
    private static boolean       isDownLoadAttachRunning    = false;
    /** 获取公告阻塞标识 */
    private static boolean       isGetPubInfoDataRunning    = false;
    /** 获取公告阻塞标识 */
    private static boolean       isGetSysDataChangeRunning  = false;
    /** 获取处理系统数据更改阻塞标识 */
    private static boolean       isDealSysDataChangeRunning = false;
    /** 获取办案系统待办阻塞标识 */
    private static boolean       isAipCaseFordoRunning      = false;

    /**
     * 向OA获取待办事宜数据 每5分钟进行一次数据获取
     */
    @Scheduled(cron = "30/30 * * * * ?")
    public void getFordoDataTask() {
        if (isFordoRunning) {
            return;
        }
        isFordoRunning = true;
        try {
            // 获取当前待办表中公文待办中最大的数据导入时间值，若无法获取时间值则获取距离当前时间一个月的时间值
            PendingCommandInfo info = this.fdFordoService.selectMaxInputDate(FordoSource.公文);
            // 若lastOutputTime无效则不做下一步
            if (Tools.isEmptyString(info.getLastSendTime()) && info.getRmPendingItemId() == null) {
                return;
            }
            // 读取当前待办事宜表中最大的rmPendingId值，再调用transferOAService的getPendingItemInfo方法
            String json = this.transferOAService.selectPendingItemInfo(info.getLastSendTime(), info.getRmPendingItemId());
            // 将OA的待办记录写入待办事宜表
            this.saveFdFordo(json, FordoSource.公文);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            isFordoRunning = false;
        }
    }

    /**
     * 获取案件办理系统的待办记录
     */
    // @Scheduled(cron = "2/10 1/1 * * * ?")
    public void getAipCaseFordo() {
        if (isAipCaseFordoRunning) {
            return;
        }
        isAipCaseFordoRunning = true;
        try {
            PendingCommandInfo info = this.fdFordoService.selectMaxInputDate(FordoSource.行政办案);
            // 若lastOutputTime无效则不做下一步
            if (Tools.isEmptyString(info.getLastSendTime()) && info.getRmPendingItemId() == null) {
                return;
            }
            String json = this.transferAipCaseService.selectPendingItemInfo(info.getLastSendTime(), info.getRmPendingItemId());
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
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, PendingCommandInfo.class);
            List<PendingCommandInfo> datas = (List<PendingCommandInfo>) mapper.readValue(jsonData, javaType);
            this.fdFordoService.insertFdFordos(datas, fdSource);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 获取案件办理数据写入本地表
     */
    // @Scheduled(cron = "20/10 1/1 * * * ?")
    public void getAipCaseDataTask() {
        if (isGetAipCaseRunning) {
            return;
        }
        isGetAipCaseRunning = true;
        try {
            // 获取指定案件登记时间的记录
            String lastInputTime = this.aipCaseApplyService.selectAipCaseMaxInputDate();
            if (!Tools.isEmptyString(lastInputTime)) {
                // 调用远程方法获取案件登记时间大于lastInputTime的案件数据
                String json = transferAipCaseService.getAipCaseApply(lastInputTime);
                // 将json数据转成AipCaseApply列表保存至AipCaseApply表
                if (!Tools.isEmptyString(json)) {
                    // 将json字符串还原回PendingCommandInfo对象，再循环将对象插入FdFordo表
                    ObjectMapper mapper = new ObjectMapper();
                    JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, AipCaseApply.class);
                    List<AipCaseApply> datas = (List<AipCaseApply>) mapper.readValue(json, javaType);
                    this.aipCaseApplyService.saveAipCaseApply(datas);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            isGetAipCaseRunning = false;
        }
    }

    /**
     * 向OA获取公文数据 每1分钟进行一次数据获取
     */
    @Scheduled(cron = "10/20 * * * * ?")
    public void getDocFileDataTask() {
        if (isGetDocFileRunning) {
            return;
        }
        isGetDocFileRunning = true;
        try {
            FlowNodeOpinionsInfo info = this.fileService.getDocFileMaxInputDate();
            if (info.getRmInstnoId() != null || !Tools.isEmptyString(info.getGetDataTime())) {
                String json = this.transferOAService.getDocTransactInfo(info.getGetDataNoId(), info.getGetDataTime());
                if (Tools.isEmptyString(json)) {
                    isGetDocFileRunning = false;
                    return;
                }
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, DocFileInfo.class);
                    List<DocFileInfo> datas = (List<DocFileInfo>) mapper.readValue(json, javaType);
                    fileService.saveDocFilesDatas(datas);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        } finally {
            isGetDocFileRunning = false;
        }

    }

    /**
     * 提交公文修改数据
     */
    @Scheduled(cron = "5/10 * * * * ?")
    public void commintDocFileDataTask() {
        if (isCommitDocFileRunning) {
            return;
        }
        isCommitDocFileRunning = true;
        DataUpdate dataUpdate = null;
        try {
            // 扫描DataUpdate数据列表，条件：workType=00;operationType=update
            List<DataUpdate> datas = this.dataUpdateService.selectDocFileDataUpdate();
            // 每次仅处理列表的第一条记录
            if (!Tools.isNotEmptyList(datas)) {
                return;
            }
            dataUpdate = (DataUpdate) datas.get(0);
            if (dataUpdate == null) {
                return;
            }
            // 获取要提交的json字符串，调用ITransferOAService的setDocTransactInfo方法修改OA端数据
            String backInfo = transferOAService.setDocTransactInfo(dataUpdate.getContent());
            if ("true".equals(backInfo)) {
                // 删除DataUpdate对象
                this.dataUpdateService.delete(dataUpdate);
            } else {
                dataUpdate.setExecuteFlag(ExecuteFlag.执行错误);
                dataUpdate.setErrorInfo(backInfo);
                this.dataUpdateService.getRepository().save(dataUpdate);
            }
        } catch (Exception ex) {
            dataUpdate.setExecuteFlag(ExecuteFlag.执行错误);
            dataUpdate.setErrorInfo(ex.getClass().getName());
            this.dataUpdateService.getRepository().save(dataUpdate);
            throw new RuntimeException(ex);
        } finally {
            isCommitDocFileRunning = false;
        }
    }

    /**
     * 获取公文的附件，拉到本地存储
     */
    @Scheduled(cron = "15/15 * * * * ?")
    public void getDocFileAttachTask() {
        if (isDownLoadAttachRunning) {
            return;
        }
        isDownLoadAttachRunning = true;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            String localFilePath = this.rootPath + this.docFileAttachPath + Tools.DATE_FORMAT4.format(new Date());
            // 获取公文OA的附件 查询DocFileAttach中localData为非本地数据("0")的数据，一次处理一个文件
            List<DocFileAttach> datas = this.docFileAttachService.selectUnLocalAttach();
            if (!Tools.isNotEmptyList(datas)) {
                return;
            }
            DocFileAttach attach = (DocFileAttach) datas.get(0);
            File file = new File(localFilePath);
            // 目录不存在则建立新目录
            if (!file.exists()) {
                file.mkdirs();
            }
            String localFileName = localFilePath + "/" + attach.getSaveName();
            String remoteFileName = attach.getSavePath();
            file = new File(localFileName);
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file, true));
            // 调用ITransferOAService的setDocTransactInfo的uploadStreamAttachFile方法
            Long offset = Long.valueOf(0);
            int uploadBlockSize = 10240;
            byte[] buffer = this.transferOAService.uploadStreamAttachFile(remoteFileName, offset, uploadBlockSize);
            while (buffer.length > 0) {
                bufferedOutputStream.write(buffer, 0, uploadBlockSize);
                offset += buffer.length;
                buffer = new byte[uploadBlockSize];
                buffer = this.transferOAService.uploadStreamAttachFile(remoteFileName, offset, uploadBlockSize);
            }
            // 将附件localData标志置为本地数据("1")
            this.docFileAttachService.saveDocFileAttachForLocal(attach.getId(), localFileName);
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
     * 向OA获取公告数据 每5分钟进行一次数据获取
     */
    @Scheduled(cron = "40/40 * * * * ?")
    public void getPubInfoDataTask() {
        if (isGetPubInfoDataRunning) {
            return;
        }
        isGetPubInfoDataRunning = true;
        try {
            String lastInputTime = this.pubInfoService.selectMaxInputDate();
            if (Tools.isEmptyString(lastInputTime)) {
                Date date = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(Calendar.MONTH, -6);
                lastInputTime = Tools.DATE_TIME_FORMAT.format(calendar.getTime());
            }
            if (!Tools.isEmptyString(lastInputTime)) {
                String json = this.transferOAService.getPublicDataInfo(lastInputTime);
                if (json.equals("")) {
                    // System.out.println("json为空");
                    return;
                }
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, PubInfoBean.class);
                    List<PubInfoBean> datas = (List<PubInfoBean>) mapper.readValue(json, javaType);
                    pubInfoService.savePubInfoDatas(datas);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        } finally {
            isGetPubInfoDataRunning = false;
        }
    }

    /**
     * 获取远程系统修改记录数据
     */
    @Scheduled(cron = "10/10 * * * * ?")
    public void getOADataChange() {
        if (isGetSysDataChangeRunning) {
            return;
        }
        isGetSysDataChangeRunning = true;
        try {
            String json = this.transferOAService.getDataChange();
            if (Tools.isEmptyString(json)) {
                return;
            }
            ObjectMapper mapper = new ObjectMapper();
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, SysDataChange.class);
            List<SysDataChange> datas = (List<SysDataChange>) mapper.readValue(json, javaType);
            this.dataChangeService.saveSysDataChange(datas);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            isGetSysDataChangeRunning = false;
        }
    }

    /**
     * 处理远程系统更改数据
     */
    @Scheduled(cron = "5/5 * * * * ?")
    public void dealDataChange() {
        if (isDealSysDataChangeRunning) {
            return;
        }
        isDealSysDataChangeRunning = true;
        try {
            Page page = this.dataChangeService.findAllByPage(1, 10);
            List<SysDataChange> datas = page.getContent();
            for (SysDataChange item : datas) {
                if (DataChangeTable.公文待办事宜.getKey().equals(item.getChangeTableName())) {
                    // 如果处理的表为oa_pending_handle_dts 则调用fdfordo的方法分析处理过程
                    this.fdFordoService.analysisDataChange(item);
                } else if (DataChangeTable.公文办结.getKey().equals(item.getChangeTableName())) {
                    // 如果处理的表为wf_flo_hisno 则将相关公文的公文状态改为办结（不包括通报）
                    String[] items = item.getChangeScript().split("=");
                    String json = this.transferOAService.getOAHistoryNodes(new Long(items[1]));
                    this.fileService.finishDocFile(json);
                } else if (DataChangeTable.组织结构.getKey().equals(item.getChangeTableName())) {
                    // 组织机构发生变更
                    this.departmentService.analysisDataChange(item);
                } else if (DataChangeTable.人员.getKey().equals(item.getChangeTableName())) {
                    // 人员数据发生变更
                    this.userService.analysisDataChange(item);
                } else if (DataChangeTable.通讯录.getKey().equals(item.getChangeTableName())) {
                    // 通讯录数据发生变更
                    this.depLinkmanService.analysisDataChange(item);
                } else if (DataChangeTable.新闻公告.getKey().equals(item.getChangeTableName())) {
                    // 新闻公告数据发生变更
                }
                // 删除变更数据
                this.dataChangeService.delete(item.getId());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            isDealSysDataChangeRunning = false;
        }
    }
}
