/*
 * FileName:    FlowInstNodeApprove.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月14日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.casecomplaint.bean;

import java.util.Date;

/**
 * @author Shicx
 *
 */
public class FlowInstNodeApprove {
    /** 远程意见id */
    private Long   approveId;
    /** 流程实例流水号 */
    private Long   flowInstId;
    /** 实例id */
    private String instId;
    /** 节点名称 */
    private String nodeName;
    /** 节点代码 */
    private String nodeCode;
    /** 节点id */
    private String nodeId;
    /** 意见填写人id userInfoId */
    private Long   approverId;
    /** 意见填写人所在部门id ! */
    private Long   approverDepId;
    /** 意见填写日期 */
    private Date   approveDate;
    /** 意见填写 */
    private String approveContent;

    /**
     * @return the approveId
     */
    public Long getApproveId() {
        return approveId;
    }

    /**
     * @param approveId
     *            the approveId to set
     */
    public void setApproveId(Long approveId) {
        this.approveId = approveId;
    }

    /**
     * @return the flowInstId
     */
    public Long getFlowInstId() {
        return flowInstId;
    }

    /**
     * @param flowInstId
     *            the flowInstId to set
     */
    public void setFlowInstId(Long flowInstId) {
        this.flowInstId = flowInstId;
    }

    /**
     * @return the instId
     */
    public String getInstId() {
        return instId;
    }

    /**
     * @param instId
     *            the instId to set
     */
    public void setInstId(String instId) {
        this.instId = instId;
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
     * @return the nodeCode
     */
    public String getNodeCode() {
        return nodeCode;
    }

    /**
     * @param nodeCode
     *            the nodeCode to set
     */
    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
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
     * @return the approverId
     */
    public Long getApproverId() {
        return approverId;
    }

    /**
     * @param approverId
     *            the approverId to set
     */
    public void setApproverId(Long approverId) {
        this.approverId = approverId;
    }

    /**
     * @return the approverDepId
     */
    public Long getApproverDepId() {
        return approverDepId;
    }

    /**
     * @param approverDepId
     *            the approverDepId to set
     */
    public void setApproverDepId(Long approverDepId) {
        this.approverDepId = approverDepId;
    }

    /**
     * @return the approveDate
     */
    public Date getApproveDate() {
        return approveDate;
    }

    /**
     * @param approveDate
     *            the approveDate to set
     */
    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    /**
     * @return the approveContent
     */
    public String getApproveContent() {
        return approveContent;
    }

    /**
     * @param approveContent
     *            the approveContent to set
     */
    public void setApproveContent(String approveContent) {
        this.approveContent = approveContent;
    }

}
