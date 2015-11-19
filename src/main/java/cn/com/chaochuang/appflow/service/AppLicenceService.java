/*
 * FileName:    AppLicenceService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年11月18日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.appflow.service;

import cn.com.chaochuang.appflow.domain.AppLicence;
import cn.com.chaochuang.common.data.service.CrudRestService;
import cn.com.chaochuang.datacenter.domain.SysDataChange;

/**
 * @author LLM
 *
 */
public interface AppLicenceService extends CrudRestService<AppLicence, Long> {

    /**
     * 许可证信息变更
     * 
     * @param dataChange
     */
    void saveAppLicence(SysDataChange dataChange);
}
