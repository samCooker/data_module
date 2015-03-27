/*
 * FileName:    PolicyType.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月25日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.commoninfo.domain;

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
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "policy_type_id")) })
public class PolicyType extends LongIdEntity {
    /** 法规类型值 */
    private String policyTypeValue;
    /** 法规类型名称 */
    private String policyTypeName;

    /**
     * @return the policyTypeValue
     */
    public String getPolicyTypeValue() {
        return policyTypeValue;
    }

    /**
     * @param policyTypeValue
     *            the policyTypeValue to set
     */
    public void setPolicyTypeValue(String policyTypeValue) {
        this.policyTypeValue = policyTypeValue;
    }

    /**
     * @return the policyTypeName
     */
    public String getPolicyTypeName() {
        return policyTypeName;
    }

    /**
     * @param policyTypeName
     *            the policyTypeName to set
     */
    public void setPolicyTypeName(String policyTypeName) {
        this.policyTypeName = policyTypeName;
    }

}
