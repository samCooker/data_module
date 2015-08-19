/*
 * FileName:    DataUpdateRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月27日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.aipcase.repository;

import java.util.List;

import cn.com.chaochuang.aipcase.domain.AipCaseAttach;
import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;

/**
 * @author LJX
 *
 */
public interface AipCaseAttachRepository extends SimpleDomainRepository<AipCaseAttach, Long> {

    /**
     * 根据远程ID查找附件
     * 
     * @param rmAttachId
     * @return
     */
    AipCaseAttach findByRmAttachId(Long rmAttachId);

    /**
     * 根据远程案件来源id查找附件
     * 
     * @param rmCaseApplyId
     * @return
     */
    List<AipCaseAttach> findByRmCaseApplyId(Long rmCaseApplyId);

    /**
     * @param localData
     * @param page
     */
    List<AipCaseAttach> findByLocalData(LocalData localData);

}
