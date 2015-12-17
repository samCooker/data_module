/*
 * FileName:    FdFordoAppRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年5月28日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.appflow.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.appflow.domain.FdFordoApp;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;

/**
 * @author LLM
 *
 */
public interface FdFordoAppRepository extends SimpleDomainRepository<FdFordoApp, Long> {

    /**
     * 根据原系统待办编号查找待办记录
     *
     * @param rmPendingId
     *            原系统待办编号
     * @return
     */
    List<FdFordoApp> findByRmPendingId(String rmPendingId);

    /**
     * 根据本地数据标识获取待办数据
     *
     * @param localData
     *            本地数据标识
     * @return
     */
    List<FdFordoApp> findByLocalDataOrderBySendTimeAsc(LocalData localData);

    /**
     * 根据本地数据标识获取待办数据
     * 
     * @param localData
     * @return
     */
    List<FdFordoApp> findByLocalDataOrderBySendTimeAsc(LocalData localData, Pageable page);

    /**
     * 
     */
    @Query(value = "select max(to_number(rm_pending_id)) from fd_fordo_app", nativeQuery = true)
    String findMaxRmPendingId();
}
