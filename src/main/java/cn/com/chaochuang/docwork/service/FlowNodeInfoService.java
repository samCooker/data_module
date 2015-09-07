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
import cn.com.chaochuang.docwork.domain.FlowNodeInfo;
import cn.com.chaochuang.task.bean.FlowNodeBeanInfo;

/**
 * @author Shicx
 *
 */
public interface FlowNodeInfoService extends CrudRestService<FlowNodeInfo, Long> {

    /** 保存从远程取出的节点信息 */
    void saveRemoteFlowNodeInfo(List<FlowNodeBeanInfo> datas, Long fileId);

    /**
     * 通过远程节点实例id查找相关流程节点
     * */
    List<FlowNodeInfo> findByRmInstanceId(String rmInstanceId);

}
