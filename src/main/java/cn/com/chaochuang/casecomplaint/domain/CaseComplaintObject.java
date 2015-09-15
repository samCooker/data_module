/*
 * FileName:    SysDataChange.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年09月10日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.casecomplaint.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;

import cn.com.chaochuang.common.data.domain.LongIdEntity;

/**
 * @author Shicx
 *
 */
@SuppressWarnings("serial")
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "COMPLAINT_OBJECT_ID")) })
public class CaseComplaintObject extends LongIdEntity {
    /** 远程投诉举报单id */
    private Long   rmComplaintId;
    /** 远程投诉举报单id */
    private Long   rmComplaintObjectId;
    /** 是否在黑名单 */
    private String blacklistFlag;
    /** 受理单位id */
    private Long   unitId;
    /** 举报人姓名 */
    private String userName;
    /** 举报人类型 */
    private String userType;
    /** 证件类型 */
    private String cerType;
    /** 证件号 */
    private String cerNum;
    /** 举报人电话 */
    private String userTelphone;
    /** 举报人邮箱 */
    private String email;
    /** 邮编 */
    private String zipCode;
    /** 举报人住址或工作单位 */
    private String userAddr;
    /** 来电电话 */
    private String comeTelphone;
    /** 其他回复方式 */
    private String otherBack;
    /** 是否匿名 */
    private String nameFlag;

    public void setBlacklistFlag(String blacklistFlag) {
        this.blacklistFlag = blacklistFlag;
    }

    public String getBlacklistFlag() {
        return blacklistFlag;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setRmComplaintId(Long rmComplaintId) {
        this.rmComplaintId = rmComplaintId;
    }

    public Long getRmComplaintId() {
        return rmComplaintId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }

    public void setCerType(String cerType) {
        this.cerType = cerType;
    }

    public String getCerType() {
        return cerType;
    }

    public void setCerNum(String cerNum) {
        this.cerNum = cerNum;
    }

    public String getCerNum() {
        return cerNum;
    }

    public void setUserTelphone(String userTelphone) {
        this.userTelphone = userTelphone;
    }

    public String getUserTelphone() {
        return userTelphone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr;
    }

    public String getUserAddr() {
        return userAddr;
    }

    public void setComeTelphone(String comeTelphone) {
        this.comeTelphone = comeTelphone;
    }

    public String getComeTelphone() {
        return comeTelphone;
    }

    public void setOtherBack(String otherBack) {
        this.otherBack = otherBack;
    }

    public String getOtherBack() {
        return otherBack;
    }

    public void setNameFlag(String nameFlag) {
        this.nameFlag = nameFlag;
    }

    public String getNameFlag() {
        return nameFlag;
    }

    /**
     * @return the rmComplaintObjectId
     */
    public Long getRmComplaintObjectId() {
        return rmComplaintObjectId;
    }

    /**
     * @param rmComplaintObjectId
     *            the rmComplaintObjectId to set
     */
    public void setRmComplaintObjectId(Long rmComplaintObjectId) {
        this.rmComplaintObjectId = rmComplaintObjectId;
    }

}
