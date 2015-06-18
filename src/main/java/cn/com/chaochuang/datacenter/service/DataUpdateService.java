/*
 * FileName:    DataUpdateService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月27日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.datacenter.service;

import java.util.List;

import cn.com.chaochuang.common.data.service.CrudRestService;
import cn.com.chaochuang.datacenter.bean.DocFileUpdate;
import cn.com.chaochuang.datacenter.domain.DataUpdate;
import cn.com.chaochuang.datacenter.reference.WorkType;

/**
 * @author LLM
 *
 */
public interface DataUpdateService extends CrudRestService<DataUpdate, Long> {

    /**
     * 获取公文办理提交处理数据
     *
     * @return
     */
    List<DataUpdate> selectDocFileDataUpdate(WorkType workType);

    /**
     * 更新公文提交数据
     *
     * @param updateInfo
     */
    void docFileDataUpdate(DocFileUpdate updateInfo);
}
