/*
 * FileName:    ComplaintSubmitContent.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月16日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.casecomplaint.bean;

import java.util.Date;

/**
 * @author Shicx
 *
 *         将所有要提交的数据保存并转成json数据格式保存到DATA_UPDATE表的content字段中
 */
public class ComplaintSubmitContent {

    /** 任务id（不能为空） */
    private String taskId;
    /** 下一个环节（不能为空） */
    private String next;
    /** 下一环节接收人id[可为空] */
    private String assignee;
    /** 意见内容 */
    private String approveContent;
    /** 意见填写时间 */
    private Date   approveTime;

    /**
     * @return the taskId
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * @param taskId
     *            the taskId to set
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
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
     * @return the approveTime
     */
    public Date getApproveTime() {
        return approveTime;
    }

    /**
     * @param approveTime
     *            the approveTime to set
     */
    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

}
