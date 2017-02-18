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
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "CASE_CHAT_ID"))})
public class AipCaseChat extends LongIdEntity {
    /**案件来源*/
    @OneToOne
    @JoinColumn(name = "caseApplyId")
    private AipCaseApply caseApply;
    /**在线聊天名称*/
    private String chatName;
    /**创建时间*/
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    public AipCaseApply getCaseApply() {
        return caseApply;
    }

    public void setCaseApply(AipCaseApply caseApply) {
        this.caseApply = caseApply;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
