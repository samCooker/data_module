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
import cn.com.chaochuang.docwork.domain.DocFileAttach;
import cn.com.chaochuang.task.bean.DocFileAttachInfo;

/**
 * @author Shicx
 *
 */
public interface DocFileAttachService extends CrudRestService<DocFileAttach, Long> {

    /** 保存从远程取出的附件信息 */
    void saveRemoteDocFileAttach(List<DocFileAttachInfo> datas, Long fileId) throws Exception;

}
