/*
 * FileName:    AuditSubmitData.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年10月26日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.task.bean;

/**
 * @author Shicx
 *
 */
public class AuditSubmitData {

    /** 处理待办编号 */
    private Long     pendingHandleId;
    /** 审批意见 */
    private String   approveContent;
    /** 节点id（非空） */
    private Long     nodeId;
    /** 下一节点名 （非空） */
    private String   next;
    /** 下一环节接收人 */
    private String   assignee;
    /** 提交操作用户编号 （非空） */
    private Long     userId;
    /** 原整改内容id */
    private String[] rmPrjContentIds;
    private String[] auditResults;
    private String[] auditRecords;
    /** 是否整改标识 */
    private String[] improveFlags;
    /** 整改内容 */
    private String[] improveResults;

    /**
     * @return the rmPrjContentIds
     */
    public String[] getRmPrjContentIds() {
        return rmPrjContentIds;
    }

    /**
     * @param rmPrjContentIds
     *            the rmPrjContentIds to set
     */
    public void setRmPrjContentIds(String[] rmPrjContentIds) {
        this.rmPrjContentIds = rmPrjContentIds;
    }

    /**
     * @return the auditResults
     */
    public String[] getAuditResults() {
        return auditResults;
    }

    /**
     * @param auditResults
     *            the auditResults to set
     */
    public void setAuditResults(String[] auditResults) {
        this.auditResults = auditResults;
    }

    /**
     * @return the auditRecords
     */
    public String[] getAuditRecords() {
        return auditRecords;
    }

    /**
     * @param auditRecords
     *            the auditRecords to set
     */
    public void setAuditRecords(String[] auditRecords) {
        this.auditRecords = auditRecords;
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

    /**
     * @return the nodeId
     */
    public Long getNodeId() {
        return nodeId;
    }

    /**
     * @param nodeId
     *            the nodeId to set
     */
    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    /**
     * @return the next
     */
    public String getNext() {
        return next;
    }

    /**
     * @param next
     *            the next to set
     */
    public void setNext(String next) {
        this.next = next;
    }

    /**
     * @return the assignee
     */
    public String getAssignee() {
        return assignee;
    }

    /**
     * @param assignee
     *            the assignee to set
     */
    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return the improveFlags
     */
    public String[] getImproveFlags() {
        return improveFlags;
    }

    /**
     * @param improveFlags
     *            the improveFlags to set
     */
    public void setImproveFlags(String[] improveFlags) {
        this.improveFlags = improveFlags;
    }

    /**
     * @return the improveResults
     */
    public String[] getImproveResults() {
        return improveResults;
    }

    /**
     * @param improveResults
     *            the improveResults to set
     */
    public void setImproveResults(String[] improveResults) {
        this.improveResults = improveResults;
    }

    /**
     * @return the pendingHandleId
     */
    public Long getPendingHandleId() {
        return pendingHandleId;
    }

    /**
     * @param pendingHandleId
     *            the pendingHandleId to set
     */
    public void setPendingHandleId(Long pendingHandleId) {
        this.pendingHandleId = pendingHandleId;
    }

}
