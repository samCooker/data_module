/*
 * FileName:    CaseComplaintOpinionService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年09月10日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.casecomplaint.service;

import cn.com.chaochuang.casecomplaint.bean.BaseCaseComplaintInfo;
import cn.com.chaochuang.casecomplaint.domain.CaseComplaintOpinion;
import cn.com.chaochuang.common.data.service.CrudRestService;

/**
 * @author Shicx
 *
 */
public interface CaseComplaintOpinionService extends CrudRestService<CaseComplaintOpinion, Long> {

    /**
     * 保存投诉举报意见信息
     * 
     * @param baseInfo
     */
    void saveComplaintOpinion(BaseCaseComplaintInfo baseInfo);

}
