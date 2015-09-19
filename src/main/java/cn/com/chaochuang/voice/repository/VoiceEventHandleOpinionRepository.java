/*
 * FileName:    VoiceEventHandleOpinionRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月16日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.voice.repository;

import java.util.List;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.voice.domain.VoiceEventHandleOpinion;

/**
 * @author LLM
 *
 */
public interface VoiceEventHandleOpinionRepository extends SimpleDomainRepository<VoiceEventHandleOpinion, Long> {

    /**
     * 根据事件审批流程编号查询审批意见
     *
     * @param rmEventHandleId
     * @return
     */
    List<VoiceEventHandleOpinion> findByRmEventHandleId(Long rmEventHandleId);

    /**
     * 根据事件审批环节编号查询审批意见
     *
     * @param rmHandleApproveId
     * @return
     */
    List<VoiceEventHandleOpinion> findByRmHandleApproveId(Long rmHandleApproveId);

    /**
     * 根据审批意见编号查询意见
     * 
     * @param rmOpinionId
     * @return
     */
    VoiceEventHandleOpinion findByRmOpinionId(Long rmOpinionId);
}
