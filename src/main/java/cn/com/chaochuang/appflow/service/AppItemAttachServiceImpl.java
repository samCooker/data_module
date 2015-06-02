/*
 * FileName:    AppItemAttachServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年6月2日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.appflow.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.appflow.domain.AppItemAttach;
import cn.com.chaochuang.appflow.repository.AppItemAttachRepository;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;

/**
 * @author LLM
 *
 */
@Service
@Transactional
public class AppItemAttachServiceImpl extends SimpleLongIdCrudRestService<AppItemAttach> implements
                AppItemAttachService {
    @Autowired
    private AppItemAttachRepository repository;

    @Override
    public SimpleDomainRepository<AppItemAttach, Long> getRepository() {
        return repository;
    }

    /**
     * @see cn.com.chaochuang.appflow.service.AppItemAttachService#saveAppItemAttach(cn.com.chaochuang.appflow.domain.AppItemAttach)
     */
    @Override
    public void saveAppItemAttach(AppItemAttach info) {
        AppItemAttach node = this.repository.findByRmAttachId(info.getRmAttachId());
        // 若数据库中无指定记录才进行新增操作
        if (node == null) {
            info.setLocalData(LocalData.非本地数据);
            this.repository.save(info);
        }
    }
}
