package dbdata.aipcase.domain;

import cn.com.chaochuang.common.data.domain.LongIdEntity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "case_type_id")) })
public class AipCaseType extends LongIdEntity {

    private static final long serialVersionUID = 962119772267205459L;

    private String caseTypeName;
    private String caseTypeFlag;
    /** 排序值 */
    private Long sortOrder;
    /** 是否默认案件类型 */
    private String caseTypeDefault;

    public String getCaseTypeName() {
        return this.caseTypeName;
    }

    public void setCaseTypeName(String caseTypeName) {
        this.caseTypeName = caseTypeName;
    }

    public String getCaseTypeFlag() {
        return this.caseTypeFlag;
    }

    public void setCaseTypeFlag(String caseTypeFlag) {
        this.caseTypeFlag = caseTypeFlag;
    }

    /**
     * @return the sortOrder
     */
    public Long getSortOrder() {
        return sortOrder;
    }

    /**
     * @param sortOrder
     *            the sortOrder to set
     */
    public void setSortOrder(Long sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * @return the caseTypeDefault
     */
    public String getCaseTypeDefault() {
        return caseTypeDefault;
    }

    /**
     * @param caseTypeDefault
     *            the caseTypeDefault to set
     */
    public void setCaseTypeDefault(String caseTypeDefault) {
        this.caseTypeDefault = caseTypeDefault;
    }

}