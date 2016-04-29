/*
 * FileName:    MobileAppDataTaskService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年5月28日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.task;

import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.appflow.bean.AppFlowPendingHandleInfo;
import cn.com.chaochuang.appflow.domain.AppItemAttach;
import cn.com.chaochuang.appflow.service.AppItemAttachService;
import cn.com.chaochuang.appflow.service.FdFordoAppService;
import cn.com.chaochuang.common.util.HttpClientHelper;
import cn.com.chaochuang.common.util.JsonMapper;
import cn.com.chaochuang.common.util.ScheduleTaskHelper;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.datacenter.domain.DataUpdate;
import cn.com.chaochuang.datacenter.reference.WorkType;
import cn.com.chaochuang.datacenter.service.DataUpdateService;
import cn.com.chaochuang.task.bean.WebServiceNodeInfo;
import cn.com.chaochuang.webservice.server.SuperviseWebService;
import com.fasterxml.jackson.databind.JavaType;
import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author LLM
 *
 */
@Component
public class MobileAppDataTaskService {

    @Value("${supervise.userName}")
    private String                     userName;
    @Value("${supervise.pwd}")
    private String                     pwd;
    @Value("${supervise.baseUrl}")
    private String                     baseUrl;
    @Value("${supervise.loginUrl}")
    private String                     loginUrl;
    @Value("${supervise.getFordoDataUrl}")
    private String                     getFordoDataUrl;
    @Value("${supervise.getSuperviseDataUrl}")
    private String                     getSuperviseDataUrl;
    @Value("${supervise.submitUrl}")
    private String                     submitUrl;
    @Value("${supervise.downloadUrl}")
    private String                     downloadUrl;
    /** 附件存放根路径 */
    @Value("${upload.rootpath}")
    private String                     rootPath;
    /** 公文附件存放相对路径 */
    @Value("${supervisefile.attach.path}")
    private String                     docFileAttachPath;

    @Autowired
    private FdFordoAppService          fdFordoAppService;
    @Autowired
    private SuperviseWebService        superviseWebService;
    @Autowired
    private DataUpdateService          dataUpdateService;
    @Autowired
    private AppItemAttachService       appItemAttachService;
    /** 创建httpClient对象 */
    private static CloseableHttpClient httpClient              = HttpClientHelper.initHttpClient();
    /** 获取公文阻塞标识 */
    private static boolean             isFordoRunning          = false;
    /** 提交行政审批数据标识 */
    private static boolean             isSubmitDataRunning     = false;
    /** 下载公文附件阻塞标识 */
    private static boolean             isDownLoadAttachRunning = false;
    /** 是否正在登录 */
    private static boolean             isLogingSys             = false;

    /**
     * 向行政审批系统获取待办事宜数据
     */
    @Scheduled(cron = "20/20 * * * * ?")
    // @Scheduled(cron = "15 0/2 * * * ?")
    public void getFordoDataTask() {
        if (isFordoRunning) {
            return;
        }
        isFordoRunning = true;
        try {
            // 获取当前待办表中公文待办中最大的id，若无法获取时间值则获取距离当前时间一个月的时间值
            AppFlowPendingHandleInfo info = this.fdFordoAppService.selectMaxInputDate();
            if (info.getLastSendTime() == null && StringUtils.isBlank(info.getRmPendingId())) {
                return;
            }
            // 使用xfire方式获取数据，由于不能传null值，所以对null值进行处理
            if (info.getLastSendTime() == null) {
                info.setLastSendTime(new Date());
            }
            if (StringUtils.isBlank(info.getRmPendingId())) {
                info.setRmPendingId("0");
            }
            String json = superviseWebService.selectPendingHandleList(info.getLastSendTime(), new Long(info.getRmPendingId()));
            if (ScheduleTaskHelper.isFormalDataMsg(json,"行政审批获取待办")) {//远程系统出现异常时返回的字符串中包含 @@exception@@
                JsonMapper mapper = JsonMapper.getInstance();
                JavaType javaType = mapper.constructParametricType(ArrayList.class, AppFlowPendingHandleInfo.class);
                List<AppFlowPendingHandleInfo> datas = mapper.readValue(json, javaType);
                this.fdFordoAppService.insertFdFordos(datas);
                ScheduleTaskHelper.taskLogger.debug("行政审批待办任务已开启：time="+info.getLastSendTime()+",id:"+info.getRmPendingId()+",数据："+datas.size()+"条");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            ScheduleTaskHelper.taskLogger.error(Tools.traceToString(ex));
        } finally {
            isFordoRunning = false;
        }
    }

    /**
     * 登录系统
     */
    public void loginSuperviseSys() {
        if (isLogingSys) {
            return;
        }
        try {
            httpClient=HttpClientHelper.initHttpClient();
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("account", userName));
            params.add(new BasicNameValuePair("password", pwd));
            boolean isLoging = HttpClientHelper.loginSys(httpClient, baseUrl + loginUrl, params, HttpClientHelper.ENCODE_GBK);
            HttpClientHelper.httpClientLogger.debug("用户"+userName+"登录状态："+isLoging);
        } catch (Exception e) {
            // 写入日志
            HttpClientHelper.httpClientLogger.error("登录行政审批出现异常："+Tools.traceToString(e));
            e.printStackTrace();
        } finally {
            isLogingSys = false;
        }
    }

