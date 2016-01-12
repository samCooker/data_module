/*
 * FileName:    AppItemApplyService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年6月1日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.appflow.service;

import java.util.List;

import cn.com.chaochuang.appflow.bean.AppFlowShowData;
import cn.com.chaochuang.appflow.domain.AppItemApply;
import cn.com.chaochuang.common.data.service.CrudRestService;
import cn.com.chaochuang.datacenter.domain.SysDataChangeApp;

/**
 * @author LLM
 *
 */
public interface AppItemApplyService extends CrudRestService<AppItemApply, Long> {

    /**
     * 批量保存审批数据
     *
     * @param appDatas
     */
    public void saveAppItemApplyDatas(List<AppFlowShowData> appDatas);

    /**
     * 更新移动端的执业药师数据
     * @param dataChange
     */
    public void updateOrDelPharmacist(SysDataChangeApp dataChange);

}
