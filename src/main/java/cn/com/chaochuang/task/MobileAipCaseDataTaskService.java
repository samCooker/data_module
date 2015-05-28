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

import cn.com.chaochuang.aipcase.domain.AipCaseApply;
import cn.com.chaochuang.aipcase.service.AipCaseApplyService;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.docwork.reference.FordoSource;
import cn.com.chaochuang.docwork.service.FdFordoService;
import cn.com.chaochuang.task.bean.OAPendingHandleInfo;
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
    private AipCaseWebService   transferAipCaseService;

    /** webservice 函数库 */
    @Autowired
    private ITransferOAService  transferOAService;

    @Autowired
    private AipCaseApplyService aipCaseApplyService;

    /** fdFordoService */
    @Autowired
    private FdFordoService      fdFordoService;

    /** 获取案件办理阻塞标识 */
    private static boolean      isGetAipCaseRunning   = false;
    /** 获取办案系统待办阻塞标识 */
    private static boolean      isAipCaseFordoRunning = false;

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
            OAPendingHandleInfo info = this.fdFordoService.selectMaxInputDate(FordoSource.行政办案);
            // 若lastOutputTime无效则不做下一步
            if (info.getLastSendTime() == null && info.getRmPendingItemId() == null) {
                return;
            }
            String json = this.transferAipCaseService.selectPendingItemInfo(info.getLastSendTime(),
                            info.getRmPendingItemId());
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
                            OAPendingHandleInfo.class);
            List<OAPendingHandleInfo> datas = (List<OAPendingHandleInfo>) mapper.readValue(jsonData, javaType);
            this.fdFordoService.insertFdFordos(datas, fdSource);
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
            // 获取指定案件登记时间的记录
            String lastInputTime = this.aipCaseApplyService.selectAipCaseMaxInputDate();
            if (!Tools.isEmptyString(lastInputTime)) {
                // 调用远程方法获取案件登记时间大于lastInputTime的案件数据
                String json = transferAipCaseService.getAipCaseApply(lastInputTime);
                // 将json数据转成AipCaseApply列表保存至AipCaseApply表
                if (!Tools.isEmptyString(json)) {
                    // 将json字符串还原回PendingCommandInfo对象，再循环将对象插入FdFordo表
                    ObjectMapper mapper = new ObjectMapper();
                    JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class,
                                    AipCaseApply.class);
                    List<AipCaseApply> datas = (List<AipCaseApply>) mapper.readValue(json, javaType);
                    this.aipCaseApplyService.saveAipCaseApply(datas);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            isGetAipCaseRunning = false;
        }
    }
}
