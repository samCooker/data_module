/*
 * FileName:    FdFordoCompServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2016
 * History:     2016年3月31日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.common.fdfordo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.fdfordo.domain.FdFordoComp;
import cn.com.chaochuang.common.fdfordo.repository.FdFordoCompRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author LLM
 *
 */
@Service
@Transactional
public class FdFordoCompServiceImpl extends SimpleLongIdCrudRestService<FdFordoComp> implements FdFordoCompService {
    @Autowired
    private FdFordoCompRepository repository;
    @Value(value = "${fordocomp.count}")
    private Integer               fordoCount;

    @Override
    public SimpleDomainRepository<FdFordoComp, Long> getRepository() {
        return repository;
    }

    /**
     * @see cn.com.chaochuang.common.fdfordo.service.FdFordoCompService#selectFdFordoCompByRecipient(java.lang.Long)
     */
    @Override
    public List<FdFordoComp> selectFdFordoCompByRecipient(Long recipientId) {
        return this.repository.findByRecipientIdOrderBySendTimeDesc(recipientId);
    }

    /**
     * @see cn.com.chaochuang.common.fdfordo.service.FdFordoCompService#saveFdFordoComp(cn.com.chaochuang.common.fdfordo.domain.FdFordoComp)
     */
    @Override
    public void saveFdFordoComp(FdFordoComp fordo) {
        // 每个用户的综合待办记录的条数有限制，若用户的新待办超过规定条数则先删除最后一条再增加新记录
        List<FdFordoComp> datas = this.repository.findByRecipientIdOrderBySendTimeDesc(fordo.getRecipientId());
        if (fordoCount.intValue() <= datas.size()) {
            // 限定条数已经满要删除最后一条记录，才能添加
            this.repository.delete(datas.get(datas.size() - 1));
        }
        this.repository.save(fordo);
    }

}
