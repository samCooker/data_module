/*
 * FileName:    MobileOADataTaskService.java
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.com.chaochuang.common.user.service.SysDepartmentService;
import cn.com.chaochuang.common.user.service.SysUserService;
import cn.com.chaochuang.common.util.JsonMapper;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.commoninfo.service.DepLinkmanService;
import cn.com.chaochuang.commoninfo.service.PubInfoService;
import cn.com.chaochuang.datacenter.domain.DataUpdate;
import cn.com.chaochuang.datacenter.reference.ExecuteFlag;
import cn.com.chaochuang.datacenter.reference.WorkType;
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
import cn.com.chaochuang.task.bean.OAPendingHandleInfo;
import cn.com.chaochuang.task.bean.OaSubmitInfo;
import cn.com.chaochuang.task.bean.PubInfoBean;
import cn.com.chaochuang.webservice.server.ITransferOAService;

import com.fasterxml.jackson.databind.JavaType;

/**
 * @author LLM
 *
 */
@Component
public class MobileOADataTaskService {
    /** webservice 函数库 */
    @Autowired
    private ITransferOAService   transferOAService;

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
    private DepLinkmanService    depLinkmanService;

    /** 附件存放根路径 */
    @Value("${upload.rootpath}")
    private String               rootPath;

    /** 公文附件存放相对路径 */
    @Value("${docfile.attach.path}")
    private String               docFileAttachPath;

    /** 获取公文阻塞标识 */
    private static boolean       isGetDocFileRunning     = false;
    /** 提交公文阻塞标识 */
    private static boolean       isCommitDocFileRunning  = false;
    /** 下载公文附件阻塞标识 */
    private static boolean       isDownLoadAttachRunning = false;
    /** 获取公告阻塞标识 */
    private static boolean       isGetPubInfoDataRunning = false;
    /** 获取待办阻塞标识 */
    private static boolean       isFordoRunning          = false;

    /**
     * 向OA获取待办事宜数据 每5分钟进行一次数据获取
     */
    // @Scheduled(cron = "8/15 * * * * ?")
    public void getFordoDataTask() {
        if (isFordoRunning) {
            return;
        }
        isFordoRunning = true;
        try {
            // 获取当前待办表中公文待办中最大的数据导入时间值，若无法获取时间值则获取距离当前时间一个月的时间值
            OAPendingHandleInfo info = this.fdFordoService.selectMaxInputDate(FordoSource.公文);
            // 若lastOutputTime无效则不做下一步
            if (info.getLastSendTime() == null && info.getRmPendingItemId() == null) {
                return;
            }
            // 读取当前待办事宜表中最大的rmPendingId值，再调用transferOAService的getPendingItemInfo方法
            String json = this.transferOAService.selectPendingItemInfo(
                            (info.getLastSendTime() != null) ? Tools.DATE_TIME_FORMAT.format(info.getLastSendTime())
                                            : "", info.getRmPendingItemId());
            // 将OA的待办记录写入待办事宜表
            this.saveFdFordo(json, FordoSource.公文);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            isFordoRunning = false;
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
            JsonMapper mapper = JsonMapper.getInstance();
            JavaType javaType = mapper.constructParametricType(ArrayList.class, OAPendingHandleInfo.class);
            List<OAPendingHandleInfo> datas = (List<OAPendingHandleInfo>) mapper.readValue(jsonData, javaType);
            this.fdFordoService.insertFdFordos(datas, fdSource);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 向OA获取公文数据 每1分钟进行一次数据获取
     */
    // @Scheduled(cron = "5/10 * * * * ?")
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
                    JsonMapper mapper = JsonMapper.getInstance();
                    JavaType javaType = mapper.constructParametricType(ArrayList.class, DocFileInfo.class);
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
    // @Scheduled(cron = "3/10 * * * * ?")
    public void commintDocFileDataTask() {
        if (isCommitDocFileRunning) {
            return;
        }
        isCommitDocFileRunning = true;
        DataUpdate dataUpdate = null;
        try {
            // 扫描DataUpdate数据列表，条件：workType=00;operationType=update
            List<DataUpdate> datas = this.dataUpdateService.selectDocFileDataUpdate(WorkType.公文办理提交);
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
            JsonMapper mapper = JsonMapper.getInstance();
            OaSubmitInfo nodeInfo = mapper.readValue(dataUpdate.getContent(), OaSubmitInfo.class);
            fileService.deleteDataUpdateAndFordo(dataUpdate, nodeInfo, backInfo);
        } catch (Exception ex) {
            dataUpdate.setExecuteFlag(ExecuteFlag.执行错误);
            dataUpdate.setErrorInfo(ex.getClass().getName());
            this.dataUpdateService.getRepository().save(dataUpdate);
            ex.printStackTrace();
        } finally {
            isCommitDocFileRunning = false;
        }
    }

    /**
     * 获取公文的附件，拉到本地存储
     */
    // @Scheduled(cron = "15/15 * * * * ?")
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
                bufferedOutputStream.write(buffer, 0, buffer.length);
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
    // @Scheduled(cron = "40/40 * * * * ?")
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
                try {
                    if (StringUtils.isNotEmpty(json)) {
                        JsonMapper mapper = JsonMapper.getInstance();
                        JavaType javaType = mapper.constructParametricType(ArrayList.class, PubInfoBean.class);
                        List<PubInfoBean> datas = (List<PubInfoBean>) mapper.readValue(json, javaType);
                        pubInfoService.savePubInfoDatas(datas);
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        } finally {
            isGetPubInfoDataRunning = false;
        }
    }
}
