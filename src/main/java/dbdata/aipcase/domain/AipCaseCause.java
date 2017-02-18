/*
 * FileName:    CauseOfAction.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年1月13日 (frt) 1.0 Create
 */

package dbdata.aipcase.domain;



import cn.com.chaochuang.common.data.domain.LongIdEntity;

import javax.persistence.*;

/**
 * @author frt
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "case_cause_id")),
                @AttributeOverride(name = "valid", column = @Column(name = "valid")) })
public class AipCaseCause extends LongIdEntity {
    private static final long serialVersionUID = 4368569898874697989L;
    /** 所属分类 */
    private String     category;
    /** 案由名称 */
    private String caseCauseName;
    /** 适用范围 */
    private String scope;
    /** 违反条款 */
    private String violation;
    /** 处罚依据 */
    private String punishBasis;
    /** 处罚内容 */
    private String punishContent;
    /** 案由类别 */
    @OneToOne
    @JoinColumn(name = "caseCauseTypeId")
    private AipCaseCauseType  aipCaseCauseType;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the caseCauseName
     */
    public String getCaseCauseName() {
        return caseCauseName;
    }

    /**
     * @param caseCauseName
     *            the caseCauseName to set
     */
    public void setCaseCauseName(String caseCauseName) {
        this.caseCauseName = caseCauseName;
    }

    /**
     * @return the scope
     */
    public String getScope() {
        return scope;
    }

    /**
     * @param scope
     *            the scope to set
     */
    public void setScope(String scope) {
        this.scope = scope;
    }

    /**
     * @return the violation
     */
    public String getViolation() {
        return violation;
    }

    /**
     * @param violation
     *            the violation to set
     */
    public void setViolation(String violation) {
        this.violation = violation;
    }

    /**
     * @return the punishBasis
     */
    public String getPunishBasis() {
        return punishBasis;
    }

    /**
     * @param punishBasis
     *            the punishBasis to set
     */
    public void setPunishBasis(String punishBasis) {
        this.punishBasis = punishBasis;
    }

    /**
     * @return the punishContent
     */
    public String getPunishContent() {
        return punishContent;
    }

    /**
     * @param punishContent
     *            the punishContent to set
     */
    public void setPunishContent(String punishContent) {
        this.punishContent = punishContent;
    }

    /**
     * @return the aipCaseCauseType
     */
    public AipCaseCauseType getAipCaseCauseType() {
        return aipCaseCauseType;
    }

    /**
     * @param aipCaseCauseType
     *            the aipCaseCauseType to set
     */
    public void setAipCaseCauseType(AipCaseCauseType aipCaseCauseType) {
        this.aipCaseCauseType = aipCaseCauseType;
    }

}
