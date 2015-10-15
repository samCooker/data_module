/*
 * FileName:    MobileVoiceDataTaskService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月7日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.task;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.com.chaochuang.common.jpush.util.JPushUtils;
import cn.com.chaochuang.common.user.domain.SysUser;
import cn.com.chaochuang.common.user.service.SysUserService;
import cn.com.chaochuang.common.util.AttachUtils;
import cn.com.chaochuang.common.util.JsonMapper;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.datacenter.domain.DataUpdate;
import cn.com.chaochuang.datacenter.domain.SysDataChange;
import cn.com.chaochuang.datacenter.reference.ExecuteFlag;
import cn.com.chaochuang.datacenter.reference.WorkType;
import cn.com.chaochuang.datacenter.service.DataUpdateService;
import cn.com.chaochuang.datacenter.service.SysDataChangeService;
import cn.com.chaochuang.voice.bean.VoiceAlarmRecordInfo;
import cn.com.chaochuang.voice.bean.VoiceEventPendingInfo;
import cn.com.chaochuang.voice.bean.VoiceInfoPendingInfo;
import cn.com.chaochuang.voice.domain.VoiceAlarmRecord;
import cn.com.chaochuang.voice.domain.VoiceInfoAttach;
import cn.com.chaochuang.voice.service.VoiceAlarmRecordService;
import cn.com.chaochuang.voice.service.VoiceEventService;
import cn.com.chaochuang.voice.service.VoiceInfoAttachService;
import cn.com.chaochuang.voice.service.VoiceInfoService;
import cn.com.chaochuang.webservice.server.ITransferOAService;
import cn.com.chaochuang.webservice.server.IVoiceWebService;

import com.fasterxml.jackson.databind.JavaType;

/**
 * @author LLM
 *
 */
@Component
public class MobileVoiceDataTaskService {
    @Autowired
    private VoiceInfoService        voiceInfoService;
    @Autowired
    private IVoiceWebService        voiceWebService;
    @Autowired
    private VoiceEventService       voiceEventService;
    @Autowired
    private DataUpdateService       dataUpdateService;
    @Autowired
    private SysDataChangeService    dataChangeService;
    @Autowired
    private VoiceInfoAttachService  voiceInfoAttachService;
    @Autowired
    private VoiceAlarmRecordService alarmRecordService;
    @Autowired
    private SysUserService          userService;

    /** 舆情信息阻塞标识 */
    private static boolean          isGetVoiceInfoPendingRunning  = false;
    /** 舆情事件阻塞标识 */
    private static boolean          isGetVoiceEventPendingRunning = false;
    /** 舆情事件数据提交阻塞标识 */
    private static boolean          isGetVoiceEventSubmitRunning  = false;
    /** 舆情信息数据提交阻塞标识 */
    private static boolean          isGetVoiceInfoSubmitRunning   = false;
    /** 变更数据获取阻塞标识 */
    private static boolean          isGetSysDataChangeRunning     = false;
    /** 舆情信息附件阻塞标识 */
    private static boolean          isGetVoiceInfoAttachRunning   = false;
    /** 舆情信息提醒阻塞标识 */
    private static boolean          isGetVoiceAlarmRunning        = false;
    /** 舆情信息推送阻塞标识 */
    private static boolean          isGetPushAlarmRunning         = false;

    /** 附件存放根路径 */
    @Value("${upload.rootpath}")
    private String                  rootPath;

    /** 舆情附件存放相对路径 */
    @Value("${voiceinfo.attach.path}")
    private String                  voiceInfoAttachPath;

