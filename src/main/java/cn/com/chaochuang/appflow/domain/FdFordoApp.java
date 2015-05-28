/*
 * FileName:    FdFordoApp.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年5月28日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.appflow.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.com.chaochuang.common.data.domain.LongIdEntity;
import cn.com.chaochuang.docwork.reference.FordoStatus;
import cn.com.chaochuang.docwork.reference.FordoStatusConverter;

/**
 * @author LLM
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "fordo_id")) })
public class FdFordoApp extends LongIdEntity {
    /**  */
    private static final long serialVersionUID = 3877301153277496258L;
    /** 远程系统待办编号 */
    private String            rmPendingId;
    /** 环节状态 */
    private String            nodeStatus;
    /** 环节标识 */
    private String            nodeCode;
    /** 环节编号 */
    private Long              nodeId;
    /** 原系统审批项编号 */
    private Long              itemApplyId;
    /** 流程实例编号 */
    private Long              flowInstId;
    /** 标题 */
    private String            title;
    /** URL */
    private String            url;
    /** 发送人编号 */
    private Long              senderId;
    /** 发送时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date              sendTime;
    /** 发送人姓名 */
    private String            senderName;
    /** 待办状态 */
    @Convert(converter = FordoStatusConverter.class)
    private FordoStatus       status;
    /** 待办类型 */
    private String            fordoType;
    /** 限办日期 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date              limitTime;
    /** 待办数据读取时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date              readTime;
    /** 待办明细接收人编号 */
    private Long              recipientId;
    /** 紧急程度 */
    private String            emergencyLevel;
    /** 发送人所在部门 */
    private String            senderDeptName;
    /** 数据导入时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date              inputDate;

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
     * @return the itemApplyId
     */
    public Long getItemApplyId() {
        return itemApplyId;
    }

    /**
     * @param itemApplyId
     *            the itemApplyId to set
     */
    public void setItemApplyId(Long itemApplyId) {
        this.itemApplyId = itemApplyId;
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
     * @return the status
     */
    public FordoStatus getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(FordoStatus status) {
        this.status = status;
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
     * @return the inputDate
     */
    public Date getInputDate() {
        return inputDate;
    }

    /**
     * @param inputDate
     *            the inputDate to set
     */
    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

}
