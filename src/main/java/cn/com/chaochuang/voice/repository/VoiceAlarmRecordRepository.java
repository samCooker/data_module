/*
 * FileName:    VoiceAlarmRecordRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月21日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.voice.repository;

import java.util.List;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.voice.domain.VoiceAlarmRecord;
import cn.com.chaochuang.voice.reference.AlarmPushFlag;

/**
 * @author LLM
 *
 */
public interface VoiceAlarmRecordRepository extends SimpleDomainRepository<VoiceAlarmRecord, Long> {

    /**
     * 根据原系统提醒编号查询
     *
     * @param rmAlarmRecordId
     * @return
     */
    VoiceAlarmRecord findByRmAlarmRecordId(Long rmAlarmRecordId);

    /**
     * 获取待推送的记录
     * 
     * @param pushFlag
     * @return
     */
    List<VoiceAlarmRecord> findByPushFlag(AlarmPushFlag pushFlag);
}
