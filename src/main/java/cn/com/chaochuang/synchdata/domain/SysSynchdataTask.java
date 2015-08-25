/*
 * FileName:    SysSynchdataTask.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年8月20日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.synchdata.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.com.chaochuang.common.data.domain.LongIdEntity;
import cn.com.chaochuang.synchdata.reference.SynchDataClearFlag;
import cn.com.chaochuang.synchdata.reference.SynchDataClearFlagConverter;
import cn.com.chaochuang.synchdata.reference.SynchDataFlag;
import cn.com.chaochuang.synchdata.reference.SynchDataFlagConverter;
import cn.com.chaochuang.synchdata.reference.SynchDataStatus;
import cn.com.chaochuang.synchdata.reference.SynchDataStatusConverter;

/**
 * @author LLM
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "synchdata_task_id")) })
public class SysSynchdataTask extends LongIdEntity {
    /** 同步表标志 */
    @Convert(converter = SynchDataFlagConverter.class)
    private SynchDataFlag      synchDataFlag;
    /** 需完成同步记录数 */
    private Long               needSynch;
    /** 已经完成同步记录数 */
    private Long               finishSynch;
    /** 同步开始时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date               beginTime;
    /** 同步完成时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date               finishTime;
    /** 状态 */
    @Convert(converter = SynchDataStatusConverter.class)
    private SynchDataStatus    status;
    /** 清空数据 */
    @Convert(converter = SynchDataClearFlagConverter.class)
    private SynchDataClearFlag clearFlag;
    /** 备注 */
    private String             memo;

    /**
     * 构造函数
     *
     * @param synchDataFlag
     * @param clearFlag
     */
    public SysSynchdataTask(SynchDataFlag synchDataFlag, SynchDataClearFlag clearFlag) {
        this.synchDataFlag = synchDataFlag;
        this.clearFlag = clearFlag;
        this.status = SynchDataStatus.未同步;
    }

    /**
     * 构造函数
     */
    public SysSynchdataTask() {
        super();
    }

    /**
     * @return the synchDataFlag
     */
    public SynchDataFlag getSynchDataFlag() {
        return synchDataFlag;
    }

    /**
     * @param synchDataFlag
     *            the synchDataFlag to set
     */
    public void setSynchDataFlag(SynchDataFlag synchDataFlag) {
        this.synchDataFlag = synchDataFlag;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(SynchDataStatus status) {
        this.status = status;
    }

    /**
     * @return the needSynch
     */
    public Long getNeedSynch() {
        return needSynch;
    }

    /**
     * @param needSynch
     *            the needSynch to set
     */
    public void setNeedSynch(Long needSynch) {
        this.needSynch = needSynch;
    }

    /**
     * @return the finishSynch
     */
    public Long getFinishSynch() {
        return finishSynch;
    }

    /**
     * @param finishSynch
     *            the finishSynch to set
     */
    public void setFinishSynch(Long finishSynch) {
        this.finishSynch = finishSynch;
    }

    /**
     * @return the beginTime
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * @param beginTime
     *            the beginTime to set
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * @return the finishTime
     */
    public Date getFinishTime() {
        return finishTime;
    }

    /**
     * @param finishTime
     *            the finishTime to set
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * @return the memo
     */
    public String getMemo() {
        return memo;
    }

    /**
     * @param memo
     *            the memo to set
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * @return the clearFlag
     */
    public SynchDataClearFlag getClearFlag() {
        return clearFlag;
    }

    /**
     * @param clearFlag
     *            the clearFlag to set
     */
    public void setClearFlag(SynchDataClearFlag clearFlag) {
        this.clearFlag = clearFlag;
    }

    /**
     * @return the status
     */
    public SynchDataStatus getStatus() {
        return status;
    }

}
