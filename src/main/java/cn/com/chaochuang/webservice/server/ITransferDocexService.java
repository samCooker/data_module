/*
 * FileName:    ITransferDocexService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2016
 * History:     2016年4月26日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.webservice.server;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * @author LLM
 *
 */
@WebService(targetNamespace = "http://webservice.docexpub.doc.spower.com")
public interface ITransferDocexService {
    /** 成功执行结果标志 */
    String TRANS_RESULT = "true";

    /**
     * 获取待办事宜信息
     *
     * @param forDoId
     * @param oaPendingHandleDtsId
     * @return
     */
    @WebResult(name = "out", targetNamespace = "http://webservice.docexpub.doc.spower.com")
    @WebMethod
    String selectPendingItemInfo(String lastOutputTime, String exRecId);

    /**
     * 提交公文传输办理数据
     *
     * @param jsonStr
     *            参数Json
     * @return
     */
    @WebResult(name = "out", targetNamespace = "http://webservice.docexpub.doc.spower.com")
    @WebMethod
    String setDocexTransactInfo(String jsonStr);

}
