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
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.commoninfo.service.PubInfoService;
import cn.com.chaochuang.datacenter.bean.DocFileUpdate;
import cn.com.chaochuang.datacenter.domain.DataUpdate;
import cn.com.chaochuang.datacenter.service.DataUpdateService;
import cn.com.chaochuang.docwork.domain.DocFileAttach;
import cn.com.chaochuang.docwork.reference.FordoSource;
import cn.com.chaochuang.docwork.service.DocFileAttachService;
import cn.com.chaochuang.docwork.service.DocFileService;
import cn.com.chaochuang.docwork.service.FdFordoService;
import cn.com.chaochuang.task.bean.DocFileInfo;
import cn.com.chaochuang.task.bean.PendingCommandInfo;
import cn.com.chaochuang.task.bean.PubInfoBean;
import cn.com.chaochuang.webservice.server.ITransferOAService;

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

    /** 附件存放根路径 */
    @Value("${upload.rootpath}")
    private String               rootPath;

    /** 公文附件存放相对路径 */
    @Value("${docfile.attach.path}")
    private String               docFileAttachPath;

    private static boolean       isRunning = false;

    /**
     * 向OA获取待办事宜数据 每5分钟进行一次数据获取
     */
    // @Scheduled(cron = "0 1/1 * * * ?")
    public void getFordoDataTask() {
        if (isRunning) {
            return;
        }
        isRunning = true;
        try {
            // 获取当前待办表中最大的数据导入时间值，若无法获取时间值则获取距离当前时间一个月的时间值
            PendingCommandInfo info = this.fdFordoService.selectMaxInputDate();
            // 若lastOutputTime无效则不做下一步
            if (Tools.isEmptyString(info.getLastSendTime()) && info.getRmPendingItemId() == null) {
                return;
            }
            // 读取当前待办事宜表中最大的rmPendingId值，再调用transferOAService的getPendingItemInfo方法
            String json = this.transferOAService.selectPendingItemInfo(info.getLastSendTime(),
                            info.getRmPendingItemId());
            // 将json字符串还原回PendingCommandInfo对象，再循环将对象插入FdFordo表
            try {
                ObjectMapper mapper = new ObjectMapper();
                JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class,
                                PendingCommandInfo.class);
                List<PendingCommandInfo> datas = (List<PendingCommandInfo>) mapper.readValue(json, javaType);
                this.fdFordoService.insertFdFordos(datas, FordoSource.公文);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            isRunning = false;
        }

    }

    /**
     * 向OA获取通讯录数据
     */
    // @Scheduled(cron = "0 1/2 * * * ?")
    public void getDeplinkmanDataTask() {

    }

    /**
     * 向OA获取公文数据 每1分钟进行一次数据获取
     */
    // @Scheduled(cron = "0 1/1 * * * ?")
    public void getDocFileDataTask() {
        String lastInputTime = this.fileService.getDocFileMaxInputDate();
        if (!Tools.isEmptyString(lastInputTime)) {
            String json = this.transferOAService.getDocTransactInfo(lastInputTime);
            try {
                ObjectMapper mapper = new ObjectMapper();
                JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, DocFileInfo.class);
                List<DocFileInfo> datas = (List<DocFileInfo>) mapper.readValue(json, javaType);
                fileService.saveDocFilesDatas(datas);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    /**
     * 提交公文修改数据
     */
    // @Scheduled(cron = "0 1/1 * * * ?")
    public void commintDocFileDataTask() {
        if (isRunning) {
            return;
        }
        isRunning = true;
        try {
            // 扫描DataUpdate数据列表，条件：workType=00;operationType=update
            List<DataUpdate> datas = this.dataUpdateService.selectDocFileDataUpdate();
            // 每次仅处理列表的第一条记录
            if (!Tools.isNotEmptyList(datas)) {
                return;
            }
            DataUpdate dataUpdate = (DataUpdate) datas.get(0);
            // 将DataUpdate的centent字符串转成DocFileUpdate对象，补全webservice所需的条件字段
            ObjectMapper mapper = new ObjectMapper();
            DocFileUpdate docFileUpdate = mapper.readValue(dataUpdate.getContent(), DocFileUpdate.class);
            // 将DocFileUpdate再转成json字符串，调用ITransferOAService的setDocTransactInfo方法修改OA端数据
            String updateInfoJson = mapper.writeValueAsString(docFileUpdate);
            transferOAService.setDocTransactInfo(updateInfoJson);
            // 删除DataUpdate对象
            this.dataUpdateService.delete(dataUpdate);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            isRunning = false;
        }
    }

    /**
     * 获取公文的附件，拉到本地存储
     */
    @Scheduled(cron = "0 1/1 * * * ?")
    public void getDocFileAttachTask() {
        if (isRunning) {
            return;
        }
        isRunning = true;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            String localFilePath = this.rootPath + this.docFileAttachPath;
            // 获取公文OA的附件 查询DocFileAttach中localData为非本地数据("0")的数据，一次处理一个文件
            List<DocFileAttach> datas = this.docFileAttachService.selectUnLocalAttach();
            if (!Tools.isNotEmptyList(datas)) {
                return;
            }
            DocFileAttach attach = (DocFileAttach) datas.get(0);
            String localFileName = localFilePath + attach.getSaveName();
            String remoteFileName = attach.getSavePath();
            File file = new File(localFileName);
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
            isRunning = false;
        }
    }

    /**
     * 向OA获取公告数据 每5分钟进行一次数据获取
     */
    @Scheduled(cron = "0 1/1 * * * ?")
    public void getPubInfoDataTask() {
        String lastInputTime = this.pubInfoService.selectMaxInputDate();
        if (!Tools.isEmptyString(lastInputTime)) {
            String json = this.transferOAService.getPublicDataInfo(lastInputTime);
            if (json.equals("")) {
                System.out.println("json为空");
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

    }

}
