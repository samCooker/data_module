/*
 * FileName:    MobileComplaintDataTask.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月10日 (Shicx) 1.0 Create
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

import javax.annotation.Resource;

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
import org.springframework.stereotype.Component;

import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.casecomplaint.bean.ComplaintSubmitContent;
import cn.com.chaochuang.casecomplaint.domain.CaseComplaintAttach;
import cn.com.chaochuang.casecomplaint.repository.FdFordoCaseRepository;
import cn.com.chaochuang.casecomplaint.service.CaseComplaintAttachService;
import cn.com.chaochuang.casecomplaint.service.CaseComplaintService;
import cn.com.chaochuang.casecomplaint.service.FdFordoCaseService;
import cn.com.chaochuang.common.util.HttpClientHelper;
import cn.com.chaochuang.common.util.JsonMapper;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.datacenter.domain.DataUpdate;
import cn.com.chaochuang.datacenter.domain.SysDataChange;
import cn.com.chaochuang.datacenter.reference.WorkType;
import cn.com.chaochuang.datacenter.service.DataUpdateService;
import cn.com.chaochuang.datacenter.service.SysDataChangeService;

import com.fasterxml.jackson.databind.JavaType;

/**
 * @author Shicx
 *
 */
@Component
public class MobileComplaintDataTask {

    @Value("${complaint.userName}")
    private String                     userName;
    @Value("${complaint.pwd}")
    private String                     pwd;
    @Value("${complaint.baseUrl}")
    private String                     baseUrl;
    @Value("${complaint.loginUrl}")
    private String                     loginUrl;
    @Value("${complaint.getFordoDataUrl}")
    private String                     getFordoDataUrl;
    @Value("${complaint.getComplaintDataUrl}")
    private String                     getComplaintDataUrl;
    @Value("${complaint.submitUrl}")
    private String                     submitUrl;
    @Value("${complaint.downloadUrl}")
    private String                     downloadUrl;
    @Value("${complaint.getChangeDataUrl}")
    private String                     getChangeDataUrl;
    /** 取数据的相隔天数 */
    @Value("${getdata.timeinterval}")
    private String                     timeInterval;
    /** 附件存放根路径 */
    @Value("${upload.rootpath}")
    private String                     rootPath;
    /** 投诉举报附件存放相对路径 */
    @Value("${complaintfile.attach.path}")
    private String                     complaintAttachPath;
    /** 要查询的待办状态（可在spring-utildata-ctx.xml文件配置） */
    @Resource
    private List<String>               complaintStatus;

    @Autowired
    private FdFordoCaseService         fdFordoCaseService;
    @Autowired
    private FdFordoCaseRepository      fdFordoCaseRepository;
    @Autowired
    private CaseComplaintService       caseComplaintService;
    @Autowired
    private DataUpdateService          dataUpdateService;
    @Autowired
    private SysDataChangeService       dataChangeService;
    @Autowired
    private CaseComplaintAttachService caseComplaintAttachService;

    /** 创建httpClient对象 */
    private static HttpClientHelper    httpClientHelper          = HttpClientHelper.newHttpClientHelper();
    /** 是否正在登录 */
    private static boolean             isLoging                  = false;
    /** 正在获取待办标识 */
    private static boolean             isGettingFordoData        = false;
    /** 正在获取投诉举报信息标识 */
    private static boolean             isGettingComplaintData    = false;
    /** 正在提交投诉举报信息标识 */
    private static boolean             isSubmitComplaintData     = false;
    /** 获取修改数据阻塞标识 */
    private static boolean             isGetSysDataChangeRunning = false;
    /** 下载公文附件阻塞标识 */
    private static boolean             isDownLoadAttachRunning   = false;

    /**
     * 登录投诉举报系统
     */
    private void loginComplaintSys() {
        if (isLoging) {
            return;
        }
        isLoging = true;
        try {
            boolean isLogin = httpClientHelper.loginSuperviseSys(userName, pwd, new HttpPost(baseUrl + loginUrl));
            System.out.println(isLogin);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            isLoging = false;
        }
    }

