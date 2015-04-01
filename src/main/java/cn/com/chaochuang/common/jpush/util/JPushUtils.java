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
import cn.jpush.api.push.model.notification.Notification;

/**
 * @author LaoZhiYong
 *
 */
public class JPushUtils {
    private static final String masterSecret = "37150a442ab0c32e5e4495b7";
    private static final String appKey       = "d3e2f212cb564f988a673bfd";

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

    public static void main(String[] args) {
        pushByRegistrationID("030980a7019", "测试发送");
    }
}
