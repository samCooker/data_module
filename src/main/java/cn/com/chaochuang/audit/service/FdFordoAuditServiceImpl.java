/*
 * FileName:    FdFordoAuditServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年10月9日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.audit.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.appflow.service.AppItemAttachService;
import cn.com.chaochuang.audit.bean.AuditPendingHandleInfo;
import cn.com.chaochuang.audit.domain.AuditTask;
import cn.com.chaochuang.audit.domain.FdFordoAudit;
import cn.com.chaochuang.audit.repository.AuditAppointRepository;
import cn.com.chaochuang.audit.repository.AuditFlowNodeInfoRepository;
import cn.com.chaochuang.audit.repository.AuditFlowNodeOpinionsRepository;
import cn.com.chaochuang.audit.repository.AuditPrjContentRepository;
import cn.com.chaochuang.audit.repository.AuditTaskRepository;
import cn.com.chaochuang.audit.repository.AuditWatcherRepository;
import cn.com.chaochuang.audit.repository.FdFordoAuditRepository;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.jpush.util.JPushUtils;
import cn.com.chaochuang.common.user.domain.SysUser;
import cn.com.chaochuang.common.user.repository.SysUserRepository;
import cn.com.chaochuang.common.util.NullBeanUtils;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.datacenter.service.DataUpdateService;
import cn.com.chaochuang.docwork.reference.FordoStatus;

/**
 * @author LLM
 *
 */
@Service
@Transactional
public class FdFordoAuditServiceImpl extends SimpleLongIdCrudRestService<FdFordoAudit> implements FdFordoAuditService {
    @PersistenceContext
    private EntityManager                   entityManager;
    @Autowired
    private SysUserRepository               userRepository;
    @Autowired
    private FdFordoAuditRepository          repository;
    @Autowired
    private AuditAppointRepository          appointRepository;
    @Autowired
    private AuditWatcherRepository          watcherRepository;
    @Autowired
    private AuditPrjContentRepository       prjContentRepository;
    @Autowired
    private AuditTaskRepository             taskRepository;
    @Autowired
    private AuditFlowNodeInfoRepository     nodeInfoRepository;
    @Autowired
    private AuditFlowNodeOpinionsRepository nodeOpinionsRepository;
    @Autowired
    private DataUpdateService               dataUpdateService;
    @Autowired
    private AppItemAttachService            attachService;

    @Value("${getdata.timeinterval}")
    private String                          timeInterval;

    /**
     * @see cn.com.chaochuang.common.data.service.CrudRestService#getRepository()
     */
    @Override
    public SimpleDomainRepository<FdFordoAudit, Long> getRepository() {
        return repository;
    }

    /**
     * @see cn.com.chaochuang.audit.service.FdFordoAuditService#selectMaxInputDate()
     */
    @Override
    public AuditPendingHandleInfo selectMaxInputDate() {
        AuditPendingHandleInfo result = new AuditPendingHandleInfo();
        String pendingId = repository.findMaxAuditPendingHandleId();
        if (!Tools.isEmptyString(pendingId)) {
            result.setRmPendingId(pendingId);
            result.setLastSendTime(null);
        } else {
            Date sendTime = Tools.diffDate(new Date(), new Integer(timeInterval));
            result.setLastSendTime(sendTime);
            result.setRmPendingId("");
        }
        return result;
    }

    /**
     * @see cn.com.chaochuang.audit.service.FdFordoAuditService#insertFdFordos(java.util.List)
     */
    @Override
    public void insertFdFordos(List<AuditPendingHandleInfo> pendingItems) {
        if (pendingItems == null) {
            return;
        }
        FdFordoAudit fdFordo;
        Date currentDate = new Date();
        for (AuditPendingHandleInfo item : pendingItems) {
            // 判断当前记录是否已经存在,不存在的情况下才写入fdfordo表
            if (Tools.isNotEmptyList(this.repository.findByRmPendingId(item.getRmPendingId()))) {
                continue;
            }
            fdFordo = new FdFordoAudit();
            SysUser user = userRepository.findByRmUserInfoId(item.getRecipientId());
            // item.setFordoType(item.getFordoType().substring(0, 3));//不需要的字段
            NullBeanUtils.copyProperties(fdFordo, item);
            fdFordo.setTaskId(item.getBusinessKey());
            // 将rmUserInfoId转成rmUserId
            if (user != null) {
                fdFordo.setRecipientId(user.getRmUserId());
            }
            if (item.getReadTime() == null) {
                fdFordo.setStatus(FordoStatus.未读);
                // 未读数据添加消息推送
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
        }
    }

    /**
     * @see cn.com.chaochuang.audit.service.FdFordoAuditService#selectTaskByRmTaskId(java.lang.Long)
     */
    @Override
    public AuditTask selectTaskByRmTaskId(Long rmTaskId) {
        List<AuditTask> tasks = this.taskRepository.findByRmAuditTaskId(rmTaskId);
        if (Tools.isNotEmptyList(tasks)) {
            return tasks.get(0);
        }
        return null;
    }

}
