/*
 * FileName:    AipCaseAttention.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2016
 * History:     2016年7月8日 (LLM) 1.0 Create
 */

package dbdata.aipcase.domain;

import cn.com.chaochuang.common.data.domain.LongIdEntity;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;

/**
 *
 */
@Entity
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "case_attention_id"))})
public class AipCaseAttention extends LongIdEntity {
    /**
     * 立案表
     */
    @OneToOne
    @JoinColumn(name = "case_apply_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private AipCaseApply aipCaseApply;
    /**
     * 关注人名称
     */
    private String attentionManName;
    /**
     * 关注人编号
     */
    private Long attentionManId;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 当前案件状态
     */
    private String attentionCaseStatus;
    /**
     * 案件变更标识
     */
    private String changeFlag;

    /**
     * @return the aipCaseApply
     */
    public AipCaseApply getAipCaseApply() {
        return aipCaseApply;
    }

    /**
     * @param aipCaseApply the aipCaseApply to set
     */
    public void setAipCaseApply(AipCaseApply aipCaseApply) {
        this.aipCaseApply = aipCaseApply;
    }

    /**
     * @return the attentionManName
     */
    public String getAttentionManName() {
        return attentionManName;
    }

    /**
     * @param attentionManName the attentionManName to set
     */
    public void setAttentionManName(String attentionManName) {
        this.attentionManName = attentionManName;
    }

    /**
     * @return the attentionManId
     */
    public Long getAttentionManId() {
        return attentionManId;
    }

    /**
     * @param attentionManId the attentionManId to set
     */
    public void setAttentionManId(Long attentionManId) {
        this.attentionManId = attentionManId;
    }

    /**
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the attentionCaseStatus
     */
    public String getAttentionCaseStatus() {
        return attentionCaseStatus;
    }

    /**
     * @param attentionCaseStatus the attentionCaseStatus to set
     */
    public void setAttentionCaseStatus(String attentionCaseStatus) {
        this.attentionCaseStatus = attentionCaseStatus;
    }

    /**
     * @return the changeFlag
     */
    public String getChangeFlag() {
        return changeFlag;
    }

    /**
     * @param changeFlag the changeFlag to set
     */
    public void setChangeFlag(String changeFlag) {
        this.changeFlag = changeFlag;
    }
}
