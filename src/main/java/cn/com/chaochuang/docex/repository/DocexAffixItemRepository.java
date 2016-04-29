/*
 * FileName:    DocexAffixItemRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2016
 * History:     2016年4月26日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.docex.repository;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.docex.domain.DocexAffixItem;

/**
 * @author LLM
 *
 */
public interface DocexAffixItemRepository extends SimpleDomainRepository<DocexAffixItem, Long> {
    /**
     * 根据原附件编号查询附件记录
     * 
     * @param rmFileId
     * @return
     */
    DocexAffixItem findByRmFileId(Long rmFileId);
}
