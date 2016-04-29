/*
 * FileName:    FdFordoDocRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2016
 * History:     2016年4月26日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.docex.repository;

import org.springframework.data.jpa.repository.Query;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.docex.domain.FdFordoDoc;

/**
 * @author LLM
 *
 */
public interface FdFordoDocRepository extends SimpleDomainRepository<FdFordoDoc, Long> {
    /**
     *
     * @return
     */
    @Query(value = "select max(rm_pending_id) from fd_fordo_doc", nativeQuery = true)
    String findMaxRmPendingItemId();

    /**
     * @param rmPendingId
     * @return
     */
    FdFordoDoc findByRmPendingId(Long rmPendingId);
}
