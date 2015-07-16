/*
 * FileName:    AipCasePendingHandleInfo.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年7月6日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.aipcase.bean;

import cn.com.chaochuang.common.fdfordo.bean.CommonPendingHandleInfo;

/**
 * @author LLM
 *
 */
public class AipCasePendingHandleInfo extends CommonPendingHandleInfo {
    /** 环节状态 */
    private String caseStatus;
    /** 原系统案件基本编号 */
    private Long   caseApplyId;

    /**
     * @return the caseStatus
     */
    public String getCaseStatus() {
        return caseStatus;
    }

    /**
     * @param caseStatus
     *            the caseStatus to set
     */
    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    /**
     * @return the caseApplyId
     */
    public Long getCaseApplyId() {
        return caseApplyId;
    }

    /**
     * @param caseApplyId
     *            the caseApplyId to set
     */
    public void setCaseApplyId(Long caseApplyId) {
        this.caseApplyId = caseApplyId;
    }

}
