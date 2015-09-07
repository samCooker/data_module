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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
import cn.com.chaochuang.datacenter.reference.ExecuteFlag;
import cn.com.chaochuang.datacenter.reference.WorkType;
import cn.com.chaochuang.datacenter.service.DataUpdateService;
import cn.com.chaochuang.task.bean.WebServiceNodeInfo;

import com.fasterxml.jackson.databind.JavaType;

/**
 * @author LLM
 *
 */
@Component
public class MobileAppDataTaskService {

    @Value("${httpClient.userName}")
    private String                  userName;
    @Value("${httpClient.pwd}")
    private String                  pwd;
    @Value("${httpClient.loginUrl}")
    private String                  loginUrl;
    @Value("${httpClient.getFordoDataUrl}")
    private String                  getFordoDataUrl;
    @Value("${httpClient.getSuperviseDataUrl}")
    private String                  getSuperviseDataUrl;
    @Value("${httpClient.submitUrl}")
    private String                  submitUrl;
    @Value("${httpClient.downloadUrl}")
    private String                  downloadUrl;

    @Autowired
    private FdFordoAppService       fdFordoAppService;
    @Autowired
    private AppItemApplyService     appItemApplyService;
    @Autowired
    private DataUpdateService       dataUpdateService;
    @Autowired
    private AppItemAttachService    appItemAttachService;
    /** 附件存放根路径 */
    @Value("${upload.rootpath}")
    private String                  rootPath;

    /** 公文附件存放相对路径 */
    @Value("${supervisefile.attach.path}")
    private String                  docFileAttachPath;
    /** 获取公文阻塞标识 */
    private static boolean          isFordoRunning          = false;
    /** 获取行政审批数据标识 */
    private static boolean          isAppItemDataRunning    = false;
    /** 提交行政审批数据标识 */
    private static boolean          isSubmitDataRunning     = false;
    /** 下载公文附件阻塞标识 */
    private static boolean          isDownLoadAttachRunning = false;
    /** 是否正在登录 */
    private static boolean          isLoging                = false;

    private static HttpClientHelper httpClientHelper        = HttpClientHelper.newHttpClientHelper();

    /**
     * 向行政审批系统获取待办事宜数据 每5秒进行一次数据获取
     */
    @Scheduled(cron = "5/5 * * * * ?")
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
            String json = httpClientHelper.doPost(new HttpPost(getFordoDataUrl), params);
            if (HttpClientHelper.RE_LOGIN.equals(json)) {
                loginSuperviseSys();
            } else {
                this.saveFdFordo(json);
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
    private void loginSuperviseSys() {
        if (isLoging) {
            return;
        }
        isLoging = true;
        try {
            boolean isLogin = httpClientHelper.loginSuperviseSys(userName, pwd, new HttpPost(loginUrl));
            System.out.println(isLogin);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            isLoging = false;
        }

    }

    /**
     * 保存待办记录
     *
     * @param jsonData
     */
    private void saveFdFordo(String jsonData) {
        // 将json字符串还原回PendingCommandInfo对象，再循环将对象插入FdFordo表
        JsonMapper mapper = JsonMapper.getInstance();
        JavaType javaType = mapper.constructParametricType(ArrayList.class, AppFlowPendingHandleInfo.class);
        List<AppFlowPendingHandleInfo> datas = (List<AppFlowPendingHandleInfo>) mapper.readValue(jsonData, javaType);
        this.fdFordoAppService.insertFdFordos(datas);
    }

    /**
     * 获取行政审批数据
     */
    @Scheduled(cron = "8/8 * * * * ?")
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
            String json = httpClientHelper.doPost(new HttpPost(getSuperviseDataUrl), params);
            if (HttpClientHelper.RE_LOGIN.equals(json)) {
                loginSuperviseSys();
            } else {
                // 将行政审批的待办记录写入待办事宜表
                JsonMapper mapper = JsonMapper.getInstance();
                JavaType javaType = mapper.constructParametricType(ArrayList.class, AppFlowShowData.class);
                List<AppFlowShowData> appDatas = (List<AppFlowShowData>) mapper.readValue(json, javaType);
                // 保存并修改待办事宜 localData=1
                this.appItemApplyService.saveAppItemApplyDatas(appDatas);
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
    @Scheduled(cron = "10/15 * * * * ?")
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
            if (dataUpdate == null) {
                return;
            }
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
            String json = httpClientHelper.doPost(new HttpPost(submitUrl), params);
            if (StringUtils.isBlank(json)) {
                return;
            }
            if (HttpClientHelper.RE_LOGIN.equals(json)) {
                loginSuperviseSys();
            } else {
                appItemApplyService.deleteDataUpdateAndFordo(dataUpdate, nodeInfo, json);
            }
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
        try {
            String localFilePath = this.rootPath + this.docFileAttachPath + Tools.DATE_FORMAT4.format(new Date());
            // 获取公文OA的附件 查询DocFileAttach中localData为非本地数据("0")的数据，一次处理一个文件
            List<AppItemAttach> datas = this.appItemAttachService.selectUnLocalAttach();
            if (!Tools.isNotEmptyList(datas)) {
                return;
            }
            AppItemAttach attach = (AppItemAttach) datas.get(0);
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
            downloadGet = new HttpGet(downloadUrl);
            CloseableHttpResponse response = httpClientHelper.doGet(downloadGet, params);
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
                this.appItemAttachService.saveDocFileAttachForLocal(attach, localFileName);
            } else if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
                loginSuperviseSys();
            }
        } catch (Exception ex) {
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
     * @return the httpClientHelper
     */
    public static HttpClientHelper getHttpClientHelper() {
        return httpClientHelper;
    }

}
