/*
 * FileName:    EmSiteReportRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年12月3日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.emergency.repository;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.emergency.domain.EmSiteReport;

/**
 * @author LLM
 *
 */
public interface EmSiteReportRepository extends SimpleDomainRepository<EmSiteReport, Long> {

    /**
     * 根据原系统报告编号查询报告记录
     * 
     * @param rmReportId
     * @return
     */
    EmSiteReport findByRmReportId(Long rmReportId);
}
