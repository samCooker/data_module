/*
 * FileName:    AuditPendingHandleInfo.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年10月9日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.audit.bean;

import java.util.List;

import cn.com.chaochuang.appflow.domain.AppItemAttach;
import cn.com.chaochuang.audit.domain.AuditAppoint;
import cn.com.chaochuang.audit.domain.AuditFlowNodeInfo;
import cn.com.chaochuang.audit.domain.AuditFlowNodeOpinions;
import cn.com.chaochuang.audit.domain.AuditPrjContent;
import cn.com.chaochuang.audit.domain.AuditTask;
import cn.com.chaochuang.audit.domain.AuditWatcher;
import cn.com.chaochuang.common.fdfordo.bean.CommonPendingHandleInfo;

/**
 * @author LLM
 *
 */
public class AuditPendingHandleInfo extends CommonPendingHandleInfo {
    /** 环节状态 */
    private String                      nodeStatus;
    /** 环节标识 */
    private String                      nodeCode;
    /** 环节编号 */
    private Long                        nodeId;
    /** 原系统审查任务编号 */
    private Long                        taskId;
    /** 流程实例编号 */
    private Long                        flowInstId;
    /** 待办来源类型(对应fordoType) */
    private String                      fordoType;
    /** 流程编号 */
    private String                      pendingModule;
    /** 审查任务对象 */
    private AuditTask                   taskInfo;
    /** 指派记录列表 */
    private List<AuditAppoint>          appointInfos;
    /** 观察员列表 */
    private List<AuditWatcher>          watcherInfos;
    /** 审查项目列表 */
    private List<AuditPrjContent>       prjContentInfos;
    /** 审批环节记录列表 */
    private List<AuditFlowNodeInfo>     nodeInfos;
    /** 审批意见记录列表 */
    private List<AuditFlowNodeOpinions> nodeOpinionsInfos;
    /** 审批附件列表 */
    private List<AppItemAttach>         appItemAttachInfos;

    /**
     * @return the nodeStatus
     */
    public String getNodeStatus() {
        return nodeStatus;
    }

    /**
     * @param nodeStatus
     *            the nodeStatus to set
     */
    public void setNodeStatus(String nodeStatus) {
        this.nodeStatus = nodeStatus;
    }

    /**
     * @return the nodeCode
     */
    public String getNodeCode() {
        return nodeCode;
    }

    /**
     * @param nodeCode
     *            the nodeCode to set
     */
    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    /**
     * @return the nodeId
     */
    public Long getNodeId() {
        return nodeId;
    }

    /**
     * @param nodeId
     *            the nodeId to set
     */
    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    /**
     * @return the taskId
     */
    public Long getTaskId() {
        return taskId;
    }

    /**
     * @param taskId
     *            the taskId to set
     */
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    /**
     * @return the flowInstId
     */
    public Long getFlowInstId() {
        return flowInstId;
    }

    /**
     * @param flowInstId
     *            the flowInstId to set
     */
    public void setFlowInstId(Long flowInstId) {
        this.flowInstId = flowInstId;
    }

    /**
     * @return the fordoType
     */
    public String getFordoType() {
        return fordoType;
    }

    /**
     * @param fordoType
     *            the fordoType to set
     */
    public void setFordoType(String fordoType) {
        this.fordoType = fordoType;
    }

    /**
     * @return the pendingModule
     */
    public String getPendingModule() {
        return pendingModule;
    }

    /**
     * @param pendingModule
     *            the pendingModule to set
     */
    public void setPendingModule(String pendingModule) {
        this.pendingModule = pendingModule;
    }

    /**
     * @return the taskInfo
     */
    public AuditTask getTaskInfo() {
        return taskInfo;
    }

    /**
     * @param taskInfo
     *            the taskInfo to set
     */
    public void setTaskInfo(AuditTask taskInfo) {
        this.taskInfo = taskInfo;
    }

    /**
     * @return the appointInfos
     */
    public List<AuditAppoint> getAppointInfos() {
        return appointInfos;
    }

    /**
     * @param appointInfos
     *            the appointInfos to set
     */
    public void setAppointInfos(List<AuditAppoint> appointInfos) {
        this.appointInfos = appointInfos;
    }

    /**
     * @return the watcherInfos
     */
    public List<AuditWatcher> getWatcherInfos() {
        return watcherInfos;
    }

    /**
     * @param watcherInfos
     *            the watcherInfos to set
     */
    public void setWatcherInfos(List<AuditWatcher> watcherInfos) {
        this.watcherInfos = watcherInfos;
    }

    /**
     * @return the prjContentInfos
     */
    public List<AuditPrjContent> getPrjContentInfos() {
        return prjContentInfos;
    }

    /**
     * @param prjContentInfos
     *            the prjContentInfos to set
     */
    public void setPrjContentInfos(List<AuditPrjContent> prjContentInfos) {
        this.prjContentInfos = prjContentInfos;
    }

    /**
     * @return the nodeInfos
     */
    public List<AuditFlowNodeInfo> getNodeInfos() {
        return nodeInfos;
    }

    /**
     * @param nodeInfos
     *            the nodeInfos to set
     */
    public void setNodeInfos(List<AuditFlowNodeInfo> nodeInfos) {
        this.nodeInfos = nodeInfos;
    }

    /**
     * @return the nodeOpinionsInfos
     */
    public List<AuditFlowNodeOpinions> getNodeOpinionsInfos() {
        return nodeOpinionsInfos;
    }

    /**
     * @param nodeOpinionsInfos
     *            the nodeOpinionsInfos to set
     */
    public void setNodeOpinionsInfos(List<AuditFlowNodeOpinions> nodeOpinionsInfos) {
        this.nodeOpinionsInfos = nodeOpinionsInfos;
    }

    /**
     * @return the appItemAttachInfos
     */
    public List<AppItemAttach> getAppItemAttachInfos() {
        return appItemAttachInfos;
    }

    /**
     * @param appItemAttachInfos
     *            the appItemAttachInfos to set
     */
    public void setAppItemAttachInfos(List<AppItemAttach> appItemAttachInfos) {
        this.appItemAttachInfos = appItemAttachInfos;
    }

}
