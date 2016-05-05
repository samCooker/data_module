/*
 * FileName:    SimpleUserDetailsService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2014
 * History:     2014年7月22日 (guig) 1.0 Create
 */

package cn.com.chaochuang.common.user.service;

import cn.com.chaochuang.common.security.UserInfo;
import cn.com.chaochuang.common.user.bean.SimpleCurrentUserInfo;
import cn.com.chaochuang.common.user.domain.SysUser;
import cn.com.chaochuang.common.user.repository.SysManageUserRepository;
import cn.com.chaochuang.common.user.repository.SysUserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author guig
 *
 */
@Service("SimpleUserDetailsService")
public class SimpleUserDetailsService implements UserDetailsService {

    @Value("${security.default_admin_account}")
    private String                  defaultAdminUserAccount;

    @Value("${security.default_admin_password}")
    private String                  defaultAdminUserPassword;

    @Autowired
    private SysUserRepository       userRepository;
    @Autowired
    private SysManageUserRepository manageUserRepository;

    private boolean doForAddFirstUser() {
        if (0 == userRepository.count()) {
            if (StringUtils.isNotBlank(defaultAdminUserAccount) && StringUtils.isNotBlank(defaultAdminUserPassword)) {
                SysUser adminUser = new SysUser();
                adminUser.setAccount(defaultAdminUserAccount);
                adminUser.setPassword(defaultAdminUserPassword);
                adminUser.setUserName(defaultAdminUserAccount);

                userRepository.save(adminUser);

                return true;
            }
        }
        return false;
    }

    private SysUser doLoadUser(String username) {
        return userRepository.findByAccountAndValid(username, SysUser.VALID);
    }

    /**
     * (non-Javadoc)
     *
     * @see UserDetailsService#loadUserByUsername(String)
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = doLoadUser(username);
        if (null == user) {
            if (doForAddFirstUser()) {
                user = doLoadUser(username);
            }
        }
        if (null != user && null != user.getId()) {
            if (this.manageUserRepository.findByUserId(user.getId()) == null) {
                throw new UsernameNotFoundException("没有权限！");
            }
            Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
            return new UserInfo(new SimpleCurrentUserInfo(user), user.getPassword(), auths);
        } else {
            throw new UsernameNotFoundException("没有用户[" + username + "]的数据！");
        }
    }

    /**
     * @return the defaultAdminUserAccount
     */
    public String getDefaultAdminUserAccount() {
        return defaultAdminUserAccount;
    }

    /**
     * @return the defaultAdminUserPassword
     */
    public String getDefaultAdminUserPassword() {
        return defaultAdminUserPassword;
    }

}
