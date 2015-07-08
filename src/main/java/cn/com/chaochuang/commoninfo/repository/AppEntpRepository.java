/*
 * FileName:    PubInfoRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月20日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.commoninfo.repository;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.commoninfo.domain.AppEntp;

/**
 * @author LLM
 *
 */
public interface AppEntpRepository extends SimpleDomainRepository<AppEntp, Long> {

    /** 通过远程企业id查找企业信息 */
    public AppEntp findByRmEntpId(Long rmEntpId);
}
