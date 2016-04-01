/*
 * FileName:    FdFordoComp.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2016
 * History:     2016年3月31日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.common.fdfordo.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.com.chaochuang.common.data.domain.LongIdEntity;
import cn.com.chaochuang.docwork.domain.FdFordo;
import cn.com.chaochuang.docwork.reference.FordoSource;
import cn.com.chaochuang.docwork.reference.FordoSourceConverter;

/**
 * @author LLM
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "comp_fordo_id")) })
public class FdFordoComp extends LongIdEntity {
    /** 待办表流水号 */
    private Long        fordoId;
    /** 待办来源类型 */
    @Convert(converter = FordoSourceConverter.class)
    private FordoSource fordoSource;
    /** 标题 */
    private String      title;
    /** URL */
    private String      url;
    /** 详情 */
    private String      detail;
    /** 发送时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date        sendTime;
    /** 发送人姓名 */
    private String      senderName;
    /** 发送人所在部门 */
    private String      senderDeptName;
    /** 待办明细接收人编号 */
    private Long        recipientId;
    /** 紧急程度 */
    private String      emergencyLevel;

    /**
     * 构造函数
     */
    public FdFordoComp() {
        super();
    }

    /**
     * 构造函数
     *
     * @param fdFordo
     */
    public FdFordoComp(FdFordo fdFordo) {
        this.fordoId = fdFordo.getId();
        this.fordoSource = FordoSource.公文;
        this.title = fdFordo.getTitle();
        this.url = fdFordo.getUrl();
        this.sendTime = fdFordo.getSendTime();
        this.senderName = fdFordo.getSenderName();
        this.senderDeptName = fdFordo.getSenderDeptName();
        this.recipientId = fdFordo.getRecipientId();
        this.emergencyLevel = fdFordo.getEmergencyLevel();
    }

    /**
     * @return the fordoId
     */
    public Long getFordoId() {
        return fordoId;
    }

    /**
     * @param fordoId
     *            the fordoId to set
     */
    public void setFordoId(Long fordoId) {
        this.fordoId = fordoId;
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
     * @return the detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * @param detail
     *            the detail to set
     */
    public void setDetail(String detail) {
        this.detail = detail;
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
}
