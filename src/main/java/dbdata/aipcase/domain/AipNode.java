/*
 * FileName:    AipNode.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2016
 * History:     2016年1月28日 (LLM) 1.0 Create
 */

package dbdata.aipcase.domain;

import cn.com.chaochuang.common.data.domain.LongIdEntity;

import javax.persistence.*;

/**
 * @author LLM
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "node_id")) })
public class AipNode extends LongIdEntity {
    /** 环节名称 */
    private String nodeName;
    /** 文书模版编号 */
    private Long noteModuleId;
    /** 环节级别 */
    private String  nodeLevel;
    /** 文书模版名称 */
    private String noteName;
    /** 是否最后环节 */
    private String isLast;
    /** 是否首环节 */
    private String isFirst;

    /**
     * @return the nodeName
     */
    public String getNodeName() {
        return nodeName;
    }

    /**
     * @param nodeName
     *            the nodeName to set
     */
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
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
     * @return the nodeLevel
     */
    public String getNodeLevel() {
        return nodeLevel;
    }

    /**
     * @param nodeLevel the nodeLevel to set
     */
    public void setNodeLevel(String nodeLevel) {
        this.nodeLevel = nodeLevel;
    }

    /**
     * @return the isLast
     */
    public String getIsLast() {
        return isLast;
    }

    /**
     * @param isLast the isLast to set
     */
    public void setIsLast(String isLast) {
        this.isLast = isLast;
    }

    /**
     * @return the isFirst
     */
    public String getIsFirst() {
        return isFirst;
    }

    /**
     * @param isFirst the isFirst to set
     */
    public void setIsFirst(String isFirst) {
        this.isFirst = isFirst;
    }
}
