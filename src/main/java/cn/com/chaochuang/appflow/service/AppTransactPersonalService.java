/*
 * FileName:    AppTransactPersonal.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年7月1日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.appflow.service;

import java.util.List;

import cn.com.chaochuang.appflow.domain.AppFlowNodeInfo;
import cn.com.chaochuang.appflow.domain.AppItemApply;
import cn.com.chaochuang.appflow.domain.AppTransactPersonal;
import cn.com.chaochuang.common.data.service.CrudRestService;

/**
 * @author Shicx
 *
 */
public interface AppTransactPersonalService extends CrudRestService<AppTransactPersonal, Long> {

    /**
     * 根据审批流程节点，保存个人经办信息
     *
     * @param nodeInfoList
     *            流程节点列表
     * @param apply
     *            事项申请基本信息
     */
    public void saveAppTransactPersonalByFlowNodes(List<AppFlowNodeInfo> nodeInfoList, AppItemApply apply);

}
