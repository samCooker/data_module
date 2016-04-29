/*
 * FileName:    DocexPendingItem.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2016
 * History:     2016年4月27日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.task.bean;

import java.util.Date;
import java.util.List;

import cn.com.chaochuang.docex.domain.DocexAffixItem;

/**
 * @author LLM
 *
 */
public class DocexPendingItem {
    /** 原系统流水号（ex_rec_id） */
    private Long         rmPendingId;
    /** 标题 */
    private String       title;
    /** 文号 */
    private String       docNumber;
    /** 密级 */
    private String       secretLevel;
    /** 紧急程度 */
    private String       emergencyLevel;
    /** 公文类别 */
    private String       flowType;
    /** 发送单位编号 */
    private Long         senderUnitId;
    /** 发送部门编号 */
    private Long         senderDeptId;
    /** 发送人编号 */
    private Long         senderId;
    /** 发送单位名称 */
    private String       senderUnitName;
    /** 发送部门名称 */
    private String       senderDeptName;
    /** 发送人姓名 */
    private String       senderName;
    /** 发送时间 */
    private Date         sendTime;
    /** 签收单位编号 */
    private Long         receiverUnitId;
    /** 签收部门编号 */
    private Long         receiverDeptId;
    /** 签收单位名称 */
    private String       receiverUnitName;
    /** 签收部门名称 */
    private String       receiverDeptName;
    /** 签收人编号 */
    private Long         receiverId;
    /** 签收人姓名 */
    private String       receiverName;
    /** 签收时间 */
    private Date         receiveTime;
    /** 附件组标识 */
    private String       docAffixId;
    /** 附件编号 */
    private Long         fileId;
    /** 反馈人编号 */
    private Long         feedbackManId;
    /** 反馈人姓名 */
    private String       feedbackManName;
    /** 反馈人部门编号 */
    private Long         feedbackDeptId;
    /** 反馈人部门名称 */
    private String       feedbackDeptName;
    /** 反馈日期 */
    private Date         feedbackDate;
    /** 反馈意见 */
    private String       feedbackContent;
    /** 撤文标识 */
    private String       cancelFlag;
    /** 附件集合 */
    List<DocexAffixItem> affixs;

    /**
     * @return the rmPendingId
     */
    public Long getRmPendingId() {
        return rmPendingId;
    }

