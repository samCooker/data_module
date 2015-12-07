/*
 * FileName:    SysDataChangeService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年4月8日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.datacenter.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import cn.com.chaochuang.common.data.service.CrudRestService;
import cn.com.chaochuang.datacenter.domain.SysDataChange;

/**
 * @author LLM
 *
 */
public interface SysDataChangeService extends CrudRestService<SysDataChange, Long> {

    /**
     * 保存远程系统记录
     *
     * @param sysDataChanges
     */
    void saveSysDataChange(List<SysDataChange> sysDataChanges);

    /**
     * 获取OA待办变更记录
     * 
     * @param page
     * @return
     */
    List<SysDataChange> selectOAPendingItem(Pageable page);
}
