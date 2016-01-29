/*
 * FileName:    VoiceEventFordoServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月17日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.voice.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.aipcase.bean.AipCasePendingHandleInfo;
import cn.com.chaochuang.common.beancopy.BeanCopyBuilder;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.user.domain.SysUser;
import cn.com.chaochuang.common.user.repository.SysUserRepository;
import cn.com.chaochuang.common.util.JsonMapper;
import cn.com.chaochuang.common.util.NullBeanUtils;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.docwork.reference.FordoStatus;
import cn.com.chaochuang.voice.bean.VoiceEventFordoData;
import cn.com.chaochuang.voice.domain.VoiceEvent;
import cn.com.chaochuang.voice.domain.VoiceEventFordo;
import cn.com.chaochuang.voice.domain.VoiceEventHandle;
import cn.com.chaochuang.voice.domain.VoiceEventHandleApprove;
import cn.com.chaochuang.voice.repository.VoiceEventFordoRepository;
import cn.com.chaochuang.voice.repository.VoiceEventHandleRepository;
import cn.com.chaochuang.webservice.server.IVoiceWebService;

/**
 * @author LLM
 *
 */
@Service
@Transactional
public class VoiceEventFordoServiceImpl extends SimpleLongIdCrudRestService<VoiceEventFordo> implements VoiceEventFordoService {
    @Autowired
    private VoiceEventFordoRepository  repository;
    @Autowired
    private SysUserRepository          userRepository;
    @Autowired
    private VoiceEventHandleRepository eventHandleRepository;
    @Autowired
    private IVoiceWebService           voiceWebService;
    @Value("${getvoiceinfodata.timeinterval}")
    private String                     timeInterval;

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
    // @Override
    // public void updateEventFordo(Long rmEventHandleId) {
    // List<VoiceEventHandleApprove> approveList = eventHandleApproveRepository.findByRmEventHandleId(rmEventHandleId);
    // if (approveList != null) {
    // // 根据流程状态更新待办信息
    // for (VoiceEventHandleApprove handleApprove : approveList) {
    // List<VoiceEventFordo> fordoList = repository.RmHandleApproveId(handleApprove.getRmHandleApproveId());
    // if (VoiceEventStatus.新建.getKey().equals(handleApprove.getStatus()) ||
    // VoiceEventStatus.办理中.getKey().equals(handleApprove.getStatus())) {
    // // 根据rmHandleApproveId查找待办中是否有相关记录，没有则添加,有则将状态更新为未读
    // if (Tools.isEmptyList(fordoList)) {
    // VoiceEventFordo fordo = new VoiceEventFordo();
    // VoiceEventHandle eventHandle = eventHandleRepository.findByRmEventHandleId(handleApprove.getRmEventHandleId());
    // VoiceEvent voiceEvent = voiceEventRepository.findByRmEventId(eventHandle.getRmEventId());
    // if (voiceEvent != null) {
    // NullBeanUtils.copyProperties(fordo, voiceEvent);
    // }
    // NullBeanUtils.copyProperties(fordo, handleApprove);
    // fordo.setFordoStatus(FordoStatus.未读);
    // repository.save(fordo);
    // } else {
    // for (VoiceEventFordo fordo : fordoList) {
    // fordo.setFordoStatus(FordoStatus.未读);
    // repository.save(fordo);
    // }
    // }
    // } else {
    // // 标记已经办理过的待办的提交状态为已提交
    // if (fordoList != null) {
    // for (VoiceEventFordo fordo : fordoList) {
    // fordo.setFordoStatus(FordoStatus.已提交);
    // repository.save(fordo);
    // }
    // }
    // }
    // }
    // }
    //
    // }

