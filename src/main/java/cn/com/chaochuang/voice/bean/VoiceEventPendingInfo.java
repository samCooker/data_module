/*
 * FileName:    VoiceEventPending.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月15日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.voice.bean;

import java.util.Date;
import java.util.List;

import cn.com.chaochuang.common.fdfordo.bean.CommonPendingHandleInfo;

/**
 * @author LLM
 *
 */
public class VoiceEventPendingInfo extends CommonPendingHandleInfo {
    /** 原系统事件流水号 */
    private Long                              rmEventId;
    /** 舆情等级 */
    private String                            grade;
    /** 舆情事件标题 */
    private String                            title;
    /** 状态 */
    private String                            status;
    /** 创建事件 */
    private Date                              createTime;
    /** 创建人编号 */
    private Long                              createrId;
    /** 创建人姓名 */
    private String                            createrName;

    /** 舆情事件办理编号 */
    private Long                              rmEventHandleId;
    /** 舆情事件办理单位 */
    private Long                              unitId;
    /** 舆情事件办理状态 */
    private String                            eventHandleStatus;
    /** 舆情事件办理时间 */
    private Date                              eventHandleCreateTime;

    /** 舆情信息记录 */
    private List<VoiceInfoPendingInfo>        voiceInfos;
    /** 舆情审批记录 */
    private List<VoiceEventHandleApproveInfo> voiceEventHandleApproves;

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
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the createrId
     */
    public Long getCreaterId() {
        return createrId;
    }

    /**
     * @param createrId
     *            the createrId to set
     */
    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    /**
     * @return the createrName
     */
    public String getCreaterName() {
        return createrName;
    }

    /**
     * @param createrName
     *            the createrName to set
     */
    public void setCreaterName(String createrName) {
        this.createrName = createrName;
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

    /**
     * @return the eventHandleStatus
     */
    public String getEventHandleStatus() {
        return eventHandleStatus;
    }

    /**
     * @param eventHandleStatus
     *            the eventHandleStatus to set
     */
    public void setEventHandleStatus(String eventHandleStatus) {
        this.eventHandleStatus = eventHandleStatus;
    }

    /**
     * @return the eventHandleCreateTime
     */
    public Date getEventHandleCreateTime() {
        return eventHandleCreateTime;
    }

    /**
     * @param eventHandleCreateTime
     *            the eventHandleCreateTime to set
     */
    public void setEventHandleCreateTime(Date eventHandleCreateTime) {
        this.eventHandleCreateTime = eventHandleCreateTime;
    }

    /**
     * @return the voiceInfos
     */
    public List<VoiceInfoPendingInfo> getVoiceInfos() {
        return voiceInfos;
    }

    /**
     * @param voiceInfos
     *            the voiceInfos to set
     */
    public void setVoiceInfos(List<VoiceInfoPendingInfo> voiceInfos) {
        this.voiceInfos = voiceInfos;
    }

    /**
     * @return the voiceEventHandleApproves
     */
    public List<VoiceEventHandleApproveInfo> getVoiceEventHandleApproves() {
        return voiceEventHandleApproves;
    }

    /**
     * @param voiceEventHandleApproves
     *            the voiceEventHandleApproves to set
     */
    public void setVoiceEventHandleApproves(List<VoiceEventHandleApproveInfo> voiceEventHandleApproves) {
        this.voiceEventHandleApproves = voiceEventHandleApproves;
    }

}
