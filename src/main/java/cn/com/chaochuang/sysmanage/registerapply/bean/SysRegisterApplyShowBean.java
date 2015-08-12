/*
 * FileName:    SysRegisterApplyShowBean.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年6月29日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.sysmanage.registerapply.bean;

import java.util.Date;

import org.dozer.Mapping;

import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.sysmanage.registerapply.reference.AppAuthStatus;

/**
 * @author Shicx
 *
 */
public class SysRegisterApplyShowBean {

    /** 申请项id */
    private Long          id;
    /** 申请人姓名 */
    @Mapping("registerUser.userName")
    private String        userName;
    /** 申请人帐号 */
    @Mapping("registerUser.account")
    private String        account;
    /** 申请时间 */
    private Date          applyTime;
    /** 格式化申请时间 */
    private String        applyShowTime;
    /** 手机IMEI码 */
    private String        imeiCode;
    /** 申请状态 */
    private AppAuthStatus status;

    /**
     * @return the account
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account
     *            the account to set
     */
    public void setAccount(String account) {
        this.account = account;
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
     * @return the applyTime
     */
    public Date getApplyTime() {
        return applyTime;
    }

    /**
     * @param applyTime
     *            the applyTime to set
     */
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    /**
     * @return the applyShowTime
     */
    public String getApplyShowTime() {
        return applyTime != null ? Tools.DATE_FORMAT.format(applyTime) : "";
    }

    /**
     * @return the imeiCode
     */
    public String getImeiCode() {
        return imeiCode;
    }

    /**
     * @param imeiCode
     *            the imeiCode to set
     */
    public void setImeiCode(String imeiCode) {
        this.imeiCode = imeiCode;
    }

    /**
     * @return the status
     */
    public AppAuthStatus getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(AppAuthStatus status) {
        this.status = status;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

}
