/*
 * FileName:    AipPunishEntp.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年8月11日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.aipcase.domain;

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
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "punish_entp_id")) })
public class AipPunishEntp extends LongIdEntity {
    /**
     *
     */
    private static final long serialVersionUID = 720060827926432974L;

    /** 原处罚记录编号 */
    private Long              rmPunishEntpId;
    /** 上报年月 */
    private String            reportMonth;
    /** 查办单位编号 */
    private Long              unitOrgId;
    /** 捣毁制假窝点数 */
    private Long              fakeNum;
    /** 案值 */
    private Double            caseValue;
    /** 没收违法所得 */
    private Double            confiscateValue;
    /** 罚款金额 */
    private Double            fineValue;
    /** 没收物品总值 */
    private Double            goodsTotalValue;
    /** 吊销许可证号 */
    private String            withdrawLicenseNo;
    /** 涉及省份 */
    private String            relateProvince;
    /** 捉获嫌疑人数 */
    private Long              suspectNum;
    /** 捣毁网络数 */
    private Long              networkNum;
    /** 案件处理结果 */
    private String            punishCaseType;
    /** 警告 */
    private String            warn;
    /** 移交公安机关情况 */
    private String            transferInfo;
    /** 备注 */
    private String            remark;
    /** 是否移交公安机关 */
    private String            isTransfer;
    /** 报表案源类型 */
    private String            reportCaseType;
    /** 是否结案 */
    private String            finishFlag;
    /** 是否停业整顿 */
    private String            suspendFlag;
    /** 销毁价值 */
    private Double            destoryValue;
    /** 是否取缔未经许可生产经营 */
    private String            forbidFlag;
    /** 刑事处罚人数 */
    private Long              penalPunishMans;
    /** 是否撤销审批文号 */
    private String            revokeNoteFlag;
    /** 司法机关是否结案 */
    private String            judicialFinishFlag;
    /** 监督检查人次 */
    private Long              supervisionMans;
    /** 罚没假劣一次性注射器 */
    private Long              confiscateInjector;
    /** 罚没假劣一次性输液器 */
    private Long              confiscateInfusion;
    /** 是否取缔未经许可生产经营 */
    private String            forbidUnauthorProdFlag;
    /** 处罚时间 */
    private Date              punishTime;
    /** 案件属性（药、食、妆、械、保） */
    private String            caseProperty;
    /** 是否含婴幼儿配方乳粉 */
    private String            babyMilk;
    /** 仅限婴幼儿配方乳粉 */
    private String            onlyBabyMilk;
    /** 案由 */
    private String            caseName;
    /** 是否停止经营 */
    private String            stopManageFlag;
    /** 是否是乳制品经营单位 */
    private String            milkFlag;
    /** 当事人（企业）名称 */
    private String            fillEntpContacter;
    /** 当事人地址 */
    private String            fillEntpAddr;
    /** 联系电话 */
    private String            corpPhone;
    /** 法人负责人 */
    private String            fillEntpCorpname;
    /** 案件摘要 */
    private String            caseBrief;
    /** 案件所属单位名称 */
    private String            unitOrgName;
    /** 记录创建人 */
    private String            createrName;

    /**
     * @return the reportMonth
     */
    public String getReportMonth() {
        return reportMonth;
    }

    /**
     * @param reportMonth
     *            the reportMonth to set
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
     * @param unitOrgId
     *            the unitOrgId to set
     */
    public void setUnitOrgId(Long unitOrgId) {
        this.unitOrgId = unitOrgId;
    }

    /**
     * @return the fakeNum
     */
    public Long getFakeNum() {
        return fakeNum;
    }

    /**
     * @param fakeNum
     *            the fakeNum to set
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
     * @param caseValue
     *            the caseValue to set
     */
    public void setCaseValue(Double caseValue) {
        this.caseValue = caseValue;
    }

    /**
     * @return the confiscateValue
     */
    public Double getConfiscateValue() {
        return confiscateValue;
    }

    /**
     * @param confiscateValue
     *            the confiscateValue to set
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
     * @param fineValue
     *            the fineValue to set
     */
    public void setFineValue(Double fineValue) {
        this.fineValue = fineValue;
    }

    /**
     * @return the goodsTotalValue
     */
    public Double getGoodsTotalValue() {
        return goodsTotalValue;
    }

    /**
     * @param goodsTotalValue
     *            the goodsTotalValue to set
     */
    public void setGoodsTotalValue(Double goodsTotalValue) {
        this.goodsTotalValue = goodsTotalValue;
    }

    /**
     * @return the destoryValue
     */
    public Double getDestoryValue() {
        return destoryValue;
    }

    /**
     * @param destoryValue
     *            the destoryValue to set
     */
    public void setDestoryValue(Double destoryValue) {
        this.destoryValue = destoryValue;
    }

    /**
     * @return the withdrawLicenseNo
     */
    public String getWithdrawLicenseNo() {
        return withdrawLicenseNo;
    }

    /**
     * @param withdrawLicenseNo
     *            the withdrawLicenseNo to set
     */
    public void setWithdrawLicenseNo(String withdrawLicenseNo) {
        this.withdrawLicenseNo = withdrawLicenseNo;
    }

    /**
     * @return the relateProvince
     */
    public String getRelateProvince() {
        return relateProvince;
    }

    /**
     * @param relateProvince
     *            the relateProvince to set
     */
    public void setRelateProvince(String relateProvince) {
        this.relateProvince = relateProvince;
    }

    /**
     * @return the suspectNum
     */
    public Long getSuspectNum() {
        return suspectNum;
    }

    /**
     * @param suspectNum
     *            the suspectNum to set
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
     * @param networkNum
     *            the networkNum to set
     */
    public void setNetworkNum(Long networkNum) {
        this.networkNum = networkNum;
    }

    /**
     * @return the punishCaseType
     */
    public String getPunishCaseType() {
        return punishCaseType;
    }

    /**
     * @param punishCaseType
     *            the punishCaseType to set
     */
    public void setPunishCaseType(String punishCaseType) {
        this.punishCaseType = punishCaseType;
    }

    /**
     * @return the warn
     */
    public String getWarn() {
        return warn;
    }

    /**
     * @param warn
     *            the warn to set
     */
    public void setWarn(String warn) {
        this.warn = warn;
    }

    /**
     * @return the transferInfo
     */
    public String getTransferInfo() {
        return transferInfo;
    }

    /**
     * @param transferInfo
     *            the transferInfo to set
     */
    public void setTransferInfo(String transferInfo) {
        this.transferInfo = transferInfo;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     *            the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return the isTransfer
     */
    public String getIsTransfer() {
        return isTransfer;
    }

    /**
     * @param isTransfer
     *            the isTransfer to set
     */
    public void setIsTransfer(String isTransfer) {
        this.isTransfer = isTransfer;
    }

    /**
     * @return the reportCaseType
     */
    public String getReportCaseType() {
        return reportCaseType;
    }

    /**
     * @param reportCaseType
     *            the reportCaseType to set
     */
    public void setReportCaseType(String reportCaseType) {
        this.reportCaseType = reportCaseType;
    }

    /**
     * @return the finishFlag
     */
    public String getFinishFlag() {
        return finishFlag;
    }

    /**
     * @param finishFlag
     *            the finishFlag to set
     */
    public void setFinishFlag(String finishFlag) {
        this.finishFlag = finishFlag;
    }

    /**
     * @return the suspendFlag
     */
    public String getSuspendFlag() {
        return suspendFlag;
    }

    /**
     * @param suspendFlag
     *            the suspendFlag to set
     */
    public void setSuspendFlag(String suspendFlag) {
        this.suspendFlag = suspendFlag;
    }

    /**
     * @return the forbidFlag
     */
    public String getForbidFlag() {
        return forbidFlag;
    }

    /**
     * @param forbidFlag
     *            the forbidFlag to set
     */
    public void setForbidFlag(String forbidFlag) {
        this.forbidFlag = forbidFlag;
    }

    /**
     * @return the penalPunishMans
     */
    public Long getPenalPunishMans() {
        return penalPunishMans;
    }

    /**
     * @param penalPunishMans
     *            the penalPunishMans to set
     */
    public void setPenalPunishMans(Long penalPunishMans) {
        this.penalPunishMans = penalPunishMans;
    }

    /**
     * @return the revokeNoteFlag
     */
    public String getRevokeNoteFlag() {
        return revokeNoteFlag;
    }

    /**
     * @param revokeNoteFlag
     *            the revokeNoteFlag to set
     */
    public void setRevokeNoteFlag(String revokeNoteFlag) {
        this.revokeNoteFlag = revokeNoteFlag;
    }

    /**
     * @return the judicialFinishFlag
     */
    public String getJudicialFinishFlag() {
        return judicialFinishFlag;
    }

    /**
     * @param judicialFinishFlag
     *            the judicialFinishFlag to set
     */
    public void setJudicialFinishFlag(String judicialFinishFlag) {
        this.judicialFinishFlag = judicialFinishFlag;
    }

    /**
     * @return the supervisionMans
     */
    public Long getSupervisionMans() {
        return supervisionMans;
    }

    /**
     * @param supervisionMans
     *            the supervisionMans to set
     */
    public void setSupervisionMans(Long supervisionMans) {
        this.supervisionMans = supervisionMans;
    }

    /**
     * @return the confiscateInjector
     */
    public Long getConfiscateInjector() {
        return confiscateInjector;
    }

    /**
     * @param confiscateInjector
     *            the confiscateInjector to set
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
     * @param confiscateInfusion
     *            the confiscateInfusion to set
     */
    public void setConfiscateInfusion(Long confiscateInfusion) {
        this.confiscateInfusion = confiscateInfusion;
    }

    /**
     * @return the forbidUnauthorProdFlag
     */
    public String getForbidUnauthorProdFlag() {
        return forbidUnauthorProdFlag;
    }

    /**
     * @param forbidUnauthorProdFlag
     *            the forbidUnauthorProdFlag to set
     */
    public void setForbidUnauthorProdFlag(String forbidUnauthorProdFlag) {
        this.forbidUnauthorProdFlag = forbidUnauthorProdFlag;
    }

    /**
     * @return the punishTime
     */
    public Date getPunishTime() {
        return punishTime;
    }

    /**
     * @param punishTime
     *            the punishTime to set
     */
    public void setPunishTime(Date punishTime) {
        this.punishTime = punishTime;
    }

    /**
     * @return the caseProperty
     */
    public String getCaseProperty() {
        return caseProperty;
    }

    /**
     * @param caseProperty
     *            the caseProperty to set
     */
    public void setCaseProperty(String caseProperty) {
        this.caseProperty = caseProperty;
    }

    /**
     * @return the babyMilk
     */
    public String getBabyMilk() {
        return babyMilk;
    }

    /**
     * @param babyMilk
     *            the babyMilk to set
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
     * @param onlyBabyMilk
     *            the onlyBabyMilk to set
     */
    public void setOnlyBabyMilk(String onlyBabyMilk) {
        this.onlyBabyMilk = onlyBabyMilk;
    }

    /**
     * @return the caseName
     */
    public String getCaseName() {
        return caseName;
    }

    /**
     * @param caseName
     *            the caseName to set
     */
    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    /**
     * @return the stopManageFlag
     */
    public String getStopManageFlag() {
        return stopManageFlag;
    }

    /**
     * @param stopManageFlag
     *            the stopManageFlag to set
     */
    public void setStopManageFlag(String stopManageFlag) {
        this.stopManageFlag = stopManageFlag;
    }

    /**
     * @return the milkFlag
     */
    public String getMilkFlag() {
        return milkFlag;
    }

    /**
     * @param milkFlag
     *            the milkFlag to set
     */
    public void setMilkFlag(String milkFlag) {
        this.milkFlag = milkFlag;
    }

    /**
     * @return the fillEntpContacter
     */
    public String getFillEntpContacter() {
        return fillEntpContacter;
    }

    /**
     * @param fillEntpContacter
     *            the fillEntpContacter to set
     */
    public void setFillEntpContacter(String fillEntpContacter) {
        this.fillEntpContacter = fillEntpContacter;
    }

    /**
     * @return the fillEntpAddr
     */
    public String getFillEntpAddr() {
        return fillEntpAddr;
    }

    /**
     * @param fillEntpAddr
     *            the fillEntpAddr to set
     */
    public void setFillEntpAddr(String fillEntpAddr) {
        this.fillEntpAddr = fillEntpAddr;
    }

    /**
     * @return the corpPhone
     */
    public String getCorpPhone() {
        return corpPhone;
    }

    /**
     * @param corpPhone
     *            the corpPhone to set
     */
    public void setCorpPhone(String corpPhone) {
        this.corpPhone = corpPhone;
    }

    /**
     * @return the fillEntpCorpname
     */
    public String getFillEntpCorpname() {
        return fillEntpCorpname;
    }

    /**
     * @param fillEntpCorpname
     *            the fillEntpCorpname to set
     */
    public void setFillEntpCorpname(String fillEntpCorpname) {
        this.fillEntpCorpname = fillEntpCorpname;
    }

    /**
     * @return the caseBrief
     */
    public String getCaseBrief() {
        return caseBrief;
    }

    /**
     * @param caseBrief
     *            the caseBrief to set
     */
    public void setCaseBrief(String caseBrief) {
        this.caseBrief = caseBrief;
    }

    /**
     * @return the unitOrgName
     */
    public String getUnitOrgName() {
        return unitOrgName;
    }

    /**
     * @param unitOrgName
     *            the unitOrgName to set
     */
    public void setUnitOrgName(String unitOrgName) {
        this.unitOrgName = unitOrgName;
    }

    /**
     * @return the rmPunishEntpId
     */
    public Long getRmPunishEntpId() {
        return rmPunishEntpId;
    }

    /**
     * @param rmPunishEntpId
     *            the rmPunishEntpId to set
     */
    public void setRmPunishEntpId(Long rmPunishEntpId) {
        this.rmPunishEntpId = rmPunishEntpId;
    }

    /**
     * @return the createrName
     */
    public String getCreaterName() {
        return createrName;
    }

    /**
     * @param createrName
     *            the createrName to set
     */
    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }

}
