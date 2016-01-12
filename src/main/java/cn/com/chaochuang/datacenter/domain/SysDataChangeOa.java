/*
 * FileName:    SysDataChangeOA.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2016
 * History:     2016年1月12日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.datacenter.domain;

import java.util.Date;

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
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "sys_data_change_id")) })
public class SysDataChangeOa extends LongIdEntity {
    /** 更改表名 */
    private String changeTableName;
    /** 更改内容描述 */
    private String changeScript;
    /** 记录填写时间 */
    private Date   changeDate;
    /** 操作类型 */
    private String operationType;

    /**
     * @return the changeTableName
     */
    public String getChangeTableName() {
        return changeTableName;
    }

    /**
     * @param changeTableName
     *            the changeTableName to set
     */
    public void setChangeTableName(String changeTableName) {
        this.changeTableName = changeTableName;
    }

    /**
     * @return the changeScript
     */
    public String getChangeScript() {
        return changeScript;
    }

    /**
     * @param changeScript
     *            the changeScript to set
     */
    public void setChangeScript(String changeScript) {
        this.changeScript = changeScript;
    }

    /**
     * @return the changeDate
     */
    public Date getChangeDate() {
        return changeDate;
    }

    /**
     * @param changeDate
     *            the changeDate to set
     */
    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    /**
     * @return the operationType
     */
    public String getOperationType() {
        return operationType;
    }

    /**
     * @param operationType
     *            the operationType to set
     */
    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }
}