    /**
     * 获取行政审批数据 (已修改为实时获取，该方法暂时无用)
     */
    // ////@Scheduled(cron = "20/20 * * * * ?")
    // public void getAppItemDataTask() {
    // if (isAppItemDataRunning) {
    // return;
    // }
    // isAppItemDataRunning = true;
    // try {
    // // 获取未下载审批数据的待办事宜，即localData=0的数据
    // List<FdFordoApp> datas = this.fdFordoAppService.selectUnLocalData(new PageRequest(0, 10));
    // if (!Tools.isNotEmptyList(datas)) {
    // isAppItemDataRunning = false;
    // return;
    // }
    // String pendingIds = Tools.changeArrayToString(datas, "rmPendingId", ",", false);
    // // 参数设置
    // List<NameValuePair> params = new ArrayList<NameValuePair>();
    // params.add(new BasicNameValuePair("pendingIds", pendingIds));
    // // 发送请求
    // String json = HttpClientHelper.doPost(httpClient, baseUrl + getSuperviseDataUrl, params,
    // HttpClientHelper.ENCODE_GBK);
    // if (StringUtils.isNotBlank(json)) {
    // if (HttpClientHelper.RE_LOGIN.equals(json)) {
    // loginSuperviseSys();
    // } else {
    // // 将行政审批的待办记录写入待办事宜表
    // JsonMapper mapper = JsonMapper.getInstance();
    // JavaType javaType = mapper.constructParametricType(ArrayList.class, AppFlowShowData.class);
    // List<AppFlowShowData> appDatas = mapper.readValue(json, javaType);
    // // 保存并修改待办事宜 localData=1
    // this.appItemApplyService.saveAppItemApplyDatas(appDatas);
    // }
    // }
    // } catch (Exception ex) {
    // ex.printStackTrace();
    // } finally {
    // isAppItemDataRunning = false;
    // }
    // }

    /**
     * 提交审批项数据
     */
    @Scheduled(cron = "20/15 * * * * ?")
    // @Scheduled(cron = "25 0/2 * * * ?")
    public void commintSuperviseDataTask() {
        if (isSubmitDataRunning) {
            return;
        }
        isSubmitDataRunning = true;
        DataUpdate dataUpdate = null;
        try {
            // 扫描DataUpdate数据列表，条件：workType=02;operationType=update
            List<DataUpdate> datas = this.dataUpdateService.selectDocFileDataUpdate(WorkType.行政审批提交);
            // 每次仅处理列表的第一条记录
            if (!Tools.isNotEmptyList(datas)) {
                return;
            }
            dataUpdate = (DataUpdate) datas.get(0);
            // 获取要提交的json字符串

            HttpClientHelper.httpClientLogger.debug("行政审批--提交："+dataUpdate.getContent());

            JsonMapper mapper = JsonMapper.getInstance();
            WebServiceNodeInfo nodeInfo = mapper.readValue(dataUpdate.getContent(), WebServiceNodeInfo.class);
            this.submitSuperviseByHttpClient(dataUpdate,nodeInfo);
        } catch (Exception ex) {
            // 保存错误信息,由于字段长度限制，对字符串进行截取
            String error = Tools.traceToString(ex);
            dataUpdateService.saveErrorInfo(dataUpdate, error.substring(0,error.length()>950?950:error.length()));
            // 写入日志
            ScheduleTaskHelper.taskLogger.error("行政审批提交数据任务异常："+error);

            ex.printStackTrace();
        } finally {
            isSubmitDataRunning = false;
        }
    }

