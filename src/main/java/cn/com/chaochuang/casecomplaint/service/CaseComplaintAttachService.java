/*
 * FileName:    CaseComplaintAttachService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年09月10日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.casecomplaint.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import cn.com.chaochuang.aipcase.reference.LocalData;
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

    /**
     * @param localData
     * @param page
     * @return
     */
    List<CaseComplaintAttach> findAttachByLocalData(LocalData localData, Pageable page);

    /**
     * 保存附件的路径信息并将localData置为1
     * 
     * @param attach
     * @param localFileName
     */
    void saveAttachForLocal(CaseComplaintAttach attach, String localFileName);

}
