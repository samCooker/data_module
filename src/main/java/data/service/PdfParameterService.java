/*
 * FileName:    SysDataChange.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2016
 * History:     2016年05月05日  (Shicx) 1.0 Create
 */

package data.service;


import data.bean.SaveBeanPdfPara;
import data.domain.PdfParameter;
import springcommon.jpa.CrudRestService;

import java.util.List;

/**
 * @author Shicx
 *
 */
public interface PdfParameterService extends CrudRestService<PdfParameter, Long> {


    boolean savePdfParameter(SaveBeanPdfPara saveBean);

    List<PdfParameter> getPdfParameterData();
}
