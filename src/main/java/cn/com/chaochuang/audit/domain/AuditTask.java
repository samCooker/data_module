/*
 * FileName:    AuditTask.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年10月13日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.audit.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;

import cn.com.chaochuang.common.data.domain.LongIdEntity;

/**
 * @author LLM
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "audit_task_id")) })
public class AuditTask extends LongIdEntity {
    /** 原系统审查任务流水号 */
    private Long   rmAuditTaskId;
    /** 审批事项编码 */
    private String prjSortId;
    /** 数据核对码 */
    private String checkNum;
    /** 审查任务受理时间 */
    private Date   auditAcceptTime;
    /** 审评会召开时间 */
    private Date   auditMeetingTime;
    /** 是否不予受理 */
    private String auditAccept;
    /** 是否区域回避 */
    private String auditArea;
    /** 事项申请编号 */
    private Long   itemApplyId;
    /** 事项申请时间 */
    private Date   applyTime;
    /** 审查开始时间 */
    private Date   auditStartTime;
    /** 审查结束时间 */
    private Date   auditEndTime;
    /** 任务检查类型 */
    private String taskCheckType;
    /** 是否需要抽样 */
    private String sampleFlag;
    /** 抽样备注 */
    private String sampleRemark;
    /** 观察员任务状态 */
    private String watchStatus;
    /** 现场检查任务状态 */
    private String siteCheckFlag;
    /** 企业提交整改状况 */
    private String entpStatus;
    /** 审查任务状态 */
    private String taskStatus;
    /** 详细地址 */
    private String auditAddr;
    /** 所在地区 */
    private String areaName;
    /** 产品名称 */
    private String foodProdType;
    /** 企业编号 */
    private Long   entpId;
    /** 企业名称 */
    private String entpName;
    /** 申请信息 */
    private String applyInfo;
    /** 承办部门编号 */
    private Long   auditDepId;
    /** 承办单位编号 */
    private Long   handleUnitId;
    /** 发送时间 */
    private Date   sendTime;
    /** 发送人编号 */
    private Long   senderId;
    /** 审查员评级状态 */
    private String taskDegreeStatus;
    /** 受理编号 */
    private Long   busNum;

    /**
     * @return the rmAuditTaskId
     */
    public Long getRmAuditTaskId() {
        return rmAuditTaskId;
    }

    /**
     * @param rmAuditTaskId
     *            the rmAuditTaskId to set
     */
    public void setRmAuditTaskId(Long rmAuditTaskId) {
        this.rmAuditTaskId = rmAuditTaskId;
    }

    /**
     * @return the prjSortId
     */
    public String getPrjSortId() {
        return prjSortId;
    }

    /**
     * @param prjSortId
     *            the prjSortId to set
     */
    public void setPrjSortId(String prjSortId) {
        this.prjSortId = prjSortId;
    }

    /**
     * @return the checkNum
     */
    public String getCheckNum() {
        return checkNum;
    }

    /**
     * @param checkNum
     *            the checkNum to set
     */
    public void setCheckNum(String checkNum) {
        this.checkNum = checkNum;
    }

    /**
     * @return the auditAcceptTime
     */
    public Date getAuditAcceptTime() {
        return auditAcceptTime;
    }

    /**
     * @param auditAcceptTime
     *            the auditAcceptTime to set
     */
    public void setAuditAcceptTime(Date auditAcceptTime) {
        this.auditAcceptTime = auditAcceptTime;
    }

    /**
     * @return the auditMeetingTime
     */
    public Date getAuditMeetingTime() {
        return auditMeetingTime;
    }

    /**
     * @param auditMeetingTime
     *            the auditMeetingTime to set
     */
    public void setAuditMeetingTime(Date auditMeetingTime) {
        this.auditMeetingTime = auditMeetingTime;
    }

    /**
     * @return the auditAccept
     */
    public String getAuditAccept() {
        return auditAccept;
    }

    /**
     * @param auditAccept
     *            the auditAccept to set
     */
    public void setAuditAccept(String auditAccept) {
        this.auditAccept = auditAccept;
    }

    /**
     * @return the auditArea
     */
    public String getAuditArea() {
        return auditArea;
    }

    /**
     * @param auditArea
     *            the auditArea to set
     */
    public void setAuditArea(String auditArea) {
        this.auditArea = auditArea;
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
     * @return the applyTime
     */
    public Date getApplyTime() {
        return applyTime;
    }

    /**
     * @param applyTime
     *            the applyTime to set
     */
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    /**
     * @return the auditStartTime
     */
    public Date getAuditStartTime() {
        return auditStartTime;
    }

    /**
     * @param auditStartTime
     *            the auditStartTime to set
     */
    public void setAuditStartTime(Date auditStartTime) {
        this.auditStartTime = auditStartTime;
    }

    /**
     * @return the auditEndTime
     */
    public Date getAuditEndTime() {
        return auditEndTime;
    }

    /**
     * @param auditEndTime
     *            the auditEndTime to set
     */
    public void setAuditEndTime(Date auditEndTime) {
        this.auditEndTime = auditEndTime;
    }

    /**
     * @return the taskCheckType
     */
    public String getTaskCheckType() {
        return taskCheckType;
    }

    /**
     * @param taskCheckType
     *            the taskCheckType to set
     */
    public void setTaskCheckType(String taskCheckType) {
        this.taskCheckType = taskCheckType;
    }

    /**
     * @return the sampleFlag
     */
    public String getSampleFlag() {
        return sampleFlag;
    }

    /**
     * @param sampleFlag
     *            the sampleFlag to set
     */
    public void setSampleFlag(String sampleFlag) {
        this.sampleFlag = sampleFlag;
    }

    /**
     * @return the sampleRemark
     */
    public String getSampleRemark() {
        return sampleRemark;
    }

    /**
     * @param sampleRemark
     *            the sampleRemark to set
     */
    public void setSampleRemark(String sampleRemark) {
        this.sampleRemark = sampleRemark;
    }

    /**
     * @return the watchStatus
     */
    public String getWatchStatus() {
        return watchStatus;
    }

    /**
     * @param watchStatus
     *            the watchStatus to set
     */
    public void setWatchStatus(String watchStatus) {
        this.watchStatus = watchStatus;
    }

    /**
     * @return the siteCheckFlag
     */
    public String getSiteCheckFlag() {
        return siteCheckFlag;
    }

    /**
     * @param siteCheckFlag
     *            the siteCheckFlag to set
     */
    public void setSiteCheckFlag(String siteCheckFlag) {
        this.siteCheckFlag = siteCheckFlag;
    }

    /**
     * @return the entpStatus
     */
    public String getEntpStatus() {
        return entpStatus;
    }

    /**
     * @param entpStatus
     *            the entpStatus to set
     */
    public void setEntpStatus(String entpStatus) {
        this.entpStatus = entpStatus;
    }

    /**
     * @return the taskStatus
     */
    public String getTaskStatus() {
        return taskStatus;
    }

    /**
     * @param taskStatus
     *            the taskStatus to set
     */
    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    /**
     * @return the auditAddr
     */
    public String getAuditAddr() {
        return auditAddr;
    }

    /**
     * @param auditAddr
     *            the auditAddr to set
     */
    public void setAuditAddr(String auditAddr) {
        this.auditAddr = auditAddr;
    }

    /**
     * @return the areaName
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * @param areaName
     *            the areaName to set
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    /**
     * @return the foodProdType
     */
    public String getFoodProdType() {
        return foodProdType;
    }

    /**
     * @param foodProdType
     *            the foodProdType to set
     */
    public void setFoodProdType(String foodProdType) {
        this.foodProdType = foodProdType;
    }

    /**
     * @return the entpId
     */
    public Long getEntpId() {
        return entpId;
    }

    /**
     * @param entpId
     *            the entpId to set
     */
    public void setEntpId(Long entpId) {
        this.entpId = entpId;
    }

    /**
     * @return the entpName
     */
    public String getEntpName() {
        return entpName;
    }

    /**
     * @param entpName
     *            the entpName to set
     */
    public void setEntpName(String entpName) {
        this.entpName = entpName;
    }

    /**
     * @return the applyInfo
     */
    public String getApplyInfo() {
        return applyInfo;
    }

    /**
     * @param applyInfo
     *            the applyInfo to set
     */
    public void setApplyInfo(String applyInfo) {
        this.applyInfo = applyInfo;
    }

    /**
     * @return the auditDepId
     */
    public Long getAuditDepId() {
        return auditDepId;
    }

    /**
     * @param auditDepId
     *            the auditDepId to set
     */
    public void setAuditDepId(Long auditDepId) {
        this.auditDepId = auditDepId;
    }

    /**
     * @return the handleUnitId
     */
    public Long getHandleUnitId() {
        return handleUnitId;
    }

    /**
     * @param handleUnitId
     *            the handleUnitId to set
     */
    public void setHandleUnitId(Long handleUnitId) {
        this.handleUnitId = handleUnitId;
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
     * @return the taskDegreeStatus
     */
    public String getTaskDegreeStatus() {
        return taskDegreeStatus;
    }

    /**
     * @param taskDegreeStatus
     *            the taskDegreeStatus to set
     */
    public void setTaskDegreeStatus(String taskDegreeStatus) {
        this.taskDegreeStatus = taskDegreeStatus;
    }

    /**
     * @return the busNum
     */
    public Long getBusNum() {
        return busNum;
    }

    /**
     * @param busNum
     *            the busNum to set
     */
    public void setBusNum(Long busNum) {
        this.busNum = busNum;
    }

}
