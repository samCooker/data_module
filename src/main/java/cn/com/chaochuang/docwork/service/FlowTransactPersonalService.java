/*
 * FileName:    FlowTransactPersonalService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年4月30日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.docwork.service;

import java.util.List;

import cn.com.chaochuang.common.data.service.CrudRestService;
import cn.com.chaochuang.docwork.domain.DocFile;
import cn.com.chaochuang.docwork.domain.FlowTransactPersonal;
import cn.com.chaochuang.task.bean.FlowNodeBeanInfo;

/**
 * @author Shicx
 *
 */
public interface FlowTransactPersonalService extends CrudRestService<FlowTransactPersonal, Long> {

    /**
     * 保存人员经办过的公文记录，公文id（原系统公文编号）和办理人id联合唯一
     * */
    void saveFlowTransactPersonalInfo(List<FlowNodeBeanInfo> flowNodeInfoList, DocFile file) throws Exception;
}
