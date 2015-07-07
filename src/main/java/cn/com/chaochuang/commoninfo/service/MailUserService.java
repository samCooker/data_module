/*
 * FileName:    DepLinkmanService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月26日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.commoninfo.service;

import cn.com.chaochuang.common.data.service.CrudRestService;
import cn.com.chaochuang.commoninfo.domain.MailUser;

/**
 * @author LLM
 *
 */
public interface MailUserService extends CrudRestService<MailUser, Long> {

    /**
     * 通过绑定ID查找邮箱用户
     *
     * @param bindUserId
     * @return
     */
    MailUser selectUserByBindId(Long bindUserId);

    void saveUser(MailUser info);
}
