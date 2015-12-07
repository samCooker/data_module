/*
 * FileName:    EmSiteReportInfo.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年12月4日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.emergency.bean;

import java.util.Date;
import java.util.List;

import cn.com.chaochuang.emergency.domain.EmAffix;

/**
 * @author LLM
 *
 */
public class EmSiteReportInfo {
    /** 原系统流水号 */
    private Long          rmReportId;
    /** 标题 */
    private String        title;
    /** 汇报内容 */
    private String        content;
    /** 汇报人编号 */
    private Long          promerId;
    /** 汇报单位编号 */
    private Long          promOrg;
    /** 汇报单位名称 */
    private String        promOrgName;
    /** 汇报日期 */
    private Date          promTime;
    /** 附件编号 */
    private String        rmAffixId;
    /** 附件列表 */
    private List<EmAffix> affixs;

    /**
     * @return the rmReportId
     */
    public Long getRmReportId() {
        return rmReportId;
    }

    /**
     * @param rmReportId
     *            the rmReportId to set
     */
    public void setRmReportId(Long rmReportId) {
        this.rmReportId = rmReportId;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     *            the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the promerId
     */
    public Long getPromerId() {
        return promerId;
    }

    /**
     * @param promerId
     *            the promerId to set
     */
    public void setPromerId(Long promerId) {
        this.promerId = promerId;
    }

    /**
     * @return the promOrg
     */
    public Long getPromOrg() {
        return promOrg;
    }

    /**
     * @param promOrg
     *            the promOrg to set
     */
    public void setPromOrg(Long promOrg) {
        this.promOrg = promOrg;
    }

    /**
     * @return the promOrgName
     */
    public String getPromOrgName() {
        return promOrgName;
    }

    /**
     * @param promOrgName
     *            the promOrgName to set
     */
    public void setPromOrgName(String promOrgName) {
        this.promOrgName = promOrgName;
    }

    /**
     * @return the promTime
     */
    public Date getPromTime() {
        return promTime;
    }

    /**
     * @param promTime
     *            the promTime to set
     */
    public void setPromTime(Date promTime) {
        this.promTime = promTime;
    }

    /**
     * @return the rmAffixId
     */
    public String getRmAffixId() {
        return rmAffixId;
    }

    /**
     * @param rmAffixId
     *            the rmAffixId to set
     */
    public void setRmAffixId(String rmAffixId) {
        this.rmAffixId = rmAffixId;
    }

    /**
     * @return the affixs
     */
    public List<EmAffix> getAffixs() {
        return affixs;
    }

    /**
     * @param affixs
     *            the affixs to set
     */
    public void setAffixs(List<EmAffix> affixs) {
        this.affixs = affixs;
    }

}
