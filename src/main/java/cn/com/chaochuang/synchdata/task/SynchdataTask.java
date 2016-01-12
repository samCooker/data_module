/*
 * FileName:    SynchdataTask.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年8月20日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.synchdata.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.synchdata.domain.SysSynchdataTask;
import cn.com.chaochuang.synchdata.reference.SynchDataFlag;
import cn.com.chaochuang.synchdata.reference.SynchDataStatus;
import cn.com.chaochuang.synchdata.service.SynchDataService;
import cn.com.chaochuang.synchdata.service.SysSynchdataTaskService;

/**
 * @author LLM
 *
 */
@Component
public class SynchdataTask {
    @Autowired
    private SynchDataService        synchDataService;
    @Autowired
    private SysSynchdataTaskService synchdataTaskService;

    /** 获取办案系统处罚记录阻塞标识 */
    private static boolean          isSynchDataTaskRunning = false;

    /**
     * 获取案件办理系统的待办记录
     */
    // @Scheduled(cron = "10 0/5 * * * ?")
    @Scheduled(cron = "10/10 * * * * ?")
    public void executeSynchDataTask() {
        if (isSynchDataTaskRunning) {
            return;
        }
        isSynchDataTaskRunning = true;
        try {
            List<SysSynchdataTask> tasks = this.synchdataTaskService.selectSynchdataTask(SynchDataStatus.未同步);
            if (!Tools.isNotEmptyList(tasks)) {
                return;
            }
            SysSynchdataTask task = tasks.get(0);
            if (task.getSynchDataFlag().equals(SynchDataFlag.企业数据)) {
                this.synchDataService.synchAppEntpData(task);
            } else if (task.getSynchDataFlag().equals(SynchDataFlag.舆情基本信息)) {
                this.synchDataService.synchVoiceInfoData(task);
            } else if (task.getSynchDataFlag().equals(SynchDataFlag.行政审批待办)) {
                this.synchDataService.synchSuperviseFdData(task);
            } else if (task.getSynchDataFlag().equals(SynchDataFlag.审评查验待办)) {
                this.synchDataService.synchAuditFdData(task);
            } else if (task.getSynchDataFlag().equals(SynchDataFlag.用户变更数据)) {
                this.synchDataService.synchSysDataChangeOfUserData(task);
            } else if (task.getSynchDataFlag().equals(SynchDataFlag.部门变更数据)) {
                this.synchDataService.synchSysDataChangeOfDeptData(task);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            isSynchDataTaskRunning = false;
        } finally {
            isSynchDataTaskRunning = false;
        }
    }

}
