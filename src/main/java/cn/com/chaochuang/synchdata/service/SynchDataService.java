/*
 * FileName:    SynchDataService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年8月14日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.synchdata.service;

import cn.com.chaochuang.synchdata.domain.SysSynchdataTask;

/**
 * @author LLM
 *
 */
public interface SynchDataService {
    /**
     * 同步行政相对人库的信息
     *
     * @param task
     */
    void synchAppEntpData(SysSynchdataTask task);

    /**
     * 同步舆情信息
     *
     * @param task
     */
    void synchVoiceInfoData(SysSynchdataTask task);

    /**
     * 同步行政审批待办
     *
     * @param task
     */
    void synchSuperviseFdData(SysSynchdataTask task);

    /**
     * 同步审评查验待办
     *
     * @param task
     */
    void synchAuditFdData(SysSynchdataTask task);

    /**
     * 同步用户数据
     *
     * @param task
     */
    void synchSysDataChangeOfUserData(SysSynchdataTask task);

    /**
     * 同步部门数据
     * 
     * @param task
     */
    void synchSysDataChangeOfDeptData(SysSynchdataTask task);
}
