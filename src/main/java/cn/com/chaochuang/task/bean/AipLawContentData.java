/*
 * FileName:    AipLawContentPrintInfo.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年7月21日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.task.bean;

/**
 * @author Shicx
 *
 */
public class AipLawContentData {

    /** 远程生成的文件路径 */
    private String rmFilePath;
    /** 文书id */
    private Long   rmNoteFileId;
    /** 文书模版id */
    private Long   moduleId;
    /** 案件来源id */
    private Long   rmCaseApplyId;
    /** 文书名称 */
    private String noteName;
    /** 对应的案件状态 */
    private String caseStatus;
    /** 文件MD5码 */
    private String mdfCode;

    /**
     * @return the moduleId
     */
    public Long getModuleId() {
        return moduleId;
    }

    /**
     * @param moduleId
     *            the moduleId to set
     */
    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    /**
     * @return the noteName
     */
    public String getNoteName() {
        return noteName;
    }

    /**
     * @param noteName
     *            the noteName to set
     */
    public void setNoteName(String noteName) {
        this.noteName = noteName;
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
     * @return the rmNoteFileId
     */
    public Long getRmNoteFileId() {
        return rmNoteFileId;
    }

    /**
     * @param rmNoteFileId
     *            the rmNoteFileId to set
     */
    public void setRmNoteFileId(Long rmNoteFileId) {
        this.rmNoteFileId = rmNoteFileId;
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
     * @return the rmFilePath
     */
    public String getRmFilePath() {
        return rmFilePath;
    }

    /**
     * @param rmFilePath
     *            the rmFilePath to set
     */
    public void setRmFilePath(String rmFilePath) {
        this.rmFilePath = rmFilePath;
    }

}
