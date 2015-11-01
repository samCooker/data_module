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
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.jpush.util.JPushUtils;
import cn.com.chaochuang.common.user.domain.SysUser;
import cn.com.chaochuang.common.user.repository.SysUserRepository;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.docwork.domain.FdFordo;
import cn.com.chaochuang.docwork.reference.FordoSource;
import cn.com.chaochuang.docwork.reference.FordoStatus;
import cn.com.chaochuang.docwork.repository.FdFordoRepository;
import cn.com.chaochuang.task.bean.OAPendingHandleInfo;

/**
 * @author LLM
 *
 */
@Service
@Transactional
public class FdFordoServiceImpl extends SimpleLongIdCrudRestService<FdFordo> implements FdFordoService {
    @PersistenceContext
    private EntityManager     entityManager;

    @Autowired
    private FdFordoRepository repository;

    @Autowired
    private SysUserRepository userRepository;

    @Override
    public SimpleDomainRepository<FdFordo, Long> getRepository() {
        return repository;
    }

    @Value("${getdata.timeinterval}")
    private String timeInterval;

    /**
     * @see cn.com.chaochuang.docwork.service.FdFordoService#selectMaxInputDate(FordoSource)
     */
    @Override
    public OAPendingHandleInfo selectMaxInputDate(FordoSource source) {
        OAPendingHandleInfo result = new OAPendingHandleInfo();
        StringBuffer sql = new StringBuffer(" select Max(rmPendingItemId) from ").append(FdFordo.class.getName())
                        .append(" where fordoSource=").append(source);
        Query query = this.entityManager.createQuery(sql.toString());
        List datas = (ArrayList) query.getResultList();
        if (Tools.isNotEmptyList(datas)) {
            for (Object o : datas) {
                if (o != null) {
                    result.setRmPendingItemId(o.toString());
                    result.setLastSendTime(null);
                    break;
                }
            }
        }
        if (result.getRmPendingItemId() == null) {
            Date sendTime = Tools.diffDate(new Date(), new Integer(timeInterval));
            result.setLastSendTime(sendTime);
            result.setRmPendingItemId("");
        }
        return result;
    }

    /**
     * @see cn.com.chaochuang.docwork.service.FdFordoService#insertFdFordos(java.util.List, FordoSource)
     */
    @Override
    public void insertFdFordos(List<OAPendingHandleInfo> pendingItems, FordoSource fordoSource) {
        List datas = new ArrayList();
        FdFordo fdFordo;
        Date currentDate = new Date();
        for (OAPendingHandleInfo item : pendingItems) {
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
                // 未读数据添加消息推送
                SysUser user = userRepository.findOne(fdFordo.getRecipientId());
                // 若待办接收用户存在且消息推送注册号不为空则发送推送消息
                if (user != null && !Tools.isEmptyString(user.getRegistrationId())) {
                    JPushUtils.pushByRegistrationID(user.getRegistrationId(), "您有一条新的待办事宜请查收：" + fdFordo.getTitle());
                }
            } else {
                fdFordo.setStatus(FordoStatus.已读);
            }
            fdFordo.setLocalData(LocalData.非本地数据);
            fdFordo.setReadTime(item.getReadTime());
            fdFordo.setInputDate(currentDate);
            this.repository.save(fdFordo);
        }
    }

    /**
     * @see cn.com.chaochuang.docwork.service.FdFordoService#findByRmPendingId(java.lang.Long)
     */
    @Override
    public FdFordo findByRmPendingItemId(String fordoId) {
        return repository.findByRmPendingItemId(fordoId);
    }

    /**
     * (non-Javadoc)
     * 
     * @see cn.com.chaochuang.docwork.service.FdFordoService#selectUnLocalData(org.springframework.data.domain.Pageable)
     */
    @Override
    public List<FdFordo> selectUnLocalData(Pageable page) {
        return this.repository.findByLocalDataOrderBySendTimeAsc(LocalData.非本地数据, page);
    }

}
