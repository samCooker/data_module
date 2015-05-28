/*
 * FileName:    FdFordoAppService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年5月28日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.appflow.service;

import java.util.List;

import cn.com.chaochuang.appflow.bean.AppFlowPendingHandleInfo;
import cn.com.chaochuang.appflow.domain.FdFordoApp;
import cn.com.chaochuang.common.data.service.CrudRestService;
import cn.com.chaochuang.datacenter.domain.SysDataChange;

/**
 * @author LLM
 *
 */
public interface FdFordoAppService extends CrudRestService<FdFordoApp, Long> {
    /**
     * 获取最大的数据导入时间
     *
     * @return
     */
    AppFlowPendingHandleInfo selectMaxInputDate();

    /**
     * 批量插入待办事宜数据
     *
     * @param pendingItems
     * @param fordoSource
     */
    void insertFdFordos(List<AppFlowPendingHandleInfo> pendingItems);

    /**
     * 分析系统数据的处理
     *
     * @param dataChange
     */
    void analysisDataChange(SysDataChange dataChange);
}
