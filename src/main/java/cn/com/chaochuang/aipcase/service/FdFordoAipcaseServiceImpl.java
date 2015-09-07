/*
 * FileName:    FdFordoAipcaseServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年7月4日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.aipcase.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.aipcase.bean.AipCasePendingHandleInfo;
import cn.com.chaochuang.aipcase.domain.FdFordoAipcase;
import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.aipcase.repository.FdFordoAipcaseRepository;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.jpush.util.JPushUtils;
import cn.com.chaochuang.common.user.domain.SysUser;
import cn.com.chaochuang.common.user.repository.SysUserRepository;
import cn.com.chaochuang.common.util.NullBeanUtils;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.docwork.reference.FordoStatus;
import cn.com.chaochuang.task.bean.AipCasePendingInfo;

/**
 * @author LLM
 *
 */
@Service
@Transactional
public class FdFordoAipcaseServiceImpl extends SimpleLongIdCrudRestService<FdFordoAipcase> implements
                FdFordoAipcaseService {
    @PersistenceContext
    private EntityManager              entityManager;
    @Autowired
    private FdFordoAipcaseRepository   repository;
    @Autowired
    private SysUserRepository          userRepository;
    @Autowired
    private AipTransactPersonalService aipTransactPersonalService;
    @Value("${getdata.timeinterval}")
    private String                     timeInterval;

    @Override
    public SimpleDomainRepository<FdFordoAipcase, Long> getRepository() {
        return repository;
    }

    /**
     * @see cn.com.chaochuang.aipcase.service.FdFordoAipcaseService#selectMaxInputDate()
     */
    @Override
    public AipCasePendingHandleInfo selectMaxInputDate() {
        AipCasePendingHandleInfo result = new AipCasePendingHandleInfo();
        StringBuffer sql = new StringBuffer(" select Max(rmPendingId) from ").append(FdFordoAipcase.class.getName());
        Query query = this.entityManager.createQuery(sql.toString());
        List datas = (ArrayList) query.getResultList();
        if (Tools.isNotEmptyList(datas)) {
            for (Object o : datas) {
                if (o != null) {
                    result.setRmPendingId(o.toString());
                    result.setLastSendTime(null);
                    break;
                }
            }
        }
        if (result.getRmPendingId() == null) {
            Date sendTime = Tools.diffDate(new Date(), new Integer(timeInterval));
            result.setLastSendTime(sendTime);
            result.setRmPendingId("");
        }
        return result;
    }

    /**
     * @see cn.com.chaochuang.aipcase.service.FdFordoAipcaseService#insertFdFordos(java.util.List)
     */
    @Override
    public void insertFdFordos(List<AipCasePendingInfo> pendingItems) {
        FdFordoAipcase fdFordo;
        Date currentDate = new Date();
        for (AipCasePendingInfo item : pendingItems) {
            // 判断当前记录是否已经存在,不存在的情况下才写入fdfordoAipCase表
            if (this.repository.findByRmPendingIdAndRecipientId(item.getRmPendingId(), item.getRecipientId()) != null) {
                continue;
            }
            fdFordo = new FdFordoAipcase();
            NullBeanUtils.copyProperties(fdFordo, item);
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
            fdFordo.setReadTime(item.getReadTime());
            fdFordo.setInputDate(currentDate);
            fdFordo.setLocalData(LocalData.非本地数据);
            this.repository.save(fdFordo);
            // 保存经办记录
            aipTransactPersonalService.saveTransactPersonalRecord(fdFordo);
        }
    }

    /**
     * @see cn.com.chaochuang.aipcase.service.FdFordoAipcaseService#selectUnLocalData()
     */
    @Override
    public List<FdFordoAipcase> selectUnLocalData() {
        // 获取未到本地数据
        return this.repository.findByLocalDataOrderBySendTimeAsc(LocalData.非本地数据);
    }

    /**
     * (non-Javadoc)
     * 
     * @see cn.com.chaochuang.aipcase.service.FdFordoAipcaseService#selectUnLocalData(org.springframework.data.domain.Pageable)
     */
    @Override
    public List<FdFordoAipcase> selectUnLocalData(Pageable page) {
        // 分页获取未到本地数据
        return this.repository.findByLocalDataOrderBySendTimeAsc(LocalData.非本地数据, page);
    }

    /**
     * @see cn.com.chaochuang.aipcase.service.FdFordoAipcaseService#updateLocalData(java.lang.String)
     */
    @Override
    public void updateLocalData(String rmPendingId) {
        FdFordoAipcase pending = this.repository.findByRmPendingId(rmPendingId);
        if (pending != null) {
            pending.setLocalData(LocalData.有本地数据);
            this.repository.save(pending);
        }
    }

    /**
     * (non-Javadoc)
     * 
     * @see cn.com.chaochuang.aipcase.service.FdFordoAipcaseService#findByRmPendingId(java.lang.Long)
     */
    @Override
    public FdFordoAipcase findByRmPendingId(String fordoId) {
        return repository.findByRmPendingId(fordoId);
    }

}
