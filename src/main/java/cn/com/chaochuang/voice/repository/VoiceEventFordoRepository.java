/*
 * FileName:    VoiceEventFordoRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月17日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.voice.repository;

import java.util.List;

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

    /**
     * 根据办理环节id查找待办
     * 
     * @param rmHandleApproveId
     * @return
     */
    List<VoiceEventFordo> RmHandleApproveId(Long rmHandleApproveId);
}
