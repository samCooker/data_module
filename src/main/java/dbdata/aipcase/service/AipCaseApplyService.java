/*
 * FileName:    AipCaseApplyService.java
 * Description: ${DESCRIPTION}
 * Company:     南宁超创信息工程有限公司
 * Copyright:    ChaoChuang (c) 2017
 * History:     2017年02月16日 (cookie) 1.0 Create
 */
package dbdata.aipcase.service;

/**
 * @author cookie
 */
public interface AipCaseApplyService {

    /**
     * 删除案件（包括案件的文书，审批意见，审批流程，待办等）
     * @param caseApplyId
     * @return
     */
    boolean deleteCaseApply(Long caseApplyId);
}
