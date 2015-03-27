package cn.com.chaochuang.common.user.domain;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import cn.com.chaochuang.common.data.domain.PersistEntity;
import cn.com.chaochuang.common.lookup.annotation.LookUp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@LookUp
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "dep_id")),
                @AttributeOverride(name = "valid", column = @Column(name = "valid")) })
public class SysDepartment extends PersistEntity {
    private static final long  serialVersionUID = -8098711992771334960L;

    /** 部门名称 */
    private String             depName;

    /** 部门编码 */
    private String             depCode;

    /** 父部门编号 */
    private Long               parentDep;

    /** 祖先部门编号 */
    private Long               ancestorDep;

    /** 排序号 */
    private Long               orderNum;

    /** 联系电话 */
    private String             telephone;

    /** 备注 */
    private String             remark;

    /** 发布类型 */
    private String             unitFlag;

    /** 部门别名 */
    private String             depAlias;

    /** ws认证标志 */
    private String             wsAuthoriFlag;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "parentDep")
    private Set<SysDepartment> subDepartment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentDep", insertable = false, updatable = false)
    private SysDepartment      parentDepartment;

    /**
     * @return the leaf
     */
    public boolean isLeaf() {
        if (null != subDepartment && subDepartment.size() > 0) {
            return false;
        } else {
            return true;
        }
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public Long getParentDep() {
        return parentDep;
    }

    public void setParentDep(Long parentDep) {
        this.parentDep = parentDep;
    }

    public Long getAncestorDep() {
        return ancestorDep;
    }

    public void setAncestorDep(Long ancestorDep) {
        this.ancestorDep = ancestorDep;
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Set<SysDepartment> getSubDepartment() {
        return subDepartment;
    }

    public void setSubDepartment(Set<SysDepartment> subDepartment) {
        this.subDepartment = subDepartment;
    }

    public String getUnitFlag() {
        return unitFlag;
    }

    public void setUnitFlag(String unitFlag) {
        this.unitFlag = unitFlag;
    }

    public String getDepAlias() {
        return depAlias;
    }

    public void setDepAlias(String depAlias) {
        this.depAlias = depAlias;
    }

    public String getWsAuthoriFlag() {
        return wsAuthoriFlag;
    }

    public void setWsAuthoriFlag(String wsAuthoriFlag) {
        this.wsAuthoriFlag = wsAuthoriFlag;
    }

    public SysDepartment getParentDepartment() {
        return parentDepartment;
    }

    public void setParentDepartment(SysDepartment parentDepartment) {
        this.parentDepartment = parentDepartment;
    }

}
