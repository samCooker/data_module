package cn.com.chaochuang.common.user.repository;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.user.domain.SysUser;

public interface SysUserRepository extends SimpleDomainRepository<SysUser, Long> {

    public SysUser findByAccountAndValid(final String account, final Integer valid);

    /**
     * 根据原系统用户编号查找用户对象
     *
     * @param rmUserId
     *            原系统用户编号
     * @return
     */
    public SysUser findByRmUserId(Long rmUserId);

    /**
     * 根据原系统用户信息编号查找用户对象
     *
     * @param rmUserInfoId
     *            原系统用户信息编号
     * @return
     */
    public SysUser findByrmUserInfoId(Long rmUserInfoId);

    /**
     * 根据rmUserInfoId查找用户
     * */
    public SysUser findByRmUserInfoId(Long rmUserInfoId);

}
