package utils;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

/**
 * Created by Cookie on 2016/5/1.
 */
public class JPushHelper {

    //ccworklog app key
    private static final String APK_KEY = "3c7b423687567f149ad9c627";
    private static final String MASTER_SECRET = "f6c4e188328976b46e8c521b";

    public static boolean sendWarnMsgToAll(String msg) {
        // 创建JPushClient对象
        JPushClient jPushClient = new JPushClient(MASTER_SECRET, APK_KEY);
        // 定义发送的设置
        PushPayload pushPayload = PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.all())
                .setNotification(Notification.android(msg, "温馨提示", null))
                .build();
        try {
            PushResult result = jPushClient.sendPush(pushPayload);
            return result.isResultOK();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
        return false;
    }
}
