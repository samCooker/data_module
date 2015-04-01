package cn.com.chaochuang.common.user.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.security.util.UserTool;
import cn.com.chaochuang.common.user.domain.SysUser;
import cn.com.chaochuang.common.user.repository.SysUserRepository;

@Service
@Transactional
public class SysUserServiceImpl extends SimpleLongIdCrudRestService<SysUser> implements SysUserService {

    @Autowired
    private SysUserRepository repository;

    @Override
    public SimpleDomainRepository<SysUser, Long> getRepository() {
        return repository;
    }

    @Override
    public List<SysUser> findByDepartmentId(Long depid) {
        return repository.findByDepartmentIdAndValidOrderByUserNameAsc(depid, SysUser.VALID);
    }

    @Override
    public SysUser saveCurrUser(SysUser user) {
        SysUser u = repository.findOne(Long.parseLong(UserTool.getInstance().getCurrentUserId()));
        u.setUserName(user.getUserName());
        u.setSex(user.getSex());
        u.setMobile(user.getMobile());
        repository.save(u);
        return u;
    }

    @Override
    public List<SysUser> findByAccount(String account) {
        return repository.findByAccount(account);
    }

    @Override
    public SysUser convert(SysUser info) {
        SysUser u = null;
        if (info.getId() != null) {
            u = repository.findOne(info.getId());
        } else {
            u = new SysUser();
        }
        u.setAccount(info.getAccount());
        u.setDepartment(info.getDepartment());
        u.setSex(info.getSex());
        u.setUserName(info.getUserName());
        return u;
    }

    /**
     * (non-Javadoc)
     *
     * @see cn.com.chaochuang.common.user.service.SysUserService#findUsers(java.lang.Long[])
     */
    @Override
    public Collection<SysUser> findUsers(Long[] ids) {
        return repository.findAll(Arrays.asList(ids));
    }

    /**
     * (non-Javadoc)
     *
     * @see cn.com.chaochuang.common.user.service.SysUserService#loadAllActiveUsers()
     */
    @Override
    public List<SysUser> loadAllActiveUsers() {
        return repository.findByValid(SysUser.VALID);
    }

}
