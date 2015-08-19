/*
 * FileName:    CommonPendingHandleServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年6月25日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.common.fdfordo.service;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.aipcase.domain.FdFordoAipcase;
import cn.com.chaochuang.aipcase.repository.FdFordoAipcaseRepository;
import cn.com.chaochuang.appflow.domain.FdFordoApp;
import cn.com.chaochuang.appflow.repository.FdFordoAppRepository;
import cn.com.chaochuang.datacenter.domain.SysDataChange;
import cn.com.chaochuang.datacenter.reference.DataChangeTable;
import cn.com.chaochuang.docwork.domain.FdFordo;
import cn.com.chaochuang.docwork.repository.FdFordoRepository;

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
        case 公文待办:// 公文系统
            FdFordo oaFordo = this.oaRepository.findByRmPendingItemId(items[1]);
            if (oaFordo != null) {
                oaRepository.delete(oaFordo);
            }
            break;
        case 审批待办:// 审批系统
            FdFordoApp superviseFordo = supviseRepository.findByRmPendingId(items[1]);
            if (superviseFordo != null) {
                supviseRepository.delete(superviseFordo);
            }
            break;
        case 办案待办:// 办案系统
            FdFordoAipcase aipcaseFordo = aipcaseRepository.findByRmPendingId(items[1]);
            if (aipcaseFordo != null) {
                aipcaseRepository.delete(aipcaseFordo);
            }
            break;
        default:
            throw new RuntimeException("找不到对应的待办类型");
        }

    }

}
