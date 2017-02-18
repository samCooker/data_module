package dbdata.aipcase.domain;



import cn.com.chaochuang.common.data.domain.LongIdEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "case_law_id")) })
public class AipCaseLaw extends LongIdEntity {
    /**  */
    private static final long serialVersionUID = 602563339802381396L;

    /** 案件基本信息 */
    private Long caseApplyId;
    /** 文书内容编号 */
    private Long contentId;
    /** 案由对象 */
    @OneToOne
    @JoinColumn(name = "case_cause_id")
    private AipCaseCause      aipCaseCause;
    /** 案件名称 */
    private String caseName;
    /** 案件基本描述 */
    private String caseBrief;
    /** 立案状态 */
    private String lawStatus;
    /** 经办人 */
    private String handleMan;
    /** 立案号 */
    private String caseNum;
    /** 记录时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordDate;
    /** 创建人编号 */
    private Long createrId;
    /** 创建人姓名 */
    private String createrName;
    /** 创建人部门 */
    private Long createrDeptId;
    /** 结案状态 */
    private String     finishFlag;
    /** 结案时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date finishLawTime;
    /** 立案时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date lawTime;
    /** 处罚时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date punishTime;
    /** 归档日期 */
    private Date filingDate;
    /** 档案归类 */
    private String filingType;
    /** 归档保存期限 */
    private String filingLimit;
    /** 执行结果 */
    private String executeResult;
    /** 结案方式 */
    private String       executeType;
    /** 处罚决定书 */
    private String punishDecision;

    /** 限办天数 */
    private Long limitDays;

    /** 办理时限 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date limitDate;

    /**
     * @return the limitDate
     */
    public Date getLimitDate() {
        return limitDate;
    }

    /**
     * @param limitDate
     *            the limitDate to set
     */
    public void setLimitDate(Date limitDate) {
        this.limitDate = limitDate;
    }

    /**
     * @return the caseApplyId
     */
    public Long getCaseApplyId() {
        return caseApplyId;
    }

    /**
     * @param caseApplyId
     *            the caseApplyId to set
     */
    public void setCaseApplyId(Long caseApplyId) {
        this.caseApplyId = caseApplyId;
    }

    /**
     * @return the contentId
     */
    public Long getContentId() {
        return contentId;
    }

    /**
     * @param contentId
     *            the contentId to set
     */
    public void setContentId(Long contentId) {
        this.contentId = contentId;
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
     * @return the caseBrief
     */
    public String getCaseBrief() {
        return caseBrief;
    }

    /**
     * @param caseBrief
     *            the caseBrief to set
     */
    public void setCaseBrief(String caseBrief) {
        this.caseBrief = caseBrief;
    }

    /**
     * @return the lawStatus
     */
    public String getLawStatus() {
        return lawStatus;
    }

    /**
     * @param lawStatus
     *            the lawStatus to set
     */
    public void setLawStatus(String lawStatus) {
        this.lawStatus = lawStatus;
    }

    /**
     * @return the handleMan
     */
    public String getHandleMan() {
        return handleMan;
    }

    /**
     * @param handleMan
     *            the handleMan to set
     */
    public void setHandleMan(String handleMan) {
        this.handleMan = handleMan;
    }

    /**
     * @return the caseNum
     */
    public String getCaseNum() {
        return caseNum;
    }

    /**
     * @param caseNum
     *            the caseNum to set
     */
    public void setCaseNum(String caseNum) {
        this.caseNum = caseNum;
    }

    /**
     * @return the recordDate
     */
    public Date getRecordDate() {
        return recordDate;
    }

    /**
     * @param recordDate
     *            the recordDate to set
     */
    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
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
     * @return the createrName
     */
    public String getCreaterName() {
        return createrName;
    }

    /**
     * @param createrName
     *            the createrName to set
     */
    public void setCreaterName(String createrName) {
        this.createrName = createrName;
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
     * @return the finishLawTime
     */
    public Date getFinishLawTime() {
        return finishLawTime;
    }

    /**
     * @param finishLawTime
     *            the finishLawTime to set
     */
    public void setFinishLawTime(Date finishLawTime) {
        this.finishLawTime = finishLawTime;
    }

    /**
     * @return the lawTime
     */
    public Date getLawTime() {
        return lawTime;
    }

    /**
     * @param lawTime
     *            the lawTime to set
     */
    public void setLawTime(Date lawTime) {
        this.lawTime = lawTime;
    }

    /**
     * @return the punishTime
     */
    public Date getPunishTime() {
        return punishTime;
    }

    /**
     * @param punishTime
     *            the punishTime to set
     */
    public void setPunishTime(Date punishTime) {
        this.punishTime = punishTime;
    }

    /**
     * @return the filingDate
     */
    public Date getFilingDate() {
        return filingDate;
    }

    /**
     * @param filingDate
     *            the filingDate to set
     */
    public void setFilingDate(Date filingDate) {
        this.filingDate = filingDate;
    }

    /**
     * @return the filingType
     */
    public String getFilingType() {
        return filingType;
    }

    /**
     * @param filingType
     *            the filingType to set
     */
    public void setFilingType(String filingType) {
        this.filingType = filingType;
    }

    /**
     * @return the filingLimit
     */
    public String getFilingLimit() {
        return filingLimit;
    }

    /**
     * @param filingLimit
     *            the filingLimit to set
     */
    public void setFilingLimit(String filingLimit) {
        this.filingLimit = filingLimit;
    }

    /**
     * @return the executeResult
     */
    public String getExecuteResult() {
        return executeResult;
    }

    /**
     * @param executeResult
     *            the executeResult to set
     */
    public void setExecuteResult(String executeResult) {
        this.executeResult = executeResult;
    }


    /**
     * @return the punishDecision
     */
    public String getPunishDecision() {
        return punishDecision;
    }

    /**
     * @param punishDecision
     *            the punishDecision to set
     */
    public void setPunishDecision(String punishDecision) {
        this.punishDecision = punishDecision;
    }

    /**
     * @return the limitDays
     */
    public Long getLimitDays() {
        return limitDays;
    }

    /**
     * @param limitDays
     *            the limitDays to set
     */
    public void setLimitDays(Long limitDays) {
        this.limitDays = limitDays;
    }

    public AipCaseCause getAipCaseCause() {
        return aipCaseCause;
    }

    public void setAipCaseCause(AipCaseCause aipCaseCause) {
        this.aipCaseCause = aipCaseCause;
    }

    public String getFinishFlag() {
        return finishFlag;
    }

    public void setFinishFlag(String finishFlag) {
        this.finishFlag = finishFlag;
    }

    public String getExecuteType() {
        return executeType;
    }

    public void setExecuteType(String executeType) {
        this.executeType = executeType;
    }
}