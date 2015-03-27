/*
 * FileName:    FdFordo.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月19日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.docwork.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.com.chaochuang.common.data.domain.LongIdEntity;
import cn.com.chaochuang.docwork.reference.FordoSource;
import cn.com.chaochuang.docwork.reference.FordoSourceConverter;
import cn.com.chaochuang.docwork.reference.FordoStatus;
import cn.com.chaochuang.docwork.reference.FordoStatusConverter;

/**
 * @author LLM
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "fordo_id")) })
public class FdFordo extends LongIdEntity {
    /** 标题 */
    private String      title;
    /** URL */
    private String      url;
    /** 发送人编号 */
    private Long        sender;
    /** 发送时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date        sendTime;
    /** 发送人姓名 */
    private String      senderName;
    /** 待办状态 */
    @Convert(converter = FordoStatusConverter.class)
    private FordoStatus status;
    /** 待办来源类型 */
    @Convert(converter = FordoSourceConverter.class)
    private FordoSource fordoSource;
    /** 限办日期 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date        limitTime;
    /** 待办数据读取时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date        readTime;
    /** 待办明细接收人编号 */
    private String      recipientId;
    /** 紧急程度 */
    private String      emergencyLevel;
    /** 发送人所在部门 */
    private String      senderDeptName;
    /** 密级 */
    private String      secretLevel;
    /** 远程系统待办编号 */
    private String      rmPendingId;
    /** 数据导入时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date        inputDate;

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
     * @return the sender
     */
    public Long getSender() {
        return sender;
    }

    /**
     * @param sender
     *            the sender to set
     */
    public void setSender(Long sender) {
        this.sender = sender;
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
     * @return the fordoSource
     */
    public FordoSource getFordoSource() {
        return fordoSource;
    }

    /**
     * @param fordoSource
     *            the fordoSource to set
     */
    public void setFordoSource(FordoSource fordoSource) {
        this.fordoSource = fordoSource;
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
    public String getRecipientId() {
        return recipientId;
    }

    /**
     * @param recipientId
     *            the recipientId to set
     */
    public void setRecipientId(String recipientId) {
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
