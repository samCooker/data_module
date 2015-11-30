/*
 * FileName:    EmSiteReport.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年11月26日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.emergency.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;

import cn.com.chaochuang.common.data.domain.LongIdEntity;

/**
 * @author LLM
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "report_id")) })
public class EmSiteReport extends LongIdEntity {
    /** 原系统流水号 */
    private Long   rmReportId;
    /** 标题 */
    private String title;
    /** 汇报人 */
    private String promerName;
    /** 汇报单位编号 */
    private Long   promOrg;
    /** 汇报单位名称 */
    private String promOrgName;
    /** 汇报日期 */
    private Date   promTime;
    /** 附件编号 */
    private String rmAffixId;

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
     * @return the promerName
     */
    public String getPromerName() {
        return promerName;
    }

    /**
     * @param promerName
     *            the promerName to set
     */
    public void setPromerName(String promerName) {
        this.promerName = promerName;
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

}
