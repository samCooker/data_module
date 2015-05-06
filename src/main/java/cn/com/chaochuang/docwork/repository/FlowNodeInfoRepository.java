/*
 * FileName:    DocFileRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月30日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.docwork.repository;

import java.util.List;

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

    /**
     * 通过rmInstnoId查找节点信息和办理人id(transactId)查找节点信息
     * */
    FlowNodeInfo findByRmInstnoIdAndTransactId(Long rmInstnoId, Long transactId);

    /**
     * 通过前实例id(rmPreInstnoId)和办理人id(transactId)查找节点信息,rmPreInstnoId=0为首环节
     * */
    FlowNodeInfo findByRmPreInstnoIdAndTransactId(Long rmPreInstnoId, Long transactId);

    /**
     * 通过远程节点实例id，环节号，接收人id
     * */
    FlowNodeInfo findByRmInstanceIdAndNodeIdAndTransactId(String rmInstanceId, String nodeId, Long transactId);

    /**
     * 通过远程节点实例id查找相关流程节点
     * */
    List<FlowNodeInfo> findByRmInstanceId(String rmInstanceId);
}
