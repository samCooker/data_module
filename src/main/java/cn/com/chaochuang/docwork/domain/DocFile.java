/*
 * FileName:    DocFile.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月19日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.docwork.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.com.chaochuang.common.data.domain.LongIdEntity;
import cn.com.chaochuang.docwork.reference.DocStatus;
import cn.com.chaochuang.docwork.reference.DocStatusConverter;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author LLM
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "doc_id")) })
public class DocFile extends LongIdEntity {

    /**
     *
     */
    private static final long      serialVersionUID = 1L;
    /** 公文标题 */
    private String                 title;
    /** 文号 */
    private String                 docNumber;
    /** 紧急程度 */
    private String                 emergencyLevel;
    /** 密级 */
    private String                 secretLevel;
    /** 来文单位 */
    private String                 sourceDept;
    /** 办理期限 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date                   limitDate;
    /** 公文类别 */
    private String                 docType;
    /** 原系统公文编号 */
    private String                 rmInstanceId;
    /** 流程编号 */
    private String                 flowId;
    /** 创建人id */
    private Long                   creatorId;
    /** 创建人姓名 */
    private String                 creatorName;
    /** 创建人部门id */
    private Long                   creatorDeptId;
    /** 创建人部门名称 */
    private String                 creatorDeptName;
    /** 创建时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date                   createDate;
    /** 数据导入时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date                   inputDate;
    /** 公文状态 */
    @Convert(converter = DocStatusConverter.class)
    private DocStatus              docStatus;
    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "docId")
    /** 公文附件集合 */
    private List<DocFileAttach>    docFileAttachs;
    /** 流程集合 */
    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "docId")
    private List<FlowNodeInfo>     flowNodeInfos;
    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "docId")
    /** 流程意见集合 */
    private List<FlowNodeOpinions> flowNodeOpinions;

    /**
     * @return the docStatus
     */
    public DocStatus getDocStatus() {
        return docStatus;
    }

    /**
     * @param docStatus
     *            the docStatus to set
     */
    public void setDocStatus(DocStatus docStatus) {
        this.docStatus = docStatus;
    }

    /**
     * @return the flowNodeOpinions
     */
    public List<FlowNodeOpinions> getFlowNodeOpinions() {
        return flowNodeOpinions;
    }

    /**
     * @param flowNodeOpinions
     *            the flowNodeOpinions to set
     */
    public void setFlowNodeOpinions(List<FlowNodeOpinions> flowNodeOpinions) {
        this.flowNodeOpinions = flowNodeOpinions;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the docNumber
     */
    public String getDocNumber() {
        return docNumber;
    }

    /**
     * @param docNumber
     *            the docNumber to set
     */
    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    /**
     * @return the emergencyLevel
     */
    public String getEmergencyLevel() {
        return emergencyLevel;
    }

    /**
     * @param emergencyLevel
     *            the emergencyLevel to set
     */
    public void setEmergencyLevel(String emergencyLevel) {
        this.emergencyLevel = emergencyLevel;
    }

    /**
     * @return the secretLevel
     */
    public String getSecretLevel() {
        return secretLevel;
    }

    /**
     * @param secretLevel
     *            the secretLevel to set
     */
    public void setSecretLevel(String secretLevel) {
        this.secretLevel = secretLevel;
    }

    /**
     * @return the sourceDept
     */
    public String getSourceDept() {
        return sourceDept;
    }

    /**
     * @param sourceDept
     *            the sourceDept to set
     */
    public void setSourceDept(String sourceDept) {
        this.sourceDept = sourceDept;
    }

    /**
     * @return the limitDate
     */
    public Date getLimitDate() {
        return limitDate;
    }

    /**
     * @param limitDate
     *            the limitDate to set
     */
    public void setLimitDate(Date limitDate) {
        this.limitDate = limitDate;
    }

    /**
     * @return the docType
     */
    public String getDocType() {
        return docType;
    }

    /**
     * @param docType
     *            the docType to set
     */
    public void setDocType(String docType) {
        this.docType = docType;
    }

    /**
     * @return the rmInstanceId
     */
    public String getRmInstanceId() {
        return rmInstanceId;
    }

    /**
     * @param rmInstanceId
     *            the rmInstanceId to set
     */
    public void setRmInstanceId(String rmInstanceId) {
        this.rmInstanceId = rmInstanceId;
    }

    /**
     * @return the flowId
     */
    public String getFlowId() {
        return flowId;
    }

    /**
     * @param flowId
     *            the flowId to set
     */
    public void setFlowId(String flowId) {
        this.flowId = flowId;
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
     * @return the inputDate
     */
    public Date getInputDate() {
        return inputDate;
    }

    /**
     * @param inputDate
     *            the inputDate to set
     */
    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    /**
     * @return the docFileAttachs
     */
    public List<DocFileAttach> getDocFileAttachs() {
        return docFileAttachs;
    }

    /**
     * @param docFileAttachs
     *            the docFileAttachs to set
     */
    public void setDocFileAttachs(List<DocFileAttach> docFileAttachs) {
        this.docFileAttachs = docFileAttachs;
    }

    /**
     * @return the flowNodeInfos
     */
    public List<FlowNodeInfo> getFlowNodeInfos() {
        return flowNodeInfos;
    }

    /**
     * @param flowNodeInfos
     *            the flowNodeInfos to set
     */
    public void setFlowNodeInfos(List<FlowNodeInfo> flowNodeInfos) {
        this.flowNodeInfos = flowNodeInfos;
    }

    /**
     * @return the creatorId
     */
    public Long getCreatorId() {
        return creatorId;
    }

    /**
     * @param creatorId
     *            the creatorId to set
     */
    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * @return the creatorName
     */
    public String getCreatorName() {
        return creatorName;
    }

    /**
     * @param creatorName
     *            the creatorName to set
     */
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    /**
     * @return the creatorDeptId
     */
    public Long getCreatorDeptId() {
        return creatorDeptId;
    }

    /**
     * @param creatorDeptId
     *            the creatorDeptId to set
     */
    public void setCreatorDeptId(Long creatorDeptId) {
        this.creatorDeptId = creatorDeptId;
    }

    /**
     * @return the creatorDeptName
     */
    public String getCreatorDeptName() {
        return creatorDeptName;
    }

    /**
     * @param creatorDeptName
     *            the creatorDeptName to set
     */
    public void setCreatorDeptName(String creatorDeptName) {
        this.creatorDeptName = creatorDeptName;
    }

}
