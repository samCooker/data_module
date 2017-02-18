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

/**
 * @author Shicx
 */
@SuppressWarnings("serial")
@Entity
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "CHAT_PERSON_ID"))})
public class AipCaseChatPerson extends LongIdEntity {
    /**人员id*/
    private Long userId;
    /**聊天记录*/
    @ManyToOne
    @JoinColumn(name = "caseChatId")
    private AipCaseChat caseChat;
    /**是否置顶*/
    private String messageTopFlag;
    /**最近一次的消息*/
    @OneToOne
    @JoinColumn(name = "lastMessageId")
    private AipCaseChatMessage lastMessage;
    /**未读消息数*/
    private Integer unreadNum;

    public Integer getUnreadNum() {
        return unreadNum;
    }

    public void setUnreadNum(Integer unreadNum) {
        this.unreadNum = unreadNum;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public AipCaseChat getCaseChat() {
        return caseChat;
    }

    public void setCaseChat(AipCaseChat caseChat) {
        this.caseChat = caseChat;
    }

    public String getMessageTopFlag() {
        return messageTopFlag;
    }

    public void setMessageTopFlag(String messageTopFlag) {
        this.messageTopFlag = messageTopFlag;
    }

    public AipCaseChatMessage getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(AipCaseChatMessage lastMessage) {
        this.lastMessage = lastMessage;
    }
}