    /**
     * 使用httpclient方式提交审批数据
     * @param dataUpdate
     * @param nodeInfo
     */
    private void submitSuperviseByHttpClient(DataUpdate dataUpdate, WebServiceNodeInfo nodeInfo) {
        // 参数设置
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("approveContent", nodeInfo.getApproveContent()));
        params.add(new BasicNameValuePair("assignee", nodeInfo.getAssignee()));
        params.add(new BasicNameValuePair("next", nodeInfo.getNext()));
        params.add(new BasicNameValuePair("nodeType", nodeInfo.getNodeType()));
        params.add(new BasicNameValuePair("timeLimitFlag", nodeInfo.getTimeLimitFlag()));
        params.add(new BasicNameValuePair("nodeId", nodeInfo.getNodeId() + ""));
        params.add(new BasicNameValuePair("pendingHandleId", nodeInfo.getPendingHandleId() + ""));
        params.add(new BasicNameValuePair("userId", nodeInfo.getUserId() + ""));
        params.add(new BasicNameValuePair("noPendingHandle", nodeInfo.isNoPendingHandle() + ""));
        String json = HttpClientHelper.doPost(httpClient, baseUrl + submitUrl, params, HttpClientHelper.ENCODE_GBK);
        HttpClientHelper.httpClientLogger.debug("行政审批--提交后返回信息："+json);
        if (StringUtils.isNotBlank(json)) {
            if (HttpClientHelper.RE_LOGIN.equals(json)) {
                loginSuperviseSys();
            } else if(DataUpdate.SUBMIT_SUCCESS.equals(json)){
                // 删除DataUpdate对象
                dataUpdateService.delete(dataUpdate);
            }else {
                // 保存错误信息
                dataUpdateService.saveErrorInfo(dataUpdate, json.substring(0,json.length()>950?950:json.length()));
                //写入日志
                HttpClientHelper.httpClientLogger.error(json);
            }
        }
    }

    /**
     * 获取公文的附件，拉到本地存储
     */
    @Scheduled(cron = "15/20 * * * * ?")
    // @Scheduled(cron = "5 0/1 * * * ?")
    public void getDocFileAttachTask() {
        if (isDownLoadAttachRunning) {
            return;
        }
        isDownLoadAttachRunning = true;
        BufferedOutputStream bufferedOutputStream = null;
        AppItemAttach attach = null;
        try {
            String localFilePath = this.rootPath + this.docFileAttachPath + Tools.DATE_FORMAT4.format(new Date());
            // 获取公文OA的附件 查询DocFileAttach中localData为非本地数据("0")的数据，一次处理一个文件
            List<AppItemAttach> datas = this.appItemAttachService.selectUnLocalAttach();
            if (!Tools.isNotEmptyList(datas)) {
                return;
            }
            attach = (AppItemAttach) datas.get(0);
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
            byte[] buffer = this.superviseWebService.uploadStreamAttachFile(remoteFileName, offset, uploadBlockSize);
            while (buffer.length > 0) {
                bufferedOutputStream.write(buffer, 0, buffer.length);
                offset += buffer.length;
                buffer = new byte[uploadBlockSize];
                buffer = this.superviseWebService.uploadStreamAttachFile(remoteFileName, offset, uploadBlockSize);
            }
            // 将附件localData标志置为本地数据("1")
            appItemAttachService.saveAttachForLocal(attach, LocalData.有本地数据, localFileName);
        } catch (Exception ex) {
            if (attach != null) {
                appItemAttachService.saveAttachForLocal(attach, LocalData.获取数据错误, null);
            }
            ex.printStackTrace();
            ScheduleTaskHelper.taskLogger.error("行政审批附件获取任务异常："+Tools.traceToString(ex));
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
     * 获取httpClient实体
     *
     * @return the httpClient
     */
    public static CloseableHttpClient getHttpClient() {
        return httpClient;
    }

}
