/*
 * FileName:    EmSiteReportService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年12月3日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.emergency.service;

import cn.com.chaochuang.common.data.service.CrudRestService;
import cn.com.chaochuang.datacenter.domain.SysDataChange;
import cn.com.chaochuang.emergency.domain.EmSiteReport;

/**
 * @author LLM
 *
 */
public interface EmSiteReportService extends CrudRestService<EmSiteReport, Long> {

    /**
     * 保存现场情况报告记录
     * 
     * @param dataChange
     */
    void saveEmSiteReport(SysDataChange dataChange);
}
