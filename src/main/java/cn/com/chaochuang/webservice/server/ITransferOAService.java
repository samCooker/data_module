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
     * 获取待办事宜信息
     *
     * @param forDoId
     * @param oaPendingHandleDtsId
     * @return
     */
    @WebResult(name = "out", targetNamespace = "http://transfer.server.webservice.spower.com")
    @WebMethod
    String selectPendingItemInfo(String lastOutputTime, String oaPendingHandleDtsId);

    /**
     * 获取公文办理数据
     *
     * @param jsonStr
     *            参数Json
     * @return
     */
    @WebResult(name = "out", targetNamespace = "http://transfer.server.webservice.spower.com")
    @WebMethod
    String getDocTransactInfo(String lastOutputTime);

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
     * 获取公共通讯录数据
     *
     * @return
     */
    @WebResult(name = "out", targetNamespace = "http://transfer.server.webservice.spower.com")
    @WebMethod
    String getDepLinkmanInfo();

    /**
     * 重置用户密码
     *
     * @param userId
     *            用户编号
     * @param newPass
     *            新密码
     * @return
     */
    @WebResult(name = "out", targetNamespace = "http://transfer.server.webservice.spower.com")
    @WebMethod
    String setUserPassInfo(Long userId, String newPass);

    /**
     * 获取OA公告信息
     *
     * @param lastOutputTime
     *            最后数据导入时间
     * @return
     */
    @WebResult(name = "out", targetNamespace = "http://transfer.server.webservice.spower.com")
    @WebMethod
    String getPublicDataInfo(String lastOutputTime);

    /**
     * 获取公文的附件
     *
     * @param fileName
     * @param offset
     * @param reads
     * @return
     */
    @WebResult(name = "out", targetNamespace = "http://transfer.server.webservice.spower.com")
    @WebMethod
    byte[] uploadStreamAttachFile(String fileName, Long offset, Integer reads);

    /**
     * 获取OA的改变数据
     *
     * @param lastOutputTime
     * @return
     */
    @WebResult(name = "out", targetNamespace = "http://transfer.server.webservice.spower.com")
    @WebMethod
    String getDataChange();
}
