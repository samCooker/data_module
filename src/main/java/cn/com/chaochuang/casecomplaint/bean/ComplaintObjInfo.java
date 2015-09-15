/*
 * FileName:    CaseComplainedObjInfo.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月13日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.casecomplaint.bean;

/**
 * @author Shicx
 *
 *         投诉举报对象信息
 */
public class ComplaintObjInfo {

    /** 远程投诉举报单id */
    private Long   complaintId;
    /** 远程投诉举报对象id */
    private Long   complaintObjectId;
    /** 受理单位id */
    private Long   unitId;
    /** 举报人姓名 */
    private String userName;
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
    /** 是否匿名（对应字典：dicComplaintNameFlag） */
    private String nameFlag;
    /** 举报人类型（对应字典：dicComplainUserTypes） */
    private String userType;
    /** 证件类型 （对应字典：dicComplainCerTypes） */
    private String cerType;
    /** 是否在黑名单(对应字典：dicBlacklistFlag) */
    private String blacklistFlag;

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
     * @return the complaintObjectId
     */
    public Long getComplaintObjectId() {
        return complaintObjectId;
    }

    /**
     * @param complaintObjectId
     *            the complaintObjectId to set
     */
    public void setComplaintObjectId(Long complaintObjectId) {
        this.complaintObjectId = complaintObjectId;
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

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     *            the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the cerNum
     */
    public String getCerNum() {
        return cerNum;
    }

    /**
     * @param cerNum
     *            the cerNum to set
     */
    public void setCerNum(String cerNum) {
        this.cerNum = cerNum;
    }

    /**
     * @return the userTelphone
     */
    public String getUserTelphone() {
        return userTelphone;
    }

    /**
     * @param userTelphone
     *            the userTelphone to set
     */
    public void setUserTelphone(String userTelphone) {
        this.userTelphone = userTelphone;
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
     * @return the zipCode
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode
     *            the zipCode to set
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return the userAddr
     */
    public String getUserAddr() {
        return userAddr;
    }

    /**
     * @param userAddr
     *            the userAddr to set
     */
    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr;
    }

    /**
     * @return the comeTelphone
     */
    public String getComeTelphone() {
        return comeTelphone;
    }

    /**
     * @param comeTelphone
     *            the comeTelphone to set
     */
    public void setComeTelphone(String comeTelphone) {
        this.comeTelphone = comeTelphone;
    }

    /**
     * @return the otherBack
     */
    public String getOtherBack() {
        return otherBack;
    }

    /**
     * @param otherBack
     *            the otherBack to set
     */
    public void setOtherBack(String otherBack) {
        this.otherBack = otherBack;
    }

    /**
     * @return the nameFlag
     */
    public String getNameFlag() {
        return nameFlag;
    }

    /**
     * @param nameFlag
     *            the nameFlag to set
     */
    public void setNameFlag(String nameFlag) {
        this.nameFlag = nameFlag;
    }

    /**
     * @return the userType
     */
    public String getUserType() {
        return userType;
    }

    /**
     * @param userType
     *            the userType to set
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * @return the cerType
     */
    public String getCerType() {
        return cerType;
    }

    /**
     * @param cerType
     *            the cerType to set
     */
    public void setCerType(String cerType) {
        this.cerType = cerType;
    }

    /**
     * @return the blacklistFlag
     */
    public String getBlacklistFlag() {
        return blacklistFlag;
    }

    /**
     * @param blacklistFlag
     *            the blacklistFlag to set
     */
    public void setBlacklistFlag(String blacklistFlag) {
        this.blacklistFlag = blacklistFlag;
    }

}
