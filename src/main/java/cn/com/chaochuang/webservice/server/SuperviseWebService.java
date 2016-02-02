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
@WebService(targetNamespace = "http://server.webservice.supervise.mobile.sbt.com")
public interface SuperviseWebService {

    /**
     * 根据指定的时间或待办编号获取行政审批待办事宜
     *
     * @param lastOutputTime
     * @param pendingHandleId
     * @return
     */
    @WebResult(name = "out", targetNamespace = "http://server.webservice.supervise.mobile.sbt.com")
    @WebMethod
    String selectPendingHandleList(Date lastOutputTime, Long pendingHandleId);

    /**
     * 根据审批事项id查询审批数据
     *
     * @param itemApplyId
     * @return
     */
    @WebResult(name = "out", targetNamespace = "http://server.webservice.supervise.mobile.sbt.com")
    @WebMethod
    String getAppItemApplyDatesByItemApplyId(Long itemApplyId);

    /**
     * 根据指定的时间或待办编号获取审批查验待办事宜
     *
     * @param lastOutputTime
     * @param pendingHandleId
     * @return
     */
    @WebResult(name = "out", targetNamespace = "http://server.webservice.supervise.mobile.sbt.com")
    @WebMethod
    String selectAuditPendingHandleList(Date lastOutputTime, Long pendingHandleId);

    /**
     * 根据待办事宜编号获取对应的审批数据
     *
     * @param pendingHandleIds
     *            待办事宜编号字符串集合按"，"分隔
     * @return AppFlowShowData列表的json字符串
     */
    @WebResult(name = "out", targetNamespace = "http://server.webservice.supervise.mobile.sbt.com")
    @WebMethod
    String selectAppItemApplyDates(String pendingHandleIds);

    /**
     * 获取指定的文件
     *
     * @param fileName
     * @param offset
     * @param reads
     * @return
     */
    // byte[] uploadStreamAttachFile(String fileName, Long offset, Integer reads);

    /**
     * 获取行政审批按钮和选人信息
     *
     * @param nodeId
     *            远程节点id
     * @param pendingHandleId
     *            远程待办id
     * @param userId
     *            对应远程的userInfoId
     * @return
     */
    @WebResult(name = "out", targetNamespace = "http://server.webservice.supervise.mobile.sbt.com")
    @WebMethod
    String getBtnAndUsersInfo(Long nodeId, Long pendingHandleId, Long userId);

    /**
     * 根据待办id获取审评数据
     *
     * @param pendingHandleIds
     * @return
     */
    @WebResult(name = "out", targetNamespace = "http://server.webservice.supervise.mobile.sbt.com")
    @WebMethod
    String selectAuditData(String pendingHandleIds);

    /**
     * 获取变动的企业信息
     *
     * @param entpId
     * @return
     */
    @WebResult(name = "out", targetNamespace = "http://server.webservice.supervise.mobile.sbt.com")
    @WebMethod
    String getChangeEntpInfo(Long entpId);

    /**
     * 获取审批查验系统指定环节的功能按钮和选人信息
     *
     * @param nodeId
     * @param pendingHandleId
     * @param userId
     * @return
     */
    @WebResult(name = "out", targetNamespace = "http://server.webservice.supervise.mobile.sbt.com")
    @WebMethod
    String getAuditBtnAndUsersInfo(Long nodeId, Long pendingHandleId, Long userId);

    /**
     * 根据日常检查编号查询检查记录，并转成json格式
     *
     * @param entpObjectId
     * @return
     */
    @WebResult(name = "out", targetNamespace = "http://server.webservice.supervise.mobile.sbt.com")
    @WebMethod
    String selectExamineData(Long entpObjectId);

    /**
     * 根据许可证编号获取许可证记录
     *
     * @param licenceId
     * @return
     */
    @WebResult(name = "out", targetNamespace = "http://server.webservice.supervise.mobile.sbt.com")
    @WebMethod
    String selectAppLicence(Long licenceId);

    /**
     * 根据材料清单Id获取材料清单
     *
     * @param materialId
     * @return
     */
    @WebResult(name = "out", targetNamespace = "http://server.webservice.supervise.mobile.sbt.com")
    @WebMethod
    String selectAppPrjMaterial(Long materialId);

    /**
     * 根据企业id获取行政审批，日常检查等基本数据列表
     *
     * @param rmEntpId
     * @return
     */
    @WebResult(name = "out", targetNamespace = "http://server.webservice.supervise.mobile.sbt.com")
    @WebMethod
    String selectEntpOtherData(Long rmEntpId);

    /**
     * 根据原系统执业药师id查找执业药师信息
     *
     * @param rmPharmId
     * @return
     */
    @WebResult(name = "out", targetNamespace = "http://server.webservice.supervise.mobile.sbt.com")
    @WebMethod
    String selectPharmacistByRmPharmId(Long rmPharmId);

}
