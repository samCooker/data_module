/*
 * FileName:    VoiceInfoAttachServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月21日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.voice.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.voice.domain.VoiceInfoAttach;
import cn.com.chaochuang.voice.repository.VoiceInfoAttachRepository;

/**
 * @author LLM
 *
 */
@Service
@Transactional
public class VoiceInfoAttachServiceImpl extends SimpleLongIdCrudRestService<VoiceInfoAttach> implements
                VoiceInfoAttachService {
    @Autowired
    private VoiceInfoAttachRepository repository;

    @Override
    public SimpleDomainRepository<VoiceInfoAttach, Long> getRepository() {
        return repository;
    }

    /**
     * @see cn.com.chaochuang.voice.service.VoiceInfoAttachService#selectUnLocalAttach()
     */
    @Override
    public List<VoiceInfoAttach> selectUnLocalAttach() {
        return this.repository.findByLocalData(LocalData.非本地数据);
    }

    /**
     * @see cn.com.chaochuang.voice.service.VoiceInfoAttachService#saveVoiceInfoAttachForLocal(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void saveVoiceInfoAttachForLocal(Long attachId, String newFilePath) {
        VoiceInfoAttach attach = this.repository.findOne(attachId);
        attach.setLocalData(LocalData.有本地数据);
        attach.setSavePath(newFilePath);
        this.repository.save(attach);
    }

}
