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
import org.springframework.stereotype.Component;

import cn.com.chaochuang.common.util.JsonMapper;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.voice.bean.VoiceEventPendingInfo;
import cn.com.chaochuang.voice.bean.VoiceInfoPendingInfo;
import cn.com.chaochuang.voice.service.VoiceEventService;
import cn.com.chaochuang.voice.service.VoiceInfoService;
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

    /** 获取舆情信息阻塞标识 */
    private static boolean    isGetVoiceInfoPendingRunning  = false;
    /** 获取舆情事件阻塞标识 */
    private static boolean    isGetVoiceEventPendingRunning = false;

    /**
     * 获取舆情的待办记录
     */
    // @Scheduled(cron = "10/30 * * * * ?")
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
    // @Scheduled(cron = "20/30 * * * * ?")
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
}
