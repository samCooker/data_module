/*
 * FileName:    AipNotePrefix.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年1月10日 (LLM) 1.0 Create
 */

package dbdata.aipcase.domain;


import cn.com.chaochuang.common.data.domain.LongIdEntity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author LLM
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "note_prefix_id")) })
public class AipNotePrefix extends LongIdEntity {

    private static final long serialVersionUID = 8447397828395145010L;

    /** 文书编号前缀 */
    private String prefixName;
    /** 文书编号标识 */
    private String prefixFlag;
    /** 所属单位编号 */
    private Long unitId;

    /**
     * @return the prefixName
     */
    public String getPrefixName() {
        return prefixName;
    }

    /**
     * @param prefixName
     *            the prefixName to set
     */
    public void setPrefixName(String prefixName) {
        this.prefixName = prefixName;
    }

    /**
     * @return the prefixFlag
     */
    public String getPrefixFlag() {
        return prefixFlag;
    }

    /**
     * @param prefixFlag
     *            the prefixFlag to set
     */
    public void setPrefixFlag(String prefixFlag) {
        this.prefixFlag = prefixFlag;
    }

    /**
     * @return the unitId
     */
    public Long getUnitId() {
        return unitId;
    }

    /**
     * @param unitId
     *            the unitId to set
     */
    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

}
