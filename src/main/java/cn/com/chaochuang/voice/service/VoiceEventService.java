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
import cn.com.chaochuang.voice.bean.VoiceEventPendingInfo;
import cn.com.chaochuang.voice.domain.VoiceEvent;

/**
 * @author LLM
 *
 */
public interface VoiceEventService extends CrudRestService<VoiceEvent, Long> {
    /**
     * 获取最大的数据导入时间
     *
     * @return
     */
    VoiceEventPendingInfo selectMaxInputDate();

    /**
     * 保存舆情事件
     *
     * @param pendingItems
     */
    void insertVoiceEvent(List<VoiceEventPendingInfo> pendingItems);
}
