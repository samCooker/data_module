/*
 * FileName:    BackData.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年4月9日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.datacenter.bean;

/**
 * @author Shicx
 *
 */
public class BackData {

    /** 环节实例id */
    private String instanceId;
    /** 节点id */
    private String nodeId;
    /** 当前环节接收人id */
    private Long   transactId;
    /** 新建的节点实例id */
    private Long   instnoId;

    /**
     *
     */
    public BackData() {
        super();
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
     * @return the instnoId
     */
    public Long getInstnoId() {
        return instnoId;
    }

    /**
     * @param instnoId
     *            the instnoId to set
     */
    public void setInstnoId(Long instnoId) {
        this.instnoId = instnoId;
    }

}
