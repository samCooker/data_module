/*
 * FileName:    FlowNodeInfo.java
 * Description:
 * Company:     ����������Ϣ�������޹�˾
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015��3��28�� (Shicx) 1.0 Create
 */
package cn.com.chaochuang.task.bean;

import java.util.Date;

/**
 * @author Shicx
 *
 */
public class FlowNodeBeanInfo {

    /** 原实例编号 inst_id */
    private String instanceId;
    /** 环节编号 node_id */
    private String nodeId;
    /** 环节名称 node_name */
    private String nodeName;
    /** 办理意见 表wf_data_text>item_id=lw_nbld... */
    private String opinions;
    /** 办理人 根据cur_man查找user表获取name */
    private String transactName;
    /** 办理人id cur_man */
    private Long   transactId;
    /** 办理部门 id 根据cur_man查找user表获取 */
    private Long   transactDeptId;
    /** 办理部门 根据cur_man查找user表获取 */
    private String transactDeptName;
    /** 办理时间 */
    private Date   transactDate;
    /** 当前环节 */
    private Long   rmInstnoId;

    /**
     *
     */
    public FlowNodeBeanInfo() {
        super();
    }

    /**
     * @param instanceId
     */
    public FlowNodeBeanInfo(String instanceId) {
        super();
        this.instanceId = instanceId;
    }

    /**
     * @return the instanceId
     */
    public String getInstanceId() {
        return instanceId;
    }

    /**
     * @param instanceId
     *            the instanceId to set
     */
    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
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
     * @return the transactDate
     */
    public Date getTransactDate() {
        return transactDate;
    }

    /**
     * @param transactDate
     *            the transactDate to set
     */
    public void setTransactDate(Date transactDate) {
        this.transactDate = transactDate;
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

}
