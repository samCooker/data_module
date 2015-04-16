/*
 * FileName:    DocFileInfo.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月28日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.task.bean;

import java.util.Date;
import java.util.List;

/**
 * @author Shicx
 *
 */
public class DocFileInfo {

    /** 公文标题 */
    private String                     title;
    /** 文号 */
    private String                     docNumber;
    /** 紧急程度 */
    private String                     emergencyLevel;
    /** 密级 */
    private String                     secretLevel;
    /** 来文单位 */
    private String                     sourceDept;
    /** 办理期限 */
    private Date                       limitDate;
    /** 公文类别 */
    private String                     docType;
    /** 原系统公文编号 */
    private String                     rmInstanceId;
    /** 流程编号 */
    private String                     flowId;
    /** 创建时间 */
    private Date                       createDate;
    /** 数据导入时间 */
    private Date                       inputDate;
    /** 公文附件集合 */
    private List<DocFileAttachInfo>    remoteDocfileAttach;
    /** 流程集合 */
    private List<FlowNodeBeanInfo>     remoteFlowNodes;
    /** 流程意见集合 */
    private List<FlowNodeOpinionsInfo> remoteFlowOpinions;

    /**
     *
     */
    public DocFileInfo() {
        super();
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
     * @return the remoteDocfileAttach
     */
    public List<DocFileAttachInfo> getRemoteDocfileAttach() {
        return remoteDocfileAttach;
    }

    /**
     * @param remoteDocfileAttach
     *            the remoteDocfileAttach to set
     */
    public void setRemoteDocfileAttach(List<DocFileAttachInfo> remoteDocfileAttach) {
        this.remoteDocfileAttach = remoteDocfileAttach;
    }

    /**
     * @return the remoteFlowNodes
     */
    public List<FlowNodeBeanInfo> getRemoteFlowNodes() {
        return remoteFlowNodes;
    }

    /**
     * @param remoteFlowNodes
     *            the remoteFlowNodes to set
     */
    public void setRemoteFlowNodes(List<FlowNodeBeanInfo> remoteFlowNodes) {
        this.remoteFlowNodes = remoteFlowNodes;
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
     * @return the remoteFlowOpinions
     */
    public List<FlowNodeOpinionsInfo> getRemoteFlowOpinions() {
        return remoteFlowOpinions;
    }

    /**
     * @param remoteFlowOpinions
     *            the remoteFlowOpinions to set
     */
    public void setRemoteFlowOpinions(List<FlowNodeOpinionsInfo> remoteFlowOpinions) {
        this.remoteFlowOpinions = remoteFlowOpinions;
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
