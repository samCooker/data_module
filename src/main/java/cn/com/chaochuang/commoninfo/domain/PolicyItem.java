/*
 * FileName:    PolicyItem.java
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
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "item_id")) })
public class PolicyItem extends LongIdEntity {
    /** 法规条文名称 */
    private String title;
    /** 关键字 */
    private String keyword;
    /** 法规条文内容 */
    private String content;
    /** 父法规条文编号 */
    private Long   parentItemId;
    /** 数据导入日期 */
    private Date   inputDate;
    /** 法规编号 */
    private Long   policyId;
    /** 原系统法规条文编号 */
    private Long   rmPolicyItemId;

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
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
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     *            the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the parentItemId
     */
    public Long getParentItemId() {
        return parentItemId;
    }

    /**
     * @param parentItemId
     *            the parentItemId to set
     */
    public void setParentItemId(Long parentItemId) {
        this.parentItemId = parentItemId;
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
     * @return the policyId
     */
    public Long getPolicyId() {
        return policyId;
    }

    /**
     * @param policyId
     *            the policyId to set
     */
    public void setPolicyId(Long policyId) {
        this.policyId = policyId;
    }

    /**
     * @return the rmPolicyItemId
     */
    public Long getRmPolicyItemId() {
        return rmPolicyItemId;
    }

    /**
     * @param rmPolicyItemId
     *            the rmPolicyItemId to set
     */
    public void setRmPolicyItemId(Long rmPolicyItemId) {
        this.rmPolicyItemId = rmPolicyItemId;
    }

}
