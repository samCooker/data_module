/*
 * FileName:    SysRegisterApplyService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年6月29日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.sysmanage.registerapply.service;

import cn.com.chaochuang.common.data.service.CrudRestService;
import cn.com.chaochuang.sysmanage.registerapply.domain.SysRegisterApply;
import cn.com.chaochuang.sysmanage.registerapply.reference.AppAuthStatus;

/**
 * @author Shicx
 *
 */
public interface SysRegisterApplyService extends CrudRestService<SysRegisterApply, Long> {

    /**
     * 用户提交使用App功能的申请
     *
     * @param userId
     *            申请的用户id
     * @param imeiCode
     *            用户手机的IMEI码
     * @return
     */
    public boolean toRegisterAppAuthority(Long userId, String imeiCode);

    /**
     * 改变多个申请的状态
     *
     * @param ids
     *            多个申请项id
     * @param status
     *            状态号
     * @return
     */
    public boolean changeApplicationStatusInBatch(Long[] ids, AppAuthStatus status);

    /**
     * 改变申请状态
     *
     * @param ids
     *            申请项id
     * @param status
     *            状态号
     * @return
     */
    public boolean changeApplicationStatus(Long id, AppAuthStatus status);

    /**
     * 删除多个申请项
     *
     * @param ids
     * @return
     */
    public boolean deleteApplicationInBatch(Long[] ids);

    /**
     * 删除申请项
     *
     * @param id
     * @return
     */
    public boolean deleteApplication(Long id);

    /**
     * 直接后台注册
     *
     * @param userId
     * @param imeiCode
     * @return
     */
    public boolean register(Long userId, String imeiCode);

    /**
     * 注销
     * 
     * @param userId
     * @return
     */
    public boolean unRregister(Long userId);

}
