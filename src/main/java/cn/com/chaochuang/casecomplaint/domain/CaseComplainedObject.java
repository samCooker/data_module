/*
 * FileName:    SysDataChange.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年09月10日 (Shicx) 1.0 Create
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
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "COMPLAINED_OBJECT_ID")) })
public class CaseComplainedObject extends LongIdEntity {
    /** 远程投诉ID */
    private Long   rmComplaintId;
    /** 远程被投诉对象id */
    private Long   rmComplainedObjectId;
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
    @Temporal(TemporalType.TIMESTAMP)
    private Date   prodTime;
    /** 被投诉举报产品生产厂家 */
    private String prodEntp;
    /** 产品销售企业 */
    private String prodSellEntp;
    /** 被投诉举报产品规格 */
    private String prodSpec;
    /** 被投诉举报产品有效期 */
    private String prodExpTime;
    /** 受理单位ID */
    private Long   unitId;

    public void setComplainedName(String complainedName) {
        this.complainedName = complainedName;
    }

    public String getComplainedName() {
        return complainedName;
    }

    public void setComplainedTelphone(String complainedTelphone) {
        this.complainedTelphone = complainedTelphone;
    }

    public String getComplainedTelphone() {
        return complainedTelphone;
    }

    public void setComplainedUnitAddr(String complainedUnitAddr) {
        this.complainedUnitAddr = complainedUnitAddr;
    }

    public String getComplainedUnitAddr() {
        return complainedUnitAddr;
    }

    public void setComplainedUnitMan(String complainedUnitMan) {
        this.complainedUnitMan = complainedUnitMan;
    }

    public String getComplainedUnitMan() {
        return complainedUnitMan;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdLicence(String prodLicence) {
        this.prodLicence = prodLicence;
    }

    public String getProdLicence() {
        return prodLicence;
    }

    public void setProdBatchNo(String prodBatchNo) {
        this.prodBatchNo = prodBatchNo;
    }

    public String getProdBatchNo() {
        return prodBatchNo;
    }

    public void setProdTime(Date prodTime) {
        this.prodTime = prodTime;
    }

    public Date getProdTime() {
        return prodTime;
    }

    public void setProdEntp(String prodEntp) {
        this.prodEntp = prodEntp;
    }

    public String getProdEntp() {
        return prodEntp;
    }

    public void setProdSellEntp(String prodSellEntp) {
        this.prodSellEntp = prodSellEntp;
    }

    public String getProdSellEntp() {
        return prodSellEntp;
    }

    public void setProdSpec(String prodSpec) {
        this.prodSpec = prodSpec;
    }

    public String getProdSpec() {
        return prodSpec;
    }

    public void setRmComplaintId(Long rmComplaintId) {
        this.rmComplaintId = rmComplaintId;
    }

    public Long getRmComplaintId() {
        return rmComplaintId;
    }

    public void setProdExpTime(String prodExpTime) {
        this.prodExpTime = prodExpTime;
    }

    public String getProdExpTime() {
        return prodExpTime;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public Long getUnitId() {
        return unitId;
    }

    /**
     * @return the rmComplainedObjectId
     */
    public Long getRmComplainedObjectId() {
        return rmComplainedObjectId;
    }

    /**
     * @param rmComplainedObjectId
     *            the rmComplainedObjectId to set
     */
    public void setRmComplainedObjectId(Long rmComplainedObjectId) {
        this.rmComplainedObjectId = rmComplainedObjectId;
    }

}
