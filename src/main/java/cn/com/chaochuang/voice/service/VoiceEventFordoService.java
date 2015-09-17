/*
 * FileName:    VoiceEventFordoService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月17日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.voice.service;

import cn.com.chaochuang.common.data.service.CrudRestService;
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
}
