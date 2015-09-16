/*
 * FileName:    VoiceEventHandleOpinion.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月16日 (LLM) 1.0 Create
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
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "event_handle_opinion_id")) })
public class VoiceEventHandleOpinion extends LongIdEntity {
    /** 原系统审批流水号 */
    private Long   rmOpinionId;
    /** 原系统审批意见编号 */
    private Long   rmHandleApproveId;
    /** 原系统审批编号 */
    private Long   rmEventHandleId;
    /** 办理意见 */
    private String handleApprove;
    /** 办理人姓名 */
    private String handleName;
    /** 创建日期 */
    private Date   createTime;

    /**
     * @return the rmOpinionId
     */
    public Long getRmOpinionId() {
        return rmOpinionId;
    }

    /**
     * @param rmOpinionId
     *            the rmOpinionId to set
     */
    public void setRmOpinionId(Long rmOpinionId) {
        this.rmOpinionId = rmOpinionId;
    }

    /**
     * @return the handleName
     */
    public String getHandleName() {
        return handleName;
    }

    /**
     * @param handleName
     *            the handleName to set
     */
    public void setHandleName(String handleName) {
        this.handleName = handleName;
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
     * @return the handleApprove
     */
    public String getHandleApprove() {
        return handleApprove;
    }

    /**
     * @param handleApprove
     *            the handleApprove to set
     */
    public void setHandleApprove(String handleApprove) {
        this.handleApprove = handleApprove;
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
