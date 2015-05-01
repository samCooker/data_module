/*
 * FileName:    FlowTransactPersonalServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年4月30日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.docwork.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.docwork.domain.DocFile;
import cn.com.chaochuang.docwork.domain.FlowTransactPersonal;
import cn.com.chaochuang.docwork.repository.FlowTransactPersonalRepository;
import cn.com.chaochuang.task.bean.FlowNodeBeanInfo;

/**
 * @author Shicx
 *
 */
@Service
@Transactional
public class FlowTransactPersonalServiceImpl extends SimpleLongIdCrudRestService<FlowTransactPersonal> implements
                FlowTransactPersonalService {

    @Autowired
    private FlowTransactPersonalRepository repository;

    @Override
    public SimpleDomainRepository<FlowTransactPersonal, Long> getRepository() {
        return repository;
    }

    /**
     * (non-Javadoc)
     *
     * @see cn.com.chaochuang.docwork.service.FlowTransactPersonalService#saveFlowTransactPersonalInfo(java.util.List)
     */
    @Override
    public void saveFlowTransactPersonalInfo(List<FlowNodeBeanInfo> flowNodeInfoList, DocFile file) throws Exception {
        if (flowNodeInfoList != null) {
            for (FlowNodeBeanInfo nodeInfo : flowNodeInfoList) {
                FlowTransactPersonal personalRecord = repository.findByDocFileAndTransactId(file,
                                nodeInfo.getTransactId());
                // 记录不能重复，为空才添加
                if (personalRecord == null) {
                    personalRecord = new FlowTransactPersonal();
                    personalRecord.setDocFile(file);
                    personalRecord.setRmInstanceId(nodeInfo.getRmInstanceId());
                    personalRecord.setTransactId(nodeInfo.getTransactId());
                } else {
                    // 取最近的办理时间
                    personalRecord.setTransactTime(nodeInfo.getArriveTime());
                }
                repository.save(personalRecord);
            }
        }
    }

}
