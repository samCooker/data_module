/*
 * FileName:    VoiceEvent.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月13日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.voice.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.aipcase.reference.LocalDataConverter;
import cn.com.chaochuang.common.data.domain.LongIdEntity;

/**
 * @author LLM
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "event_id") ) })
public class VoiceEvent extends LongIdEntity {
    /** 原系统事件流水号 */
    private Long      rmEventId;
    /** 舆情等级 */
    private String    grade;
    /** 舆情事件标题 */
    private String    title;
    /** 状态 */
    private String    status;
    /** 创建事件 */
    private Date      createTime;
    /** 创建人编号 */
    private Long      createrId;
    /** 创建人姓名 */
    private String    createrName;
    /***/
    private Long      unitOrgId;
    /***/
    private String    voiceInfoTitle;
    /***/
    private String    voiceInfoSource;
    /***/
    private String    voiceInfoSourceUrl;
    /***/
    private String    voiceInfoAuthor;
    /***/
    @Temporal(TemporalType.TIMESTAMP)
    private Date      voiceInfoIssueTime;
    /***/
    @Temporal(TemporalType.TIMESTAMP)
    private Date      voiceInfoDiscoverTime;
    /***/
    private Long      voiceInfoDiscoverUser;
    /***/
    private String    voiceInfoNature;
    /***/
    private String    voiceInfoContent;
    /***/
    private Long      rmInfoId;
    /** 是否本地新增数据 */
    @Convert(converter = LocalDataConverter.class)
    private LocalData localNewData;

    public Long getUnitOrgId() {
        return unitOrgId;
    }

    public void setUnitOrgId(Long unitOrgId) {
        this.unitOrgId = unitOrgId;
    }

    public String getVoiceInfoTitle() {
        return voiceInfoTitle;
    }

    public void setVoiceInfoTitle(String voiceInfoTitle) {
        this.voiceInfoTitle = voiceInfoTitle;
    }

    public String getVoiceInfoSource() {
        return voiceInfoSource;
    }

    public void setVoiceInfoSource(String voiceInfoSource) {
        this.voiceInfoSource = voiceInfoSource;
    }

    public String getVoiceInfoSourceUrl() {
        return voiceInfoSourceUrl;
    }

    public void setVoiceInfoSourceUrl(String voiceInfoSourceUrl) {
        this.voiceInfoSourceUrl = voiceInfoSourceUrl;
    }

    public String getVoiceInfoAuthor() {
        return voiceInfoAuthor;
    }

    public void setVoiceInfoAuthor(String voiceInfoAuthor) {
        this.voiceInfoAuthor = voiceInfoAuthor;
    }

    public Date getVoiceInfoIssueTime() {
        return voiceInfoIssueTime;
    }

    public void setVoiceInfoIssueTime(Date voiceInfoIssueTime) {
        this.voiceInfoIssueTime = voiceInfoIssueTime;
    }

    public Date getVoiceInfoDiscoverTime() {
        return voiceInfoDiscoverTime;
    }

    public void setVoiceInfoDiscoverTime(Date voiceInfoDiscoverTime) {
        this.voiceInfoDiscoverTime = voiceInfoDiscoverTime;
    }

    public Long getVoiceInfoDiscoverUser() {
        return voiceInfoDiscoverUser;
    }

    public void setVoiceInfoDiscoverUser(Long voiceInfoDiscoverUser) {
        this.voiceInfoDiscoverUser = voiceInfoDiscoverUser;
    }

    public String getVoiceInfoNature() {
        return voiceInfoNature;
    }

    public void setVoiceInfoNature(String voiceInfoNature) {
        this.voiceInfoNature = voiceInfoNature;
    }

    public String getVoiceInfoContent() {
        return voiceInfoContent;
    }

    public void setVoiceInfoContent(String voiceInfoContent) {
        this.voiceInfoContent = voiceInfoContent;
    }

    public Long getRmInfoId() {
        return rmInfoId;
    }

    public void setRmInfoId(Long rmInfoId) {
        this.rmInfoId = rmInfoId;
    }

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
     * @return the localNewData
     */
    public LocalData getLocalNewData() {
        return localNewData;
    }

    /**
     * @param localNewData
     *            the localNewData to set
     */
    public void setLocalNewData(LocalData localNewData) {
        this.localNewData = localNewData;
    }

}
