/*
 * FileName:    AipCaseNoteFileInfo.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年7月21日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.aipcase.bean;

/**
 * @author Shicx
 *
 */
public class AipCaseNoteFileInfo {

    /** 案件来源id */
    private Long   caseApplyId;
    /** 文书名称 */
    private String noteName;
    /** 对应的案件状态 */
    private String caseStatus;

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

}
