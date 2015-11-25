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

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.com.chaochuang.audit.bean.AuditPendingHandleInfo;
import cn.com.chaochuang.audit.service.FdFordoAuditService;
import cn.com.chaochuang.common.util.HttpClientHelper;
import cn.com.chaochuang.common.util.JsonMapper;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.datacenter.domain.DataUpdate;
import cn.com.chaochuang.datacenter.reference.WorkType;
import cn.com.chaochuang.datacenter.service.DataUpdateService;
import cn.com.chaochuang.task.bean.AuditSubmitData;

import com.fasterxml.jackson.databind.JavaType;

/**
 * @author LLM
 *
 */
@Component
public class MobileAuditDataTaskService {
    @Value("${supervise.userName}")
    private String                   userName;
    @Value("${supervise.pwd}")
    private String                   pwd;
    @Value("${supervise.baseUrl}")
    private String                   baseUrl;
    @Value("${supervise.loginUrl}")
    private String                   loginUrl;
    @Value("${audit.getFordoDataUrl}")
    private String                   getFordoDataUrl;
    @Value("${supervise.submitUrl}")
    private String                   submitUrl;

    @Autowired
    private FdFordoAuditService      fdFordoAuditService;
    @Autowired
    private DataUpdateService        dataUpdateService;

    @Resource
    private MobileAppDataTaskService mobileAppDataTaskService;

    /** 附件存放根路径 */
    @Value("${upload.rootpath}")
    private String                   rootPath;
    /** 公文附件存放相对路径 */
    @Value("${supervisefile.attach.path}")
    private String                   docFileAttachPath;
    /** 获取公文阻塞标识 */
    private static boolean           isFordoRunning      = false;
    /** 数据提交标识 */
    private static boolean           isSubmitDataRunning = false;

    /**
     * 向审批查验系统获取待办事宜数据 每5秒进行一次数据获取
     */
    // @Scheduled(cron = "5/5 * * * * ?")
    public void getFordoDataTask() {
        if (isFordoRunning) {
            return;
        }
        isFordoRunning = true;
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
            String json = HttpClientHelper.doPost(getHttpClient(), baseUrl + getFordoDataUrl, params,
                            HttpClientHelper.ENCODE_GBK);
            if (StringUtils.isNotBlank(json)) {
                if (HttpClientHelper.RE_LOGIN.equals(json)) {
                    mobileAppDataTaskService.loginSuperviseSys();
                } else if (!HttpClientHelper.RE_LOGIN.equals(json) && !"FALSE".equals(json)) {
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
     * 提交审批项数据
     */
    // @Scheduled(cron = "10/15 * * * * ?")
    public void commintSuperviseDataTask() {
        if (isSubmitDataRunning) {
            return;
        }
        isSubmitDataRunning = true;
        DataUpdate dataUpdate = null;
        try {
            // 扫描DataUpdate数据列表，条件：workType=07;operationType=update
            List<DataUpdate> datas = this.dataUpdateService.selectDocFileDataUpdate(WorkType.审评查验提交);
            // 每次仅处理列表的第一条记录
            if (!Tools.isNotEmptyList(datas)) {
                return;
            }
            dataUpdate = (DataUpdate) datas.get(0);
            // 获取要提交的json字符串

            JsonMapper mapper = JsonMapper.getInstance();
            AuditSubmitData nodeInfo = mapper.readValue(dataUpdate.getContent(), AuditSubmitData.class);
            // 参数设置
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("approveContent", nodeInfo.getApproveContent()));
            params.add(new BasicNameValuePair("assignee", nodeInfo.getAssignee()));
            params.add(new BasicNameValuePair("next", nodeInfo.getNext()));
            params.add(new BasicNameValuePair("nodeId", nodeInfo.getNodeId() + ""));
            params.add(new BasicNameValuePair("userId", nodeInfo.getUserId() + ""));
            if (nodeInfo.getRmPrjContentIds() != null) {
                // 整改内容
                for (int i = 0; i < nodeInfo.getRmPrjContentIds().length; i++) {
                    params.add(new BasicNameValuePair("improveFlag_" + (i + 1), nodeInfo.getImproveFlags()[i]));
                    params.add(new BasicNameValuePair("improveFlag", nodeInfo.getImproveFlags()[i]));
                    params.add(new BasicNameValuePair("improveResult", nodeInfo.getImproveResults()[i]));
                    params.add(new BasicNameValuePair("prjContentId", nodeInfo.getRmPrjContentIds()[i]));
                    params.add(new BasicNameValuePair("auditResult", nodeInfo.getAuditResults()[i]));
                    params.add(new BasicNameValuePair("auditRecord", nodeInfo.getAuditRecords()[i]));
                }
            }
            String json = HttpClientHelper.doPost(getHttpClient(), baseUrl + submitUrl, params,
                            HttpClientHelper.ENCODE_GBK);
            if (StringUtils.isNotBlank(json)) {
                if (HttpClientHelper.RE_LOGIN.equals(json)) {
                    mobileAppDataTaskService.loginSuperviseSys();
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
     * 获取与行政审批相同的httpClient
     * 
     * @return
     */
    private CloseableHttpClient getHttpClient() {
        return MobileAppDataTaskService.getHttpClient();
    }
}
