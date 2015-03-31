/*
 * FileName:    FlowNodeInfo.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月19日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.docwork.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;

import cn.com.chaochuang.common.data.domain.LongIdEntity;
import cn.com.chaochuang.docwork.reference.IsSubmitData;

/**
 * @author LLM
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "node_info_id")) })
public class FlowNodeInfo extends LongIdEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /** 公文id */
    private Long              fileId;
    /** 原实例编号 */
    private String            instanceId;
    /** 环节编号 */
    private String            nodeId;
    /** 环节名称 */
    private String            nodeName;
    /** 办理意见 */
    private String            opinions;
    /** 办理人 */
    private String            transactName;
    /** 办理人id */
    private Long              transactId;
    /** 办理部门 id */
    private Long              transactDeptId;
    /** 办理部门 */
    private String            transactDeptName;
    /** 办理时间 */
    private Date              transactDate;
    /** 当前环节 */
    private Long              rmInstnoId;
    /** 是否提交数据 */
    private IsSubmitData      submitData;

    /**
            *
            */
    public FlowNodeInfo() {
        super();
    }

    /**
     * @param instanceId
     */
    public FlowNodeInfo(String instanceId) {
        super();
        this.instanceId = instanceId;
    }

    /**
     * @return the instanceId
     */
    public String getInstanceId() {
        return instanceId;
    }

    /**
     * @param instanceId
     *            the instanceId to set
     */
    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    /**
     * @return the nodeId
     */
    public String getNodeId() {
        return nodeId;
    }

    /**
     * @param nodeId
     *            the nodeId to set
     */
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    /**
     * @return the nodeName
     */
    public String getNodeName() {
        return nodeName;
    }

    /**
     * @param nodeName
     *            the nodeName to set
     */
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    /**
     * @return the transactName
     */
    public String getTransactName() {
        return transactName;
    }

    /**
     * @param transactName
     *            the transactName to set
     */
    public void setTransactName(String transactName) {
        this.transactName = transactName;
    }

    /**
     * @return the transactId
     */
    public Long getTransactId() {
        return transactId;
    }

    /**
     * @param transactId
     *            the transactId to set
     */
    public void setTransactId(Long transactId) {
        this.transactId = transactId;
    }

    /**
     * @return the transactDeptId
     */
    public Long getTransactDeptId() {
        return transactDeptId;
    }

    /**
     * @param transactDeptId
     *            the transactDeptId to set
     */
    public void setTransactDeptId(Long transactDeptId) {
        this.transactDeptId = transactDeptId;
    }

    /**
     * @return the transactDeptName
     */
    public String getTransactDeptName() {
        return transactDeptName;
    }

    /**
     * @param transactDeptName
     *            the transactDeptName to set
     */
    public void setTransactDeptName(String transactDeptName) {
        this.transactDeptName = transactDeptName;
    }

    /**
     * @return the transactDate
     */
    public Date getTransactDate() {
        return transactDate;
    }

    /**
     * @param transactDate
     *            the transactDate to set
     */
    public void setTransactDate(Date transactDate) {
        this.transactDate = transactDate;
    }

    /**
     * @return the fileId
     */
    public Long getFileId() {
        return fileId;
    }

    /**
     * @param fileId
     *            the fileId to set
     */
    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    /**
     * @return the opinions
     */
    public String getOpinions() {
        return opinions;
    }

    /**
     * @param opinions
     *            the opinions to set
     */
    public void setOpinions(String opinions) {
        this.opinions = opinions;
    }

    /**
     * @return the submitData
     */
    public IsSubmitData getSubmitData() {
        return submitData;
    }

    /**
     * @param submitData
     *            the submitData to set
     */
    public void setSubmitData(IsSubmitData submitData) {
        this.submitData = submitData;
    }

    /**
     * @return the rmInstnoId
     */
    public Long getRmInstnoId() {
        return rmInstnoId;
    }

    /**
     * @param rmInstnoId
     *            the rmInstnoId to set
     */
    public void setRmInstnoId(Long rmInstnoId) {
        this.rmInstnoId = rmInstnoId;
    }

}
