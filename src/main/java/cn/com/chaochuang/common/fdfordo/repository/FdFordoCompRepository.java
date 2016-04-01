/*
 * FileName:    FdFordoCompRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2016
 * History:     2016年3月31日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.common.fdfordo.repository;

import java.util.List;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.fdfordo.domain.FdFordoComp;
import cn.com.chaochuang.docwork.reference.FordoSource;

/**
 * @author LLM
 *
 */
public interface FdFordoCompRepository extends SimpleDomainRepository<FdFordoComp, Long> {

    /**
     * 根据接收人编号查询综合待办
     *
     * @param recipientId
     *            接收人编号
     * @return 综合待办记录
     */
    List<FdFordoComp> findByRecipientIdOrderBySendTimeDesc(Long recipientId);

    /**
     * 根据原待办记录编号和待办类型查询综合待办
     *
     * @param fordoId
     *            原待办记录编号
     * @param fordoSource
     *            待办类型查询
     * @return 综合待办记录
     */
    List<FdFordoComp> findByFordoIdAndFordoSource(Long fordoId, FordoSource fordoSource);
}
