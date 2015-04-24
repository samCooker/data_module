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

    /** 原系统部门编号 */
    private Long               rmDepId;
    /** 部门名称 */
    private String             depName;
    /** 父部门编号 */
    private Long               parentDep;
    /** 祖先部门编号 */
    private Long               ancestorDep;
    /** 排序号 */
    private Long               orderNum;
    /** 部门别名 */
    private String             depAlias;
    /** 部门领导 */
    private String             depLead;
    /** 县局标识 */
    private String             depRank;
    /** 原系统的有效性标志 */
    private String             delFlag;
    /** MD5校验码 */
    private String             mdfCode;

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
        }
        return true;
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
     * @return the depName
     */
    public String getDepName() {
        return depName;
    }

    /**
     * @param depName
     *            the depName to set
     */
    public void setDepName(String depName) {
        this.depName = depName;
    }

    /**
     * @return the parentDep
     */
    public Long getParentDep() {
        return parentDep;
    }

    /**
     * @param parentDep
     *            the parentDep to set
     */
    public void setParentDep(Long parentDep) {
        this.parentDep = parentDep;
    }

    /**
     * @return the ancestorDep
     */
    public Long getAncestorDep() {
        return ancestorDep;
    }

    /**
     * @param ancestorDep
     *            the ancestorDep to set
     */
    public void setAncestorDep(Long ancestorDep) {
        this.ancestorDep = ancestorDep;
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

    /**
     * @return the depAlias
     */
    public String getDepAlias() {
        return depAlias;
    }

    /**
     * @param depAlias
     *            the depAlias to set
     */
    public void setDepAlias(String depAlias) {
        this.depAlias = depAlias;
    }

    /**
     * @return the depLead
     */
    public String getDepLead() {
        return depLead;
    }

    /**
     * @param depLead
     *            the depLead to set
     */
    public void setDepLead(String depLead) {
        this.depLead = depLead;
    }

    /**
     * @return the depRank
     */
    public String getDepRank() {
        return depRank;
    }

    /**
     * @param depRank
     *            the depRank to set
     */
    public void setDepRank(String depRank) {
        this.depRank = depRank;
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
     * @return the subDepartment
     */
    public Set<SysDepartment> getSubDepartment() {
        return subDepartment;
    }

    /**
     * @param subDepartment
     *            the subDepartment to set
     */
    public void setSubDepartment(Set<SysDepartment> subDepartment) {
        this.subDepartment = subDepartment;
    }

    /**
     * @return the parentDepartment
     */
    public SysDepartment getParentDepartment() {
        return parentDepartment;
    }

    /**
     * @param parentDepartment
     *            the parentDepartment to set
     */
    public void setParentDepartment(SysDepartment parentDepartment) {
        this.parentDepartment = parentDepartment;
    }

}
