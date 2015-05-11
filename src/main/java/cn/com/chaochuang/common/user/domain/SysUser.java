package cn.com.chaochuang.common.user.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;

import cn.com.chaochuang.common.data.domain.PersistEntity;
import cn.com.chaochuang.common.lookup.annotation.LookUp;

@Entity
@LookUp
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "user_id")),
                @AttributeOverride(name = "valid", column = @Column(name = "valid")) })
public class SysUser extends PersistEntity {

    private static final long serialVersionUID = -4615274498193533591L;

    /** 原系统用户编号 */
    private Long              rmUserId;
    /** 原系统用户信息编号 */
    private Long              rmUserInfoId;
    /** 原系统部门编号 */
    private Long              rmDepId;
    /** 部门编号 */
    private Long              depId;
    /** 姓名 */
    private String            userName;
    /** 性别 */
    private String            sex;
    /** 登录账号 */
    private String            account;
    /** 登录密码 */
    private String            password;
    /** 职务名称 */
    private String            dutyName;
    /** 移动电话 */
    private String            mobile;
    /** 工作电话 */
    private String            workPhone;
    /** 消息推送注册号 */
    private String            registrationId;
    /** 原系统状态标识 */
    private String            delFlag;
    /** MD5校验码 */
    private String            mdfCode;

    /**
     * @return the workPhone
     */
    public String getWorkPhone() {
        return workPhone;
    }

    /**
     * @param workPhone
     *            the workPhone to set
     */
    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    /**
     * @return the depId
     */
    public Long getDepId() {
        return depId;
    }

    /**
     * @param depId
     *            the depId to set
     */
    public void setDepId(Long depId) {
        this.depId = depId;
    }

    /**
     * @return the rmUserId
     */
    public Long getRmUserId() {
        return rmUserId;
    }

    /**
     * @param rmUserId
     *            the rmUserId to set
     */
    public void setRmUserId(Long rmUserId) {
        this.rmUserId = rmUserId;
    }

    /**
     * @return the rmDepId
     */
    public Long getRmDepId() {
        return rmDepId;
    }

    /**
     * @param rmDepId
     *            the rmDepId to set
     */
    public void setRmDepId(Long rmDepId) {
        this.rmDepId = rmDepId;
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
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex
     *            the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

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
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the dutyName
     */
    public String getDutyName() {
        return dutyName;
    }

    /**
     * @param dutyName
     *            the dutyName to set
     */
    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     *            the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the registrationId
     */
    public String getRegistrationId() {
        return registrationId;
    }

    /**
     * @param registrationId
     *            the registrationId to set
     */
    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    /**
     * @return the delFlag
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * @param delFlag
     *            the delFlag to set
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * @return the mdfCode
     */
    public String getMdfCode() {
        return mdfCode;
    }

    /**
     * @param mdfCode
     *            the mdfCode to set
     */
    public void setMdfCode(String mdfCode) {
        this.mdfCode = mdfCode;
    }

    /**
     * @return the rmUserInfoId
     */
    public Long getRmUserInfoId() {
        return rmUserInfoId;
    }

    /**
     * @param rmUserInfoId
     *            the rmUserInfoId to set
     */
    public void setRmUserInfoId(Long rmUserInfoId) {
        this.rmUserInfoId = rmUserInfoId;
    }

}
