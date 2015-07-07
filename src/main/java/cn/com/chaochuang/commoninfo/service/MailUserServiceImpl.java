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
import cn.com.chaochuang.common.user.repository.SysUserRepository;
import cn.com.chaochuang.commoninfo.domain.MailUser;
import cn.com.chaochuang.commoninfo.repository.MailUserRepository;

/**
 * @author LJX
 *
 */
@Service
@Transactional
public class MailUserServiceImpl extends SimpleLongIdCrudRestService<MailUser> implements MailUserService {
    @Autowired
    private MailUserRepository repository;

    @Autowired
    private SysUserRepository  sysUserRepository;

    @Override
    public SimpleDomainRepository<MailUser, Long> getRepository() {
        return repository;
    }

    /**
     * @see cn.com.chaochuang.commoninfo.service.MailUserService#selectUserByBindId(java.lang.Long)
     */
    @Override
    public MailUser selectUserByBindId(Long bindUserId) {
        return this.repository.findByBindUserId(bindUserId);
    }

    /**
     * @see cn.com.chaochuang.commoninfo.service.MailUserService#saveUser(cn.com.chaochuang.commoninfo.domain.MailUser)
     */
    @Override
    public void saveUser(MailUser info) {
        MailUser mailUser = new MailUser();
        if (info.getId() != null) {
            mailUser = this.repository.findOne(info.getId());
        }
        mailUser.setAccount(info.getAccount());
        mailUser.setDomain(info.getDomain());
        mailUser.setOrgCode(info.getOrgCode());
        mailUser.setOrgName(info.getOrgName());
        this.repository.save(mailUser);
    }

}
