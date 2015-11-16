/*
 * FileName:    AppLicence.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年11月16日 (LLM) 1.0 Create
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
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "licence_id")) })
public class AppLicence extends LongIdEntity {
    /** 原系统许可流水号 */
    private Long   rmLicenceId;
    /** 原系统企业编号 */
    private Long   rmEntpId;
    /** 许可证号 */
    private String licenceNo;
    /** 有效期（开始） */
    private Date   fromDate;
    /** 有效期（结束） */
    private Date   toDate;
    /** 发证日期 */
    private Date   licenceTime;
    /** 许可类型名称 */
    private String entpTypeName;

    /**
     * @return the rmLicenceId
     */
    public Long getRmLicenceId() {
        return rmLicenceId;
    }

    /**
     * @param rmLicenceId
     *            the rmLicenceId to set
     */
    public void setRmLicenceId(Long rmLicenceId) {
        this.rmLicenceId = rmLicenceId;
    }

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
     * @return the licenceNo
     */
    public String getLicenceNo() {
        return licenceNo;
    }

    /**
     * @param licenceNo
     *            the licenceNo to set
     */
    public void setLicenceNo(String licenceNo) {
        this.licenceNo = licenceNo;
    }

    /**
     * @return the fromDate
     */
    public Date getFromDate() {
        return fromDate;
    }

    /**
     * @param fromDate
     *            the fromDate to set
     */
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * @return the toDate
     */
    public Date getToDate() {
        return toDate;
    }

    /**
     * @param toDate
     *            the toDate to set
     */
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    /**
     * @return the licenceTime
     */
    public Date getLicenceTime() {
        return licenceTime;
    }

    /**
     * @param licenceTime
     *            the licenceTime to set
     */
    public void setLicenceTime(Date licenceTime) {
        this.licenceTime = licenceTime;
    }

    /**
     * @return the entpTypeName
     */
    public String getEntpTypeName() {
        return entpTypeName;
    }

    /**
     * @param entpTypeName
     *            the entpTypeName to set
     */
    public void setEntpTypeName(String entpTypeName) {
        this.entpTypeName = entpTypeName;
    }

}
