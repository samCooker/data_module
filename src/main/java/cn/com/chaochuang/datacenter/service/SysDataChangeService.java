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
import cn.com.chaochuang.datacenter.domain.SysDataChangeApp;
import cn.com.chaochuang.datacenter.domain.SysDataChangeOa;
import cn.com.chaochuang.datacenter.domain.SysDataChangeSystem;
import cn.com.chaochuang.datacenter.domain.SysDataChangeVoice;

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
    List<SysDataChangeOa> selectOAPendingItem(Pageable page);

    /**
     * 获取OA办结变更记录
     *
     * @param page
     * @return
     */
    List<SysDataChangeOa> selectOAHisnoItem(Pageable page);

    /**
     * 获取行政审批待办变更记录
     *
     * @param pageRequest
     * @return
     */
    List<SysDataChangeApp> selectSuperviseFordo(Pageable page);

    /**
     * 获取行政审批相关变更记录
     *
     * @param pageRequest
     * @return
     */
    List<SysDataChangeApp> selectAppItem(Pageable page);

    /**
     * 获取系统（用户、组织、通讯录）变更数据
     *
     * @param page
     * @return
     */
    List<SysDataChangeSystem> selectSystemItem(Pageable page);

    /**
     * 获取舆情相关变更记录
     *
     * @param pageRequest
     * @return
     */
    List<SysDataChangeVoice> selectVoiceItem(Pageable page);

    /**
     * 获取舆情信息变更记录
     *
     * @param pageRequest
     * @return
     */
    List<SysDataChangeVoice> selectVoiceInfoItem(Pageable page);

    /**
     * 删除更数据记录
     *
     * @param dataChange
     * @param errMsg
     */
    void deleteChangeData(SysDataChange dataChange, String errMsg);

    /**
     * 删除OA类变更数据记录
     *
     * @param dataChange
     * @param errMsg
     */
    void deleteOAChangeData(SysDataChangeOa dataChange, String errMsg);

    /**
     * 删除App类变更数据记录
     *
     * @param dataChange
     * @param errMsg
     */
    void deleteAppChangeData(SysDataChangeApp dataChange, String errMsg);

    /**
     * 删除System类变更数据记录
     *
     * @param dataChange
     * @param errMsg
     */
    void deleteSystemChangeData(SysDataChangeSystem dataChange, String errMsg);

    /**
     * 删除Voice类变更数据记录
     *
     * @param dataChange
     * @param errMsg
     */
    void deleteVoiceChangeData(SysDataChangeVoice dataChange, String errMsg);

    /**
     * 分页查询记录并按id排序
     *
     * @param page
     * @return
     */
    List<SysDataChange> findByPageOrderById(Pageable page);

}
