/*
 * FileName:    CommonPendingHandleInfo.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年5月28日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.common.fdfordo.bean;

import java.util.Date;

/**
 * @author LLM
 *
 */
public class CommonPendingHandleInfo {
    /** 远程系统待办编号 */
    private String rmPendingId;
    /** URL */
    private String url;
    /** 标题 */
    private String title;
    /** 发送人编号 */
    private Long   senderId;
    /** 发送时间 */
    private Date   sendTime;
    /** 发送人姓名 */
    private String senderName;
    /** 待办数据读取时间 */
    private Date   readTime;
    /** 待办明细接收人编号 */
    private Long   recipientId;
    /** 紧急程度 */
    private String emergencyLevel;
    /** 发送人所在部门 */
    private String senderDeptName;
    /** 最后的发送时间 */
    private Date   lastSendTime;

    /**
     * @return the rmPendingId
     */
    public String getRmPendingId() {
        return rmPendingId;
    }

    /**
     * @param rmPendingId
     *            the rmPendingId to set
     */
    public void setRmPendingId(String rmPendingId) {
        this.rmPendingId = rmPendingId;
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
     * @return the recipientId
     */
    public Long getRecipientId() {
        return recipientId;
    }

    /**
     * @param recipientId
     *            the recipientId to set
     */
    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    /**
     * @return the emergencyLevel
     */
    public String getEmergencyLevel() {
        return emergencyLevel;
    }

    /**
     * @param emergencyLevel
     *            the emergencyLevel to set
     */
    public void setEmergencyLevel(String emergencyLevel) {
        this.emergencyLevel = emergencyLevel;
    }

    /**
     * @return the senderDeptName
     */
    public String getSenderDeptName() {
        return senderDeptName;
    }

    /**
     * @param senderDeptName
     *            the senderDeptName to set
     */
    public void setSenderDeptName(String senderDeptName) {
        this.senderDeptName = senderDeptName;
    }

    /**
     * @return the lastSendTime
     */
    public Date getLastSendTime() {
        return lastSendTime;
    }

    /**
     * @param lastSendTime
     *            the lastSendTime to set
     */
    public void setLastSendTime(Date lastSendTime) {
        this.lastSendTime = lastSendTime;
    }

}
