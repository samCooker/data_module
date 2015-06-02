/*
 * FileName:    AppItemAttachService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年6月2日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.appflow.service;

import cn.com.chaochuang.appflow.domain.AppItemAttach;
import cn.com.chaochuang.common.data.service.CrudRestService;

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
}
