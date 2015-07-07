/*
 * FileName:    UserShowBean.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年7月6日 (LJX) 1.0 Create
 */

package cn.com.chaochuang.common.user.bean;

import org.dozer.Mapping;

import cn.com.chaochuang.sysmanage.registerapply.reference.IsRegister;

/**
 * @author LJX
 *
 */
public class UserShowBean {
    private Long       id;
    /** 姓名 */
    private String     userName;
    /** 登录账号 */
    private String     account;
    /** 是否注册app */
    private IsRegister isRegister;
    @Mapping("department.depName")
    private String     depName;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     *            the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the account
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account
     *            the account to set
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * @return the isRegister
     */
    public IsRegister getIsRegister() {
        return isRegister;
    }

    /**
     * @param isRegister
     *            the isRegister to set
     */
    public void setIsRegister(IsRegister isRegister) {
        this.isRegister = isRegister;
    }

    /**
     * @return the depName
     */
    public String getDepName() {
        return depName;
    }

    /**
     * @param depName
     *            the depName to set
     */
    public void setDepName(String depName) {
        this.depName = depName;
    }

}
