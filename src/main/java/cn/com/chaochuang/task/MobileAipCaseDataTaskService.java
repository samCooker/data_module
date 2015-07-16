/*
 * FileName:    MobileAipCaseDataTaskService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年5月28日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.task;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.com.chaochuang.aipcase.bean.AipCasePendingHandleInfo;
import cn.com.chaochuang.aipcase.bean.AipCaseShowData;
import cn.com.chaochuang.aipcase.domain.FdFordoAipcase;
import cn.com.chaochuang.aipcase.service.AipCaseApplyService;
import cn.com.chaochuang.aipcase.service.FdFordoAipcaseService;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.docwork.reference.FordoSource;
import cn.com.chaochuang.webservice.server.ITransferOAService;
import cn.com.chaochuang.webservice.server.aipcasetransfer.AipCaseWebService;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author LLM
 *
 */
@Component
public class MobileAipCaseDataTaskService {

    @Autowired
    private AipCaseWebService     transferAipCaseService;

    /** webservice 函数库 */
    @Autowired
    private ITransferOAService    transferOAService;

    @Autowired
    private AipCaseApplyService   aipCaseApplyService;

    /** FdFordoAipcaseService */
    @Autowired
    private FdFordoAipcaseService fdFordoService;

    /** 获取案件办理阻塞标识 */
    private static boolean        isGetAipCaseRunning   = false;
    /** 获取办案系统待办阻塞标识 */
    private static boolean        isAipCaseFordoRunning = false;

    /**
     * 获取案件办理系统的待办记录
     */
    // @Scheduled(cron = "2/10 1/1 * * * ?")
    public void getAipCaseFordo() {
        if (isAipCaseFordoRunning) {
            return;
        }
        isAipCaseFordoRunning = true;
        try {
            AipCasePendingHandleInfo info = this.fdFordoService.selectMaxInputDate();
            // 若lastOutputTime无效则不做下一步
            if (info.getLastSendTime() == null && info.getRmPendingId() == null) {
                return;
            }
            String json = this.transferAipCaseService.selectPendingItemInfo(info.getLastSendTime(),
                            info.getRmPendingId());
            // 将案件办理的待办记录写入待办事宜表
            this.saveFdFordo(json, FordoSource.行政办案);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            isAipCaseFordoRunning = false;
        }
    }

    /**
     * 保存待办记录
     *
     * @param jsonData
     * @param fdSource
     */
    private void saveFdFordo(String jsonData, FordoSource fdSource) {
        if (Tools.isEmptyString(jsonData)) {
            return;
        }
        try {
            // 将json字符串还原回PendingCommandInfo对象，再循环将对象插入FdFordo表
            ObjectMapper mapper = new ObjectMapper();
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class,
                            AipCasePendingHandleInfo.class);
            List<AipCasePendingHandleInfo> datas = (List<AipCasePendingHandleInfo>) mapper
                            .readValue(jsonData, javaType);
            this.fdFordoService.insertFdFordos(datas);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 获取案件办理数据写入本地表
     */
    // @Scheduled(cron = "20/10 1/1 * * * ?")
    public void getAipCaseDataTask() {
        if (isGetAipCaseRunning) {
            return;
        }
        isGetAipCaseRunning = true;
        try {
            // 获取未下载审批数据的待办事宜，即localData=0的数据
            List<FdFordoAipcase> fordoDatas = this.fdFordoService.selectUnLocalData();
            if (!Tools.isNotEmptyList(fordoDatas)) {
                isGetAipCaseRunning = false;
                return;
            }
            String pendingIds = Tools.changeArrayToString(fordoDatas, "rmPendingId", ",", false);
            // 获取pendingIds指定的审批数据
            String json = this.transferAipCaseService.selectAipCaseApplyDates(pendingIds);
            if (!Tools.isEmptyString(json)) {
                // 将json字符串还原回PendingCommandInfo对象，再循环将对象插入FdFordo表
                ObjectMapper mapper = new ObjectMapper();
                JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class,
                                AipCaseShowData.class);
                List<AipCaseShowData> datas = (List<AipCaseShowData>) mapper.readValue(json, javaType);
                this.aipCaseApplyService.saveAipCaseApply(datas);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            isGetAipCaseRunning = false;
        }
    }
}
