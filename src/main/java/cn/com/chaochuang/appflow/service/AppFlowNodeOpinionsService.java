/*
 * FileName:    AppFlowNodeOpinionsService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年6月2日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.appflow.service;

import cn.com.chaochuang.appflow.domain.AppFlowNodeOpinions;
import cn.com.chaochuang.common.data.service.CrudRestService;

/**
 * @author LLM
 *
 */
public interface AppFlowNodeOpinionsService extends CrudRestService<AppFlowNodeOpinions, Long> {

    /**
     * 保存环节意见
     * 
     * @param info
     *            环节意见信息
     */
    void saveFlowNodeOpinions(AppFlowNodeOpinions info);
}
