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

import javax.transaction.Transactional;

import cn.com.chaochuang.common.beancopy.BeanCopyBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.fdfordo.domain.FdFordoComp;
import cn.com.chaochuang.common.fdfordo.service.FdFordoCompService;
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

    @Autowired
    private FdFordoRepository  repository;
    @Autowired
    private SysUserRepository  userRepository;
    @Autowired
    private FdFordoCompService fordoCompService;

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
        String rmPendingItemId = repository.findMaxRmPendingItemId();
        if (Tools.isEmptyString(rmPendingItemId)) {
            Date sendTime = Tools.diffDate(new Date(), new Integer(timeInterval));
            result.setLastSendTime(sendTime);
            result.setRmPendingItemId("");
        } else {
            result.setRmPendingItemId(rmPendingItemId);
            result.setLastSendTime(null);
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
                SysUser user = userRepository.findByRmUserId(fdFordo.getRecipientId());
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
            this.repository.saveAndFlush(fdFordo);
            // 向综合待办表中添加记录
            FdFordoComp fdFordoComp = BeanCopyBuilder.buildObject(item,FdFordoComp.class);
            fdFordoComp.setFordoId(fdFordo.getId());
            fdFordoComp.setFordoSource(FordoSource.oa);
            this.fordoCompService.saveFdFordoComp(fdFordoComp);
        }
    }

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
