/*
 * FileName:    PolicyAttach.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月25日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.commoninfo.domain;

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
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "attach_id")) })
public class PolicyAttach extends LongIdEntity {
    /** 保存名 */
    private String saveName;
    /** 附件大小 */
    private Long   fileSize;
    /** 文件真实名 */
    private String trueName;
    /** 保存路径 */
    private String savePath;
    /** 法规编号 */
    private Long   rmPolicyId;

    /**
     * @return the saveName
     */
    public String getSaveName() {
        return saveName;
    }

    /**
     * @param saveName
     *            the saveName to set
     */
    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    /**
     * @return the fileSize
     */
    public Long getFileSize() {
        return fileSize;
    }

    /**
     * @param fileSize
     *            the fileSize to set
     */
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * @return the trueName
     */
    public String getTrueName() {
        return trueName;
    }

    /**
     * @param trueName
     *            the trueName to set
     */
    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    /**
     * @return the savePath
     */
    public String getSavePath() {
        return savePath;
    }

    /**
     * @param savePath
     *            the savePath to set
     */
    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    /**
     * @return the rmPolicyId
     */
    public Long getRmPolicyId() {
        return rmPolicyId;
    }

    /**
     * @param rmPolicyId
     *            the rmPolicyId to set
     */
    public void setRmPolicyId(Long rmPolicyId) {
        this.rmPolicyId = rmPolicyId;
    }

}
