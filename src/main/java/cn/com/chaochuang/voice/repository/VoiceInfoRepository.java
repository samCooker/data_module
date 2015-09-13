/*
 * FileName:    VoiceInfoRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月11日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.voice.repository;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.voice.domain.VoiceInfo;

/**
 * @author LLM
 *
 */
public interface VoiceInfoRepository extends SimpleDomainRepository<VoiceInfo, Long> {

    /**
     * 根据原系统舆情信息ID查询舆情记录
     *
     * @param rmInfoId
     *            原系统舆情信息ID
     * @return 舆情记录
     */
    VoiceInfo findByRmInfoId(Long rmInfoId);
}
