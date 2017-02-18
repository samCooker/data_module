package dbdata.aipcase.domain;

import cn.com.chaochuang.common.data.domain.LongIdEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "punish_entp_id"))})
public class AipPunishEntp extends LongIdEntity {

    private static final long serialVersionUID = 2704649749217240056L;
    /**
     * 案件
     */
    private Long caseApplyId;
    /**  */
    private String caseStatus;
    /**
     * 立案流水号
     */
    private Long caseLawId;
    /**
     * 案由
     */
    private String caseName;
    /**
     * 立案时间
     */
    private Date lawTime;
    /**
     * 案由类别
     */
    @OneToOne
    @JoinColumn(name = "case_cause_type_id")
    private AipCaseCauseType aipCaseCauseType;
    /**
     * 报表案源类型
     */
    private String reportCaseType;
    /**
     * 案件属性（药、食、妆、械、保）
     */
    private String caseProperty;
    /**
     * 案件分类
     */
    private String caseClasses;
    /**
     * 附加案件内容分类编号
     */
    private String classifyCode;
    /**
     * 案件处理结果
     */
    private String punishCaseType;
    /**
     * 适用简易程序
     */
    private String simpleProgram;
    /**
     * 处罚时间
     */
    private Date punishTime;
    /**
     * 立案年月
     */
    private String reportMonth;
    /**
     * 查办单位
     */
    private Long unitOrgId;
    /**
     * 涉及省份
     */
    private String relateProvince;
    /**
     * 当事人编号
     */
    private Long fillEntpId;
    /**
     * 违法主体编号
     */
    private String reportEntpType;
    /**
     * 监督检查人次
     */
    private Long supervisionMans;
    /**
     * 刑事处罚人数
     */
    private Long penalPunishMans;
    /**
     * 捉获嫌疑人数
     */
    private Long suspectNum;
    /**
     * 捣毁网络数
     */
    private Long networkNum;
    /**
     * 捣毁制假窝点数
     */
    private Long fakeNum;
    /**
     * 案值
     */
    private Double caseValue;
    /**
     * 没收物品总值
     */
    private Double goodsTotalValue;
    /**
     * 没收违法所得
     */
    private Double confiscateValue;
    /**
     * 罚款金额
     */
    private Double fineValue;
    /**
     * 销毁物品总值
     */
    private Double destoryValue;
    /**
     * 罚没假劣一次性注射器
     */
    private Long confiscateInjector;
    /**
     * 罚没假劣一次性输液器
     */
    private Long confiscateInfusion;
    /**
     * 是否停业整顿
     */
    private String suspendFlag;
    /**
     * 是否公开
     */
    private String isPublic;
    /**
     * 案件公开链接
     */
    private String publicLink;
    /**
     * 是否结案
     */
    private String finishFlag;
    /**
     * 是否取缔未经许可生产经营
     */
    private String forbidUnauthorProdFlag;
    /**
     * 是否取缔无证经营
     */
    private String forbidFlag;
    /**
     * 是否撤销审批文号
     */
    private String revokeNoteFlag;
    /**
     * 是否移送司法机关
     */
    private String isTransfer;
    /**
     * 是否是乳制品经营单位
     */
    private String milkFlag;
    /**
     * 是否含婴幼儿配方乳粉
     */
    private String babyMilk;
    /**
     * 仅限婴幼儿配方乳粉
     */
    private String onlyBabyMilk;
    /**
     * 司法机关是否结案
     */
    private String judicialFinishFlag;
    /**
     * 移交公安机关情况
     */
    private String transferInfo;
    /**
     * 是否吊销许可证
     */
    private String licenseFlag;
    /**
     * 吊销许可证类型
     */
    private String licenseType;
    /**
     * 吊销许可证号
     */
    private String withdrawLicenseNo;
    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    /**
     * 创建人编号
     */
    private Long createrId;
    /**
     * 创建人姓名
     */
    private String createrName;
    /**
     * 创建人部门编号
     */
    private Long createrDeptId;
    /**
     * 警告
     */
    private String warn;
    /**
     * 备注
     */
    private String remark;
    /**
     * 不涉刑理由
     */
    private String noCriminalReason;
    /**
     * 不立案理由
     */
    private String noCaseReason;
    /**
     * 不起诉理由
     */
    private String noSuedReason;
    /**
     * 行政处罚措施
     */
    private String punishResult;
    /**
     * 是否停止经营
     */
    private String stopManageFlag;
    /**
     * 公安机关处置情况
     */
    private String policeHandleFlag;
    /**
     * 检察院处置情况
     */
    private String procuracyHandleFlag;
    /**
     * 法院处置情况
     */
    private String courtHandleFlag;
    /** 附加案件内容分类对象 */
    //    @ManyToOne
    //    @JoinColumn(name = "report_case_classify_id", referencedColumnName = "report_case_classify_id")
    //    private AipReportCaseClassify aipReportCaseClassify;


