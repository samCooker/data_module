/*
 * FileName:    AuditFlowNodeInfoRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年10月14日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.audit.repository;

import java.util.List;

import cn.com.chaochuang.audit.domain.AuditFlowNodeInfo;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;

/**
 * @author LLM
 *
 */
public interface AuditFlowNodeInfoRepository extends SimpleDomainRepository<AuditFlowNodeInfo, Long> {
    /**
     * 根据原系统审批环节编号查询审批环节记录
     *
     * @param rmNodeInfoId
     * @return
     */
    List<AuditFlowNodeInfo> findByRmNodeInfoId(Long rmNodeInfoId);
}
