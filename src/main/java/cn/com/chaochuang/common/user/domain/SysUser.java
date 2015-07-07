package cn.com.chaochuang.common.user.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import cn.com.chaochuang.common.data.domain.PersistEntity;
import cn.com.chaochuang.common.lookup.annotation.LookUp;
import cn.com.chaochuang.sysmanage.registerapply.reference.IsRegister;
import cn.com.chaochuang.sysmanage.registerapply.reference.IsRegisterConverter;

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
    /** 所属部门 */
    @ManyToOne
    @JoinColumn(name = "rmDepId", referencedColumnName = "rmDepId", insertable = false, updatable = false)
    private SysDepartment     department;
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
    /** 排序号 */
    private Long              orderNum;
    /** 是否注册app */
    @Convert(converter = IsRegisterConverter.class)
    private IsRegister        isRegister;
    /** 注册时间 */
    private Date              registerTime;
    /** 手机IMEI码 */
    private String            imeiCode;

    /**
     * @return the isRegister
     */
    public IsRegister getIsRegister() {
        return isRegister;
    }

    /**
     * @param isRegister
     *            the isRegister to set
     */
    public void setIsRegister(IsRegister isRegister) {
        this.isRegister = isRegister;
    }

    /**
     * @return the registerTime
     */
    public Date getRegisterTime() {
        return registerTime;
    }

    /**
     * @param registerTime
     *            the registerTime to set
     */
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
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
     * @return the department
     */
    public SysDepartment getDepartment() {
        return department;
    }

    /**
     * @param department
     *            the department to set
     */
    public void setDepartment(SysDepartment department) {
        this.department = department;
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

    /**
     * @return the orderNum
     */
    public Long getOrderNum() {
        return orderNum;
    }

    /**
     * @param orderNum
     *            the orderNum to set
     */
    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

}
