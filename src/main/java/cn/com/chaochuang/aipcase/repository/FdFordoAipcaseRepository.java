/*
 * FileName:    FdFordoAipcaseRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年7月4日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.aipcase.repository;

import java.util.List;

import cn.com.chaochuang.aipcase.domain.FdFordoAipcase;
import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;

/**
 * @author LLM
 *
 */
public interface FdFordoAipcaseRepository extends SimpleDomainRepository<FdFordoAipcase, Long> {

    /**
     *
     * @param rmPendingId
     * @param recipientId
     * @return
     */
    FdFordoAipcase findByRmPendingIdAndRecipientId(String rmPendingId, Long recipientId);

    /**
     * 根据本地数据标识获取待办数据
     *
     * @param localData
     *            本地数据标识
     * @return
     */
    List<FdFordoAipcase> findByLocalDataOrderBySendTimeAsc(LocalData localData);

    /**
     * 根据原系统待办编号查找待办记录
     *
     * @param rmPendingId
     *            原系统待办编号
     * @return
     */
    FdFordoAipcase findByRmPendingId(String rmPendingId);
}
