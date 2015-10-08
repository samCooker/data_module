/*
 * FileName:    DocFileService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月30日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.aipcase.service;

import java.util.List;

import cn.com.chaochuang.aipcase.bean.AipCaseAttachInfo;
import cn.com.chaochuang.aipcase.domain.AipCaseAttach;
import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.common.data.service.CrudRestService;

/**
 * @author LJX
 *
 */
public interface AipCaseAttachService extends CrudRestService<AipCaseAttach, Long> {

    /**
     * 保存附件信息
     * 
     * @param attachInfos
     * @param rmCaseApplyId
     */
    void saveAttachments(List<AipCaseAttachInfo> attachInfos, Long rmCaseApplyId);

    /**
     * 选择未下载附件
     * 
     * @return
     */
    List<AipCaseAttach> selectUnLocalAttach();

    /**
     * 保存附件信息
     * 
     * @param attach
     * @param localData
     *            是否本地数据
     * @param localFileName
     *            本地数据路径，若不为空则localData=本地数据
     */
    void saveAttachForLocal(AipCaseAttach attach, LocalData localData, String localFileName);

}
