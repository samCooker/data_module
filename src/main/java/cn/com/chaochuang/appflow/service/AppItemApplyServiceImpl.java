/*
 * FileName:    AppItemApplyServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年6月1日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.appflow.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.appflow.bean.AppFlowShowData;
import cn.com.chaochuang.appflow.domain.AppFlowNodeInfo;
import cn.com.chaochuang.appflow.domain.AppFlowNodeOpinions;
import cn.com.chaochuang.appflow.domain.AppItemApply;
import cn.com.chaochuang.appflow.domain.FdFordoApp;
import cn.com.chaochuang.appflow.repository.AppItemApplyRepository;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.util.NullBeanUtils;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.datacenter.domain.DataUpdate;
import cn.com.chaochuang.datacenter.reference.ExecuteFlag;
import cn.com.chaochuang.datacenter.service.DataUpdateService;
import cn.com.chaochuang.task.bean.WebServiceNodeInfo;

/**
 * @author LLM
 *
 */
@Service
@Transactional
public class AppItemApplyServiceImpl extends SimpleLongIdCrudRestService<AppItemApply> implements AppItemApplyService {
    @Autowired
    private FdFordoAppService          fdFordoAppService;
    @Autowired
    private AppItemApplyRepository     repository;
    @Autowired
    private AppFlowNodeInfoService     nodeInfoService;
    @Autowired
    private AppFlowNodeOpinionsService nodeOpinionsService;
    @Autowired
    private AppItemAttachService       attachService;
    @Autowired
    private AppTransactPersonalService appTransactPersonalService;
    @Autowired
    private DataUpdateService          dataUpdateService;

    @Override
    public SimpleDomainRepository<AppItemApply, Long> getRepository() {
        return repository;
    }

    /**
     * @see cn.com.chaochuang.appflow.service.AppItemApplyService#saveAppItemApplyDatas(java.util.List)
     */
    @Override
    public void saveAppItemApplyDatas(List<AppFlowShowData> appDatas) {
        if (appDatas == null) {
            return;
        }
        AppItemApply apply;
        try {
            for (AppFlowShowData applyData : appDatas) {
                // 若按指定的待办编号查找的申请数据已经不存在，则删除该待办数据
                if (AppFlowShowData.UNFINDEDPENDING.equals(applyData.getDataFlag())) {
                    this.fdFordoAppService.deleteExpirePending(applyData.getPendingHandleId().toString());
                    continue;
                }
                // 保存AppItemApply
                apply = repository.findByRmItemApplyId(applyData.getRmItemApplyId());
                if (apply == null) {
                    apply = new AppItemApply();
                }
                NullBeanUtils.copyProperties(apply, applyData);
                this.repository.save(apply);
                // 保存AppFlowNodeInfo
                if (Tools.isNotEmptyList(applyData.getFlowNodeInfos())) {
                    for (AppFlowNodeInfo nodeInfo : applyData.getFlowNodeInfos()) {
                        this.nodeInfoService.saveFlowNodeInfo(nodeInfo);
                    }
                    // 保存经办列表
                    appTransactPersonalService.saveAppTransactPersonalByFlowNodes(applyData.getFlowNodeInfos(), apply);
                }
                // 保存AppFlowNodeOpinions
                if (Tools.isNotEmptyList(applyData.getFlowNodeOpinions())) {
                    for (AppFlowNodeOpinions nodeInfo : applyData.getFlowNodeOpinions()) {
                        this.nodeOpinionsService.saveFlowNodeOpinions(nodeInfo);
                    }
                }
                // 保存AppItemAttach
                this.attachService.saveAppItemAttach(applyData.getAppItemAttachInfos(), applyData.getRmItemApplyId());
                // 更新待办事宜记录的本地记录状态
                this.fdFordoAppService.updateLocalData(applyData.getPendingHandleId().toString());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteDataUpdateAndFordo(DataUpdate dataUpdate, WebServiceNodeInfo nodeInfo, String backInfo) {
        if ("true".equals(backInfo)) {
            // 删除DataUpdate对象
            this.dataUpdateService.delete(dataUpdate);
        } else {
            dataUpdate.setExecuteFlag(ExecuteFlag.执行错误);
            dataUpdate.setErrorInfo(backInfo);
            this.dataUpdateService.getRepository().save(dataUpdate);
        }
        // 删除待办
        FdFordoApp fordo = fdFordoAppService.findByRmPendingId(nodeInfo.getPendingHandleId() + "");
        if (fordo != null) {
            fdFordoAppService.delete(fordo);
        }

    }
}
