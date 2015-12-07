/*
 * FileName:    EmAffixRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年12月3日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.emergency.repository;

import java.util.List;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.emergency.domain.EmAffix;

/**
 * @author LLM
 *
 */
public interface EmAffixRepository extends SimpleDomainRepository<EmAffix, Long> {

    /**
     * 根据文件编号查询附件记录
     *
     * @param fileId
     * @return
     */
    EmAffix findByFileId(Long fileId);

    /**
     * 根据附件编号查询附件记录集合
     * 
     * @param rmAffixId
     * @return
     */
    List<EmAffix> findByRmAffixId(String rmAffixId);
}
