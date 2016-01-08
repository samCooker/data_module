/*
 * FileName:    VoiceInfo.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月11日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.voice.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;

import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.aipcase.reference.LocalDataConverter;
import cn.com.chaochuang.common.data.domain.LongIdEntity;

/**
 * @author LLM
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "info_id") ) })
public class VoiceInfo extends LongIdEntity {
    /** 原系统流水号 */
    private Long      rmInfoId;
    /** 规则编号 */
    private Long      ruleId;
    /** 规则名称 */
    private String    ruleName;
    /** 是否首页 */
    private String    voiceInfoIsHome;
    /** 舆情标题 */
    private String    voiceInfoTitle;
    /** 舆情内容 */
    private String    voiceInfoContent;
    /** 舆情来源名 */
    private String    voiceInfoSource;
    /** 舆情来源URL */
    private String    voiceInfoSourceUrl;
    /** 舆情作者 */
    private String    voiceInfoAuthor;
    /** 舆情发布时间 */
    private Date      voiceInfoIssueTime;
    /** 舆情状态 */
    private String    voiceInfoStatus;
    /** 舆情收录时间 */
    private Date      voiceInfoDiscoverTime;
    /** 舆情收录人 */
    private Long      voiceInfoDiscoverUser;
    /** 舆情性质 */
    private String    voiceInfoNature;
    /** 是否本地舆情 */
    private String    voiceInfoIsLocal;
    /** 媒体类型 */
    private String    metaType;
    /** 转载量 */
    private String    transmitCount;
    /** 点击量 */
    private String    clickCount;
    /** 是否敏感 */
    private String    voiceInfoIsSensitive;
    /** 舆情附件标识 */
    private String    rmAffixId;
    /** 内容区域标识 */
    private String    contentArea;
    /** 本地数据标识 0：非本地数据（默认）1：有本地数据 */
    @Convert(converter = LocalDataConverter.class)
    private LocalData localData;
    /** 所属单位编号 */
    private Long      unitOrgId;

    /**
     * @return the rmInfoId
     */
    public Long getRmInfoId() {
        return rmInfoId;
    }

    /**
     * @param rmInfoId
     *            the rmInfoId to set
     */
    public void setRmInfoId(Long rmInfoId) {
        this.rmInfoId = rmInfoId;
    }

    /**
     * @return the ruleName
     */
    public String getRuleName() {
        return ruleName;
    }

    /**
     * @param ruleName
     *            the ruleName to set
     */
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    /**
     * @return the voiceInfoIsHome
     */
    public String getVoiceInfoIsHome() {
        return voiceInfoIsHome;
    }

    /**
     * @param voiceInfoIsHome
     *            the voiceInfoIsHome to set
     */
    public void setVoiceInfoIsHome(String voiceInfoIsHome) {
        this.voiceInfoIsHome = voiceInfoIsHome;
    }

    /**
     * @return the voiceInfoTitle
     */
    public String getVoiceInfoTitle() {
        return voiceInfoTitle;
    }

    /**
     * @param voiceInfoTitle
     *            the voiceInfoTitle to set
     */
    public void setVoiceInfoTitle(String voiceInfoTitle) {
        this.voiceInfoTitle = voiceInfoTitle;
    }

    /**
     * @return the voiceInfoContent
     */
    public String getVoiceInfoContent() {
        return voiceInfoContent;
    }

    /**
     * @param voiceInfoContent
     *            the voiceInfoContent to set
     */
    public void setVoiceInfoContent(String voiceInfoContent) {
        this.voiceInfoContent = voiceInfoContent;
    }

    /**
     * @return the voiceInfoSource
     */
    public String getVoiceInfoSource() {
        return voiceInfoSource;
    }

    /**
     * @param voiceInfoSource
     *            the voiceInfoSource to set
     */
    public void setVoiceInfoSource(String voiceInfoSource) {
        this.voiceInfoSource = voiceInfoSource;
    }

    /**
     * @return the voiceInfoSourceUrl
     */
    public String getVoiceInfoSourceUrl() {
        return voiceInfoSourceUrl;
    }

    /**
     * @param voiceInfoSourceUrl
     *            the voiceInfoSourceUrl to set
     */
    public void setVoiceInfoSourceUrl(String voiceInfoSourceUrl) {
        this.voiceInfoSourceUrl = voiceInfoSourceUrl;
    }

    /**
     * @return the voiceInfoAuthor
     */
    public String getVoiceInfoAuthor() {
        return voiceInfoAuthor;
    }

    /**
     * @param voiceInfoAuthor
     *            the voiceInfoAuthor to set
     */
    public void setVoiceInfoAuthor(String voiceInfoAuthor) {
        this.voiceInfoAuthor = voiceInfoAuthor;
    }

    /**
     * @return the voiceInfoIssueTime
     */
    public Date getVoiceInfoIssueTime() {
        return voiceInfoIssueTime;
    }

    /**
     * @param voiceInfoIssueTime
     *            the voiceInfoIssueTime to set
     */
    public void setVoiceInfoIssueTime(Date voiceInfoIssueTime) {
        this.voiceInfoIssueTime = voiceInfoIssueTime;
    }

    /**
     * @return the voiceInfoStatus
     */
    public String getVoiceInfoStatus() {
        return voiceInfoStatus;
    }

    /**
     * @param voiceInfoStatus
     *            the voiceInfoStatus to set
     */
    public void setVoiceInfoStatus(String voiceInfoStatus) {
        this.voiceInfoStatus = voiceInfoStatus;
    }

    /**
     * @return the voiceInfoDiscoverTime
     */
    public Date getVoiceInfoDiscoverTime() {
        return voiceInfoDiscoverTime;
    }

    /**
     * @param voiceInfoDiscoverTime
     *            the voiceInfoDiscoverTime to set
     */
    public void setVoiceInfoDiscoverTime(Date voiceInfoDiscoverTime) {
        this.voiceInfoDiscoverTime = voiceInfoDiscoverTime;
    }

    /**
     * @return the voiceInfoDiscoverUser
     */
    public Long getVoiceInfoDiscoverUser() {
        return voiceInfoDiscoverUser;
    }

    /**
     * @param voiceInfoDiscoverUser
     *            the voiceInfoDiscoverUser to set
     */
    public void setVoiceInfoDiscoverUser(Long voiceInfoDiscoverUser) {
        this.voiceInfoDiscoverUser = voiceInfoDiscoverUser;
    }

    /**
     * @return the voiceInfoNature
     */
    public String getVoiceInfoNature() {
        return voiceInfoNature;
    }

    /**
     * @param voiceInfoNature
     *            the voiceInfoNature to set
     */
    public void setVoiceInfoNature(String voiceInfoNature) {
        this.voiceInfoNature = voiceInfoNature;
    }

    /**
     * @return the voiceInfoIsLocal
     */
    public String getVoiceInfoIsLocal() {
        return voiceInfoIsLocal;
    }

    /**
     * @param voiceInfoIsLocal
     *            the voiceInfoIsLocal to set
     */
    public void setVoiceInfoIsLocal(String voiceInfoIsLocal) {
        this.voiceInfoIsLocal = voiceInfoIsLocal;
    }

    /**
     * @return the metaType
     */
    public String getMetaType() {
        return metaType;
    }

    /**
     * @param metaType
     *            the metaType to set
     */
    public void setMetaType(String metaType) {
        this.metaType = metaType;
    }

    /**
     * @return the voiceInfoIsSensitive
     */
    public String getVoiceInfoIsSensitive() {
        return voiceInfoIsSensitive;
    }

    /**
     * @param voiceInfoIsSensitive
     *            the voiceInfoIsSensitive to set
     */
    public void setVoiceInfoIsSensitive(String voiceInfoIsSensitive) {
        this.voiceInfoIsSensitive = voiceInfoIsSensitive;
    }

    public String getTransmitCount() {
        return transmitCount;
    }

    public void setTransmitCount(String transmitCount) {
        this.transmitCount = transmitCount;
    }

    public String getClickCount() {
        return clickCount;
    }

    public void setClickCount(String clickCount) {
        this.clickCount = clickCount;
    }

    /**
     * @return the rmAffixId
     */
    public String getRmAffixId() {
        return rmAffixId;
    }

    /**
     * @param rmAffixId
     *            the rmAffixId to set
     */
    public void setRmAffixId(String rmAffixId) {
        this.rmAffixId = rmAffixId;
    }

    /**
     * @return the contentArea
     */
    public String getContentArea() {
        return contentArea;
    }

    /**
     * @param contentArea
     *            the contentArea to set
     */
    public void setContentArea(String contentArea) {
        this.contentArea = contentArea;
    }

    /**
     * @return the localData
     */
    public LocalData getLocalData() {
        return localData;
    }

    /**
     * @param localData
     *            the localData to set
     */
    public void setLocalData(LocalData localData) {
        this.localData = localData;
    }

    /**
     * @return the ruleId
     */
    public Long getRuleId() {
        return ruleId;
    }

    /**
     * @param ruleId
     *            the ruleId to set
     */
    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    /**
     * @return the unitOrgId
     */
    public Long getUnitOrgId() {
        return unitOrgId;
    }

    /**
     * @param unitOrgId
     *            the unitOrgId to set
     */
    public void setUnitOrgId(Long unitOrgId) {
        this.unitOrgId = unitOrgId;
    }

}
