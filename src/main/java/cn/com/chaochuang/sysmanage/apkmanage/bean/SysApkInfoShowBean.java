/*
 * FileName:    SysApkInfoShowBean.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年7月20日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.sysmanage.apkmanage.bean;

import java.util.Date;

import javax.persistence.Convert;

import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.sysmanage.apkmanage.reference.UpdateFlag;
import cn.com.chaochuang.sysmanage.apkmanage.reference.UpdateFlagConverter;

/**
 * @author LLM
 *
 */
public class SysApkInfoShowBean {
    /** 安装包包名 */
    private String     packageName;
    /** 安装包文件大小 */
    private Long       fileSize;
    /** 格式化安装包文件大小 */
    private String     fileSizeFormat;
    /** 安装包版本号 */
    private String     version;
    /** 变更标志 */
    @Convert(converter = UpdateFlagConverter.class)
    private UpdateFlag updateFlag;
    /** 上传时间 */
    private Date       uploadTime;
    /** 格式化上传时间 */
    private String     uploadTimeFormat;
    /** 上传人姓名 */
    private String     uploadName;
    /** 安装包描述 */
    private String     packageScript;
    /** 上传记录编号 */
    private String     attach;

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
     * @return the fileSize
     */
    public Long getFileSize() {
        return fileSize;
    }

    /**
     * @param fileSize
     *            the fileSize to set
     */
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
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
     * @return the updateFlag
     */
    public UpdateFlag getUpdateFlag() {
        return updateFlag;
    }

    /**
     * @param updateFlag
     *            the updateFlag to set
     */
    public void setUpdateFlag(UpdateFlag updateFlag) {
        this.updateFlag = updateFlag;
    }

    /**
     * @return the uploadTime
     */
    public Date getUploadTime() {
        return uploadTime;
    }

    /**
     * @param uploadTime
     *            the uploadTime to set
     */
    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    /**
     * @return the uploadName
     */
    public String getUploadName() {
        return uploadName;
    }

    /**
     * @param uploadName
     *            the uploadName to set
     */
    public void setUploadName(String uploadName) {
        this.uploadName = uploadName;
    }

    /**
     * @return the packageScript
     */
    public String getPackageScript() {
        return packageScript;
    }

    /**
     * @param packageScript
     *            the packageScript to set
     */
    public void setPackageScript(String packageScript) {
        this.packageScript = packageScript;
    }

    /**
     * @return the uploadTimeFormat
     */
    public String getUploadTimeFormat() {
        return (uploadTime != null) ? Tools.DATE_MINUTE_FORMAT.format(uploadTime) : "";
    }

    /**
     * @param uploadTimeFormat
     *            the uploadTimeFormat to set
     */
    public void setUploadTimeFormat(String uploadTimeFormat) {
        this.uploadTimeFormat = uploadTimeFormat;
    }

    /**
     * @return the attach
     */
    public String getAttach() {
        return attach;
    }

    /**
     * @param attach
     *            the attach to set
     */
    public void setAttach(String attach) {
        this.attach = attach;
    }

    /**
     * @return the fileSizeFormat
     */
    public String getFileSizeFormat() {
        return Tools.convertFileSize(this.fileSize);
    }

    /**
     * @param fileSizeFormat
     *            the fileSizeFormat to set
     */
    public void setFileSizeFormat(String fileSizeFormat) {
        this.fileSizeFormat = fileSizeFormat;
    }

}
