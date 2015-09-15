/*
 * FileName:    CaseComplaintNodeInfoRepository
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年09月10日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.casecomplaint.repository;

import cn.com.chaochuang.casecomplaint.domain.CaseComplaintNodeInfo;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;

/**
 * @author Shicx
 *
 */
public interface CaseComplaintNodeInfoRepository extends SimpleDomainRepository<CaseComplaintNodeInfo, Long> {

    /**
     * 通过远程节点id获取节点信息
     * 
     * @param hisnoId
     * @return
     */
    CaseComplaintNodeInfo findByRmNodeInfoId(Long hisnoId);

}
