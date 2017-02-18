package dbdata.aipcase.domain;

import cn.com.chaochuang.common.data.domain.LongIdEntity;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "law_content_tmp_id")) })
public class AipLawContentTmp extends LongIdEntity {

    /** 文书内容 */
    @OneToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "content_id")
    private AipLawContent content;
    /** 当事人 */
    private String fillEntpContacter;
    /** 企业Id */
    private Long fillEntpId;
    /** 当事人地址 */
    private String fillEntpAddr;
    /** 法人身份证 */
    private String corpIdentity;
    /** 法人联系电话 */
    private String corpPhone;
    /** 企业负责人 */
    private String fillEntpCorpname;
    /** 创建人 */
    private Long createrId;
    /** 创建人姓名 */
    private String createrName;
    /** 创建部门 */
    private Long createrDeptId;
    /** 创建日期 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    /** 所属单位 */
    private Long unitOrgId;
    /** 移动设备显示标识 */
    private String mobileDisplay;
    /** 案件状态 */
    private String    caseStatus;

    /**
     * @return the content
     */
    public AipLawContent getContent() {
        return content;
    }

    /**
     * @param content
     *            the content to set
     */
    public void setContent(AipLawContent content) {
        this.content = content;
    }

    /**
     * @return the fillEntpContacter
     */
    public String getFillEntpContacter() {
        return fillEntpContacter;
    }

    /**
     * @param fillEntpContacter
     *            the fillEntpContacter to set
     */
    public void setFillEntpContacter(String fillEntpContacter) {
        this.fillEntpContacter = fillEntpContacter;
    }

    /**
     * @return the fillEntpId
     */
    public Long getFillEntpId() {
        return fillEntpId;
    }

    /**
     * @param fillEntpId
     *            the fillEntpId to set
     */
    public void setFillEntpId(Long fillEntpId) {
        this.fillEntpId = fillEntpId;
    }

    /**
     * @return the fillEntpAddr
     */
    public String getFillEntpAddr() {
        return fillEntpAddr;
    }

    /**
     * @param fillEntpAddr
     *            the fillEntpAddr to set
     */
    public void setFillEntpAddr(String fillEntpAddr) {
        this.fillEntpAddr = fillEntpAddr;
    }

    /**
     * @return the corpIdentity
     */
    public String getCorpIdentity() {
        return corpIdentity;
    }

    /**
     * @param corpIdentity
     *            the corpIdentity to set
     */
    public void setCorpIdentity(String corpIdentity) {
        this.corpIdentity = corpIdentity;
    }

    /**
     * @return the corpPhone
     */
    public String getCorpPhone() {
        return corpPhone;
    }

    /**
     * @param corpPhone
     *            the corpPhone to set
     */
    public void setCorpPhone(String corpPhone) {
        this.corpPhone = corpPhone;
    }

    /**
     * @return the fillEntpCorpname
     */
    public String getFillEntpCorpname() {
        return fillEntpCorpname;
    }

    /**
     * @param fillEntpCorpname
     *            the fillEntpCorpname to set
     */
    public void setFillEntpCorpname(String fillEntpCorpname) {
        this.fillEntpCorpname = fillEntpCorpname;
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
     * @return the createrName
     */
    public String getCreaterName() {
        return createrName;
    }

    /**
     * @param createrName
     *            the createrName to set
     */
    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }

    /**
     * @return the createrDeptId
     */
    public Long getCreaterDeptId() {
        return createrDeptId;
    }

    /**
     * @param createrDeptId
     *            the createrDeptId to set
     */
    public void setCreaterDeptId(Long createrDeptId) {
        this.createrDeptId = createrDeptId;
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

    /**
     * @return the mobileDisplay
     */
    public String getMobileDisplay() {
        return mobileDisplay;
    }

    /**
     * @param mobileDisplay the mobileDisplay to set
     */
    public void setMobileDisplay(String mobileDisplay) {
        this.mobileDisplay = mobileDisplay;
    }

    /**
     * @return the caseStatus
     */
    public String getCaseStatus() {
        return caseStatus;
    }

    /**
     * @param caseStatus the caseStatus to set
     */
    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }
}