/*
 * FileName:    DepLinkmanService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月26日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.commoninfo.service;

import java.util.List;

import cn.com.chaochuang.common.data.service.CrudRestService;
import cn.com.chaochuang.commoninfo.domain.PubInfo;
import cn.com.chaochuang.task.bean.PubInfoBean;

/**
 * @author LLM
 *
 */
public interface PubInfoService extends CrudRestService<PubInfo, Long> {

    /**
     * 获取最大的数据导入时间
     *
     * @return
     */
    String selectMaxInputDate();

    void savePubInfoDatas(List<PubInfoBean> datas) throws Exception;
}
