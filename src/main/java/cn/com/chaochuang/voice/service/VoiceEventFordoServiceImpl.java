/*
 * FileName:    VoiceEventFordoServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月17日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.voice.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.util.NullBeanUtils;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.docwork.reference.FordoStatus;
import cn.com.chaochuang.voice.domain.VoiceEvent;
import cn.com.chaochuang.voice.domain.VoiceEventFordo;
import cn.com.chaochuang.voice.domain.VoiceEventHandle;
import cn.com.chaochuang.voice.domain.VoiceEventHandleApprove;
import cn.com.chaochuang.voice.reference.VoiceEventStatus;
import cn.com.chaochuang.voice.repository.VoiceEventFordoRepository;
import cn.com.chaochuang.voice.repository.VoiceEventHandleApproveRepository;
import cn.com.chaochuang.voice.repository.VoiceEventHandleRepository;
import cn.com.chaochuang.voice.repository.VoiceEventRepository;

/**
 * @author LLM
 *
 */
@Service
@Transactional
public class VoiceEventFordoServiceImpl extends SimpleLongIdCrudRestService<VoiceEventFordo> implements VoiceEventFordoService {
    @Autowired
    private VoiceEventFordoRepository         repository;
    @Autowired
    private VoiceEventHandleRepository        eventHandleRepository;
    @Autowired
    private VoiceEventHandleApproveRepository eventHandleApproveRepository;
    @Autowired
    private VoiceEventRepository              voiceEventRepository;

    @Override
    public SimpleDomainRepository<VoiceEventFordo, Long> getRepository() {
        return repository;
    }

    /**
     * @see cn.com.chaochuang.voice.service.VoiceEventFordoService#saveVoiceEventFordo(cn.com.chaochuang.voice.domain.VoiceEvent,
     *      cn.com.chaochuang.voice.domain.VoiceEventHandleApprove)
     */
    @Override
    public void saveVoiceEventFordo(VoiceEvent event, VoiceEventHandleApprove approve) {
        // 判断数据库中有没有对应的待办记录，有则比较rmHandleApproveId的大小，若大于数据库的值则写入数据库覆盖原记录
        List<VoiceEventFordo> fordos = this.repository.findByRmEventIdAndUserIdOrderByRmHandleApproveIdDesc(event.getRmEventId(), approve.getUserId());
        VoiceEventFordo fordo = new VoiceEventFordo();
        if (Tools.isNotEmptyList(fordos)) {
            fordo = fordos.get(0);
            // 若数据库中的RmHandleApproveId大于待保存环节的值RmHandleApproveId，则无需保存
            if (fordo.getRmHandleApproveId() > approve.getRmHandleApproveId()) {
                return;
            }
        }
        NullBeanUtils.copyProperties(fordo, event);
        NullBeanUtils.copyProperties(fordo, approve);
        fordo.setFordoStatus(FordoStatus.未读);
        this.repository.save(fordo);
    }

    /**
     * @see cn.com.chaochuang.voice.service.VoiceEventFordoService#updateEventFordo(java.lang.Long)
     */
    @Override
    public void updateEventFordo(Long rmEventHandleId) {
        List<VoiceEventHandleApprove> approveList = eventHandleApproveRepository.findByRmEventHandleId(rmEventHandleId);
        if (approveList != null) {
            // 根据流程状态更新待办信息
            for (VoiceEventHandleApprove handleApprove : approveList) {
                List<VoiceEventFordo> fordoList = repository.RmHandleApproveId(handleApprove.getRmHandleApproveId());
                if (VoiceEventStatus.新建.getKey().equals(handleApprove.getStatus()) || VoiceEventStatus.办理中.getKey().equals(handleApprove.getStatus())) {
                    // 根据rmHandleApproveId查找待办中是否有相关记录，没有则添加,有则将状态更新为未读
                    if (Tools.isEmptyList(fordoList)) {
                        VoiceEventFordo fordo = new VoiceEventFordo();
                        VoiceEventHandle eventHandle = eventHandleRepository.findByRmEventHandleId(handleApprove.getRmEventHandleId());
                        VoiceEvent voiceEvent = voiceEventRepository.findByRmEventId(eventHandle.getRmEventId());
                        if (voiceEvent != null) {
                            NullBeanUtils.copyProperties(fordo, voiceEvent);
                        }
                        NullBeanUtils.copyProperties(fordo, handleApprove);
                        fordo.setFordoStatus(FordoStatus.未读);
                        repository.save(fordo);
                    } else {
                        for (VoiceEventFordo fordo : fordoList) {
                            fordo.setFordoStatus(FordoStatus.未读);
                            repository.save(fordo);
                        }
                    }
                } else {
                    // 标记已经办理过的待办的提交状态为已提交
                    if (fordoList != null) {
                        for (VoiceEventFordo fordo : fordoList) {
                            fordo.setFordoStatus(FordoStatus.已提交);
                            repository.save(fordo);
                        }
                    }
                }
            }
        }

    }

}
