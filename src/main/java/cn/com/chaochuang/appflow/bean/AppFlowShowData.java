/*
 * FileName:    AppFlowShowData.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年6月1日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.appflow.bean;

import java.util.Date;
import java.util.List;

import cn.com.chaochuang.appflow.domain.AppFlowNodeInfo;
import cn.com.chaochuang.appflow.domain.AppFlowNodeOpinions;
import cn.com.chaochuang.appflow.domain.AppItemAttach;

/**
 * @author LLM
 *
 */
public class AppFlowShowData {

    public static final String UNFINDEDPENDING = "unfinded";
    /** 数据显示标识 */
    private String             dataFlag;

    /** 待办编号 */
    private Long               pendingHandleId;
    /** 原系统事项申请编号 */
    private Long               rmItemApplyId;
    /** 受理编号 */
    private String             itemNum;
    /**  */
    private Long               prjId;
    /**  */
    private String             prjSortId;
    /**  */
    private String             prjName;
    /** 审批流程分类 */
    private String             prjApproveFlag;
    /** 委托审批中心现场检查 */
    private String             prjCensorFlag;
    /** 数据核对码 */
    private String             checkNum;
    /** 承办单位编号 */
    private Long               handleUnitId;
    /** 承办处室编号 */
    private Long               handleDepId;
    /** 承办处室名称 */
    private String             handleDepName;
    /** 转办来源单位 */
    private Long               transferUnitId;
    /** 转办来源单位名称 */
    private String             transferUnitName;
    /** 申请信息 */
    private String             applyInfo;
    /** 申请时间 */
    private Date               applyTime;
    /** 办结时间 */
    private Date               archiveTime;
    /** 办结人 */
    private Long               archiver;
    /** 申请有效标志 */
    private String             applyValid;
    /** 申请状态 */
    private String             applyStatus;
    /** 申请类型 */
    private String             itemClass;
    /** 申请人编号（createrId对应sysuser表的rmUserInfoId） */
    private Long               createrId;
    /** 申请人姓名 */
    private String             createrName;
    /** 填写时间 */
    private Date               createTime;
    /** 窗口（网上）收件时间 */
    private Date               acceptTime;
    /** 承办（受理）时间 */
    private Date               handleTime;
    /** 审批通过人ID */
    private Long               checkerId;
    /** 审批通过人姓名 */
    private String             checkerName;
    /** 审批通过时间 */
    private Date               checkTime;
    /** 申请企业ID */
    private Long               entpId;
    /** 申请执业药师ID */
    private Long               pharmId;
    /** 申请执业药师姓名 */
    private String             pharmName;
    /** 流程填补标志 */
    private String             flowFlag;
    /** 流程环节实例列表 */
    List<AppFlowNodeInfo>      flowNodeInfos;
    /** 流程环节审批列表 */
    List<AppFlowNodeOpinions>  flowNodeOpinions;
    /** 审批附件列表 */
    List<AppItemAttach>        appItemAttachInfos;

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
     * @return the dataFlag
     */
    public String getDataFlag() {
        return dataFlag;
    }

    /**
     * @param dataFlag
     *            the dataFlag to set
     */
    public void setDataFlag(String dataFlag) {
        this.dataFlag = dataFlag;
    }

    /**
     * @return the rmItemApplyId
     */
    public Long getRmItemApplyId() {
        return rmItemApplyId;
    }

    /**
     * @param rmItemApplyId
     *            the rmItemApplyId to set
     */
    public void setRmItemApplyId(Long rmItemApplyId) {
        this.rmItemApplyId = rmItemApplyId;
    }

    /**
     * @return the itemNum
     */
    public String getItemNum() {
        return itemNum;
    }

    /**
     * @param itemNum
     *            the itemNum to set
     */
    public void setItemNum(String itemNum) {
        this.itemNum = itemNum;
    }

    /**
     * @return the prjId
     */
    public Long getPrjId() {
        return prjId;
    }

    /**
     * @param prjId
     *            the prjId to set
     */
    public void setPrjId(Long prjId) {
        this.prjId = prjId;
    }

