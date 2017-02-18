package dbdata.aipcase.domain;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import cn.com.chaochuang.common.data.domain.LongIdEntity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "supervise_id")) })
public class AipSupervise extends LongIdEntity {

    private static final long             serialVersionUID    = -8476037493720506478L;
    /** 时间格式对象 */
    private static final SimpleDateFormat CH_DATE_TIME_FORMAT = new SimpleDateFormat("yyyy年MM月dd日");
    /** 案件基本号 */
    private Long caseApplyId;
    /** 案件状态 */
    private String                    caseStatus;
    /** 文书模板编号 */
    private Long noteModuleId;
    /** 文书内容编号 */
    private Long contentId;
    /** 审批意见 */
    private String superviseOpinion;
    /** 审批人 */
    private Long superviserId;
    /** 审批人姓名 */
    private String superviserName;
    /** 意见填写时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date superviseTime;
    /** 所属单位 */
    private Long unitOrgId;
    /** 审批人部门编号 */
    private Long superviserDeptId;
    /** 创建人 */
    private Long createrId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    private String createrName;
    private Long createrDeptId;
    /** 环节名称 */
    private String nodeName;
    /** 环节级别 */
    private String                     nodeLevel;
    /** 环节类型 */
    private String                      nodeType;
    /** 环节状态 */
    private String                    nodeStatus;
    /** 前一个审批 */
    private Long preSuperviseId;
    @OneToOne
    @JoinColumn(name = "preSuperviseId", insertable = false, updatable = false)
    @NotFound(action= NotFoundAction.IGNORE)
    private AipSupervise                  preSupervise;
    /** 流程实例编号 */
    private Long instId;

    /**
     * 构造函数
     */
    public AipSupervise() {
        this.createDate = new Date();
    }

    /**
     * @return
     */
    public String getFormatSuperviseTime() {
        if (superviseTime != null) {
            return CH_DATE_TIME_FORMAT.format(superviseTime);
        }
        return null;
    }

    /**
     * @return
     */
    public String geJtFormatCreateDate() {
        if (createDate != null) {
            return CH_DATE_TIME_FORMAT.format(createDate);
        }
        return null;
    }

    /**
     * @return the preSuperviseId
     */
    public Long getPreSuperviseId() {
        return preSuperviseId;
    }

    /**
     * @return the preSupervise
     */
    public AipSupervise getPreSupervise() {
        return preSupervise;
    }

    /**
     * @param preSupervise
     *            the preSupervise to set
     */
    public void setPreSupervise(AipSupervise preSupervise) {
        this.preSupervise = preSupervise;
    }

    /**
     * @param preSuperviseId
     *            the preSuperviseId to set
     */
    public void setPreSuperviseId(Long preSuperviseId) {
        this.preSuperviseId = preSuperviseId;
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
     * @return the noteModuleId
     */
    public Long getNoteModuleId() {
        return noteModuleId;
    }

    /**
     * @param noteModuleId
     *            the noteModuleId to set
     */
    public void setNoteModuleId(Long noteModuleId) {
        this.noteModuleId = noteModuleId;
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
     * @return the superviseOpinion
     */
    public String getSuperviseOpinion() {
        return superviseOpinion;
    }

    /**
     * @param superviseOpinion
     *            the superviseOpinion to set
     */
    public void setSuperviseOpinion(String superviseOpinion) {
        this.superviseOpinion = superviseOpinion;
    }

    /**
     * @return the superviserId
     */
    public Long getSuperviserId() {
        return superviserId;
    }

    /**
     * @param superviserId
     *            the superviserId to set
     */
    public void setSuperviserId(Long superviserId) {
        this.superviserId = superviserId;
    }

    /**
     * @return the superviserName
     */
    public String getSuperviserName() {
        return superviserName;
    }

    /**
     * @param superviserName
     *            the superviserName to set
     */
    public void setSuperviserName(String superviserName) {
        this.superviserName = superviserName;
    }

    /**
     * @return the superviseTime
     */
    public Date getSuperviseTime() {
        return superviseTime;
    }

    /**
     * @param superviseTime
     *            the superviseTime to set
     */
    public void setSuperviseTime(Date superviseTime) {
        this.superviseTime = superviseTime;
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
     * @return the superviserDeptId
     */
    public Long getSuperviserDeptId() {
        return superviserDeptId;
    }

    /**
     * @param superviserDeptId
     *            the superviserDeptId to set
     */
    public void setSuperviserDeptId(Long superviserDeptId) {
        this.superviserDeptId = superviserDeptId;
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
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     *            the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
     * @return the nodeName
     */
    public String getNodeName() {
        return nodeName;
    }

    /**
     * @param nodeName
     *            the nodeName to set
     */
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String getNodeLevel() {
        return nodeLevel;
    }

    public void setNodeLevel(String nodeLevel) {
        this.nodeLevel = nodeLevel;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getNodeStatus() {
        return nodeStatus;
    }

    public void setNodeStatus(String nodeStatus) {
        this.nodeStatus = nodeStatus;
    }

    /**
     * @return the instId
     */
    public Long getInstId() {
        return instId;
    }

    /**
     * @param instId
     *            the instId to set
     */
    public void setInstId(Long instId) {
        this.instId = instId;
    }

}