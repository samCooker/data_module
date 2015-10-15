/*
 * FileName:    AuditFlowNodeOpinionsRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年10月14日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.audit.repository;

import java.util.List;

import cn.com.chaochuang.audit.domain.AuditFlowNodeOpinions;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;

/**
 * @author LLM
 *
 */
public interface AuditFlowNodeOpinionsRepository extends SimpleDomainRepository<AuditFlowNodeOpinions, Long> {
    /**
     * 根据原系统审批意见编号查询审批意见记录
     *
     * @param rmNodeOpinionsId
     * @return
     */
    List<AuditFlowNodeOpinions> findByRmNodeOpinionsId(Long rmNodeOpinionsId);
}
