/*
 * FileName:    SysRegisterApplyService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年6月29日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.registerapply.service;

import cn.com.chaochuang.common.data.service.CrudRestService;
import cn.com.chaochuang.registerapply.domain.SysRegisterApply;

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

}
