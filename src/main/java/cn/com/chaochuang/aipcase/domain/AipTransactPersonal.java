/*
 * FileName:    AipTransactPersonal.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年8月20日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.aipcase.domain;

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
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "transact_personal_id")) })
public class AipTransactPersonal extends LongIdEntity {

    /** 远程案件来源id */
    private Long   rmCaseApplyId;
    /** 经办人id(远程用户id即rmUserId) */
    private Long   transactId;
    /** 经办时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date   transactTime;
    /** 经办人单位 */
    private Long   unitOrgId;
    /** 经办人部门 */
    private Long   transactDeptId;
    /** 案件标题 */
    private String title;

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
     * @return the transactId
     */
    public Long getTransactId() {
        return transactId;
    }

    /**
     * @param transactId
     *            the transactId to set
     */
    public void setTransactId(Long transactId) {
        this.transactId = transactId;
    }

    /**
     * @return the transactTime
     */
    public Date getTransactTime() {
        return transactTime;
    }

    /**
     * @param transactTime
     *            the transactTime to set
     */
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
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
     * @return the transactDeptId
     */
    public Long getTransactDeptId() {
        return transactDeptId;
    }

    /**
     * @param transactDeptId
     *            the transactDeptId to set
     */
    public void setTransactDeptId(Long transactDeptId) {
        this.transactDeptId = transactDeptId;
    }

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

}
