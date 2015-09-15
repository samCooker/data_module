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
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.com.chaochuang.common.data.domain.LongIdEntity;

/**
 * @author Shicx
 *
 */
@SuppressWarnings("serial")
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "COMPLAINT_ID")) })
public class CaseComplaint extends LongIdEntity {
    /** 远程投诉id */
    private Long   rmComplaintId;
    /** 投诉举报编号 */
    private String complaintNum;
    /** 投诉举报受理码 */
    private String complaintCode;
    /** 问题分类 */
    private String problemName;
    /** 举报标题 */
    private String complaintTitle;
    /** 并入的投诉举报id */
    private Long   joinComplaintId;
    /** 投诉内容 */
    private String complaintContent;
    /** 投诉举报产品分类 */
    private String complaintTypeName;
    /** 投诉时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date   complaintTime;
    /** 是否匿名 */
    private String anonymityFlag;
    /** 投诉举报当前状态 */
    private String caseComplaintStauts;
    /** 投诉举报来源 */
    private String complaintWayName;
    /** 相关证据 */
    private String complaintProof;
    /** 举报人信息id */
    private Long   complaintObjectId;
    /** 投诉环节分类 */
    private String prodFlag;
    /** 被投诉对象信息id */
    private Long   complainedObjectId;
    /** 流程实例id */
    private String instId;
    /** 流程实例流水号 */
    private Long   flowInstId;
    /** 重要程度 */
    private String importantName;
    /** 业务类别 */
    private String bussType;
    /** 受理方式 */
    private String caseWay;
    /** 是否立案 */
    private String caseFlag;
    /** 是否延期 */
    private String delayFlag;
    /** 延期理由 */
    private String delayReason;
    /** 答复/反馈内容 */
    private String answerContent;
    /** 答复/反馈时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date   answerTime;
    /** 完成时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date   finishTime;
    /** 满意程度 0：满意1：非常满意2：不满意 */
    private String satisfFlag;
    /** 受理单位id */
    private Long   unitId;
    /** 投诉附件id */
    private String affixId;
    /** 转办来源单位id */
    private Long   transferUnitId;
    /** 执法处理附件id */
    private String workAffixId;
    /** 是否保密 */
    private String securityFlag;
    /** 办理时限 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date   timeLimit;
    /** 上报时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date   reportTime;
    /** 退回内容 */
    private String stateBackContent;

    public void setComplaintNum(String complaintNum) {
        this.complaintNum = complaintNum;
    }

    public String getComplaintNum() {
        return complaintNum;
    }

    public void setComplaintCode(String complaintCode) {
        this.complaintCode = complaintCode;
    }

    public String getComplaintCode() {
        return complaintCode;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public String getProblemName() {
        return problemName;
    }

    public void setComplaintTitle(String complaintTitle) {
        this.complaintTitle = complaintTitle;
    }

    public String getComplaintTitle() {
        return complaintTitle;
    }

    public void setJoinComplaintId(Long joinComplaintId) {
        this.joinComplaintId = joinComplaintId;
    }

    public Long getJoinComplaintId() {
        return joinComplaintId;
    }

    public void setComplaintContent(String complaintContent) {
        this.complaintContent = complaintContent;
    }

    public String getComplaintContent() {
        return complaintContent;
    }

    public void setComplaintTypeName(String complaintTypeName) {
        this.complaintTypeName = complaintTypeName;
    }

    public String getComplaintTypeName() {
        return complaintTypeName;
    }

    public void setComplaintTime(Date complaintTime) {
        this.complaintTime = complaintTime;
    }

    public Date getComplaintTime() {
        return complaintTime;
    }

    public void setAnonymityFlag(String anonymityFlag) {
        this.anonymityFlag = anonymityFlag;
    }

    public String getAnonymityFlag() {
        return anonymityFlag;
    }

    public void setCaseComplaintStauts(String caseComplaintStauts) {
        this.caseComplaintStauts = caseComplaintStauts;
    }

    public String getCaseComplaintStauts() {
        return caseComplaintStauts;
    }

    public void setComplaintWayName(String complaintWayName) {
        this.complaintWayName = complaintWayName;
    }

    public String getComplaintWayName() {
        return complaintWayName;
    }

    public void setComplaintProof(String complaintProof) {
        this.complaintProof = complaintProof;
    }

    public String getComplaintProof() {
        return complaintProof;
    }

    public void setComplaintObjectId(Long complaintObjectId) {
        this.complaintObjectId = complaintObjectId;
    }

    public Long getComplaintObjectId() {
        return complaintObjectId;
    }

    public void setProdFlag(String prodFlag) {
        this.prodFlag = prodFlag;
    }

    public String getProdFlag() {
        return prodFlag;
    }

    public void setComplainedObjectId(Long complainedObjectId) {
        this.complainedObjectId = complainedObjectId;
    }

    public Long getComplainedObjectId() {
        return complainedObjectId;
    }

    public void setInstId(String instId) {
        this.instId = instId;
    }

    public String getInstId() {
        return instId;
    }

    public void setImportantName(String importantName) {
        this.importantName = importantName;
    }

    public String getImportantName() {
        return importantName;
    }

    public void setBussType(String bussType) {
        this.bussType = bussType;
    }

    public String getBussType() {
        return bussType;
    }

    public void setCaseWay(String caseWay) {
        this.caseWay = caseWay;
    }

    public String getCaseWay() {
        return caseWay;
    }

    public void setCaseFlag(String caseFlag) {
        this.caseFlag = caseFlag;
    }

    public String getCaseFlag() {
        return caseFlag;
    }

    public void setDelayFlag(String delayFlag) {
        this.delayFlag = delayFlag;
    }

    public String getDelayFlag() {
        return delayFlag;
    }

    public void setDelayReason(String delayReason) {
        this.delayReason = delayReason;
    }

    public String getDelayReason() {
        return delayReason;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    public Date getAnswerTime() {
        return answerTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setSatisfFlag(String satisfFlag) {
        this.satisfFlag = satisfFlag;
    }

    public String getSatisfFlag() {
        return satisfFlag;
    }

    public void setFlowInstId(Long flowInstId) {
        this.flowInstId = flowInstId;
    }

    public Long getFlowInstId() {
        return flowInstId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setAffixId(String affixId) {
        this.affixId = affixId;
    }

    public String getAffixId() {
        return affixId;
    }

    public void setTransferUnitId(Long transferUnitId) {
        this.transferUnitId = transferUnitId;
    }

    public Long getTransferUnitId() {
        return transferUnitId;
    }

    public void setWorkAffixId(String workAffixId) {
        this.workAffixId = workAffixId;
    }

    public String getWorkAffixId() {
        return workAffixId;
    }

    public void setSecurityFlag(String securityFlag) {
        this.securityFlag = securityFlag;
    }

    public String getSecurityFlag() {
        return securityFlag;
    }

    public void setTimeLimit(Date timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Date getTimeLimit() {
        return timeLimit;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setStateBackContent(String stateBackContent) {
        this.stateBackContent = stateBackContent;
    }

    public String getStateBackContent() {
        return stateBackContent;
    }

    public void setRmComplaintId(Long rmComplaintId) {
        this.rmComplaintId = rmComplaintId;
    }

    public Long getRmComplaintId() {
        return rmComplaintId;
    }

}
