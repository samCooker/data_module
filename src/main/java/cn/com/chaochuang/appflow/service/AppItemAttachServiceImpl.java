/*
 * FileName:    AppItemAttachServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年6月2日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.appflow.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.appflow.domain.AppItemAttach;
import cn.com.chaochuang.appflow.repository.AppItemAttachRepository;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.util.NullBeanUtils;

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

    /**
     * (non-Javadoc)
     *
     * @see cn.com.chaochuang.appflow.service.AppItemAttachService#selectUnLocalAttach()
     */
    @Override
    public List<AppItemAttach> selectUnLocalAttach() {
        return this.repository.findByLocalData(LocalData.非本地数据);
    }

    /**
     * (non-Javadoc)
     *
     * @see cn.com.chaochuang.appflow.service.AppItemAttachService#saveDocFileAttachForLocal(cn.com.chaochuang.appflow.domain.AppItemAttach,
     *      java.lang.String)
     */
    @Override
    public void saveDocFileAttachForLocal(AppItemAttach attach, String localFileName) {
        if (attach != null) {
            attach.setLocalData(LocalData.有本地数据);
            attach.setSavePath(localFileName);
            this.repository.save(attach);
        }
    }

    /**
     * (non-Javadoc)
     * 
     * @see cn.com.chaochuang.appflow.service.AppItemAttachService#saveAppItemAttach(java.util.List)
     */
    @Override
    public void saveAppItemAttach(List<AppItemAttach> attachList, Long rmItemApplyId) {
        // 查找出所有该案件的附件
        List<AppItemAttach> deleteAttachList = repository.findByItemApplyId(rmItemApplyId);
        if (attachList != null) {
            // 更新旧附件，保存新附件，删除不存在的附件
            for (AppItemAttach info : attachList) {
                AppItemAttach attach = this.repository.findByRmAttachId(info.getRmAttachId());
                if (attach == null) {
                    // 保存新附件
                    attach = new AppItemAttach();
                    NullBeanUtils.copyProperties(attach, info);
                    attach.setLocalData(LocalData.非本地数据);
                } else {
                    // 更改旧附件名
                    attach.setTrueName(info.getTrueName());
                }
                if (deleteAttachList != null && attach != null) {
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
}
