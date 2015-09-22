/*
 * FileName:    VoiceAlarmRecordService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月21日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.voice.service;

import java.util.List;

import cn.com.chaochuang.common.data.service.CrudRestService;
import cn.com.chaochuang.voice.bean.VoiceAlarmRecordInfo;
import cn.com.chaochuang.voice.bean.VoiceInfoPendingInfo;
import cn.com.chaochuang.voice.domain.VoiceAlarmRecord;

/**
 * @author LLM
 *
 */
public interface VoiceAlarmRecordService extends CrudRestService<VoiceAlarmRecord, Long> {

    /**
     * 保存舆情信息提醒
     *
     * @param info
     */
    void saveVoiceAlarmRecord(List<VoiceAlarmRecordInfo> items);

    /**
     * 获取最大的数据导入时间
     *
     * @return
     */
    VoiceInfoPendingInfo selectMaxInputDate();

    /**
     * 获取待推送的记录
     *
     * @return
     */
    List<VoiceAlarmRecord> selectPushRecord();

    /**
     * 更新提醒记录的状态
     * 
     * @param alarmRecordId
     */
    void updateAlarmRecord(Long alarmRecordId);
}