    /**
     * @return the prjSortId
     */
    public String getPrjSortId() {
        return prjSortId;
    }

    /**
     * @param prjSortId
     *            the prjSortId to set
     */
    public void setPrjSortId(String prjSortId) {
        this.prjSortId = prjSortId;
    }

    /**
     * @return the prjName
     */
    public String getPrjName() {
        return prjName;
    }

    /**
     * @param prjName
     *            the prjName to set
     */
    public void setPrjName(String prjName) {
        this.prjName = prjName;
    }

    /**
     * @return the prjApproveFlag
     */
    public String getPrjApproveFlag() {
        return prjApproveFlag;
    }

    /**
     * @param prjApproveFlag
     *            the prjApproveFlag to set
     */
    public void setPrjApproveFlag(String prjApproveFlag) {
        this.prjApproveFlag = prjApproveFlag;
    }

    /**
     * @return the prjCensorFlag
     */
    public String getPrjCensorFlag() {
        return prjCensorFlag;
    }

    /**
     * @param prjCensorFlag
     *            the prjCensorFlag to set
     */
    public void setPrjCensorFlag(String prjCensorFlag) {
        this.prjCensorFlag = prjCensorFlag;
    }

    /**
     * @return the checkNum
     */
    public String getCheckNum() {
        return checkNum;
    }

    /**
     * @param checkNum
     *            the checkNum to set
     */
    public void setCheckNum(String checkNum) {
        this.checkNum = checkNum;
    }

    /**
     * @return the handleUnitId
     */
    public Long getHandleUnitId() {
        return handleUnitId;
    }

    /**
     * @param handleUnitId
     *            the handleUnitId to set
     */
    public void setHandleUnitId(Long handleUnitId) {
        this.handleUnitId = handleUnitId;
    }

    /**
     * @return the handleDepId
     */
    public Long getHandleDepId() {
        return handleDepId;
    }

    /**
     * @param handleDepId
     *            the handleDepId to set
     */
    public void setHandleDepId(Long handleDepId) {
        this.handleDepId = handleDepId;
    }

    /**
     * @return the handleDepName
     */
    public String getHandleDepName() {
        return handleDepName;
    }

    /**
     * @param handleDepName
     *            the handleDepName to set
     */
    public void setHandleDepName(String handleDepName) {
        this.handleDepName = handleDepName;
    }

    /**
     * @return the transferUnitId
     */
    public Long getTransferUnitId() {
        return transferUnitId;
    }

    /**
     * @param transferUnitId
     *            the transferUnitId to set
     */
    public void setTransferUnitId(Long transferUnitId) {
        this.transferUnitId = transferUnitId;
    }

    /**
     * @return the transferUnitName
     */
    public String getTransferUnitName() {
        return transferUnitName;
    }

    /**
     * @param transferUnitName
     *            the transferUnitName to set
     */
    public void setTransferUnitName(String transferUnitName) {
        this.transferUnitName = transferUnitName;
    }

    /**
     * @return the applyInfo
     */
    public String getApplyInfo() {
        return applyInfo;
    }

    /**
     * @param applyInfo
     *            the applyInfo to set
     */
    public void setApplyInfo(String applyInfo) {
        this.applyInfo = applyInfo;
    }

    /**
     * @return the applyTime
     */
    public Date getApplyTime() {
        return applyTime;
    }

    /**
     * @param applyTime
     *            the applyTime to set
     */
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    /**
     * @return the archiveTime
     */
    public Date getArchiveTime() {
        return archiveTime;
    }

    /**
     * @param archiveTime
     *            the archiveTime to set
     */
    public void setArchiveTime(Date archiveTime) {
        this.archiveTime = archiveTime;
    }

    /**
     * @return the archiver
     */
    public Long getArchiver() {
        return archiver;
    }

    /**
     * @param archiver
     *            the archiver to set
     */
    public void setArchiver(Long archiver) {
        this.archiver = archiver;
    }

    /**
     * @return the applyValid
     */
    public String getApplyValid() {
        return applyValid;
    }

    /**
     * @param applyValid
     *            the applyValid to set
     */
    public void setApplyValid(String applyValid) {
        this.applyValid = applyValid;
    }

