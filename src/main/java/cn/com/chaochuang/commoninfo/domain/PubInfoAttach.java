/*
 * FileName:    PubInfoAttach.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月19日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.commoninfo.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;

import cn.com.chaochuang.common.data.domain.LongIdEntity;
import cn.com.chaochuang.docwork.reference.IsLocalData;

/**
 * @author LLM
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "attach_id")) })
public class PubInfoAttach extends LongIdEntity {
    /** 附件保存名 */
    private String      saveName;
    /** 文件原名 */
    private String      trueName;
    /** 文件大小 */
    private Long        fileSize;
    /** 是否是图片 */
    private String      isImage;
    /** 文件路径 */
    private String      savePath;
    /** 公共信息表编号 */
    private Long        pubInfoId;
    /** 是否本地数据 */
    private IsLocalData localData;

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
     * @return the pubInfoId
     */
    public Long getPubInfoId() {
        return pubInfoId;
    }

    /**
     * @param pubInfoId
     *            the pubInfoId to set
     */
    public void setPubInfoId(Long pubInfoId) {
        this.pubInfoId = pubInfoId;
    }

    /**
     * @return the localData
     */
    public IsLocalData getLocalData() {
        return localData;
    }

    /**
     * @param localData
     *            the localData to set
     */
    public void setLocalData(IsLocalData localData) {
        this.localData = localData;
    }

}
