/*
 * FileName:    AipFlowInst.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2016
 * History:     2016年1月28日 (LLM) 1.0 Create
 */

package dbdata.aipcase.domain;

import cn.com.chaochuang.common.data.domain.LongIdEntity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * @author LLM
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "inst_id")) })
public class AipFlowInst extends LongIdEntity {
    /** 文书模版编号 */
    private Long noteModuleId;
    /** 文书模版名称 */
    private String noteName;
    /** 案件基础编号 */
    private Long caseApplyId;
    /** 文书内容编号 */
    private Long contentId;
    /** 创建时间 */
    private Date createDate;
    /** 创建人编号 */
    private Long createrId;
    /** 创建单位编号 */
    private Long unitOrgId;

    /**
     * 构造函数
     */
    public AipFlowInst() {
    }

    /**
     * @return the noteModuleId
     */
    public Long getNoteModuleId() {
        return noteModuleId;
    }

    /**
     * @param noteModuleId
     *            the noteModuleId to set
     */
    public void setNoteModuleId(Long noteModuleId) {
        this.noteModuleId = noteModuleId;
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
     * @return the contentId
     */
    public Long getContentId() {
        return contentId;
    }

    /**
     * @param contentId
     *            the contentId to set
     */
    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    /**
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     *            the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the createrId
     */
    public Long getCreaterId() {
        return createrId;
    }

    /**
     * @param createrId
     *            the createrId to set
     */
    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    /**
     * @return the unitOrgId
     */
    public Long getUnitOrgId() {
        return unitOrgId;
    }

    /**
     * @param unitOrgId
     *            the unitOrgId to set
     */
    public void setUnitOrgId(Long unitOrgId) {
        this.unitOrgId = unitOrgId;
    }

}
