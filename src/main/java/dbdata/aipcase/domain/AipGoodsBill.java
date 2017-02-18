package dbdata.aipcase.domain;

import cn.com.chaochuang.common.data.domain.LongIdEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "goods_bill_id") ) })
public class AipGoodsBill extends LongIdEntity {

    private static final long serialVersionUID = -5692557609868983072L;
    /** 案件基本情况编号 */
    private Long caseApplyId;
    /** 文书模板编号 */
    private Long noteModuleId;
    /** 文书内容编号 */
    private Long contentId;
    /** 关联决定书内容编号 */
    private Long relContentId;
    /** 案件状态 */
    private String        caseStatus;
    /** 品名 */
    private String goodsName;
    /** 生产厂家 */
    private String factory;
    /** 规格 */
    private String goodsSpec;
    /** 批号 */
    private String batch;
    /** 数量 */
    private Long quantity;
    /** 单价 */
    private Double price;
    /** 包装 */
    private String casing;
    /** 实施没收时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date execTime;
    /** 备注 */
    private String remark;
    /** 页号 */
    private Long pageIndex;
    /** 填写人编号 */
    private Long createrId;
    /** 填写时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    /** 执法人员签字时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date examSignDate;
    /** 当事人签字时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date contacterSignDate;
    /** 填写人所属单位编号 */
    private Long unitOrgId;
    /** 物品单位 */
    private String goodsUnit;
    /** 处理方式 */
    private String handleWay;
    /** 地点 */
    private String location;
    /** 经办人 */
    private String handleMan;

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
     * @return the caseStatus
     */
    public String getCaseStatus() {
        return caseStatus;
    }

    /**
     * @param caseStatus the caseStatus to set
     */
    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    /**
     * @return the goodsName
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * @param goodsName
     *            the goodsName to set
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * @return the factory
     */
    public String getFactory() {
        return factory;
    }

    /**
     * @param factory
     *            the factory to set
     */
    public void setFactory(String factory) {
        this.factory = factory;
    }

    /**
     * @return the goodsSpec
     */
    public String getGoodsSpec() {
        return goodsSpec;
    }

    /**
     * @param goodsSpec
     *            the goodsSpec to set
     */
    public void setGoodsSpec(String goodsSpec) {
        this.goodsSpec = goodsSpec;
    }

    /**
     * @return the batch
     */
    public String getBatch() {
        return batch;
    }

    /**
     * @param batch
     *            the batch to set
     */
    public void setBatch(String batch) {
        this.batch = batch;
    }

    /**
     * @return the quantity
     */
    public Long getQuantity() {
        return quantity;
    }

    /**
     * @param quantity
     *            the quantity to set
     */
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price
     *            the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return the casing
     */
    public String getCasing() {
        return casing;
    }

    /**
     * @param casing
     *            the casing to set
     */
    public void setCasing(String casing) {
        this.casing = casing;
    }

    /**
     * @return the execTime
     */
    public Date getExecTime() {
        return execTime;
    }

    /**
     * @param execTime
     *            the execTime to set
     */
    public void setExecTime(Date execTime) {
        this.execTime = execTime;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     *            the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return the pageIndex
     */
    public Long getPageIndex() {
        return pageIndex;
    }

    /**
     * @param pageIndex
     *            the pageIndex to set
     */
    public void setPageIndex(Long pageIndex) {
        this.pageIndex = pageIndex;
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
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the examSignDate
     */
    public Date getExamSignDate() {
        return examSignDate;
    }

    /**
     * @param examSignDate
     *            the examSignDate to set
     */
    public void setExamSignDate(Date examSignDate) {
        this.examSignDate = examSignDate;
    }

    /**
     * @return the contacterSignDate
     */
    public Date getContacterSignDate() {
        return contacterSignDate;
    }

    /**
     * @param contacterSignDate
     *            the contacterSignDate to set
     */
    public void setContacterSignDate(Date contacterSignDate) {
        this.contacterSignDate = contacterSignDate;
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
     * @return the goodsUnit
     */
    public String getGoodsUnit() {
        return goodsUnit;
    }

    /**
     * @param goodsUnit
     *            the goodsUnit to set
     */
    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    /**
     * @return the handleWay
     */
    public String getHandleWay() {
        return handleWay;
    }

    /**
     * @param handleWay
     *            the handleWay to set
     */
    public void setHandleWay(String handleWay) {
        this.handleWay = handleWay;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location
     *            the location to set
     */
    public void setLocation(String location) {
        this.location = location;
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
     * @return the relContentId
     */
    public Long getRelContentId() {
        return relContentId;
    }

    /**
     * @param relContentId
     *            the relContentId to set
     */
    public void setRelContentId(Long relContentId) {
        this.relContentId = relContentId;
    }

}