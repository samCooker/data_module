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

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.docwork.domain.FlowNodeOpinions;
import cn.com.chaochuang.docwork.repository.FlowNodeOpinionsRepository;

/**
 * @author Shicx
 *
 */
@Service
public class FlowNodeOpinionsServiceImpl extends SimpleLongIdCrudRestService<FlowNodeOpinions> implements
                FlowNodeOpinionsService {

    @Autowired
    private FlowNodeOpinionsRepository repository;

    @Override
    public SimpleDomainRepository<FlowNodeOpinions, Long> getRepository() {
        return repository;
    }

    /** 保存远程获取的意见表 */
    @Override
    public void saveRemoteFlowNodeOpinions(List<FlowNodeOpinions> datas, Long fileId) throws Exception {
        if (datas == null) {
            return;
        }
        List<FlowNodeOpinions> opinionsList = new ArrayList<FlowNodeOpinions>();
        for (FlowNodeOpinions nodeOp : datas) {
            FlowNodeOpinions opinion = new FlowNodeOpinions();
            BeanUtils.copyProperties(opinion, nodeOp);
            opinion.setDocId(fileId);
            opinionsList.add(opinion);
        }
        repository.save(opinionsList);
    }

}
