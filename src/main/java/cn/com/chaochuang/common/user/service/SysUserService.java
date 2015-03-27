package cn.com.chaochuang.common.user.service;

import java.util.Collection;
import java.util.List;

import cn.com.chaochuang.common.data.service.CrudRestService;
import cn.com.chaochuang.common.user.domain.SysUser;

public interface SysUserService extends CrudRestService<SysUser, Long> {

    public List<SysUser> findByDepartmentId(final Long depid);

    public SysUser saveCurrUser(SysUser user);

    public List<SysUser> findByAccount(String account);

    public SysUser convert(SysUser info);

    public List<SysUser> loadAllActiveUsers();

    public Collection<SysUser> findUsers(Long[] ids);

}
