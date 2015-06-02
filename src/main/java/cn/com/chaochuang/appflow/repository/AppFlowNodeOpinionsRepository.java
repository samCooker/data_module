/*
 * FileName:    AppFlowNodeOpinionsRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年6月1日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.appflow.repository;

import cn.com.chaochuang.appflow.domain.AppFlowNodeOpinions;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;

/**
 * @author LLM
 *
 */
public interface AppFlowNodeOpinionsRepository extends SimpleDomainRepository<AppFlowNodeOpinions, Long> {

    /**
     * 根据rmNodeOpinionsId查询环节意见
     * 
     * @param rmNodeOpinionsId
     * @return
     */
    AppFlowNodeOpinions findByRmNodeOpinionsId(Long rmNodeOpinionsId);
}