    /**
     * @param rmPendingId
     *            the rmPendingId to set
     */
    public void setRmPendingId(Long rmPendingId) {
        this.rmPendingId = rmPendingId;
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
     * @return the docNumber
     */
    public String getDocNumber() {
        return docNumber;
    }

    /**
     * @param docNumber
     *            the docNumber to set
     */
    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    /**
     * @return the secretLevel
     */
    public String getSecretLevel() {
        return secretLevel;
    }

    /**
     * @param secretLevel
     *            the secretLevel to set
     */
    public void setSecretLevel(String secretLevel) {
        this.secretLevel = secretLevel;
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
     * @return the flowType
     */
    public String getFlowType() {
        return flowType;
    }

    /**
     * @param flowType
     *            the flowType to set
     */
    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    /**
     * @return the senderUnitId
     */
    public Long getSenderUnitId() {
        return senderUnitId;
    }

    /**
     * @param senderUnitId
     *            the senderUnitId to set
     */
    public void setSenderUnitId(Long senderUnitId) {
        this.senderUnitId = senderUnitId;
    }

    /**
     * @return the senderDeptId
     */
    public Long getSenderDeptId() {
        return senderDeptId;
    }

    /**
     * @param senderDeptId
     *            the senderDeptId to set
     */
    public void setSenderDeptId(Long senderDeptId) {
        this.senderDeptId = senderDeptId;
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
     * @return the senderUnitName
     */
    public String getSenderUnitName() {
        return senderUnitName;
    }

    /**
     * @param senderUnitName
     *            the senderUnitName to set
     */
    public void setSenderUnitName(String senderUnitName) {
        this.senderUnitName = senderUnitName;
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
     * @return the receiverUnitId
     */
    public Long getReceiverUnitId() {
        return receiverUnitId;
    }

    /**
     * @param receiverUnitId
     *            the receiverUnitId to set
     */
    public void setReceiverUnitId(Long receiverUnitId) {
        this.receiverUnitId = receiverUnitId;
    }

    /**
     * @return the receiverDeptId
     */
    public Long getReceiverDeptId() {
        return receiverDeptId;
    }

    /**
     * @param receiverDeptId
     *            the receiverDeptId to set
     */
    public void setReceiverDeptId(Long receiverDeptId) {
        this.receiverDeptId = receiverDeptId;
    }

    /**
     * @return the receiverUnitName
     */
    public String getReceiverUnitName() {
        return receiverUnitName;
    }

    /**
     * @param receiverUnitName
     *            the receiverUnitName to set
     */
    public void setReceiverUnitName(String receiverUnitName) {
        this.receiverUnitName = receiverUnitName;
    }

    /**
     * @return the receiverDeptName
     */
    public String getReceiverDeptName() {
        return receiverDeptName;
    }

    /**
     * @param receiverDeptName
     *            the receiverDeptName to set
     */
    public void setReceiverDeptName(String receiverDeptName) {
        this.receiverDeptName = receiverDeptName;
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

    /**
     * @return the receiverName
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     * @param receiverName
     *            the receiverName to set
     */
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    /**
     * @return the receiveTime
     */
    public Date getReceiveTime() {
        return receiveTime;
    }

    /**
     * @param receiveTime
     *            the receiveTime to set
     */
    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    /**
     * @return the docAffixId
     */
    public String getDocAffixId() {
        return docAffixId;
    }

    /**
     * @param docAffixId
     *            the docAffixId to set
     */
    public void setDocAffixId(String docAffixId) {
        this.docAffixId = docAffixId;
    }

    /**
     * @return the fileId
     */
    public Long getFileId() {
        return fileId;
    }

    /**
     * @param fileId
     *            the fileId to set
     */
    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    /**
     * @return the feedbackManId
     */
    public Long getFeedbackManId() {
        return feedbackManId;
    }

    /**
     * @param feedbackManId
     *            the feedbackManId to set
     */
    public void setFeedbackManId(Long feedbackManId) {
        this.feedbackManId = feedbackManId;
    }

    /**
     * @return the feedbackManName
     */
    public String getFeedbackManName() {
        return feedbackManName;
    }

    /**
     * @param feedbackManName
     *            the feedbackManName to set
     */
    public void setFeedbackManName(String feedbackManName) {
        this.feedbackManName = feedbackManName;
    }

    /**
     * @return the feedbackDeptId
     */
    public Long getFeedbackDeptId() {
        return feedbackDeptId;
    }

    /**
     * @param feedbackDeptId
     *            the feedbackDeptId to set
     */
    public void setFeedbackDeptId(Long feedbackDeptId) {
        this.feedbackDeptId = feedbackDeptId;
    }

    /**
     * @return the feedbackDeptName
     */
    public String getFeedbackDeptName() {
        return feedbackDeptName;
    }

    /**
     * @param feedbackDeptName
     *            the feedbackDeptName to set
     */
    public void setFeedbackDeptName(String feedbackDeptName) {
        this.feedbackDeptName = feedbackDeptName;
    }

    /**
     * @return the feedbackDate
     */
    public Date getFeedbackDate() {
        return feedbackDate;
    }

    /**
     * @param feedbackDate
     *            the feedbackDate to set
     */
    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    /**
     * @return the feedbackContent
     */
    public String getFeedbackContent() {
        return feedbackContent;
    }

    /**
     * @param feedbackContent
     *            the feedbackContent to set
     */
    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    /**
     * @return the affixs
     */
    public List<DocexAffixItem> getAffixs() {
        return affixs;
    }

    /**
     * @param affixs
     *            the affixs to set
     */
    public void setAffixs(List<DocexAffixItem> affixs) {
        this.affixs = affixs;
    }

    /**
     * @return the cancelFlag
     */
    public String getCancelFlag() {
        return cancelFlag;
    }

    /**
     * @param cancelFlag
     *            the cancelFlag to set
     */
    public void setCancelFlag(String cancelFlag) {
        this.cancelFlag = cancelFlag;
    }

}
