/*
 * FileName:    AppPharmacist.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年5月29日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.appflow.domain;

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
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "pharm_id")) })
public class AppPharmacist extends LongIdEntity {
    /**  */
    private static final long serialVersionUID = 1340125287448670981L;

    /** 原系统执业药师编号 */
    private Long              rmPharmId;
    /** 药师姓名 */
    private String            pharmName;
    /** 性别：不祥；男；女 */
    private String            pharmGender;
    /** 民族 */
    private Long              pharmNation;
    /** 学历 */
    private String            pharmDiploma;
    /** 所学专业 */
    private String            pharmSubject;
    /** 技术职称 */
    private String            pharmProfession;
    /** 有效证件号码 */
    private String            pharmIdCard;
    /** 资格证书号码 */
    private String            pharmCertNo;
    /** 考试年份 */
    private String            pharmAffirmYear;
    /** 毕业学校 */
    private String            pharmGraduateSchool;
    /** 执行范围 ：生产；经营；使用 */
    private String            pharmWorkField;
    /** 执业类别 ：药学；中药学 */
    private String            pharmJobType;
    /** 执业单位名称 */
    private String            pharmEntpName;
    /** 执业单位地址 */
    private String            pharmEntpAddr;
    /** 联系手机 */
    private String            pharmMobile;
    /** 联系电话 */
    private String            pharmTel;
    /** 邮编 */
    private String            pharmPostalCode;
    /** 注册时间 */
    private Date              pharmLicenceTime;
    /** 此字段用于医疗注册证的变更/换证/注销/补办时填写, 填写变更/换证/注销/补办原因 */
    private String            contentReason;
    /** 1： 有效 2： 无效 3： 注销 4： 执业药师注册审批中 5： 执业药师注册审批不通过 */
    private String            pharmValid;

    /**
     * @return the rmPharmId
     */
    public Long getRmPharmId() {
        return rmPharmId;
    }

    /**
     * @param rmPharmId
     *            the rmPharmId to set
     */
    public void setRmPharmId(Long rmPharmId) {
        this.rmPharmId = rmPharmId;
    }

    /**
     * @return the pharmName
     */
    public String getPharmName() {
        return pharmName;
    }

    /**
     * @param pharmName
     *            the pharmName to set
     */
    public void setPharmName(String pharmName) {
        this.pharmName = pharmName;
    }

    /**
     * @return the pharmGender
     */
    public String getPharmGender() {
        return pharmGender;
    }

    /**
     * @param pharmGender
     *            the pharmGender to set
     */
    public void setPharmGender(String pharmGender) {
        this.pharmGender = pharmGender;
    }

    /**
     * @return the pharmNation
     */
    public Long getPharmNation() {
        return pharmNation;
    }

    /**
     * @param pharmNation
     *            the pharmNation to set
     */
    public void setPharmNation(Long pharmNation) {
        this.pharmNation = pharmNation;
    }

    /**
     * @return the pharmDiploma
     */
    public String getPharmDiploma() {
        return pharmDiploma;
    }

    /**
     * @param pharmDiploma
     *            the pharmDiploma to set
     */
    public void setPharmDiploma(String pharmDiploma) {
        this.pharmDiploma = pharmDiploma;
    }

    /**
     * @return the pharmSubject
     */
    public String getPharmSubject() {
        return pharmSubject;
    }

    /**
     * @param pharmSubject
     *            the pharmSubject to set
     */
    public void setPharmSubject(String pharmSubject) {
        this.pharmSubject = pharmSubject;
    }

    /**
     * @return the pharmProfession
     */
    public String getPharmProfession() {
        return pharmProfession;
    }

    /**
     * @param pharmProfession
     *            the pharmProfession to set
     */
    public void setPharmProfession(String pharmProfession) {
        this.pharmProfession = pharmProfession;
    }

    /**
     * @return the pharmIdCard
     */
    public String getPharmIdCard() {
        return pharmIdCard;
    }

    /**
     * @param pharmIdCard
     *            the pharmIdCard to set
     */
    public void setPharmIdCard(String pharmIdCard) {
        this.pharmIdCard = pharmIdCard;
    }

    /**
     * @return the pharmCertNo
     */
    public String getPharmCertNo() {
        return pharmCertNo;
    }

    /**
     * @param pharmCertNo
     *            the pharmCertNo to set
     */
    public void setPharmCertNo(String pharmCertNo) {
        this.pharmCertNo = pharmCertNo;
    }

    /**
     * @return the pharmAffirmYear
     */
    public String getPharmAffirmYear() {
        return pharmAffirmYear;
    }

    /**
     * @param pharmAffirmYear
     *            the pharmAffirmYear to set
     */
    public void setPharmAffirmYear(String pharmAffirmYear) {
        this.pharmAffirmYear = pharmAffirmYear;
    }

    /**
     * @return the pharmGraduateSchool
     */
    public String getPharmGraduateSchool() {
        return pharmGraduateSchool;
    }

    /**
     * @param pharmGraduateSchool
     *            the pharmGraduateSchool to set
     */
    public void setPharmGraduateSchool(String pharmGraduateSchool) {
        this.pharmGraduateSchool = pharmGraduateSchool;
    }

    /**
     * @return the pharmWorkField
     */
    public String getPharmWorkField() {
        return pharmWorkField;
    }

    /**
     * @param pharmWorkField
     *            the pharmWorkField to set
     */
    public void setPharmWorkField(String pharmWorkField) {
        this.pharmWorkField = pharmWorkField;
    }

    /**
     * @return the pharmJobType
     */
    public String getPharmJobType() {
        return pharmJobType;
    }

    /**
     * @param pharmJobType
     *            the pharmJobType to set
     */
    public void setPharmJobType(String pharmJobType) {
        this.pharmJobType = pharmJobType;
    }

    /**
     * @return the pharmEntpName
     */
    public String getPharmEntpName() {
        return pharmEntpName;
    }

    /**
     * @param pharmEntpName
     *            the pharmEntpName to set
     */
    public void setPharmEntpName(String pharmEntpName) {
        this.pharmEntpName = pharmEntpName;
    }

    /**
     * @return the pharmEntpAddr
     */
    public String getPharmEntpAddr() {
        return pharmEntpAddr;
    }

    /**
     * @param pharmEntpAddr
     *            the pharmEntpAddr to set
     */
    public void setPharmEntpAddr(String pharmEntpAddr) {
        this.pharmEntpAddr = pharmEntpAddr;
    }

    /**
     * @return the pharmMobile
     */
    public String getPharmMobile() {
        return pharmMobile;
    }

    /**
     * @param pharmMobile
     *            the pharmMobile to set
     */
    public void setPharmMobile(String pharmMobile) {
        this.pharmMobile = pharmMobile;
    }

    /**
     * @return the pharmTel
     */
    public String getPharmTel() {
        return pharmTel;
    }

    /**
     * @param pharmTel
     *            the pharmTel to set
     */
    public void setPharmTel(String pharmTel) {
        this.pharmTel = pharmTel;
    }

    /**
     * @return the pharmPostalCode
     */
    public String getPharmPostalCode() {
        return pharmPostalCode;
    }

    /**
     * @param pharmPostalCode
     *            the pharmPostalCode to set
     */
    public void setPharmPostalCode(String pharmPostalCode) {
        this.pharmPostalCode = pharmPostalCode;
    }

    /**
     * @return the pharmLicenceTime
     */
    public Date getPharmLicenceTime() {
        return pharmLicenceTime;
    }

    /**
     * @param pharmLicenceTime
     *            the pharmLicenceTime to set
     */
    public void setPharmLicenceTime(Date pharmLicenceTime) {
        this.pharmLicenceTime = pharmLicenceTime;
    }

    /**
     * @return the contentReason
     */
    public String getContentReason() {
        return contentReason;
    }

    /**
     * @param contentReason
     *            the contentReason to set
     */
    public void setContentReason(String contentReason) {
        this.contentReason = contentReason;
    }

    /**
     * @return the pharmValid
     */
    public String getPharmValid() {
        return pharmValid;
    }

    /**
     * @param pharmValid
     *            the pharmValid to set
     */
    public void setPharmValid(String pharmValid) {
        this.pharmValid = pharmValid;
    }

}