    /**
     * 获取待办信息（通过待办id或指定时间获取）
     * */
    // @Scheduled(cron = "5/10 * * * * ?")
    public void getFordoData() {
        if (isGettingFordoData) {
            return;
        }
        isGettingFordoData = true;
        try {
            // 获取最大的待办id,若无则待办按时间获取
            String maxId = fdFordoCaseRepository.findMaxRmPendingId();
            // 参数设置
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            if (maxId == null) {
                params.add(new BasicNameValuePair("inputDate", Tools.diffDate(new Date(), new Integer(timeInterval))
                                .getTime() + ""));
            } else {
                params.add(new BasicNameValuePair("maxId", maxId));
            }
            for (String fordoStatus : complaintStatus) {
                params.add(new BasicNameValuePair("fordoStatus", fordoStatus));
            }
            // 发送请求
            String returnInfo = httpClientHelper.doPost(new HttpPost(baseUrl + getFordoDataUrl), params,
                            HttpClientHelper.ENCODE_GBK);
            if (HttpClientHelper.RE_LOGIN.equals(returnInfo)) {
                // 需要登录
                loginComplaintSys();
            } else {
                // 保存待办
                fdFordoCaseService.saveFdFordo(returnInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            isGettingFordoData = false;
        }
    }

    /**
     * 查询待办localData=0的待办,根据远程待办id获取投诉举报信息
     * */
    // @Scheduled(cron = "8/8 * * * * ?")
    public void getComplaintInfoByFordoId() {
        if (isGettingComplaintData) {
            return;
        }
        isGettingComplaintData = true;
        try {
            // 获取localData=0的远程待办id,每次获取10条记录
            List<String> pendingIds = fdFordoCaseRepository.findRmPendingIdListByLocalData(new PageRequest(0, 10));
            if (pendingIds != null && pendingIds.size() > 0) {
                JsonMapper mapper = JsonMapper.getInstance();
                // 参数设置
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("pendingIds", mapper.writeValueAsString(pendingIds)));
                // 发送请求
                String returnInfo = httpClientHelper.doPost(new HttpPost(baseUrl + getComplaintDataUrl), params,
                                HttpClientHelper.ENCODE_GBK);
                if (HttpClientHelper.RE_LOGIN.equals(returnInfo)) {
                    // 需要登录
                    loginComplaintSys();
                } else {
                    // 保存投诉举报信息
                    caseComplaintService.saveCaseComplaintInfo(returnInfo, pendingIds);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            isGettingComplaintData = false;
        }
    }

    /**
     * 投诉举报提交办理
     * */
    // @Scheduled(cron = "10/12 * * * * ?")
    public void submitComplaintData() {
        if (isSubmitComplaintData) {
            return;
        }
        isSubmitComplaintData = true;
        try {
            // 扫描DataUpdate数据列表，条件：workType=04;operationType=update
            List<DataUpdate> datas = dataUpdateService.selectDfferentDataByWorkType(WorkType.投诉举报提交, new PageRequest(0,
                            5));
            // 参数设置
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            for (DataUpdate dataUpdate : datas) {
                JsonMapper mapper = JsonMapper.getInstance();
                ComplaintSubmitContent submitContent = mapper.readValue(dataUpdate.getContent(),
                                ComplaintSubmitContent.class);
                params.clear(); // 先清除提交参数
                params.add(new BasicNameValuePair("approveContent", submitContent.getApproveContent()));
                params.add(new BasicNameValuePair("assignee", submitContent.getAssignee()));
                params.add(new BasicNameValuePair("approveTime", Tools.DATE_TIME_FORMAT.format(submitContent
                                .getApproveTime())));
                params.add(new BasicNameValuePair("next", submitContent.getNext()));
                params.add(new BasicNameValuePair("taskId", submitContent.getTaskId()));
                // 提交数据
                String json = httpClientHelper.doPost(new HttpPost(baseUrl + submitUrl), params,
                                HttpClientHelper.ENCODE_GBK);
                if (StringUtils.isNotBlank(json)) {
                    if (HttpClientHelper.RE_LOGIN.equals(json)) {
                        loginComplaintSys();// 需要登录
                        break;// 结束循环
                    } else {
                        // 提交成功，删除待办和dataUpdate记录
                        fdFordoCaseService.deleteFordoAndDataUpdate(submitContent.getTaskId(), dataUpdate, json);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            isSubmitComplaintData = false;
        }
    }

    /**
     * 获取远程系统待办修改记录数据
     */
    // @Scheduled(cron = "15/6 * * * * ?")
    public void getCaseCompalintDataChange() {
        if (isGetSysDataChangeRunning) {
            return;
        }
        isGetSysDataChangeRunning = true;
        try {
            String json = httpClientHelper.doGet(new HttpGet(baseUrl + getChangeDataUrl), null);
            if (StringUtils.isNotBlank(json)) {
                if (HttpClientHelper.RE_LOGIN.equals(json)) {
                    loginComplaintSys();// 需要登录
                } else {
                    // 保存变动的数据信息
                    JsonMapper mapper = JsonMapper.getInstance();
                    JavaType javaType = mapper.constructParametricType(ArrayList.class, SysDataChange.class);
                    List<SysDataChange> datas = (List<SysDataChange>) mapper.readValue(json, javaType);
                    dataChangeService.saveSysDataChange(datas);
                }
            }
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
        BufferedInputStream bis = null;
        HttpGet downloadGet = null;
        InputStream is = null;
        try {
            String localFilePath = this.rootPath + this.complaintAttachPath + Tools.DATE_FORMAT4.format(new Date());
            // 获取投诉举报的附件 查询localData为非本地数据("0")的数据，一次处理一个文件
            List<CaseComplaintAttach> datas = caseComplaintAttachService.findAttachByLocalData(LocalData.非本地数据,
                            new PageRequest(0, 1));
            if (!Tools.isNotEmptyList(datas)) {
                return;
            }
            CaseComplaintAttach attach = datas.get(0);
            File file = new File(localFilePath);
            // 目录不存在则建立新目录
            if (!file.exists()) {
                file.mkdirs();
            }
            String localFileName = localFilePath + "/" + attach.getSaveName();
            // 参数设置
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("fileId", attach.getRmAttachId() + ""));
            downloadGet = new HttpGet(baseUrl + downloadUrl);
            CloseableHttpResponse response = httpClientHelper.doGetAndReturnResponse(downloadGet, params);
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
                this.caseComplaintAttachService.saveAttachForLocal(attach, localFileName);
            } else if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
                loginComplaintSys();// 需要登录
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
