/*
 * FileName:    FdFordoAuditRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年10月9日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.audit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.com.chaochuang.audit.domain.FdFordoAudit;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;

/**
 * @author LLM
 *
 */
public interface FdFordoAuditRepository extends SimpleDomainRepository<FdFordoAudit, Long> {

    /**
     * 根据原系统待办编号查询待办记录
     *
     * @param rmPendingId
     * @return
     */
    List<FdFordoAudit> findByRmPendingId(String rmPendingId);

    /**
     * 
     */
    @Query(value = "select max(to_number(rm_pending_id)) from fd_fordo_audit", nativeQuery = true)
    String findMaxAuditPendingHandleId();
}
