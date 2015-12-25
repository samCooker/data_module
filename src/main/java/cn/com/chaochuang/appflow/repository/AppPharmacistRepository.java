/*
 * FileName:    AppPrjMaterialRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年6月2日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.appflow.repository;

import java.util.List;

import cn.com.chaochuang.appflow.domain.AppPharmacist;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;

/**
 * @author LLM
 *
 */
public interface AppPharmacistRepository extends SimpleDomainRepository<AppPharmacist, Long> {

    /** 通过远程药师id查找药师信息 */
    public List<AppPharmacist> findByRmPharmId(Long rmPharmId);
}
