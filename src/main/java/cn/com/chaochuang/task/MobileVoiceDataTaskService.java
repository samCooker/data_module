/*
 * FileName:    MobileVoiceDataTaskService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月7日 (LLM) 1.0 Create
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
import cn.com.chaochuang.voice.bean.VoiceEventPendingInfo;
import cn.com.chaochuang.voice.bean.VoiceInfoPendingInfo;
import cn.com.chaochuang.voice.service.VoiceEventService;
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
    private VoiceInfoService  voiceInfoService;
    @Autowired
    private IVoiceWebService  voiceWebService;
    @Autowired
    private VoiceEventService voiceEventService;
    @Autowired
    private DataUpdateService dataUpdateService;

    /** 舆情信息阻塞标识 */
    private static boolean    isGetVoiceInfoPendingRunning  = false;
    /** 舆情事件阻塞标识 */
    private static boolean    isGetVoiceEventPendingRunning = false;
    /** 舆情事件数据提交阻塞标识 */
    private static boolean    isGetVoiceEventSubmitRunning  = false;

    /**
     * 获取舆情的待办记录
     */
    @Scheduled(cron = "10/30 * * * * ?")
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
     * 获取事件
     */
    @Scheduled(cron = "20/30 * * * * ?")
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
     * 提交舆情事件的数据
     */
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
            dataUpdate = (DataUpdate) datas.get(0);
            if (dataUpdate == null) {
                return;
            }
            // 获取要提交的json字符串，调用ITransferOAService的setDocTransactInfo方法修改OA端数据
            String dataBack = this.voiceWebService.setVoiceEventHandleInfo(dataUpdate.getContent());
            if (!ITransferOAService.TRANS_RESULT.equals(dataBack.toLowerCase())) {
                dataUpdate.setExecuteFlag(ExecuteFlag.执行错误);
                dataUpdate.setErrorInfo(dataBack);
            }
        } catch (Exception ex) {
            dataUpdate.setExecuteFlag(ExecuteFlag.执行错误);
            dataUpdate.setErrorInfo(ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (ExecuteFlag.执行错误.equals(dataUpdate.getExecuteFlag())) {
                this.dataUpdateService.getRepository().save(dataUpdate);
            }
            isGetVoiceEventSubmitRunning = false;
        }
    }
}
