package dbdata.aipcase.domain;


import cn.com.chaochuang.common.data.domain.LongIdEntity;

import javax.persistence.*;

@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "reference_num_id")) })
public class AipReferenceNum extends LongIdEntity {

    private static final long serialVersionUID = 4679593656374418466L;

    /** 文书编号前缀对象 */
    @OneToOne
    @JoinColumn(name = "note_prefix_id")
    private AipNotePrefix     aipNotePrefix;
    private String referenceFormat;
    private Long unitId;

    public String getReferenceFormat() {
        return this.referenceFormat;
    }

    public void setReferenceFormat(String referenceFormat) {
        this.referenceFormat = referenceFormat;
    }

    public Long getUnitId() {
        return this.unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    /**
     * @return the aipNotePrefix
     */
    public AipNotePrefix getAipNotePrefix() {
        return aipNotePrefix;
    }

    /**
     * @param aipNotePrefix
     *            the aipNotePrefix to set
     */
    public void setAipNotePrefix(AipNotePrefix aipNotePrefix) {
        this.aipNotePrefix = aipNotePrefix;
    }

}