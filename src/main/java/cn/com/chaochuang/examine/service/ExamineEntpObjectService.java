/*
 * FileName:    ExamineEntpObjectService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年11月18日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.examine.service;

import cn.com.chaochuang.common.data.service.CrudRestService;
import cn.com.chaochuang.datacenter.domain.SysDataChange;
import cn.com.chaochuang.examine.domain.ExamineEntpObject;

/**
 * @author LLM
 *
 */
public interface ExamineEntpObjectService extends CrudRestService<ExamineEntpObject, Long> {

    /**
     * 更新日常监管记录
     * 
     * @param dataChange
     */
    void saveExamineEntpObject(SysDataChange dataChange);
}
