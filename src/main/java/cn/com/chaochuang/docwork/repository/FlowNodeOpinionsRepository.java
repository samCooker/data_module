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
import cn.com.chaochuang.docwork.domain.FlowNodeOpinions;

/**
 * @author Shicx
 *
 */
public interface FlowNodeOpinionsRepository extends SimpleDomainRepository<FlowNodeOpinions, Long> {

    /**
     * 通过rmInstnoId查找意见信息
     *
     * */
    List<FlowNodeOpinions> findByRmInstnoId(Long rmInstnoId);

    /**
     * 通过远程实例id(rmInstId)和环节标识(nodeFlag，对应oa的item_id)查找意见信息,同一个环节无重复的环节标识
     *
     * */
    FlowNodeOpinions findByRmInstanceIdAndNodeFlagAndTransactId(String rmInstanceId, String nodeFlag, Long transactId);

    /**
     * 通过公文id查找相关意见
     * */
    List<FlowNodeOpinions> findByDocId(Long docId);

}
