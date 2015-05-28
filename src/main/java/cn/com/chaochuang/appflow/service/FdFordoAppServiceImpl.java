/*
 * FileName:    FdFordoAppServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年5月28日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.appflow.service;

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
import org.springframework.stereotype.Service;

import cn.com.chaochuang.appflow.bean.AppFlowPendingHandleInfo;
import cn.com.chaochuang.appflow.domain.FdFordoApp;
import cn.com.chaochuang.appflow.repository.FdFordoAppRepository;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.jpush.util.JPushUtils;
import cn.com.chaochuang.common.user.domain.SysUser;
import cn.com.chaochuang.common.user.repository.SysUserRepository;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.datacenter.domain.SysDataChange;
import cn.com.chaochuang.docwork.reference.FordoStatus;

/**
 * @author LLM
 *
 */
@Service
@Transactional
public class FdFordoAppServiceImpl extends SimpleLongIdCrudRestService<FdFordoApp> implements FdFordoAppService {
    @PersistenceContext
    private EntityManager        entityManager;

    @Autowired
    private SysUserRepository    userRepository;

    @Value("${getdata.timeinterval}")
    private String               timeInterval;

    @Autowired
    private FdFordoAppRepository repository;

    @Override
    public SimpleDomainRepository<FdFordoApp, Long> getRepository() {
        return repository;
    }

    /**
     * @see cn.com.chaochuang.appflow.service.FdFordoAppService#selectMaxInputDate()
     */
    @Override
    public AppFlowPendingHandleInfo selectMaxInputDate() {
        AppFlowPendingHandleInfo result = new AppFlowPendingHandleInfo();
        StringBuffer sql = new StringBuffer(" select Max(rmPendingId) from ").append(FdFordoApp.class.getName());
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
     * @see cn.com.chaochuang.appflow.service.FdFordoAppService#insertFdFordos(java.util.List)
     */
    @Override
    public void insertFdFordos(List<AppFlowPendingHandleInfo> pendingItems) {
        FdFordoApp fdFordo;
        Date currentDate = new Date();
        for (AppFlowPendingHandleInfo item : pendingItems) {
            // 判断当前记录是否已经存在,不存在的情况下才写入fdfordo表
            if (this.repository.findByRmPendingId(item.getRmPendingId()) != null) {
                continue;
            }
            // 没有待办类别或待办类别长度小于3的记录不写入数据库
            if (Tools.isEmptyString(item.getFordoType()) || item.getFordoType().length() < 3) {
                continue;
            }
            fdFordo = new FdFordoApp();
            SysUser user = userRepository.findByrmUserInfoId(item.getRecipientId());
            item.setRecipientId(user.getRmUserId());
            item.setFordoType(item.getFordoType().substring(0, 3));
            BeanUtils.copyProperties(item, fdFordo);
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
            this.repository.save(fdFordo);
        }
    }

    /**
     * @see cn.com.chaochuang.appflow.service.FdFordoAppService#analysisDataChange(cn.com.chaochuang.datacenter.domain.SysDataChange)
     */
    @Override
    public void analysisDataChange(SysDataChange dataChange) {
        // TODO Auto-generated method stub

    }

}
