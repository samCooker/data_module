/*
 * FileName:   FdFordoCaseServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年09月10日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.casecomplaint.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.casecomplaint.bean.FdFordoCaseInfo;
import cn.com.chaochuang.casecomplaint.domain.FdFordoCase;
import cn.com.chaochuang.casecomplaint.repository.FdFordoCaseRepository;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.jpush.util.JPushUtils;
import cn.com.chaochuang.common.user.domain.SysUser;
import cn.com.chaochuang.common.user.service.SysUserService;
import cn.com.chaochuang.common.util.JsonMapper;
import cn.com.chaochuang.common.util.NullBeanUtils;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.datacenter.domain.DataUpdate;
import cn.com.chaochuang.datacenter.service.DataUpdateService;
import cn.com.chaochuang.docwork.reference.FordoStatus;

import com.fasterxml.jackson.databind.JavaType;

/**
 * @author Shicx
 *
 */
@Service
@Transactional
public class FdFordoCaseServiceImpl extends SimpleLongIdCrudRestService<FdFordoCase> implements FdFordoCaseService {
    @Autowired
    private FdFordoCaseRepository       repository;
    @Autowired
    private SysUserService              sysUserService;
    @Autowired
    private DataUpdateService           dataUpdateService;
    @Autowired
    private CaseTransactPersonalService caseTransactPersonalService;

    @Override
    public SimpleDomainRepository<FdFordoCase, Long> getRepository() {
        return repository;
    }

    @Override
    public void saveFdFordo(String returnInfo) {
        if (StringUtils.isBlank(returnInfo)) {
            return;
        }
        JsonMapper mapper = JsonMapper.getInstance();
        JavaType javaType = mapper.constructParametricType(ArrayList.class, FdFordoCaseInfo.class);
        List<FdFordoCaseInfo> data = (List<FdFordoCaseInfo>) mapper.readValue(returnInfo, javaType);
        for (FdFordoCaseInfo fordoInfo : data) {
            // 待办标识curHandleFlag=1表明该待办类型为坐席，在移动端处理为产生相同的多个待办（rmPendingId相同），只是接收人不同而已。
            // 通过rmPendingId和接收人id查询已有的待办
            FdFordoCase fdFordoCase = repository.findByRmPendingIdAndRecipientId(fordoInfo.getPendingHandleId(),
                            fordoInfo.getReceiverId());
            if (fdFordoCase == null) {
                fdFordoCase = new FdFordoCase();
                NullBeanUtils.copyProperties(fdFordoCase, fordoInfo);
                fdFordoCase.setEmergencyLevel(fordoInfo.getEmergencyFlag());
                fdFordoCase.setRmPendingId(fordoInfo.getPendingHandleId());
                fdFordoCase.setRecipientId(fordoInfo.getReceiverId());
                fdFordoCase.setSenderDeptName(fordoInfo.getSenderDepName());
                fdFordoCase.setLocalData(LocalData.非本地数据);
                if (fdFordoCase.getReadTime() == null) {
                    fdFordoCase.setStatus(FordoStatus.未读);
                    SysUser user = sysUserService.findByRmUserInfoId(fordoInfo.getReceiverId());
                    if (user != null && !Tools.isEmptyString(user.getRegistrationId())) {
                        JPushUtils.pushByRegistrationID(user.getRegistrationId(),
                                        "您有一条新的待办事宜请查收：" + fordoInfo.getTitle());
                    }
                } else {
                    fdFordoCase.setStatus(FordoStatus.已读);
                }
                fdFordoCase.setInputDate(new Date());
                repository.save(fdFordoCase);
            }
        }

    }

    @Override
    public void changeFordoLocalData(String pendingId, LocalData localData) {
        List<FdFordoCase> fordoList = repository.findByRmPendingId(pendingId);
        if (fordoList != null) {
            for (FdFordoCase fordo : fordoList) {
                fordo.setLocalData(localData);
                repository.save(fordo);
            }
        }

    }

    @Override
    public void deleteFordoAndDataUpdate(String taskId, DataUpdate dataUpdate, String backInfo) {
        // 根据taskId查找待办
        List<FdFordoCase> fordoList = repository.findByNodeId(taskId);
        if (fordoList != null && fordoList.size() > 0) {
            // 删除待办
            repository.delete(fordoList);
        }
        if (DataUpdate.SUBMIT_SUCCESS.equals(backInfo)) {
            // 删除提交记录
            dataUpdateService.delete(dataUpdate);
        } else {
            // 保存错误信息
            dataUpdateService.saveErrorInfo(dataUpdate, backInfo);
        }
    }

    @Override
    public void deleteFordoByRmPendingId(String pendingId) {
        List<FdFordoCase> fordoList = repository.findByRmPendingId(pendingId);
        // 删除待办
        if (Tools.isNotEmptyList(fordoList)) {
            repository.delete(fordoList);
        }
    }
}
