/*
 * FileName:    FdFordoCompService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2016
 * History:     2016年3月31日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.common.fdfordo.service;

import java.util.List;

import cn.com.chaochuang.common.data.service.CrudRestService;
import cn.com.chaochuang.common.fdfordo.domain.FdFordoComp;

/**
 * @author LLM
 *
 */
public interface FdFordoCompService extends CrudRestService<FdFordoComp, Long> {

    /**
     * 根据接收人编号查询综合待办
     *
     * @param recipientId
     *            接收人编号
     * @return 综合待办记录
     */
    List<FdFordoComp> selectFdFordoCompByRecipient(Long recipientId);

    /**
     * 保存公共待办表
     * 
     * @param fordo
     */
    void saveFdFordoComp(FdFordoComp fordo);
}
