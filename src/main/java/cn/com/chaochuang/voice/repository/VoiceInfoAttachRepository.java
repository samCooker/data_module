/*
 * FileName:    VoiceInfoAttachRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月13日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.voice.repository;

import java.util.List;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.voice.domain.VoiceInfoAttach;

/**
 * @author LLM
 *
 */
public interface VoiceInfoAttachRepository extends SimpleDomainRepository<VoiceInfoAttach, Long> {

    /**
     * 根据附件标识获取附件
     *
     * @param rmAffixId
     * @return
     */
    List<VoiceInfoAttach> findByRmAffixId(String rmAffixId);

    /**
     * 根据原系统附件编号获取附件
     * 
     * @param rmAttachId
     * @return
     */
    VoiceInfoAttach findByRmAttachId(Long rmAttachId);
}
