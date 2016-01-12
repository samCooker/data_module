/*
 * FileName:    DocFileService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月30日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.docwork.service;

import java.util.List;

import cn.com.chaochuang.common.data.service.CrudRestService;
import cn.com.chaochuang.datacenter.domain.SysDataChangeOa;
import cn.com.chaochuang.docwork.domain.DocFile;
import cn.com.chaochuang.docwork.domain.FdFordo;
import cn.com.chaochuang.task.bean.DocFileInfo;
import cn.com.chaochuang.task.bean.FlowNodeOpinionsInfo;

/**
 * @author Shicx
 *
 */
public interface DocFileService extends CrudRestService<DocFile, Long> {

    /**
     * 保存远程获取的公文数据，包括附件信息和流程信息
     *
     * @param datas
     * @param fordoData
     */
    void saveDocFilesDatas(List<DocFileInfo> datas, List<FdFordo> fordoData);

    /**
     * 保存远程获取的公文数据，包括附件信息和流程信息
     *
     * @param data
     * @param fordo
     */
    void saveDocFilesDatas(DocFileInfo data, FdFordo fordo);

    /** 获取公文数据最大的导入时间 */
    FlowNodeOpinionsInfo getDocFileMaxInputDate();

    /** 1获取oa的历史节点信息，将mobile端的节点信息删除，添加获取的历史节点信息，2将公文状态改为办结 */
    void finishDocFile(String hisNoJsonStr);

    /** 1获取oa的历史节点信息，将mobile端的节点信息删除，添加获取的历史节点信息，2将公文状态改为办结 */
    void finishDocFile(SysDataChangeOa item);

    /**
     * @param rmInstanceId
     * @return
     */
    DocFile findByRmInstanceId(String rmInstanceId);

}
