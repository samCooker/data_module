/*
 * FileName:    MobileDocexDataTaskService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2016
 * History:     2016年4月26日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.task;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.com.chaochuang.common.util.JsonMapper;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.datacenter.domain.DataUpdate;
import cn.com.chaochuang.datacenter.reference.ExecuteFlag;
import cn.com.chaochuang.datacenter.reference.WorkType;
import cn.com.chaochuang.datacenter.service.DataUpdateService;
import cn.com.chaochuang.docex.service.FdFordoDocService;
import cn.com.chaochuang.task.bean.DocexPendingItem;
import cn.com.chaochuang.task.bean.OAPendingHandleInfo;
import cn.com.chaochuang.webservice.server.ITransferDocexService;

import com.fasterxml.jackson.databind.JavaType;

/**
 * @author LLM
 *
 */
@Component
public class MobileDocexDataTaskService {
    /** webservice 函数库 */
    @Autowired
    private ITransferDocexService transferDocService;
    /** 公文传输待办服务 */
    @Autowired
    private FdFordoDocService     fdFordoDocService;
    /** 数据更新服务 */
    @Autowired
    private DataUpdateService     dataUpdateService;

    /** 获取待办阻塞标识 */
    private static boolean        isFordoRunning         = false;
    /** 提交公文处理阻塞标识 */
    private static boolean        isCommitDocFileRunning = false;

    /**
     * 向OA获取待办事宜数据 每5分钟进行一次数据获取
     */
    // @Scheduled(cron = "10/10 * * * * ?")
    @Scheduled(cron = "10 0/2 * * * ?")
    public void getDocexFordoDataTask() {
        if (isFordoRunning) {
            return;
        }
        isFordoRunning = true;
        try {
            // 获取当前待办表中公文待办中最大的数据导入时间值，若无法获取时间值则获取距离当前时间一个月的时间值
            OAPendingHandleInfo info = this.fdFordoDocService.selectMaxInputDate();
            // 若lastOutputTime无效则不做下一步
            if (info.getLastSendTime() == null && info.getRmPendingItemId() == null) {
                return;
            }
            // 读取当前待办事宜表中最大的rmPendingId值，再调用transferOAService的getPendingItemInfo方法
            String json = this.transferDocService.selectPendingItemInfo(
                            (info.getLastSendTime() != null) ? Tools.DATE_TIME_FORMAT.format(info.getLastSendTime())
                                            : "", info.getRmPendingItemId());
            // 将OA的待办记录写入待办事宜表
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
            JsonMapper mapper = JsonMapper.getInstance();
            JavaType javaType = mapper.constructParametricType(ArrayList.class, DocexPendingItem.class);
            List<DocexPendingItem> datas = mapper.readValue(jsonData, javaType);
            this.fdFordoDocService.insertFdFordos(datas);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 提交公文修改数据
     */
    @Scheduled(cron = "15/15 * * * * ?")
    // @Scheduled(cron = "20 0/2 * * * ?")
    public void commintDocFileDataTask() {
        if (isCommitDocFileRunning) {
            return;
        }
        isCommitDocFileRunning = true;
        DataUpdate dataUpdate = null;
        try {
            // 扫描DataUpdate数据列表，条件：workType=08;operationType=update
            List<DataUpdate> datas = this.dataUpdateService.selectDocFileDataUpdate(WorkType.公文传输提交);
            // 每次仅处理列表的第一条记录
            if (!Tools.isNotEmptyList(datas)) {
                return;
            }
            dataUpdate = (DataUpdate) datas.get(0);
            if (dataUpdate == null) {
                return;
            }
            // 获取要提交的json字符串，调用ITransferDocexService的setDocexTransactInfo方法修改PC端数据
            String backInfo = this.transferDocService.setDocexTransactInfo(dataUpdate.getContent());
            if ("true".equals(backInfo)) {
                // 删除DataUpdate对象
                this.dataUpdateService.delete(dataUpdate);
            } else {
                dataUpdate.setExecuteFlag(ExecuteFlag.执行错误);
                dataUpdate.setErrorInfo(backInfo);
                this.dataUpdateService.getRepository().save(dataUpdate);
            }
        } catch (Exception ex) {
            dataUpdate.setExecuteFlag(ExecuteFlag.执行错误);
            dataUpdate.setErrorInfo(ex.getClass().getName());
            this.dataUpdateService.getRepository().save(dataUpdate);
            ex.printStackTrace();
        } finally {
            isCommitDocFileRunning = false;
        }
    }
}
