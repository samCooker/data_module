/*
 * FileName:    PendingHandleInfo.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年5月28日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.appflow.bean;

import cn.com.chaochuang.common.fdfordo.bean.CommonPendingHandleInfo;

/**
 * @author LLM
 *
 */
public class AppFlowPendingHandleInfo extends CommonPendingHandleInfo {
    /** 环节状态 */
    private String nodeStatus;
    /** 环节标识 */
    private String nodeCode;
    /** 环节编号 */
    private Long   nodeId;
    /** 原系统审批项编号 */
    // private Long itemApplyId;
    private Long   businessKey;
    /** 流程实例编号 */
    private Long   flowInstId;
    /** 待办来源类型(对应fordoType) */
    private String fordoType;

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
     * @return the businessKey
     */
    public Long getBusinessKey() {
        return businessKey;
    }

    /**
     * @param businessKey
     *            the businessKey to set
     */
    public void setBusinessKey(Long businessKey) {
        this.businessKey = businessKey;
    }

}
