/*
 * FileName:    FdFordoAppRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年5月28日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.appflow.repository;

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
    FdFordoApp findByRmPendingId(String rmPendingId);
}
