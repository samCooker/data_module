package task;

import org.apache.http.NameValuePair;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import utils.CalendarHelper;
import utils.HttpClientHelper;
import utils.JPushHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Cookie on 2016/5/1.
 */
@Component
public class ScheduleSendMessage {

    @Value("${cc.worklog.login.url}")
    private String worklogLoginUrl;
    @Value("${cc.worklog.account}")
    private String worklogAccount;
    @Value("${cc.worklog.password}")
    private String worklogPassword;
    @Value("${cc.worklog.edit.url}")
    private String worklogEditUrl;

    private Logger logger = Logger.getLogger("schedule.task.logger");

    /**
     * 每天17:55 发送一次
     */
    //@Scheduled(cron = "0 55 17 * * ?")
    //@Scheduled(cron = "5/5 * * * * ?")
    public void sendWarnMessage(){
        Calendar calendar =Calendar.getInstance();
        // 周末、节假日和加班判断
        if(this.worklogWarnByDate(calendar)){
            boolean isSendOk = JPushHelper.sendWarnMsgToAll("今天还没写日志？点我开始填写。");
            logger.debug("发送完成。返回"+isSendOk);
        }
    }

    /**
     * 每天18:30 19:30 22:30 发送一次
     */
    //@Scheduled(cron = "0 30 18,19,22 * * ?")
    //@Scheduled(cron = "5/10 * * * * ?")
    public void sendSecondWarnMessage(){
        Calendar calendar =Calendar.getInstance();
        // 周末、节假日和加班判断，是否已经填写判断
        if(this.worklogWarnByDate(calendar)&&!this.hasWritenWorklog(calendar)){
            boolean isSendOk = JPushHelper.sendWarnMsgToAll("还没写日志呢？快开始写吧。");
            logger.debug("发送完成。返回"+isSendOk);
        }
    }

    /**
     * 根据日期判断是否需要提醒填写日志
     * @param calendar
     * @return true 需要提醒 false 不需要
     */
    private boolean worklogWarnByDate(Calendar calendar){
        //判断是否为周末
        if(CalendarHelper.isWeekend(calendar)){
            // 是周末则判断是否需要加班
            if(CalendarHelper.isExtraWorkDay(calendar)){
                return true;
            }
        }else{
            // 不是周末则判断是否为节假日
            if(!CalendarHelper.isFestivalDay(calendar)){
                //不是节假日需提醒
                return true;
            }
        }
        return false;
    }

    /**
     * 根据日期判断是否已经填写填写日志
     * @param calendar
     * @return true 已经填写 false 没有填写
     */
    private boolean hasWritenWorklog(Calendar calendar){
        CloseableHttpClient httpClient = HttpClientHelper.initHttpClient();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("account", worklogAccount));
        params.add(new BasicNameValuePair("password", worklogPassword));
        String result = HttpClientHelper.doGet(httpClient,worklogLoginUrl,params);
        if(result!=null&&result!= ""){
            if(result.indexOf("../workdaily/myDaily.do")!=-1){
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                params.clear();
                params.add(new BasicNameValuePair("day", simpleDateFormat.format(calendar.getTime())));
                result = HttpClientHelper.doGet(httpClient,worklogEditUrl,null);
                if(result!=null&&result.indexOf("无工作记录！")!=-1){
                    logger.debug("日志已经填写。");
                    return false;
                }
            }else{
                logger.debug("日志登陆出现问题："+result);
                return false;
            }
        }else{
            logger.debug("日志登陆返回为空。");
            return false;
        }
        return true;
    }

}
