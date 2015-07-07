/*
 * FileName:    MailOrg.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年6月12日 (LJX) 1.0 Create
 */

package cn.com.chaochuang.commoninfo.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Transient;

import cn.com.chaochuang.common.data.domain.LongIdEntity;
import cn.com.chaochuang.common.util.RSAUtils;
import cn.com.chaochuang.common.util.Tools;

/**
 * @author LJX
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "mail_user_id")) })
public class MailUser extends LongIdEntity {
    private static final long serialVersionUID = 7370139331284123591L;
    /** 用户名 */
    private String            mailUserName;
    /** 域名 */
    private String            domain;
    /** 帐号 */
    private String            account;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "password", columnDefinition = "BLOB")
    /** 密码 */
    private byte[]            password;
    /** 组织代码 */
    private String            orgCode;
    /** 组织名称 */
    private String            orgName;
    /** 状态 */
    private String            valid;
    /** 绑定用户编码 */
    private Long              bindUserId;
    /** 收件箱附件路径 */
    @Transient
    private String            inboxAttachPath;

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
     * @return the orgCode
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * @param orgCode
     *            the orgCode to set
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    /**
     * @return the valid
     */
    public String getValid() {
        return valid;
    }

    /**
     * @param valid
     *            the valid to set
     */
    public void setValid(String valid) {
        this.valid = valid;
    }

    /**
     * @return the bindUserId
     */
    public Long getBindUserId() {
        return bindUserId;
    }

    /**
     * @param bindUserId
     *            the bindUserId to set
     */
    public void setBindUserId(Long bindUserId) {
        this.bindUserId = bindUserId;
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

    /**
     * @return the inboxAttachPath
     */
    public String getInboxAttachPath() {
        return inboxAttachPath;
    }

    /**
     * @param inboxAttachPath
     *            the inboxAttachPath to set
     */
    public void setInboxAttachPath(String inboxAttachPath) {
        this.inboxAttachPath = inboxAttachPath;
    }

    /**
     * @return the password
     */
    public byte[] getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(byte[] password) {
        this.password = password;
    }

    /**
     * 获取用户的邮箱地址
     *
     * @return
     */
    public String getMailAddr() {
        if (Tools.isEmptyString(this.account) || Tools.isEmptyString(this.domain)) {
            return "";
        }
        return this.account + "@" + this.domain;
    }

    /**
     * 获取密码的明文
     *
     * @return
     */
    public String getDecryptPass() {
        if (this.password == null || this.password.length == 0) {
            return "";
        }
        try {
            byte[] pass = RSAUtils.decrypt(RSAUtils.getRSAPrivateKey(), this.password);
            if (pass == null || pass.length == 0) {
                return "";
            }
            return new String(pass);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
}
