/*
 * FileName:    AppItemAttachServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年6月2日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.appflow.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.appflow.domain.AppItemAttach;
import cn.com.chaochuang.appflow.domain.AppPrjMaterial;
import cn.com.chaochuang.appflow.repository.AppItemAttachRepository;
import cn.com.chaochuang.appflow.repository.AppPrjMaterialRepository;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.util.JsonMapper;
import cn.com.chaochuang.common.util.NullBeanUtils;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.datacenter.domain.SysDataChangeApp;
import cn.com.chaochuang.datacenter.reference.OperationType;
import cn.com.chaochuang.webservice.server.SuperviseWebService;

/**
 * @author LLM
 *
 */
@Service
@Transactional
public class AppItemAttachServiceImpl extends SimpleLongIdCrudRestService<AppItemAttach> implements
                AppItemAttachService {
    @Autowired
    private AppItemAttachRepository  repository;
    @Autowired
    private AppPrjMaterialRepository appPrjMaterialRepository;
    @Autowired
    private SuperviseWebService      superviseWebService;

    @Override
    public SimpleDomainRepository<AppItemAttach, Long> getRepository() {
        return repository;
    }

    /**
     * @see cn.com.chaochuang.appflow.service.AppItemAttachService#saveAppItemAttach(cn.com.chaochuang.appflow.domain.AppItemAttach)
     */
    @Override
    public void saveAppItemAttach(AppItemAttach info) {
        AppItemAttach node = this.repository.findByRmAttachId(info.getRmAttachId());
        // 若数据库中无指定记录才进行新增操作
        if (node == null) {
            info.setLocalData(LocalData.非本地数据);
            this.repository.save(info);
        }
    }

    /**
     * (non-Javadoc)
     *
     * @see cn.com.chaochuang.appflow.service.AppItemAttachService#selectUnLocalAttach()
     */
    @Override
    public List<AppItemAttach> selectUnLocalAttach() {
        return this.repository.findByLocalData(LocalData.非本地数据);
    }

    /**
     * (non-Javadoc)
     *
     * @see cn.com.chaochuang.appflow.service.AppItemAttachService#saveDocFileAttachForLocal(cn.com.chaochuang.appflow.domain.AppItemAttach,
     *      java.lang.String)
     */
    @Override
    public void saveAttachForLocal(AppItemAttach attach, LocalData localData, String localFileName) {
        if (attach != null) {
            if (StringUtils.isNotBlank(localFileName)) {
                attach.setLocalData(LocalData.有本地数据);
                attach.setSavePath(localFileName);
            } else {
                attach.setLocalData(localData);
            }
            this.repository.save(attach);
        }
    }

    /**
     * (non-Javadoc)
     * 
     * @see cn.com.chaochuang.appflow.service.AppItemAttachService#saveAppItemAttach(java.util.List)
     */
    @Override
    public void saveAppItemAttach(List<AppItemAttach> attachList, Long rmItemApplyId) {
        // 查找出所有该案件的附件
        List<AppItemAttach> deleteAttachList = repository.findByItemApplyId(rmItemApplyId);
        if (attachList != null) {
            // 更新旧附件，保存新附件，删除不存在的附件
            for (AppItemAttach info : attachList) {
                AppItemAttach attach = this.repository.findByRmAttachId(info.getRmAttachId());
                if (attach == null) {
                    // 保存新附件
                    attach = new AppItemAttach();
                    NullBeanUtils.copyProperties(attach, info);
                    attach.setLocalData(LocalData.非本地数据);
                } else {
                    // 更改旧附件名
                    attach.setTrueName(info.getTrueName());
                }
                if (deleteAttachList != null && attach != null && attach.getId() != null) {
                    // 存在的附件剔除出删除列表中
                    deleteAttachList.remove(attach);
                }
                repository.save(attach);
            }
        }
        if (deleteAttachList != null) {
            // 删除多余的附件
            repository.deleteInBatch(deleteAttachList);
        }

    }

    /**
     * 
     * @see cn.com.chaochuang.appflow.service.AppItemAttachService#updatePrjMaterial(SysDataChangeApp)
     */
    @Override
    public void updatePrjMaterial(SysDataChangeApp dataChange) {
        // 分解变更内容
        if (dataChange == null || Tools.isEmptyString(dataChange.getChangeScript())) {
            return;
        }
        // 获取要操作的事件审批编号
        String[] items = dataChange.getChangeScript().split("=");
        if (items == null || items.length != 2) {
            return;
        }
        Long materialId = Long.valueOf(items[1]);
        if (OperationType.删除.getKey().equals(dataChange.getOperationType())) {
            List<AppPrjMaterial> materialList = appPrjMaterialRepository.findByRmMaterialId(materialId);
            if (Tools.isNotEmptyList(materialList)) {
                appPrjMaterialRepository.delete(materialList);
            }
        } else {
            // 从原系统获取数据
            String json = superviseWebService.selectAppPrjMaterial(materialId);
            if (StringUtils.isNotBlank(json)) {
                JsonMapper mapper = JsonMapper.getInstance();
                AppPrjMaterial appPrjMaterial = mapper.readValue(json, AppPrjMaterial.class);
                List<AppPrjMaterial> materialList = appPrjMaterialRepository.findByRmMaterialId(appPrjMaterial
                                .getRmMaterialId());
                if (Tools.isNotEmptyList(materialList)) {
                    // 更新已有的数据
                    appPrjMaterial.setId(materialList.get(0).getId());
                }
                appPrjMaterialRepository.save(appPrjMaterial);
            }
        }
    }
}
