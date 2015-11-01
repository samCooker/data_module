/*
 * FileName:    FdFordoRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月22日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.docwork.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;

import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.docwork.domain.FdFordo;

/**
 * @author LLM
 *
 */
public interface FdFordoRepository extends SimpleDomainRepository<FdFordo, Long> {

    /**
     * 根据原系统的待办编号和接收人编号查询待办事宜记录
     *
     * @param rmPendingId
     * @param recipientId
     * @return
     */
    List<FdFordo> findByRmPendingIdAndRecipientId(String rmPendingId, Long recipientId);

    /**
     * 根据原系统的待办明细编号查询待办事宜记录
     *
     * @param rmPendingItemId
     * @return
     */
    FdFordo findByRmPendingItemId(String rmPendingItemId);

    /**
     * @param localDate
     * @param page
     * @return
     */
    List<FdFordo> findByLocalDataOrderBySendTimeAsc(LocalData localDate, Pageable page);

    /**
     * @param rmInstanceId
     * @param localData
     * @return
     */
    List<FdFordo> findByRmInstanceIdAndLocalData(String rmInstanceId, LocalData localData);

}
