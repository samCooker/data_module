/*
 * FileName:    VoiceAlarmRecordServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月21日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.voice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.user.service.SysUserService;
import cn.com.chaochuang.common.util.NullBeanUtils;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.voice.bean.VoiceAlarmRecordInfo;
import cn.com.chaochuang.voice.bean.VoiceInfoPendingInfo;
import cn.com.chaochuang.voice.domain.VoiceAlarmRecord;
import cn.com.chaochuang.voice.reference.AlarmPushFlag;
import cn.com.chaochuang.voice.repository.VoiceAlarmRecordRepository;

/**
 * @author LLM
 *
 */
@Service
@Transactional
public class VoiceAlarmRecordServiceImpl extends SimpleLongIdCrudRestService<VoiceAlarmRecord> implements
                VoiceAlarmRecordService {
    @PersistenceContext
    private EntityManager              entityManager;
    @Autowired
    private SysUserService             userService;
    @Autowired
    private VoiceAlarmRecordRepository repository;

    @Override
    public SimpleDomainRepository<VoiceAlarmRecord, Long> getRepository() {
        return repository;
    }

    /**
     * @see cn.com.chaochuang.voice.service.VoiceAlarmRecordService#selectMaxInputDate()
     */
    @Override
    public VoiceInfoPendingInfo selectMaxInputDate() {
        VoiceInfoPendingInfo result = new VoiceInfoPendingInfo();
        StringBuffer sql = new StringBuffer(" select Max(rmAlarmRecordId) from ").append(VoiceAlarmRecord.class
                        .getName());
        Query query = this.entityManager.createQuery(sql.toString());
        List datas = (ArrayList) query.getResultList();
        if (Tools.isNotEmptyList(datas)) {
            for (Object o : datas) {
                if (o != null) {
                    result.setRmPendingId(o.toString());
                    result.setLastSendTime(null);
                    break;
                }
            }
        }
        if (Tools.isEmptyString(result.getRmPendingId())) {
            Date sendTime = Tools.diffDate(new Date(), Integer.valueOf(-1));
            result.setLastSendTime(sendTime);
            result.setRmPendingId("");
        }
        return result;
    }

    /**
     * @see cn.com.chaochuang.voice.service.VoiceAlarmRecordService#saveVoiceAlarmRecord(java.util.List)
     */
    @Override
    public void saveVoiceAlarmRecord(List<VoiceAlarmRecordInfo> items) {
        VoiceAlarmRecord alarmRecord;
        for (VoiceAlarmRecordInfo item : items) {
            alarmRecord = new VoiceAlarmRecord();
            // 系统中已有的记录不能重复插入
            if (this.repository.findByRmAlarmRecordId(item.getRmAlarmRecordId()) != null) {
                continue;
            }
            NullBeanUtils.copyProperties(alarmRecord, item);
            // 将人员的userInfoId转成userId
            alarmRecord.setAlarmUserId(this.userService.selectUserIdByInfoId(item.getAlarmUserId()));
            alarmRecord.setPushFlag(AlarmPushFlag.未推送);
            this.repository.save(alarmRecord);
        }
    }

    /**
     * @see cn.com.chaochuang.voice.service.VoiceAlarmRecordService#selectPushRecord()
     */
    @Override
    public List<VoiceAlarmRecord> selectPushRecord() {
        return this.repository.findByPushFlag(AlarmPushFlag.未推送);
    }

    /**
     * @see cn.com.chaochuang.voice.service.VoiceAlarmRecordService#updateAlarmRecord(java.lang.Long)
     */
    @Override
    public void updateAlarmRecord(Long alarmRecordId) {
        VoiceAlarmRecord alarm = this.repository.findOne(alarmRecordId);
        if (alarm != null) {
            alarm.setPushFlag(AlarmPushFlag.已推送);
            alarm.setPushTime(new Date());
            this.repository.save(alarm);
        }
    }
}
