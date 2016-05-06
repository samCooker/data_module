/*
 * FileName:    SysDataChange.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2016
 * History:     2016年05月05日  (Shicx) 1.0 Create
 */

package cn.com.chaochuang.data.domain;

import cn.com.chaochuang.common.data.domain.LongIdEntity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Shicx
 *
 */
@SuppressWarnings("serial")
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "parameter_id")) })
public class PdfParameter extends LongIdEntity { 

	/***/
	private Float pageZoom;
	
	/***/
	private Integer currentPageNo;


	/**
	 * @return the currentPageNo
	 */
	public Integer getCurrentPageNo() {
		return currentPageNo;
	}

	/**
	 * @param currentPageNo the currentPageNo to set
	 */
	public void setCurrentPageNo(Integer currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	/**
	 * @return the pageZoom
	 */
	public Float getPageZoom() {
		return pageZoom;
	}

	/**
	 * @param pageZoom the pageZoom to set
	 */
	public void setPageZoom(Float pageZoom) {
		this.pageZoom = pageZoom;
	}
}

