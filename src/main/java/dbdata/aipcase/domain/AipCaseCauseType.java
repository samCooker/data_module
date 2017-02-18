/*
 * FileName:    AipCaseCauseType.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年1月16日 (frt) 1.0 Create
 */

package dbdata.aipcase.domain;



import cn.com.chaochuang.common.data.domain.LongIdEntity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author frt
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "case_cause_type_id")),
                @AttributeOverride(name = "valid", column = @Column(name = "valid")) })
public class AipCaseCauseType extends LongIdEntity {
    private static final long serialVersionUID = 4368569898874697989L;
    private String caseCauseTypeName;

    /**
     * @return the caseCauseTypeId
     */
    public Long getd() {
        return id;
    }

    /**
     * @param caseCauseTypeId
     *            the caseCauseTypeId to set
     */
    public void setId(Long caseCauseTypeId) {
        this.id = caseCauseTypeId;
    }

    /**
     * @return the caseCauseTypeName
     */
    public String getCaseCauseTypeName() {
        return caseCauseTypeName;
    }

    /**
     * @param caseCauseTypeName
     *            the caseCauseTypeName to set
     */
    public void setCaseCauseTypeName(String caseCauseTypeName) {
        this.caseCauseTypeName = caseCauseTypeName;
    }

}
