/*
 * FileName:    SysSynchdataTaskRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年8月20日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.synchdata.repository;

import java.util.List;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.synchdata.domain.SysSynchdataTask;
import cn.com.chaochuang.synchdata.reference.SynchDataStatus;

/**
 * @author LLM
 *
 */
public interface SysSynchdataTaskRepository extends SimpleDomainRepository<SysSynchdataTask, Long> {
    /**
     * 根据同步任务的状态查询
     *
     * @param status
     *            任务的状态
     * @return
     */
    List<SysSynchdataTask> findByStatus(SynchDataStatus status);
}
