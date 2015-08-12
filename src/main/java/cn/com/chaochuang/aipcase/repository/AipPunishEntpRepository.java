/*
 * FileName:    AipPunishEntpRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年8月11日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.aipcase.repository;

import cn.com.chaochuang.aipcase.domain.AipPunishEntp;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;

/**
 * @author LLM
 *
 */
public interface AipPunishEntpRepository extends SimpleDomainRepository<AipPunishEntp, Long> {

    /**
     * 根据原系统处罚记录编号查询处罚记录
     *
     * @param rmPunishEntpId
     *            原系统处罚记录编号
     * @return
     */
    AipPunishEntp findByRmPunishEntpId(Long rmPunishEntpId);
}
