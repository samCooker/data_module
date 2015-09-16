/*
 * FileName:    VoiceEventHandleApprove.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月15日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.voice.domain;

import java.util.Date;

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
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "handle_approve_id")) })
public class VoiceEventHandleApprove extends LongIdEntity {
    /** 原系统审批意见编号 */
    private Long   rmHandleApproveId;
    /** 原系统审批编号 */
    private Long   rmEventHandleId;
    /** 办结时间 */
    private Date   finishTime;
    /** 状态 */
    private String status;
    /** 环节角色 */
    private String chainName;
    /** 办理人姓名 */
    private String name;
    /** 办理人部门 */
    private Long   depId;
    /** 办理人部门名称 */
    private String depName;
    /** 办理人编号 */
    private Long   userId;
    /** 交办人 */
    private Long   assigngeeId;
    /** 添加交办节点（上一个交办人 id） */
    private Long   assigngeeNode;
    /** 交办人姓名 */
    private String assigneeName;
    /** 交办时间 */
    private Date   assigneeTime;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the depId
     */
    public Long getDepId() {
        return depId;
    }

    /**
     * @param depId
     *            the depId to set
     */
    public void setDepId(Long depId) {
        this.depId = depId;
    }

    /**
     * @return the depName
     */
    public String getDepName() {
        return depName;
    }

    /**
     * @param depName
     *            the depName to set
     */
    public void setDepName(String depName) {
        this.depName = depName;
    }

    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return the rmHandleApproveId
     */
    public Long getRmHandleApproveId() {
        return rmHandleApproveId;
    }

    /**
     * @param rmHandleApproveId
     *            the rmHandleApproveId to set
     */
    public void setRmHandleApproveId(Long rmHandleApproveId) {
        this.rmHandleApproveId = rmHandleApproveId;
    }

    /**
     * @return the rmEventHandleId
     */
    public Long getRmEventHandleId() {
        return rmEventHandleId;
    }

    /**
     * @param rmEventHandleId
     *            the rmEventHandleId to set
     */
    public void setRmEventHandleId(Long rmEventHandleId) {
        this.rmEventHandleId = rmEventHandleId;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the assigngeeId
     */
    public Long getAssigngeeId() {
        return assigngeeId;
    }

    /**
     * @param assigngeeId
     *            the assigngeeId to set
     */
    public void setAssigngeeId(Long assigngeeId) {
        this.assigngeeId = assigngeeId;
    }

    /**
     * @return the assigneeName
     */
    public String getAssigneeName() {
        return assigneeName;
    }

    /**
     * @param assigneeName
     *            the assigneeName to set
     */
    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    /**
     * @return the assigneeTime
     */
    public Date getAssigneeTime() {
        return assigneeTime;
    }

    /**
     * @param assigneeTime
     *            the assigneeTime to set
     */
    public void setAssigneeTime(Date assigneeTime) {
        this.assigneeTime = assigneeTime;
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
     * @return the chainName
     */
    public String getChainName() {
        return chainName;
    }

    /**
     * @param chainName
     *            the chainName to set
     */
    public void setChainName(String chainName) {
        this.chainName = chainName;
    }

    /**
     * @return the assigngeeNode
     */
    public Long getAssigngeeNode() {
        return assigngeeNode;
    }

    /**
     * @param assigngeeNode
     *            the assigngeeNode to set
     */
    public void setAssigngeeNode(Long assigngeeNode) {
        this.assigngeeNode = assigngeeNode;
    }

}
