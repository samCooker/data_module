package cn.com.chaochuang.common.syspower.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.syspower.domain.SysRole;
import cn.com.chaochuang.common.syspower.repository.SysRoleRepository;

@Service
@Transactional
public class SysRoleServiceImpl extends SimpleLongIdCrudRestService<SysRole> implements SysRoleService {

    @Autowired
    private SysRoleRepository repository;

    /**
     * (non-Javadoc)
     *
     * @see cn.com.chaochuang.common.data.service.CrudRestService#getRepository()
     */
    @Override
    public SimpleDomainRepository<SysRole, Long> getRepository() {
        return repository;
    }

    @CacheEvict(value = "UserPowerCache", allEntries = true)
    public void persist(SysRole e) {
        super.persist(e);
    }

    @CacheEvict(value = "UserPowerCache", allEntries = true)
    public void merge(SysRole e) {
        super.merge(e);
    }

    @CacheEvict(value = "UserPowerCache", allEntries = true)
    public void delete(Long id) {
        super.delete(id);
    }

    @CacheEvict(value = "UserPowerCache", allEntries = true)
    public void delete(SysRole entity) {
        super.delete(entity);
    }

}
