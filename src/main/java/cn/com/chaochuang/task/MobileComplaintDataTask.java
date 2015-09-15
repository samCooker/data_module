/*
 * FileName:    MobileComplaintDataTask.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月10日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.com.chaochuang.casecomplaint.repository.FdFordoCaseRepository;
import cn.com.chaochuang.casecomplaint.service.CaseComplaintService;
import cn.com.chaochuang.casecomplaint.service.FdFordoCaseService;
import cn.com.chaochuang.common.util.HttpClientHelper;
import cn.com.chaochuang.common.util.JsonMapper;
import cn.com.chaochuang.common.util.Tools;

/**
 * @author Shicx
 *
 */
@Component
public class MobileComplaintDataTask {

    @Value("${complaint.userName}")
    private String                  userName;
    @Value("${complaint.pwd}")
    private String                  pwd;
    @Value("${complaint.baseUrl}")
    private String                  baseUrl;
    @Value("${complaint.loginUrl}")
    private String                  loginUrl;
    @Value("${complaint.getFordoDataUrl}")
    private String                  getFordoDataUrl;
    @Value("${complaint.getComplaintDataUrl}")
    private String                  getComplaintDataUrl;
    @Value("${complaint.submitUrl}")
    private String                  submitUrl;
    @Value("${complaint.downloadUrl}")
    private String                  downloadUrl;
    /** 取数据的相隔天数 */
    @Value("${getdata.timeinterval}")
    private String                  timeInterval;
    /** 要查询的待办状态（可在spring-utildata-ctx.xml文件配置） */
    @Resource
    private List<String>            complaintStatus;

    @Autowired
    private FdFordoCaseService      fdFordoCaseService;
    @Autowired
    private FdFordoCaseRepository   fdFordoCaseRepository;
    @Autowired
    private CaseComplaintService    caseComplaintService;

    /** 创建httpClient对象 */
    private static HttpClientHelper httpClientHelper       = HttpClientHelper.newHttpClientHelper();
    /** 是否正在登录 */
    private static boolean          isLoging               = false;
    /** 正在获取待办标识 */
    private static boolean          isGettingFordoData     = false;
    /** 正在获取投诉举报信息标识 */
    private static boolean          isGettingComplaintData = false;

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
    @Scheduled(cron = "5/10 * * * * ?")
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
    @Scheduled(cron = "8/8 * * * * ?")
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
     * @return the httpClientHelper
     */
    public static HttpClientHelper getHttpClientHelper() {
        return httpClientHelper;
    }

}
