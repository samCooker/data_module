/*
 * FileName:    MobileAppDataTaskService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年5月28日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.task;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.com.chaochuang.appflow.bean.AppFlowPendingHandleInfo;
import cn.com.chaochuang.appflow.service.FdFordoAppService;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.webservice.server.SuperviseWebService;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author LLM
 *
 */
@Component
public class MobileAppDataTaskService {

    @Autowired
    private SuperviseWebService superviseWebService;
    @Autowired
    private FdFordoAppService   fdFordoAppService;

    /** 获取公文阻塞标识 */
    private static boolean      isFordoRunning = false;

    /**
     * 向行政审批系统获取待办事宜数据 每10秒进行一次数据获取
     */
    @Scheduled(cron = "5/10 * * * * ?")
    public void getFordoDataTask() {
        if (isFordoRunning) {
            return;
        }
        isFordoRunning = true;
        try {
            // 获取当前待办表中公文待办中最大的数据导入时间值，若无法获取时间值则获取距离当前时间一个月的时间值
            AppFlowPendingHandleInfo info = this.fdFordoAppService.selectMaxInputDate();
            // 若lastOutputTime或rmPendingId无效则不做下一步
            if (info.getLastSendTime() == null && info.getRmPendingId() == null) {
                isFordoRunning = false;
                return;
            }
            // 读取当前待办事宜表中最大的rmPendingId值，再调用transferOAService的getPendingItemInfo方法
            String json = this.superviseWebService.selectPendingHandleList(info.getLastSendTime(),
                            Tools.isEmptyString(info.getRmPendingId()) ? null : Long.valueOf(info.getRmPendingId()));
            // 将行政审批的待办记录写入待办事宜表
            this.saveFdFordo(json);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            isFordoRunning = false;
        }
    }

    /**
     * 保存待办记录
     *
     * @param jsonData
     */
    private void saveFdFordo(String jsonData) {
        if (Tools.isEmptyString(jsonData)) {
            return;
        }
        try {
            // 将json字符串还原回PendingCommandInfo对象，再循环将对象插入FdFordo表
            ObjectMapper mapper = new ObjectMapper();
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class,
                            AppFlowPendingHandleInfo.class);
            List<AppFlowPendingHandleInfo> datas = (List<AppFlowPendingHandleInfo>) mapper
                            .readValue(jsonData, javaType);
            this.fdFordoAppService.insertFdFordos(datas);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
