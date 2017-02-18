package dbdata.aipcase.domain;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import cn.com.chaochuang.common.data.domain.LongIdEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "content_id")) })
public class AipLawContent extends LongIdEntity {

    private static final long   serialVersionUID = 3435598344629367608L;

    /** 执法文书模板 */
    @ManyToOne
    @JoinColumn(name = "note_module_id")
    private AipNoteModule       aipNoteModule;
    /** 文书编号（当前自增序号） */
    private Long referenceCurNum;
    /** 文书编号（案件类型） */
    private String referenceCaseType;
    /** 文书编号（年份） */
    private Long referenceYearPer;
    /** 文书名称 */
    private String noteName;
    /** 执法内容 */
    private String lawContent;
    /** 文书关联码 */
    private String lawCode;
    /** 所属单位ID */
    private Long unitOrgId;
    /** 填写日期 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    /** 文书编号 */
    private String noteNum;
    /** 案件基本信息ID */
    private Long caseApplyId;
    /** 立案信息ID */
    private Long caseLawId;
    /** 案由编号 */
    private Long caseCauseId;
    /** 案由名称 */
    private String caseName;
    /** 案件状态 */
    private String          caseStatus;
    /** 页码字段 */
    private Long pageNum;
    /** 创建人对象 */
    private Long createrId;
    /** 创建人姓名 */
    private String createrName;
    /** 创建人部门编号 */
    private Long createrDeptId;
    /** 创建人部门名称 */
    private String createrDeptName;
    /** 是否临时文书 */
    private String tempNoteFlag;
    /** 离线文书临时流水号 */
    private String offlineTempId;
    /** 送达结果 */
    private String          sendResult;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "contentId")
    @NotFound(action = NotFoundAction.IGNORE)
    @OrderBy("id desc")
    private List<AipSupervise> supervises;

    /** 经度 */
    private Double longitude;
    /** 纬度 */
    private Double latitude;

    public String getCreaterName() {
        return createrName;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String getTempNoteFlag() {
        return tempNoteFlag;
    }

    public void setTempNoteFlag(String tempNoteFlag) {
        this.tempNoteFlag = tempNoteFlag;
    }

    public void setSendResult(String sendResult) {
        this.sendResult = sendResult;
    }

    public List<AipSupervise> getSupervises() {
        return supervises;
    }

    public void setSupervises(List<AipSupervise> supervises) {
        this.supervises = supervises;
    }

    public AipNoteModule getAipNoteModule() {
        return aipNoteModule;
    }

    public void setAipNoteModule(AipNoteModule aipNoteModule) {
        this.aipNoteModule = aipNoteModule;
    }

    public Long getReferenceCurNum() {
        return this.referenceCurNum;
    }

    public void setReferenceCurNum(Long referenceCurNum) {
        this.referenceCurNum = referenceCurNum;
    }

    public Long getReferenceYearPer() {
        return this.referenceYearPer;
    }

    public void setReferenceYearPer(Long referenceYearPer) {
        this.referenceYearPer = referenceYearPer;
    }

    public String getNoteName() {
        return this.noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public String getLawContent() {
        return this.lawContent;
    }

    public void setLawContent(String lawContent) {
        this.lawContent = lawContent;
    }

    public String getLawCode() {
        return this.lawCode;
    }

    public void setLawCode(String lawCode) {
        this.lawCode = lawCode;
    }

    public Long getUnitOrgId() {
        return this.unitOrgId;
    }

    public void setUnitOrgId(Long unitOrgId) {
        this.unitOrgId = unitOrgId;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getNoteNum() {
        return this.noteNum;
    }

    public void setNoteNum(String noteNum) {
        this.noteNum = noteNum;
    }

    public Long getCaseApplyId() {
        return this.caseApplyId;
    }

    public void setCaseApplyId(Long caseApplyId) {
        this.caseApplyId = caseApplyId;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public Long getPageNum() {
        return this.pageNum;
    }

    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * @return the createrId
     */
    public Long getCreaterId() {
        return createrId;
    }

    /**
     * @param createrId
     *            the createrId to set
     */
    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    /**
     * @return the referenceCaseType
     */
    public String getReferenceCaseType() {
        return referenceCaseType;
    }

    /**
     * @param referenceCaseType
     *            the referenceCaseType to set
     */
    public void setReferenceCaseType(String referenceCaseType) {
        this.referenceCaseType = referenceCaseType;
    }

    /**
     * @return the caseLawId
     */
    public Long getCaseLawId() {
        return caseLawId;
    }

    /**
     * @param caseLawId
     *            the caseLawId to set
     */
    public void setCaseLawId(Long caseLawId) {
        this.caseLawId = caseLawId;
    }

    public String getSendResult() {
        return sendResult;
    }

    /**
     * @return the caseCauseId
     */
    public Long getCaseCauseId() {
        return caseCauseId;
    }

    /**
     * @param caseCauseId
     *            the caseCauseId to set
     */
    public void setCaseCauseId(Long caseCauseId) {
        this.caseCauseId = caseCauseId;
    }

    /**
     * @return the caseName
     */
    public String getCaseName() {
        return caseName;
    }

    /**
     * @param caseName
     *            the caseName to set
     */
    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    /**
     * @return the offlineTempId
     */
    public String getOfflineTempId() {
        return offlineTempId;
    }

    /**
     * @param offlineTempId
     *            the offlineTempId to set
     */
    public void setOfflineTempId(String offlineTempId) {
        this.offlineTempId = offlineTempId;
    }

    /**
     * @return the createrDeptId
     */
    public Long getCreaterDeptId() {
        return createrDeptId;
    }

    /**
     * @param createrDeptId
     *            the createrDeptId to set
     */
    public void setCreaterDeptId(Long createrDeptId) {
        this.createrDeptId = createrDeptId;
    }

    /**
     * @return the createrDeptName
     */
    public String getCreaterDeptName() {
        return createrDeptName;
    }

    /**
     * @param createrDeptName
     *            the createrDeptName to set
     */
    public void setCreaterDeptName(String createrDeptName) {
        this.createrDeptName = createrDeptName;
    }

    /**
     * @param createrName
     *            the createrName to set
     */
    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }

    /**
     * @return the longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude
     *            the longitude to set
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude
     *            the latitude to set
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

}