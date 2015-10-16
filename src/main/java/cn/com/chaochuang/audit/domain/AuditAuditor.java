/*
 * FileName:    AuditAuditor.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年10月16日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.audit.domain;

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
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "auditor_id")) })
public class AuditAuditor extends LongIdEntity {
    /** 流水号 */
    private Long   rmAuditorId;
    /** 出生年月 */
    private Date   birthday;
    /** 姓名 */
    private String auditorName;
    /** 性别 */
    private String gender;
    /** 所属区域 */
    private String areaName;
    /** 民族 */
    private String nationName;
    /** 政治面貌 */
    private String politicalStatus;
    /** 从事工作 */
    private String auditorWork;
    /** 工作单位 */
    private String workUnit;
    /** 学历 */
    private String diploma;
    /** 职称 */
    private String profession;
    /** 手机 */
    private String auditorPhone;
    /** 行政职务 */
    private String adminPost;
    /** 工作单位地址 */
    private String workUnitAddr;
    /** 电子邮箱 */
    private String auditorEmail;
    /** 单位是否为检验所 */
    private String workUnitFlag;
    /** 邮编 */
    private String postcode;
    /** 联系电话 */
    private String auditorTel;
    /** 传真 */
    private String auditorFax;
    /** 是否有效 */
    private String validFlag;
    /** 身份证 */
    private String idCard;
    /** 专业及毕业院校 */
    private String subject;

    /**
     * @return the rmAuditorId
     */
    public Long getRmAuditorId() {
        return rmAuditorId;
    }

    /**
     * @param rmAuditorId
     *            the rmAuditorId to set
     */
    public void setRmAuditorId(Long rmAuditorId) {
        this.rmAuditorId = rmAuditorId;
    }

    /**
     * @return the birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     *            the birthday to set
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the auditorName
     */
    public String getAuditorName() {
        return auditorName;
    }

    /**
     * @param auditorName
     *            the auditorName to set
     */
    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender
     *            the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the areaName
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * @param areaName
     *            the areaName to set
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    /**
     * @return the nationName
     */
    public String getNationName() {
        return nationName;
    }

    /**
     * @param nationName
     *            the nationName to set
     */
    public void setNationName(String nationName) {
        this.nationName = nationName;
    }

    /**
     * @return the politicalStatus
     */
    public String getPoliticalStatus() {
        return politicalStatus;
    }

    /**
     * @param politicalStatus
     *            the politicalStatus to set
     */
    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    /**
     * @return the auditorWork
     */
    public String getAuditorWork() {
        return auditorWork;
    }

    /**
     * @param auditorWork
     *            the auditorWork to set
     */
    public void setAuditorWork(String auditorWork) {
        this.auditorWork = auditorWork;
    }

    /**
     * @return the workUnit
     */
    public String getWorkUnit() {
        return workUnit;
    }

    /**
     * @param workUnit
     *            the workUnit to set
     */
    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit;
    }

    /**
     * @return the diploma
     */
    public String getDiploma() {
        return diploma;
    }

    /**
     * @param diploma
     *            the diploma to set
     */
    public void setDiploma(String diploma) {
        this.diploma = diploma;
    }

    /**
     * @return the profession
     */
    public String getProfession() {
        return profession;
    }

    /**
     * @param profession
     *            the profession to set
     */
    public void setProfession(String profession) {
        this.profession = profession;
    }

    /**
     * @return the auditorPhone
     */
    public String getAuditorPhone() {
        return auditorPhone;
    }

    /**
     * @param auditorPhone
     *            the auditorPhone to set
     */
    public void setAuditorPhone(String auditorPhone) {
        this.auditorPhone = auditorPhone;
    }

    /**
     * @return the adminPost
     */
    public String getAdminPost() {
        return adminPost;
    }

    /**
     * @param adminPost
     *            the adminPost to set
     */
    public void setAdminPost(String adminPost) {
        this.adminPost = adminPost;
    }

    /**
     * @return the workUnitAddr
     */
    public String getWorkUnitAddr() {
        return workUnitAddr;
    }

    /**
     * @param workUnitAddr
     *            the workUnitAddr to set
     */
    public void setWorkUnitAddr(String workUnitAddr) {
        this.workUnitAddr = workUnitAddr;
    }

    /**
     * @return the auditorEmail
     */
    public String getAuditorEmail() {
        return auditorEmail;
    }

    /**
     * @param auditorEmail
     *            the auditorEmail to set
     */
    public void setAuditorEmail(String auditorEmail) {
        this.auditorEmail = auditorEmail;
    }

    /**
     * @return the workUnitFlag
     */
    public String getWorkUnitFlag() {
        return workUnitFlag;
    }

    /**
     * @param workUnitFlag
     *            the workUnitFlag to set
     */
    public void setWorkUnitFlag(String workUnitFlag) {
        this.workUnitFlag = workUnitFlag;
    }

    /**
     * @return the postcode
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * @param postcode
     *            the postcode to set
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * @return the auditorTel
     */
    public String getAuditorTel() {
        return auditorTel;
    }

    /**
     * @param auditorTel
     *            the auditorTel to set
     */
    public void setAuditorTel(String auditorTel) {
        this.auditorTel = auditorTel;
    }

    /**
     * @return the auditorFax
     */
    public String getAuditorFax() {
        return auditorFax;
    }

    /**
     * @param auditorFax
     *            the auditorFax to set
     */
    public void setAuditorFax(String auditorFax) {
        this.auditorFax = auditorFax;
    }

    /**
     * @return the validFlag
     */
    public String getValidFlag() {
        return validFlag;
    }

    /**
     * @param validFlag
     *            the validFlag to set
     */
    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    /**
     * @return the idCard
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * @param idCard
     *            the idCard to set
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject
     *            the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

}
