/*
 * FileName:    ITransferAipCaseService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年5月4日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.webservice.server.aipcasetransfer;

import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * @author LLM
 *
 */
@WebService(targetNamespace = "http://aipcasetransfer.server.webservice.chaochuang.com.cn/")
public interface AipCaseWebService {

    @WebResult(targetNamespace = "http://aipcasetransfer.server.webservice.chaochuang.com.cn/")
    @WebMethod
    String sayHellow(String saywhat);

    /**
     * 获取待办事宜
     *
     * @param lastOutputTime
     *            待办事宜获取时间
     * @param fordoId
     *            待办记录ID
     * @return
     */
    @WebResult(targetNamespace = "http://aipcasetransfer.server.webservice.chaochuang.com.cn/")
    @WebMethod
    String selectPendingItemInfo(Date lastOutputTime, String fordoId);

    /**
     * 获取案件基本信息
     *
     * @param lastOutputTime
     *            最后案件办理记录时间
     * @return
     */
    @WebResult(targetNamespace = "http://aipcasetransfer.server.webservice.chaochuang.com.cn/")
    @WebMethod
    String getAipCaseApply(String lastOutputTime);

    /**
     * 获取案件基本信息
     *
     * @param pendingHandleIds
     *            案件待办编号
     * @return 案件基本信息
     */
    @WebResult(targetNamespace = "http://aipcasetransfer.server.webservice.chaochuang.com.cn/")
    @WebMethod
    String selectAipCaseApplyDates(String pendingHandleIds);

    /**
     * 保存案件办理信息
     *
     * @param command
     * @return
     */
    @WebResult(targetNamespace = "http://aipcasetransfer.server.webservice.chaochuang.com.cn/")
    @WebMethod
    String saveAipCaseApprove(String command);

    /**
     * 获取文书信息
     * 
     * @param caseApplyId
     * @param dataMap
     * @return
     */
    @WebResult(targetNamespace = "http://aipcasetransfer.server.webservice.chaochuang.com.cn/")
    @WebMethod
    String getLawContentData(Long caseApplyId, String mdfCode);

    /**
     * 获取附件信息
     * 
     * @param fileName
     * @param offset
     * @param reads
     * @return
     */
    @WebResult(targetNamespace = "http://aipcasetransfer.server.webservice.chaochuang.com.cn/")
    @WebMethod
    byte[] uploadStreamAttachFile(String fileName, Long offset, Integer reads);
}
