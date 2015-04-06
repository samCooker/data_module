/*
 * FileName:    PubInfo.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月19日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.task.bean;

import java.util.Date;

/**
 * @author frt
 *
 */

public class PubInfoBean {

    /** 公共信息类别 */
    private String pubInfoType;
    /** 发布人名称 */
    private String publishName;
    /** 发布时间 */
    private Date   publishTime;
    /** 信息标题 */
    private String title;
    /** 内容 */
    private String content;
    /** 发布单位 */
    private String publishDep;
    /** 发布范围编号 */
    private Long   pubRangeId;
    /** 发布范围 */
    private String pubRange;
    /** 功能信息级别 */
    private String pubLevel;
    /** 数据导入时间 */
    private Date   inputDate;
    /** 过期标志 */
    private String expireFlag;

    /**
     * @return the pubInfoType
     */
    public String getPubInfoType() {
        return pubInfoType;
    }

    /**
     * @param pubInfoType
     *            the pubInfoType to set
     */
    public void setPubInfoType(String pubInfoType) {
        this.pubInfoType = pubInfoType;
    }

    /**
     * @return the publishName
     */
    public String getPublishName() {
        return publishName;
    }

    /**
     * @param publishName
     *            the publishName to set
     */
    public void setPublishName(String publishName) {
        this.publishName = publishName;
    }

    /**
     * @return the publishTime
     */
    public Date getPublishTime() {
        return publishTime;
    }

    /**
     * @param publishTime
     *            the publishTime to set
     */
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
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
     * @return the publishDep
     */
    public String getPublishDep() {
        return publishDep;
    }

    /**
     * @param publishDep
     *            the publishDep to set
     */
    public void setPublishDep(String publishDep) {
        this.publishDep = publishDep;
    }

    /**
     * @return the pubRangeId
     */
    public Long getPubRangeId() {
        return pubRangeId;
    }

    /**
     * @param pubRangeId
     *            the pubRangeId to set
     */
    public void setPubRangeId(Long pubRangeId) {
        this.pubRangeId = pubRangeId;
    }

    /**
     * @return the pubRange
     */
    public String getPubRange() {
        return pubRange;
    }

    /**
     * @param pubRange
     *            the pubRange to set
     */
    public void setPubRange(String pubRange) {
        this.pubRange = pubRange;
    }

    /**
     * @return the pubLevel
     */
    public String getPubLevel() {
        return pubLevel;
    }

    /**
     * @param pubLevel
     *            the pubLevel to set
     */
    public void setPubLevel(String pubLevel) {
        this.pubLevel = pubLevel;
    }

    /**
     * @return the inputDate
     */
    public Date getInputDate() {
        return inputDate;
    }

    /**
     * @param inputDate
     *            the inputDate to set
     */
    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    /**
     * @return the expireFlag
     */
    public String getExpireFlag() {
        return expireFlag;
    }

    /**
     * @param expireFlag
     *            the expireFlag to set
     */
    public void setExpireFlag(String expireFlag) {
        this.expireFlag = expireFlag;
    }

}
