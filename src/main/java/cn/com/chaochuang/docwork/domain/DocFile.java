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
import cn.com.chaochuang.docwork.reference.EmergencyLevel;
import cn.com.chaochuang.docwork.reference.EmergencyLevelConverter;
import cn.com.chaochuang.docwork.reference.SecrectLevel;
import cn.com.chaochuang.docwork.reference.SecrectLevelConverter;

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
    @Convert(converter = EmergencyLevelConverter.class)
    private EmergencyLevel         emergencyLevel;
    /** 密级 */
    @Convert(converter = SecrectLevelConverter.class)
    private SecrectLevel           secretLevel;
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
    /** 创建时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date                   createDate;
    /** 数据导入时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date                   inputDate;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "fileId")
    /** 公文附件集合 */
    private List<DocFileAttach>    docFileAttachments;
    /** 流程集合 */
    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "fileId")
    private List<FlowNodeInfo>     flowNodes;
    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "fileId")
    /** 流程意见集合 */
    private List<FlowNodeOpinions> remoteFlowOpinions;

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
     * @return the docFileAttachments
     */
    public List<DocFileAttach> getDocFileAttachments() {
        return docFileAttachments;
    }

    /**
     * @param docFileAttachments
     *            the docFileAttachments to set
     */
    public void setDocFileAttachments(List<DocFileAttach> docFileAttachments) {
        this.docFileAttachments = docFileAttachments;
    }

    /**
     * @return the flowNodes
     */
    public List<FlowNodeInfo> getFlowNodes() {
        return flowNodes;
    }

    /**
     * @param flowNodes
     *            the flowNodes to set
     */
    public void setFlowNodes(List<FlowNodeInfo> flowNodes) {
        this.flowNodes = flowNodes;
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

}
