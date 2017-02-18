package dbdata.aipcase.domain;


import cn.com.chaochuang.common.data.domain.LongIdEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "case_apply_id")) })
public class AipCaseApply extends LongIdEntity {

    /** 案件来源类型 */
    @ManyToOne
    @JoinColumn(name = "case_type_id")
    private AipCaseType            aipCaseType;

    /** 立案表 */
    @OneToOne
    @JoinColumn(name = "case_law_id")
    private AipCaseLaw             aipCaseLaw;

    /** 临时案件登记标识 */
    private String      tempFlag;
    /** 填写时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    /** 投诉受理码 */
    private String complaintCode;
    /** 案由 */
    private String caseBrief;
    /** 当事企业编号 */
    private Long fillEntpId;
    /** 当事企业类别 */
    private String entpType;
    /** 当事企业名称 */
    private String fillEntpName;
    /** 当事人 */
    private String fillEntpContacter;
    /** 当事人联系电话 */
    private String fillContacterPhone;
    /** 当事企业法人代表 */
    private String fillEntpCorpname;
    /** 当事人地址 */
    private String fillEntpAddr;
    /** 当事企业法人性别 */
    private String                    corpGender;
    /** 当事企业法人职务 */
    private String corpDuty;
    /** 当事企业法人年纪 */
    private String corpAge;
    /** 当事企业法人身份证 */
    private String corpIdentity;
    /** 当事企业法人联系电话 */
    private String corpPhone;
    /** 是否立案 */
    private String               caseFlag;
    /** 所属单位 */
    private Long unitOrgId;
    /** 案件状态 */
    private String             caseStatus;
    /** 案件状态变更时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date caseStatusChangeDate;
    /** 案件办结方式 */
    private String             finishFlag;

    private String caseCode;
    /** 案件处罚方式 */
    private String         casePunishFlag;
    /** 创建人 */
    private Long createrId;
    /** 创建人姓名 */
    private String createrName;
    /** 创建人部门编号 */
    private Long createrDeptId;
    /** 创建人部门名称 */
    private String createrDeptName;
    /** 当事人性别 */
    private String                    contacterGender;
    /** 当事人职务 */
    private String contacterDuty;
    /** 当事人年纪 */
    private String contacterAge;
    /** 当事人邮编 */
    private String contacterPostcode;
    /** 转办来源单位 */
    private Long transferUnitId;
    /** 登记时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date loginDate;
    /** 现场核实人 */
    private String inspectMans;
    /** 现场核实人编号 */
    private String inspectManIds;
    /** 现场核实人执法证编号 */
    private String inspectManIdens;
    /** 案件承办人姓名，即立案审批表中调查取证人 */
    private String undertakerMans;
    /** 案件承办人编号，即立案审批表中调查取证人编号 */
    private String undertakerManIds;
    /** 记录人 */
    private String recordMan;
    /** 记录时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordTime;
    /** 归档时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date fileDate;
    /** 归档标识 */
    private String               fileFlag;


    public AipCaseType getAipCaseType() {
        return aipCaseType;
    }

    public void setAipCaseType(AipCaseType aipCaseType) {
        this.aipCaseType = aipCaseType;
    }

    public AipCaseLaw getAipCaseLaw() {
        return aipCaseLaw;
    }

    public void setAipCaseLaw(AipCaseLaw aipCaseLaw) {
        this.aipCaseLaw = aipCaseLaw;
    }

    public String getTempFlag() {
        return tempFlag;
    }

