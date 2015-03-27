package cn.com.chaochuang.common.user.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import cn.com.chaochuang.common.data.domain.PersistEntity;
import cn.com.chaochuang.common.lookup.annotation.LookUp;
import cn.com.chaochuang.common.syspower.domain.SysRole;
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

    /** CA key */
    private String            cakey;

    /** 职务id */
    private Long              dutyId;
    /** 职务名称 */
    private String            dutyName;
    /** 移动电话 */
    private String            mobile;

    /** email */
    private String            email;

    /** 注册日期 */
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date              regDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date              lastPasswdDate;

    /** 排序号 */
    private Long              orderNum;

    /** 备注 */
    private String            remark;

    /** 分管部门 */
    private String            mandepts;

    /** 通讯地址 */
    private String            address;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sys_user_role_rel", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "role_id") })
    private Set<SysRole>      roles;

    private String            certificateNo;

    /**
     * @return the certificateNo
     */
    public String getCertificateNo() {
        return certificateNo;
    }

    /**
     * @param certificateNo
     *            the certificateNo to set
     */
    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public Set<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<SysRole> roles) {
        this.roles = roles;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     *            the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public SysDepartment getDepartment() {
        return department;
    }

    public void setDepartment(SysDepartment department) {
        this.department = department;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCakey() {
        return cakey;
    }

    public void setCakey(String cakey) {
        this.cakey = cakey;
    }

    public Long getDutyId() {
        return dutyId;
    }

    public void setDutyId(Long dutyId) {
        this.dutyId = dutyId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMandepts() {
        return mandepts;
    }

    public void setMandepts(String mandepts) {
        this.mandepts = mandepts;
    }

    public Date getLastPasswdDate() {
        return lastPasswdDate;
    }

    public void setLastPasswdDate(Date lastPasswdDate) {
        this.lastPasswdDate = lastPasswdDate;
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

}
