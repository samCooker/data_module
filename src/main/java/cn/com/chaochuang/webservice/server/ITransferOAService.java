/*
 * FileName:    ITransferOAService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2009
 * History:     2009-8-6 (LLM) 1.0 Create
 */
package cn.com.chaochuang.webservice.server;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * The <code>ITransferOAService</code>
 *
 * @author LLM
 * @version 1.0, 2009-8-6
 */
@WebService(targetNamespace = "http://transfer.server.webservice.spower.com")
public interface ITransferOAService {
    /** 成功执行结果标志 */
    String TRANS_RESULT = "true";

    /**
     * 获取通讯录数据
     *
     * @param receivePhone
     * @param content
     * @return
     */
    @WebResult(name = "out", targetNamespace = "http://transfer.server.webservice.spower.com")
    @WebMethod
    String sendSms(String receivePhone, String content);

    /**
     * 获取待办事宜信息
     *
     * @param forDoId
     * @return
     */
    @WebResult(name = "out", targetNamespace = "http://transfer.server.webservice.spower.com")
    @WebMethod
    String getPendingItemInfo(Long pendingId);

    /**
     * 获取公文数据
     *
     * @param jsonStr
     *            参数Json
     * @return
     */
    @WebResult(name = "out", targetNamespace = "http://transfer.server.webservice.spower.com")
    @WebMethod
    String setDocumentTransact(String jsonStr);

    /**
     * 提交公文办理数据
     *
     * @param jsonStr
     *            参数Json
     * @return
     */
    @WebResult(name = "out", targetNamespace = "http://transfer.server.webservice.spower.com")
    @WebMethod
    String setDocTransactInfo(String jsonStr);

    /**
     * 重置用户密码
     *
     * @param jsonStr
     *            参数Json
     * @return
     */
    @WebResult(name = "out", targetNamespace = "http://transfer.server.webservice.spower.com")
    @WebMethod
    String setSysMessageInfo(String jsonStr);

}
