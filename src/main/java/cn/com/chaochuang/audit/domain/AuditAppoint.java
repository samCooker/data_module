/*
 * FileName:    AuditAppoint.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年10月10日 (LLM) 1.0 Create
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
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "appoint_id")) })
public class AuditAppoint extends LongIdEntity {
    /** 原系统审查员派选记录编号 */
    private Long   rmAppointId;
    /** 审查任务编号 */
    private Long   auditTaskId;
    /** 审查员派选记录编号 */
    private Long   auditorId;
    /** 是否同意标识 */
    private String agree;
    /** 是否变更标识 */
    private String change;
    /** 审查员等级 */
    private String degree;
    /** 审查员专业 */
    private String auditSubject;
    /** 不同意原因 */
    private String notAgreeReason;
    /** 变更原因 */
    private String changeReason;
    /** 指派人姓名 */
    private String appointer;
    /** 审查员评级 */
    private String auditorAssess;
    /** 指派时间 */
    private Date   appointTime;
    /** 承办单位（审批中心） */
    private Long   auditUnitId;
    /** 承办单位（行政审批） */
    private Long   handleUnitId;

    /**
     * @return the auditUnitId
     */
    public Long getAuditUnitId() {
        return auditUnitId;
    }

    /**
     * @param auditUnitId
     *            the auditUnitId to set
     */
    public void setAuditUnitId(Long auditUnitId) {
        this.auditUnitId = auditUnitId;
    }

    /**
     * @return the rmAppointId
     */
    public Long getRmAppointId() {
        return rmAppointId;
    }

    /**
     * @param rmAppointId
     *            the rmAppointId to set
     */
    public void setRmAppointId(Long rmAppointId) {
        this.rmAppointId = rmAppointId;
    }

    /**
     * @return the auditorId
     */
    public Long getAuditorId() {
        return auditorId;
    }

    /**
     * @param auditorId
     *            the auditorId to set
     */
    public void setAuditorId(Long auditorId) {
        this.auditorId = auditorId;
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
     * @return the auditTaskId
     */
    public Long getAuditTaskId() {
        return auditTaskId;
    }

    /**
     * @param auditTaskId
     *            the auditTaskId to set
     */
    public void setAuditTaskId(Long auditTaskId) {
        this.auditTaskId = auditTaskId;
    }

    /**
     * @return the agree
     */
    public String getAgree() {
        return agree;
    }

    /**
     * @param agree
     *            the agree to set
     */
    public void setAgree(String agree) {
        this.agree = agree;
    }

    /**
     * @return the change
     */
    public String getChange() {
        return change;
    }

    /**
     * @param change
     *            the change to set
     */
    public void setChange(String change) {
        this.change = change;
    }

    /**
     * @return the notAgreeReason
     */
    public String getNotAgreeReason() {
        return notAgreeReason;
    }

    /**
     * @param notAgreeReason
     *            the notAgreeReason to set
     */
    public void setNotAgreeReason(String notAgreeReason) {
        this.notAgreeReason = notAgreeReason;
    }

    /**
     * @return the changeReason
     */
    public String getChangeReason() {
        return changeReason;
    }

    /**
     * @param changeReason
     *            the changeReason to set
     */
    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason;
    }

    /**
     * @return the appointer
     */
    public String getAppointer() {
        return appointer;
    }

    /**
     * @param appointer
     *            the appointer to set
     */
    public void setAppointer(String appointer) {
        this.appointer = appointer;
    }

    /**
     * @return the auditorAssess
     */
    public String getAuditorAssess() {
        return auditorAssess;
    }

    /**
     * @param auditorAssess
     *            the auditorAssess to set
     */
    public void setAuditorAssess(String auditorAssess) {
        this.auditorAssess = auditorAssess;
    }

    /**
     * @return the appointTime
     */
    public Date getAppointTime() {
        return appointTime;
    }

    /**
     * @param appointTime
     *            the appointTime to set
     */
    public void setAppointTime(Date appointTime) {
        this.appointTime = appointTime;
    }

    /**
     * @return the degree
     */
    public String getDegree() {
        return degree;
    }

    /**
     * @param degree
     *            the degree to set
     */
    public void setDegree(String degree) {
        this.degree = degree;
    }

    /**
     * @return the auditSubject
     */
    public String getAuditSubject() {
        return auditSubject;
    }

    /**
     * @param auditSubject
     *            the auditSubject to set
     */
    public void setAuditSubject(String auditSubject) {
        this.auditSubject = auditSubject;
    }

}
