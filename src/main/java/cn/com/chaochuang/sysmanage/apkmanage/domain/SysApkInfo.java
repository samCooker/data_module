/*
 * FileName:    SysApkInfo.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年7月19日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.sysmanage.apkmanage.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.com.chaochuang.common.data.domain.LongIdEntity;
import cn.com.chaochuang.sysmanage.apkmanage.reference.ApkDataStatus;
import cn.com.chaochuang.sysmanage.apkmanage.reference.ApkDataStatusConverter;
import cn.com.chaochuang.sysmanage.apkmanage.reference.UpdateFlag;
import cn.com.chaochuang.sysmanage.apkmanage.reference.UpdateFlagConverter;

/**
 * @author LLM
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "apk_info_id")) })
public class SysApkInfo extends LongIdEntity {
    /** 安装包包名 */
    private String        packageName;
    /** 安装包签名信息 */
    private String        signInfo;
    /** 安装包存放路径 */
    private String        filePath;
    /** 安装包真实文件名 */
    private String        trueName;
    /** 安装包MD5校验码 */
    private String        mdfCode;
    /** 安装包文件大小 */
    private Long          fileSize;
    /** 上传人编号 */
    private Long          uploadId;
    /** 上传人姓名 */
    private String        uploadName;
    /** 安装包版本号 */
    private String        version;
    /** 变更标志 */
    @Convert(converter = UpdateFlagConverter.class)
    private UpdateFlag    updateFlag;
    /** 上传时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date          uploadTime;
    /** 安装包描述 */
    private String        packageScript;
    /** 数据状态 */
    @Convert(converter = ApkDataStatusConverter.class)
    private ApkDataStatus status;

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
     * @return the signInfo
     */
    public String getSignInfo() {
        return signInfo;
    }

    /**
     * @param signInfo
     *            the signInfo to set
     */
    public void setSignInfo(String signInfo) {
        this.signInfo = signInfo;
    }

    /**
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath
     *            the filePath to set
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * @return the mdfCode
     */
    public String getMdfCode() {
        return mdfCode;
    }

    /**
     * @param mdfCode
     *            the mdfCode to set
     */
    public void setMdfCode(String mdfCode) {
        this.mdfCode = mdfCode;
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
     * @return the uploadId
     */
    public Long getUploadId() {
        return uploadId;
    }

    /**
     * @param uploadId
     *            the uploadId to set
     */
    public void setUploadId(Long uploadId) {
        this.uploadId = uploadId;
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
     * @return the trueName
     */
    public String getTrueName() {
        return trueName;
    }

    /**
     * @param trueName
     *            the trueName to set
     */
    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    /**
     * @return the status
     */
    public ApkDataStatus getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(ApkDataStatus status) {
        this.status = status;
    }

}
