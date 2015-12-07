/*
 * FileName:    EmAffix.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年11月26日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.emergency.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;

import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.aipcase.reference.LocalDataConverter;
import cn.com.chaochuang.common.data.domain.LongIdEntity;

/**
 * @author LLM
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "affix_id")) })
public class EmAffix extends LongIdEntity {
    /** 文件编号 */
    private Long      fileId;
    /** 文件保存名 */
    private String    saveName;
    /** 文件大小 */
    private Long      fileSize;
    /** 真实文件名 */
    private String    trueName;
    /** 文件路径 */
    private String    savePath;
    /** 是否图片 */
    private String    isImage;
    /** 原系统附件编号 */
    private String    rmAffixId;
    /** 本地已有数据 */
    @Convert(converter = LocalDataConverter.class)
    private LocalData localData = LocalData.非本地数据;

    /**
     * @return the fileId
     */
    public Long getFileId() {
        return fileId;
    }

    /**
     * @param fileId
     *            the fileId to set
     */
    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    /**
     * @return the saveName
     */
    public String getSaveName() {
        return saveName;
    }

    /**
     * @param saveName
     *            the saveName to set
     */
    public void setSaveName(String saveName) {
        this.saveName = saveName;
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
     * @return the savePath
     */
    public String getSavePath() {
        return savePath;
    }

    /**
     * @param savePath
     *            the savePath to set
     */
    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    /**
     * @return the isImage
     */
    public String getIsImage() {
        return isImage;
    }

    /**
     * @param isImage
     *            the isImage to set
     */
    public void setIsImage(String isImage) {
        this.isImage = isImage;
    }

    /**
     * @return the rmAffixId
     */
    public String getRmAffixId() {
        return rmAffixId;
    }

    /**
     * @param rmAffixId
     *            the rmAffixId to set
     */
    public void setRmAffixId(String rmAffixId) {
        this.rmAffixId = rmAffixId;
    }

    /**
     * @return the localData
     */
    public LocalData getLocalData() {
        return localData;
    }

    /**
     * @param localData
     *            the localData to set
     */
    public void setLocalData(LocalData localData) {
        this.localData = localData;
    }

}
