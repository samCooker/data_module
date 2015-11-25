/*
 * FileName:    DocFileRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月30日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.docwork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.docwork.domain.DocFileAttach;

/**
 * @author Shicx
 *
 */
public interface DocFileAttachRepository extends SimpleDomainRepository<DocFileAttach, Long> {

    /**
     * 查询未存到本地的附件数据
     *
     * @param localDate
     * @return
     */
    List<DocFileAttach> findByLocalData(LocalData localDate);

    /**
     * 通过原远程附件id查找附件信息
     *
     * */
    DocFileAttach findByRmAttachId(String rmAttachId);

    /**
     * 查询公文的所有附件
     * 
     * @param fileId
     * @return
     */
    List<DocFileAttach> findByDocId(Long fileId);

    /**
     * @return
     */
    @Query(value = "select inst_id from gx_fda_oa.wf_data_shareword where inst_id in (select rm_instance_id from doc_file where doc_id not in(select doc_id from doc_file_attach))", nativeQuery = true)
    List<Object> findUnfeatchAttachInstId();
}
