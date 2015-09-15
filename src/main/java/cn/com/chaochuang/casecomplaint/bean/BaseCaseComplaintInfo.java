/*
 * FileName:    BaseCaseComplaintInfo.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月13日 (Shicx) 1.0 Create
 */
package cn.com.chaochuang.casecomplaint.bean;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author Shicx
 *
 */
public class BaseCaseComplaintInfo {

    /** 远程投诉id ! */
    private Long                      complaintId;
    /** 投诉举报编号 */
    private String                    complaintNum;
    /** 投诉举报受理码 */
    private String                    complaintCode;
    /** 举报标题 */
    private String                    complaintTitle;
    /** 并入的投诉举报id */
    private Long                      joinComplaintId;
    /** 投诉内容 */
    private String                    complaintContent;
    /** 投诉时间 */
    private Date                      complaintTime;
    /** 相关证据 */
    private String                    complaintProof;
    /** 举报人信息id */
    private Long                      complaintObjectId;
    /** 被投诉对象信息id */
    private Long                      complainedObjectId;
    /** 流程实例id */
    private String                    instId;
    /** 流程实例流水号 */
    private Long                      flowInstId;
    /** 延期理由 */
    private String                    delayReason;
    /** 答复/反馈内容 */
    private String                    answerContent;
    /** 答复/反馈时间 */
    private Date                      answerTime;
    /** 完成时间 */
    private Date                      finishTime;
    /** 受理单位id */
    private Long                      unitId;
    /** 投诉附件id */
    private String                    affixId;
    /** 转办来源单位id */
    private Long                      transferUnitId;
    /** 执法处理附件id */
    private String                    workAffixId;
    /** 办理时限 */
    private Date                      timeLimit;
    /** 上报时间 */
    private Date                      reportTime;
    /** 退回内容 */
    private String                    stateBackContent;
    /** 满意程度 0：满意1：非常满意2：不满意 */
    private String                    satisfFlag;

    /** 被投诉举报人对象 */
    private ComplainedObjInfo         complainedObjInfo;
    /** 投诉举报人对象 */
    private ComplaintObjInfo          complaintObjInfo;
    /** 节点信息 */
    private List<FlowHisNode>         nodesList;
    /** 意见信息 */
    private List<FlowInstNodeApprove> approveList;

    /** 投诉举报附件及办理附件 */
    private Set<ComplaintAttachInfo>  affixItemsList;

    /** 投诉环节分类 (字典字段，对应字典：dicProdFlag) */
    private String                    prodFlag;
    /** 重要程度 （字典字段：CaseImportant的importantType） */
    private String                    importantName;
    /** 业务类别 （字典字段,对应字典：dicComplaintBussType） */
    private String                    bussType;
    /** 受理方式 （字典字段，对应字典：dicComplaintCaseWay） */
    private String                    caseWay;
    /** 是否立案 （对应字典：dicComplaintLawFlag） */
    private String                    caseFlag;
    /** 是否延期 （对应字典：dicComplaintDelayFlag） */
    private String                    delayFlag;
    /** 是否匿名（字典字段： CaseComplaint的nameFlag,对应字典：dicComplaintNameFlag） */
    private String                    anonymityFlag;
    /** 投诉举报当前状态(字典字段：CaseComplaint的publicFlag,对应字典：dicComplainPublicFlag) */
    private String                    caseComplaintStauts;
    /** 投诉举报来源 (字典字段：CaseComplaintWay的complaintWayName) */
    private String                    complaintWayName;
    /** 问题分类（字典字段：CaseComplaintProblem 的problemName） */
    private String                    problemName;
    /** 投诉举报产品分类 （字典字段：CaseComplaintType 的complaintTypeName） */
    private String                    complaintTypeName;
    /** 是否保密 （对应字典：dicSecretFlag） */
    private String                    securityFlag;

    /**
     * @return the complaintId
     */
    public Long getComplaintId() {
        return complaintId;
    }

    /**
     * @param complaintId
     *            the complaintId to set
     */
    public void setComplaintId(Long complaintId) {
        this.complaintId = complaintId;
    }

    /**
     * @return the complaintNum
     */
    public String getComplaintNum() {
        return complaintNum;
    }

    /**
     * @param complaintNum
     *            the complaintNum to set
     */
    public void setComplaintNum(String complaintNum) {
        this.complaintNum = complaintNum;
    }

    /**
     * @return the complaintCode
     */
    public String getComplaintCode() {
        return complaintCode;
    }

    /**
     * @param complaintCode
     *            the complaintCode to set
     */
    public void setComplaintCode(String complaintCode) {
        this.complaintCode = complaintCode;
    }

    /**
     * @return the complaintTitle
     */
    public String getComplaintTitle() {
        return complaintTitle;
    }

    /**
     * @param complaintTitle
     *            the complaintTitle to set
     */
    public void setComplaintTitle(String complaintTitle) {
        this.complaintTitle = complaintTitle;
    }

