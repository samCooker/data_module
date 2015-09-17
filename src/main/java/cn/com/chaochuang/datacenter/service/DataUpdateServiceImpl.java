/*
 * FileName:    DataUpdateServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月27日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.datacenter.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.datacenter.domain.DataUpdate;
import cn.com.chaochuang.datacenter.reference.ExecuteFlag;
import cn.com.chaochuang.datacenter.reference.OperationType;
import cn.com.chaochuang.datacenter.reference.WorkType;
import cn.com.chaochuang.datacenter.repository.DataUpdateRepository;
import cn.com.chaochuang.webservice.server.ITransferOAService;

/**
 * @author LLM
 *
 */
@Service
@Transactional
public class DataUpdateServiceImpl extends SimpleLongIdCrudRestService<DataUpdate> implements DataUpdateService {
    @Autowired
    private DataUpdateRepository repository;
    /** webservice 函数库 */
    @Autowired
    private ITransferOAService   transferOAService;

    @Override
    public SimpleDomainRepository<DataUpdate, Long> getRepository() {
        return repository;
    }

    @Override
    public List<DataUpdate> selectDocFileDataUpdate(WorkType workType) {
        return repository.findByWorkTypeAndOperationTypeAndExecuteFlag(workType, OperationType.修改, ExecuteFlag.未执行);
    }

    @Override
    public List<DataUpdate> selectDfferentDataByWorkType(WorkType workType, Pageable page) {
        return repository.findByWorkTypeAndOperationTypeAndExecuteFlag(workType, OperationType.修改, ExecuteFlag.未执行,
                        page);
    }

    @Override
    public void saveErrorInfo(DataUpdate dataUpdate, String backInfo) {
        if (dataUpdate != null) {
            dataUpdate.setExecuteFlag(ExecuteFlag.执行错误);
            dataUpdate.setErrorInfo(backInfo);
            repository.save(dataUpdate);
        }
    }

}
