/*
 * FileName:    CaseComplaintRepository
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年09月10日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.casecomplaint.repository;

import cn.com.chaochuang.casecomplaint.domain.CaseComplaint;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;

/**
 * @author Shicx
 *
 */
public interface CaseComplaintRepository extends SimpleDomainRepository<CaseComplaint, Long> {

    /**
     * 通过远程投诉举报id查找投诉举报信息
     * 
     * @param complaintId
     * @return
     */
    CaseComplaint findByRmComplaintId(Long complaintId);

    /**
     * @param flowInstId
     * @return
     */
    CaseComplaint findByFlowInstId(Long flowInstId);

}
