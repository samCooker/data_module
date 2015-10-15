/*
 * FileName:    AuditPrjContent.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年10月13日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.audit.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;

import cn.com.chaochuang.common.data.domain.LongIdEntity;

/**
 * @author LLM
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "prj_content_id")) })
public class AuditPrjContent extends LongIdEntity {
    /** 原系统审查项目编号 */
    private Long   rmPrjContentId;
    /** 审查任务编号 */
    private Long   auditTaskId;
    /** 审查项目 */
    private String prjContent;
    /** 审查项目类别 */
    private String prjType;
    /** 审查方法 */
    private String auditMethod;
    /** 审查结论 */
    private String auditResult;
    /** 审查记录 */
    private String auditRecord;
    /** 整改结论 */
    private String improveFlag;
    /** 整改情况 */
    private String improveResult;

    /**
     * @return the rmPrjContentId
     */
    public Long getRmPrjContentId() {
        return rmPrjContentId;
    }

    /**
     * @param rmPrjContentId
     *            the rmPrjContentId to set
     */
    public void setRmPrjContentId(Long rmPrjContentId) {
        this.rmPrjContentId = rmPrjContentId;
    }

    /**
     * @return the auditTaskId
     */
    public Long getAuditTaskId() {
        return auditTaskId;
    }

    /**
     * @param auditTaskId
     *            the auditTaskId to set
     */
    public void setAuditTaskId(Long auditTaskId) {
        this.auditTaskId = auditTaskId;
    }

    /**
     * @return the prjContent
     */
    public String getPrjContent() {
        return prjContent;
    }

    /**
     * @param prjContent
     *            the prjContent to set
     */
    public void setPrjContent(String prjContent) {
        this.prjContent = prjContent;
    }

    /**
     * @return the prjType
     */
    public String getPrjType() {
        return prjType;
    }

    /**
     * @param prjType
     *            the prjType to set
     */
    public void setPrjType(String prjType) {
        this.prjType = prjType;
    }

    /**
     * @return the auditMethod
     */
    public String getAuditMethod() {
        return auditMethod;
    }

    /**
     * @param auditMethod
     *            the auditMethod to set
     */
    public void setAuditMethod(String auditMethod) {
        this.auditMethod = auditMethod;
    }

    /**
     * @return the auditResult
     */
    public String getAuditResult() {
        return auditResult;
    }

    /**
     * @param auditResult
     *            the auditResult to set
     */
    public void setAuditResult(String auditResult) {
        this.auditResult = auditResult;
    }

    /**
     * @return the auditRecord
     */
    public String getAuditRecord() {
        return auditRecord;
    }

    /**
     * @param auditRecord
     *            the auditRecord to set
     */
    public void setAuditRecord(String auditRecord) {
        this.auditRecord = auditRecord;
    }

    /**
     * @return the improveFlag
     */
    public String getImproveFlag() {
        return improveFlag;
    }

    /**
     * @param improveFlag
     *            the improveFlag to set
     */
    public void setImproveFlag(String improveFlag) {
        this.improveFlag = improveFlag;
    }

    /**
     * @return the improveResult
     */
    public String getImproveResult() {
        return improveResult;
    }

    /**
     * @param improveResult
     *            the improveResult to set
     */
    public void setImproveResult(String improveResult) {
        this.improveResult = improveResult;
    }

}
