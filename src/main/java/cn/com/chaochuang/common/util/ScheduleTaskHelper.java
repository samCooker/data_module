/*
 * FileName:    ScheduleTaskHelper.java
 * Description: 
 * Company:     南宁超创信息工程有限公司
 * Copyright:    ChaoChuang (c) 2016
 * History:     2016年04月28日 (Shicx 1.0 Create
 */

package cn.com.chaochuang.common.util;

import org.apache.log4j.Logger;

/**
 * 定时任务日志输出辅助类
 *
 * @author Shicx
 */
public class ScheduleTaskHelper {

    /**
     * 定时任务从远程系统获取信息时若出现异常，则返回的信息中包含该字符串，以此标记返回的信息是异常信息。
     */
    public static final String TASK_ECXEPTION_FLAG = "@@exception@@";

    /**
     * 定时任务日志
     */
    public static Logger taskLogger = Logger.getLogger("datacenter.schedule.task.logger");

    /**
     * 判断返回的字符串信息是否正常
     * @param msg
     * @param prefix 写入日志时的异常信息前缀，可为空
     * @return true 正常 false 有异常
     */
    public static boolean isFormalDataMsg(String msg,String prefix){
        if(Tools.isEmptyString(msg)){
            //返回的信息为空，没有获取到符合的数据
            return false;
        }
        if(msg.indexOf(TASK_ECXEPTION_FLAG)!=-1){
            //返回异常信息，将异常信息写入日志
            taskLogger.error(prefix+":"+msg);
            return false;
        }
        return true;
    }

}
