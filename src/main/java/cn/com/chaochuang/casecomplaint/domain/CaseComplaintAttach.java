/*
 * FileName:    SysDataChange.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年09月10日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.casecomplaint.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;

import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.aipcase.reference.LocalDataConverter;
import cn.com.chaochuang.common.data.domain.LongIdEntity;

/**
 * @author Shicx
 *
 */
@SuppressWarnings("serial")
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "ATTACH_ID")) })
public class CaseComplaintAttach extends LongIdEntity {
    /** 远程附件id，对应远程的fileId */
    private Long      rmAttachId;
    /** 附件保存名 */
    private String    saveName;
    /** 附件真实名称 */
    private String    trueName;
    /** 附件大小 */
    private Long      fileSize;
    /** 是否是图片 */
    private String    isImage;
    /** 保存路径 */
    private String    savePath;
    /** 原附件标识 */
    private String    rmAffixId;
    /** 是否是pdf */
    private String    pdfFlag;
    /** 是否有本地数据 */
    @Convert(converter = LocalDataConverter.class)
    private LocalData localData;

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public String getSaveName() {
        return saveName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setIsImage(String isImage) {
        this.isImage = isImage;
    }

    public String getIsImage() {
        return isImage;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setRmAffixId(String rmAffixId) {
        this.rmAffixId = rmAffixId;
    }

    public String getRmAffixId() {
        return rmAffixId;
    }

    public void setPdfFlag(String pdfFlag) {
        this.pdfFlag = pdfFlag;
    }

    public String getPdfFlag() {
        return pdfFlag;
    }

    public void setRmAttachId(Long rmAttachId) {
        this.rmAttachId = rmAttachId;
    }

    public Long getRmAttachId() {
        return rmAttachId;
    }

    public void setLocalData(LocalData localData) {
        this.localData = localData;
    }

    public LocalData getLocalData() {
        return localData;
    }

}
