/*
 * FileName:    FlowNodeInfo.java
 * Description:
 * Company:     ����������Ϣ�������޹�˾
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015��3��28�� (Shicx) 1.0 Create
 */
package cn.com.chaochuang.task.bean;

/**
 * @author Shicx
 *
 */
public class DocFileAttachInfo {
    /** 附件保存名 */
    private String saveName;
    /** 附件大小 */
    private Long   fileSize;
    /** 附件原名 */
    private String trueName;
    /** 附件保存路径 */
    private String savePath;
    /** 是否为图片公告 */
    private String isImage;
    /** 原远程附件id */
    private String rmAttachId;
    /** 单独获取附件时传递的参数 */
    private String rmInstanceId;

    /**
     * @return the saveName
     */
    public String getSaveName() {
        return saveName;
    }

    /**
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DocFileAttachInfo [saveName=" + saveName + ", fileSize=" + fileSize + ", trueName=" + trueName
                        + ", savePath=" + savePath + ", isImage=" + isImage + ", rmAttachId=" + rmAttachId
                        + ", getSaveName()=" + getSaveName() + ", getFileSize()=" + getFileSize() + ", getTrueName()="
                        + getTrueName() + ", getSavePath()=" + getSavePath() + ", getIsImage()=" + getIsImage()
                        + ", getRmAttachId()=" + getRmAttachId() + ", getClass()=" + getClass() + ", hashCode()="
                        + hashCode() + ", toString()=" + super.toString() + "]";
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
     * @return the rmAttachId
     */
    public String getRmAttachId() {
        return rmAttachId;
    }

    /**
     * @param rmAttachId
     *            the rmAttachId to set
     */
    public void setRmAttachId(String rmAttachId) {
        this.rmAttachId = rmAttachId;
    }

    /**
     * @return the rmInstanceId
     */
    public String getRmInstanceId() {
        return rmInstanceId;
    }

    /**
     * @param rmInstanceId
     *            the rmInstanceId to set
     */
    public void setRmInstanceId(String rmInstanceId) {
        this.rmInstanceId = rmInstanceId;
    }

}
