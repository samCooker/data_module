/*
 * FileName:    VoiceAlarmRecord.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月21日 (LLM) 1.0 Create
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

import cn.com.chaochuang.common.data.domain.LongIdEntity;
import cn.com.chaochuang.voice.reference.AlarmPushFlag;
import cn.com.chaochuang.voice.reference.AlarmPushFlagConverter;

/**
 * @author LLM
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "alarm_record_id")) })
public class VoiceAlarmRecord extends LongIdEntity {
    /** 消息推送内容 */
    private String        alarmContent;
    /** 提醒用户编号 */
    private Long          alarmUserId;
    /** 原系统信息提醒编号 */
    private Long          rmAlarmRecordId;
    /** 原系统舆情信息编号 */
    private Long          rmVoiceInfoId;
    /** 记录日期 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date          recordTime;
    /** 推送标志 */
    @Convert(converter = AlarmPushFlagConverter.class)
    private AlarmPushFlag pushFlag;
    /** 推送时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date          pushTime;

    /**
     * @return the alarmContent
     */
    public String getAlarmContent() {
        return alarmContent;
    }

    /**
     * @param alarmContent
     *            the alarmContent to set
     */
    public void setAlarmContent(String alarmContent) {
        this.alarmContent = alarmContent;
    }

    /**
     * @return the alarmUserId
     */
    public Long getAlarmUserId() {
        return alarmUserId;
    }

    /**
     * @param alarmUserId
     *            the alarmUserId to set
     */
    public void setAlarmUserId(Long alarmUserId) {
        this.alarmUserId = alarmUserId;
    }

    /**
     * @return the rmAlarmRecordId
     */
    public Long getRmAlarmRecordId() {
        return rmAlarmRecordId;
    }

    /**
     * @param rmAlarmRecordId
     *            the rmAlarmRecordId to set
     */
    public void setRmAlarmRecordId(Long rmAlarmRecordId) {
        this.rmAlarmRecordId = rmAlarmRecordId;
    }

    /**
     * @return the rmVoiceInfoId
     */
    public Long getRmVoiceInfoId() {
        return rmVoiceInfoId;
    }

    /**
     * @param rmVoiceInfoId
     *            the rmVoiceInfoId to set
     */
    public void setRmVoiceInfoId(Long rmVoiceInfoId) {
        this.rmVoiceInfoId = rmVoiceInfoId;
    }

    /**
     * @return the recordTime
     */
    public Date getRecordTime() {
        return recordTime;
    }

    /**
     * @param recordTime
     *            the recordTime to set
     */
    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    /**
     * @return the pushFlag
     */
    public AlarmPushFlag getPushFlag() {
        return pushFlag;
    }

    /**
     * @param pushFlag
     *            the pushFlag to set
     */
    public void setPushFlag(AlarmPushFlag pushFlag) {
        this.pushFlag = pushFlag;
    }

    /**
     * @return the pushTime
     */
    public Date getPushTime() {
        return pushTime;
    }

    /**
     * @param pushTime
     *            the pushTime to set
     */
    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

}
