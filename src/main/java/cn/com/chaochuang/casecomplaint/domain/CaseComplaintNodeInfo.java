/*
 * FileName:    SysDataChange.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年09月10日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.casecomplaint.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.com.chaochuang.common.data.domain.LongIdEntity;

/**
 * @author Shicx
 *
 */
@SuppressWarnings("serial")
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "NODE_INFO_ID")) })
public class CaseComplaintNodeInfo extends LongIdEntity {
    /** 远程节点id */
    private Long   rmNodeInfoId;
    /** 发送时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date   arriveTime;
    /** 是否超时 */
    private String timeOut;
    /** 环节类型 */
    private String nodeType;
    /** 发送人id */
    private Long   sendMan;
    /** 发送人姓名 */
    private String sendManName;
    /** 经办单位 */
    private Long   unitId;
    /** 流程实例流水号 */
    private Long   flowInstId;
    /** 流程实例id */
    private String instId;
    /** 远程投诉举报id!! */
    private Long   caseComplaintId;
    /** 当前经办标识 0：用户 1：坐席 */
    private String curHandleFlag;
    /** 当前节点id */
    private String curNodeId;
    /** 当前节点代码 */
    private String curNodeCode;
    /** 当前节点名称 */
    private String curNodeName;
    /** 上一节点代码 */
    private String preNodeCode;
    /** 上一节点名称 */
    private String preNodeName;
    /** 经办人 */
    private Long   curMan;
    /** 经办人姓名 */
    private String curManName;
    /** 经办人所在部门id */
    private Long   curDepId;
    /** 经办人所在部门名称 */
    private String curDepName;
    /** 限制办理时间 */
    private Long   timeLimit;
    /** 完成时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date   doneTime;

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Date getArriveTime() {
        return arriveTime;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setSendMan(Long sendMan) {
        this.sendMan = sendMan;
    }

    public Long getSendMan() {
        return sendMan;
    }

    public void setSendManName(String sendManName) {
        this.sendManName = sendManName;
    }

    public String getSendManName() {
        return sendManName;
    }

    public void setCurDepName(String curDepName) {
        this.curDepName = curDepName;
    }

    public String getCurDepName() {
        return curDepName;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setFlowInstId(Long flowInstId) {
        this.flowInstId = flowInstId;
    }

    public Long getFlowInstId() {
        return flowInstId;
    }

    public void setRmNodeInfoId(Long rmNodeInfoId) {
        this.rmNodeInfoId = rmNodeInfoId;
    }

    public Long getRmNodeInfoId() {
        return rmNodeInfoId;
    }

    public void setCaseComplaintId(Long caseComplaintId) {
        this.caseComplaintId = caseComplaintId;
    }

    public Long getCaseComplaintId() {
        return caseComplaintId;
    }

    public void setInstId(String instId) {
        this.instId = instId;
    }

    public String getInstId() {
        return instId;
    }

    public void setCurHandleFlag(String curHandleFlag) {
        this.curHandleFlag = curHandleFlag;
    }

    public String getCurHandleFlag() {
        return curHandleFlag;
    }

    public void setCurNodeId(String curNodeId) {
        this.curNodeId = curNodeId;
    }

    public String getCurNodeId() {
        return curNodeId;
    }

    public void setCurNodeCode(String curNodeCode) {
        this.curNodeCode = curNodeCode;
    }

    public String getCurNodeCode() {
        return curNodeCode;
    }

    public void setCurNodeName(String curNodeName) {
        this.curNodeName = curNodeName;
    }

    public String getCurNodeName() {
        return curNodeName;
    }

    public void setPreNodeCode(String preNodeCode) {
        this.preNodeCode = preNodeCode;
    }

    public String getPreNodeCode() {
        return preNodeCode;
    }

    public void setPreNodeName(String preNodeName) {
        this.preNodeName = preNodeName;
    }

    public String getPreNodeName() {
        return preNodeName;
    }

    public void setCurMan(Long curMan) {
        this.curMan = curMan;
    }

    public Long getCurMan() {
        return curMan;
    }

    public void setCurManName(String curManName) {
        this.curManName = curManName;
    }

    public String getCurManName() {
        return curManName;
    }

    public void setCurDepId(Long curDepId) {
        this.curDepId = curDepId;
    }

    public Long getCurDepId() {
        return curDepId;
    }

    public void setTimeLimit(Long timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Long getTimeLimit() {
        return timeLimit;
    }

    public void setDoneTime(Date doneTime) {
        this.doneTime = doneTime;
    }

    public Date getDoneTime() {
        return doneTime;
    }

}
