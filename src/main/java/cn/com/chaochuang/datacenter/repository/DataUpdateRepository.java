/*
 * FileName:    DataUpdateRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月27日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.datacenter.repository;

import java.util.List;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.datacenter.domain.DataUpdate;
import cn.com.chaochuang.datacenter.reference.OperationType;
import cn.com.chaochuang.datacenter.reference.WorkType;

/**
 * @author LLM
 *
 */
public interface DataUpdateRepository extends SimpleDomainRepository<DataUpdate, Long> {
    /**
     * 查询DataUpdate记录
     *
     * @param workType
     * @param operationType
     * @return
     */
    List<DataUpdate> findByWorkTypeAndOperationType(WorkType workType, OperationType operationType);
}
