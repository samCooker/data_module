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
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${transferFile.rootPath}")
    private String                    transferFileRootPath;
    @Value("${docFile.savePath}")
    private String                    docFilePath;
    @Value("${pdfFile.savePath}")
    private String                    pdfFilePath;

    @Override
    public SimpleDomainRepository<AipCaseNoteFile, Long> getRepository() {
        return repository;
    }

    /**
     * (non-Javadoc)
     * 
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
     * (non-Javadoc)
     * 
     * @see cn.com.chaochuang.aipcase.service.AipCaseNoteFileService#saveLawContentDataAndHtmlFile(java.util.List)
     */
    @Override
    public void saveAipCaseNoteFile(List<AipLawContentData> datas) throws Exception {
        if (datas != null) {
            for (AipLawContentData data : datas) {
                AipCaseNoteFile noteFile = repository.findByRmNoteFileId(data.getRmNoteFileId());
                if (noteFile == null) {
                    noteFile = new AipCaseNoteFile();
                    BeanUtils.copyProperties(noteFile, data);
                }
                noteFile.setLocalData(LocalData.非本地数据);
                repository.save(noteFile);
            }
        }
    }

    @Override
    public void saveNoteFileForLocal(Long rmNoteFileId, String localFileName) {
        AipCaseNoteFile noteFile = repository.findByRmNoteFileId(rmNoteFileId);
        if (noteFile != null) {
            noteFile.setFilePath(localFileName);
            noteFile.setLocalData(LocalData.有本地数据);
        }
    }

    @Override
    public List<AipCaseNoteFile> findByLocalData(LocalData localData, Pageable page) {
        return repository.findByLocalData(localData, page);
    }

}
