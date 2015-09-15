/*
 * FileName:    VoiceEventHandle.java
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
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "event_handle_id")) })
public class VoiceEventHandle extends LongIdEntity {
    /** 舆情事件编号 */
    private Long   rmEventId;
    /** 舆情处理编号 */
    private Long   rmEventHandleId;
    /** 单位编号 */
    private Long   unitId;
    /** 状态 */
    private String status;
    /** 创建时间 */
    private Date   createTime;

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
}
