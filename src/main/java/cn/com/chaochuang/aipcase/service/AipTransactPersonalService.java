/*
 * FileName:    AipPunishEntpService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年8月11日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.aipcase.service;

import cn.com.chaochuang.aipcase.domain.AipTransactPersonal;
import cn.com.chaochuang.aipcase.domain.FdFordoAipcase;
import cn.com.chaochuang.common.data.service.CrudRestService;

/**
 * @author LLM
 *
 */
public interface AipTransactPersonalService extends CrudRestService<AipTransactPersonal, Long> {

    /**
     * 保存经办记录，经办人id和案件id联合唯一
     * 
     * @param fdFordo
     */
    void saveTransactPersonalRecord(FdFordoAipcase fdFordo);

}
