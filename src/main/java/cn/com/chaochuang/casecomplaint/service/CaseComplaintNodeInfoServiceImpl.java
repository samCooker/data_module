/*
 * FileName:   CaseComplaintNodeInfoServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年09月10日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.casecomplaint.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.casecomplaint.bean.BaseCaseComplaintInfo;
import cn.com.chaochuang.casecomplaint.bean.FlowHisNode;
import cn.com.chaochuang.casecomplaint.domain.CaseComplaintNodeInfo;
import cn.com.chaochuang.casecomplaint.repository.CaseComplaintNodeInfoRepository;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.util.NullBeanUtils;

/**
 * @author Shicx
 *
 */
@Service
@Transactional
public class CaseComplaintNodeInfoServiceImpl extends SimpleLongIdCrudRestService<CaseComplaintNodeInfo> implements
                CaseComplaintNodeInfoService {
    @Autowired
    private CaseComplaintNodeInfoRepository repository;

    @Override
    public SimpleDomainRepository<CaseComplaintNodeInfo, Long> getRepository() {
        return repository;
    }

    @Override
    public void saveComplaintNodeInfo(BaseCaseComplaintInfo baseInfo) {
        if (baseInfo != null && baseInfo.getNodesList() != null) {
            for (FlowHisNode flowHisNode : baseInfo.getNodesList()) {
                // 查找已存在的节点
                CaseComplaintNodeInfo nodeInfo = repository.findByRmNodeInfoId(flowHisNode.getHisnoId());
                if (nodeInfo == null) {
                    // 新建节点
                    nodeInfo = new CaseComplaintNodeInfo();
                }
                NullBeanUtils.copyProperties(nodeInfo, flowHisNode);
                nodeInfo.setRmNodeInfoId(flowHisNode.getHisnoId());
                nodeInfo.setCaseComplaintId(baseInfo.getComplaintId());
                repository.save(nodeInfo);
            }
        }
    }

}
