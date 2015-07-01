package cn.com.chaochuang.common.user.repository;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.user.domain.SysManageUser;

public interface SysManageUserRepository extends SimpleDomainRepository<SysManageUser, Long> {

    public SysManageUser findByUserId(Long userId);

}
