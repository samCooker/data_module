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

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
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

    @Override
    public SimpleDomainRepository<FlowNodeInfo, Long> getRepository() {
        return repository;
    }

    /** 保存从远程取出的附件信息 */
    @Override
    public void saveRemoteFlowNodeInfo(List<FlowNodeBeanInfo> datas, Long fileId) throws Exception {
        if (datas == null) {
            return;
        }
        List<FlowNodeInfo> nodesList = new ArrayList<FlowNodeInfo>();
        for (FlowNodeBeanInfo nodeInfo : datas) {
            FlowNodeInfo node = repository.findByRmInstnoId(nodeInfo.getRmInstnoId());
            if (node == null) {
                // 为空说明本地数据库无此信息，应添加
                node = new FlowNodeInfo();
                BeanUtils.copyProperties(node, nodeInfo);
                node.setDocId(fileId);
                nodesList.add(node);
            }
        }
        repository.save(nodesList);

    }

}
