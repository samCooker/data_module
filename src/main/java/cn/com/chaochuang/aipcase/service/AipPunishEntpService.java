/*
 * FileName:    AipPunishEntpService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年8月11日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.aipcase.service;

import java.util.List;

import cn.com.chaochuang.aipcase.domain.AipPunishEntp;
import cn.com.chaochuang.common.data.service.CrudRestService;
import cn.com.chaochuang.datacenter.domain.SysDataChange;
import cn.com.chaochuang.task.bean.AipPunishInfo;

/**
 * @author LLM
 *
 */
public interface AipPunishEntpService extends CrudRestService<AipPunishEntp, Long> {

    /**
     * 获取当前同步数据的时间和编号
     *
     * @return
     */
    AipPunishInfo selectMaxInputDate();

    /**
     * 保存处罚数据
     *
     * @param info
     */
    void savePunishInfo(AipPunishInfo info);

    /**
     * 保存处罚数据
     *
     * @param datas
     */
    void savePunishInfo(List<AipPunishInfo> datas);

    /**
     * 更新处罚数据
     *
     * @param item
     */
    void savePunishInfo(SysDataChange dataChange);
}
