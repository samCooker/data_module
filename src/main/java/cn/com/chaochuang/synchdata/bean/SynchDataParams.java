/*
 * FileName:    SynchDataParams.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年12月23日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.synchdata.bean;

import cn.com.chaochuang.synchdata.reference.SynchDataClearFlag;
import cn.com.chaochuang.synchdata.reference.SynchDataFlag;

/**
 * @author Shicx
 *
 */
public class SynchDataParams {

    /** 同步类型 */
    private SynchDataFlag      synchDataFlag;
    /** 同步状态 */
    private SynchDataClearFlag clearFlag;

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

}
