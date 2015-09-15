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

}
