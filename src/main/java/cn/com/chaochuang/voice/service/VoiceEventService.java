/*
 * FileName:    VoiceEventService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月15日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.voice.service;

import java.util.List;

import cn.com.chaochuang.common.data.service.CrudRestService;
import cn.com.chaochuang.datacenter.domain.SysDataChangeVoice;
import cn.com.chaochuang.voice.bean.VoiceEventPendingInfo;
import cn.com.chaochuang.voice.domain.VoiceEvent;

/**
 * @author LLM
 *
 */
public interface VoiceEventService extends CrudRestService<VoiceEvent, Long> {

    /**
     * 保存舆情事件
     *
     * @param pendingItems
     */
    void insertVoiceEvent(List<VoiceEventPendingInfo> pendingItems);

    /**
     * 舆情事件内容变更
     *
     * @param dataChange
     */
    void updateVoiceInfoEvent(SysDataChangeVoice dataChange);

    /**
     * 舆情事件变更
     *
     * @param dataChange
     */
    void saveVoiceEvent(SysDataChangeVoice dataChange);

    /**
     * 舆情事件审批变更
     *
     * @param dataChange
     */
    void saveVoiceEventHandleApprove(SysDataChangeVoice dataChange);

    /**
     * 舆情事件审批意见变更
     *
     * @param dataChange
     */
    void saveVoiceEventHandleOpinion(SysDataChangeVoice dataChange);

    /**
     * 更新舆情事件的原系统舆情事件编号
     * 
     * @param rmEventId
     * @param eventId
     */
    void updateVoiceEventRmEventId(Long rmEventId, Long eventId);

    /**
     * 更新舆情事件的处理意见，与舆情审批意见同表，但approveFlag=2
     * 
     * @param item
     */
    void saveVoiceEventHandleResult(SysDataChangeVoice item);
}
