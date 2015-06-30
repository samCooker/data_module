/*
 * FileName:    SysRegisterApplyServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年6月29日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.registerapply.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.user.domain.SysUser;
import cn.com.chaochuang.common.user.repository.SysUserRepository;
import cn.com.chaochuang.registerapply.domain.SysRegisterApply;
import cn.com.chaochuang.registerapply.reference.AppAuthStatus;
import cn.com.chaochuang.registerapply.repository.SysRegisterApplyRepository;

/**
 * @author Shicx
 *
 */
@Service
@Transactional
public class SysRegisterApplyServiceImpl extends SimpleLongIdCrudRestService<SysRegisterApply> implements
                SysRegisterApplyService {

    @Autowired
    private SysRegisterApplyRepository repository;
    @Autowired
    private SysUserRepository          userRepository;

    @Override
    public SimpleDomainRepository<SysRegisterApply, Long> getRepository() {
        return repository;
    }

    /**
     * (non-Javadoc)
     *
     * @see cn.com.chaochuang.common.user.service.SysUserService#toRegisterAppAuthority(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public boolean toRegisterAppAuthority(Long userId, String imeiCode) {
        if (userId == null || StringUtils.isEmpty(imeiCode)) {
            return false;
        }
        SysUser user = userRepository.findOne(userId);
        if (user == null) {
            throw new RuntimeException("找不到id为" + userId + "的用户。");
        }
        SysRegisterApply registerApply = repository.findByRegisterUser(user);
        if (registerApply == null) {
            // 用户第一次申请
            registerApply = new SysRegisterApply();
            registerApply.setRegisterUser(user);
            registerApply.setImeiCode(imeiCode);
            registerApply.setApplyTime(new Date());
            registerApply.setStatus(AppAuthStatus.未审批);
        } else {
            // 重新申请
            registerApply.setImeiCode(imeiCode);
            registerApply.setApplyTime(new Date());
            registerApply.setStatus(AppAuthStatus.未审批);
        }
        repository.save(registerApply);
        return true;
    }

}
