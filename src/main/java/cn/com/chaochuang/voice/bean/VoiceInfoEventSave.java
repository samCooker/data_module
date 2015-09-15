/*
 * FileName:    VoiceInfoEventSave.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月14日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.voice.bean;

/**
 * @author LLM
 *
 */
public class VoiceInfoEventSave {
    /** 舆情信息编号 */
    private Long   infoId;
    /** 舆情性质标识 */
    private String voiceInfoNature;
    /** 舆情内容区域标识 */
    private String contentArea;
    /** 舆情事件编号 */
    private Long   eventId;
    /** 舆情信息编号 */
    private Long   gradeId;
    /** 舆情事件标题 */
    private String title;
    /** 舆情事件创建人 */
    private Long   createrId;
    /** 舆情事件创建时间 */
    private String createDate;

    /**
     * @return the infoId
     */
    public Long getInfoId() {
        return infoId;
    }

    /**
     * @param infoId
     *            the infoId to set
     */
    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    /**
     * @return the voiceInfoNature
     */
    public String getVoiceInfoNature() {
        return voiceInfoNature;
    }

    /**
     * @param voiceInfoNature
     *            the voiceInfoNature to set
     */
    public void setVoiceInfoNature(String voiceInfoNature) {
        this.voiceInfoNature = voiceInfoNature;
    }

    /**
     * @return the contentArea
     */
    public String getContentArea() {
        return contentArea;
    }

    /**
     * @param contentArea
     *            the contentArea to set
     */
    public void setContentArea(String contentArea) {
        this.contentArea = contentArea;
    }

    /**
     * @return the eventId
     */
    public Long getEventId() {
        return eventId;
    }

    /**
     * @param eventId
     *            the eventId to set
     */
    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    /**
     * @return the gradeId
     */
    public Long getGradeId() {
        return gradeId;
    }

    /**
     * @param gradeId
     *            the gradeId to set
     */
    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
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
    public String getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     *            the createDate to set
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

}