    /**
     * @return the caseApplyId
     */
    public Long getCaseApplyId() {
        return caseApplyId;
    }

    /**
     * @param caseApplyId the caseApplyId to set
     */
    public void setCaseApplyId(Long caseApplyId) {
        this.caseApplyId = caseApplyId;
    }

    /**
     * @return the caseStatus
     */
    public String getCaseStatus() {
        return caseStatus;
    }

    /**
     * @param caseStatus the caseStatus to set
     */
    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    /**
     * @return the caseLawId
     */
    public Long getCaseLawId() {
        return caseLawId;
    }

    /**
     * @param caseLawId the caseLawId to set
     */
    public void setCaseLawId(Long caseLawId) {
        this.caseLawId = caseLawId;
    }

    /**
     * @return the caseName
     */
    public String getCaseName() {
        return caseName;
    }

    /**
     * @param caseName the caseName to set
     */
    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    /**
     * @return the lawTime
     */
    public Date getLawTime() {
        return lawTime;
    }

    /**
     * @param lawTime the lawTime to set
     */
    public void setLawTime(Date lawTime) {
        this.lawTime = lawTime;
    }

    /**
     * @return the aipCaseCauseType
     */
    public AipCaseCauseType getAipCaseCauseType() {
        return aipCaseCauseType;
    }

    /**
     * @param aipCaseCauseType the aipCaseCauseType to set
     */
    public void setAipCaseCauseType(AipCaseCauseType aipCaseCauseType) {
        this.aipCaseCauseType = aipCaseCauseType;
    }

    /**
     * @return the reportCaseType
     */
    public String getReportCaseType() {
        return reportCaseType;
    }

    /**
     * @param reportCaseType the reportCaseType to set
     */
    public void setReportCaseType(String reportCaseType) {
        this.reportCaseType = reportCaseType;
    }

    /**
     * @return the caseProperty
     */
    public String getCaseProperty() {
        return caseProperty;
    }

    /**
     * @param caseProperty the caseProperty to set
     */
    public void setCaseProperty(String caseProperty) {
        this.caseProperty = caseProperty;
    }

    /**
     * @return the caseClasses
     */
    public String getCaseClasses() {
        return caseClasses;
    }

    /**
     * @param caseClasses the caseClasses to set
     */
    public void setCaseClasses(String caseClasses) {
        this.caseClasses = caseClasses;
    }

    /**
     * @return the classifyCode
     */
    public String getClassifyCode() {
        return classifyCode;
    }

    /**
     * @param classifyCode the classifyCode to set
     */
    public void setClassifyCode(String classifyCode) {
        this.classifyCode = classifyCode;
    }

    /**
     * @return the punishCaseType
     */
    public String getPunishCaseType() {
        return punishCaseType;
    }

    /**
     * @param punishCaseType the punishCaseType to set
     */
    public void setPunishCaseType(String punishCaseType) {
        this.punishCaseType = punishCaseType;
    }

    /**
     * @return the simpleProgram
     */
    public String getSimpleProgram() {
        return simpleProgram;
    }

    /**
     * @param simpleProgram the simpleProgram to set
     */
    public void setSimpleProgram(String simpleProgram) {
        this.simpleProgram = simpleProgram;
    }

    /**
     * @return the punishTime
     */
    public Date getPunishTime() {
        return punishTime;
    }

    /**
     * @param punishTime the punishTime to set
     */
    public void setPunishTime(Date punishTime) {
        this.punishTime = punishTime;
    }

    /**
     * @return the reportMonth
     */
    public String getReportMonth() {
        return reportMonth;
    }

