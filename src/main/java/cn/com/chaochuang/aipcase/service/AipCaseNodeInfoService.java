/*
 * FileName:    DocFileService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月30日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.aipcase.service;

import java.util.List;

import cn.com.chaochuang.aipcase.bean.AipCaseNodes;
import cn.com.chaochuang.aipcase.domain.AipCaseNodeInfo;
import cn.com.chaochuang.common.data.service.CrudRestService;

/**
 * @author LJX
 *
 */
public interface AipCaseNodeInfoService extends CrudRestService<AipCaseNodeInfo, Long> {

    /**
     * 保存办理环节信息
     * 
     * @param nodeInfos
     */
    void saveNodeInfos(List<AipCaseNodes> nodeInfos);

}
