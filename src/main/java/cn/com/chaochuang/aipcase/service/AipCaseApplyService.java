/*
 * FileName:    DocFileService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月30日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.aipcase.service;

import java.util.List;

import cn.com.chaochuang.aipcase.bean.AipCaseShowData;
import cn.com.chaochuang.aipcase.domain.AipCaseApply;
import cn.com.chaochuang.common.data.service.CrudRestService;
import cn.com.chaochuang.datacenter.domain.DataUpdate;
import cn.com.chaochuang.task.bean.AipCaseSubmitInfo;

/**
 * @author LJX
 *
 */
public interface AipCaseApplyService extends CrudRestService<AipCaseApply, Long> {

    /**
     * 获取案件办理表中最新记录导入时间
     *
     * @return
     */
    String selectAipCaseMaxInputDate();

    /**
     * 保存案件办理记录
     *
     * @param datas
     */
    void saveAipCaseApply(List<AipCaseShowData> datas);

    /**
     * @param dataUpdate
     * @param backInfo
     * @param nodeInfo
     */
    void deleteDataUpdateAndFordo(DataUpdate dataUpdate, String backInfo, AipCaseSubmitInfo nodeInfo);
}
