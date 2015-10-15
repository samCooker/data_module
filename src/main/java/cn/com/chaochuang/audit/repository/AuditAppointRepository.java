/*
 * FileName:    AuditAppointRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年10月14日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.audit.repository;

import java.util.List;

import cn.com.chaochuang.audit.domain.AuditAppoint;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;

/**
 * @author LLM
 *
 */
public interface AuditAppointRepository extends SimpleDomainRepository<AuditAppoint, Long> {
    /**
     * 根据原系统选派记录编号查询选派记录
     *
     * @param rmAppointId
     * @return
     */
    List<AuditAppoint> findByRmAppointId(Long rmAppointId);
}
