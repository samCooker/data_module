/*
 * FileName:    WebServiceNodeInfo.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2013
 * History:     2015-6-11 (LLM) 1.0 Create
 */
package cn.com.chaochuang.task.bean;

/**
 * The <code>WebServiceNodeInfo.java</code>
 *
 * @author LLM
 * @version 1.0, 2015-6-11
 */
public class WebServiceNodeInfo {
    /** 审批内容 */
    private String  approveContent;
    /** 处理待办编号 */
    private Long    pendingHandleId;
    /** 提交操作用户编号 */
    private Long    userId;
    private Long    nodeId;
    private String  next;
    private String  assignee;
    private String  nodeType;
    private String  timeLimitFlag;
    private boolean noPendingHandle;

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
     * @return the nodeType
     */
    public String getNodeType() {
        return nodeType;
    }

    /**
     * @param nodeType
     *            the nodeType to set
     */
    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    /**
     * @return the timeLimitFlag
     */
    public String getTimeLimitFlag() {
        return timeLimitFlag;
    }

    /**
     * @param timeLimitFlag
     *            the timeLimitFlag to set
     */
    public void setTimeLimitFlag(String timeLimitFlag) {
        this.timeLimitFlag = timeLimitFlag;
    }

    /**
     * @return the noPendingHandle
     */
    public boolean isNoPendingHandle() {
        return noPendingHandle;
    }

    /**
     * @param noPendingHandle
     *            the noPendingHandle to set
     */
    public void setNoPendingHandle(boolean noPendingHandle) {
        this.noPendingHandle = noPendingHandle;
    }

}
