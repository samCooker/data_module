/*
 * FileName:    AipCaseNoteFileService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年7月21日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.aipcase.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import cn.com.chaochuang.aipcase.domain.AipCaseNoteFile;
import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.common.data.service.CrudRestService;
import cn.com.chaochuang.task.bean.AipLawContentData;

/**
 * @author Shicx
 *
 */
public interface AipCaseNoteFileService extends CrudRestService<AipCaseNoteFile, Long> {

    /**
     * 通过案件来源ID查找AIP_CASE_NOTE_FILE表记录，将文书id为key,md5为value组成map数据返回
     * 
     * @param caseApplyId
     * @return
     */
    public Map<Long, String> noteFileTransferToMapByCaseApplyId(Long caseApplyId) throws Exception;

    /**
     * 生成文书的html文件，保存文书信息
     * 
     * @param datas
     * @param rmCaseApplyId
     */
    public void saveAipCaseNoteFile(List<AipLawContentData> datas, Long rmCaseApplyId) throws Exception;

    /**
     * 根据远程文书id查找文书记录,保存文书存放路径并localData=1
     * 
     * @param contentData
     * @param localFileName
     */
    public void saveNoteFileForLocal(AipLawContentData contentData, String localFileName);

    /**
     * @param localData
     * @param page
     * @return
     */
    public List<AipCaseNoteFile> findByLocalData(LocalData localData, Pageable page);

}
