/*
 * FileName:    AppTransactPersonal.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年7月1日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.appflow.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;

import cn.com.chaochuang.common.data.domain.LongIdEntity;

/**
 * @author Shicx
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "transact_personal_id")) })
public class AppTransactPersonal extends LongIdEntity {

    /**
     *
     */
    private static final long serialVersionUID = -8453578016801449236L;

    /** 审批事项编号 */
    // @OneToOne
    // @JoinColumn(name = "rmItemApplyId", referencedColumnName = "rmItemApplyId")
    // private AppItemApply itemApply;
    private Long              rmItemApplyId;
    /** 经办人 */
    private Long              transactId;
    /** 经办时间 */
    private Date              transactTime;
    /** 经办人所属单位 */
    private Long              unitOrgId;
    /** 经办人所属部门 */
    private Long              transactDeptId;

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
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * @return the rmItemApplyId
     */
    public Long getRmItemApplyId() {
        return rmItemApplyId;
    }

    /**
     * @param rmItemApplyId
     *            the rmItemApplyId to set
     */
    public void setRmItemApplyId(Long rmItemApplyId) {
        this.rmItemApplyId = rmItemApplyId;
    }

}
