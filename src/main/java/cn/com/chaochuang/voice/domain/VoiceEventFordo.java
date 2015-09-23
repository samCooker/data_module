/*
 * FileName:    VoiceEventFordo.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月17日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.voice.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;

import cn.com.chaochuang.common.data.domain.LongIdEntity;
import cn.com.chaochuang.docwork.reference.FordoStatus;
import cn.com.chaochuang.docwork.reference.FordoStatusConverter;

/**
 * @author LLM
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "event_fordo_id")) })
public class VoiceEventFordo extends LongIdEntity {
    /** 原系统事件流水号 */
    private Long        rmEventId;
    /** 原系统审批环节编号（对应handle_man_id） */
    private Long        rmHandleApproveId;
    /** 舆情等级 */
    private String      grade;
    /** 舆情事件标题 */
    private String      title;
    /** 状态 */
    private String      status;
    /** 交办人姓名 */
    private String      assigneeName;
    /** 交办时间 */
    private Date        assigneeTime;
    /** 办理人姓名 */
    private String      name;
    /** 办理人编号 */
    private Long        userId;
    /** 办理人部门编号 */
    private Long        depId;
    /** 待办状态 */
    @Convert(converter = FordoStatusConverter.class)
    private FordoStatus fordoStatus;

    /**
     * @return the rmEventId
     */
    public Long getRmEventId() {
        return rmEventId;
    }

    /**
     * @param rmEventId
     *            the rmEventId to set
     */
    public void setRmEventId(Long rmEventId) {
        this.rmEventId = rmEventId;
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
     * @return the grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * @param grade
     *            the grade to set
     */
    public void setGrade(String grade) {
        this.grade = grade;
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
     * @return the fordoStatus
     */
    public FordoStatus getFordoStatus() {
        return fordoStatus;
    }

    /**
     * @param fordoStatus
     *            the fordoStatus to set
     */
    public void setFordoStatus(FordoStatus fordoStatus) {
        this.fordoStatus = fordoStatus;
    }

}
