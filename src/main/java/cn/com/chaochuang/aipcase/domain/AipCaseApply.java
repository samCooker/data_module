/*
 * FileName:    AipCaseApply.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年4月29日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.aipcase.domain;

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
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "case_apply_id")) })
public class AipCaseApply extends LongIdEntity {

    private static final long serialVersionUID = 6615160861586298395L;

    /** 远程系统案件基本编号 */
    private Long              rmCaseApplyId;
    /** 远程系统立案编号 */
    private Long              rmCaseLawId;
    /** 案件名称 */
    private String            caseName;
    /** 案由 */
    private String            caseBrief;
    /** 当事人 */
    private String            fillEntpContacter;
    /** 当事企业法人代表 */
    private String            fillEntpCorpname;
    /** 当事人地址 */
    private String            fillEntpAddr;
    /** 当事企业法人联系电话 */
    private String            corpPhone;
    /** 当事人邮编 */
    private String            contacterPostcode;
    /** 所属单位 */
    private Long              unitOrgId;
    /** 案件状态 */
    private String            caseStatus;
    /** 现场核实人 */
    private String            inspectMans;
    /** 当事人地址经度 */
    private String            longitude;
    /** 当事人地址纬度 */
    private String            latitude;

    /**
     * @return the rmCaseApplyId
     */
    public Long getRmCaseApplyId() {
        return rmCaseApplyId;
    }

    /**
     * @param rmCaseApplyId
     *            the rmCaseApplyId to set
     */
    public void setRmCaseApplyId(Long rmCaseApplyId) {
        this.rmCaseApplyId = rmCaseApplyId;
    }

    /**
     * @return the rmCaseLawId
     */
    public Long getRmCaseLawId() {
        return rmCaseLawId;
    }

    /**
     * @param rmCaseLawId
     *            the rmCaseLawId to set
     */
    public void setRmCaseLawId(Long rmCaseLawId) {
        this.rmCaseLawId = rmCaseLawId;
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
     * @return the contacterPostcode
     */
    public String getContacterPostcode() {
        return contacterPostcode;
    }

    /**
     * @param contacterPostcode
     *            the contacterPostcode to set
     */
    public void setContacterPostcode(String contacterPostcode) {
        this.contacterPostcode = contacterPostcode;
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
     * @return the caseStatus
     */
    public String getCaseStatus() {
        return caseStatus;
    }

    /**
     * @param caseStatus
     *            the caseStatus to set
     */
    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    /**
     * @return the inspectMans
     */
    public String getInspectMans() {
        return inspectMans;
    }

    /**
     * @param inspectMans
     *            the inspectMans to set
     */
    public void setInspectMans(String inspectMans) {
        this.inspectMans = inspectMans;
    }

    /**
     * @return the longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * @param longitude
     *            the longitude to set
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * @param latitude
     *            the latitude to set
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

}