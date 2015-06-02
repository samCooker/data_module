/*
 * FileName:    AppFlowNodeInfoService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年6月2日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.appflow.service;

import cn.com.chaochuang.appflow.domain.AppFlowNodeInfo;
import cn.com.chaochuang.common.data.service.CrudRestService;

/**
 * @author LLM
 *
 */
public interface AppFlowNodeInfoService extends CrudRestService<AppFlowNodeInfo, Long> {

    /**
     * 保存流程环节数据
     * 
     * @param info
     */
    void saveFlowNodeInfo(AppFlowNodeInfo info);
}
