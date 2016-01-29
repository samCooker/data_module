/*
 * FileName:    VoiceEventServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月15日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.voice.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.user.service.SysUserService;
import cn.com.chaochuang.common.util.JsonMapper;
import cn.com.chaochuang.common.util.NullBeanUtils;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.datacenter.domain.SysDataChangeVoice;
import cn.com.chaochuang.datacenter.reference.OperationType;
import cn.com.chaochuang.voice.bean.VoiceEventHandleApproveInfo;
import cn.com.chaochuang.voice.bean.VoiceEventHandleOpinionInfo;
import cn.com.chaochuang.voice.bean.VoiceEventPendingInfo;
import cn.com.chaochuang.voice.bean.VoiceInfoPendingInfo;
import cn.com.chaochuang.voice.domain.VoiceEvent;
import cn.com.chaochuang.voice.domain.VoiceEventHandle;
import cn.com.chaochuang.voice.domain.VoiceEventHandleApprove;
import cn.com.chaochuang.voice.domain.VoiceEventHandleOpinion;
import cn.com.chaochuang.voice.domain.VoiceInfoEvent;
import cn.com.chaochuang.voice.reference.VoiceEventOpinionType;
import cn.com.chaochuang.voice.reference.VoiceEventStatus;
import cn.com.chaochuang.voice.repository.VoiceEventFordoRepository;
import cn.com.chaochuang.voice.repository.VoiceEventHandleApproveRepository;
import cn.com.chaochuang.voice.repository.VoiceEventHandleOpinionRepository;
import cn.com.chaochuang.voice.repository.VoiceEventHandleRepository;
import cn.com.chaochuang.voice.repository.VoiceEventRepository;
import cn.com.chaochuang.voice.repository.VoiceInfoEventRepository;
import cn.com.chaochuang.voice.repository.VoiceInfoRepository;
import cn.com.chaochuang.webservice.server.IVoiceWebService;

/**
 * @author LLM
 *
 */
@Service
@Transactional
public class VoiceEventServiceImpl extends SimpleLongIdCrudRestService<VoiceEvent> implements VoiceEventService {
    @Autowired
    private VoiceEventRepository              repository;
    @Autowired
    private VoiceEventFordoRepository         eventFordoRepository;
    @Autowired
    private VoiceEventFordoService            eventFordoService;
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
    @Autowired
    private VoiceInfoRepository               voiceInfoRepository;
    @Autowired
    private VoiceEventFordoService            voiceEventFordoService;
    @Autowired
    private IVoiceWebService                  voiceWebService;
    @Value("${getvoiceeventdata.timeinterval}")
    private String                            timeInterval;

    @Override
    public SimpleDomainRepository<VoiceEvent, Long> getRepository() {
        return repository;
    }

    /**
     * @see cn.com.chaochuang.voice.service.VoiceEventService#insertVoiceEvent(java.util.List)
     */
    @Override
    public void insertVoiceEvent(List<VoiceEventPendingInfo> pendingItems) {
        for (VoiceEventPendingInfo info : pendingItems) {
            insertVoiceEvent(info);
        }
    }

