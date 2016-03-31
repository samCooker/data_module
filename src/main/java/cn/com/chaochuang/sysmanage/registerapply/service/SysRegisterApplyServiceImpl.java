/*
 * FileName:    SysRegisterApplyServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年6月29日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.sysmanage.registerapply.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.user.domain.SysUser;
import cn.com.chaochuang.common.user.repository.SysUserRepository;
import cn.com.chaochuang.sysmanage.registerapply.domain.SysRegisterApply;
import cn.com.chaochuang.sysmanage.registerapply.reference.AppAuthStatus;
import cn.com.chaochuang.sysmanage.registerapply.reference.IsRegister;
import cn.com.chaochuang.sysmanage.registerapply.repository.SysRegisterApplyRepository;

/**
 * @author Shicx
 *
 */
@Service
@Transactional
public class SysRegisterApplyServiceImpl extends SimpleLongIdCrudRestService<SysRegisterApply>
                implements SysRegisterApplyService {

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
            // 保存申请项
            repository.save(registerApply);
            // 将用户注册信息置为提交状态
            user.setIsRegister(IsRegister.已提交注册);
            user.setImeiCode(imeiCode);
            user.setRegisterTime(new Date());
            userRepository.save(user);
        }
        return true;
    }

    /**
     * (non-Javadoc)
     *
     * @see cn.com.chaochuang.registerapply.service.SysRegisterApplyService#changeApplicationStatus(java.lang.Long,
     *      cn.com.chaochuang.registerapply.reference.AppAuthStatus)
     */
    @Override
    public boolean changeApplicationStatusInBatch(Long[] ids, AppAuthStatus status) {
        if (ids == null) {
            return false;
        }
        for (Long id : ids) {
            this.changeApplicationStatus(id, status);
        }
        return true;
    }

    /**
     * (non-Javadoc)
     *
     * @see cn.com.chaochuang.registerapply.service.SysRegisterApplyService#deleteByApplicationId(java.lang.Long)
     */
    @Override
    public boolean deleteApplicationInBatch(Long[] ids) {
        if (ids == null) {
            return false;
        }
        for (Long id : ids) {
            this.deleteApplication(id);
        }
        return true;
    }

    /**
     * (non-Javadoc)
     *
     * @see cn.com.chaochuang.registerapply.service.SysRegisterApplyService#changeApplicationStatus(java.lang.Long,
     *      cn.com.chaochuang.registerapply.reference.AppAuthStatus)
     */
    @Override
    public boolean changeApplicationStatus(Long id, AppAuthStatus status) {
        if (id == null || status == null) {
            return false;
        }
        SysRegisterApply registerApply = repository.findOne(id);
        if (registerApply != null) {
            // 设置申请状态
            registerApply.setStatus(status);
            registerApply.setApplyTime(new Date());
            registerApply.setMemo("管理员手动审批。非短信验证。");
            repository.save(registerApply);
            // 保存申请信息到用户表(旧的非短信验证方式，可在旧app都升级后删除)
            this.saveRegisterInfoInUser(registerApply, status);
            return true;
        }
        return false;
    }

    /**
     * (non-Javadoc)
     *
     * @see cn.com.chaochuang.registerapply.service.SysRegisterApplyService#deleteApplication(java.lang.Long)
     */
    @Override
    public boolean deleteApplication(Long id) {
        if (id == null) {
            return false;
        }
        SysRegisterApply registerApply = repository.findOne(id);
        if (registerApply != null) {
            repository.delete(registerApply);
            // 将用户表的申请信息置回未注册状态
            saveRegisterInfoInUser(registerApply, AppAuthStatus.未审批);
        }
        return false;
    }

    /**
     * 保存申请信息到用户表
     *
     * @param registerApply
     * @param status
     */
    private void saveRegisterInfoInUser(SysRegisterApply registerApply, AppAuthStatus status) {
        SysUser user = registerApply.getRegisterUser();
        if (AppAuthStatus.审批通过.equals(status)) {
            user.setRegisterTime(new Date());
            user.setImeiCode(registerApply.getImeiCode());
            user.setIsRegister(IsRegister.已注册);
        } else if (AppAuthStatus.审批不通过.equals(status)) {
            user.setRegisterTime(new Date());
            user.setImeiCode(registerApply.getImeiCode());
            user.setIsRegister(IsRegister.注册未通过);
        } else {
            user.setRegisterTime(null);
            user.setImeiCode(null);
            user.setIsRegister(IsRegister.未注册);
        }
        userRepository.save(user);
    }

    /**
     * (non-Javadoc)
     *
     * @see cn.com.chaochuang.sysmanage.registerapply.service.SysRegisterApplyService#register(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public boolean register(Long userId, String imeiCode) {
        this.toRegisterAppAuthority(userId, imeiCode);
        SysRegisterApply sra = this.repository.findByRegisterUser(this.userRepository.findOne(userId));
        if (sra != null) {
            this.changeApplicationStatus(sra.getId(), AppAuthStatus.审批通过);
            return true;
        }
        return false;
    }

    /**
     * (non-Javadoc)
     *
     * @see cn.com.chaochuang.sysmanage.registerapply.service.SysRegisterApplyService#unRregister(java.lang.Long)
     */
    @Override
    public boolean unRregister(Long userId) {
        SysRegisterApply sra = this.repository.findByRegisterUser(this.userRepository.findOne(userId));
        if (sra != null) {
            this.deleteApplication(sra.getId());
            return true;
        }
        return false;
    }

}
