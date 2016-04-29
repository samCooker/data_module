/*
 * FileName:    FdFordoDocServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2016
 * History:     2016年4月26日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.docex.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.docex.domain.DocexAffixItem;
import cn.com.chaochuang.docex.domain.FdFordoDoc;
import cn.com.chaochuang.docex.repository.DocexAffixItemRepository;
import cn.com.chaochuang.docex.repository.FdFordoDocRepository;
import cn.com.chaochuang.docwork.reference.FordoStatus;
import cn.com.chaochuang.task.bean.DocexPendingItem;
import cn.com.chaochuang.task.bean.OAPendingHandleInfo;

/**
 * @author LLM
 *
 */
@Service
@Transactional
public class FdFordoDocServiceImpl extends SimpleLongIdCrudRestService<FdFordoDoc> implements FdFordoDocService {
    @Autowired
    private FdFordoDocRepository     repository;
    @Autowired
    private DocexAffixItemRepository affixItemRepository;
    @Value("${getdata.timeinterval}")
    private String                   timeInterval;

    @Override
    public SimpleDomainRepository<FdFordoDoc, Long> getRepository() {
        return repository;
    }

    /**
     * @see cn.com.chaochuang.docex.service.FdFordoDocService#selectMaxInputDate()
     */
    @Override
    public OAPendingHandleInfo selectMaxInputDate() {
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
     * @see cn.com.chaochuang.docex.service.FdFordoDocService#insertFdFordos(java.util.List)
     */
    @Override
    public void insertFdFordos(List<DocexPendingItem> pendingItems) {
        FdFordoDoc fdFordo;
        for (DocexPendingItem item : pendingItems) {
            // 判断当前记录是否已经存在,不存在的情况下才写入fdfordo表
            fdFordo = this.repository.findByRmPendingId(item.getRmPendingId());
            if (fdFordo == null) {
                fdFordo = new FdFordoDoc();
            }
            BeanUtils.copyProperties(item, fdFordo);
            if (item.getReceiveTime() == null) {
                fdFordo.setStatus(FordoStatus.未读);
            } else {
                fdFordo.setStatus(FordoStatus.已读);
            }
            // 若有附件记录则插入附件表
            if (!Tools.isEmptyList(item.getAffixs())) {
                for (DocexAffixItem affixInfo : item.getAffixs()) {
                    if (this.affixItemRepository.findByRmFileId(affixInfo.getRmFileId()) != null) {
                        continue;
                    }
                    affixInfo.setLocalData(LocalData.非本地数据);
                    this.affixItemRepository.save(affixInfo);
                }
            }
            // 向综合待办表中添加记录
            // this.fordoCompService.saveFdFordoComp(new FdFordoComp(fdFordo));
            // fdFordo.setLocalData(LocalData.非本地数据);
            // fdFordo.setReadTime(item.getReadTime());
            // fdFordo.setInputDate(currentDate);
            this.repository.save(fdFordo);
        }

    }

}
