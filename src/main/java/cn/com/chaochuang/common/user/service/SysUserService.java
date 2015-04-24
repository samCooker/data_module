package cn.com.chaochuang.common.user.service;

import cn.com.chaochuang.common.data.service.CrudRestService;
import cn.com.chaochuang.common.user.domain.SysUser;
import cn.com.chaochuang.datacenter.domain.SysDataChange;

public interface SysUserService extends CrudRestService<SysUser, Long> {

    /**
     * 保存用户信息
     * 
     * @param user
     * @return
     */
    public SysUser saveCurrUser(SysUser user);

    /**
     * 分析系统数据的处理
     *
     * @param dataChange
     */
    void analysisDataChange(SysDataChange dataChange);
}
