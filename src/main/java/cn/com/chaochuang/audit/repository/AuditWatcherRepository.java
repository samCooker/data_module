/*
 * FileName:    AuditWatcherRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年10月14日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.audit.repository;

import java.util.List;

import cn.com.chaochuang.audit.domain.AuditWatcher;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;

/**
 * @author LLM
 *
 */
public interface AuditWatcherRepository extends SimpleDomainRepository<AuditWatcher, Long> {
    /**
     * 根据原系统观察员记录编号查询观察员记录
     *
     * @param rmWatcherId
     * @return
     */
    List<AuditWatcher> findByRmWatcherId(Long rmWatcherId);
}
