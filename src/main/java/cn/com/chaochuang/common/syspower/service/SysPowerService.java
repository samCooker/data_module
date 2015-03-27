package cn.com.chaochuang.common.syspower.service;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;

import cn.com.chaochuang.common.data.service.CrudRestService;
import cn.com.chaochuang.common.syspower.domain.SysPower;
import cn.com.chaochuang.common.syspower.reference.PowerType;

public interface SysPowerService extends CrudRestService<SysPower, Long> {

    public Page<SysPower> loadAllPower();

    public Page<SysPower> loadPowerNeed();

    public Collection<SysPower> findPowers(Long[] ids);

    public List<SysPower> findByPowerType(PowerType powerType);

}
