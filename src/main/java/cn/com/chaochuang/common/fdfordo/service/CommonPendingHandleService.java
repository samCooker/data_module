/*
 * FileName:    CommonFordoService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年6月25日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.common.fdfordo.service;

import cn.com.chaochuang.datacenter.domain.SysDataChange;

/**
 * @author Shicx
 *
 */
public interface CommonPendingHandleService {

    /**
     * 分析系统数据的处理
     *
     * @param dataChange
     */
    void analysisDataChange(SysDataChange dataChange);
}