    public void setTempFlag(String tempFlag) {
        this.tempFlag = tempFlag;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getComplaintCode() {
        return complaintCode;
    }

    public void setComplaintCode(String complaintCode) {
        this.complaintCode = complaintCode;
    }

    public String getCaseBrief() {
        return caseBrief;
    }

    public void setCaseBrief(String caseBrief) {
        this.caseBrief = caseBrief;
    }

    public Long getFillEntpId() {
        return fillEntpId;
    }

    public void setFillEntpId(Long fillEntpId) {
        this.fillEntpId = fillEntpId;
    }

    public String getEntpType() {
        return entpType;
    }

    public void setEntpType(String entpType) {
        this.entpType = entpType;
    }

    public String getFillEntpName() {
        return fillEntpName;
    }

    public void setFillEntpName(String fillEntpName) {
        this.fillEntpName = fillEntpName;
    }

    public String getFillEntpContacter() {
        return fillEntpContacter;
    }

    public void setFillEntpContacter(String fillEntpContacter) {
        this.fillEntpContacter = fillEntpContacter;
    }

    public String getFillContacterPhone() {
        return fillContacterPhone;
    }

    public void setFillContacterPhone(String fillContacterPhone) {
        this.fillContacterPhone = fillContacterPhone;
    }

    public String getFillEntpCorpname() {
        return fillEntpCorpname;
    }

    public void setFillEntpCorpname(String fillEntpCorpname) {
        this.fillEntpCorpname = fillEntpCorpname;
    }

    public String getFillEntpAddr() {
        return fillEntpAddr;
    }

    public void setFillEntpAddr(String fillEntpAddr) {
        this.fillEntpAddr = fillEntpAddr;
    }

    public String getCorpGender() {
        return corpGender;
    }

    public void setCorpGender(String corpGender) {
        this.corpGender = corpGender;
    }

    public String getCorpDuty() {
        return corpDuty;
    }

    public void setCorpDuty(String corpDuty) {
        this.corpDuty = corpDuty;
    }

    public String getCorpAge() {
        return corpAge;
    }

    public void setCorpAge(String corpAge) {
        this.corpAge = corpAge;
    }

    public String getCorpIdentity() {
        return corpIdentity;
    }

    public void setCorpIdentity(String corpIdentity) {
        this.corpIdentity = corpIdentity;
    }

    public String getCorpPhone() {
        return corpPhone;
    }

    public void setCorpPhone(String corpPhone) {
        this.corpPhone = corpPhone;
    }

    public String getCaseFlag() {
        return caseFlag;
    }

    public void setCaseFlag(String caseFlag) {
        this.caseFlag = caseFlag;
    }

    public Long getUnitOrgId() {
        return unitOrgId;
    }

    public void setUnitOrgId(Long unitOrgId) {
        this.unitOrgId = unitOrgId;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public Date getCaseStatusChangeDate() {
        return caseStatusChangeDate;
    }

    public void setCaseStatusChangeDate(Date caseStatusChangeDate) {
        this.caseStatusChangeDate = caseStatusChangeDate;
    }

    public String getFinishFlag() {
        return finishFlag;
    }

    public void setFinishFlag(String finishFlag) {
        this.finishFlag = finishFlag;
    }

    public String getCaseCode() {
        return caseCode;
    }

    public void setCaseCode(String caseCode) {
        this.caseCode = caseCode;
    }

    public String getCasePunishFlag() {
        return casePunishFlag;
    }

    public void setCasePunishFlag(String casePunishFlag) {
        this.casePunishFlag = casePunishFlag;
    }

    public Long getCreaterId() {
        return createrId;
    }

    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    public String getCreaterName() {
        return createrName;
    }

    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }

    public Long getCreaterDeptId() {
        return createrDeptId;
    }

    public void setCreaterDeptId(Long createrDeptId) {
        this.createrDeptId = createrDeptId;
    }

    public String getCreaterDeptName() {
        return createrDeptName;
    }

    public void setCreaterDeptName(String createrDeptName) {
        this.createrDeptName = createrDeptName;
    }

    public String getContacterGender() {
        return contacterGender;
    }

    public void setContacterGender(String contacterGender) {
        this.contacterGender = contacterGender;
    }

    public String getContacterDuty() {
        return contacterDuty;
    }

    public void setContacterDuty(String contacterDuty) {
        this.contacterDuty = contacterDuty;
    }

    public String getContacterAge() {
        return contacterAge;
    }

    public void setContacterAge(String contacterAge) {
        this.contacterAge = contacterAge;
    }

    public String getContacterPostcode() {
        return contacterPostcode;
    }

    public void setContacterPostcode(String contacterPostcode) {
        this.contacterPostcode = contacterPostcode;
    }

    public Long getTransferUnitId() {
        return transferUnitId;
    }

    public void setTransferUnitId(Long transferUnitId) {
        this.transferUnitId = transferUnitId;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getInspectMans() {
        return inspectMans;
    }

    public void setInspectMans(String inspectMans) {
        this.inspectMans = inspectMans;
    }

    public String getInspectManIds() {
        return inspectManIds;
    }

    public void setInspectManIds(String inspectManIds) {
        this.inspectManIds = inspectManIds;
    }

    public String getInspectManIdens() {
        return inspectManIdens;
    }

    public void setInspectManIdens(String inspectManIdens) {
        this.inspectManIdens = inspectManIdens;
    }

    public String getUndertakerMans() {
        return undertakerMans;
    }

    public void setUndertakerMans(String undertakerMans) {
        this.undertakerMans = undertakerMans;
    }

    public String getUndertakerManIds() {
        return undertakerManIds;
    }

    public void setUndertakerManIds(String undertakerManIds) {
        this.undertakerManIds = undertakerManIds;
    }

    public String getRecordMan() {
        return recordMan;
    }

    public void setRecordMan(String recordMan) {
        this.recordMan = recordMan;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public Date getFileDate() {
        return fileDate;
    }

    public void setFileDate(Date fileDate) {
        this.fileDate = fileDate;
    }

    public String getFileFlag() {
        return fileFlag;
    }

    public void setFileFlag(String fileFlag) {
        this.fileFlag = fileFlag;
    }

    /**
     * 获取案件待办的标识
     *
     * @return
     */
    public String getFordoFlag(Long noteModuleId) {
        return "caseApplyId=" + this.id + "@@noteModuleId=" + noteModuleId + "@@caseStatus=" + this.caseStatus;
    }
}