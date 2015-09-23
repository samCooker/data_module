/*
 * FileName:    VoiceInfoServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月11日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.voice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.user.domain.SysUser;
import cn.com.chaochuang.common.user.service.SysUserService;
import cn.com.chaochuang.common.util.AttachUtils;
import cn.com.chaochuang.common.util.JsonMapper;
import cn.com.chaochuang.common.util.NullBeanUtils;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.datacenter.domain.SysDataChange;
import cn.com.chaochuang.datacenter.reference.OperationType;
import cn.com.chaochuang.voice.bean.VoiceInfoAffixItem;
import cn.com.chaochuang.voice.bean.VoiceInfoPendingInfo;
import cn.com.chaochuang.voice.domain.VoiceInfo;
import cn.com.chaochuang.voice.domain.VoiceInfoAttach;
import cn.com.chaochuang.voice.domain.VoiceInfoEvent;
import cn.com.chaochuang.voice.repository.VoiceInfoAttachRepository;
import cn.com.chaochuang.voice.repository.VoiceInfoEventRepository;
import cn.com.chaochuang.voice.repository.VoiceInfoRepository;
import cn.com.chaochuang.webservice.server.IVoiceWebService;

/**
 * @author LLM
 *
 */
@Service
@Transactional
public class VoiceInfoServiceImpl extends SimpleLongIdCrudRestService<VoiceInfo> implements VoiceInfoService {
    @PersistenceContext
    private EntityManager             entityManager;
    @Autowired
    private IVoiceWebService          voiceWebService;
    @Autowired
    private VoiceInfoRepository       repository;
    @Autowired
    private VoiceInfoAttachRepository attachRepository;
    @Autowired
    private VoiceInfoEventRepository  voiceInfoEventRepository;
    @Autowired
    private SysUserService            userService;
    @Value("${getvoiceinfodata.timeinterval}")
    private String                    timeInterval;

    @Override
    public SimpleDomainRepository<VoiceInfo, Long> getRepository() {
        return repository;
    }

    /**
     * @see cn.com.chaochuang.voice.service.VoiceInfoService#selectMaxInputDate()
     */
    @Override
    public VoiceInfoPendingInfo selectMaxInputDate() {
        VoiceInfoPendingInfo result = new VoiceInfoPendingInfo();
        StringBuffer sql = new StringBuffer(" select Max(rmInfoId) from ").append(VoiceInfo.class.getName());
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
     * @see cn.com.chaochuang.voice.service.VoiceInfoService#insertVoiceInfo(java.util.List)
     */
    @Override
    public void insertVoiceInfo(List<VoiceInfoPendingInfo> pendingItems) {
        for (VoiceInfoPendingInfo item : pendingItems) {
            this.insertVoiceInfo(item);
        }
    }

    /**
     * @see cn.com.chaochuang.voice.service.VoiceInfoService#insertVoiceInfo(cn.com.chaochuang.voice.bean.VoiceInfoPendingInfo)
     */
    @Override
    public boolean insertVoiceInfo(VoiceInfoPendingInfo pending) {
        // 判断当前记录是否已经存在,不存在的情况下才写入voiceInfo表
        VoiceInfo voiceInfo = this.repository.findByRmInfoId(pending.getRmInfoId());
        if (voiceInfo != null) {
            return false;
        }
        try {
            voiceInfo = new VoiceInfo();
            BeanUtils.copyProperties(pending, voiceInfo);
            // 设置为非本地数据，表示相关的附件信息没有下载到移动服务器
            voiceInfo.setLocalData(LocalData.非本地数据);
            SysUser user = this.userService.findByRmUserInfoId(voiceInfo.getVoiceInfoDiscoverUser());
            // 设置发布人的ID，原舆情系统用的是userInfoId，需要转成userId，在移动系统中都使用userId
            voiceInfo.setVoiceInfoDiscoverUser(user.getRmUserId());
            voiceInfo.setUnitOrgId(user.getDepartment().getAncestorDep());
            // 写入附件记录
            if (Tools.isNotEmptyList(pending.getAffixItems())) {
                VoiceInfoAttach attach;
                for (VoiceInfoAffixItem affixItem : pending.getAffixItems()) {
                    attach = new VoiceInfoAttach();
                    BeanUtils.copyProperties(affixItem, attach);
                    this.attachRepository.save(attach);
                }
            }
            this.repository.save(voiceInfo);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * @see cn.com.chaochuang.voice.service.VoiceInfoService#updateVoiceInfo(cn.com.chaochuang.datacenter.domain.SysDataChange)
     */
    @Override
    public void updateVoiceInfo(SysDataChange dataChange) {
        if (dataChange == null || Tools.isEmptyString(dataChange.getChangeScript())) {
            return;
        }
        // 获取要操作的ID
        String[] items = dataChange.getChangeScript().split("=");
        if (items == null || items.length != 2) {
            return;
        }
        JsonMapper mapper = JsonMapper.getInstance();
        VoiceInfo info = this.repository.findByRmInfoId(Long.valueOf(items[1]));
        // 若舆情信息存在则更新舆情
        if (info == null) {
            return;
        }
        // 删除相应的舆情信息
        if (OperationType.删除.getKey().equals(dataChange.getOperationType())) {
            this.deleteVoiceInfo(info.getId());
            return;
        }
        String updateInfo = this.voiceWebService.selectVoiceInfo(info.getRmInfoId());
        if (StringUtils.isNotEmpty(updateInfo)) {
            VoiceInfoPendingInfo updateVoice = mapper.readValue(updateInfo, VoiceInfoPendingInfo.class);
            NullBeanUtils.copyProperties(info, updateVoice);
            info.setVoiceInfoDiscoverUser(this.userService.selectUserIdByInfoId(updateVoice.getVoiceInfoDiscoverUser()));
            repository.save(info);
            // 若有附件则保存附件
            if (Tools.isNotEmptyList(updateVoice.getAffixItems())) {
                VoiceInfoAttach attach;
                for (VoiceInfoAffixItem affixItem : updateVoice.getAffixItems()) {
                    if (this.attachRepository.findByRmAttachId(affixItem.getRmAttachId()) == null) {
                        attach = new VoiceInfoAttach();
                        BeanUtils.copyProperties(affixItem, attach);
                        this.attachRepository.save(attach);
                    }
                }
            }
        }
    }

    /**
     * @see cn.com.chaochuang.voice.service.VoiceInfoService#deleteVoiceInfo(java.lang.Long)
     */
    @Override
    public void deleteVoiceInfo(Long infoId) {
        VoiceInfo info = this.repository.findOne(infoId);
        if (info == null) {
            return;
        }
        // 删除附件
        if (!Tools.isEmptyString(info.getRmAffixId())) {
            List<VoiceInfoAttach> attachs = this.attachRepository.findByRmAffixId(info.getRmAffixId());
            for (VoiceInfoAttach attach : attachs) {
                if (LocalData.有本地数据.getKey().equals(attach.getLocalData())) {
                    AttachUtils.removeFile(attach.getSavePath());
                }
                this.attachRepository.delete(attach);
            }
        }
        // 删除事件舆情信息关联表
        List<VoiceInfoEvent> infoEvents = this.voiceInfoEventRepository.findByRmVoiceInfoId(infoId);
        if (Tools.isNotEmptyList(infoEvents)) {
            this.voiceInfoEventRepository.delete(infoEvents);
        }
        // 删除舆情
        this.repository.delete(info);
    }

}
