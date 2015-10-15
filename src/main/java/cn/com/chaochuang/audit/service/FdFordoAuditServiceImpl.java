/*
 * FileName:    FdFordoAuditServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年10月9日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.audit.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.audit.bean.AuditPendingHandleInfo;
import cn.com.chaochuang.audit.domain.AuditAppoint;
import cn.com.chaochuang.audit.domain.AuditFlowNodeInfo;
import cn.com.chaochuang.audit.domain.AuditFlowNodeOpinions;
import cn.com.chaochuang.audit.domain.AuditPrjContent;
import cn.com.chaochuang.audit.domain.AuditWatcher;
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
        StringBuffer sql = new StringBuffer(" select Max(rmPendingId) from ").append(FdFordoAudit.class.getName());
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
            // 没有待办类别或待办类别长度小于3的记录不写入数据库
            if (Tools.isEmptyString(item.getFordoType()) || item.getFordoType().length() < 3) {
                continue;
            }
            fdFordo = new FdFordoAudit();
            SysUser user = userRepository.findByRmUserInfoId(item.getRecipientId());
            item.setFordoType(item.getFordoType().substring(0, 3));
            NullBeanUtils.copyProperties(fdFordo, item);
            // 将rmUserInfoId转成rmUserId
            fdFordo.setRecipientId(user.getRmUserId());
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
            // 插入审查任务对象
            if (item.getTaskInfo() != null) {
                if (!Tools.isNotEmptyList(this.taskRepository
                                .findByRmAuditTaskId(item.getTaskInfo().getRmAuditTaskId()))) {
                    this.taskRepository.save(item.getTaskInfo());
                }
            }
            // 插入选派记录
            if (Tools.isNotEmptyList(item.getAppointInfos())) {
                for (AuditAppoint appoint : item.getAppointInfos()) {
                    if (!Tools.isNotEmptyList(this.appointRepository.findByRmAppointId(appoint.getRmAppointId()))) {
                        this.appointRepository.save(appoint);
                    }
                }
            }
            // 插入观察员记录
            if (Tools.isNotEmptyList(item.getWatcherInfos())) {
                for (AuditWatcher watcher : item.getWatcherInfos()) {
                    if (!Tools.isNotEmptyList(this.watcherRepository.findByRmWatcherId(watcher.getRmWatcherId()))) {
                        this.watcherRepository.save(watcher);
                    }
                }
            }
            // 插入审查项目记录
            if (Tools.isNotEmptyList(item.getPrjContentInfos())) {
                for (AuditPrjContent prjContent : item.getPrjContentInfos()) {
                    if (!Tools.isNotEmptyList(this.prjContentRepository.findByRmPrjContentId(prjContent
                                    .getRmPrjContentId()))) {
                        this.prjContentRepository.save(prjContent);
                    }
                }
            }
            // 插入审批环节记录
            if (Tools.isNotEmptyList(item.getNodeInfos())) {
                for (AuditFlowNodeInfo nodeInfo : item.getNodeInfos()) {
                    if (!Tools.isNotEmptyList(this.nodeInfoRepository.findByRmNodeInfoId(nodeInfo.getRmNodeInfoId()))) {
                        this.nodeInfoRepository.save(nodeInfo);
                    }
                }
            }
            // 插入审批意见记录
            if (Tools.isNotEmptyList(item.getNodeOpinionsInfos())) {
                for (AuditFlowNodeOpinions opinion : item.getNodeOpinionsInfos()) {
                    if (!Tools.isNotEmptyList(this.nodeOpinionsRepository.findByRmNodeOpinionsId(opinion
                                    .getRmNodeOpinionsId()))) {
                        this.nodeOpinionsRepository.save(opinion);
                    }
                }
            }
            this.repository.save(fdFordo);
        }
    }

}
