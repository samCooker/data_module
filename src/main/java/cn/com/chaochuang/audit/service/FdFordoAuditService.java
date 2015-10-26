/*
 * FileName:    FdFordoAuditService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年10月9日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.audit.service;

import java.util.List;

import cn.com.chaochuang.audit.bean.AuditPendingHandleInfo;
import cn.com.chaochuang.audit.domain.AuditTask;
import cn.com.chaochuang.audit.domain.FdFordoAudit;
import cn.com.chaochuang.common.data.service.CrudRestService;
import cn.com.chaochuang.datacenter.domain.DataUpdate;
import cn.com.chaochuang.task.bean.WebServiceNodeInfo;

/**
 * @author LLM
 *
 */
public interface FdFordoAuditService extends CrudRestService<FdFordoAudit, Long> {
    /**
     * 获取最大的数据导入时间
     *
     * @return
     */
    AuditPendingHandleInfo selectMaxInputDate();

    /**
     * 批量插入待办事宜数据
     *
     * @param pendingItems
     * @param fordoSource
     */
    void insertFdFordos(List<AuditPendingHandleInfo> pendingItems);

    /**
     * 根据原系统任务编号获取任务记录
     * 
     * @param rmTaskId
     * @return
     */
    AuditTask selectTaskByRmTaskId(Long rmTaskId);

    /**
     * @param dataUpdate
     * @param nodeInfo
     * @param backInfo
     */
    void deleteDataUpdateAndFordo(DataUpdate dataUpdate, WebServiceNodeInfo nodeInfo, String backInfo);
}
