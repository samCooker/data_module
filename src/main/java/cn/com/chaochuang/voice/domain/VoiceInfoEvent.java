/*
 * FileName:    VoiceInfoEvent.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月15日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.voice.domain;

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
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "voice_info_event_id")) })
public class VoiceInfoEvent extends LongIdEntity {
    /** 原系统事件流水号 */
    private Long rmVoiceEventId;
    /** 原系统舆情流水号 */
    private Long rmVoiceInfoId;

    /**
     * 构造函数
     */
    public VoiceInfoEvent() {
    }

    /**
     * 构造函数
     * 
     * @param rmVoiceEventId
     * @param rmVoiceInfoId
     */
    public VoiceInfoEvent(Long rmVoiceEventId, Long rmVoiceInfoId) {
        this.rmVoiceEventId = rmVoiceEventId;
        this.rmVoiceInfoId = rmVoiceInfoId;
    }

    /**
     * @return the rmVoiceEventId
     */
    public Long getRmVoiceEventId() {
        return rmVoiceEventId;
    }

    /**
     * @param rmVoiceEventId
     *            the rmVoiceEventId to set
     */
    public void setRmVoiceEventId(Long rmVoiceEventId) {
        this.rmVoiceEventId = rmVoiceEventId;
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

}
