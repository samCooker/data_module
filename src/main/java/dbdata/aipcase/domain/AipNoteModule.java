/*
 * FileName:    AipNoteModule.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年1月8日 (LLM) 1.0 Create
 */

package dbdata.aipcase.domain;

import cn.com.chaochuang.common.data.domain.LongIdEntity;

import javax.persistence.*;

/**
 * @author LLM
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "note_module_id")) })
public class AipNoteModule extends LongIdEntity {
    /** 登记表模板名称 */
    public static final String REG_MODULE_NAME                  = "案件来源登记表";
    /** 立案审批表 名称 */
    public static final String LAW_CONTENT_NAME                 = "立案审批表";
    /** （查封（扣押））审批表 名称 */
    public static final String DETAIN_CONTENT_NAME              = "（查封（扣押））审批表";
    /** （延期查封（扣押））审批表 名称 */
    public static final String POSTPONE_DETAIN_CONTENT_NAME     = "（延期查封（扣押））审批表";
    /** （查封（扣押））物品清单 */
    public static final String GOODSLIST_CONTENT_NAME           = "（查封（扣押））物品清单";
    /** 查封（扣押）决定书 */
    public static final String DETAIN_DECISION_CONTENT_NAME     = "查封（扣押）决定书";
    /** （先行登记保存物品处理）物品清单 */
    public static final String PRE_REG_GOODSDEAL_CONTENT_NAME   = "（先行登记保存物品处理）物品清单";
    /** （先行登记保存）物品清单 */
    public static final String PRE_REG_GOODS_CONTENT_NAME       = "（先行登记保存）物品清单";
    /** （解除查封（扣押））物品清单 */
    public static final String REMOVE_DETAIN_GOODS_CONTENT_NAME = "（解除查封（扣押））物品清单";
    /** （没收物品）物品清单 */
    public static final String SEIZE_GOODS_CONTENT_NAME         = "（没收物品）物品清单";
    /** 没收物品处理清单 */
    public static final String SEIZE_GOODSDEAL_CONTENT_NAME     = "没收物品处理清单";
    /** （先行处理物品）物品清单 */
    public static final String PRE_GOODSDEAL_CONTENT_NAME       = "（先行处理物品）物品清单";
    /** 询问调查笔录 */
    public static final String LIVE_INVEST_CONTENT_NAME         = "询问调查笔录";
    /** 现场检查笔录 */
    public static final String LIVE_CHECK_CONTENT_NAME          = "现场检查笔录";
    /** 行政处罚结案报告 名称 */
    public static final String CASE_FINISH_NAME                 = "行政处罚结案报告";
    /** 案件调查终结报告 */
    public static final String INVESTIGATION_CONTENT_NAME       = "案件调查终结报告";
    /** 行政处罚决定书 名称 */
    public static final String PUNISH_CONTENT_NAME              = "行政处罚决定书";
    /** 当场行政处罚决定书名称 */
    public static final String LIVE_PUNISH_CONTENT_NAME         = "当场行政处罚决定书";
    /** 证据提取单 名称 */
    public static final String EVIDENCE_FILE_NAME               = "证据提取单";
    /** 先行处理物品通知书 */
    public static final String MODULE_NAME_XXCLWPTZS            = "先行处理物品通知书";
    /** 先行登记保存物品通知书 */
    public static final String MODULE_NAME_XXDJBCWPTZS          = "先行登记保存物品通知书";
    /** 解除查封（扣押）决定书 */
    public static final String MODULE_NAME_JCCFKYJDS            = "解除查封（扣押）决定书";
    /** 查封（扣押）延期通知书 */
    public static final String MODULE_NAME_CFKYYQTZS            = "查封（扣押）延期通知书";
    /** 先行登记保存物品处理决定书 */
    public static final String MODULE_NAME_XXDJBCWPCLJDS        = "先行登记保存物品处理决定书";
    /** 检验（检测、检疫、鉴定）告知书 */
    public static final String MODULE_NAME_JYGZS                = "检验（检测、检疫、鉴定）告知书";
    /** 责令改正通知书 */
    public static final String MODULE_NAME_ZLGZTZS              = "责令改正通知书";
    /** 行政处罚事先告知书 */
    public static final String MODULE_NAME_XZCFSXGZS            = "行政处罚事先告知书";
    /** 听证告知书 */
    public static final String MODULE_NAME_TZGZS                = "听证告知书";
    /** 听证通知书 */
    public static final String MODULE_NAME_TZTZS                = "听证通知书";
    /** 听证意见书 */
    public static final String MODULE_NAME_TZYJS                = "听证意见书";
    /** 听证笔录 */
    public static final String MODULE_NAME_TZBL                 = "听证笔录";
    /** 涉嫌犯罪案件移送书 */
    public static final String MODULE_NAME_SXFZAJYSS            = "涉嫌犯罪案件移送书";
    /** 案件移送审批表 */
    public static final String MODULE_NAME_AJYSSPB              = "案件移送审批表";
    /** （延长办案期限）审批表 */
    public static final String MODULE_NAME_YCBAQXSPB            = "（延长办案期限）审批表";
    /** （案件终止调查）审批表 */
    public static final String MODULE_NAME_AJZZDCSPB            = "（案件终止调查）审批表";
    /** 撤案审批表 */
    public static final String MODULE_NAME_CASPB                = "撤案审批表";
    /** 案件移送书 */
    public static final String MODULE_NAME_AJYSS                = "案件移送书";
    /** 查封（扣押）物品移交通知书 */
    public static final String MODULE_NAME_CFKYWPYJTZS          = "查封（扣押）物品移交通知书";
    /** 没收物品凭证 */
    public static final String MODULE_NAME_MSWPPZ               = "没收物品凭证";
    /** 案件集体讨论记录 */
    public static final String MODULE_NAME_AJJTTLJL             = "案件集体讨论记录";
    /** 案件合议记录 */
    public static final String MODULE_NAME_AJHYJL               = "案件合议记录";
    /** 陈述申辩笔录 */
    public static final String MODULE_NAME_CSSBBL               = "陈述申辩笔录";
    /** 陈述申辩复核意见书 */
    public static final String MODULE_NAME_CSSBFHYJS            = "陈述申辩复核意见书";
    /** 行政处罚决定审批表 */
    public static final String MODULE_NAME_XZCFJDSPB            = "行政处罚决定审批表";
    private static final long  serialVersionUID                 = -5712953196285607117L;

    private Long noteTypeId;
    private String noteName;
    private String noteContent;
    private String lawContent;
    private String notePostface;
    private String printFormat;
    private String printPaperDirc;
    private String pageHeader;
    private String pageFooter;
    private String notePreface;
    /** 文书模版路径 */
    private String editView;
    private String printPaperSize;
    private String printerSetting;
    private Long sort;
    private String noteTypeObj;
    /** 文书模板 */
    private String         moduleType;
    /** 案件来源类型 */
    @OneToOne
    @JoinColumn(name = "reference_num_id")
    private AipReferenceNum    aipReferenceNum;

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public Long getNoteTypeId() {
        return noteTypeId;
    }

    public void setNoteTypeId(Long noteTypeId) {
        this.noteTypeId = noteTypeId;
    }

    public String getNoteName() {
        return this.noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public String getNoteContent() {
        return this.noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public String getLawContent() {
        return this.lawContent;
    }

    public void setLawContent(String lawContent) {
        this.lawContent = lawContent;
    }

    public String getNotePostface() {
        return this.notePostface;
    }

    public void setNotePostface(String notePostface) {
        this.notePostface = notePostface;
    }

    public String getPrintFormat() {
        return this.printFormat;
    }

    public void setPrintFormat(String printFormat) {
        this.printFormat = printFormat;
    }

    public String getPrintPaperDirc() {
        return this.printPaperDirc;
    }

    public void setPrintPaperDirc(String printPaperDirc) {
        this.printPaperDirc = printPaperDirc;
    }

    public String getPageHeader() {
        return this.pageHeader;
    }

    public void setPageHeader(String pageHeader) {
        this.pageHeader = pageHeader;
    }

    public String getPageFooter() {
        return this.pageFooter;
    }

    public void setPageFooter(String pageFooter) {
        this.pageFooter = pageFooter;
    }

    public String getNotePreface() {
        return this.notePreface;
    }

    public void setNotePreface(String notePreface) {
        this.notePreface = notePreface;
    }

    public String getEditView() {
        return this.editView;
    }

    public void setEditView(String editView) {
        this.editView = editView;
    }

    public String getPrintPaperSize() {
        return this.printPaperSize;
    }

    public void setPrintPaperSize(String printPaperSize) {
        this.printPaperSize = printPaperSize;
    }

    public String getPrinterSetting() {
        return this.printerSetting;
    }

    public void setPrinterSetting(String printerSetting) {
        this.printerSetting = printerSetting;
    }

    public Long getSort() {
        return this.sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public String getNoteTypeObj() {
        return this.noteTypeObj;
    }

    public void setNoteTypeObj(String noteTypeObj) {
        this.noteTypeObj = noteTypeObj;
    }

    /**
     * @return the aipReferenceNum
     */
    public AipReferenceNum getAipReferenceNum() {
        return aipReferenceNum;
    }

    /**
     * @param aipReferenceNum
     *            the aipReferenceNum to set
     */
    public void setAipReferenceNum(AipReferenceNum aipReferenceNum) {
        this.aipReferenceNum = aipReferenceNum;
    }

}
