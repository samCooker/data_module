/*
 * FileName:    FdFordoServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月22日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.docwork.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.docwork.domain.FdFordo;
import cn.com.chaochuang.docwork.reference.FordoSource;
import cn.com.chaochuang.docwork.reference.FordoStatus;
import cn.com.chaochuang.docwork.repository.FdFordoRepository;
import cn.com.chaochuang.task.bean.PendingCommandInfo;

/**
 * @author LLM
 *
 */
@Service
public class FdFordoServiceImpl extends SimpleLongIdCrudRestService<FdFordo> implements FdFordoService {
    @PersistenceContext
    private EntityManager     entityManager;

    @Autowired
    private FdFordoRepository repository;

    @Override
    public SimpleDomainRepository<FdFordo, Long> getRepository() {
        return repository;
    }

    /**
     * @see cn.com.chaochuang.docwork.service.FdFordoService#selectMaxInputDate()
     */
    @Override
    public PendingCommandInfo selectMaxInputDate() {
        PendingCommandInfo result = new PendingCommandInfo();
        StringBuffer sql = new StringBuffer(" select Max(rmPendingItemId) from ").append(FdFordo.class.getName());
        Query query = this.entityManager.createQuery(sql.toString());
        List datas = (ArrayList) query.getResultList();
        if (Tools.isNotEmptyList(datas)) {
            for (Object o : datas) {
                if (o != null) {
                    result.setRmPendingItemId(o.toString());
                    result.setLastSendTime("");
                    break;
                }
            }
        }
        if (result.getRmPendingItemId() == null) {
            Date sendTime = Tools.diffDate(new Date(), -30);
            result.setLastSendTime(Tools.DATE_TIME_FORMAT.format(sendTime));
            result.setRmPendingItemId("");
        }
        return result;
    }

    /**
     * @see cn.com.chaochuang.docwork.service.FdFordoService#insertFdFordos(java.util.List, FordoSource)
     */
    @Override
    public void insertFdFordos(List<PendingCommandInfo> pendingItems, FordoSource fordoSource) {
        List datas = new ArrayList();
        FdFordo fdFordo;
        Date currentDate = new Date();
        for (PendingCommandInfo item : pendingItems) {
            // 判断当前记录是否已经存在,不存在的情况下才写入fdfordo表
            datas = this.repository.findByRmPendingIdAndRecipientId(item.getRmPendingId(), item.getRecipientId());
            if (Tools.isNotEmptyList(datas)) {
                continue;
            }
            fdFordo = new FdFordo();
            BeanUtils.copyProperties(item, fdFordo);
            fdFordo.setFordoSource(fordoSource);
            if (item.getReadTime() == null) {
                fdFordo.setStatus(FordoStatus.未读);
            } else {
                fdFordo.setStatus(FordoStatus.已读);
            }
            fdFordo.setReadTime(item.getReadTime());
            fdFordo.setInputDate(currentDate);
            this.repository.save(fdFordo);
        }

    }
}
