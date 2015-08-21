/*
 * FileName:    AipPunishEntpRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年8月11日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.aipcase.repository;

import cn.com.chaochuang.aipcase.domain.AipTransactPersonal;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;

/**
 * @author LLM
 *
 */
public interface AipTransactPersonalRepository extends SimpleDomainRepository<AipTransactPersonal, Long> {

    /**
     * 查找经办记录
     * 
     * @param rmCaseApplyId
     * @param recipientId
     */
    AipTransactPersonal findByRmCaseApplyIdAndTransactId(Long rmCaseApplyId, Long recipientId);

}
