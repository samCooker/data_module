/*
 * FileName:    AipCaseAttachInfo.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年7月7日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.aipcase.bean;

/**
 * @author LLM
 *
 */
public class AipCaseAttachInfo {
    /** 原系统附件编号 */
    private Long   rmAttachId;
    /** 原系统案件基础编号 */
    private Long   rmCaseApplyId;
    /** 所属案件状态 */
    private String caseStatus;
    /** 附件保存名 */
    private String saveName;
    /** 附件原名 */
    private String trueName;
    /** 附件大小 */
    private Long   fileSize;
    /** 是否图文公告 */
    private String isImage;
    /** 保存路径 */
    private String savePath;

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
     * @return the rmCaseApplyId
     */
    public Long getRmCaseApplyId() {
        return rmCaseApplyId;
    }

    /**
     * @param rmCaseApplyId
     *            the rmCaseApplyId to set
     */
    public void setRmCaseApplyId(Long rmCaseApplyId) {
        this.rmCaseApplyId = rmCaseApplyId;
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

}
