/*
 * FileName:    SuperviseWebService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年5月28日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.webservice.server;

import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * @author LLM
 *
 */
@WebService(targetNamespace = "http://server.webservice.supervise.mobile.sbt.com/")
public interface SuperviseWebService {
    /**
     * 根据指定的时间或待办编号获取待办事宜
     *
     * @param lastOutputTime
     * @param pendingHandleId
     * @return
     */
    @WebResult(targetNamespace = "http://server.webservice.supervise.mobile.sbt.com/")
    @WebMethod
    String selectPendingHandleList(Date lastOutputTime, Long pendingHandleId);

    /**
     * 根据待办事宜编号获取对应的审批数据
     *
     * @param pendingHandleIds
     *            待办事宜编号字符串集合按"，"分隔
     * @return AppFlowShowData列表的json字符串
     */
    @WebResult(targetNamespace = "http://server.webservice.supervise.mobile.sbt.com/")
    @WebMethod
    String selectAppItemApplyDates(String pendingHandleIds);
}
