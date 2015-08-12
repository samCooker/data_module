/*
 * FileName:    SysApkInfoCheckShow.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年7月21日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.sysmanage.apkmanage.bean;

/**
 * @author LLM
 *
 */
public class SysApkInfoCheckShow {
    /** 安装包包名 */
    private String packageName;
    /** 安装包版本号 */
    private String version;
    /** 校验错误信息 */
    private String errorMessage = "";

    /**
     * @return the packageName
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * @param packageName
     *            the packageName to set
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version
     *            the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage
     *            the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
