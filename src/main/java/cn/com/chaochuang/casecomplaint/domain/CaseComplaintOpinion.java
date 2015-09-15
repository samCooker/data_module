/*
 * FileName:    SysDataChange.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年09月10日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.casecomplaint.domain;

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
@SuppressWarnings("serial")
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "OPINION_ID")) })
public class CaseComplaintOpinion extends LongIdEntity {
    /** 远程意见id */
    private Long   rmOpinionId;
    /** 节点名称 */
    private String nodeName;
    /** 节点id */
    private Long   nodeId;
    /** 节点代码 */
    private String nodeCode;
    /** 流程实例流水号 */
    private Long   flowInstId;
    /** 流程实例id */
    private String instId;
    /** 意见填写人id */
    private Long   approverId;
    /** 意见填写人名称 */
    private String approverName;
    /** 意见填写人所在部门id ! */
    private Long   approverDeptId;
    /** 意见填写人所在部门名称 ! */
    private String approverDeptName;
    /** 意见填写日期 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date   approveDate;
    /** 意见 */
    private String approveContent;

    public void setRmOpinionId(Long rmOpinionId) {
        this.rmOpinionId = rmOpinionId;
    }

    public Long getRmOpinionId() {
        return rmOpinionId;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setApproverId(Long approverId) {
        this.approverId = approverId;
    }

    public Long getApproverId() {
        return approverId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setApproverDeptId(Long approverDeptId) {
        this.approverDeptId = approverDeptId;
    }

    public Long getApproverDeptId() {
        return approverDeptId;
    }

    public void setFlowInstId(Long flowInstId) {
        this.flowInstId = flowInstId;
    }

    public Long getFlowInstId() {
        return flowInstId;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setApproverName(String approverName) {
        this.approverName = approverName;
    }

    public String getApproverName() {
        return approverName;
    }

    public void setApproverDeptName(String approverDeptName) {
        this.approverDeptName = approverDeptName;
    }

    public String getApproverDeptName() {
        return approverDeptName;
    }

    public void setInstId(String instId) {
        this.instId = instId;
    }

    public String getInstId() {
        return instId;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveContent(String approveContent) {
        this.approveContent = approveContent;
    }

    public String getApproveContent() {
        return approveContent;
    }

}
