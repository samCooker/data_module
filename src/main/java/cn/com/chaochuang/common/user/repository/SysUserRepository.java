package cn.com.chaochuang.common.user.repository;

import java.util.List;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.user.domain.SysUser;

public interface SysUserRepository extends SimpleDomainRepository<SysUser, Long> {

    public SysUser findByAccountAndValid(final String account, final Integer valid);

    public List<SysUser> findByValid(final Integer valid);

    public List<SysUser> findByDepartmentIdAndValidOrderByUserNameAsc(final Long depid, final Integer valid);

    public List<SysUser> findByAccount(String account);

}
