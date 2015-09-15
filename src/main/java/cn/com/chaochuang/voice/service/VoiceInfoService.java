/*
 * FileName:    VoiceInfoService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月11日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.voice.service;

import java.util.List;

import cn.com.chaochuang.common.data.service.CrudRestService;
import cn.com.chaochuang.voice.bean.VoiceInfoPendingInfo;
import cn.com.chaochuang.voice.domain.VoiceInfo;

/**
 * @author LLM
 *
 */
public interface VoiceInfoService extends CrudRestService<VoiceInfo, Long> {

    /**
     * 获取最大的数据导入时间
     *
     * @return
     */
    VoiceInfoPendingInfo selectMaxInputDate();

    /**
     * 批量保存舆情信息
     *
     * @param pendingItems
     */
    void insertVoiceInfo(List<VoiceInfoPendingInfo> pendingItems);

    /**
     * 保存舆情信息
     * 
     * @param pending
     */
    void insertVoiceInfo(VoiceInfoPendingInfo pending);
}
