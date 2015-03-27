/*
 * FileName:    MobileDataTaskService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月21日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.task;

import cn.com.chaochuang.webservice.server.ITransferOAService;

/**
 * @author LLM
 *
 */
// @Component
public class MobileDataTaskService {
    /** webservice 函数库 */
    private ITransferOAService transferOAService;

    /**
     * 向OA获取待办事宜数据 每5分钟进行一次数据获取
     */
    // @Scheduled(cron = "0 60/12 * * * ?")
    public void getFordoDataTask() {
        // 读取当前待办事宜表中最大的rmPendingId值，再调用transferOAService的getPendingItemInfo方法
    }

    /** 向OA获取通讯录数据 */

}
