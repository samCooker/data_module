/*
 * FileName:    VoiceEventServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月15日 (LLM) 1.0 Create
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.user.service.SysUserService;
import cn.com.chaochuang.common.util.NullBeanUtils;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.voice.bean.VoiceEventHandleApproveInfo;
import cn.com.chaochuang.voice.bean.VoiceEventHandleOpinionInfo;
import cn.com.chaochuang.voice.bean.VoiceEventPendingInfo;
import cn.com.chaochuang.voice.bean.VoiceInfoPendingInfo;
import cn.com.chaochuang.voice.domain.VoiceEvent;
import cn.com.chaochuang.voice.domain.VoiceEventHandle;
import cn.com.chaochuang.voice.domain.VoiceEventHandleApprove;
import cn.com.chaochuang.voice.domain.VoiceEventHandleOpinion;
import cn.com.chaochuang.voice.domain.VoiceInfoEvent;
import cn.com.chaochuang.voice.repository.VoiceEventHandleApproveRepository;
import cn.com.chaochuang.voice.repository.VoiceEventHandleOpinionRepository;
import cn.com.chaochuang.voice.repository.VoiceEventHandleRepository;
import cn.com.chaochuang.voice.repository.VoiceEventRepository;
import cn.com.chaochuang.voice.repository.VoiceInfoEventRepository;

/**
 * @author LLM
 *
 */
@Service
@Transactional
public class VoiceEventServiceImpl extends SimpleLongIdCrudRestService<VoiceEvent> implements VoiceEventService {
    @PersistenceContext
    private EntityManager                     entityManager;
    @Autowired
    private VoiceEventRepository              repository;
    @Autowired
    private VoiceEventHandleRepository        eventHandleRepository;
    @Autowired
    private VoiceEventHandleApproveRepository eventHandleApproveRepository;
    @Autowired
    private VoiceInfoEventRepository          voiceInfoEventRepository;
    @Autowired
    private VoiceEventHandleOpinionRepository eventHandleOpinionRepository;
    @Autowired
    private SysUserService                    userService;
    @Autowired
    private VoiceInfoService                  voiceInfoService;
    @Value("${getvoiceeventdata.timeinterval}")
    private String                            timeInterval;

    @Override
    public SimpleDomainRepository<VoiceEvent, Long> getRepository() {
        return repository;
    }

    /**
     * @see cn.com.chaochuang.voice.service.VoiceEventService#selectMaxInputDate()
     */
    @Override
    public VoiceEventPendingInfo selectMaxInputDate() {
        VoiceEventPendingInfo result = new VoiceEventPendingInfo();
        StringBuffer sql = new StringBuffer(" select Max(rmEventId) from ").append(VoiceEvent.class.getName());
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
            Date sendTime = Tools.diffDate(new Date(), new Integer(timeInterval));
            result.setLastSendTime(sendTime);
            result.setRmPendingId("");
        }
        return result;
    }

    /**
     * @see cn.com.chaochuang.voice.service.VoiceEventService#insertVoiceEvent(java.util.List)
     */
    @Override
    public void insertVoiceEvent(List<VoiceEventPendingInfo> pendingItems) {
        // 保存VoiceEvent对象，再保存VoiceInfo、VoiceInfoAttach、VoiceEventHandle等对象
        VoiceEvent event;
        for (VoiceEventPendingInfo info : pendingItems) {
            // 若数据库中已经有相同的事件则不做处理
            event = this.repository.findOne(info.getRmEventId());
            if (event != null) {
                continue;
            }
            event = new VoiceEvent();
            try {
                NullBeanUtils.copyProperties(event, info);
                this.repository.save(event);
                // 将createrId改成userId，原系统对应的userInfoId
                event.setCreaterId(this.userService.selectUserIdByInfoId(event.getCreaterId()));
                // 保存VoiceInfo、VoiceInfoAttach
                if (Tools.isNotEmptyList(info.getVoiceInfos())) {
                    // 先查询是否存在VoiceInfo对象，若存在则跳过，仅插入不存在的舆情信息
                    for (VoiceInfoPendingInfo voiceItem : info.getVoiceInfos()) {
                        this.voiceInfoService.insertVoiceInfo(voiceItem);
                        this.voiceInfoEventRepository.save(new VoiceInfoEvent(event.getRmEventId(), voiceItem
                                        .getRmInfoId()));
                    }
                }
                // 若rmEventHandleId不为空则表示有审批记录，保存VoiceEventHandle等对象
                if (info.getRmEventHandleId() != null) {
                    VoiceEventHandle eventHandle = new VoiceEventHandle();
                    eventHandle.setRmEventId(info.getRmEventId());
                    eventHandle.setRmEventHandleId(info.getRmEventHandleId());
                    eventHandle.setStatus(info.getEventHandleStatus());
                    eventHandle.setCreateTime(info.getEventHandleCreateTime());
                    this.eventHandleRepository.save(eventHandle);
                    if (Tools.isNotEmptyList(info.getVoiceEventHandleApproves())) {
                        // 保存审批意见对象，注意要将userInfoId改成userId
                        VoiceEventHandleApprove approve;
                        VoiceEventHandleOpinion opinion;
                        for (VoiceEventHandleApproveInfo approveInfo : info.getVoiceEventHandleApproves()) {
                            // 保存审批环节对象
                            approve = new VoiceEventHandleApprove();
                            NullBeanUtils.copyProperties(approve, approveInfo);
                            approve.setRmEventHandleId(info.getRmEventHandleId());
                            approve.setUserId(this.userService.selectUserIdByInfoId(approveInfo.getUserInfoId()));
                            approve.setAssigngeeId(this.userService.selectUserIdByInfoId(approve.getAssigngeeId()));
                            approve.setAssigngeeNode(this.userService.selectUserIdByInfoId(approve.getAssigngeeNode()));
                            this.eventHandleApproveRepository.save(approve);
                            // 保存审批意见对象
                            for (VoiceEventHandleOpinionInfo opinionInfo : approveInfo.getOpinions()) {
                                opinion = new VoiceEventHandleOpinion();
                                NullBeanUtils.copyProperties(opinion, opinionInfo);
                                opinion.setRmEventHandleId(approve.getRmEventHandleId());
                                opinion.setRmHandleApproveId(approve.getRmHandleApproveId());
                                this.eventHandleOpinionRepository.save(opinion);
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
        }
    }
}
