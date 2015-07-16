/*
 * FileName:    PubInfoRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月20日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.commoninfo.repository;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.commoninfo.domain.MailUser;

/**
 * @author LLM
 *
 */
public interface MailUserRepository extends SimpleDomainRepository<MailUser, Long> {

    /**
     * 通过绑定用户编号获取邮箱用户
     *
     * @param userId
     * @return
     */
    MailUser findByBindUserId(Long userId);

    /**
     * 通过用户帐号查询用户信息
     * 
     * @param account
     * @return
     */
    MailUser findByAccount(String account);

}