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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.aipcase.bean.AipCaseNodes;
import cn.com.chaochuang.aipcase.domain.AipCaseNodeInfo;
import cn.com.chaochuang.aipcase.repository.AipCaseNodeInfoRepository;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.util.NullBeanUtils;

/**
 * @author LJX
 *
 */
@Service
@Transactional
public class AipCaseNodeInfoServiceImpl extends SimpleLongIdCrudRestService<AipCaseNodeInfo> implements
                AipCaseNodeInfoService {

    @Autowired
    private AipCaseNodeInfoRepository repository;

    @Override
    public SimpleDomainRepository<AipCaseNodeInfo, Long> getRepository() {
        return repository;
    }

    @Override
    public void saveNodeInfos(List<AipCaseNodes> nodeInfos) {
        if (nodeInfos == null) {
            return;
        }
        for (AipCaseNodes node : nodeInfos) {
            AipCaseNodeInfo aipCaseNodeInfo = repository.findByRmNodeId(node.getRmNodeId());
            if (aipCaseNodeInfo == null) {
                aipCaseNodeInfo = new AipCaseNodeInfo();
            }
            NullBeanUtils.copyProperties(aipCaseNodeInfo, node);
            repository.save(aipCaseNodeInfo);
        }
    }

}
