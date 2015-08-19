/*
 * FileName:    DocFileServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月30日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.aipcase.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.aipcase.bean.AipCaseAttachInfo;
import cn.com.chaochuang.aipcase.domain.AipCaseAttach;
import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.aipcase.repository.AipCaseAttachRepository;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;

/**
 * @author LJX
 *
 */
@Service
@Transactional
public class AipCaseAttachServiceImpl extends SimpleLongIdCrudRestService<AipCaseAttach> implements
                AipCaseAttachService {

    @Autowired
    private AipCaseAttachRepository repository;

    @Override
    public SimpleDomainRepository<AipCaseAttach, Long> getRepository() {
        return repository;
    }

    @Override
    public void saveAttachments(List<AipCaseAttachInfo> attachInfos, Long rmCaseApplyId) {
        // 查找出所有该案件的附件
        List<AipCaseAttach> deleteAttachList = repository.findByRmCaseApplyId(rmCaseApplyId);
        if (attachInfos != null) {
            // 更新旧附件，保存新附件，删除不存在的附件
            for (AipCaseAttachInfo info : attachInfos) {
                AipCaseAttach attach = repository.findByRmAttachId(info.getRmAttachId());
                if (attach == null) {
                    attach = new AipCaseAttach();
                }
                if (deleteAttachList != null && attach != null) {
                    // 存在的附件剔除出删除列表中
                    deleteAttachList.remove(attach);
                }
                try {
                    BeanUtils.copyProperties(attach, info);
                } catch (Exception e) {
                    e.printStackTrace();

                }
                attach.setLocalData(LocalData.非本地数据);
                repository.save(attach);
            }
        }
        if (deleteAttachList != null) {
            // 删除多余的附件
            repository.deleteInBatch(deleteAttachList);
        }
    }

    @Override
    public List<AipCaseAttach> selectUnLocalAttach() {
        return repository.findByLocalData(LocalData.非本地数据);
    }

    @Override
    public void saveDocFileAttachForLocal(AipCaseAttach attach, String localFileName) {
        if (attach != null) {
            attach.setSavePath(localFileName);
            attach.setLocalData(LocalData.有本地数据);
            repository.save(attach);
        }
    }

}