    /**
     * @return the applyStatus
     */
    public String getApplyStatus() {
        return applyStatus;
    }

    /**
     * @param applyStatus
     *            the applyStatus to set
     */
    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    /**
     * @return the itemClass
     */
    public String getItemClass() {
        return itemClass;
    }

    /**
     * @param itemClass
     *            the itemClass to set
     */
    public void setItemClass(String itemClass) {
        this.itemClass = itemClass;
    }

    /**
     * @return the createrId
     */
    public Long getCreaterId() {
        return createrId;
    }

    /**
     * @param createrId
     *            the createrId to set
     */
    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    /**
     * @return the createrName
     */
    public String getCreaterName() {
        return createrName;
    }

    /**
     * @param createrName
     *            the createrName to set
     */
    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the acceptTime
     */
    public Date getAcceptTime() {
        return acceptTime;
    }

    /**
     * @param acceptTime
     *            the acceptTime to set
     */
    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }

    /**
     * @return the handleTime
     */
    public Date getHandleTime() {
        return handleTime;
    }

    /**
     * @param handleTime
     *            the handleTime to set
     */
    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    /**
     * @return the checkerId
     */
    public Long getCheckerId() {
        return checkerId;
    }

    /**
     * @param checkerId
     *            the checkerId to set
     */
    public void setCheckerId(Long checkerId) {
        this.checkerId = checkerId;
    }

    /**
     * @return the checkerName
     */
    public String getCheckerName() {
        return checkerName;
    }

    /**
     * @param checkerName
     *            the checkerName to set
     */
    public void setCheckerName(String checkerName) {
        this.checkerName = checkerName;
    }

    /**
     * @return the checkTime
     */
    public Date getCheckTime() {
        return checkTime;
    }

    /**
     * @param checkTime
     *            the checkTime to set
     */
    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    /**
     * @return the entpId
     */
    public Long getEntpId() {
        return entpId;
    }

    /**
     * @param entpId
     *            the entpId to set
     */
    public void setEntpId(Long entpId) {
        this.entpId = entpId;
    }

    /**
     * @return the pharmId
     */
    public Long getPharmId() {
        return pharmId;
    }

    /**
     * @param pharmId
     *            the pharmId to set
     */
    public void setPharmId(Long pharmId) {
        this.pharmId = pharmId;
    }

    /**
     * @return the pharmName
     */
    public String getPharmName() {
        return pharmName;
    }

    /**
     * @param pharmName
     *            the pharmName to set
     */
    public void setPharmName(String pharmName) {
        this.pharmName = pharmName;
    }

    /**
     * @return the flowFlag
     */
    public String getFlowFlag() {
        return flowFlag;
    }

    /**
     * @param flowFlag
     *            the flowFlag to set
     */
    public void setFlowFlag(String flowFlag) {
        this.flowFlag = flowFlag;
    }

    /**
     * @return the flowNodeInfos
     */
    public List<AppFlowNodeInfo> getFlowNodeInfos() {
        return flowNodeInfos;
    }

    /**
     * @param flowNodeInfos
     *            the flowNodeInfos to set
     */
    public void setFlowNodeInfos(List<AppFlowNodeInfo> flowNodeInfos) {
        this.flowNodeInfos = flowNodeInfos;
    }

    /**
     * @return the flowNodeOpinions
     */
    public List<AppFlowNodeOpinions> getFlowNodeOpinions() {
        return flowNodeOpinions;
    }

    /**
     * @param flowNodeOpinions
     *            the flowNodeOpinions to set
     */
    public void setFlowNodeOpinions(List<AppFlowNodeOpinions> flowNodeOpinions) {
        this.flowNodeOpinions = flowNodeOpinions;
    }

    /**
     * @return the appItemAttachInfos
     */
    public List<AppItemAttach> getAppItemAttachInfos() {
        return appItemAttachInfos;
    }

    /**
     * @param appItemAttachInfos
     *            the appItemAttachInfos to set
     */
    public void setAppItemAttachInfos(List<AppItemAttach> appItemAttachInfos) {
        this.appItemAttachInfos = appItemAttachInfos;
    }

}
