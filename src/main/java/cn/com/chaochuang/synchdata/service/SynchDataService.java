/*
 * FileName:    SynchDataService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年8月14日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.synchdata.service;

import cn.com.chaochuang.synchdata.domain.SysSynchdataTask;

/**
 * @author LLM
 *
 */
public interface SynchDataService {
    /**
     * 同步行政相对人库的信息
     * 
     * @param task
     */
    void synchAppEntpData(SysSynchdataTask task);
}
