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
import cn.com.chaochuang.appflow.service.AppItemAttachService;
import cn.com.chaochuang.audit.bean.AuditPendingHandleInfo;
import cn.com.chaochuang.audit.domain.AuditAppoint;
import cn.com.chaochuang.audit.domain.AuditFlowNodeInfo;
import cn.com.chaochuang.audit.domain.AuditFlowNodeOpinions;
import cn.com.chaochuang.audit.domain.AuditPrjContent;
import cn.com.chaochuang.audit.domain.AuditTask;
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
import cn.com.chaochuang.datacenter.domain.DataUpdate;
import cn.com.chaochuang.datacenter.service.DataUpdateService;
import cn.com.chaochuang.docwork.reference.FordoStatus;
import cn.com.chaochuang.task.bean.AuditSubmitData;

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
        Long tmpId;
        FdFordoAudit fdFordo;
        Date currentDate = new Date();
        for (AuditPendingHandleInfo item : pendingItems) {
            // 判断当前记录是否已经存在,不存在的情况下才写入fdfordo表
            if (Tools.isNotEmptyList(this.repository.findByRmPendingId(item.getRmPendingId()))) {
                continue;
            }
            fdFordo = new FdFordoAudit();
            SysUser user = userRepository.findByRmUserInfoId(item.getRecipientId());
            if (user == null) {
                continue;
            }
            item.setFordoType(item.getFordoType().substring(0, 3));
            NullBeanUtils.copyProperties(fdFordo, item);
            // 插入审查任务对象
            if (item.getTaskInfo() != null) {
                AuditTask task = this.selectTaskByRmTaskId(item.getTaskInfo().getRmAuditTaskId());
                if (task != null) {
                    tmpId = task.getId();
                    // 由于task与item.getTaskInfo()对象相同，在NullBeanUtils.copyProperties后需要重新设置审批任务编号
                    NullBeanUtils.copyProperties(task, item.getTaskInfo());
                    task.setId(tmpId);
                    this.taskRepository.save(task);
                } else {
                    this.taskRepository.save(item.getTaskInfo());
                }
            }
            // 插入选派记录
            if (Tools.isNotEmptyList(item.getAppointInfos())) {
                List<AuditAppoint> appoints;
                AuditAppoint appoint;
                for (AuditAppoint appointInfo : item.getAppointInfos()) {
                    appoints = this.appointRepository.findByRmAppointId(appointInfo.getRmAppointId());
                    // 若选派记录为空则直接保存，否则将原记录赋值再保存
                    if (!Tools.isNotEmptyList(appoints)) {
                        this.appointRepository.save(appointInfo);
                    } else {
                        appoint = appoints.get(0);
                        tmpId = appoint.getId();
                        NullBeanUtils.copyProperties(appoint, appointInfo);
                        appoint.setId(tmpId);
                        this.appointRepository.save(appoint);
                    }
                }
            }
            // 插入观察员记录
            if (Tools.isNotEmptyList(item.getWatcherInfos())) {
                List<AuditWatcher> watchers;
                AuditWatcher watcher;
                for (AuditWatcher watcherInfo : item.getWatcherInfos()) {
                    watchers = this.watcherRepository.findByRmWatcherId(watcherInfo.getRmWatcherId());
                    if (!Tools.isNotEmptyList(watchers)) {
                        this.watcherRepository.save(watcherInfo);
                    } else {
                        watcher = watchers.get(0);
                        tmpId = watcher.getId();
                        NullBeanUtils.copyProperties(watcher, watcherInfo);
                        watcher.setId(tmpId);
                        this.watcherRepository.save(watcher);
                    }
                }
            }
            // 插入审查项目记录
            if (Tools.isNotEmptyList(item.getPrjContentInfos())) {
                List<AuditPrjContent> prjContents;
                AuditPrjContent prjContent;
                for (AuditPrjContent prjContentInfo : item.getPrjContentInfos()) {
                    prjContents = this.prjContentRepository.findByRmPrjContentId(prjContentInfo.getRmPrjContentId());
                    if (!Tools.isNotEmptyList(prjContents)) {
                        this.prjContentRepository.save(prjContentInfo);
                    } else {
                        prjContent = prjContents.get(0);
                        tmpId = prjContent.getId();
                        NullBeanUtils.copyProperties(prjContent, prjContentInfo);
                        prjContent.setId(tmpId);
                        this.prjContentRepository.save(prjContent);
                    }
                }
            }
            // 插入审批环节记录
            if (Tools.isNotEmptyList(item.getNodeInfos())) {
                List<AuditFlowNodeInfo> nodes;
                AuditFlowNodeInfo node;
                for (AuditFlowNodeInfo nodeInfo : item.getNodeInfos()) {
                    nodes = this.nodeInfoRepository.findByRmNodeInfoId(nodeInfo.getRmNodeInfoId());
                    if (!Tools.isNotEmptyList(nodes)) {
                        this.nodeInfoRepository.save(nodeInfo);
                    } else {
                        node = nodes.get(0);
                        tmpId = node.getId();
                        NullBeanUtils.copyProperties(node, nodeInfo);
                        node.setId(tmpId);
                        this.nodeInfoRepository.save(node);
                    }
                }
            }
            // 插入审批意见记录
            if (Tools.isNotEmptyList(item.getNodeOpinionsInfos())) {
                List<AuditFlowNodeOpinions> opinions;
                AuditFlowNodeOpinions opinion;
                for (AuditFlowNodeOpinions opinionInfo : item.getNodeOpinionsInfos()) {
                    opinions = this.nodeOpinionsRepository.findByRmNodeOpinionsId(opinionInfo.getRmNodeOpinionsId());
                    if (!Tools.isNotEmptyList(opinions)) {
                        this.nodeOpinionsRepository.save(opinionInfo);
                    } else {
                        opinion = opinions.get(0);
                        tmpId = opinion.getId();
                        NullBeanUtils.copyProperties(opinion, opinionInfo);
                        opinion.setId(tmpId);
                        this.nodeOpinionsRepository.save(opinion);
                    }
                }
            }
            // 保存AppItemAttach
            if (item.getAppItemAttachInfos() != null && item.getAppItemAttachInfos().size() > 0) {
                this.attachService.saveAppItemAttach(item.getAppItemAttachInfos(), item.getAppItemAttachInfos().get(0)
                                .getItemApplyId());
            }
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

    /**
     * @see cn.com.chaochuang.audit.service.FdFordoAuditService#deleteDataUpdateAndFordo(cn.com.chaochuang.datacenter.domain.DataUpdate,
     *      cn.com.chaochuang.task.bean.WebServiceNodeInfo, java.lang.String)
     */
    @Override
    public void deleteDataUpdateAndFordo(DataUpdate dataUpdate, AuditSubmitData nodeInfo, String backInfo) {
        if (DataUpdate.SUBMIT_SUCCESS.equals(backInfo)) {
            // 删除DataUpdate对象
            dataUpdateService.delete(dataUpdate);
        } else {
            // 保存错误信息
            dataUpdateService.saveErrorInfo(dataUpdate, backInfo);
        }
        List<FdFordoAudit> fordoList = repository.findByRmPendingId(nodeInfo.getPendingHandleId() + "");
        if (Tools.isNotEmptyList(fordoList)) {
            repository.delete(fordoList);
        }

    }

}
