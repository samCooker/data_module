/*
 * FileName:    WfDocRecman.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2016
 * History:     2016年4月26日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.docex.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;

import cn.com.chaochuang.common.data.domain.LongIdEntity;

/**
 * @author LLM
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "doc_recman_id")) })
public class WfDocRecman extends LongIdEntity {
    /** 原系统流水号 */
    private Long rmDocRecmanId;
    /** 单位编号 */
    private Long unitOrgId;
    /** 接收人编号 */
    private Long receiverId;
    /** 接收部门编号 */
    private Long receiverDeptId;

    /**
     * @return the rmDocRecmanId
     */
    public Long getRmDocRecmanId() {
        return rmDocRecmanId;
    }

    /**
     * @param rmDocRecmanId
     *            the rmDocRecmanId to set
     */
    public void setRmDocRecmanId(Long rmDocRecmanId) {
        this.rmDocRecmanId = rmDocRecmanId;
    }

    /**
     * @return the unitOrgId
     */
    public Long getUnitOrgId() {
        return unitOrgId;
    }

    /**
     * @param unitOrgId
     *            the unitOrgId to set
     */
    public void setUnitOrgId(Long unitOrgId) {
        this.unitOrgId = unitOrgId;
    }

    /**
     * @return the receiverId
     */
    public Long getReceiverId() {
        return receiverId;
    }

    /**
     * @param receiverId
     *            the receiverId to set
     */
    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    /**
     * @return the receiverDeptId
     */
    public Long getReceiverDeptId() {
        return receiverDeptId;
    }

    /**
     * @param receiverDeptId
     *            the receiverDeptId to set
     */
    public void setReceiverDeptId(Long receiverDeptId) {
        this.receiverDeptId = receiverDeptId;
    }

}
