/*
 * FileName:    AuditAuditorRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年10月16日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.audit.repository;

import cn.com.chaochuang.audit.domain.AuditAuditor;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;

/**
 * @author LLM
 *
 */
public interface AuditAuditorRepository extends SimpleDomainRepository<AuditAuditor, Long> {

}
