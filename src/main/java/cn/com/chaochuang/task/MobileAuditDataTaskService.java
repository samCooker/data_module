/*
 * FileName:    MobileAuditDataTaskService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年10月9日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.task;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.com.chaochuang.audit.bean.AuditPendingHandleInfo;
import cn.com.chaochuang.audit.service.FdFordoAuditService;
import cn.com.chaochuang.common.util.HttpClientHelper;
import cn.com.chaochuang.common.util.JsonMapper;
import cn.com.chaochuang.common.util.Tools;

import com.fasterxml.jackson.databind.JavaType;

/**
 * @author LLM
 *
 */
@Component
public class MobileAuditDataTaskService {
    @Value("${supervise.userName}")
    private String                  userName;
    @Value("${supervise.pwd}")
    private String                  pwd;
    @Value("${supervise.baseUrl}")
    private String                  baseUrl;
    @Value("${supervise.loginUrl}")
    private String                  loginUrl;
    @Value("${audit.getFordoDataUrl}")
    private String                  getFordoDataUrl;
    @Value("${supervise.getSuperviseDataUrl}")
    private String                  getSuperviseDataUrl;
    @Value("${supervise.submitUrl}")
    private String                  submitUrl;
    @Value("${supervise.downloadUrl}")
    private String                  downloadUrl;

    @Autowired
    private FdFordoAuditService     fdFordoAuditService;
    /** 附件存放根路径 */
    @Value("${upload.rootpath}")
    private String                  rootPath;

    /** 公文附件存放相对路径 */
    @Value("${supervisefile.attach.path}")
    private String                  docFileAttachPath;
    /** 获取公文阻塞标识 */
    private static boolean          isFordoRunning   = false;
    /** 是否正在登录 */
    private static boolean          isLoging         = false;
    /** 创建httpClient对象 */
    private static HttpClientHelper httpClientHelper = HttpClientHelper.newHttpClientHelper();

    /**
     * 向审批查验系统获取待办事宜数据 每5秒进行一次数据获取
     */
    @Scheduled(cron = "5/5 * * * * ?")
    public void getFordoDataTask() {
        if (isFordoRunning) {
            return;
        }
        isFordoRunning = true;
        if (!isLoging) {
            if (!loginSuperviseSys()) {
                isFordoRunning = false;
                return;
            }
        }
        try {
            // 获取当前待办表中公文待办中最大的id，若无法获取时间值则获取距离当前时间一个月的时间值
            AuditPendingHandleInfo info = this.fdFordoAuditService.selectMaxInputDate();
            // 参数设置
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            if (info.getLastSendTime() != null) {
                params.add(new BasicNameValuePair("lastOutputTime", Tools.DATE_TIME_FORMAT.format(info
                                .getLastSendTime())));
            }
            if (info.getRmPendingId() != null) {
                params.add(new BasicNameValuePair("pendingHandleId", info.getRmPendingId()));
            }
            // 发送请求
            String json = httpClientHelper.doPost(new HttpPost(baseUrl + getFordoDataUrl), params,
                            HttpClientHelper.ENCODE_GBK);
            if (StringUtils.isNotBlank(json)) {
                if (!HttpClientHelper.RE_LOGIN.equals(json) && !"FALSE".equals(json)) {
                    // 将json字符串还原回PendingCommandInfo对象，再循环将对象插入FdFordo表
                    JsonMapper mapper = JsonMapper.getInstance();
                    JavaType javaType = mapper.constructParametricType(ArrayList.class, AuditPendingHandleInfo.class);
                    List<AuditPendingHandleInfo> datas = (List<AuditPendingHandleInfo>) mapper
                                    .readValue(json, javaType);
                    this.fdFordoAuditService.insertFdFordos(datas);
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
    private boolean loginSuperviseSys() {
        if (isLoging) {
            return true;
        }
        isLoging = true;
        try {
            isLoging = httpClientHelper.loginSuperviseSys(userName, pwd, new HttpPost(baseUrl + loginUrl));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isLoging;
    }
}
