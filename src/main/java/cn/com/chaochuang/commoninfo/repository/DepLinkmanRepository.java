/*
 * FileName:    DepLinkmanRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月20日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.commoninfo.repository;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.commoninfo.domain.DepLinkman;

/**
 * @author LLM
 *
 */
public interface DepLinkmanRepository extends SimpleDomainRepository<DepLinkman, Long> {

    /**
     * 根据原系统的通讯录编号查询通讯录记录
     * 
     * @param rmLinkmanId
     *            原系统通讯录编号
     * @return
     */
    DepLinkman findByRmLinkmanId(Long rmLinkmanId);
}
