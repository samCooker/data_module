/*
 * FileName:    FloNodeOpinions.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年4月1日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.docwork.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.com.chaochuang.common.data.domain.LongIdEntity;

/**
 * @author Shicx
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "node_opinions_id")) })
public class FlowNodeOpinions extends LongIdEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /** 公文id */
    private Long              docId;
    /** 环节标识 ? */
    private String            nodeFlag;
    /** 办理人姓名 */
    private String            transactName;
    /** 办理人id */
    private Long              transactId;
    /** 办理部门 */
    private String            transactDeptName;
    /** 办理部门id */
    private Long              transactDeptId;
    /** 办理意见 */
    private String            opinions;
    /** 办理时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date              transactTime;
    /** 原实例编号 */
    private String            rmInstanceId;
    /** 原系统流程环节实例编号 */
    private Long              rmInstnoId;

    /**
     * @return the nodeFlag
     */
    public String getNodeFlag() {
        return nodeFlag;
    }

    /**
     * @param nodeFlag
     *            the nodeFlag to set
     */
    public void setNodeFlag(String nodeFlag) {
        this.nodeFlag = nodeFlag;
    }

    /**
     * @return the transactName
     */
    public String getTransactName() {
        return transactName;
    }

    /**
     * @param transactName
     *            the transactName to set
     */
    public void setTransactName(String transactName) {
        this.transactName = transactName;
    }

    /**
     * @return the transactId
     */
    public Long getTransactId() {
        return transactId;
    }

    /**
     * @param transactId
     *            the transactId to set
     */
    public void setTransactId(Long transactId) {
        this.transactId = transactId;
    }

    /**
     * @return the transactDeptName
     */
    public String getTransactDeptName() {
        return transactDeptName;
    }

    /**
     * @param transactDeptName
     *            the transactDeptName to set
     */
    public void setTransactDeptName(String transactDeptName) {
        this.transactDeptName = transactDeptName;
    }

    /**
     * @return the transactDeptId
     */
    public Long getTransactDeptId() {
        return transactDeptId;
    }

    /**
     * @param transactDeptId
     *            the transactDeptId to set
     */
    public void setTransactDeptId(Long transactDeptId) {
        this.transactDeptId = transactDeptId;
    }

    /**
     * @return the opinions
     */
    public String getOpinions() {
        return opinions;
    }

    /**
     * @param opinions
     *            the opinions to set
     */
    public void setOpinions(String opinions) {
        this.opinions = opinions;
    }

    /**
     * @return the transactTime
     */
    public Date getTransactTime() {
        return transactTime;
    }

    /**
     * @param transactTime
     *            the transactTime to set
     */
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
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

    /**
     * @return the rmInstnoId
     */
    public Long getRmInstnoId() {
        return rmInstnoId;
    }

    /**
     * @param rmInstnoId
     *            the rmInstnoId to set
     */
    public void setRmInstnoId(Long rmInstnoId) {
        this.rmInstnoId = rmInstnoId;
    }

    /**
     * @return the docId
     */
    public Long getDocId() {
        return docId;
    }

    /**
     * @param docId
     *            the docId to set
     */
    public void setDocId(Long docId) {
        this.docId = docId;
    }

}
