/*
 * FileName:    SysSynchdataTaskServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年8月20日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.synchdata.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.synchdata.bean.SynchDataParams;
import cn.com.chaochuang.synchdata.domain.SysSynchdataTask;
import cn.com.chaochuang.synchdata.reference.SynchDataStatus;
import cn.com.chaochuang.synchdata.repository.SysSynchdataTaskRepository;

/**
 * @author LLM
 *
 */
@Service
@Transactional
public class SysSynchdataTaskServiceImpl extends SimpleLongIdCrudRestService<SysSynchdataTask> implements
                SysSynchdataTaskService {

    @Autowired
    private SysSynchdataTaskRepository repository;

    @Override
    public SimpleDomainRepository<SysSynchdataTask, Long> getRepository() {
        return repository;
    }

    /**
     * @see cn.com.chaochuang.synchdata.service.SysSynchdataTaskService#selectSynchdataTask(cn.com.chaochuang.synchdata.reference.SynchDataStatus)
     */
    @Override
    public List<SysSynchdataTask> selectSynchdataTask(SynchDataStatus status) {
        return this.repository.findByStatus(status);
    }

    /**
     * @see cn.com.chaochuang.synchdata.service.SysSynchdataTaskService#saveSynchdataTask(cn.com.chaochuang.synchdata.reference.SynchDataFlag,
     *      cn.com.chaochuang.synchdata.reference.SynchDataClearFlag)
     */
    @Override
    public void saveSynchdataTask(SynchDataParams params) {
        SysSynchdataTask task = new SysSynchdataTask(params.getSynchDataFlag(), params.getClearFlag());
        this.repository.save(task);
    }

}
