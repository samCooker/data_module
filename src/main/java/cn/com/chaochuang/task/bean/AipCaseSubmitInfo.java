/*
 * FileName:    AipCaseUpdate.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年4月30日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.task.bean;

import java.util.Date;

/**
 * @author LLM
 *
 */
public class AipCaseSubmitInfo {
    /** 待办事宜编号 */
    private Long   fordoId;
    /** 案件基础编号 */
    private Long   caseApplyId;
    /** 立案表id */
    private Long   rmCaseLawId;
    /** 当前办理环节编号 */
    private Long   curNodeInfoId;
    /** 下一环节办理人 */
    private Long   receiveMan;
    /** 审批日期 */
    private Date   superviseDate;
    /** 审批意见 */
    private String opinions;
    /** 现场核实人 */
    private String inspectMans;
    /** 现场核实人编号 */
    private String inspectManIds;
    /** 操纵标识：0：提交/确定；1：退回 */
    private String oprationType;

    /**
     * @return the curNodeInfoId
     */
    public Long getCurNodeInfoId() {
        return curNodeInfoId;
    }

    /**
     * @param curNodeInfoId
     *            the curNodeInfoId to set
     */
    public void setCurNodeInfoId(Long curNodeInfoId) {
        this.curNodeInfoId = curNodeInfoId;
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
     * @return the receiveMan
     */
    public Long getReceiveMan() {
        return receiveMan;
    }

    /**
     * @param receiveMan
     *            the receiveMan to set
     */
    public void setReceiveMan(Long receiveMan) {
        this.receiveMan = receiveMan;
    }

    /**
     * @return the superviseDate
     */
    public Date getSuperviseDate() {
        return superviseDate;
    }

    /**
     * @param superviseDate
     *            the superviseDate to set
     */
    public void setSuperviseDate(Date superviseDate) {
        this.superviseDate = superviseDate;
    }

    /**
     * @return the inspectMans
     */
    public String getInspectMans() {
        return inspectMans;
    }

    /**
     * @param inspectMans
     *            the inspectMans to set
     */
    public void setInspectMans(String inspectMans) {
        this.inspectMans = inspectMans;
    }

    /**
     * @return the inspectManIds
     */
    public String getInspectManIds() {
        return inspectManIds;
    }

    /**
     * @param inspectManIds
     *            the inspectManIds to set
     */
    public void setInspectManIds(String inspectManIds) {
        this.inspectManIds = inspectManIds;
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
     * @return the rmCaseLawId
     */
    public Long getRmCaseLawId() {
        return rmCaseLawId;
    }

    /**
     * @param rmCaseLawId
     *            the rmCaseLawId to set
     */
    public void setRmCaseLawId(Long rmCaseLawId) {
        this.rmCaseLawId = rmCaseLawId;
    }

}
