/*
 * FileName:    DocFileUpdate.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年4月3日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.datacenter.bean;

/**
 * @author LLM
 *
 */
public class DocFileUpdate {
    /** 待办事宜编号 */
    private Long   fordoId;
    /** 远程系统待办明细编号 */
    private String rmPendingItemId;
    /** 原实例编号 */
    private String rmInstanceId;
    /** 流程编号 */
    private String flowId;
    /** 公文编号 */
    private Long   docId;
    /** 当前实例环节编号 */
    private Long   curInstnoId;
    /** 下一环节编号 */
    private String nextNodeId;
    /** 下一环节办理人 */
    private String nextManId;
    /** 审批意见 */
    private String opinions;
    /** 环节标识（就是itemId） */
    private String nodeFlag;
    /** 操纵标识：0：呈送；1：办结；2：退回 */
    private String oprationType;

    /**
     * @return the rmPendingItemId
     */
    public String getRmPendingItemId() {
        return rmPendingItemId;
    }

    /**
     * @param rmPendingItemId
     *            the rmPendingItemId to set
     */
    public void setRmPendingItemId(String rmPendingItemId) {
        this.rmPendingItemId = rmPendingItemId;
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
     * @return the flowId
     */
    public String getFlowId() {
        return flowId;
    }

    /**
     * @param flowId
     *            the flowId to set
     */
    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    /**
     * @return the fordoId
     */
    public Long getFordoId() {
        return fordoId;
    }

    /**
     * @param fordoId
     *            the fordoId to set
     */
    public void setFordoId(Long fordoId) {
        this.fordoId = fordoId;
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

    /**
     * @return the curInstnoId
     */
    public Long getCurInstnoId() {
        return curInstnoId;
    }

    /**
     * @param curInstnoId
     *            the curInstnoId to set
     */
    public void setCurInstnoId(Long curInstnoId) {
        this.curInstnoId = curInstnoId;
    }

    /**
     * @return the nextNodeId
     */
    public String getNextNodeId() {
        return nextNodeId;
    }

    /**
     * @param nextNodeId
     *            the nextNodeId to set
     */
    public void setNextNodeId(String nextNodeId) {
        this.nextNodeId = nextNodeId;
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
     * @return the oprationType
     */
    public String getOprationType() {
        return oprationType;
    }

    /**
     * @param oprationType
     *            the oprationType to set
     */
    public void setOprationType(String oprationType) {
        this.oprationType = oprationType;
    }

    /**
     * @return the nextManId
     */
    public String getNextManId() {
        return nextManId;
    }

    /**
     * @param nextManId
     *            the nextManId to set
     */
    public void setNextManId(String nextManId) {
        this.nextManId = nextManId;
    }
}
