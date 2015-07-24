/*
 * FileName:    AipCaseNoteFile.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年7月4日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.aipcase.domain;

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
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "case_note_file_id")) })
public class AipCaseNoteFile extends LongIdEntity {
    /**
     * 
     */
    private static final long serialVersionUID = -8168613326874740930L;
    /** 远程系统案件基本信息编号 */
    private Long              rmCaseApplyId;
    /** 文书名称 */
    private String            noteName;
    /** 远程系统文书文件编号 */
    private Long              rmNoteFileId;
    /** 文件路径 */
    private String            filePath;
    /** 是否本地数据 */
    private LocalData         localData;
    /** 案件状态 */
    private String            caseStatus;
    /** 文件MD5码 */
    private String            mdfCode;

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
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath
     *            the filePath to set
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
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

}
