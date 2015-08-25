/*
 * FileName:    AppEntp.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年6月2日 (LLM) 1.0 Create
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
 * @author LLM
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "entp_id")) })
public class AppEntp extends LongIdEntity {
    /**
     *
     */
    private static final long serialVersionUID = 2200370368113192779L;
    /** 原系统企业编号 */
    private Long              rmEntpId;
    /** 注册资金 */
    private Long              registerFund;
    /** 经度 */
    private Double            longitude;
    /** 纬度 */
    private Double            latitude;
    /** 企业名称 */
    private String            entpName;
    /** 省份 */
    private String            entpProvince;
    /** 联系人 */
    private String            contact;
    /** 联系人职务 */
    private String            contactDuty;
    /** 联系人手机 */
    private String            cellPhone;
    /** 联系人地址 */
    private String            contactAddress;
    /** 联系人邮编 */
    private String            contactPostalCode;
    /** 联系人电话 */
    private String            tel;
    /** 注册地址 */
    private String            registerAddress;
    /** 营业执照 */
    private String            businessLicense;
    /** 城市 */
    private String            cityName;
    /** 管理单位 */
    private String            handleUnitName;
    /** 传真 */
    private String            fax;
    /** 电子邮件 */
    private String            email;
    /** 执照注册时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date              businessLicenseDate;
    /** 数据导入时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date              inputDate;
    /** 经营范围 */
    private String            busName;
    /** 许可证有效期描述 */
    private String            licenceDateScript;

    /**
     * @return the rmEntpId
     */
    public Long getRmEntpId() {
        return rmEntpId;
    }

    /**
     * @param rmEntpId
     *            the rmEntpId to set
     */
    public void setRmEntpId(Long rmEntpId) {
        this.rmEntpId = rmEntpId;
    }

    /**
     * @return the registerFund
     */
    public Long getRegisterFund() {
        return registerFund;
    }

    /**
     * @param registerFund
     *            the registerFund to set
     */
    public void setRegisterFund(Long registerFund) {
        this.registerFund = registerFund;
    }

    /**
     * @return the longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude
     *            the longitude to set
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude
     *            the latitude to set
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the entpName
     */
    public String getEntpName() {
        return entpName;
    }

    /**
     * @param entpName
     *            the entpName to set
     */
    public void setEntpName(String entpName) {
        this.entpName = entpName;
    }

    /**
     * @return the entpProvince
     */
    public String getEntpProvince() {
        return entpProvince;
    }

    /**
     * @param entpProvince
     *            the entpProvince to set
     */
    public void setEntpProvince(String entpProvince) {
        this.entpProvince = entpProvince;
    }

    /**
     * @return the contact
     */
    public String getContact() {
        return contact;
    }

    /**
     * @param contact
     *            the contact to set
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * @return the contactDuty
     */
    public String getContactDuty() {
        return contactDuty;
    }

    /**
     * @param contactDuty
     *            the contactDuty to set
     */
    public void setContactDuty(String contactDuty) {
        this.contactDuty = contactDuty;
    }

    /**
     * @return the cellPhone
     */
    public String getCellPhone() {
        return cellPhone;
    }

    /**
     * @param cellPhone
     *            the cellPhone to set
     */
    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    /**
     * @return the contactAddress
     */
    public String getContactAddress() {
        return contactAddress;
    }

    /**
     * @param contactAddress
     *            the contactAddress to set
     */
    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    /**
     * @return the contactPostalCode
     */
    public String getContactPostalCode() {
        return contactPostalCode;
    }

    /**
     * @param contactPostalCode
     *            the contactPostalCode to set
     */
    public void setContactPostalCode(String contactPostalCode) {
        this.contactPostalCode = contactPostalCode;
    }

    /**
     * @return the tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel
     *            the tel to set
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @return the registerAddress
     */
    public String getRegisterAddress() {
        return registerAddress;
    }

    /**
     * @param registerAddress
     *            the registerAddress to set
     */
    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
    }

    /**
     * @return the businessLicense
     */
    public String getBusinessLicense() {
        return businessLicense;
    }

    /**
     * @param businessLicense
     *            the businessLicense to set
     */
    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    /**
     * @return the cityName
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * @param cityName
     *            the cityName to set
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
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
     * @return the fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * @param fax
     *            the fax to set
     */
    public void setFax(String fax) {
        this.fax = fax;
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
     * @return the businessLicenseDate
     */
    public Date getBusinessLicenseDate() {
        return businessLicenseDate;
    }

    /**
     * @param businessLicenseDate
     *            the businessLicenseDate to set
     */
    public void setBusinessLicenseDate(Date businessLicenseDate) {
        this.businessLicenseDate = businessLicenseDate;
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
     * @return the busName
     */
    public String getBusName() {
        return busName;
    }

    /**
     * @param busName
     *            the busName to set
     */
    public void setBusName(String busName) {
        this.busName = busName;
    }

    /**
     * @return the licenceDateScript
     */
    public String getLicenceDateScript() {
        return licenceDateScript;
    }

    /**
     * @param licenceDateScript
     *            the licenceDateScript to set
     */
    public void setLicenceDateScript(String licenceDateScript) {
        this.licenceDateScript = licenceDateScript;
    }

}
