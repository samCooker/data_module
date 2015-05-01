/*
 * FileName:    FlowTransactPersonal.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年4月30日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.docwork.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.com.chaochuang.common.data.domain.LongIdEntity;

/**
 * @author Shicx 公文个人办理记录表
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "transact_personal_id")) })
public class FlowTransactPersonal extends LongIdEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /** 公文实体 */
    @ManyToOne
    @JoinColumn(name = "DOC_ID")
    private DocFile           docFile;
    /** 办理人id */
    private Long              transactId;
    /** 办理时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date              transactTime;
    /** 原系统公文编号 */
    private String            rmInstanceId;

    /**
     * @return the docFile
     */
    public DocFile getDocFile() {
        return docFile;
    }

    /**
     * @param docFile
     *            the docFile to set
     */
    public void setDocFile(DocFile docFile) {
        this.docFile = docFile;
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
     * @return the transactTime
     */
    public Date getTransactTime() {
        return transactTime;
    }

    /**
     * @param transactTime
     *            the transactTime to set
     */
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
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

}
