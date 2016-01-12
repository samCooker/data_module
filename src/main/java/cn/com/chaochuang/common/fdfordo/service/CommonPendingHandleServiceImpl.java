/*
 * FileName:    CommonPendingHandleServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年6月25日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.common.fdfordo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.aipcase.domain.FdFordoAipcase;
import cn.com.chaochuang.aipcase.repository.FdFordoAipcaseRepository;
import cn.com.chaochuang.appflow.domain.FdFordoApp;
import cn.com.chaochuang.appflow.repository.AppItemApplyRepository;
import cn.com.chaochuang.appflow.repository.FdFordoAppRepository;
import cn.com.chaochuang.audit.domain.FdFordoAudit;
import cn.com.chaochuang.audit.repository.FdFordoAuditRepository;
import cn.com.chaochuang.casecomplaint.service.FdFordoCaseService;
import cn.com.chaochuang.common.util.JsonMapper;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.datacenter.domain.SysDataChange;
import cn.com.chaochuang.datacenter.domain.SysDataChangeApp;
import cn.com.chaochuang.datacenter.domain.SysDataChangeOa;
import cn.com.chaochuang.datacenter.reference.DataChangeTable;
import cn.com.chaochuang.docwork.domain.DocFile;
import cn.com.chaochuang.docwork.domain.FdFordo;
import cn.com.chaochuang.docwork.repository.FdFordoRepository;
import cn.com.chaochuang.docwork.service.DocFileService;
import cn.com.chaochuang.task.bean.DocFileInfo;
import cn.com.chaochuang.webservice.server.ITransferOAService;

import com.fasterxml.jackson.databind.JavaType;

/**
 * @author Shicx
 *
 */
@Service
@Transactional
public class CommonPendingHandleServiceImpl implements CommonPendingHandleService {

    @Autowired
    private FdFordoRepository        oaRepository;
    @Autowired
    private FdFordoAppRepository     supviseRepository;
    @Autowired
    private FdFordoAipcaseRepository aipcaseRepository;
    @Autowired
    private FdFordoCaseService       fdFordoCaseService;
    @Autowired
    private FdFordoAuditRepository   auditFordoRepository;
    @Autowired
    private ITransferOAService       transferOAService;
    @Autowired
    private DocFileService           docFileService;
    @Autowired
    private AppItemApplyRepository   appItemApplyRepository;

    /**
     * (non-Javadoc)
     *
     * @see cn.com.chaochuang.common.fdfordo.service.CommonPendingHandleService#analysisDataChange(cn.com.chaochuang.datacenter.domain.SysDataChange)
     */
    @Override
    public void analysisDataChange(SysDataChange dataChange, DataChangeTable changeName) {
        if (changeName == null || dataChange == null || StringUtils.isEmpty(dataChange.getChangeScript())) {
            return;
        }
        // 获取要操作的ID
        String[] items = dataChange.getChangeScript().split("=");
        if (items == null || items.length != 2) {
            return;
        }
        switch (changeName) {
        case 办案待办:// 办案系统
            FdFordoAipcase aipcaseFordo = aipcaseRepository.findByRmPendingId(items[1]);
            if (aipcaseFordo != null) {
                aipcaseRepository.delete(aipcaseFordo);
            }
            break;
        case 投诉举报待办:// 投诉举报系统
            fdFordoCaseService.deleteFordoByRmPendingId(items[1]);
            break;
        default:
            throw new RuntimeException("找不到对应的待办类型");
        }

    }

    /**
     * @see cn.com.chaochuang.common.fdfordo.service.CommonPendingHandleService#updateOADataIfExist(SysDataChangeOa,
     *      cn.com.chaochuang.datacenter.reference.DataChangeTable)
     */
    @Override
    public void updateOADataIfExist(SysDataChangeOa dataChange, DataChangeTable changeName) {
        if (changeName == null || dataChange == null || StringUtils.isEmpty(dataChange.getChangeScript())) {
            return;
        }
        // 获取要操作的ID
        String[] items = dataChange.getChangeScript().split("=");
        if (items == null || items.length != 2) {
            return;
        }
        FdFordo oaFordo = this.oaRepository.findByRmPendingItemId(items[1]);
        if (oaFordo != null) {
            DocFile docFile = docFileService.findByRmInstanceId(oaFordo.getRmInstanceId());
            // 更新文件信息
            if (docFile != null) {
                String json = transferOAService.getDocTransactInfo(oaFordo.getRmInstanceId());
                if (!Tools.isEmptyString(json)) {
                    JsonMapper mapper = JsonMapper.getInstance();
                    JavaType javaType = mapper.constructParametricType(ArrayList.class, DocFileInfo.class);
                    List<DocFileInfo> datas = (List<DocFileInfo>) mapper.readValue(json, javaType);
                    if (Tools.isNotEmptyList(datas)) {
                        docFileService.saveDocFilesDatas(datas.get(0), oaFordo);
                    }
                }
            }
            oaRepository.delete(oaFordo);
        }
    }

    /**
     * @see cn.com.chaochuang.common.fdfordo.service.CommonPendingHandleService#updateSuperviseDataIfExist(SysDataChangeApp,
     *      cn.com.chaochuang.datacenter.reference.DataChangeTable)
     */
    @Override
    public void updateSuperviseDataIfExist(SysDataChangeApp dataChange, DataChangeTable changeName) {
        if (changeName == null || dataChange == null || StringUtils.isEmpty(dataChange.getChangeScript())) {
            return;
        }
        // 获取要操作的ID
        String[] items = dataChange.getChangeScript().split("=");
        if (items == null || items.length != 2) {
            return;
        }
        List<FdFordoApp> superviseFordoList = supviseRepository.findByRmPendingId(items[1]);
        if (Tools.isNotEmptyList(superviseFordoList)) {
            supviseRepository.delete(superviseFordoList);
            // 更新办理数据？
        } else {
            List<FdFordoAudit> auditFordos = this.auditFordoRepository.findByRmPendingId(items[1]);
            // 审批系统待办记录不存在则查询审评查验系统
            if (Tools.isNotEmptyList(auditFordos)) {
                this.auditFordoRepository.delete(auditFordos);
            }
        }
    }

}
