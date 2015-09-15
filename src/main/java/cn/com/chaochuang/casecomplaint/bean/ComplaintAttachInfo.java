/*
 * FileName:    ComplaintAttachInfo.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月13日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.casecomplaint.bean;

/**
 * @author Shicx
 *
 */
public class ComplaintAttachInfo {

    /** 远程附件id */
    private Long   fileId;
    /** 附件保存名 */
    private String saveName;
    /** 附件真实名称 */
    private String trueName;
    /** 附件大小 */
    private Long   fileSize;
    /** 是否是图片 */
    private String isImage;
    /** 保存路径 */
    private String savePath;
    /** 附件标识 ！ */
    private String affixId;
    /** 是否是pdf */
    private String pdfFlag;

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
     * @return the affixId
     */
    public String getAffixId() {
        return affixId;
    }

    /**
     * @param affixId
     *            the affixId to set
     */
    public void setAffixId(String affixId) {
        this.affixId = affixId;
    }

    /**
     * @return the pdfFlag
     */
    public String getPdfFlag() {
        return pdfFlag;
    }

    /**
     * @param pdfFlag
     *            the pdfFlag to set
     */
    public void setPdfFlag(String pdfFlag) {
        this.pdfFlag = pdfFlag;
    }

}
