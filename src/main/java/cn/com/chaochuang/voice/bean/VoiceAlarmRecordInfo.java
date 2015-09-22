/*
 * FileName:    VoiceAlarmRecordInfo.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月21日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.voice.bean;

import java.util.Date;

/**
 * @author LLM
 *
 */
public class VoiceAlarmRecordInfo {
    /** 原系统信息提醒编号 */
    private Long   rmAlarmRecordId;
    /** 原系统舆情信息编号 */
    private Long   rmVoiceInfoId;
    /** 消息推送内容 */
    private String alarmContent;
    /** 提醒用户编号 */
    private Long   alarmUserId;
    /** 原系统提醒时间 */
    private Date   recordTime;

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

}
