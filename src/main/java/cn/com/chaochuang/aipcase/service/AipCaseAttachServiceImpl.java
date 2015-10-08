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

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.aipcase.bean.AipCaseAttachInfo;
import cn.com.chaochuang.aipcase.domain.AipCaseAttach;
import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.aipcase.repository.AipCaseAttachRepository;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.util.NullBeanUtils;

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
                    // 保存新附件
                    attach = new AipCaseAttach();
                    attach.setLocalData(LocalData.非本地数据);
                    try {
                        NullBeanUtils.copyProperties(attach, info);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    // 更改旧附件名
                    attach.setTrueName(info.getTrueName());
                }
                if (deleteAttachList != null) {
                    // 存在的附件剔除出删除列表中
                    deleteAttachList.remove(attach);
                }
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
    public void saveAttachForLocal(AipCaseAttach attach, LocalData localData, String localFileName) {
        if (attach != null) {
            if (StringUtils.isNotBlank(localFileName)) {
                attach.setSavePath(localFileName);
                attach.setLocalData(LocalData.有本地数据);
            } else {
                attach.setLocalData(localData);
            }
            repository.save(attach);
        }
    }

}
