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
import cn.com.chaochuang.docwork.domain.FlowNodeOpinions;
import cn.com.chaochuang.task.bean.FlowNodeOpinionsInfo;

/**
 * @author Shicx
 *
 */
public interface FlowNodeOpinionsService extends CrudRestService<FlowNodeOpinions, Long> {

    /** 保存远程获取的意见表 */
    void saveRemoteFlowNodeOpinions(List<FlowNodeOpinionsInfo> datas, Long fileId);

}
