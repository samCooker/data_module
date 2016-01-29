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
import cn.com.chaochuang.datacenter.domain.SysDataChangeVoice;
import cn.com.chaochuang.voice.bean.VoiceInfoPendingInfo;
import cn.com.chaochuang.voice.domain.VoiceInfo;

/**
 * @author LLM
 *
 */
public interface VoiceInfoService extends CrudRestService<VoiceInfo, Long> {

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
    boolean insertVoiceInfo(VoiceInfoPendingInfo pending);

    /**
     * 更新舆情基础信息
     *
     * @param dataChange
     */
    void updateVoiceInfo(SysDataChangeVoice dataChange);

    /**
     * 删除舆情相关信息
     *
     * @param infoId
     */
    void deleteVoiceInfo(Long infoId);
}
