/*
 * FileName:    ExamineEntpObject.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年7月13日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.examine.domain;

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
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "entp_object_id")) })
public class ExamineEntpObject extends LongIdEntity {
    /** 检查计划名称 */
    private String proName;
    /** 检查状态 */
    private String fillState;
    /** 情况说明 */
    private String examineRemark;
    /** 企业名称 */
    private String fillEntpName;
    /** 营业执照 */
    private String fillEntpBusiLicense;
    /** 联系电话 */
    private String fillEntpPhone;
    /** 企业地址 */
    private String fillEntpAddr;
    /** 联系人 */
    private String fillEntpContact;
    /** 企业传真 */
    private String fillEntpFax;
    /** 企业邮编 */
    private String fillEntpPostalCode;
    /** 负责人 */
    private String fillEntpCorpname;
    /** 负责人身份证 */
    private String fillEntpCorpnameIdcard;
    /** 记录创建时间 */
    private Date   createTime;
    /** 检查时间开始 */
    private Date   fillTime;
    /** 检查时间结束 */
    private Date   fillTimeEnd;
    /** 执法部门编号 */
    private Long   examineDepId;
    /** 执法单位编号 */
    private Long   examineUnitId;
    /** 执法单位名称 */
    private String examineUnitName;
    /** 任务承办单位编号 */
    private Long   handleUnitId;
    /** 任务承办单位名称 */
    private String handleUnitName;
    /** 原系统检查流水号 */
    private Long   rmEntpObjectId;
    /** 检查企业编号 */
    private Long   fillEntpId;

    /**
     * @return the proName
     */
    public String getProName() {
        return proName;
    }

    /**
     * @param proName
     *            the proName to set
     */
    public void setProName(String proName) {
        this.proName = proName;
    }

    /**
     * @return the fillState
     */
    public String getFillState() {
        return fillState;
    }

    /**
     * @param fillState
     *            the fillState to set
     */
    public void setFillState(String fillState) {
        this.fillState = fillState;
    }

    /**
     * @return the examineRemark
     */
    public String getExamineRemark() {
        return examineRemark;
    }

    /**
     * @param examineRemark
     *            the examineRemark to set
     */
    public void setExamineRemark(String examineRemark) {
        this.examineRemark = examineRemark;
    }

    /**
     * @return the fillEntpName
     */
    public String getFillEntpName() {
        return fillEntpName;
    }

    /**
     * @param fillEntpName
     *            the fillEntpName to set
     */
    public void setFillEntpName(String fillEntpName) {
        this.fillEntpName = fillEntpName;
    }

    /**
     * @return the fillEntpBusiLicense
     */
    public String getFillEntpBusiLicense() {
        return fillEntpBusiLicense;
    }

    /**
     * @param fillEntpBusiLicense
     *            the fillEntpBusiLicense to set
     */
    public void setFillEntpBusiLicense(String fillEntpBusiLicense) {
        this.fillEntpBusiLicense = fillEntpBusiLicense;
    }

    /**
     * @return the fillEntpPhone
     */
    public String getFillEntpPhone() {
        return fillEntpPhone;
    }

    /**
     * @param fillEntpPhone
     *            the fillEntpPhone to set
     */
    public void setFillEntpPhone(String fillEntpPhone) {
        this.fillEntpPhone = fillEntpPhone;
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
     * @return the fillEntpContact
     */
    public String getFillEntpContact() {
        return fillEntpContact;
    }

    /**
     * @param fillEntpContact
     *            the fillEntpContact to set
     */
    public void setFillEntpContact(String fillEntpContact) {
        this.fillEntpContact = fillEntpContact;
    }

    /**
     * @return the fillEntpFax
     */
    public String getFillEntpFax() {
        return fillEntpFax;
    }

    /**
     * @param fillEntpFax
     *            the fillEntpFax to set
     */
    public void setFillEntpFax(String fillEntpFax) {
        this.fillEntpFax = fillEntpFax;
    }

    /**
     * @return the fillEntpPostalCode
     */
    public String getFillEntpPostalCode() {
        return fillEntpPostalCode;
    }

    /**
     * @param fillEntpPostalCode
     *            the fillEntpPostalCode to set
     */
    public void setFillEntpPostalCode(String fillEntpPostalCode) {
        this.fillEntpPostalCode = fillEntpPostalCode;
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
     * @return the fillEntpCorpnameIdcard
     */
    public String getFillEntpCorpnameIdcard() {
        return fillEntpCorpnameIdcard;
    }

    /**
     * @param fillEntpCorpnameIdcard
     *            the fillEntpCorpnameIdcard to set
     */
    public void setFillEntpCorpnameIdcard(String fillEntpCorpnameIdcard) {
        this.fillEntpCorpnameIdcard = fillEntpCorpnameIdcard;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the fillTime
     */
    public Date getFillTime() {
        return fillTime;
    }

    /**
     * @param fillTime
     *            the fillTime to set
     */
    public void setFillTime(Date fillTime) {
        this.fillTime = fillTime;
    }

    /**
     * @return the fillTimeEnd
     */
    public Date getFillTimeEnd() {
        return fillTimeEnd;
    }

    /**
     * @param fillTimeEnd
     *            the fillTimeEnd to set
     */
    public void setFillTimeEnd(Date fillTimeEnd) {
        this.fillTimeEnd = fillTimeEnd;
    }

    /**
     * @return the examineDepId
     */
    public Long getExamineDepId() {
        return examineDepId;
    }

    /**
     * @param examineDepId
     *            the examineDepId to set
     */
    public void setExamineDepId(Long examineDepId) {
        this.examineDepId = examineDepId;
    }

    /**
     * @return the examineUnitId
     */
    public Long getExamineUnitId() {
        return examineUnitId;
    }

    /**
     * @param examineUnitId
     *            the examineUnitId to set
     */
    public void setExamineUnitId(Long examineUnitId) {
        this.examineUnitId = examineUnitId;
    }

    /**
     * @return the examineUnitName
     */
    public String getExamineUnitName() {
        return examineUnitName;
    }

    /**
     * @param examineUnitName
     *            the examineUnitName to set
     */
    public void setExamineUnitName(String examineUnitName) {
        this.examineUnitName = examineUnitName;
    }

    /**
     * @return the handleUnitId
     */
    public Long getHandleUnitId() {
        return handleUnitId;
    }

    /**
     * @param handleUnitId
     *            the handleUnitId to set
     */
    public void setHandleUnitId(Long handleUnitId) {
        this.handleUnitId = handleUnitId;
    }

    /**
     * @return the handleUnitName
     */
    public String getHandleUnitName() {
        return handleUnitName;
    }

    /**
     * @param handleUnitName
     *            the handleUnitName to set
     */
    public void setHandleUnitName(String handleUnitName) {
        this.handleUnitName = handleUnitName;
    }

    /**
     * @return the rmEntpObjectId
     */
    public Long getRmEntpObjectId() {
        return rmEntpObjectId;
    }

    /**
     * @param rmEntpObjectId
     *            the rmEntpObjectId to set
     */
    public void setRmEntpObjectId(Long rmEntpObjectId) {
        this.rmEntpObjectId = rmEntpObjectId;
    }

    /**
     * @return the fillEntpId
     */
    public Long getFillEntpId() {
        return fillEntpId;
    }

    /**
     * @param fillEntpId
     *            the fillEntpId to set
     */
    public void setFillEntpId(Long fillEntpId) {
        this.fillEntpId = fillEntpId;
    }

}
