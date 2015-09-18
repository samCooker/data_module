/*
 * FileName:    VoiceInfoEventRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月15日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.voice.repository;

import java.util.List;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.voice.domain.VoiceInfoEvent;

/**
 * @author LLM
 *
 */
public interface VoiceInfoEventRepository extends SimpleDomainRepository<VoiceInfoEvent, Long> {

    /**
     * 通过原系统的事件编号查询事件所属舆情列表
     *
     * @param rmVoiceEventId
     *            原系统的事件编号
     * @return
     */
    List<VoiceInfoEvent> findByRmVoiceEventId(Long rmVoiceEventId);

    /**
     * 通过原系统的舆情基本编号查询舆情记录列表
     *
     * @param rmVoiceInfoId
     * @return
     */
    List<VoiceInfoEvent> findByRmVoiceInfoId(Long rmVoiceInfoId);
}
