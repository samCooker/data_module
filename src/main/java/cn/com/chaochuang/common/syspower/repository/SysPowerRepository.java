package cn.com.chaochuang.common.syspower.repository;

import java.util.List;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.syspower.domain.SysPower;
import cn.com.chaochuang.common.syspower.reference.PowerType;

public interface SysPowerRepository extends SimpleDomainRepository<SysPower, Long> {

    public SysPower findByUrl(String url);

    public SysPower findByPowerName(String powerName);

    public List<SysPower> findByPowerTypeFlag(PowerType powerType);

    public List<SysPower> findByPowerTypeFlagInOrderByPowerCodeAsc(PowerType[] powerType);

}
