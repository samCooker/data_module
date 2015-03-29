/*
 * FileName:    DataUpdateServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月27日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.datacenter.service;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.datacenter.domain.DataUpdate;
import cn.com.chaochuang.datacenter.repository.DataUpdateRepository;

/**
 * @author LLM
 *
 */
public class DataUpdateServiceImpl extends SimpleLongIdCrudRestService<DataUpdate> implements DataUpdateService {
    @Autowired
    private DataUpdateRepository repository;

    @Override
    public SimpleDomainRepository<DataUpdate, Long> getRepository() {
        return repository;
    }
}
