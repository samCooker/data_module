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

import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.aipcase.reference.LocalDataConverter;
import cn.com.chaochuang.common.data.domain.LongIdEntity;
import cn.com.chaochuang.voice.reference.VoiceEventStatus;
import cn.com.chaochuang.voice.reference.VoiceEventStatusConverter;

/**
 * @author LLM
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "event_id")) })
public class VoiceEvent extends LongIdEntity {
    /** 原系统事件流水号 */
    private Long             rmEventId;
    /** 舆情等级 */
    private String           grade;
    /** 舆情事件标题 */
    private String           title;
    /** 状态 */
    @Convert(converter = VoiceEventStatusConverter.class)
    private VoiceEventStatus status;
    /** 创建事件 */
    private Date             createTime;
    /** 创建人编号 */
    private Long             createrId;
    /** 创建人姓名 */
    private String           createrName;
    /** 是否本地新增数据 */
    @Convert(converter = LocalDataConverter.class)
    private LocalData        localNewData;

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
    public VoiceEventStatus getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(VoiceEventStatus status) {
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
