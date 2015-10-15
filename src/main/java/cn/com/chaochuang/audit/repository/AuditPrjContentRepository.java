/*
 * FileName:    AuditPrjContentRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年10月14日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.audit.repository;

import java.util.List;

import cn.com.chaochuang.audit.domain.AuditPrjContent;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;

/**
 * @author LLM
 *
 */
public interface AuditPrjContentRepository extends SimpleDomainRepository<AuditPrjContent, Long> {
    /**
     * 根据原系统审查项目编号查询审查项目记录
     *
     * @param rmPrjContentId
     * @return
     */
    List<AuditPrjContent> findByRmPrjContentId(Long rmPrjContentId);
}
