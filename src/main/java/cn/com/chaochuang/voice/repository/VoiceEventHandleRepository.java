/*
 * FileName:    VoiceEventHandleRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月15日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.voice.repository;

import java.util.List;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.voice.domain.VoiceEventHandle;

/**
 * @author LLM
 *
 */
public interface VoiceEventHandleRepository extends SimpleDomainRepository<VoiceEventHandle, Long> {

    /**
     * 根据原系统事件编号获取审批流程
     * 
     * @param rmEventId
     * @return
     */
    List<VoiceEventHandle> findByRmEventIdOrderByRmEventHandleIdDesc(Long rmEventId);
}
