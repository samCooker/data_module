/*
 * FileName:    AppFlowNodeOpinions.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年5月31日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.appflow.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.com.chaochuang.common.data.domain.LongIdEntity;

/**
 * @author LLM
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "node_opinions_id")) })
public class AppFlowNodeOpinions extends LongIdEntity {
    /** 原系统审批意见流水号 */
    private Long   rmNodeOpinionsId;
    /** 审批事项编号 */
    private Long   itemApplyId;
    /** 流程实例编号 */
    private Long   flowInstId;
    /** 意见所属环节编号 */
    private Long   nodeId;
    /** 环节名称 */
    private String nodeName;
    /** 环节代码 */
    private String nodeCode;
    /** 审批人编号 */
    private Long   transactId;
    /** 审批人名称 */
    private String transactName;
    /** 审批人部门编号 */
    private Long   transactDeptId;
    /** 审批人部门名称 */
    private String transactDeptName;
    /** 审批时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date   transactTime;
    /** 审批意见内容 */
    private String opinions;
    /** 阅示标识 */
    private String readSign;

    /**
     * @return the itemApplyId
     */
    public Long getItemApplyId() {
        return itemApplyId;
    }

    /**
     * @param itemApplyId
     *            the itemApplyId to set
     */
    public void setItemApplyId(Long itemApplyId) {
        this.itemApplyId = itemApplyId;
    }

    /**
     * @return the rmNodeOpinionsId
     */
    public Long getRmNodeOpinionsId() {
        return rmNodeOpinionsId;
    }

    /**
     * @param rmNodeOpinionsId
     *            the rmNodeOpinionsId to set
     */
    public void setRmNodeOpinionsId(Long rmNodeOpinionsId) {
        this.rmNodeOpinionsId = rmNodeOpinionsId;
    }

    /**
     * @return the nodeId
     */
    public Long getNodeId() {
        return nodeId;
    }

    /**
     * @param nodeId
     *            the nodeId to set
     */
    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    /**
     * @return the flowInstId
     */
    public Long getFlowInstId() {
        return flowInstId;
    }

    /**
     * @param flowInstId
     *            the flowInstId to set
     */
    public void setFlowInstId(Long flowInstId) {
        this.flowInstId = flowInstId;
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
     * @return the nodeCode
     */
    public String getNodeCode() {
        return nodeCode;
    }

    /**
     * @param nodeCode
     *            the nodeCode to set
     */
    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
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
     * @return the transactTime
     */
    public Date getTransactTime() {
        return transactTime;
    }

    /**
     * @param transactTime
     *            the transactTime to set
     */
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
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
     * @return the readSign
     */
    public String getReadSign() {
        return readSign;
    }

    /**
     * @param readSign
     *            the readSign to set
     */
    public void setReadSign(String readSign) {
        this.readSign = readSign;
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

}
