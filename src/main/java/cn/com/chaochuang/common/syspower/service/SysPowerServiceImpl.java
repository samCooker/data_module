package cn.com.chaochuang.common.syspower.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.syspower.domain.SysPower;
import cn.com.chaochuang.common.syspower.reference.PowerType;
import cn.com.chaochuang.common.syspower.repository.SysPowerRepository;

@Service
@Transactional
public class SysPowerServiceImpl extends SimpleLongIdCrudRestService<SysPower> implements SysPowerService {

    @Autowired
    private SysPowerRepository repository;

    @CacheEvict(value = "UserPowerCache", allEntries = true)
    public void persist(SysPower e) {
        super.persist(e);
    }

    @CacheEvict(value = "UserPowerCache", allEntries = true)
    public void merge(SysPower e) {
        super.merge(e);
    }

    @CacheEvict(value = "UserPowerCache", allEntries = true)
    public void delete(Long id) {
        super.delete(id);
    }

    @CacheEvict(value = "UserPowerCache", allEntries = true)
    public void delete(SysPower entity) {
        super.delete(entity);
    }

    /**
     * (non-Javadoc)
     *
     * @see cn.com.chaochuang.common.data.service.CrudRestService#getRepository()
     */
    @Override
    public SimpleDomainRepository<SysPower, Long> getRepository() {
        return repository;
    }

    /**
     * (non-Javadoc)
     *
     * @see cn.com.chaochuang.common.syspower.service.SysPowerService#loadAllPower()
     */
    @Override
    public Page<SysPower> loadAllPower() {
        return new PageImpl<SysPower>(repository.findAll(new Sort("powerCode")));
    }

    /**
     * (non-Javadoc)
     *
     * @see cn.com.chaochuang.common.syspower.service.SysPowerService#findPowers(java.lang.Long[])
     */
    @Override
    public Collection<SysPower> findPowers(Long[] ids) {
        return repository.findAll(Arrays.asList(ids));
    }

    /**
     * (non-Javadoc)
     *
     * @see cn.com.chaochuang.common.syspower.service.SysPowerService#findByPowerType(cn.com.chaochuang.common.syspower.reference.PowerType)
     */
    @Override
    public List<SysPower> findByPowerType(PowerType powerType) {
        return repository.findByPowerTypeFlag(powerType);
    }

    /**
     * (non-Javadoc)
     *
     * @see cn.com.chaochuang.common.syspower.service.SysPowerService#loadPowerNeed()
     */
    @Override
    public Page<SysPower> loadPowerNeed() {
        PowerType[] powerTypes = { PowerType.自动增加, PowerType.需要授权 };
        return new PageImpl<SysPower>(repository.findByPowerTypeFlagInOrderByPowerCodeAsc(powerTypes));
    }
}
