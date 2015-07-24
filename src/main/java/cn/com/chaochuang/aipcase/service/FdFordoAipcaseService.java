/*
 * FileName:    FdFordoAipcaseService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年7月4日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.aipcase.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import cn.com.chaochuang.aipcase.bean.AipCasePendingHandleInfo;
import cn.com.chaochuang.aipcase.domain.FdFordoAipcase;
import cn.com.chaochuang.common.data.service.CrudRestService;
import cn.com.chaochuang.task.bean.AipCasePendingInfo;

/**
 * @author LLM
 *
 */
public interface FdFordoAipcaseService extends CrudRestService<FdFordoAipcase, Long> {
    /**
     * 获取最大的数据导入时间
     *
     * @param source
     *            待办来源
     *
     * @return
     */
    AipCasePendingHandleInfo selectMaxInputDate();

    /**
     * 批量插入待办事宜数据
     *
     * @param pendingItems
     * @param fordoSource
     *            TODO
     */
    void insertFdFordos(List<AipCasePendingInfo> pendingItems);

    /**
     * 获取所有未下载到本地数据的待办事宜
     *
     * @return
     */
    List<FdFordoAipcase> selectUnLocalData();

    /**
     * 获取所有未下载到本地数据的待办事宜
     * 
     * @param page
     * @return
     */
    List<FdFordoAipcase> selectUnLocalData(Pageable page);

    /**
     * 更新待办事宜的本地状态为“在本地”
     *
     * @param rmPendingId
     */
    void updateLocalData(String rmPendingId);
}
