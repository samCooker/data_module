/*
 * FileName:    SysDataChange.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2016年07月04日 (Shicx) 1.0 Create
 */

package dbdata.aipcase.domain;

import cn.com.chaochuang.common.data.domain.LongIdEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Shicx
 */
@SuppressWarnings("serial")
@Entity
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "CASE_CHAT_MESSAGE_ID"))})
public class AipCaseChatMessage extends LongIdEntity {
    /**聊天记录*/
    private Long caseChatId;
    /**发送人ID*/
    private Long senderId;
    /**发送人姓名*/
    private String senderName;
    /**发送人部门名称*/
    private String senderDeptName;
    /**发送人单位名称*/
    private String senderOrgName;
    /**发送消息*/
    private String message;
    /**发送时间*/
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendTime;

    public void setCaseChatId(Long caseChatId) {
        this.caseChatId = caseChatId;
    }

    public Long getCaseChatId() {
        return caseChatId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderDeptName(String senderDeptName) {
        this.senderDeptName = senderDeptName;
    }

    public String getSenderDeptName() {
        return senderDeptName;
    }

    public void setSenderOrgName(String senderOrgName) {
        this.senderOrgName = senderOrgName;
    }

    public String getSenderOrgName() {
        return senderOrgName;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

}
