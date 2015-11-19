/*
 * FileName:    AppLicenceRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年11月16日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.appflow.repository;

import cn.com.chaochuang.appflow.domain.AppLicence;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;

/**
 * @author LLM
 *
 */
public interface AppLicenceRepository extends SimpleDomainRepository<AppLicence, Long> {

    /**
     * 根据原系统许可证信息查找许可证记录
     * 
     * @param rmLicenceId
     * @return
     */
    AppLicence findByRmLicenceId(Long rmLicenceId);
}
