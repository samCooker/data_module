package cn.com.chaochuang.common.user.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import cn.com.chaochuang.common.data.domain.PersistEntity;
import cn.com.chaochuang.common.lookup.annotation.LookUp;
import cn.com.chaochuang.common.user.reference.Sex;
import cn.com.chaochuang.common.user.reference.SexConverter;

@Entity
@LookUp
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "user_id")),
                @AttributeOverride(name = "valid", column = @Column(name = "valid")) })
public class SysUser extends PersistEntity {

    private static final long serialVersionUID = -4615274498193533591L;

    /** 所属部门 */
    @ManyToOne
    @JoinColumn(name = "dep_id")
    private SysDepartment     department;
    /** 姓名 */
    private String            userName;
    /** 性别 */
    @Convert(converter = SexConverter.class)
    private Sex               sex;
    /** 登录账号 */
    private String            account;
    /** 登录密码 */
    private String            password;
    /** 职务名称 */
    private String            dutyName;
    /** 移动电话 */
    private String            mobile;
    /** 消息推送注册号 */
    private String            registrationId;
    /** 原系统状态标识 */
    private String            delFlag;

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
    public Sex getSex() {
        return sex;
    }

    /**
     * @param sex
     *            the sex to set
     */
    public void setSex(Sex sex) {
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

}
