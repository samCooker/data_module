/*
 * FileName:    AipFlowNoteInfo.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年1月20日 (LLM) 1.0 Create
 */

package dbdata.aipcase.domain;

import cn.com.chaochuang.common.data.domain.LongIdEntity;
import dbdata.aipcase.reference.NodeType;

import javax.persistence.*;
import java.util.Date;

/**
 * @author LLM
 */
@Entity
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "flow_node_info_id"))})
public class AipFlowNoteInfo extends LongIdEntity {
    /**   */
    private static final long serialVersionUID = -6834355465242993954L;
    /**
     * 案件基本编号
     */
    private Long caseApplyId;
    /**
     * 案件状态
     */
    private String caseStatus;
    /**
     * 案件标识
     */
    private String flowFlag;
    /**
     * 案件标题
     */
    private String flowTitle;
    /**
     * 审批意见
     */
    private String opinion;
    /**
     * 经办人编号
     */
    private Long transactor;
    /**
     * 发送人编号
     */
    private Long senderId;
    /**
     * 发送时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendTime;
    /**
     * 完成时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date finishTime;
    /**
     * 是否超时
     */
    private String isOverTime;
    /**
     * 发送人部门
     */
    private Long senderDeptId;
    /**
     * 发送人姓名
     */
    private String senderName;
    /**
     * 经办人本名编号
     */
    private Long transactorDeptId;
    /**
     * 经办人姓名
     */
    private String transactorName;
    /**
     * 实例编号
     */
    private Long instId;
    /**
     * 环节编号
     */
    @ManyToOne
    @JoinColumn(name = "node_id")
    private AipNode node;
    /**
     * 前序环节编号
     */
    @ManyToOne
    @JoinColumn(name = "pre_node_id")
    private AipNode preNode;
    /**
     * 环节操作类型
     */
    private String nodeType;

    /**
     * @return the caseApplyId
     */
    public Long getCaseApplyId() {
        return caseApplyId;
    }

    /**
     * @param caseApplyId the caseApplyId to set
     */
    public void setCaseApplyId(Long caseApplyId) {
        this.caseApplyId = caseApplyId;
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

    /**
     * @return the flowFlag
     */
    public String getFlowFlag() {
        return flowFlag;
    }

    /**
     * @param flowFlag the flowFlag to set
     */
    public void setFlowFlag(String flowFlag) {
        this.flowFlag = flowFlag;
    }

    /**
     * @return the flowTitle
     */
    public String getFlowTitle() {
        return flowTitle;
    }

    /**
     * @param flowTitle the flowTitle to set
     */
    public void setFlowTitle(String flowTitle) {
        this.flowTitle = flowTitle;
    }

    /**
     * @return the opinion
     */
    public String getOpinion() {
        return opinion;
    }

    /**
     * @param opinion the opinion to set
     */
    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    /**
     * @return the transactor
     */
    public Long getTransactor() {
        return transactor;
    }

    /**
     * @param transactor the transactor to set
     */
    public void setTransactor(Long transactor) {
        this.transactor = transactor;
    }

    /**
     * @return the senderId
     */
    public Long getSenderId() {
        return senderId;
    }

    /**
     * @param senderId the senderId to set
     */
    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    /**
     * @return the sendTime
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * @param sendTime the sendTime to set
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * @return the finishTime
     */
    public Date getFinishTime() {
        return finishTime;
    }

    /**
     * @param finishTime the finishTime to set
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * @return the isOverTime
     */
    public String getIsOverTime() {
        return isOverTime;
    }

    /**
     * @param isOverTime the isOverTime to set
     */
    public void setIsOverTime(String isOverTime) {
        this.isOverTime = isOverTime;
    }

    /**
     * @return the senderDeptId
     */
    public Long getSenderDeptId() {
        return senderDeptId;
    }

    /**
     * @param senderDeptId the senderDeptId to set
     */
    public void setSenderDeptId(Long senderDeptId) {
        this.senderDeptId = senderDeptId;
    }

    /**
     * @return the senderName
     */
    public String getSenderName() {
        return senderName;
    }

    /**
     * @param senderName the senderName to set
     */
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    /**
     * @return the transactorDeptId
     */
    public Long getTransactorDeptId() {
        return transactorDeptId;
    }

    /**
     * @param transactorDeptId the transactorDeptId to set
     */
    public void setTransactorDeptId(Long transactorDeptId) {
        this.transactorDeptId = transactorDeptId;
    }

    /**
     * @return the transactorName
     */
    public String getTransactorName() {
        return transactorName;
    }

    /**
     * @param transactorName the transactorName to set
     */
    public void setTransactorName(String transactorName) {
        this.transactorName = transactorName;
    }

    /**
     * @return the instId
     */
    public Long getInstId() {
        return instId;
    }

    /**
     * @param instId the instId to set
     */
    public void setInstId(Long instId) {
        this.instId = instId;
    }

    /**
     * @return the node
     */
    public AipNode getNode() {
        return node;
    }

    /**
     * @param node the node to set
     */
    public void setNode(AipNode node) {
        this.node = node;
    }

    /**
     * @return the preNode
     */
    public AipNode getPreNode() {
        return preNode;
    }

    /**
     * @param preNode the preNode to set
     */
    public void setPreNode(AipNode preNode) {
        this.preNode = preNode;
    }

    /**
     * @return the nodeType
     */
    public String getNodeType() {
        return nodeType;
    }

    /**
     * @param nodeType the nodeType to set
     */
    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }
}
