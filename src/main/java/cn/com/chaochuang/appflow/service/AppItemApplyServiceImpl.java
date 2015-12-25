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

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.appflow.bean.AppFlowShowData;
import cn.com.chaochuang.appflow.domain.AppFlowNodeInfo;
import cn.com.chaochuang.appflow.domain.AppFlowNodeOpinions;
import cn.com.chaochuang.appflow.domain.AppItemApply;
import cn.com.chaochuang.appflow.domain.AppPharmacist;
import cn.com.chaochuang.appflow.repository.AppItemApplyRepository;
import cn.com.chaochuang.appflow.repository.AppPharmacistRepository;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.util.JsonMapper;
import cn.com.chaochuang.common.util.NullBeanUtils;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.datacenter.domain.SysDataChange;
import cn.com.chaochuang.datacenter.reference.OperationType;
import cn.com.chaochuang.webservice.server.SuperviseWebService;

/**
 * @author LLM
 *
 */
@Service
@Transactional
public class AppItemApplyServiceImpl extends SimpleLongIdCrudRestService<AppItemApply>implements AppItemApplyService {
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
    private SuperviseWebService        superviseWebService;
    @Autowired
    private AppPharmacistRepository    appPharmacistRepository;

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

    /**
     * @see cn.com.chaochuang.appflow.service.AppItemApplyService#updateOrDelPharmacist(cn.com.chaochuang.datacenter.domain.SysDataChange)
     */
    @Override
    public void updateOrDelPharmacist(SysDataChange dataChange) {
        // 分解变更内容
        if (dataChange == null || Tools.isEmptyString(dataChange.getChangeScript())) {
            return;
        }
        // 获取要操作的事件审批编号
        String[] items = dataChange.getChangeScript().split("=");
        if (items == null || items.length != 2) {
            return;
        }
        Long rmPharmId = Long.valueOf(items[1]);
        List<AppPharmacist> pharmacistList = appPharmacistRepository.findByRmPharmId(rmPharmId);
        if (OperationType.删除.getKey().equals(dataChange.getOperationType())) {
            if (Tools.isNotEmptyList(pharmacistList)) {
                appPharmacistRepository.delete(pharmacistList);
            }
        } else {
            // 从原系统获取数据
            String json = superviseWebService.selectPharmacistByRmPharmId(rmPharmId);
            if (StringUtils.isNotBlank(json)) {
                JsonMapper mapper = JsonMapper.getInstance();
                AppPharmacist pharmaData = mapper.readValue(json, AppPharmacist.class);
                if (Tools.isNotEmptyList(pharmacistList)) {
                    // 更新已有的数据
                    pharmaData.setId(pharmacistList.get(0).getId());
                }
                // 删除rmPharmId重复的记录
                if (pharmacistList != null && pharmacistList.size() > 1) {
                    pharmacistList.remove(0);
                    appPharmacistRepository.delete(pharmacistList);
                }
                appPharmacistRepository.save(pharmaData);
            }
        }

    }

}
