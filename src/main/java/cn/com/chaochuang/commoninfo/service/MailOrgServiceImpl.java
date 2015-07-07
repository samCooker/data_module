/*
 * FileName:    DepLinkmanServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月26日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.commoninfo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.commoninfo.domain.MailOrg;
import cn.com.chaochuang.commoninfo.repository.MailOrgRepository;

/**
 * @author LJX
 *
 */
@Service
@Transactional
public class MailOrgServiceImpl extends SimpleLongIdCrudRestService<MailOrg> implements MailOrgService {
    @Autowired
    private MailOrgRepository repository;

    @Override
    public SimpleDomainRepository<MailOrg, Long> getRepository() {
        return repository;
    }

}