    /**
     * @return the joinComplaintId
     */
    public Long getJoinComplaintId() {
        return joinComplaintId;
    }

    /**
     * @param joinComplaintId
     *            the joinComplaintId to set
     */
    public void setJoinComplaintId(Long joinComplaintId) {
        this.joinComplaintId = joinComplaintId;
    }

    /**
     * @return the complaintContent
     */
    public String getComplaintContent() {
        return complaintContent;
    }

    /**
     * @param complaintContent
     *            the complaintContent to set
     */
    public void setComplaintContent(String complaintContent) {
        this.complaintContent = complaintContent;
    }

    /**
     * @return the complaintTime
     */
    public Date getComplaintTime() {
        return complaintTime;
    }

    /**
     * @param complaintTime
     *            the complaintTime to set
     */
    public void setComplaintTime(Date complaintTime) {
        this.complaintTime = complaintTime;
    }

    /**
     * @return the complaintProof
     */
    public String getComplaintProof() {
        return complaintProof;
    }

    /**
     * @param complaintProof
     *            the complaintProof to set
     */
    public void setComplaintProof(String complaintProof) {
        this.complaintProof = complaintProof;
    }

    /**
     * @return the complaintObjectId
     */
    public Long getComplaintObjectId() {
        return complaintObjectId;
    }

    /**
     * @param complaintObjectId
     *            the complaintObjectId to set
     */
    public void setComplaintObjectId(Long complaintObjectId) {
        this.complaintObjectId = complaintObjectId;
    }

    /**
     * @return the complainedObjectId
     */
    public Long getComplainedObjectId() {
        return complainedObjectId;
    }

    /**
     * @param complainedObjectId
     *            the complainedObjectId to set
     */
    public void setComplainedObjectId(Long complainedObjectId) {
        this.complainedObjectId = complainedObjectId;
    }

    /**
     * @return the instId
     */
    public String getInstId() {
        return instId;
    }