    /**
     * @param reportMonth the reportMonth to set
     */
    public void setReportMonth(String reportMonth) {
        this.reportMonth = reportMonth;
    }

    /**
     * @return the unitOrgId
     */
    public Long getUnitOrgId() {
        return unitOrgId;
    }

    /**
     * @param unitOrgId the unitOrgId to set
     */
    public void setUnitOrgId(Long unitOrgId) {
        this.unitOrgId = unitOrgId;
    }

    /**
     * @return the relateProvince
     */
    public String getRelateProvince() {
        return relateProvince;
    }

    /**
     * @param relateProvince the relateProvince to set
     */
    public void setRelateProvince(String relateProvince) {
        this.relateProvince = relateProvince;
    }

    /**
     * @return the fillEntpId
     */
    public Long getFillEntpId() {
        return fillEntpId;
    }

    /**
     * @param fillEntpId the fillEntpId to set
     */
    public void setFillEntpId(Long fillEntpId) {
        this.fillEntpId = fillEntpId;
    }

    /**
     * @return the reportEntpType
     */
    public String getReportEntpType() {
        return reportEntpType;
    }

    /**
     * @param reportEntpType the reportEntpType to set
     */
    public void setReportEntpType(String reportEntpType) {
        this.reportEntpType = reportEntpType;
    }

    /**
     * @return the supervisionMans
     */
    public Long getSupervisionMans() {
        return supervisionMans;
    }

    /**
     * @param supervisionMans the supervisionMans to set
     */
    public void setSupervisionMans(Long supervisionMans) {
        this.supervisionMans = supervisionMans;
    }

    /**
     * @return the penalPunishMans
     */
    public Long getPenalPunishMans() {
        return penalPunishMans;
    }

    /**
     * @param penalPunishMans the penalPunishMans to set
     */
    public void setPenalPunishMans(Long penalPunishMans) {
        this.penalPunishMans = penalPunishMans;
    }

    /**
     * @return the suspectNum
     */
    public Long getSuspectNum() {
        return suspectNum;
    }

    /**
     * @param suspectNum the suspectNum to set
     */
    public void setSuspectNum(Long suspectNum) {
        this.suspectNum = suspectNum;
    }

    /**
     * @return the networkNum
     */
    public Long getNetworkNum() {
        return networkNum;
    }

    /**
     * @param networkNum the networkNum to set
     */
    public void setNetworkNum(Long networkNum) {
        this.networkNum = networkNum;
    }

    /**
     * @return the fakeNum
     */
    public Long getFakeNum() {
        return fakeNum;
    }

    /**
     * @param fakeNum the fakeNum to set
     */
    public void setFakeNum(Long fakeNum) {
        this.fakeNum = fakeNum;
    }

    /**
     * @return the caseValue
     */
    public Double getCaseValue() {
        return caseValue;
    }

    /**
     * @param caseValue the caseValue to set
     */
    public void setCaseValue(Double caseValue) {
        this.caseValue = caseValue;
    }

    /**
     * @return the goodsTotalValue
     */
    public Double getGoodsTotalValue() {
        return goodsTotalValue;
    }

    /**
     * @param goodsTotalValue the goodsTotalValue to set
     */
    public void setGoodsTotalValue(Double goodsTotalValue) {
        this.goodsTotalValue = goodsTotalValue;
    }

    /**
     * @return the confiscateValue
     */
    public Double getConfiscateValue() {
        return confiscateValue;
    }

    /**
     * @param confiscateValue the confiscateValue to set
     */
    public void setConfiscateValue(Double confiscateValue) {
        this.confiscateValue = confiscateValue;
    }

    /**
     * @return the fineValue
     */
    public Double getFineValue() {
        return fineValue;
    }

    /**
     * @param fineValue the fineValue to set
     */
    public void setFineValue(Double fineValue) {
        this.fineValue = fineValue;
    }

    /**
     * @return the destoryValue
     */
    public Double getDestoryValue() {
        return destoryValue;
    }

    /**
     * @param destoryValue the destoryValue to set
     */
    public void setDestoryValue(Double destoryValue) {
        this.destoryValue = destoryValue;
    }

    /**
     * @return the confiscateInjector
     */
    public Long getConfiscateInjector() {
        return confiscateInjector;
    }

