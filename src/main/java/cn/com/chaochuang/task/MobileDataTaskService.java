/*
 * FileName:    MobileDataTaskService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月21日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.task;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.docwork.service.FdFordoService;
import cn.com.chaochuang.task.bean.PendingCommandInfo;
import cn.com.chaochuang.webservice.server.ITransferOAService;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author LLM
 *
 */
@Component
public class MobileDataTaskService {

    /** webservice 函数库 */
    @Autowired
    private ITransferOAService transferOAService;
    /** fdFordoService */
    @Autowired
    private FdFordoService     fdFordoService;

    private static boolean     isRunning = false;

    /**
     * 向OA获取待办事宜数据 每5分钟进行一次数据获取
     */
    @Scheduled(cron = "0 1/1 * * * ?")
    public void getFordoDataTask() {
        if (isRunning) {
            return;
        }
        isRunning = true;
        try {
            // 获取当前待办表中最大的数据导入时间值，若无法获取时间值则获取距离当前时间一个月的时间值
            PendingCommandInfo info = this.fdFordoService.selectMaxInputDate();
            // 若lastOutputTime无效则不做下一步
            if (Tools.isEmptyString(info.getLastSendTime()) && info.getRmPendingItemId() == null) {
                return;
            }
            // 读取当前待办事宜表中最大的rmPendingId值，再调用transferOAService的getPendingItemInfo方法
            String json = this.transferOAService.selectPendingItemInfo(info.getLastSendTime(),
                            info.getRmPendingItemId());
            // 将json字符串还原回PendingCommandInfo对象，再循环将对象插入FdFordo表
            try {
                ObjectMapper mapper = new ObjectMapper();
                JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class,
                                PendingCommandInfo.class);
                List<PendingCommandInfo> datas = (List<PendingCommandInfo>) mapper.readValue(json, javaType);
                this.fdFordoService.insertFdFordos(datas);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            isRunning = false;
        }

    }

    /**
     * 向OA获取通讯录数据
     */
    // @Scheduled(cron = "0 1/2 * * * ?")
    public void getDeplinkmanDataTask() {

    }

}
