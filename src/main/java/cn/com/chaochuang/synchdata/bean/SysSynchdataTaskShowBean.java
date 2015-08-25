/*
 * FileName:    SysSynchdataTaskShowBean.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年8月25日 (lub) 1.0 Create
 */

package cn.com.chaochuang.synchdata.bean;

import java.util.Date;

import javax.persistence.Convert;

import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.synchdata.reference.SynchDataClearFlag;
import cn.com.chaochuang.synchdata.reference.SynchDataClearFlagConverter;
import cn.com.chaochuang.synchdata.reference.SynchDataFlag;
import cn.com.chaochuang.synchdata.reference.SynchDataFlagConverter;
import cn.com.chaochuang.synchdata.reference.SynchDataStatus;
import cn.com.chaochuang.synchdata.reference.SynchDataStatusConverter;

/**
 * @author lub
 *
 */
public class SysSynchdataTaskShowBean {
    /** 同步表标志 */
    @Convert(converter = SynchDataFlagConverter.class)
    private SynchDataFlag      synchDataFlag;
    /** 需完成同步记录数 */
    private Long               needSynch;
    /** 已经完成同步记录数 */
    private Long               finishSynch;
    /** 同步开始时间 */
    private Date               beginTime;
    private String             beginTimeFormat;
    /** 同步完成时间 */
    private Date               finishTime;
    private String             finishTimeFormat;
    /** 状态 */
    @Convert(converter = SynchDataStatusConverter.class)
    private SynchDataStatus    status;
    /** 清空数据 */
    @Convert(converter = SynchDataClearFlagConverter.class)
    private SynchDataClearFlag clearFlag;
    /** 备注 */
    private String             memo;

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
     * @return the beginTimeFormat
     */
    public String getBeginTimeFormat() {
        return (beginTime != null) ? Tools.DATE_MINUTE_FORMAT.format(beginTime) : "";
    }

    /**
     * @param beginTimeFormat
     *            the beginTimeFormat to set
     */
    public void setBeginTimeFormat(String beginTimeFormat) {
        this.beginTimeFormat = beginTimeFormat;
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
     * @return the finishTimeFormat
     */
    public String getFinishTimeFormat() {
        return (finishTime != null) ? Tools.DATE_MINUTE_FORMAT.format(finishTime) : "";
    }

    /**
     * @param finishTimeFormat
     *            the finishTimeFormat to set
     */
    public void setFinishTimeFormat(String finishTimeFormat) {
        this.finishTimeFormat = finishTimeFormat;
    }

    /**
     * @return the status
     */
    public SynchDataStatus getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(SynchDataStatus status) {
        this.status = status;
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

}
