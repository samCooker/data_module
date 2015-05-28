/*
 * FileName:    PendingCommandInfo.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月29日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.task.bean;

import cn.com.chaochuang.common.fdfordo.bean.CommonPendingHandleInfo;

/**
 * @author LLM
 *
 */
public class OAPendingHandleInfo extends CommonPendingHandleInfo {
    /** 远程系统待办明细编号 */
    private String rmPendingItemId;
    /** 密级 */
    private String secretLevel;

    /**
     * @return the rmPendingItemId
     */
    public String getRmPendingItemId() {
        return rmPendingItemId;
    }

    /**
     * @param rmPendingItemId
     *            the rmPendingItemId to set
     */
    public void setRmPendingItemId(String rmPendingItemId) {
        this.rmPendingItemId = rmPendingItemId;
    }

    /**
     * @return the secretLevel
     */
    public String getSecretLevel() {
        return secretLevel;
    }

    /**
     * @param secretLevel
     *            the secretLevel to set
     */
    public void setSecretLevel(String secretLevel) {
        this.secretLevel = secretLevel;
    }

}
