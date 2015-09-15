/*
 * FileName:    CaseComplaintObjInfo.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月13日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.casecomplaint.bean;

import java.util.Date;

/**
 * @author Shicx
 * 
 *         被投诉举报对象信息
 *
 */
public class ComplainedObjInfo {

    /** 远程被投诉对象id */
    private Long   complainedObjectId;
    /** 被投诉举报单位/个人名称 */
    private String complainedName;
    /** 被投诉举报单位/个人电话 */
    private String complainedTelphone;
    /** 被投诉举报单位/个人经营地址 */
    private String complainedUnitAddr;
    /** 被投诉举报单位负责人 */
    private String complainedUnitMan;
    /** 被投诉举报产品名称 */
    private String prodName;
    /** 被投诉举报产品批准文号 */
    private String prodLicence;
    /** 被投诉举报产品批号 */
    private String prodBatchNo;
    /** 产品生产日期 */
    private Date   prodTime;
    /** 被投诉举报产品生产厂家 */
    private String prodEntp;
    /** 产品销售企业 */
    private String prodSellEntp;
    /** 被投诉举报产品规格 */
    private String prodSpec;
    /** 远程投诉ID */
    private Long   complaintId;
    /** 被投诉举报产品有效期 */
    private String prodExpTime;
    /** 受理单位ID */
    private Long   unitId;

    /**
     * @return the complainedObjectId
     */
    public Long getComplainedObjectId() {
        return complainedObjectId;
    }

    /**
     * @param complainedObjectId
     *            the complainedObjectId to set
     */
    public void setComplainedObjectId(Long complainedObjectId) {
        this.complainedObjectId = complainedObjectId;
    }

    /**
     * @return the complainedName
     */
    public String getComplainedName() {
        return complainedName;
    }

    /**
     * @param complainedName
     *            the complainedName to set
     */
    public void setComplainedName(String complainedName) {
        this.complainedName = complainedName;
    }

    /**
     * @return the complainedTelphone
     */
    public String getComplainedTelphone() {
        return complainedTelphone;
    }

    /**
     * @param complainedTelphone
     *            the complainedTelphone to set
     */
    public void setComplainedTelphone(String complainedTelphone) {
        this.complainedTelphone = complainedTelphone;
    }

    /**
     * @return the complainedUnitAddr
     */
    public String getComplainedUnitAddr() {
        return complainedUnitAddr;
    }

    /**
     * @param complainedUnitAddr
     *            the complainedUnitAddr to set
     */
    public void setComplainedUnitAddr(String complainedUnitAddr) {
        this.complainedUnitAddr = complainedUnitAddr;
    }

    /**
     * @return the complainedUnitMan
     */
    public String getComplainedUnitMan() {
        return complainedUnitMan;
    }

    /**
     * @param complainedUnitMan
     *            the complainedUnitMan to set
     */
    public void setComplainedUnitMan(String complainedUnitMan) {
        this.complainedUnitMan = complainedUnitMan;
    }

    /**
     * @return the prodName
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * @param prodName
     *            the prodName to set
     */
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    /**
     * @return the prodLicence
     */
    public String getProdLicence() {
        return prodLicence;
    }

    /**
     * @param prodLicence
     *            the prodLicence to set
     */
    public void setProdLicence(String prodLicence) {
        this.prodLicence = prodLicence;
    }

    /**
     * @return the prodBatchNo
     */
    public String getProdBatchNo() {
        return prodBatchNo;
    }

    /**
     * @param prodBatchNo
     *            the prodBatchNo to set
     */
    public void setProdBatchNo(String prodBatchNo) {
        this.prodBatchNo = prodBatchNo;
    }

    /**
     * @return the prodTime
     */
    public Date getProdTime() {
        return prodTime;
    }

    /**
     * @param prodTime
     *            the prodTime to set
     */
    public void setProdTime(Date prodTime) {
        this.prodTime = prodTime;
    }

    /**
     * @return the prodEntp
     */
    public String getProdEntp() {
        return prodEntp;
    }

    /**
     * @param prodEntp
     *            the prodEntp to set
     */
    public void setProdEntp(String prodEntp) {
        this.prodEntp = prodEntp;
    }

    /**
     * @return the prodSellEntp
     */
    public String getProdSellEntp() {
        return prodSellEntp;
    }

    /**
     * @param prodSellEntp
     *            the prodSellEntp to set
     */
    public void setProdSellEntp(String prodSellEntp) {
        this.prodSellEntp = prodSellEntp;
    }

    /**
     * @return the prodSpec
     */
    public String getProdSpec() {
        return prodSpec;
    }

    /**
     * @param prodSpec
     *            the prodSpec to set
     */
    public void setProdSpec(String prodSpec) {
        this.prodSpec = prodSpec;
    }

    /**
     * @return the complaintId
     */
    public Long getComplaintId() {
        return complaintId;
    }

    /**
     * @param complaintId
     *            the complaintId to set
     */
    public void setComplaintId(Long complaintId) {
        this.complaintId = complaintId;
    }

    /**
     * @return the prodExpTime
     */
    public String getProdExpTime() {
        return prodExpTime;
    }

    /**
     * @param prodExpTime
     *            the prodExpTime to set
     */
    public void setProdExpTime(String prodExpTime) {
        this.prodExpTime = prodExpTime;
    }

    /**
     * @return the unitId
     */
    public Long getUnitId() {
        return unitId;
    }

    /**
     * @param unitId
     *            the unitId to set
     */
    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

}