    /**
     * 新增舆情事件
     *
     * @param pendingItem
     */
    private void insertVoiceEvent(VoiceEventPendingInfo pendingItem) {
        // 保存VoiceEvent对象，再保存VoiceInfo、VoiceInfoAttach、VoiceEventHandle等对象
        VoiceEvent event;
        Map<Long, VoiceEventHandleApprove> fordoApproves = Maps.newHashMap();
        // 若数据库中已经有相同的事件则不做处理
        event = this.repository.findOne(pendingItem.getRmEventId());
        if (event != null) {
            return;
        }
        event = new VoiceEvent();
        try {
            NullBeanUtils.copyProperties(event, pendingItem);
            this.repository.save(event);
            // 将createrId改成userId，原系统对应的userInfoId
            event.setCreaterId(this.userService.selectUserIdByInfoId(event.getCreaterId()));
            // 若rmEventHandleId不为空则表示有审批记录，保存VoiceEventHandle等对象
            if (pendingItem.getRmEventHandleId() != null) {
                VoiceEventHandle eventHandle = new VoiceEventHandle();
                eventHandle.setRmEventId(pendingItem.getRmEventId());
                eventHandle.setRmEventHandleId(pendingItem.getRmEventHandleId());
                eventHandle.setStatus(pendingItem.getEventHandleStatus());
                eventHandle.setCreateTime(pendingItem.getEventHandleCreateTime());
                this.eventHandleRepository.save(eventHandle);
                if (Tools.isNotEmptyList(pendingItem.getVoiceEventHandleApproves())) {
                    // 保存审批意见对象，注意要将userInfoId改成userId
                    VoiceEventHandleApprove approve;
                    VoiceEventHandleOpinion opinion;
                    for (VoiceEventHandleApproveInfo approveInfo : pendingItem.getVoiceEventHandleApproves()) {
                        // 保存审批环节对象
                        approve = new VoiceEventHandleApprove();
                        NullBeanUtils.copyProperties(approve, approveInfo);
                        approve.setRmEventHandleId(pendingItem.getRmEventHandleId());
                        approve.setUserId(this.userService.selectUserIdByInfoId(approveInfo.getUserInfoId()));
                        approve.setAssigngeeId(this.userService.selectUserIdByInfoId(approve.getAssigngeeId()));
                        approve.setAssigngeeNode(this.userService.selectUserIdByInfoId(approve.getAssigngeeNode()));
                        this.eventHandleApproveRepository.save(approve);
                        // 保存审批意见对象
                        for (VoiceEventHandleOpinionInfo opinionInfo : approveInfo.getOpinions()) {
                            opinion = new VoiceEventHandleOpinion();
                            NullBeanUtils.copyProperties(opinion, opinionInfo);
                            this.eventHandleOpinionRepository.save(opinion);
                        }
                        // 若审批环节的状态为EVENT_STATE_NEW(1)或EVENT_STATE_HANDING(2)则判断最大的rmHandleApproveId是否比当前的rmHandleApproveId小，是则写入待办表
                        if (VoiceEventStatus.新建.getKey().equals(approve.getStatus()) || VoiceEventStatus.办理中.getKey().equals(approve.getStatus())) {
                            if (fordoApproves.containsKey(approve.getUserId()) && fordoApproves.get(approve.getUserId()).getRmHandleApproveId() < approve.getRmHandleApproveId()) {
                                fordoApproves.put(approve.getUserId(), approve);
                            } else if (!fordoApproves.containsKey(approve.getUserId())) {
                                fordoApproves.put(approve.getUserId(), approve);
                            }
                        }
                    }
                    // 若有待办保存记录则写入数据库
                    if (Tools.isNotEmptyMap(fordoApproves)) {
                        for (Iterator it = fordoApproves.entrySet().iterator(); it.hasNext();) {
                            Entry entry = (Entry) it.next();
                            this.voiceEventFordoService.saveVoiceEventFordo(event, (VoiceEventHandleApprove) entry.getValue());
                        }
                        fordoApproves = new HashMap();
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /**
     * @see cn.com.chaochuang.voice.service.VoiceEventService#updateVoiceInfoEvent(SysDataChangeVoice)
     */
    @Override
    public void updateVoiceInfoEvent(SysDataChangeVoice dataChange) {
        // 分解变更内容
        if (dataChange == null || Tools.isEmptyString(dataChange.getChangeScript())) {
            return;
        }
        // 获取要操作的事件和舆情信息编号
        String[] items = dataChange.getChangeScript().split(",");
        if (items == null || items.length != 2) {
            return;
        }
        Long infoId = Long.valueOf(items[0].split("=")[1]);
        Long eventId = Long.valueOf(items[1].split("=")[1]);
        try {
            // 删除相应的舆情事件映射记录
            if (OperationType.删除.getKey().equals(dataChange.getOperationType())) {
                VoiceInfoEvent voiceInfoEvent = this.voiceInfoEventRepository.findByRmVoiceEventIdAndRmVoiceInfoId(eventId, infoId);
                if (voiceInfoEvent != null) {
                    this.voiceInfoEventRepository.delete(voiceInfoEvent);
                }
                return;
            }
            // 新增映射记录，先校验事件是否存在，仅在事件存在情况下才检查舆情基础信息是否存在，存在直接添加映射记录，否则远程获取舆情基础信息后才添加映射记录
            if (this.repository.findByRmEventId(eventId) != null) {
                // 若存在相同记录则不做处理
                if (this.voiceInfoEventRepository.findByRmVoiceEventIdAndRmVoiceInfoId(eventId, infoId) != null) {
                    return;
                }
                if (this.voiceInfoRepository.findByRmInfoId(infoId) != null) {
                    // 直接添加映射记录
                    this.voiceInfoEventRepository.save(new VoiceInfoEvent(eventId, infoId));
                } else {
                    // 远程获取舆情基础信息后才添加映射记录
                    String addVoice = this.voiceWebService.selectVoiceInfo(infoId);
                    JsonMapper mapper = JsonMapper.getInstance();
                    if (StringUtils.isNotEmpty(addVoice)) {
                        VoiceInfoPendingInfo voice = mapper.readValue(addVoice, VoiceInfoPendingInfo.class);
                        if (this.voiceInfoService.insertVoiceInfo(voice)) {
                            // 添加映射记录
                            this.voiceInfoEventRepository.save(new VoiceInfoEvent(eventId, infoId));
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /**
     * @see cn.com.chaochuang.voice.service.VoiceEventService#saveVoiceEvent(SysDataChangeVoice)
     */
    @Override
    public void saveVoiceEvent(SysDataChangeVoice dataChange) {
        // 分解变更内容
        if (dataChange == null || Tools.isEmptyString(dataChange.getChangeScript())) {
            return;
        }
        // 获取要操作的事件和舆情信息编号
        String[] items = dataChange.getChangeScript().split("=");
        if (items == null || items.length != 2) {
            return;
        }
        Long eventId = Long.valueOf(items[1]);
        try {
            VoiceEvent event = this.repository.findByRmEventId(eventId);
            if (OperationType.修改.getKey().equals(dataChange.getOperationType())) {
                // 要更新的舆情事件必须存在
                if (event == null) {
                    return;
                }
                String addVoiceEvent = this.voiceWebService.selectVoiceEvent(eventId);
                JsonMapper mapper = JsonMapper.getInstance();
                if (StringUtils.isNotEmpty(addVoiceEvent)) {
                    VoiceEventPendingInfo eventInfo = mapper.readValue(addVoiceEvent, VoiceEventPendingInfo.class);
                    NullBeanUtils.copyProperties(event, eventInfo);
                    event.setCreaterId(this.userService.selectUserIdByInfoId(eventInfo.getCreaterId()));
                    this.repository.save(event);
                }
            } else if (OperationType.删除.getKey().equals(dataChange.getOperationType())) {
                // 事件删除,先删除voiceEventHandleOpinion、voiceEventHandleApprove、voiceEventHandle、voiceInfoEvent、voiceEvent
                List<VoiceEventHandle> eventHandles = this.eventHandleRepository.findByRmEventIdOrderByRmEventHandleIdDesc(eventId);
                if (Tools.isNotEmptyList(eventHandles)) {
                    this.eventHandleOpinionRepository.delete(this.eventHandleOpinionRepository.findByRmEventHandleId(eventHandles.get(0).getRmEventHandleId()));
                    this.eventHandleApproveRepository.delete(this.eventHandleApproveRepository.findByRmEventHandleId(eventHandles.get(0).getRmEventHandleId()));
                    this.eventHandleRepository.delete(eventHandles.get(0));
                }
                this.voiceInfoEventRepository.delete(this.voiceInfoEventRepository.findByRmVoiceEventId(eventId));
                if (event != null) {
                    this.repository.delete(event);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /**
     * @see cn.com.chaochuang.voice.service.VoiceEventService#saveVoiceEventHandleApprove(SysDataChangeVoice)
     */
    @Override
    public void saveVoiceEventHandleApprove(SysDataChangeVoice dataChange) {
        // 分解变更内容
        if (dataChange == null || Tools.isEmptyString(dataChange.getChangeScript())) {
            return;
        }
        // 获取要操作的事件审批编号
        Map<String, String> dataMap = Tools.splitData(dataChange.getChangeScript());
        // 若本地数据库中舆情事件不存在，则不进行任何操作
        if (dataMap.get("handle_man_id") == null) {
            return;
        }
        Long handleApproveId = Long.valueOf(dataMap.get("handle_man_id"));
        // 更新待办
        eventFordoService.updateVoiceEventFordo(handleApproveId);
        try {
            // 若本地数据库中舆情事件存在，则进行相关操作
            if (this.VoiceEventIsExist(dataMap)) {
                VoiceEventHandleApprove approve = this.eventHandleApproveRepository.findByRmHandleApproveId(handleApproveId);
                if (OperationType.新增.getKey().equals(dataChange.getOperationType())) {
                    // 若本地数据库中不存在则新建对象
                    if (approve == null) {
                        approve = new VoiceEventHandleApprove();
                        this.updateVoiceEventHandleApprove(handleApproveId, approve);
                    }
                } else if (OperationType.修改.getKey().equals(dataChange.getOperationType())) {
                    if (approve != null) {
                        this.updateVoiceEventHandleApprove(handleApproveId, approve);
                    }
                } else if (OperationType.删除.getKey().equals(dataChange.getOperationType())) {
                    if (approve == null) {
                        return;
                    }
                    List<VoiceEventHandleOpinion> opinionDatas = this.eventHandleOpinionRepository.findByRmHandleApproveId(handleApproveId);
                    // 删除审批意见集合
                    if (Tools.isNotEmptyList(opinionDatas)) {
                        this.eventHandleOpinionRepository.delete(opinionDatas);
                    }
                    // 删除审批环节
                    this.eventHandleApproveRepository.delete(approve);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /**
     * 查看本地数据库voiceEvent是否为空
     * 
     * @param dataMap
     * @return
     */
    private boolean VoiceEventIsExist(Map<String, String> dataMap) {
        String eventId = dataMap.get("event_id");
        if (eventId != null) {
            // 本地数据库有相关数据才更新
            VoiceEvent event = repository.findByRmEventId(new Long(eventId));
            if (event != null) {
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * 更新本地的舆情事件经办记录
     * 
     * @param handleApproveId
     * @param approve
     */
    private void updateVoiceEventHandleApprove(Long handleApproveId, VoiceEventHandleApprove approve) {
        JsonMapper mapper = JsonMapper.getInstance();
        String returnJsonData = this.voiceWebService.getVoiceEventHandleInfo(handleApproveId);
        if (!Tools.isEmptyString(returnJsonData)) {
            VoiceEventHandleApproveInfo approveInfo = mapper.readValue(returnJsonData, VoiceEventHandleApproveInfo.class);
            // 保存审批环节
            NullBeanUtils.copyProperties(approve, approveInfo);
            approve.setRmEventHandleId(approveInfo.getEventHandleId());
            approve.setUserId(this.userService.selectUserIdByInfoId(approveInfo.getUserInfoId()));
            approve.setAssigngeeId(this.userService.selectUserIdByInfoId(approveInfo.getAssigngeeId()));
            this.eventHandleApproveRepository.save(approve);
        }
    }

    /**
     * @see cn.com.chaochuang.voice.service.VoiceEventService#saveVoiceEventHandleOpinion(SysDataChangeVoice)
     */
    @Override
    public void saveVoiceEventHandleOpinion(SysDataChangeVoice dataChange) {
        // 分解变更内容
        if (dataChange == null || Tools.isEmptyString(dataChange.getChangeScript())) {
            return;
        }
        // 获取要操作的事件审批编号
        Map<String, String> dataMap = Tools.splitData(dataChange.getChangeScript());
        // 若本地数据库中舆情事件不存在，则不进行任何操作
        if (!this.VoiceEventIsExist(dataMap) || dataMap.get("handle_approve_id") == null) {
            return;
        }

        Long opinionId = Long.valueOf(dataMap.get("handle_approve_id"));
        List<String> typeList = Lists.newArrayList();
        typeList.add(VoiceEventOpinionType.拟办意见.getKey());
        typeList.add(VoiceEventOpinionType.经办意见.getKey());
        if (OperationType.新增.getKey().equals(dataChange.getOperationType())) {
            // 查询意见是否已经存在，不存在则根据意见id从原系统获取
            VoiceEventHandleOpinion opinion = this.eventHandleOpinionRepository.findByRmOpinionIdAndApproveTypeIn(opinionId, typeList);
            if (opinion != null) {
                return;
            }
            opinion = new VoiceEventHandleOpinion();
            this.saveVoiceEventHandleOpinionByRmOpinionId(opinion, opinionId);
        } else if (OperationType.修改.getKey().equals(dataChange.getOperationType())) {
            VoiceEventHandleOpinion opinion = this.eventHandleOpinionRepository.findByRmOpinionIdAndApproveTypeIn(opinionId, typeList);
            // 本地数据库有相关数据才进行修改
            if (opinion == null) {
                return;
            }
            this.saveVoiceEventHandleOpinionByRmOpinionId(opinion, opinionId);
        }
    }

    /**
     * 从原舆情系统获取办理意见保存
     * 
     * @param opinion
     * @param opinionId
     */
    private void saveVoiceEventHandleOpinionByRmOpinionId(VoiceEventHandleOpinion opinion, Long opinionId) {
        String addOpinion = this.voiceWebService.getVoiceEventHandleOpinion(opinionId);
        if (StringUtils.isNotBlank(addOpinion)) {
            JsonMapper mapper = JsonMapper.getInstance();
            VoiceEventHandleOpinionInfo opinionInfo = mapper.readValue(addOpinion, VoiceEventHandleOpinionInfo.class);
            NullBeanUtils.copyProperties(opinion, opinionInfo);
            this.eventHandleOpinionRepository.save(opinion);
        }

    }

    /**
     * @see cn.com.chaochuang.voice.service.VoiceEventService#updateVoiceEventRmEventId(java.lang.Long, java.lang.Long)
     */
    @Override
    public void updateVoiceEventRmEventId(Long rmEventId, Long eventId) {
        VoiceEvent event = this.repository.findOne(eventId);
        if (event == null) {
            return;
        }
        event.setRmEventId(rmEventId);
        this.repository.save(event);
    }

    /**
     * @see cn.com.chaochuang.voice.service.VoiceEventService#saveVoiceEventHandleResult(SysDataChangeVoice)
     */
    @Override
    public void saveVoiceEventHandleResult(SysDataChangeVoice dataChange) {
        // 分解变更内容
        if (dataChange == null || Tools.isEmptyString(dataChange.getChangeScript())) {
            return;
        }
        Map<String, String> dataMap = Tools.splitData(dataChange.getChangeScript());
        // 若本地数据库中舆情事件不存在，则不进行任何操作
        if (!this.VoiceEventIsExist(dataMap) || dataMap.get("handle_result_id") == null) {
            return;
        }

        Long opinionId = Long.valueOf(dataMap.get("handle_result_id"));
        List<String> typeList = Lists.newArrayList();
        typeList.add(VoiceEventOpinionType.处理情况.getKey());
        if (OperationType.新增.getKey().equals(dataChange.getOperationType())) {
            // 查询意见是否已经存在，不存在则根据意见id从原系统获取
            VoiceEventHandleOpinion opinion = this.eventHandleOpinionRepository.findByRmOpinionIdAndApproveTypeIn(opinionId, typeList);
            if (opinion != null) {
                return;
            }
            opinion = new VoiceEventHandleOpinion();
            String addOpinion = this.voiceWebService.getVoiceEventHandleResult(opinionId);
            if (StringUtils.isNotBlank(addOpinion)) {
                JsonMapper mapper = JsonMapper.getInstance();
                VoiceEventHandleOpinionInfo opinionInfo = mapper.readValue(addOpinion, VoiceEventHandleOpinionInfo.class);
                NullBeanUtils.copyProperties(opinion, opinionInfo);
                this.eventHandleOpinionRepository.save(opinion);
            }
        }

    }

}
