/*
 * FileName:    CaseComplaintAttachRepository
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年09月10日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.casecomplaint.repository;

import java.util.List;
import java.util.Set;

import cn.com.chaochuang.casecomplaint.domain.CaseComplaintAttach;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;

/**
 * @author Shicx
 *
 */
public interface CaseComplaintAttachRepository extends SimpleDomainRepository<CaseComplaintAttach, Long> {

    /**
     * 通过远程附件标识查找附件列表信息
     * 
     * @param rmAffixId
     * @return
     */
    List<CaseComplaintAttach> findByRmAffixIdIn(Set<String> rmAffixIdSet);

    /**
     * 通过远程附件id查找附件信息
     * 
     * @param fileId
     * @return
     */
    CaseComplaintAttach findByRmAttachId(Long fileId);

}
