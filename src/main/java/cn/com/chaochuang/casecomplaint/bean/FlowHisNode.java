/*
 * FileName:    FlowHisNode.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月14日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.casecomplaint.bean;

import java.util.Date;

/**
 * @author Shicx
 *
 */
public class FlowHisNode {
    /** 远程节点id */
    private Long   hisnoId;
    /** 实例流水号 */
    private String instId;
    /** 流程实例流水号 */
    private Long   flowInstId;
    /** 上一节点代码 */
    private String preNodeCode;
    /** 上一节点名称 */
    private String preNodeName;
    /** 当前节点id */
    private String curNodeId;
    /** 当前节点代码 */
    private String curNodeCode;
    /** 经办人 */
    private Long   curMan;
    /** 当前节点名称 */
    private String curNodeName;
    /** 经办人姓名 */
    private String curManName;
    /** 当前经办标识 0：用户 1：坐席 */
    private String curHandleFlag;
    /** 发送时间 */
    private Date   arriveTime;
    /** 完成时间 */
    private Date   doneTime;
    /** 限制办理时间 */
    private Long   timeLimit;
    /** 是否超时 */
    private String timeOut;
    /** 经办人所在部门id */
    private Long   curDepId;
    /** 经办人所在部门名称 */
    private String curDepName;
    /** 发送人id */
    private Long   sendMan;
    /** 发送人姓名 */
    private String sendManName;
    /** 环节类型 */
    private String nodeType;
    /** 经办单位 */
    private Long   unitId;

    // private String timeSet;
    // private Long dispMan;
    // private Long dispId;
    // private Long timeFact;
    // private String remark;
    // private Long signMan;
    // private Long curRole;
    // private Date signinTime;
    /**
     * @return the hisnoId
     */
    public Long getHisnoId() {
        return hisnoId;
    }

    /**
     * @param hisnoId
     *            the hisnoId to set
     */
    public void setHisnoId(Long hisnoId) {
        this.hisnoId = hisnoId;
    }

    /**
     * @return the instId
     */
    public String getInstId() {
        return instId;
    }

    /**
     * @param instId
     *            the instId to set
     */
    public void setInstId(String instId) {
        this.instId = instId;
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
     * @return the curNodeId
     */
    public String getCurNodeId() {
        return curNodeId;
    }

    /**
     * @param curNodeId
     *            the curNodeId to set
     */
    public void setCurNodeId(String curNodeId) {
        this.curNodeId = curNodeId;
    }

    /**
     * @return the curNodeCode
     */
    public String getCurNodeCode() {
        return curNodeCode;
    }

    /**
     * @param curNodeCode
     *            the curNodeCode to set
     */
    public void setCurNodeCode(String curNodeCode) {
        this.curNodeCode = curNodeCode;
    }

    /**
     * @return the curMan
     */
    public Long getCurMan() {
        return curMan;
    }

    /**
     * @param curMan
     *            the curMan to set
     */
    public void setCurMan(Long curMan) {
        this.curMan = curMan;
    }

    /**
     * @return the curNodeName
     */
    public String getCurNodeName() {
        return curNodeName;
    }

    /**
     * @param curNodeName
     *            the curNodeName to set
     */
    public void setCurNodeName(String curNodeName) {
        this.curNodeName = curNodeName;
    }

    /**
     * @return the curManName
     */
    public String getCurManName() {
        return curManName;
    }

    /**
     * @param curManName
     *            the curManName to set
     */
    public void setCurManName(String curManName) {
        this.curManName = curManName;
    }

    /**
     * @return the curHandleFlag
     */
    public String getCurHandleFlag() {
        return curHandleFlag;
    }

    /**
     * @param curHandleFlag
     *            the curHandleFlag to set
     */
    public void setCurHandleFlag(String curHandleFlag) {
        this.curHandleFlag = curHandleFlag;
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
     * @return the doneTime
     */
    public Date getDoneTime() {
        return doneTime;
    }

    /**
     * @param doneTime
     *            the doneTime to set
     */
    public void setDoneTime(Date doneTime) {
        this.doneTime = doneTime;
    }

    /**
     * @return the timeLimit
     */
    public Long getTimeLimit() {
        return timeLimit;
    }

    /**
     * @param timeLimit
     *            the timeLimit to set
     */
    public void setTimeLimit(Long timeLimit) {
        this.timeLimit = timeLimit;
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
     * @return the curDepId
     */
    public Long getCurDepId() {
        return curDepId;
    }

    /**
     * @param curDepId
     *            the curDepId to set
     */
    public void setCurDepId(Long curDepId) {
        this.curDepId = curDepId;
    }

    /**
     * @return the curDepName
     */
    public String getCurDepName() {
        return curDepName;
    }

    /**
     * @param curDepName
     *            the curDepName to set
     */
    public void setCurDepName(String curDepName) {
        this.curDepName = curDepName;
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
     * @return the unitId
     */
    public Long getUnitId() {
        return unitId;
    }

    /**
     * @param unitId
     *            the unitId to set
     */
    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

}
