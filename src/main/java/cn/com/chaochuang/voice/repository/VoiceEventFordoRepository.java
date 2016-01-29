/*
 * FileName:    VoiceEventFordoRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月17日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.voice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.voice.domain.VoiceEventFordo;

/**
 * @author LLM
 *
 */
public interface VoiceEventFordoRepository extends SimpleDomainRepository<VoiceEventFordo, Long> {

    /**
     * 根据原系统事件编号和用户编号查询事件待办记录
     *
     * @param rmEventId
     *            原系统事件编号
     * @param userId
     *            用户编号
     * @return
     */
    List<VoiceEventFordo> findByRmEventIdAndUserIdOrderByRmHandleApproveIdDesc(Long rmEventId, Long userId);

    List<VoiceEventFordo> findByRmEventIdAndUserId(Long rmEventId, Long userId);

    /**
     * 根据办理环节id查找待办
     * 
     * @param rmHandleApproveId
     * @return
     */
    List<VoiceEventFordo> RmHandleApproveId(Long rmHandleApproveId);

    /**
     * @return
     */
    @Query(value = "select max(to_number(rm_event_id)) from voice_event_fordo", nativeQuery = true)
    String findMaxRmPendingId();

    /**
     * 正常情况下，返回的结果唯一
     * 
     * @param rmHandleApproveId
     */
    VoiceEventFordo findByRmHandleApproveId(Long rmHandleApproveId);
}
