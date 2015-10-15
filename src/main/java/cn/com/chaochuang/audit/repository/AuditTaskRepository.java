/*
 * FileName:    AuditTaskRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年10月14日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.audit.repository;

import java.util.List;

import cn.com.chaochuang.audit.domain.AuditTask;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;

/**
 * @author LLM
 *
 */
public interface AuditTaskRepository extends SimpleDomainRepository<AuditTask, Long> {

    /**
     * 根据原系统检查任务编号查询检查任务记录
     * 
     * @param rmAuditTaskId
     * @return
     */
    List<AuditTask> findByRmAuditTaskId(Long rmAuditTaskId);
}
