package dbdata.common.domain;

import cn.com.chaochuang.common.data.domain.PersistEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "dep_id")),
                @AttributeOverride(name = "valid", column = @Column(name = "valid")) })
public class SysDepartment extends PersistEntity {
    private static final long  serialVersionUID   = -8098711992771334960L;
    /** 自治区药监局机构代码 */
    public static final String PROVINCE_UNIT_CODE = "000";
    //属于稽查支队
    public static final String DOCASE_UNIT_RANK = "18";

    /** 部门名称 */
    private String depName;
    /** 部门编码 */
    private String depCode;
    /** 父部门编号 */
    private Long parentDep;
    /** 祖先部门编号 */
    private Long ancestorDep;
    /** 排序号 */
    private Long orderNum;
    /** 联系电话 */
    private String telephone;
    /** 单位标识 */
    private String            depRank;
    /** 备注 */
    private String remark;
    /** 发布类型 */
    private String unitFlag;
    /** 部门别名 */
    private String depAlias;
    /** ws认证标志 */
    private String wsAuthoriFlag;
    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "parentDep")
    private Set<SysDepartment> subDepartment;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentDep", insertable = false, updatable = false)
    private SysDepartment      parentDepartment;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ancestorDep", insertable = false, updatable = false)
    private SysDepartment      ancestorDepartment;

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

    public Boolean getHasChild() {
        if (null != subDepartment && subDepartment.size() > 0) {
            return true;
        } else {
            return false;
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

    public String getDepRank() {
        return depRank;
    }

    public void setDepRank(String depRank) {
        this.depRank = depRank;
    }

    /**
     * @return the ancestorDepartment
     */
    public SysDepartment getAncestorDepartment() {
        return ancestorDepartment;
    }

    /**
     * @param ancestorDepartment
     *            the ancestorDepartment to set
     */
    public void setAncestorDepartment(SysDepartment ancestorDepartment) {
        this.ancestorDepartment = ancestorDepartment;
    }

}
