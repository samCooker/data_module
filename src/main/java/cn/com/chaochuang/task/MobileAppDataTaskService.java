/*
 * FileName:    MobileAppDataTaskService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年5月28日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.task;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;

import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.appflow.bean.AppFlowPendingHandleInfo;
import cn.com.chaochuang.appflow.bean.AppFlowShowData;
import cn.com.chaochuang.appflow.domain.AppItemAttach;
import cn.com.chaochuang.appflow.domain.FdFordoApp;
import cn.com.chaochuang.appflow.service.AppItemApplyService;
import cn.com.chaochuang.appflow.service.AppItemAttachService;
import cn.com.chaochuang.appflow.service.FdFordoAppService;
import cn.com.chaochuang.common.util.HttpClientHelper;
import cn.com.chaochuang.common.util.JsonMapper;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.datacenter.domain.DataUpdate;
import cn.com.chaochuang.datacenter.reference.WorkType;
import cn.com.chaochuang.datacenter.service.DataUpdateService;
import cn.com.chaochuang.task.bean.WebServiceNodeInfo;

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
    private AppItemApplyService        appItemApplyService;
    @Autowired
    private DataUpdateService          dataUpdateService;
    @Autowired
    private AppItemAttachService       appItemAttachService;
    /** 创建httpClient对象 */
    private static CloseableHttpClient httpClient              = HttpClientHelper.initHttpClient();
    /** 获取公文阻塞标识 */
    private static boolean             isFordoRunning          = false;
    /** 获取行政审批数据标识 */
    private static boolean             isAppItemDataRunning    = false;
    /** 提交行政审批数据标识 */
    private static boolean             isSubmitDataRunning     = false;
    /** 下载公文附件阻塞标识 */
    private static boolean             isDownLoadAttachRunning = false;
    /** 是否正在登录 */
    private static boolean             isLogingSys             = false;

    /**
     * 向行政审批系统获取待办事宜数据
     */
    // @Scheduled(cron = "15/15 * * * * ?")
    public void getFordoDataTask() {
        if (isFordoRunning) {
            return;
        }
        isFordoRunning = true;
        try {
            // 获取当前待办表中公文待办中最大的id，若无法获取时间值则获取距离当前时间一个月的时间值
            AppFlowPendingHandleInfo info = this.fdFordoAppService.selectMaxInputDate();
            // 参数设置
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            if (info.getLastSendTime() != null) {
                params.add(new BasicNameValuePair("lastOutputTime", info.getLastSendTime().getTime() + ""));
            }
            if (info.getRmPendingId() != null) {
                params.add(new BasicNameValuePair("pendingHandleId", info.getRmPendingId() + ""));
            }
            // 发送请求
            String json = HttpClientHelper.doPost(httpClient, baseUrl + getFordoDataUrl, params, HttpClientHelper.ENCODE_GBK);
            if (StringUtils.isNotBlank(json)) {
                if (HttpClientHelper.RE_LOGIN.equals(json)) {
                    loginSuperviseSys();
                } else {
                    // 将json字符串还原回PendingCommandInfo对象，再循环将对象插入FdFordo表
                    JsonMapper mapper = JsonMapper.getInstance();
                    JavaType javaType = mapper.constructParametricType(ArrayList.class, AppFlowPendingHandleInfo.class);
                    List<AppFlowPendingHandleInfo> datas = mapper.readValue(json, javaType);
                    this.fdFordoAppService.insertFdFordos(datas);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("account", userName));
            params.add(new BasicNameValuePair("password", pwd));
            boolean isLoging = HttpClientHelper.loginSys(httpClient, baseUrl + loginUrl, params, HttpClientHelper.ENCODE_GBK);
            System.out.println(isLoging);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            isLogingSys = false;
        }
    }

    /**
     * 获取行政审批数据
     */
    @Scheduled(cron = "20/20 * * * * ?")
    public void getAppItemDataTask() {
        if (isAppItemDataRunning) {
            return;
        }
        isAppItemDataRunning = true;
        try {
            // 获取未下载审批数据的待办事宜，即localData=0的数据
            List<FdFordoApp> datas = this.fdFordoAppService.selectUnLocalData(new PageRequest(0, 10));
            if (!Tools.isNotEmptyList(datas)) {
                isAppItemDataRunning = false;
                return;
            }
            String pendingIds = Tools.changeArrayToString(datas, "rmPendingId", ",", false);
            // 参数设置
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("pendingIds", pendingIds));
            // 发送请求
            String json = HttpClientHelper.doPost(httpClient, baseUrl + getSuperviseDataUrl, params, HttpClientHelper.ENCODE_GBK);
            if (StringUtils.isNotBlank(json)) {
                if (HttpClientHelper.RE_LOGIN.equals(json)) {
                    loginSuperviseSys();
                } else {
                    // 将行政审批的待办记录写入待办事宜表
                    JsonMapper mapper = JsonMapper.getInstance();
                    JavaType javaType = mapper.constructParametricType(ArrayList.class, AppFlowShowData.class);
                    List<AppFlowShowData> appDatas = mapper.readValue(json, javaType);
                    // 保存并修改待办事宜 localData=1
                    this.appItemApplyService.saveAppItemApplyDatas(appDatas);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            isAppItemDataRunning = false;
        }
    }

    /**
     * 提交审批项数据
     */
    @Scheduled(cron = "20/15 * * * * ?")
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

            JsonMapper mapper = JsonMapper.getInstance();
            WebServiceNodeInfo nodeInfo = mapper.readValue(dataUpdate.getContent(), WebServiceNodeInfo.class);
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
            if (StringUtils.isNotBlank(json)) {
                if (HttpClientHelper.RE_LOGIN.equals(json)) {
                    loginSuperviseSys();
                } else {
                    if (DataUpdate.SUBMIT_SUCCESS.equals(json)) {
                        // 删除DataUpdate对象
                        dataUpdateService.delete(dataUpdate);
                    } else {
                        // 保存错误信息
                        dataUpdateService.saveErrorInfo(dataUpdate, json);
                    }
                }
            }
        } catch (Exception ex) {
            // 保存错误信息
            dataUpdateService.saveErrorInfo(dataUpdate, ex.getClass().getName());
            ex.printStackTrace();
        } finally {
            isSubmitDataRunning = false;
        }
    }

    /**
     * 获取公文的附件，拉到本地存储
     */
    @Scheduled(cron = "15/20 * * * * ?")
    public void getDocFileAttachTask() {
        if (isDownLoadAttachRunning) {
            return;
        }
        isDownLoadAttachRunning = true;
        BufferedOutputStream bufferedOutputStream = null;
        BufferedInputStream bis = null;
        HttpGet downloadGet = null;
        InputStream is = null;
        AppItemAttach attach = null;
        try {
            String localFilePath = this.rootPath + this.docFileAttachPath + Tools.DATE_FORMAT4.format(new Date());
            // 获取行政审批的附件 查询DocFileAttach中localData为非本地数据("0")的数据，一次处理一个文件
            List<AppItemAttach> datas = this.appItemAttachService.selectUnLocalAttach();
            if (!Tools.isNotEmptyList(datas)) {
                return;
            }
            attach = datas.get(0);
            File file = new File(localFilePath);
            // 目录不存在则建立新目录
            if (!file.exists()) {
                file.mkdirs();
            }
            String localFileName = localFilePath + "/" + attach.getSaveName();
            // 参数设置
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("area", "appitem"));
            params.add(new BasicNameValuePair("fileId", attach.getRmAttachId() + ""));
            downloadGet = new HttpGet(baseUrl + downloadUrl);
            CloseableHttpResponse response = HttpClientHelper.doGetAndReturnResponse(httpClient, downloadGet, params);
            if (response == null) {
                return;
            }
            int statusCode = response.getStatusLine().getStatusCode();
            if (HttpStatus.SC_OK == statusCode) {// 成功返回
                file = new File(localFileName);
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file, true));
                byte[] b = new byte[1024];
                is = response.getEntity().getContent();
                bis = new BufferedInputStream(is);
                int len = -1;
                while ((len = bis.read(b)) != -1) {
                    bufferedOutputStream.write(b, 0, len);
                }
                bufferedOutputStream.flush();
                // 将附件localData标志置为本地数据("1")
                appItemAttachService.saveAttachForLocal(attach, LocalData.有本地数据, localFileName);
            } else if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
                loginSuperviseSys();
            } else {
                // 获取附件错误，将附件状态保存为获取错误
                appItemAttachService.saveAttachForLocal(attach, LocalData.获取数据错误, null);
            }
        } catch (Exception ex) {
            appItemAttachService.saveAttachForLocal(attach, LocalData.获取数据错误, null);
            throw new RuntimeException(ex);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException exf) {
                    throw new RuntimeException(exf);
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (downloadGet != null) {
                downloadGet.releaseConnection();
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