    /**
     * @param instId
     *            the instId to set
     */
    public void setInstId(String instId) {
        this.instId = instId;
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
     * @return the delayReason
     */
    public String getDelayReason() {
        return delayReason;
    }

    /**
     * @param delayReason
     *            the delayReason to set
     */
    public void setDelayReason(String delayReason) {
        this.delayReason = delayReason;
    }

    /**
     * @return the answerContent
     */
    public String getAnswerContent() {
        return answerContent;
    }

    /**
     * @param answerContent
     *            the answerContent to set
     */
    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    /**
     * @return the answerTime
     */
    public Date getAnswerTime() {
        return answerTime;
    }

    /**
     * @param answerTime
     *            the answerTime to set
     */
    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    /**
     * @return the finishTime
     */
    public Date getFinishTime() {
        return finishTime;
    }

    /**
     * @param finishTime
     *            the finishTime to set
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * @return the unitId
     */
    public Long getUnitId() {
        return unitId;
    }

    /**
     * @param unitId
     *            the unitId to set
     */
    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    /**
     * @return the affixId
     */
    public String getAffixId() {
        return affixId;
    }

    /**
     * @param affixId
     *            the affixId to set
     */
    public void setAffixId(String affixId) {
        this.affixId = affixId;
    }

    /**
     * @return the transferUnitId
     */
    public Long getTransferUnitId() {
        return transferUnitId;
    }

    /**
     * @param transferUnitId
     *            the transferUnitId to set
     */
    public void setTransferUnitId(Long transferUnitId) {
        this.transferUnitId = transferUnitId;
    }

    /**
     * @return the workAffixId
     */
    public String getWorkAffixId() {
        return workAffixId;
    }

    /**
     * @param workAffixId
     *            the workAffixId to set
     */
    public void setWorkAffixId(String workAffixId) {
        this.workAffixId = workAffixId;
    }

    /**
     * @return the securityFlag
     */
    public String getSecurityFlag() {
        return securityFlag;
    }

    /**
     * @param securityFlag
     *            the securityFlag to set
     */
    public void setSecurityFlag(String securityFlag) {
        this.securityFlag = securityFlag;
    }

    /**
     * @return the timeLimit
     */
    public Date getTimeLimit() {
        return timeLimit;
    }

    /**
     * @param timeLimit
     *            the timeLimit to set
     */
    public void setTimeLimit(Date timeLimit) {
        this.timeLimit = timeLimit;
    }

    /**
     * @return the reportTime
     */
    public Date getReportTime() {
        return reportTime;
    }

    /**
     * @param reportTime
     *            the reportTime to set
     */
    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    /**
     * @return the stateBackContent
     */
    public String getStateBackContent() {
        return stateBackContent;
    }

    /**
     * @param stateBackContent
     *            the stateBackContent to set
     */
    public void setStateBackContent(String stateBackContent) {
        this.stateBackContent = stateBackContent;
    }

    /**
     * @return the importantName
     */
    public String getImportantName() {
        return importantName;
    }

    /**
     * @param importantName
     *            the importantName to set
     */
    public void setImportantName(String importantName) {
        this.importantName = importantName;
    }

    /**
     * @return the bussType
     */
    public String getBussType() {
        return bussType;
    }

    /**
     * @param bussType
     *            the bussType to set
     */
    public void setBussType(String bussType) {
        this.bussType = bussType;
    }

    /**
     * @return the caseWay
     */
    public String getCaseWay() {
        return caseWay;
    }

    /**
     * @param caseWay
     *            the caseWay to set
     */
    public void setCaseWay(String caseWay) {
        this.caseWay = caseWay;
    }

    /**
     * @return the caseFlag
     */
    public String getCaseFlag() {
        return caseFlag;
    }

    /**
     * @param caseFlag
     *            the caseFlag to set
     */
    public void setCaseFlag(String caseFlag) {
        this.caseFlag = caseFlag;
    }

    /**
     * @return the delayFlag
     */
    public String getDelayFlag() {
        return delayFlag;
    }

    /**
     * @param delayFlag
     *            the delayFlag to set
     */
    public void setDelayFlag(String delayFlag) {
        this.delayFlag = delayFlag;
    }

    /**
     * @return the anonymityFlag
     */
    public String getAnonymityFlag() {
        return anonymityFlag;
    }

    /**
     * @param anonymityFlag
     *            the anonymityFlag to set
     */
    public void setAnonymityFlag(String anonymityFlag) {
        this.anonymityFlag = anonymityFlag;
    }

    /**
     * @return the caseComplaintStauts
     */
    public String getCaseComplaintStauts() {
        return caseComplaintStauts;
    }

    /**
     * @param caseComplaintStauts
     *            the caseComplaintStauts to set
     */
    public void setCaseComplaintStauts(String caseComplaintStauts) {
        this.caseComplaintStauts = caseComplaintStauts;
    }

    /**
     * @return the complaintWayName
     */
    public String getComplaintWayName() {
        return complaintWayName;
    }

    /**
     * @param complaintWayName
     *            the complaintWayName to set
     */
    public void setComplaintWayName(String complaintWayName) {
        this.complaintWayName = complaintWayName;
    }

    /**
     * @return the problemName
     */
    public String getProblemName() {
        return problemName;
    }

    /**
     * @param problemName
     *            the problemName to set
     */
    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    /**
     * @return the complaintTypeName
     */
    public String getComplaintTypeName() {
        return complaintTypeName;
    }

    /**
     * @param complaintTypeName
     *            the complaintTypeName to set
     */
    public void setComplaintTypeName(String complaintTypeName) {
        this.complaintTypeName = complaintTypeName;
    }

    /**
     * @return the satisfFlag
     */
    public String getSatisfFlag() {
        return satisfFlag;
    }

    /**
     * @param satisfFlag
     *            the satisfFlag to set
     */
    public void setSatisfFlag(String satisfFlag) {
        this.satisfFlag = satisfFlag;
    }

    /**
     * @return the complainedObjInfo
     */
    public ComplainedObjInfo getComplainedObjInfo() {
        return complainedObjInfo;
    }

    /**
     * @param complainedObjInfo
     *            the complainedObjInfo to set
     */
    public void setComplainedObjInfo(ComplainedObjInfo complainedObjInfo) {
        this.complainedObjInfo = complainedObjInfo;
    }

    /**
     * @return the complaintObjInfo
     */
    public ComplaintObjInfo getComplaintObjInfo() {
        return complaintObjInfo;
    }

    /**
     * @param complaintObjInfo
     *            the complaintObjInfo to set
     */
    public void setComplaintObjInfo(ComplaintObjInfo complaintObjInfo) {
        this.complaintObjInfo = complaintObjInfo;
    }

    /**
     * @return the nodesList
     */
    public List<FlowHisNode> getNodesList() {
        return nodesList;
    }

    /**
     * @param nodesList
     *            the nodesList to set
     */
    public void setNodesList(List<FlowHisNode> nodesList) {
        this.nodesList = nodesList;
    }

    /**
     * @return the approveList
     */
    public List<FlowInstNodeApprove> getApproveList() {
        return approveList;
    }

    /**
     * @param approveList
     *            the approveList to set
     */
    public void setApproveList(List<FlowInstNodeApprove> approveList) {
        this.approveList = approveList;
    }

    /**
     * @return the affixItemsList
     */
    public Set<ComplaintAttachInfo> getAffixItemsList() {
        return affixItemsList;
    }

    /**
     * @param affixItemsList
     *            the affixItemsList to set
     */
    public void setAffixItemsList(Set<ComplaintAttachInfo> affixItemsList) {
        this.affixItemsList = affixItemsList;
    }

    /**
     * @return the prodFlag
     */
    public String getProdFlag() {
        return prodFlag;
    }

    /**
     * @param prodFlag
     *            the prodFlag to set
     */
    public void setProdFlag(String prodFlag) {
        this.prodFlag = prodFlag;
    }

}
