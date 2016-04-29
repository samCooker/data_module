/*
 * FileName:    FdFordoDocService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2016
 * History:     2016年4月26日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.docex.service;

import java.util.List;

import cn.com.chaochuang.common.data.service.CrudRestService;
import cn.com.chaochuang.docex.domain.FdFordoDoc;
import cn.com.chaochuang.task.bean.DocexPendingItem;
import cn.com.chaochuang.task.bean.OAPendingHandleInfo;

/**
 * @author LLM
 *
 */
public interface FdFordoDocService extends CrudRestService<FdFordoDoc, Long> {
    /**
     * 获取最大的数据导入时间
     *
     * @return
     */
    OAPendingHandleInfo selectMaxInputDate();

    /**
     * 保存公文传输的待办记录
     *
     * @param datas
     */
    void insertFdFordos(List<DocexPendingItem> pendingItems);
}
