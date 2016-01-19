/*
 * FileName:    JPushUtils.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月31日 (LaoZhiYong) 1.0 Create
 */

package cn.com.chaochuang.common.jpush.util;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * @author LaoZhiYong
 *
 */
public class JPushUtils {
    private static final String masterSecret = "004f0567fe10a2f064b87572";
    private static final String appKey       = "ba6b55aa4fc772b5f2e9ce92";

    /**
     * 直接消息推送
     *
     * @param registrationID
     * @param message
     */
    public static void pushByRegistrationID(String registrationID, String message) {
        JPushClient jpc = new JPushClient(masterSecret, appKey);
        PushPayload.Builder builder = PushPayload.newBuilder();
        builder.setPlatform(Platform.all());
        builder.setNotification(Notification.alert(message));
        builder.setAudience(Audience.registrationId(registrationID));
        try {
            jpc.sendPush(builder.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 带附加条件的信息推送
     *
     * @param registrationID
     * @param message
     * @param extraKey
     * @param extraValue
     */
    public static void pushByRegistrationID(String registrationID, String message, String extraKey, String extraValue) {
        JPushClient jpc = new JPushClient(masterSecret, appKey);
        PushPayload payload = PushPayload
                        .newBuilder()
                        .setPlatform(Platform.all())
                        .setAudience(Audience.registrationId(registrationID))
                        .setNotification(
                                        Notification.newBuilder()
                                                        .addPlatformNotification(
                                                                        AndroidNotification.newBuilder()
                                                                                        .setAlert(message)
                                                                                        .addExtra(extraKey, extraValue)
                                                                                        .build()).build()).build();

        try {
            jpc.sendPush(payload);
        } catch (Exception ex) {
            System.out.println("消息：" + message + "；推送失败！");
        }

    }

    public static void main(String[] args) {
        pushByRegistrationID("030980a7019", "测试发送");
    }
}
