/*
 * FileName:    ITransferEmService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年12月4日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.webservice.server;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * @author LLM
 *
 */
@WebService(targetNamespace = "http://server.webservice.mobile.em")
public interface ITransferEmService {
    /** 成功执行结果标志 */
    String TRANS_RESULT = "true";

    /**
     * 获取情况汇报记录
     *
     * @param reportId
     * @return
     */
    @WebResult(name = "out", targetNamespace = "http://server.webservice.mobile.em")
    @WebMethod
    String selectEmSiteReport(Long reportId);
}
