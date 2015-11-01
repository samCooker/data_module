/*
 * FileName:    DocFileServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月30日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.docwork.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.user.domain.SysUser;
import cn.com.chaochuang.common.user.repository.SysUserRepository;
import cn.com.chaochuang.common.util.NullBeanUtils;
import cn.com.chaochuang.docwork.domain.FlowNodeInfo;
import cn.com.chaochuang.docwork.repository.FlowNodeInfoRepository;
import cn.com.chaochuang.task.bean.FlowNodeBeanInfo;

/**
 * @author Shicx
 *
 */
@Service
@Transactional
public class FlowNodeInfoServiceImpl extends SimpleLongIdCrudRestService<FlowNodeInfo> implements FlowNodeInfoService {

    @Autowired
    private FlowNodeInfoRepository repository;
    @Autowired
    private SysUserRepository      userRepository;

    @Override
    public SimpleDomainRepository<FlowNodeInfo, Long> getRepository() {
        return repository;
    }

    /** 保存从远程取出的节点信息 */
    @Override
    public void saveRemoteFlowNodeInfo(List<FlowNodeBeanInfo> datas, Long fileId) {
        if (datas == null) {
            return;
        }
        List<FlowNodeInfo> nodesList = new ArrayList<FlowNodeInfo>();
        List<FlowNodeInfo> preNodesList = repository.findByDocId(fileId);
        // 删除原节点信息，因为如果OA端进行公文退回操作后，流程节点改动较大
        repository.deleteInBatch(preNodesList);
        for (FlowNodeBeanInfo nodeInfo : datas) {
            FlowNodeInfo node = new FlowNodeInfo();
            node.setRmPreInstnoId(nodeInfo.getRmLastInstnoId());
            NullBeanUtils.copyProperties(node, nodeInfo);
            SysUser user = userRepository.findByRmUserId(nodeInfo.getTransactId());
            if (user != null) {
                node.setTransactName(user.getUserName());
            }
            node.setDocId(fileId);
            nodesList.add(node);
        }
        repository.save(nodesList);

    }

    /**
     * (non-Javadoc)
     *
     * @see cn.com.chaochuang.docwork.service.FlowNodeInfoService#findByRmInstanceId(java.lang.String)
     */
    @Override
    public List<FlowNodeInfo> findByRmInstanceId(String rmInstanceId) {
        return repository.findByRmInstanceId(rmInstanceId);
    }

}
