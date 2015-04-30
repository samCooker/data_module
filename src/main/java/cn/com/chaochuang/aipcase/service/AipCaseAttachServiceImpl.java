/*
 * FileName:    DocFileServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月30日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.aipcase.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.aipcase.domain.AipCaseAttach;
import cn.com.chaochuang.aipcase.repository.AipCaseAttachRepository;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;

/**
 * @author LJX
 *
 */
@Service
@Transactional
public class AipCaseAttachServiceImpl extends SimpleLongIdCrudRestService<AipCaseAttach> implements
                AipCaseAttachService {

    @Autowired
    private AipCaseAttachRepository repository;

    @Override
    public SimpleDomainRepository<AipCaseAttach, Long> getRepository() {
        return repository;
    }

}
