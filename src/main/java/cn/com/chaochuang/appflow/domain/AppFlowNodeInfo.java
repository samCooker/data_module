/*
 * FileName:    AppFlowNodeInfo.java
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
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "node_info_id")) })
public class AppFlowNodeInfo extends LongIdEntity {
    /** 原系统实例环节流水号 */
    private Long   rmNodeInfoId;
    /** 审批事项编号 */
    private Long   itemApplyId;
    /** 流程实例编号 */
    private Long   flowInstId;
    /** 前继环节流水号 */
    private Long   preNodeId;
    /** 前继环节代号 */
    private String preNodeCode;
    /** 前继环节名称 */
    private String preNodeName;
    /** 当前环节代号 */
    private String nodeCode;
    /** 当前环节名称 */
    private String nodeName;
    /** 环节发送时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date   arriveTime;
    /** 环节处理结束 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date   finishTime;
    /** 处理时限 */
    private Long   limitDate;
    /** 超时标志 */
    private String timeOut;
    /** 环节类型 */
    private String nodeType;
    /** 当前环节发送人编号 */
    private Long   sendMan;
    /** 当前环节发送人名称 */
    private String sendManName;
    /** 当前环节处理人编号 */
    private Long   transactId;
    /** 当前环节处理人姓名 */
    private String transactName;
    /** 当前环节处理人所属部门 */
    private Long   transactDeptId;
    /** 当前环节处理人所属部门名称 */
    private String transactDeptName;
    /** 环节所属单位 */
    private Long   handleUnitId;

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
     * @return the rmNodeInfoId
     */
    public Long getRmNodeInfoId() {
        return rmNodeInfoId;
    }

    /**
     * @param rmNodeInfoId
     *            the rmNodeInfoId to set
     */
    public void setRmNodeInfoId(Long rmNodeInfoId) {
        this.rmNodeInfoId = rmNodeInfoId;
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
     * @return the preNodeId
     */
    public Long getPreNodeId() {
        return preNodeId;
    }

    /**
     * @param preNodeId
     *            the preNodeId to set
     */
    public void setPreNodeId(Long preNodeId) {
        this.preNodeId = preNodeId;
    }

    /**
     * @return the preNodeCode
     */
    public String getPreNodeCode() {
        return preNodeCode;
    }

    /**
     * @param preNodeCode
     *            the preNodeCode to set
     */
    public void setPreNodeCode(String preNodeCode) {
        this.preNodeCode = preNodeCode;
    }

    /**
     * @return the preNodeName
     */
    public String getPreNodeName() {
        return preNodeName;
    }

    /**
     * @param preNodeName
     *            the preNodeName to set
     */
    public void setPreNodeName(String preNodeName) {
        this.preNodeName = preNodeName;
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
     * @return the arriveTime
     */
    public Date getArriveTime() {
        return arriveTime;
    }

    /**
     * @param arriveTime
     *            the arriveTime to set
     */
    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    /**
     * @return the finishTime
     */
    public Date getFinishTime() {
        return finishTime;
    }

    /**
     * @param finishTime
     *            the finishTime to set
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * @return the limitDate
     */
    public Long getLimitDate() {
        return limitDate;
    }

    /**
     * @param limitDate
     *            the limitDate to set
     */
    public void setLimitDate(Long limitDate) {
        this.limitDate = limitDate;
    }

    /**
     * @return the timeOut
     */
    public String getTimeOut() {
        return timeOut;
    }

    /**
     * @param timeOut
     *            the timeOut to set
     */
    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    /**
     * @return the nodeType
     */
    public String getNodeType() {
        return nodeType;
    }

    /**
     * @param nodeType
     *            the nodeType to set
     */
    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    /**
     * @return the sendMan
     */
    public Long getSendMan() {
        return sendMan;
    }

    /**
     * @param sendMan
     *            the sendMan to set
     */
    public void setSendMan(Long sendMan) {
        this.sendMan = sendMan;
    }

    /**
     * @return the sendManName
     */
    public String getSendManName() {
        return sendManName;
    }

    /**
     * @param sendManName
     *            the sendManName to set
     */
    public void setSendManName(String sendManName) {
        this.sendManName = sendManName;
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
     * @return the handleUnitId
     */
    public Long getHandleUnitId() {
        return handleUnitId;
    }

    /**
     * @param handleUnitId
     *            the handleUnitId to set
     */
    public void setHandleUnitId(Long handleUnitId) {
        this.handleUnitId = handleUnitId;
    }

}
