/*
 * FileName:    AipCaseNoteFileServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年7月21日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.aipcase.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.aipcase.domain.AipCaseNoteFile;
import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.aipcase.repository.AipCaseNoteFileRepository;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.task.bean.AipLawContentData;

/**
 * @author Shicx
 *
 */
@Service
@Transactional
public class AipCaseNoteFileServiceImpl extends SimpleLongIdCrudRestService<AipCaseNoteFile> implements
                AipCaseNoteFileService {

    @Autowired
    private AipCaseNoteFileRepository repository;

    @Override
    public SimpleDomainRepository<AipCaseNoteFile, Long> getRepository() {
        return repository;
    }

    /**
     * @see cn.com.chaochuang.aipcase.service.AipCaseNoteFileService#noteFileTransferToMapByCaseApplyId(java.lang.Long)
     */
    @Override
    public Map<Long, String> noteFileTransferToMapByCaseApplyId(Long caseApplyId) throws Exception {
        List<AipCaseNoteFile> noteFileList = repository.findByRmCaseApplyId(caseApplyId);
        if (noteFileList == null || noteFileList.size() == 0) {
            return null;
        }
        Map<Long, String> dataMap = new HashMap<Long, String>();
        for (AipCaseNoteFile noteFile : noteFileList) {
            dataMap.put(noteFile.getRmNoteFileId(), noteFile.getMdfCode());
        }
        return dataMap;
    }

    /**
     * @see cn.com.chaochuang.aipcase.service.AipCaseNoteFileService#saveLawContentDataAndHtmlFile(java.util.List)
     */
    @Override
    public void saveAipCaseNoteFile(List<AipLawContentData> datas, Long rmCaseApplyId) throws Exception {
        List<AipCaseNoteFile> deleteNoteFileList = repository.findByRmCaseApplyId(rmCaseApplyId);
        if (datas != null) {
            for (AipLawContentData lawContent : datas) {
                AipCaseNoteFile noteFile = repository.findByRmNoteFileId(lawContent.getRmNoteFileId());
                if (noteFile == null) {
                    noteFile = new AipCaseNoteFile();
                    BeanUtils.copyProperties(noteFile, lawContent);
                }
                if (deleteNoteFileList != null && noteFile != null) {
                    deleteNoteFileList.remove(noteFile);
                }
                noteFile.setLocalData(LocalData.非本地数据);
                repository.save(noteFile);
            }
        }
        // 无新文书则删除原有文书
        if (deleteNoteFileList != null) {
            repository.deleteInBatch(deleteNoteFileList);
        }
    }

    /**
     * @see cn.com.chaochuang.aipcase.service.AipCaseNoteFileService#saveNoteFileForLocal(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void saveNoteFileForLocal(AipLawContentData contentData, String localFileName) {
        if (contentData == null) {
            return;
        }
        AipCaseNoteFile noteFile = repository.findByRmNoteFileId(contentData.getRmNoteFileId());
        if (noteFile != null) {
            noteFile.setFilePath(localFileName);
            noteFile.setMdfCode(contentData.getMdfCode());
            noteFile.setLocalData(LocalData.有本地数据);
            repository.save(noteFile);
        }
    }

    /**
     * @see cn.com.chaochuang.aipcase.service.AipCaseNoteFileService#findByLocalData(cn.com.chaochuang.aipcase.reference.LocalData,
     *      org.springframework.data.domain.Pageable)
     */
    @Override
    public List<AipCaseNoteFile> findByLocalData(LocalData localData, Pageable page) {
        return repository.findByLocalData(localData, page);
    }

}
