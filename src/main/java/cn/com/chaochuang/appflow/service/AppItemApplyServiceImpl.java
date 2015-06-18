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

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.appflow.bean.AppFlowShowData;
import cn.com.chaochuang.appflow.domain.AppFlowNodeInfo;
import cn.com.chaochuang.appflow.domain.AppFlowNodeOpinions;
import cn.com.chaochuang.appflow.domain.AppItemApply;
import cn.com.chaochuang.appflow.domain.AppItemAttach;
import cn.com.chaochuang.appflow.repository.AppItemApplyRepository;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.util.Tools;

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

    @Override
    public SimpleDomainRepository<AppItemApply, Long> getRepository() {
        return repository;
    }

    /**
     * @see cn.com.chaochuang.appflow.service.AppItemApplyService#saveAppItemApplyDatas(java.util.List)
     */
    @Override
    public void saveAppItemApplyDatas(List<AppFlowShowData> appDatas) {
        if (!Tools.isNotEmptyList(appDatas)) {
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
                BeanUtils.copyProperties(apply, applyData);
                this.repository.save(apply);
                // 保存AppFlowNodeInfo
                if (Tools.isNotEmptyList(applyData.getFlowNodeInfos())) {
                    for (AppFlowNodeInfo nodeInfo : applyData.getFlowNodeInfos()) {
                        this.nodeInfoService.saveFlowNodeInfo(nodeInfo);
                    }
                }
                // 保存AppFlowNodeOpinions
                if (Tools.isNotEmptyList(applyData.getFlowNodeOpinions())) {
                    for (AppFlowNodeOpinions nodeInfo : applyData.getFlowNodeOpinions()) {
                        this.nodeOpinionsService.saveFlowNodeOpinions(nodeInfo);
                    }
                }
                // 保存AppItemAttach
                if (Tools.isNotEmptyList(applyData.getAppItemAttachInfos())) {
                    for (AppItemAttach nodeInfo : applyData.getAppItemAttachInfos()) {
                        this.attachService.saveAppItemAttach(nodeInfo);
                    }
                }
                // 更新待办事宜记录的本地记录状态
                this.fdFordoAppService.updateLocalData(applyData.getPendingHandleId().toString());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
