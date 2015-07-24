/*
 * FileName:    DocFileServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月30日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.aipcase.service;

import java.lang.reflect.InvocationTargetException;
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
    public void saveAttachments(List<AipCaseAttachInfo> attachInfos) {
        if (attachInfos == null) {
            return;
        }
        for (AipCaseAttachInfo info : attachInfos) {
            AipCaseAttach attach = repository.findByRmAttachId(info.getRmAttachId());
            if (attach == null) {
                attach = new AipCaseAttach();
            }
            try {
                BeanUtils.copyProperties(attach, info);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            attach.setLocalData(LocalData.非本地数据);
            repository.save(attach);

        }
    }

}
