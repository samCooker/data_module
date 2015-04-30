/*
 * FileName:    AipCaseAttach.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年4月30日 (LJX) 1.0 Create
 */

package cn.com.chaochuang.aipcase.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;

import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.aipcase.reference.LocalDataConverter;
import cn.com.chaochuang.common.data.domain.LongIdEntity;

/**
 * @author LJX
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "attach_id")) })
public class AipCaseAttach extends LongIdEntity {

    private static final long serialVersionUID = -573065209439125672L;

    /** 附件保存名 */
    private String            saveName;
    /** 附件原名 */
    private String            trueName;
    /** 附件大小 */
    private Long              fileSize;
    /** 是否图文公告 */
    private String            isImage;
    /** 保存路径 */
    private String            savePath;
    /** 案件流水号 */
    private Long              caseApplyId;
    /** 原系统附件编号 */
    private Long              rmAttachId;
    /** 本地已有数据 */
    @Convert(converter = LocalDataConverter.class)
    private LocalData         localData;
    /** 所属案件状态 */
    private String            caseStatus;

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
     * @return the caseApplyId
     */
    public Long getCaseApplyId() {
        return caseApplyId;
    }

    /**
     * @param caseApplyId
     *            the caseApplyId to set
     */
    public void setCaseApplyId(Long caseApplyId) {
        this.caseApplyId = caseApplyId;
    }

    /**
     * @return the rmAttachId
     */
    public Long getRmAttachId() {
        return rmAttachId;
    }

    /**
     * @param rmAttachId
     *            the rmAttachId to set
     */
    public void setRmAttachId(Long rmAttachId) {
        this.rmAttachId = rmAttachId;
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

    /**
     * @return the caseStatus
     */
    public String getCaseStatus() {
        return caseStatus;
    }

    /**
     * @param caseStatus
     *            the caseStatus to set
     */
    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

}
