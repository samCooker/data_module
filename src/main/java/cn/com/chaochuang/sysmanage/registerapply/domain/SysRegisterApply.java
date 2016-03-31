/*
 * FileName:    SysRegisterApply.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年6月29日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.sysmanage.registerapply.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.com.chaochuang.common.data.domain.LongIdEntity;
import cn.com.chaochuang.common.user.domain.SysUser;
import cn.com.chaochuang.sysmanage.registerapply.reference.AppAuthStatus;
import cn.com.chaochuang.sysmanage.registerapply.reference.AppAuthStatusConverter;

/**
 * @author Shicx
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "sys_register_apply_id")) })
public class SysRegisterApply extends LongIdEntity {

    /**
     *
     */
    private static final long serialVersionUID = -7326925437743879415L;
    /** 用户id */
    @ManyToOne
    @JoinColumn(name = "userId")
    private SysUser           registerUser;
    /** 申请时间 */
    private Date              applyTime;
    /** 手机IMEI码 */
    private String            imeiCode;
    /** 申请状态 */
    @Convert(converter = AppAuthStatusConverter.class)
    private AppAuthStatus     status;
    /** 短信验证码 */
    private String            smsCode;
    /** 短信验证码发送时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date              smsCodeSendTime;
    /** 移动设备类型 */
    private String            deviceType;
    /** 备注 */
    private String            memo;

    /**
     * @return the smsCode
     */
    public String getSmsCode() {
        return smsCode;
    }

    /**
     * @param smsCode
     *            the smsCode to set
     */
    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    /**
     * @return the smsCodeSendTime
     */
    public Date getSmsCodeSendTime() {
        return smsCodeSendTime;
    }

    /**
     * @param smsCodeSendTime
     *            the smsCodeSendTime to set
     */
    public void setSmsCodeSendTime(Date smsCodeSendTime) {
        this.smsCodeSendTime = smsCodeSendTime;
    }

    /**
     * @return the deviceType
     */
    public String getDeviceType() {
        return deviceType;
    }

    /**
     * @param deviceType
     *            the deviceType to set
     */
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * @return the memo
     */
    public String getMemo() {
        return memo;
    }

    /**
     * @param memo
     *            the memo to set
     */
    public void setMemo(String memo) {
        this.memo = memo;
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
     * @return the registerUser
     */
    public SysUser getRegisterUser() {
        return registerUser;
    }

    /**
     * @param registerUser
     *            the registerUser to set
     */
    public void setRegisterUser(SysUser registerUser) {
        this.registerUser = registerUser;
    }

}
