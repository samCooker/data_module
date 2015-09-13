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

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.user.domain.SysUser;
import cn.com.chaochuang.common.user.repository.SysUserRepository;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.voice.bean.VoiceInfoAffixItem;
import cn.com.chaochuang.voice.bean.VoiceInfoPendingInfo;
import cn.com.chaochuang.voice.domain.VoiceInfo;
import cn.com.chaochuang.voice.domain.VoiceInfoAttach;
import cn.com.chaochuang.voice.repository.VoiceInfoAttachRepository;
import cn.com.chaochuang.voice.repository.VoiceInfoRepository;

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
    private VoiceInfoRepository       repository;
    @Autowired
    private VoiceInfoAttachRepository attachRepository;
    @Autowired
    private SysUserRepository         userRepository;
    @Value("${getdata.timeinterval}")
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
        VoiceInfo voiceInfo;
        for (VoiceInfoPendingInfo item : pendingItems) {
            // 判断当前记录是否已经存在,不存在的情况下才写入voiceInfo表
            voiceInfo = this.repository.findByRmInfoId(item.getRmInfoId());
            if (voiceInfo != null) {
                continue;
            }
            voiceInfo = new VoiceInfo();
            BeanUtils.copyProperties(item, voiceInfo);
            // 设置为非本地数据，表示相关的附件信息没有下载到移动服务器
            voiceInfo.setLocalData(LocalData.非本地数据);
            // 设置发布人的ID，原舆情系统用的是userInfoId，需要转成userId，在移动系统中都使用userId
            SysUser user = userRepository.findByRmUserInfoId(voiceInfo.getVoiceInfoDiscoverUser());
            if (user != null) {
                voiceInfo.setVoiceInfoDiscoverUser(user.getId());
            }
            // 写入附件记录
            if (Tools.isNotEmptyList(item.getAffixItems())) {
                VoiceInfoAttach attach;
                for (VoiceInfoAffixItem affixItem : item.getAffixItems()) {
                    attach = new VoiceInfoAttach();
                    BeanUtils.copyProperties(affixItem, attach);
                    this.attachRepository.save(attach);
                }
            }
            this.repository.save(voiceInfo);
        }
    }
}
