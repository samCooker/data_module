/*
 * FileName:    DocFileService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月30日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.docwork.service;

import java.util.List;

import cn.com.chaochuang.common.data.service.CrudRestService;
import cn.com.chaochuang.datacenter.bean.BackData;
import cn.com.chaochuang.docwork.domain.FlowNodeInfo;
import cn.com.chaochuang.task.bean.FlowNodeBeanInfo;

/**
 * @author Shicx
 *
 */
public interface FlowNodeInfoService extends CrudRestService<FlowNodeInfo, Long> {

    /** 保存从远程取出的附件信息 */
    void saveRemoteFlowNodeInfo(List<FlowNodeBeanInfo> datas, Long fileId) throws Exception;

    /**
     * 根据远程OA返回的数据跟新mobile端FlowNodeInfo表， 根据远程实例id(RM_INSTANCE_ID), 节点id(NODE_ID),接收人id(TRANSACT_ID)查找节点信息,再跟新节点实例
     *
     * @param backDataList
     *            远程OA返回的数据列表
     * */
    void findAndUpdateFlowNodeInfo(List<BackData> backDataList);

}
