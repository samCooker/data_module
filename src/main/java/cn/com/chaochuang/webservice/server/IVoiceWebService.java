/*
 * FileName:    IVoiceWebService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月7日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.webservice.server;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * @author LLM
 *
 */
@WebService(targetNamespace = "http://webservice.mobile.spower.com")
public interface IVoiceWebService {
    /**
     * 获取新加舆情
     *
     * @param lastOutputTime
     * @param pendingInfoId
     * @return
     */
    @WebResult(name = "out", targetNamespace = "http://webservice.mobile.spower.com")
    @WebMethod
    String selectPendingVoiceInfo(String lastOutputTime, String pendingInfoId);

    /**
     * 获取指定ID的舆情
     *
     * @param infoId
     * @return
     */
    @WebResult(name = "out", targetNamespace = "http://webservice.mobile.spower.com")
    @WebMethod
    String selectVoiceInfo(Long infoId);

    /**
     * 保存舆情到事件
     *
     * @param jsonStr
     * @return
     */
    @WebResult(name = "out", targetNamespace = "http://webservice.mobile.spower.com")
    @WebMethod
    String saveVoiceInfoEvent(String jsonStr);

    /**
     * 获取新加舆情事件
     *
     * @param lastOutputTime
     * @param eventId
     * @return
     */
    @WebResult(name = "out", targetNamespace = "http://webservice.mobile.spower.com")
    @WebMethod
    String selectVoiceEventPending(String lastOutputTime, String eventId);

    /**
     * 更新舆情事件审批数据
     *
     * @param jsonStr
     * @return
     */
    @WebResult(name = "out", targetNamespace = "http://webservice.mobile.spower.com")
    @WebMethod
    String setVoiceEventHandleInfo(String jsonStr);

    /**
     * 获取数据变动记录
     *
     * @return
     */
    @WebResult(name = "out", targetNamespace = "http://webservice.mobile.spower.com")
    @WebMethod
    String getDataChange();

    /**
     * 获取指定ID的舆情事件
     *
     * @param eventId
     * @return
     */
    @WebResult(name = "out", targetNamespace = "http://webservice.mobile.spower.com")
    @WebMethod
    String selectVoiceEvent(Long eventId);

    /**
     * 获取舆情事件审批数据，将其转成json字符串格式
     *
     * @param handleManId
     * @return
     */
    @WebResult(name = "out", targetNamespace = "http://webservice.mobile.spower.com")
    @WebMethod
    String getVoiceEventHandleInfo(Long handleManId);

    /**
     * 获取舆情审批意见数据，将其转成json字符串格式
     *
     * @param handleApproveId
     * @return
     */
    @WebResult(name = "out", targetNamespace = "http://webservice.mobile.spower.com")
    @WebMethod
    String getVoiceEventHandleOpinion(Long handleApproveId);

    //
    // /**
    // * 获取指定的文件
    // *
    // * @param fileName
    // * @param offset
    // * @param reads
    // * @return
    // */
    // @WebResult(name = "out", targetNamespace = "http://webservice.mobile.spower.com")
    // @WebMethod
    // byte[] uploadStreamAttachFile(String fileName, Long offset, Integer reads);
}
