/*
 * FileName:    AipEvidenceFile.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年11月6日 (LLM) 1.0 Create
 */

package dbdata.aipcase.domain;

import cn.com.chaochuang.common.data.domain.LongIdEntity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "evidence_file_id")) })
public class AipEvidenceFile extends LongIdEntity {
    /** 案件基本编号 */
    private Long caseApplyId;
    /** 文书内容编号 */
    private Long contentId;
    /** 临时文书编号 */
    private Long lawContentTmpId;
    /** 证据说明 */
    private String caseBrief;
    /** 记录创建人编号 */
    private Long createrId;
    /** 创建人姓名 */
    private String createrName;
    /** 创建人部门编号 */
    private Long createrDeptId;
    /** 记录创建时间 */
    private Date createDate;
    /** 当事人姓名 */
    private String litigantName;
    /** 证据提取人姓名 */
    private String signName;
    /** 所属单位 */
    private Long unitOrgId;

    /**
     * @return the caseBrief
     */
    public String getCaseBrief() {
        return caseBrief;
    }

    /**
     * @param caseBrief
     *            the caseBrief to set
     */
    public void setCaseBrief(String caseBrief) {
        this.caseBrief = caseBrief;
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
     * @return the contentId
     */
    public Long getContentId() {
        return contentId;
    }

    /**
     * @param contentId
     *            the contentId to set
     */
    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    /**
     * @return the lawContentTmpId
     */
    public Long getLawContentTmpId() {
        return lawContentTmpId;
    }

    /**
     * @param lawContentTmpId
     *            the lawContentTmpId to set
     */
    public void setLawContentTmpId(Long lawContentTmpId) {
        this.lawContentTmpId = lawContentTmpId;
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
     * @return the litigantName
     */
    public String getLitigantName() {
        return litigantName;
    }

    /**
     * @param litigantName
     *            the litigantName to set
     */
    public void setLitigantName(String litigantName) {
        this.litigantName = litigantName;
    }

    /**
     * @return the signName
     */
    public String getSignName() {
        return signName;
    }

    /**
     * @param signName
     *            the signName to set
     */
    public void setSignName(String signName) {
        this.signName = signName;
    }

}
