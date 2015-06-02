/*
 * FileName:    AppFlowNodeInfoRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年6月1日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.appflow.repository;

import cn.com.chaochuang.appflow.domain.AppFlowNodeInfo;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;

/**
 * @author LLM
 *
 */
public interface AppFlowNodeInfoRepository extends SimpleDomainRepository<AppFlowNodeInfo, Long> {

    /**
     * 根据原系统环节编号查找环节
     * 
     * @param rmNodeInfoId
     * @return
     */
    AppFlowNodeInfo findByRmNodeInfoId(Long rmNodeInfoId);
}
