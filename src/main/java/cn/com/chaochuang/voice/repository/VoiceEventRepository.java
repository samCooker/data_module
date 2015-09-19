/*
 * FileName:    VoiceEventRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月14日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.voice.repository;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.voice.domain.VoiceEvent;

/**
 * @author LLM
 *
 */
public interface VoiceEventRepository extends SimpleDomainRepository<VoiceEvent, Long> {

    /**
     * 根据原系统事件编号获取事件记录
     * 
     * @param rmEventId
     * @return
     */
    VoiceEvent findByRmEventId(Long rmEventId);
}
