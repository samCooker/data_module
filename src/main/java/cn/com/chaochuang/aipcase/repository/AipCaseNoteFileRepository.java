/*
 * FileName:    AipCaseNoteFileRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年7月4日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.aipcase.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;

import cn.com.chaochuang.aipcase.domain.AipCaseNoteFile;
import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;

/**
 * @author LLM
 *
 */
public interface AipCaseNoteFileRepository extends SimpleDomainRepository<AipCaseNoteFile, Long> {

    /**
     * 通过远程文书ID查找文书文件记录
     * 
     * @return
     */
    AipCaseNoteFile findByRmNoteFileId(Long noteFileId);

    /**
     * 通过案件来源id查找所有相关文书
     * 
     * @param rmCaseApplyId
     * @return
     */
    List<AipCaseNoteFile> findByRmCaseApplyId(Long rmCaseApplyId);

    /**
     * @param localData
     * @param page
     * @return
     */
    List<AipCaseNoteFile> findByLocalData(LocalData localData, Pageable page);

}
