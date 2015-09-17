/*
 * FileName:    FdFordoCaseService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年09月10日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.casecomplaint.service;

import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.casecomplaint.domain.FdFordoCase;
import cn.com.chaochuang.common.data.service.CrudRestService;
import cn.com.chaochuang.datacenter.domain.DataUpdate;

/**
 * @author Shicx
 *
 */
public interface FdFordoCaseService extends CrudRestService<FdFordoCase, Long> {

    /**
     * 保存待办
     * 
     * @param returnInfo
     */
    public void saveFdFordo(String returnInfo);

    /**
     * 修改待办localData字段
     * 
     * @param pendingId
     * @param localData
     */
    public void changeFordoLocalData(String pendingId, LocalData localData);

    /**
     * 删除同一个taskId的待办和dataUpdate记录
     * 
     * @param taskId
     * @param dataUpdate
     * @param backInfo
     */
    public void deleteFordoAndDataUpdate(String taskId, DataUpdate dataUpdate, String backInfo);

    /**
     * 根据远程待办id删除待办
     * 
     * @param pendingId
     */
    public void deleteFordoByRmPendingId(String pendingId);

}