    /**
     * 获取舆情的待办记录
     */
    // @Scheduled(cron = "11/30 * * * * ?")
    public void getVoiceInfoFordo() {
        if (isGetVoiceInfoPendingRunning) {
            return;
        }
        isGetVoiceInfoPendingRunning = true;
        try {
            VoiceInfoPendingInfo info = this.voiceInfoService.selectMaxInputDate();
            // 若lastOutputTime无效则不做下一步
            if (info.getLastSendTime() == null && info.getRmPendingId() == null) {
                return;
            }
            String json = this.voiceWebService.selectPendingVoiceInfo(
                            (info.getLastSendTime() != null) ? Tools.DATE_TIME_FORMAT.format(info.getLastSendTime())
                                            : "", info.getRmPendingId());
            // 将舆情记录写入舆情信息表
            if (!Tools.isEmptyString(json)) {
                try {
                    // 将json字符串还原回PendingCommandInfo对象，再循环将对象插入FdFordo表
                    JsonMapper mapper = JsonMapper.getInstance();
                    JavaType javaType = mapper.constructParametricType(ArrayList.class, VoiceInfoPendingInfo.class);
                    List<VoiceInfoPendingInfo> datas = (List<VoiceInfoPendingInfo>) mapper.readValue(json, javaType);
                    this.voiceInfoService.insertVoiceInfo(datas);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            isGetVoiceInfoPendingRunning = false;
        }
    }

    /**
     * 获取待办事件
     */
    // @Scheduled(cron = "17/30 * * * * ?")
    public void getVoiceEventFordo() {
        if (isGetVoiceEventPendingRunning) {
            return;
        }
        isGetVoiceEventPendingRunning = true;
        try {
            VoiceEventPendingInfo info = this.voiceEventService.selectMaxInputDate();
            // 若lastOutputTime无效则不做下一步
            if (info.getLastSendTime() == null && info.getRmPendingId() == null) {
                return;
            }
            String json = this.voiceWebService.selectVoiceEventPending(
                            (info.getLastSendTime() != null) ? Tools.DATE_TIME_FORMAT.format(info.getLastSendTime())
                                            : "", info.getRmPendingId());
            // 将舆情记录写入舆情信息表
            if (!Tools.isEmptyString(json)) {
                try {
                    // 将json字符串还原回PendingCommandInfo对象，再循环将对象插入FdFordo表
                    JsonMapper mapper = JsonMapper.getInstance();
                    JavaType javaType = mapper.constructParametricType(ArrayList.class, VoiceEventPendingInfo.class);
                    List<VoiceEventPendingInfo> datas = (List<VoiceEventPendingInfo>) mapper.readValue(json, javaType);
                    this.voiceEventService.insertVoiceEvent(datas);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            isGetVoiceEventPendingRunning = false;
        }
    }

    /**
     * 提交舆情信息修改数据
     */
    // @Scheduled(cron = "22/30 * * * * ?")
    public void commitVoiceInfoDataTask() {
        if (isGetVoiceInfoSubmitRunning) {
            return;
        }
        isGetVoiceInfoSubmitRunning = true;
        DataUpdate dataUpdate = null;
        try {
            // 扫描DataUpdate数据列表，条件：workType=00;operationType=update
            List<DataUpdate> datas = this.dataUpdateService.selectDocFileDataUpdate(WorkType.舆情信息提交);
            // 每次仅处理列表的第一条记录
            if (!Tools.isNotEmptyList(datas)) {
                return;
            }
            dataUpdate = datas.get(0);
            if (dataUpdate == null) {
                return;
            }
            // 获取要提交的json字符串
            String dataBack = this.voiceWebService.saveVoiceInfoEvent(dataUpdate.getContent());
            if (!ITransferOAService.TRANS_RESULT.equals(dataBack.toLowerCase())) {
                dataUpdate.setExecuteFlag(ExecuteFlag.执行错误);
                dataUpdate.setErrorInfo(dataBack);
            } else {
                // 成功则删除
                dataUpdateService.delete(dataUpdate);
            }
        } catch (Exception ex) {
            dataUpdate.setExecuteFlag(ExecuteFlag.执行错误);
            dataUpdate.setErrorInfo(ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (dataUpdate != null && ExecuteFlag.执行错误.equals(dataUpdate.getExecuteFlag())) {
                this.dataUpdateService.getRepository().save(dataUpdate);
            }
            isGetVoiceInfoSubmitRunning = false;
        }
    }

    /**
     * 提交舆情事件的数据
     */
    // @Scheduled(cron = "25/30 * * * * ?")
    public void commintVoiceEventDataTask() {
        if (isGetVoiceEventSubmitRunning) {
            return;
        }
        isGetVoiceEventSubmitRunning = true;
        DataUpdate dataUpdate = null;
        try {
            // 扫描DataUpdate数据列表，条件：workType=00;operationType=update
            List<DataUpdate> datas = this.dataUpdateService.selectDocFileDataUpdate(WorkType.舆情事件提交);
            // 每次仅处理列表的第一条记录
            if (!Tools.isNotEmptyList(datas)) {
                return;
            }
            dataUpdate = datas.get(0);
            if (dataUpdate == null) {
                return;
            }
            // 获取要提交的json字符串，调用ITransferOAService的setDocTransactInfo方法修改OA端数据
            String dataBack = this.voiceWebService.setVoiceEventHandleInfo(dataUpdate.getContent());
            if (!ITransferOAService.TRANS_RESULT.equals(dataBack.toLowerCase())) {
                dataUpdate.setExecuteFlag(ExecuteFlag.执行错误);
                dataUpdate.setErrorInfo(dataBack);
            } else {
                // 成功则删除
                dataUpdateService.delete(dataUpdate);
            }
        } catch (Exception ex) {
            dataUpdate.setExecuteFlag(ExecuteFlag.执行错误);
            dataUpdate.setErrorInfo(ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (dataUpdate != null && ExecuteFlag.执行错误.equals(dataUpdate.getExecuteFlag())) {
                this.dataUpdateService.getRepository().save(dataUpdate);
            }
            isGetVoiceEventSubmitRunning = false;
        }
    }

    /**
     * 获取舆情相关变更数据
     */
    // @Scheduled(cron = "5/5 * * * * ?")
    public void getDataChange() {
        if (isGetSysDataChangeRunning) {
            return;
        }
        isGetSysDataChangeRunning = true;
        try {
            String json = this.voiceWebService.getDataChange();
            if (Tools.isEmptyString(json)) {
                return;
            }
            JsonMapper mapper = JsonMapper.getInstance();
            JavaType javaType = mapper.constructParametricType(ArrayList.class, SysDataChange.class);
            List<SysDataChange> datas = (List<SysDataChange>) mapper.readValue(json, javaType);
            this.dataChangeService.saveSysDataChange(datas);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            isGetSysDataChangeRunning = false;
        }
    }

    /**
     * 获取舆情文件
     */
    // @Scheduled(cron = "15/15 * * * * ?")
    public void getVoiceInfoAttachTask() {
        if (isGetVoiceInfoAttachRunning) {
            return;
        }
        isGetVoiceInfoAttachRunning = true;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            String localFilePath = this.rootPath + this.voiceInfoAttachPath + Tools.DATE_FORMAT4.format(new Date());
            // 获取公文OA的附件 查询DocFileAttach中localData为非本地数据("0")的数据，一次处理一个文件
            List<VoiceInfoAttach> datas = this.voiceInfoAttachService.selectUnLocalAttach();
            if (!Tools.isNotEmptyList(datas)) {
                return;
            }
            VoiceInfoAttach attach = datas.get(0);
            File file = new File(localFilePath);
            // 目录不存在则建立新目录
            if (!file.exists()) {
                file.mkdirs();
            }
            String localFileName = localFilePath + "/" + AttachUtils.getUniqueFileName();
            String remoteFileName = attach.getSavePath();
            file = new File(localFileName);
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file, true));
            // 调用ITransferOAService的setDocTransactInfo的uploadStreamAttachFile方法
            Long offset = Long.valueOf(0);
            int uploadBlockSize = 10240;
            byte[] buffer = this.voiceWebService.uploadStreamAttachFile(remoteFileName, offset, uploadBlockSize);
            while (buffer.length > 0) {
                bufferedOutputStream.write(buffer, 0, buffer.length);
                offset += buffer.length;
                buffer = new byte[uploadBlockSize];
                buffer = this.voiceWebService.uploadStreamAttachFile(remoteFileName, offset, uploadBlockSize);
            }
            // 将附件localData标志置为本地数据("1")
            this.voiceInfoAttachService.saveVoiceInfoAttachForLocal(attach.getId(), localFileName);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                } catch (IOException exf) {
                    throw new RuntimeException(exf);
                }
            }
            isGetVoiceInfoAttachRunning = false;
        }
    }

    /**
     * 获取舆情提醒信息
     */
    // @Scheduled(cron = "7/12 * * * * ?")
    public void getVoiceAlarm() {
        if (isGetVoiceAlarmRunning) {
            return;
        }
        isGetVoiceAlarmRunning = true;
        try {
            VoiceInfoPendingInfo info = this.alarmRecordService.selectMaxInputDate();
            // 若lastOutputTime无效则不做下一步
            if (info.getLastSendTime() == null && info.getRmPendingId() == null) {
                return;
            }
            String json = this.voiceWebService.selectVoiceAlarmRecord(
                            (info.getLastSendTime() != null) ? Tools.DATE_TIME_FORMAT.format(info.getLastSendTime())
                                            : "", info.getRmPendingId());
            // 将舆情提醒记录写入舆情推送信息表
            if (!Tools.isEmptyString(json)) {
                try {
                    // 将json字符串还原回PendingCommandInfo对象，再循环将对象插入FdFordo表
                    JsonMapper mapper = JsonMapper.getInstance();
                    JavaType javaType = mapper.constructParametricType(ArrayList.class, VoiceAlarmRecordInfo.class);
                    List<VoiceAlarmRecordInfo> datas = (List<VoiceAlarmRecordInfo>) mapper.readValue(json, javaType);
                    this.alarmRecordService.saveVoiceAlarmRecord(datas);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            isGetVoiceAlarmRunning = false;
        }
    }

    /**
     * 推送舆情提醒
     */
    // @Scheduled(cron = "5/10 * * * * ?")
    public void pushVoiceAlarm() {
        if (isGetPushAlarmRunning) {
            return;
        }
        isGetPushAlarmRunning = true;
        List<VoiceAlarmRecord> records = this.alarmRecordService.selectPushRecord();
        for (VoiceAlarmRecord alarm : records) {
            SysUser user = this.userService.findByRmUserId(alarm.getAlarmUserId());
            if (user != null && user.getRegistrationId() != null) {
                JPushUtils.pushByRegistrationID(user.getRegistrationId(), alarm.getAlarmContent());
            }
            this.alarmRecordService.updateAlarmRecord(alarm.getId());
        }
        isGetPushAlarmRunning = false;
    }
}
