/*
 * FileName:    AipCaseNodeInfo.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年4月29日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.aipcase.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.com.chaochuang.common.data.domain.LongIdEntity;

/**
 * @author LLM
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "node_info_id")) })
public class AipCaseNodeInfo extends LongIdEntity {

    private static final long serialVersionUID = -2905142543107190293L;

    /** 公文id */
    private Long              caseApplyId;
    /** 原系统流程环节实例编号 */
    private Long              rmCaseApplyId;
    /** 原系统前驱流程环节实例编号 */
    private Long              rmPreInstnoId;
    /** 前驱环节编号 */
    private String            preNodeId;
    /** 环节编号 node_id */
    private String            nodeId;
    /** 环节名称 node_name */
    private String            nodeName;
    /** 当前办理人姓名 */
    private String            transactName;
    /** 当前办理人id */
    private Long              transactId;
    /** 到达时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date              arriveTime;
    /** 完成时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date              finishTime;
    /** 办理部门id */
    private Long              transactDeptId;

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
     * @return the rmPreInstnoId
     */
    public Long getRmPreInstnoId() {
        return rmPreInstnoId;
    }

    /**
     * @param rmPreInstnoId
     *            the rmPreInstnoId to set
     */
    public void setRmPreInstnoId(Long rmPreInstnoId) {
        this.rmPreInstnoId = rmPreInstnoId;
    }

    /**
     * @return the preNodeId
     */
    public String getPreNodeId() {
        return preNodeId;
    }

    /**
     * @param preNodeId
     *            the preNodeId to set
     */
    public void setPreNodeId(String preNodeId) {
        this.preNodeId = preNodeId;
    }

    /**
     * @return the nodeId
     */
    public String getNodeId() {
        return nodeId;
    }

    /**
     * @param nodeId
     *            the nodeId to set
     */
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

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
     * @return the arriveTime
     */
    public Date getArriveTime() {
        return arriveTime;
    }

    /**
     * @param arriveTime
     *            the arriveTime to set
     */
    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    /**
     * @return the finishTime
     */
    public Date getFinishTime() {
        return finishTime;
    }

    /**
     * @param finishTime
     *            the finishTime to set
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
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

}
