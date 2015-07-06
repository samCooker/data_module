/*
 * FileName:    AppItemAttachRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年6月1日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.appflow.repository;

import java.util.List;

import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.appflow.domain.AppItemAttach;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;

/**
 * @author LLM
 *
 */
public interface AppItemAttachRepository extends SimpleDomainRepository<AppItemAttach, Long> {

    /**
     * 根据原材料清单编号获取审批附件
     *
     * @param rmAttachId
     * @return
     */
    AppItemAttach findByRmAttachId(Long rmAttachId);

    /**
     * 查询localData=0的附件信息
     *
     * @return
     */
    List<AppItemAttach> findByLocalData(LocalData local);
}
