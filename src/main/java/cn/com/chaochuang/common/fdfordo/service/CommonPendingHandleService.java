/*
 * FileName:    CommonFordoService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年6月25日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.common.fdfordo.service;

import cn.com.chaochuang.datacenter.domain.SysDataChange;
import cn.com.chaochuang.datacenter.domain.SysDataChangeApp;
import cn.com.chaochuang.datacenter.domain.SysDataChangeOa;
import cn.com.chaochuang.datacenter.reference.DataChangeTable;

/**
 * @author Shicx
 *
 */
public interface CommonPendingHandleService {

    /**
     * 分析系统数据的处理
     *
     * @param dataChange
     */
    void analysisDataChange(SysDataChange dataChange, DataChangeTable changName);

    /**
     * 同步移动端与OA的待办及办理信息，若办理信息存在于移动端数据库，则从原系统获取办理信息并删除旧待办，否则只删除旧待办
     * 
     * @param dataChange
     * @param changeName
     */
    public void updateOADataIfExist(SysDataChangeOa dataChange, DataChangeTable changeName);

    /**
     * 同步移动端与行政审批的待办及办理信息，若办理信息存在于移动端数据库，则从原系统获取办理信息并删除旧待办，否则只删除旧待办
     * 
     * @param dataChange
     * @param changeName
     */
    public void updateSuperviseDataIfExist(SysDataChangeApp dataChange, DataChangeTable changeName);
}
