/*
 * FileName:    CommonPendingHandleServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年6月25日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.common.fdfordo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.appflow.domain.FdFordoApp;
import cn.com.chaochuang.appflow.repository.FdFordoAppRepository;
import cn.com.chaochuang.common.fdfordo.reference.SystemType;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.datacenter.domain.SysDataChange;
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
    private FdFordoRepository    oaRepository;
    @Autowired
    private FdFordoAppRepository supviseRepository;

    /**
     * (non-Javadoc)
     *
     * @see cn.com.chaochuang.common.fdfordo.service.CommonPendingHandleService#analysisDataChange(cn.com.chaochuang.datacenter.domain.SysDataChange)
     */
    @Override
    public void analysisDataChange(SysDataChange dataChange) {
        if (Tools.isEmptyString(dataChange.getChangeScript())) {
            return;
        }
        // 获取要操作的ID
        String[] items = dataChange.getChangeScript().split("=");
        if (items == null || items.length != 2) {
            return;
        }
        // 判断要删除哪个系统的待办
        if (dataChange.getChangeTableName() == null) {
            return;
        }
        String[] type = dataChange.getChangeTableName().split("_");
        SystemType systemType = SystemType.valueOf(type[0]);
        if (systemType == null) {
            return;
        }
        switch (systemType) {
        case oa:// 公文系统
            FdFordo oaFordo = this.oaRepository.findByRmPendingItemId(items[1]);
            if (oaFordo != null) {
                oaRepository.delete(oaFordo);
            }
            break;
        case supervise:// 审批系统
            FdFordoApp superviseFordo = supviseRepository.findByRmPendingId(items[1]);
            if (superviseFordo != null) {
                supviseRepository.delete(superviseFordo);
            }
            break;
        case aipcase:// 办案系统
            // undone
            break;
        default:
            throw new RuntimeException("找不到对应的待办类型");
        }

    }

}
