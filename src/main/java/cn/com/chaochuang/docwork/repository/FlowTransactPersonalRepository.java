/*
 * FileName:    DocFileRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月30日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.docwork.repository;

import java.util.List;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.docwork.domain.DocFile;
import cn.com.chaochuang.docwork.domain.FlowTransactPersonal;

/**
 * @author Shicx
 *
 */
public interface FlowTransactPersonalRepository extends SimpleDomainRepository<FlowTransactPersonal, Long> {

    /**
     * 根据办理人id和公文id和共享标识shareFlag查找公文个人办理记录，查找结果返回唯一
     * */
    FlowTransactPersonal findByDocFileAndTransactIdAndShareFlag(DocFile file, Long transactId, String shareFlag);

    /**
     * 根据公文id和公文主办处室id和共享标识shareFlag查找公文个人办理记录，查找结果返回唯一
     * */
    // FlowTransactPersonal findByDocFileAndRedactDeptIdAndShareFlag(DocFile file, Long redactDeptId, String shareFlag);

    List<FlowTransactPersonal> findByDocFileAndRedactDeptIdAndShareFlag(DocFile file, Long redactDeptId,
                    String shareFlag);

    /**
     * 根据公文id和公文所属单位id和共享标识shareFlag查找公文个人办理记录，查找结果返回唯一
     * */
    // FlowTransactPersonal findByDocFileAndUnitOrgIdAndShareFlag(DocFile file, Long unitOrgId, String shareFlag);
    List<FlowTransactPersonal> findByDocFileAndUnitOrgIdAndShareFlag(DocFile file, Long unitOrgId, String shareFlag);

}
