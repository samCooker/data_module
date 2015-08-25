/*
 * FileName:    SysSynchdataTaskService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年8月20日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.synchdata.service;

import java.util.List;

import cn.com.chaochuang.common.data.service.CrudRestService;
import cn.com.chaochuang.synchdata.domain.SysSynchdataTask;
import cn.com.chaochuang.synchdata.reference.SynchDataClearFlag;
import cn.com.chaochuang.synchdata.reference.SynchDataFlag;
import cn.com.chaochuang.synchdata.reference.SynchDataStatus;

/**
 * @author LLM
 *
 */
public interface SysSynchdataTaskService extends CrudRestService<SysSynchdataTask, Long> {

    /**
     * 根据同步任务的状态查询
     *
     * @param status
     *            任务的状态
     * @return
     */
    List<SysSynchdataTask> selectSynchdataTask(SynchDataStatus status);

    /**
     * 保存数据同步任务记录
     *
     * @param synchDataFlag
     *            任务标识
     * @param clearFlag
     *            是否清除原数据
     */
    void saveSynchdataTask(SynchDataFlag synchDataFlag, SynchDataClearFlag clearFlag);
}
