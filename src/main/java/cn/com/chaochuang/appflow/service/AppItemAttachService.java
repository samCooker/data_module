/*
 * FileName:    AppItemAttachService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年6月2日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.appflow.service;

import java.util.List;

import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.appflow.domain.AppItemAttach;
import cn.com.chaochuang.common.data.service.CrudRestService;
import cn.com.chaochuang.datacenter.domain.SysDataChangeApp;

/**
 * @author LLM
 *
 */
public interface AppItemAttachService extends CrudRestService<AppItemAttach, Long> {

    /**
     * 保存审批材料数据
     *
     * @param info
     *            审批材料
     */
    void saveAppItemAttach(AppItemAttach info);

    /**
     * 保存审批材料数据
     * 
     * @param attachList
     * @param rmItemApplyId
     */
    void saveAppItemAttach(List<AppItemAttach> attachList, Long rmItemApplyId);

    /**
     * 查询localData=0的附件信息
     *
     * @return
     */
    List<AppItemAttach> selectUnLocalAttach();

    /**
     * 保存附件信息
     * 
     * @param attach
     * @param localData
     *            是否本地数据
     * @param localFileName
     *            本地数据路径，若不为空则localData=本地数据
     */
    void saveAttachForLocal(AppItemAttach attach, LocalData localData, String localFileName);

    /**
     * 审批材料清单更新
     * 
     * @param item
     */
    void updatePrjMaterial(SysDataChangeApp item);

}
