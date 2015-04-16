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
import cn.com.chaochuang.datacenter.domain.SysDataChange;
import cn.com.chaochuang.docwork.domain.DocFile;
import cn.com.chaochuang.task.bean.DocFileInfo;

/**
 * @author Shicx
 *
 */
public interface DocFileService extends CrudRestService<DocFile, Long> {

    /** 保存远程获取的公文数据，包括附件信息和流程信息 */
    void saveDocFilesDatas(List<DocFileInfo> datas) throws Exception;

    /** 获取公文数据最大的导入时间 */
    String getDocFileMaxInputDate();

    /** 将公文状态改为办结 */
    void finishDocFile(SysDataChange item);

}
