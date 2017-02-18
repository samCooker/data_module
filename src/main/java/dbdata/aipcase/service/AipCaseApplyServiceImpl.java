/*
 * FileName:    AipCaseApplyServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2017
 * History:     2017年02月16日 (Shicx) 1.0 Create
 */

package dbdata.aipcase.service;

import dbdata.aipcase.domain.AipCaseApply;
import dbdata.aipcase.repository.AipCaseApplyRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Shicx
 */
@Service
@Transactional
public class AipCaseApplyServiceImpl implements AipCaseApplyService {

    private Logger dataLogger = Logger.getLogger("data.logger");

    @Autowired
    private AipCaseApplyRepository aipCaseApplyRepository;

    @Override
    public boolean deleteCaseApply(Long caseApplyId) {
        dataLogger.debug("-------删除案件,案件ID："+caseApplyId+"-------");
        if(caseApplyId == null){
            return false;
        }
        //案件详情
        AipCaseApply caseApply = aipCaseApplyRepository.findOne(caseApplyId);
        if(caseApply == null){
            dataLogger.debug("找不到案件ID:"+caseApplyId);
            return false;
        }
        return false;
    }
}

