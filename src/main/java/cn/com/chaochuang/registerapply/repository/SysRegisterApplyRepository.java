/*
 * FileName:    SysRegisterApplyRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年6月29日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.registerapply.repository;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.user.domain.SysUser;
import cn.com.chaochuang.registerapply.domain.SysRegisterApply;

/**
 * @author Shicx
 *
 */
public interface SysRegisterApplyRepository extends SimpleDomainRepository<SysRegisterApply, Long> {

    /**
     * 通过用户id查询申请事项
     *
     * @param registerUser
     * @return
     */
    public SysRegisterApply findByRegisterUser(SysUser registerUser);

}
