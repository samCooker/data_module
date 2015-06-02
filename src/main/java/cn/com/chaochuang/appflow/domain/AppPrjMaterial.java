/*
 * FileName:    AppPrjMaterial.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年5月31日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.appflow.domain;

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
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "material_id")) })
public class AppPrjMaterial extends LongIdEntity {
    /** 原系统材料清单分类编号 */
    private Long   rmMaterialId;
    /** 材料清单分类名称 */
    private String materialName;
    /** 材料清单对应审批项编号 */
    private Long   prjId;
    /** 显示标识 */
    private String typeFlag;
    /** 排序号 */
    private Long   showSort;
    /** 是否有效 */
    private String validFlag;
    /** 必须上传 */
    private String needUploadFlag;
    /** 才来大类名称 */
    private String materialNameType;

    /**
     * @return the rmMaterialId
     */
    public Long getRmMaterialId() {
        return rmMaterialId;
    }

    /**
     * @param rmMaterialId
     *            the rmMaterialId to set
     */
    public void setRmMaterialId(Long rmMaterialId) {
        this.rmMaterialId = rmMaterialId;
    }

    /**
     * @return the materialName
     */
    public String getMaterialName() {
        return materialName;
    }

    /**
     * @param materialName
     *            the materialName to set
     */
    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    /**
     * @return the prjId
     */
    public Long getPrjId() {
        return prjId;
    }

    /**
     * @param prjId
     *            the prjId to set
     */
    public void setPrjId(Long prjId) {
        this.prjId = prjId;
    }

    /**
     * @return the typeFlag
     */
    public String getTypeFlag() {
        return typeFlag;
    }

    /**
     * @param typeFlag
     *            the typeFlag to set
     */
    public void setTypeFlag(String typeFlag) {
        this.typeFlag = typeFlag;
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
     * @return the validFlag
     */
    public String getValidFlag() {
        return validFlag;
    }

    /**
     * @param validFlag
     *            the validFlag to set
     */
    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    /**
     * @return the needUploadFlag
     */
    public String getNeedUploadFlag() {
        return needUploadFlag;
    }

    /**
     * @param needUploadFlag
     *            the needUploadFlag to set
     */
    public void setNeedUploadFlag(String needUploadFlag) {
        this.needUploadFlag = needUploadFlag;
    }

    /**
     * @return the materialNameType
     */
    public String getMaterialNameType() {
        return materialNameType;
    }

    /**
     * @param materialNameType
     *            the materialNameType to set
     */
    public void setMaterialNameType(String materialNameType) {
        this.materialNameType = materialNameType;
    }

}
