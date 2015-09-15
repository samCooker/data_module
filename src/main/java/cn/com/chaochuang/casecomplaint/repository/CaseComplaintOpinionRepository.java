/*
 * FileName:    CaseComplaintOpinionRepository
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年09月10日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.casecomplaint.repository;

import cn.com.chaochuang.casecomplaint.domain.CaseComplaintOpinion;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;

/**
 * @author Shicx
 *
 */
public interface CaseComplaintOpinionRepository extends SimpleDomainRepository<CaseComplaintOpinion, Long> {

    /**
     * 通过远程意见id查找意见信息
     * 
     * @param approveId
     * @return
     */
    CaseComplaintOpinion findByRmOpinionId(Long approveId);

}
