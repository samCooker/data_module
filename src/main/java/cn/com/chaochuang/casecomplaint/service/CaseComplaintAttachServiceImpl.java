/*
 * FileName:   CaseComplaintAttachServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年09月10日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.casecomplaint.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.casecomplaint.bean.BaseCaseComplaintInfo;
import cn.com.chaochuang.casecomplaint.bean.ComplaintAttachInfo;
import cn.com.chaochuang.casecomplaint.domain.CaseComplaintAttach;
import cn.com.chaochuang.casecomplaint.repository.CaseComplaintAttachRepository;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.util.NullBeanUtils;

/**
 * @author Shicx
 *
 */
@Service
@Transactional
public class CaseComplaintAttachServiceImpl extends SimpleLongIdCrudRestService<CaseComplaintAttach> implements
                CaseComplaintAttachService {
    @Autowired
    private CaseComplaintAttachRepository repository;

    @Override
    public SimpleDomainRepository<CaseComplaintAttach, Long> getRepository() {
        return repository;
    }

    @Override
    public void saveComplaintAttach(BaseCaseComplaintInfo baseInfo) {
        if (baseInfo != null && baseInfo.getAffixItemsList() != null) {
            Set<String> params = new HashSet();
            // 投诉举报附件标识
            if (baseInfo.getAffixId() != null) {
                params.add(baseInfo.getAffixId());
            }
            // 处理附件标识
            if (baseInfo.getWorkAffixId() != null) {
                params.add(baseInfo.getWorkAffixId());
            }
            // 查找出投诉举报附件和处理附件
            List<CaseComplaintAttach> oldAttachList = null;
            if (params.size() > 0) {
                oldAttachList = repository.findByRmAffixIdIn(params);
            }
            if (baseInfo.getAffixItemsList() != null) {
                for (ComplaintAttachInfo attachInfo : baseInfo.getAffixItemsList()) {
                    CaseComplaintAttach attach = repository.findByRmAttachId(attachInfo.getFileId());
                    if (attach == null) {
                        attach = new CaseComplaintAttach();
                        attach.setLocalData(LocalData.非本地数据);
                        NullBeanUtils.copyProperties(attach, attachInfo);
                        attach.setRmAttachId(attachInfo.getFileId());
                        attach.setRmAffixId(attachInfo.getAffixId());
                        repository.save(attach);
                    } else {
                        // 移除已存在的附件(若报空指针，则数据有问题)
                        oldAttachList.remove(attach);
                    }
                }
            }
            if (oldAttachList != null) {
                // 删除多余的附件
                repository.deleteInBatch(oldAttachList);
            }
        }

    }

    @Override
    public List<CaseComplaintAttach> findAttachByLocalData(LocalData localData, Pageable page) {
        return repository.findByLocalData(localData, page);
    }

    @Override
    public void saveAttachForLocal(CaseComplaintAttach attach, String localFileName) {
        if (attach != null) {
            attach.setLocalData(LocalData.有本地数据);
            attach.setSavePath(localFileName);// 修改保存路径
            repository.save(attach);
        }
    }

}
