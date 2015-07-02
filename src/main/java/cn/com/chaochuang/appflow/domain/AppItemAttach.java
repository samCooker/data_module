/*
 * FileName:    AppItemAttach.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年5月31日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.appflow.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.common.data.domain.LongIdEntity;

/**
 * @author LLM
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "attach_id")) })
public class AppItemAttach extends LongIdEntity {
    /**
     *
     */
    private static final long serialVersionUID = 2453637155776496271L;
    /** 原系统附件编号 */
    private Long              rmAttachId;
    /** 材料清单编号 */
    private Long              materialId;
    /** 申请项目编号 */
    private Long              itemApplyId;
    /** 附件保存名 */
    private String            saveName;
    /** 附件真实名 */
    private String            trueName;
    /** 附件保存路径 */
    private String            savePath;
    /** 附件大小 */
    private Long              fileSize;
    /** 是否是图像 */
    private String            isImage;
    /** 排序号 */
    private Long              showSort;
    /** 所属单位 */
    private Long              handleUnitId;
    /** 上传时间 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date              applyTime;
    /** 是否是本地数据 */
    private LocalData         localData;

    /**
     * @return the rmAttachId
     */
    public Long getRmAttachId() {
        return rmAttachId;
    }

    /**
     * @param rmAttachId
     *            the rmAttachId to set
     */
    public void setRmAttachId(Long rmAttachId) {
        this.rmAttachId = rmAttachId;
    }

    /**
     * @return the materialId
     */
    public Long getMaterialId() {
        return materialId;
    }

    /**
     * @param materialId
     *            the materialId to set
     */
    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    /**
     * @return the itemApplyId
     */
    public Long getItemApplyId() {
        return itemApplyId;
    }

    /**
     * @param itemApplyId
     *            the itemApplyId to set
     */
    public void setItemApplyId(Long itemApplyId) {
        this.itemApplyId = itemApplyId;
    }

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
     * @return the isImage
     */
    public String getIsImage() {
        return isImage;
    }

    /**
     * @param isImage
     *            the isImage to set
     */
    public void setIsImage(String isImage) {
        this.isImage = isImage;
    }

    /**
     * @return the showSort
     */
    public Long getShowSort() {
        return showSort;
    }

    /**
     * @param showSort
     *            the showSort to set
     */
    public void setShowSort(Long showSort) {
        this.showSort = showSort;
    }

    /**
     * @return the handleUnitId
     */
    public Long getHandleUnitId() {
        return handleUnitId;
    }

    /**
     * @param handleUnitId
     *            the handleUnitId to set
     */
    public void setHandleUnitId(Long handleUnitId) {
        this.handleUnitId = handleUnitId;
    }

    /**
     * @return the applyTime
     */
    public Date getApplyTime() {
        return applyTime;
    }

    /**
     * @param applyTime
     *            the applyTime to set
     */
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    /**
     * @return the localData
     */
    public LocalData getLocalData() {
        return localData;
    }

    /**
     * @param localData
     *            the localData to set
     */
    public void setLocalData(LocalData localData) {
        this.localData = localData;
    }

}
