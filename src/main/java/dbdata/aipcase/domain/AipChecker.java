package dbdata.aipcase.domain;

import dbdata.common.domain.SysUser;
import cn.com.chaochuang.common.data.domain.LongIdEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "check_id")) })
public class AipChecker extends LongIdEntity {

    private static final long serialVersionUID = 4176397576378928246L;
    /** 案件基本信息编号 */
    private Long caseApplyId;
    @ManyToOne
    @JoinColumn(name = "caseApplyId",insertable = false,updatable = false)
    private AipCaseApply caseApply;
    /** 检查人编号 */
    @OneToOne
    @JoinColumn(name = "checker_id")
    private SysUser checker;
    /** 检查人部门编号 */
    private Long checkerDeptId;
    /** 案件状态 */
    private String caseStatus;
    /** 记录创建人编号 */
    private Long createrId;
    /** 记录创建人日期 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    /** 检查人所属单位编号 */
    private Long unitOrgId;

    /**
     * 构造函数
     */
    public AipChecker() {
        super();
    }

    /**
     * 构造函数
     *
     * @param caseApplyId
     * @param caseStatus
     */
    public AipChecker(Long caseApplyId, String caseStatus) {
        super();
        this.caseApplyId = caseApplyId;
        this.caseStatus = caseStatus;
        this.createDate = new Date();
    }

    /**
     * @return the caseApplyId
     */
    public Long getCaseApplyId() {
        return caseApplyId;
    }

    /**
     * @param caseApplyId
     *            the caseApplyId to set
     */
    public void setCaseApplyId(Long caseApplyId) {
        this.caseApplyId = caseApplyId;
    }

    /**
     * @return the checker
     */
    public SysUser getChecker() {
        return checker;
    }

    /**
     * @param checker
     *            the checker to set
     */
    public void setChecker(SysUser checker) {
        this.checker = checker;
        if (checker != null) {
            this.checkerDeptId = checker.getDepartment().getId();
        }
    }

    /**
     * @return the checkerDeptId
     */
    public Long getCheckerDeptId() {
        return checkerDeptId;
    }

    /**
     * @param checkerDeptId
     *            the checkerDeptId to set
     */
    public void setCheckerDeptId(Long checkerDeptId) {
        this.checkerDeptId = checkerDeptId;
    }

    /**
     * @return the caseStatus
     */
    public String getCaseStatus() {
        return caseStatus;
    }

    /**
     * @param caseStatus
     *            the caseStatus to set
     */
    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    /**
     * @return the createrId
     */
    public Long getCreaterId() {
        return createrId;
    }

    /**
     * @param createrId
     *            the createrId to set
     */
    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    /**
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     *            the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the unitOrgId
     */
    public Long getUnitOrgId() {
        return unitOrgId;
    }

    /**
     * @param unitOrgId
     *            the unitOrgId to set
     */
    public void setUnitOrgId(Long unitOrgId) {
        this.unitOrgId = unitOrgId;
    }

    public AipCaseApply getCaseApply() {
        return caseApply;
    }

    public void setCaseApply(AipCaseApply caseApply) {
        this.caseApply = caseApply;
    }
}