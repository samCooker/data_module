/*
 * FileName:    CaseComplaintService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年09月10日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.casecomplaint.service;

import java.util.List;

import cn.com.chaochuang.casecomplaint.domain.CaseComplaint;
import cn.com.chaochuang.common.data.service.CrudRestService;

/**
 * @author Shicx
 *
 */
public interface CaseComplaintService extends CrudRestService<CaseComplaint, Long> {

    /**
     * 保存投诉举报信息（包括基本信息、节点、意见和附件信息）
     * 
     * @param returnInfo
     * @param pendingIds
     */
    void saveCaseComplaintInfo(String returnInfo, List<String> pendingIds);

}
