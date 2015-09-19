/*
 * FileName:    VoiceEventHandleApproveRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月15日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.voice.repository;

import java.util.List;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.voice.domain.VoiceEventHandleApprove;

/**
 * @author LLM
 *
 */
public interface VoiceEventHandleApproveRepository extends SimpleDomainRepository<VoiceEventHandleApprove, Long> {

    /**
     * 根据事件审批流程编号查询审批环节
     *
     * @param rmEventHandleId
     * @return
     */
    List<VoiceEventHandleApprove> findByRmEventHandleId(Long rmEventHandleId);

    /**
     * 根据原系统的事件办理环节编号查询审批环节
     * 
     * @param rmHandleApproveId
     * @return
     */
    VoiceEventHandleApprove findByRmHandleApproveId(Long rmHandleApproveId);
}
