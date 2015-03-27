/*
 * FileName:    DepLinkman.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月19日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.commoninfo.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.com.chaochuang.common.data.domain.LongIdEntity;

/**
 * @author LLM 公共通讯录
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "linkman_id")) })
public class DepLinkman extends LongIdEntity {
    /** 通讯录成员项目 */
    private String staffName;
    /** 成员所在部门 */
    private String department;
    /** 成员单位 */
    private String unitName;
    /** 成员职务 */
    private String duty;
    /** 固定电话 */
    private String phone;
    /** 移动电话 */
    private String mobile;
    /** 电子邮件 */
    private String email;
    /** QQ */
    private String QQ;
    /** 邮编 */
    private String zip;
    /** 通讯录所属单位编号 */
    private Long   depId;
    /** 数据导入时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date   inputDate;

    /**
     * @return the staffName
     */
    public String getStaffName() {
        return staffName;
    }

    /**
     * @param staffName
     *            the staffName to set
     */
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    /**
     * @return the unitName
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * @param unitName
     *            the unitName to set
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    /**
     * @return the duty
     */
    public String getDuty() {
        return duty;
    }

    /**
     * @param duty
     *            the duty to set
     */
    public void setDuty(String duty) {
        this.duty = duty;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     *            the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department
     *            the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     *            the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the qQ
     */
    public String getQQ() {
        return QQ;
    }

    /**
     * @param qQ
     *            the qQ to set
     */
    public void setQQ(String qQ) {
        QQ = qQ;
    }

    /**
     * @return the depId
     */
    public Long getDepId() {
        return depId;
    }

    /**
     * @param depId
     *            the depId to set
     */
    public void setDepId(Long depId) {
        this.depId = depId;
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
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip
     *            the zip to set
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

}
