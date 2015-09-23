/*
 * FileName:    SysDataChange.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年09月23日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.casecomplaint.domain;

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
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "TRANSACT_PERSONAL_ID")) })
public class CaseTransactPersonal extends LongIdEntity {
    /** 标题 */
    private String title;
    /** 经办人id */
    private Long   transactId;
    /** 经办时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date   transactTime;
    /** 经办单位id */
    private Long   unitOrgId;
    /** 经办部门id */
    private Long   transactDeptId;
    /** 投诉举报id */
    private Long   rmComplaintId;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTransactId(Long transactId) {
        this.transactId = transactId;
    }

    public Long getTransactId() {
        return transactId;
    }

    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
    }

    public Date getTransactTime() {
        return transactTime;
    }

    public void setUnitOrgId(Long unitOrgId) {
        this.unitOrgId = unitOrgId;
    }

    public Long getUnitOrgId() {
        return unitOrgId;
    }

    public void setTransactDeptId(Long transactDeptId) {
        this.transactDeptId = transactDeptId;
    }

    public Long getTransactDeptId() {
        return transactDeptId;
    }

    public void setRmComplaintId(Long rmComplaintId) {
        this.rmComplaintId = rmComplaintId;
    }

    public Long getRmComplaintId() {
        return rmComplaintId;
    }

}
