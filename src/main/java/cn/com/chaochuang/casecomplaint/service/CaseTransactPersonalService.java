/*
 * FileName:    CaseTransactPersonalService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年09月23日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.casecomplaint.service;

import cn.com.chaochuang.casecomplaint.domain.CaseComplaintNodeInfo;
import cn.com.chaochuang.casecomplaint.domain.CaseTransactPersonal;
import cn.com.chaochuang.common.data.service.CrudRestService;

/**
 * @author Shicx
 *
 */
public interface CaseTransactPersonalService extends CrudRestService<CaseTransactPersonal, Long> {

    /**
     * 根据待办保存经办信息
     * 
     * @param nodeInfo
     */
    void saveTransactRecordByFlowNode(CaseComplaintNodeInfo nodeInfo);

}
