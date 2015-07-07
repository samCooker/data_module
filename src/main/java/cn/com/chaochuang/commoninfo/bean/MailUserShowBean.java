/*
 * FileName:    MailUserShowBean.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年7月3日 (LJX) 1.0 Create
 */

package cn.com.chaochuang.commoninfo.bean;

/**
 * @author LJX
 *
 */
public class MailUserShowBean {
    private Long   id;
    /** 用户名 */
    private String mailUserName;
    /** 域名 */
    private String domain;
    /** 帐号 */
    private String account;
    /** 组织名称 */
    private String orgName;

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
     * @return the mailUserName
     */
    public String getMailUserName() {
        return mailUserName;
    }

    /**
     * @param mailUserName
     *            the mailUserName to set
     */
    public void setMailUserName(String mailUserName) {
        this.mailUserName = mailUserName;
    }

    /**
     * @return the domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * @param domain
     *            the domain to set
     */
    public void setDomain(String domain) {
        this.domain = domain;
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
     * @return the orgName
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * @param orgName
     *            the orgName to set
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

}
