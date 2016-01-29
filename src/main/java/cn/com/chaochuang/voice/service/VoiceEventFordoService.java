/*
 * FileName:    VoiceEventFordoService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月17日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.voice.service;

import java.util.List;

import cn.com.chaochuang.aipcase.bean.AipCasePendingHandleInfo;
import cn.com.chaochuang.common.data.service.CrudRestService;
import cn.com.chaochuang.voice.bean.VoiceEventFordoData;
import cn.com.chaochuang.voice.domain.VoiceEvent;
import cn.com.chaochuang.voice.domain.VoiceEventFordo;
import cn.com.chaochuang.voice.domain.VoiceEventHandleApprove;

/**
 * @author LLM
 *
 */
public interface VoiceEventFordoService extends CrudRestService<VoiceEventFordo, Long> {

    /**
     * 保存事件待办记录
     * 
     * @param event
     * @param approve
     */
    void saveVoiceEventFordo(VoiceEvent event, VoiceEventHandleApprove approve);

    /**
     * 根据流程更新待办信息
     * 
     * @param rmEventHandleId
     */
    // void updateEventFordo(Long rmEventHandleId);

    /**
     * 获取最大的待办事件id,若无则返回一个获取待办的起始时间
     * 
     * @return
     */
    AipCasePendingHandleInfo selectMaxInputDate();

    /**
     * 保存舆情待办事件
     * 
     * @param data
     */
    void saveVoiceEventFordos(List<VoiceEventFordoData> data);

    /**
     * 保存舆情待办事件
     * 
     * @param fordoData
     */
    void saveVoiceEventFordo(VoiceEventFordoData fordoData);

    /**
     * 更新待办
     * 
     * @param handleApproveId
     */
    void updateVoiceEventFordo(Long handleApproveId);
}
