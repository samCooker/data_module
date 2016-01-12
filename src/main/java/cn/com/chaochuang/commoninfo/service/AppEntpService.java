/*
 * FileName:    DepLinkmanService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月26日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.commoninfo.service;

import cn.com.chaochuang.common.data.service.CrudRestService;
import cn.com.chaochuang.commoninfo.domain.AppEntp;
import cn.com.chaochuang.datacenter.domain.SysDataChangeApp;

/**
 * @author LLM
 *
 */
public interface AppEntpService extends CrudRestService<AppEntp, Long> {

    /** 通过远程企业id查找企业信息 */
    public AppEntp findByRmEntpId(Long rmEntpId);

    /**
     * 新增或更新企业信息
     * 
     * @param item
     */
    public void insertOrUpdataEntp(SysDataChangeApp item);

}