    /**
     * @param confiscateInjector the confiscateInjector to set
     */
    public void setConfiscateInjector(Long confiscateInjector) {
        this.confiscateInjector = confiscateInjector;
    }

    /**
     * @return the confiscateInfusion
     */
    public Long getConfiscateInfusion() {
        return confiscateInfusion;
    }

    /**
     * @param confiscateInfusion the confiscateInfusion to set
     */
    public void setConfiscateInfusion(Long confiscateInfusion) {
        this.confiscateInfusion = confiscateInfusion;
    }

    /**
     * @return the suspendFlag
     */
    public String getSuspendFlag() {
        return suspendFlag;
    }

    /**
     * @param suspendFlag the suspendFlag to set
     */
    public void setSuspendFlag(String suspendFlag) {
        this.suspendFlag = suspendFlag;
    }

    /**
     * @return the isPublic
     */
    public String getIsPublic() {
        return isPublic;
    }

    /**
     * @param isPublic the isPublic to set
     */
    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic;
    }

    /**
     * @return the publicLink
     */
    public String getPublicLink() {
        return publicLink;
    }

    /**
     * @param publicLink the publicLink to set
     */
    public void setPublicLink(String publicLink) {
        this.publicLink = publicLink;
    }

    /**
     * @return the finishFlag
     */
    public String getFinishFlag() {
        return finishFlag;
    }

    /**
     * @param finishFlag the finishFlag to set
     */
    public void setFinishFlag(String finishFlag) {
        this.finishFlag = finishFlag;
    }

    /**
     * @return the forbidUnauthorProdFlag
     */
    public String getForbidUnauthorProdFlag() {
        return forbidUnauthorProdFlag;
    }

    /**
     * @param forbidUnauthorProdFlag the forbidUnauthorProdFlag to set
     */
    public void setForbidUnauthorProdFlag(String forbidUnauthorProdFlag) {
        this.forbidUnauthorProdFlag = forbidUnauthorProdFlag;
    }

    /**
     * @return the forbidFlag
     */
    public String getForbidFlag() {
        return forbidFlag;
    }

    /**
     * @param forbidFlag the forbidFlag to set
     */
    public void setForbidFlag(String forbidFlag) {
        this.forbidFlag = forbidFlag;
    }

    /**
     * @return the revokeNoteFlag
     */
    public String getRevokeNoteFlag() {
        return revokeNoteFlag;
    }

    /**
     * @param revokeNoteFlag the revokeNoteFlag to set
     */
    public void setRevokeNoteFlag(String revokeNoteFlag) {
        this.revokeNoteFlag = revokeNoteFlag;
    }

    /**
     * @return the isTransfer
     */
    public String getIsTransfer() {
        return isTransfer;
    }

    /**
     * @param isTransfer the isTransfer to set
     */
    public void setIsTransfer(String isTransfer) {
        this.isTransfer = isTransfer;
    }

    /**
     * @return the milkFlag
     */
    public String getMilkFlag() {
        return milkFlag;
    }

    /**
     * @param milkFlag the milkFlag to set
     */
    public void setMilkFlag(String milkFlag) {
        this.milkFlag = milkFlag;
    }

    /**
     * @return the babyMilk
     */
    public String getBabyMilk() {
        return babyMilk;
    }

    /**
     * @param babyMilk the babyMilk to set
     */
    public void setBabyMilk(String babyMilk) {
        this.babyMilk = babyMilk;
    }

    /**
     * @return the onlyBabyMilk
     */
    public String getOnlyBabyMilk() {
        return onlyBabyMilk;
    }

    /**
     * @param onlyBabyMilk the onlyBabyMilk to set
     */
    public void setOnlyBabyMilk(String onlyBabyMilk) {
        this.onlyBabyMilk = onlyBabyMilk;
    }

    /**
     * @return the judicialFinishFlag
     */
    public String getJudicialFinishFlag() {
        return judicialFinishFlag;
    }

    /**
     * @param judicialFinishFlag the judicialFinishFlag to set
     */
    public void setJudicialFinishFlag(String judicialFinishFlag) {
        this.judicialFinishFlag = judicialFinishFlag;
    }

    /**
     * @return the transferInfo
     */
    public String getTransferInfo() {
        return transferInfo;
    }

    /**
     * @param transferInfo the transferInfo to set
     */
    public void setTransferInfo(String transferInfo) {
        this.transferInfo = transferInfo;
    }

    /**
     * @return the licenseFlag
     */
    public String getLicenseFlag() {
        return licenseFlag;
    }

    /**
     * @param licenseFlag the licenseFlag to set
     */
    public void setLicenseFlag(String licenseFlag) {
        this.licenseFlag = licenseFlag;
    }

    /**
     * @return the licenseType
     */
    public String getLicenseType() {
        return licenseType;
    }

    /**
     * @param licenseType the licenseType to set
     */
    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    /**
     * @return the withdrawLicenseNo
     */
    public String getWithdrawLicenseNo() {
        return withdrawLicenseNo;
    }

    /**
     * @param withdrawLicenseNo the withdrawLicenseNo to set
     */
    public void setWithdrawLicenseNo(String withdrawLicenseNo) {
        this.withdrawLicenseNo = withdrawLicenseNo;
    }

    /**
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the createrId
     */
    public Long getCreaterId() {
        return createrId;
    }

    /**
     * @param createrId the createrId to set
     */
    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    /**
     * @return the createrName
     */
    public String getCreaterName() {
        return createrName;
    }

    /**
     * @param createrName the createrName to set
     */
    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }

    /**
     * @return the createrDeptId
     */
    public Long getCreaterDeptId() {
        return createrDeptId;
    }

    /**
     * @param createrDeptId the createrDeptId to set
     */
    public void setCreaterDeptId(Long createrDeptId) {
        this.createrDeptId = createrDeptId;
    }

    /**
     * @return the warn
     */
    public String getWarn() {
        return warn;
    }

    /**
     * @param warn the warn to set
     */
    public void setWarn(String warn) {
        this.warn = warn;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return the noCriminalReason
     */
    public String getNoCriminalReason() {
        return noCriminalReason;
    }

    /**
     * @param noCriminalReason the noCriminalReason to set
     */
    public void setNoCriminalReason(String noCriminalReason) {
        this.noCriminalReason = noCriminalReason;
    }

    /**
     * @return the noCaseReason
     */
    public String getNoCaseReason() {
        return noCaseReason;
    }

    /**
     * @param noCaseReason the noCaseReason to set
     */
    public void setNoCaseReason(String noCaseReason) {
        this.noCaseReason = noCaseReason;
    }

    /**
     * @return the noSuedReason
     */
    public String getNoSuedReason() {
        return noSuedReason;
    }

    /**
     * @param noSuedReason the noSuedReason to set
     */
    public void setNoSuedReason(String noSuedReason) {
        this.noSuedReason = noSuedReason;
    }

    /**
     * @return the punishResult
     */
    public String getPunishResult() {
        return punishResult;
    }

    /**
     * @param punishResult the punishResult to set
     */
    public void setPunishResult(String punishResult) {
        this.punishResult = punishResult;
    }

    /**
     * @return the stopManageFlag
     */
    public String getStopManageFlag() {
        return stopManageFlag;
    }

    /**
     * @param stopManageFlag the stopManageFlag to set
     */
    public void setStopManageFlag(String stopManageFlag) {
        this.stopManageFlag = stopManageFlag;
    }

    /**
     * @return the policeHandleFlag
     */
    public String getPoliceHandleFlag() {
        return policeHandleFlag;
    }

    /**
     * @param policeHandleFlag the policeHandleFlag to set
     */
    public void setPoliceHandleFlag(String policeHandleFlag) {
        this.policeHandleFlag = policeHandleFlag;
    }

    /**
     * @return the procuracyHandleFlag
     */
    public String getProcuracyHandleFlag() {
        return procuracyHandleFlag;
    }

    /**
     * @param procuracyHandleFlag the procuracyHandleFlag to set
     */
    public void setProcuracyHandleFlag(String procuracyHandleFlag) {
        this.procuracyHandleFlag = procuracyHandleFlag;
    }

    /**
     * @return the courtHandleFlag
     */
    public String getCourtHandleFlag() {
        return courtHandleFlag;
    }

    /**
     * @param courtHandleFlag the courtHandleFlag to set
     */
    public void setCourtHandleFlag(String courtHandleFlag) {
        this.courtHandleFlag = courtHandleFlag;
    }
}