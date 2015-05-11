/*
 * FileName:    DepLinkmanService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月26日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.commoninfo.service;

import cn.com.chaochuang.common.data.service.CrudRestService;
import cn.com.chaochuang.commoninfo.domain.DepLinkman;
import cn.com.chaochuang.datacenter.domain.SysDataChange;

/**
 * @author LLM
 *
 */
public interface DepLinkmanService extends CrudRestService<DepLinkman, Long> {
    /**
     * 分析系统数据的处理
     *
     * @param dataChange
     */
    void analysisDataChange(SysDataChange dataChange);
}
