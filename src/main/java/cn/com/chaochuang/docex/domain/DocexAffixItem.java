/*
 * FileName:    DocexAffixItem.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2016
 * History:     2016年4月26日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.docex.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;

import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.common.data.domain.LongIdEntity;

/**
 * @author LLM
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "file_id")) })
public class DocexAffixItem extends LongIdEntity {
    /** 原系统流水号 */
    private Long      rmFileId;
    /** 附件组标识 */
    private String    docAffixId;
    /** 保存名 */
    private String    saveName;
    /** 文件大小 */
    private Long      fileSize;
    /** 附件真实名 */
    private String    trueName;
    /** 附件保存路径 */
    private String    savePath;
    /** 是否图片 */
    private String    isImage;
    /** 是否本地数据 */
    private LocalData localData;

    /**
     * @return the rmFileId
     */
    public Long getRmFileId() {
        return rmFileId;
    }

    /**
     * @param rmFileId
     *            the rmFileId to set
     */
    public void setRmFileId(Long rmFileId) {
        this.rmFileId = rmFileId;
    }

    /**
     * @return the docAffixId
     */
    public String getDocAffixId() {
        return docAffixId;
    }

    /**
     * @param docAffixId
     *            the docAffixId to set
     */
    public void setDocAffixId(String docAffixId) {
        this.docAffixId = docAffixId;
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
