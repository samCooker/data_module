/*
 * FileName:    AipCaseFordo.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年1月13日 (LLM) 1.0 Create
 */

package dbdata.aipcase.domain;



import cn.com.chaochuang.common.data.domain.LongIdEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author LLM
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "case_fordo_id")) })
public class AipCaseFordo extends LongIdEntity {

    private static final long serialVersionUID = 6203306617009190591L;

    /** 案件基本信息 */
    @OneToOne
    @JoinColumn(name = "case_apply_id")
    private AipCaseApply      aipCaseApply;
    /** 案件状态 */
    private String        caseStatus;
    /** 发送人编号 */
    private Long senderId;
    /** 发送时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendDate;
    /** 接收时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date receiveDate;
    /** 待办标题 */
    private String title;
    /** 发送人姓名 */
    private String senderName;
    /** 发送人单位 */
    private Long senderDept;
    /** 发送人单位名称 */
    private String senderDeptName;
    /** 发送人单位 */
    private Long sendOrgId;
    /** 接收人编号 */
    private Long receiverId;
    /** 接收人姓名 */
    private String receiverName;
    /** 接收部门编号 */
    private Long receiverDept;
    /** URL */
    private String url;
    /** 待办状态 */
    private String       fordoStatus;
    /** 待办记录标识 */
    private String fordoFlag;
    /** 待办模式 */
    private String       fordoModule;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public AipCaseApply getAipCaseApply() {
        return aipCaseApply;
    }

    public void setAipCaseApply(AipCaseApply aipCaseApply) {
        this.aipCaseApply = aipCaseApply;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public Long getSenderDept() {
        return senderDept;
    }

    public void setSenderDept(Long senderDept) {
        this.senderDept = senderDept;
    }

    public String getSenderDeptName() {
        return senderDeptName;
    }

    public void setSenderDeptName(String senderDeptName) {
        this.senderDeptName = senderDeptName;
    }

    public Long getSendOrgId() {
        return sendOrgId;
    }

    public void setSendOrgId(Long sendOrgId) {
        this.sendOrgId = sendOrgId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public Long getReceiverDept() {
        return receiverDept;
    }

    public void setReceiverDept(Long receiverDept) {
        this.receiverDept = receiverDept;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFordoStatus() {
        return fordoStatus;
    }

    public void setFordoStatus(String fordoStatus) {
        this.fordoStatus = fordoStatus;
    }

    public String getFordoFlag() {
        return fordoFlag;
    }

    public void setFordoFlag(String fordoFlag) {
        this.fordoFlag = fordoFlag;
    }

    public String getFordoModule() {
        return fordoModule;
    }

    public void setFordoModule(String fordoModule) {
        this.fordoModule = fordoModule;
    }
}
