import org.apache.http.NameValuePair;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import task.ScheduleSendMessage;
import utils.HttpClientHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cookie on 2016/5/4.
 */
public class HttpClientTest {

    public static void main(String[] args){
        CloseableHttpClient httpClient = HttpClientHelper.initHttpClient();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("account", "shicx"));
        params.add(new BasicNameValuePair("password", "sam159"));
        String loginHttp ="http://116.10.203.202:7070/ccoa/common/login.action";
        String result = HttpClientHelper.doGet(httpClient,loginHttp,params);
        //System.out.println(result);

        result =HttpClientHelper.doGet(httpClient,"http://116.10.203.202:7070/ccoa/workdaily/workDailyEdit.do?day=2016-05-04",null);
        System.out.println(result);
    }
}
