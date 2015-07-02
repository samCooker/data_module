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

    /**
     * 根据远程OA的用户id查找用户
     *
     * @param 远程OA的用户id
     *
     * @return SysUser,若rmUserId为空返回null
     * */
    public SysUser findByRmUserId(Long rmUserId);

    /**
     * 根据rmUserInfoId查找用户
     *
     * @param rmUserInfoId
     * @return
     */
    public SysUser findByRmUserInfoId(Long rmUserInfoId);
}