    /**
     * @see cn.com.chaochuang.voice.service.VoiceEventFordoService#selectMaxInputDate()
     */
    @Override
    public AipCasePendingHandleInfo selectMaxInputDate() {
        AipCasePendingHandleInfo result = new AipCasePendingHandleInfo();
        String pendingId = repository.findMaxRmPendingId();
        if (Tools.isEmptyString(pendingId)) {
            Date sendTime = Tools.diffDate(new Date(), new Integer(timeInterval));
            result.setLastSendTime(sendTime);
            result.setRmPendingId("");
        } else {
            result.setRmPendingId(pendingId);
            result.setLastSendTime(null);
        }
        return result;
    }

    /**
     * @see cn.com.chaochuang.voice.service.VoiceEventFordoService#saveVoiceEventFordos(java.util.List)
     */
    @Override
    public void saveVoiceEventFordos(List<VoiceEventFordoData> data) {
        if (data != null) {
            for (VoiceEventFordoData fordo : data) {
                this.saveVoiceEventFordo(fordo);
            }
        }

    }

    /**
     * @see cn.com.chaochuang.voice.service.VoiceEventFordoService#saveVoiceEventFordo(cn.com.chaochuang.voice.domain.VoiceEventFordo)
     */
    @Override
    public void saveVoiceEventFordo(VoiceEventFordoData fordoData) {
        VoiceEventFordo oldFordo = repository.findByRmHandleApproveId(fordoData.getRmHandleApproveId());
        // 待办不存在，则写入数据库
        if (oldFordo == null) {
            VoiceEventFordo fordo = BeanCopyBuilder.buildObject(fordoData, VoiceEventFordo.class);
            fordo.setFordoStatus(FordoStatus.未读);
            // 未读数据添加消息推送
            SysUser user = userRepository.findByRmUserInfoId(fordo.getUserId());
            if (user != null) {
                // 将userInfoId转成userId
                fordo.setUserId(user.getRmUserId());
                // 若待办接收用户存在且消息推送注册号不为空则发送推送消息
                if (Tools.isEmptyString(user.getRegistrationId())) {
                    // JPushUtils.pushByRegistrationID(user.getRegistrationId(), "您有一条新的待办事宜请查收：" +
                    // fordo.getTitle());
                }
            }
            repository.save(fordo);
            //
            // List<VoiceEventHandle> eventHandleList =
            // eventHandleRepository.findByRmEventHandleId(fordoData.getEventHandleId());
            // if (Tools.isEmptyList(eventHandleList)) {
            // // 保存经办记录表
            // VoiceEventHandle eventHandle = new VoiceEventHandle();
            // NullBeanUtils.copyProperties(eventHandle, fordoData);
            // eventHandle.setRmEventHandleId(fordoData.getEventHandleId());
            // eventHandleRepository.save(eventHandle);
            // }
        }

    }

    /**
     * @see cn.com.chaochuang.voice.service.VoiceEventFordoService#updateVoiceEventFordo(java.lang.Long)
     */
    @Override
    public void updateVoiceEventFordo(Long handleApproveId) {
        String returnData = voiceWebService.updateVoiceEventFordo(handleApproveId);
        JsonMapper mapper = JsonMapper.getInstance();
        if (StringUtils.isEmpty(returnData)) {
            return;
        }
        VoiceEventFordoData fordoData = mapper.readValue(returnData, VoiceEventFordoData.class);
        // 流程修改，删除旧待办
        VoiceEventFordo eventFordo = repository.findByRmHandleApproveId(handleApproveId);
        if (eventFordo != null) {
            repository.delete(eventFordo);
        }
        // 流程新增，进入下一步，新增待办
        if (fordoData.getRmHandleApproveId() != null) {
            this.saveVoiceEventFordo(fordoData);
        }
        // 保存办理流程(仅status属性可能会改变)
        List<VoiceEventHandle> handleList = this.eventHandleRepository.findByRmEventHandleId(fordoData.getEventHandleId());
        if (handleList != null) {
            for (VoiceEventHandle handle : handleList) {
                handle.setStatus(fordoData.getStatus());
                this.eventHandleRepository.save(handle);
            }
        }
    }

}
