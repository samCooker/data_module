/*
 * FileName:    FdFordoService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月22日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.docwork.service;

import java.util.List;

import cn.com.chaochuang.common.data.service.CrudRestService;
import cn.com.chaochuang.datacenter.domain.SysDataChange;
import cn.com.chaochuang.docwork.domain.FdFordo;
import cn.com.chaochuang.docwork.reference.FordoSource;
import cn.com.chaochuang.task.bean.PendingCommandInfo;

/**
 * @author LLM
 *
 */
public interface FdFordoService extends CrudRestService<FdFordo, Long> {

    /**
     * 获取最大的数据导入时间
     *
     * @return
     */
    PendingCommandInfo selectMaxInputDate();

    /**
     * 批量插入待办事宜数据
     *
     * @param pendingItems
     * @param fordoSource
     *            TODO
     */
    void insertFdFordos(List<PendingCommandInfo> pendingItems, FordoSource fordoSource);

    /**
     * 分析系统数据的处理
     *
     * @param dataChange
     */
    void analysisDataChange(SysDataChange dataChange);
}
