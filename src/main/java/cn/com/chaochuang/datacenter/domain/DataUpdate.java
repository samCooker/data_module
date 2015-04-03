/*
 * FileName:    DataUpdate.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月27日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.datacenter.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.com.chaochuang.common.data.domain.LongIdEntity;
import cn.com.chaochuang.datacenter.reference.OperationType;
import cn.com.chaochuang.datacenter.reference.OperationTypeConverter;
import cn.com.chaochuang.datacenter.reference.WorkType;
import cn.com.chaochuang.datacenter.reference.WorkTypeConverter;

/**
 * @author LLM
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "data_update_id")) })
public class DataUpdate extends LongIdEntity {
    /** 业务类型 */
    @Convert(converter = WorkTypeConverter.class)
    private WorkType      workType;
    /** 操作类型 */
    @Convert(converter = OperationTypeConverter.class)
    private OperationType operationType;
    /** 更新内容 */
    private String        content;
    /** 数据输入时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date          inputDate;

    /**
     * @return the workType
     */
    public WorkType getWorkType() {
        return workType;
    }

    /**
     * @param workType
     *            the workType to set
     */
    public void setWorkType(WorkType workType) {
        this.workType = workType;
    }

    /**
     * @return the operationType
     */
    public OperationType getOperationType() {
        return operationType;
    }

    /**
     * @param operationType
     *            the operationType to set
     */
    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     *            the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the inputDate
     */
    public Date getInputDate() {
        return inputDate;
    }

    /**
     * @param inputDate
     *            the inputDate to set
     */
    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

}
