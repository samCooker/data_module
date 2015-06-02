/*
 * FileName:    AppFlowNodeInfoServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年6月2日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.appflow.service;

import javax.transaction.Transactional;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.appflow.domain.AppFlowNodeInfo;
import cn.com.chaochuang.appflow.repository.AppFlowNodeInfoRepository;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;

/**
 * @author LLM
 *
 */
@Service
@Transactional
public class AppFlowNodeInfoServiceImpl extends SimpleLongIdCrudRestService<AppFlowNodeInfo> implements
                AppFlowNodeInfoService {
    @Autowired
    private AppFlowNodeInfoRepository repository;

    @Override
    public SimpleDomainRepository<AppFlowNodeInfo, Long> getRepository() {
        return repository;
    }

    /**
     * @see cn.com.chaochuang.appflow.service.AppFlowNodeInfoService#saveFlowNodeInfo(cn.com.chaochuang.appflow.domain.AppFlowNodeInfo)
     */
    @Override
    public void saveFlowNodeInfo(AppFlowNodeInfo info) {
        AppFlowNodeInfo node = this.repository.findByRmNodeInfoId(info.getRmNodeInfoId());
        if (node != null) {
            try {
                BeanUtils.copyProperties(node, info);
                this.repository.save(node);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            this.repository.save(info);
        }
    }

}
