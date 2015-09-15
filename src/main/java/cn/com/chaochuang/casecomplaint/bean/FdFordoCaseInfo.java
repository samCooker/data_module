/*
 * FileName:    FdFordoCaseInfo.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月10日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.casecomplaint.bean;

import java.util.Date;

/**
 * @author Shicx 投诉举报待办信息
 *
 */
public class FdFordoCaseInfo {

    /** 远程待办id */
    private String pendingHandleId;
    /** 紧急程度 */
    private String emergencyFlag;
    /** 投诉举报状态 */
    private String caseStatus;
    /** 接收人id */
    private Long   receiverId;
    /** 标题 */
    private String title;
    /***/
    private String url;
    /** 发送人id */
    private Long   senderId;
    /** 发送时间 */
    private Date   sendTime;
    /** 发送人姓名 */
    private String senderName;
    /** 发送人所在部门 */
    private String senderDepName;
    /** 限制时间 */
    private Date   limitTime;
    /** 已阅时间 */
    private Date   readTime;
    /** 原流程节点id */
    private String nodeId;
    /** 原流程实例id */
    private Long   flowInstId;
    /** 待办标识 0：用户 1：坐席 */
    private String curHandleFlag;
    /** 模块名 */
    private String pendingModule;
    /** 投诉单接收id */
    private Long   recvinId;

    /**
     * @return the pendingHandleId
     */
    public String getPendingHandleId() {
        return pendingHandleId;
    }

    /**
     * @param pendingHandleId
     *            the pendingHandleId to set
     */
    public void setPendingHandleId(String pendingHandleId) {
        this.pendingHandleId = pendingHandleId;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the senderId
     */
    public Long getSenderId() {
        return senderId;
    }

    /**
     * @param senderId
     *            the senderId to set
     */
    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    /**
     * @return the sendTime
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * @param sendTime
     *            the sendTime to set
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * @return the senderName
     */
    public String getSenderName() {
        return senderName;
    }

    /**
     * @param senderName
     *            the senderName to set
     */
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    /**
     * @return the senderDepName
     */
    public String getSenderDepName() {
        return senderDepName;
    }

    /**
     * @param senderDepName
     *            the senderDepName to set
     */
    public void setSenderDepName(String senderDepName) {
        this.senderDepName = senderDepName;
    }

    /**
     * @return the limitTime
     */
    public Date getLimitTime() {
        return limitTime;
    }

    /**
     * @param limitTime
     *            the limitTime to set
     */
    public void setLimitTime(Date limitTime) {
        this.limitTime = limitTime;
    }

    /**
     * @return the readTime
     */
    public Date getReadTime() {
        return readTime;
    }

    /**
     * @param readTime
     *            the readTime to set
     */
    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    /**
     * @return the emergencyFlag
     */
    public String getEmergencyFlag() {
        return emergencyFlag;
    }

    /**
     * @param emergencyFlag
     *            the emergencyFlag to set
     */
    public void setEmergencyFlag(String emergencyFlag) {
        this.emergencyFlag = emergencyFlag;
    }

    /**
     * @return the caseStatus
     */
    public String getCaseStatus() {
        return caseStatus;
    }

    /**
     * @param caseStatus
     *            the caseStatus to set
     */
    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    /**
     * @return the nodeId
     */
    public String getNodeId() {
        return nodeId;
    }

    /**
     * @param nodeId
     *            the nodeId to set
     */
    public void setNodeId(String nodeId) {
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
     * @return the curHandleFlag
     */
    public String getCurHandleFlag() {
        return curHandleFlag;
    }

    /**
     * @param curHandleFlag
     *            the curHandleFlag to set
     */
    public void setCurHandleFlag(String curHandleFlag) {
        this.curHandleFlag = curHandleFlag;
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
     * @return the recvinId
     */
    public Long getRecvinId() {
        return recvinId;
    }

    /**
     * @param recvinId
     *            the recvinId to set
     */
    public void setRecvinId(Long recvinId) {
        this.recvinId = recvinId;
    }

    /**
     * @return the receiverId
     */
    public Long getReceiverId() {
        return receiverId;
    }

    /**
     * @param receiverId
     *            the receiverId to set
     */
    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

}
