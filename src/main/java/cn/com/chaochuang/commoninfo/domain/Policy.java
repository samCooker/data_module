/*
 * FileName:    Policy.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月25日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.commoninfo.domain;

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
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "policy_id")) })
public class Policy extends LongIdEntity {
    /** 原系统的法规编号 */
    private Long   rmPolicyId;
    /** 法规名称 */
    private String policyName;
    /** 法规类型 */
    private String policyType;
    /** 颁发单位 */
    private String releaseUnit;
    /** 颁发日期 */
    private Date   releaseDate;
    /** 实施日期 */
    private Date   effectDate;
    /** 关键字 */
    private String keyword;
    /** 数据导入日期 */
    private Date   inputDate;

    /**
     * @return the policyName
     */
    public String getPolicyName() {
        return policyName;
    }

    /**
     * @param policyName
     *            the policyName to set
     */
    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    /**
     * @return the policyType
     */
    public String getPolicyType() {
        return policyType;
    }

    /**
     * @param policyType
     *            the policyType to set
     */
    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    /**
     * @return the releaseUnit
     */
    public String getReleaseUnit() {
        return releaseUnit;
    }

    /**
     * @param releaseUnit
     *            the releaseUnit to set
     */
    public void setReleaseUnit(String releaseUnit) {
        this.releaseUnit = releaseUnit;
    }

    /**
     * @return the keyword
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * @param keyword
     *            the keyword to set
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * @return the inputDate
     */
    public Date getInputDate() {
        return inputDate;
    }

    /**
     * @param inputDate
     *            the inputDate to set
     */
    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    /**
     * @return the rmPolicyId
     */
    public Long getRmPolicyId() {
        return rmPolicyId;
    }

    /**
     * @param rmPolicyId
     *            the rmPolicyId to set
     */
    public void setRmPolicyId(Long rmPolicyId) {
        this.rmPolicyId = rmPolicyId;
    }

    /**
     * @return the releaseDate
     */
    public Date getReleaseDate() {
        return releaseDate;
    }

    /**
     * @param releaseDate
     *            the releaseDate to set
     */
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * @return the effectDate
     */
    public Date getEffectDate() {
        return effectDate;
    }

    /**
     * @param effectDate
     *            the effectDate to set
     */
    public void setEffectDate(Date effectDate) {
        this.effectDate = effectDate;
    }

}
