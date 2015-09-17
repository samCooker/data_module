/*
 * FileName:    SysDataChange.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年09月10日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.casecomplaint.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.common.data.domain.LongIdEntity;
import cn.com.chaochuang.docwork.reference.FordoStatus;
import cn.com.chaochuang.docwork.reference.FordoStatusConverter;

/**
 * @author Shicx
 * 
 *         待办标识curHandleFlag=1表明该待办类型为坐席，在移动端处理为产生相同的多个待办（rmPendingId相同），只是接收人不同而已。
 *
 */
@SuppressWarnings("serial")
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "FORDO_ID")) })
public class FdFordoCase extends LongIdEntity {
    /** 远程待办id */
    private String      rmPendingId;
    /** 标题 */
    private String      title;
    /***/
    private String      url;
    /** 发送人id */
    private Long        senderId;
    /** 发送时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date        sendTime;
    /** 发送人姓名 */
    private String      senderName;
    /** 发送人所在部门 */
    private String      senderDeptName;
    /** 已读状态 0:未读 1:已读 */
    @Convert(converter = FordoStatusConverter.class)
    private FordoStatus status;
    /** 限制时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date        limitTime;
    /** 已阅时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date        readTime;
    /** 接收人id */
    private Long        recipientId;
    /** 紧急程度 0：非紧急 1：紧急 */
    private String      emergencyLevel;
    /** 数据导入时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date        inputDate;
    /** 是否有本地数据 */
    // @Convert(converter = LocalDataConverter.class)
    private LocalData   localData;
    /** 投诉举报状态 */
    private String      caseStatus;
    /** 原流程节点id */
    private String      nodeId;
    /** 原流程实例id */
    private Long        flowInstId;
    /** 待办标识 0：用户 1：坐席 */
    private String      curHandleFlag;
    /** 模块名 */
    private String      pendingModule;
    /** 投诉单接收id */
    private Long        recvinId;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setStatus(FordoStatus status) {
        this.status = status;
    }

    public FordoStatus getStatus() {
        return status;
    }

    public void setLimitTime(Date limitTime) {
        this.limitTime = limitTime;
    }

    public Date getLimitTime() {
        return limitTime;
    }

    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    public Date getReadTime() {
        return readTime;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public void setEmergencyLevel(String emergencyLevel) {
        this.emergencyLevel = emergencyLevel;
    }

    public String getEmergencyLevel() {
        return emergencyLevel;
    }

    public void setSenderDeptName(String senderDeptName) {
        this.senderDeptName = senderDeptName;
    }

    public String getSenderDeptName() {
        return senderDeptName;
    }

    public void setRmPendingId(String rmPendingId) {
        this.rmPendingId = rmPendingId;
    }

    public String getRmPendingId() {
        return rmPendingId;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setLocalData(LocalData localData) {
        this.localData = localData;
    }

    public LocalData getLocalData() {
        return localData;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setFlowInstId(Long flowInstId) {
        this.flowInstId = flowInstId;
    }

    public Long getFlowInstId() {
        return flowInstId;
    }

    public void setCurHandleFlag(String curHandleFlag) {
        this.curHandleFlag = curHandleFlag;
    }

    public String getCurHandleFlag() {
        return curHandleFlag;
    }

    public void setPendingModule(String pendingModule) {
        this.pendingModule = pendingModule;
    }

    public String getPendingModule() {
        return pendingModule;
    }

    public void setRecvinId(Long recvinId) {
        this.recvinId = recvinId;
    }

    public Long getRecvinId() {
        return recvinId;
    }

}
