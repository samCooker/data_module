/*
 * FileName:    DocFileRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月30日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.docwork.repository;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.docwork.domain.FlowNodeInfo;

/**
 * @author Shicx
 *
 */
public interface FlowNodeInfoRepository extends SimpleDomainRepository<FlowNodeInfo, Long> {

    /**
     * 通过rmInstnoId查找节点信息
     * */
    FlowNodeInfo findByRmInstnoId(Long rmInstnoId);
}
