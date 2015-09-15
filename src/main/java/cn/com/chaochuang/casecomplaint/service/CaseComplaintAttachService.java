/*
 * FileName:    CaseComplaintAttachService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年09月10日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.casecomplaint.service;

import cn.com.chaochuang.casecomplaint.bean.BaseCaseComplaintInfo;
import cn.com.chaochuang.casecomplaint.domain.CaseComplaintAttach;
import cn.com.chaochuang.common.data.service.CrudRestService;

/**
 * @author Shicx
 *
 */
public interface CaseComplaintAttachService extends CrudRestService<CaseComplaintAttach, Long> {

    /**
     * 保存投诉举报附件信息（投诉附件及处理附件）
     * 
     * @param baseInfo
     */
    void saveComplaintAttach(BaseCaseComplaintInfo baseInfo);

}
