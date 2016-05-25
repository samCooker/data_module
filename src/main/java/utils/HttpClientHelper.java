/*
 * FileName:    HttpClientHelper.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年8月31日 (Shicx) 1.0 Create
 */

package utils;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author Shicx
 */
public class HttpClientHelper {

    /**
     * 提交表单编码-gbk
     */
    public static final String ENCODE_GBK = "GBK";
    /**
     * 提交表单编码-utf-8
     */
    public static final String ENCODE_UTF8 = "UTF-8";
    /**
     * 连接超时(ms)
     */
    public static final int CONN_TIME_OUT = 20000;
    /**
     * 读取超时(ms)
     */
    public static final int SOCKET_TIME_OUT = 30000;
    /**
     * http client 日志
     */
    public static Logger httpClientLogger = Logger.getLogger("schedule.task.logger");

    /**
     * 创建一个httpClient对象，并进行相关配置
     *
     * @return
     */
    public static CloseableHttpClient initHttpClient() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        // 默认连接池最大连接数(MaxTotal)20 ，每个路由基础的连接数(DefaultMaxPerRoute)2
        // 将连接池最大连接数
        // cm.setMaxTotal(20);
        // 每个路由基础的连接数
        // cm.setDefaultMaxPerRoute(2);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(new Integer(SOCKET_TIME_OUT))
                .setConnectTimeout(new Integer(CONN_TIME_OUT)).build();// 设置请求和传输超时时间
        return HttpClients.custom().setConnectionManager(cm).setDefaultRequestConfig(requestConfig).build();
    }

    /**
     * 发送post请求
     *
     * @param httpClient
     * @param charset    为空则使用gbk
     * @param params
     * @return
     */
    public static String doPost(CloseableHttpClient httpClient, String postUrl, List<NameValuePair> params,
                                String charset) {
        HttpPost post = null;
        try {
            post = new HttpPost(postUrl);
            // 设置参数及表单编码，charset为空则使用gbk
            post.setEntity(new UrlEncodedFormEntity(params, charset != null ? charset : ENCODE_GBK));
            CloseableHttpResponse response = httpClient.execute(post);
            if (response == null) {
                return null;
            }
            int statusCode = response.getStatusLine().getStatusCode();
            httpClientLogger.debug("发送post请求:"+postUrl+",返回" + statusCode);
            return EntityUtils.toString(response.getEntity());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (post != null) {
                post.releaseConnection();
            }
        }
        return null;
    }

    /**
     * Get请求
     *
     * @param params
     * @return
     */
    public static String doGet(CloseableHttpClient httpClient, String getUrl, List<NameValuePair> params) {
        HttpGet httpGet = null;
        try {
            httpGet = new HttpGet(getUrl);
            // 设置参数
            if (params != null) {
                String str = EntityUtils.toString(new UrlEncodedFormEntity(params));
                httpGet.setURI(new URI(httpGet.getURI().toString() + "?" + str));
            }
            // 发送请求
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if (response == null) {
                return null;
            }
            int statusCode = response.getStatusLine().getStatusCode();
            httpClientLogger.debug("发送get请求:"+getUrl+",返回" + statusCode);
            // 返回响应的字符串实体
            return EntityUtils.toString(response.getEntity());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {
            httpGet.releaseConnection();
        }
        return null;
    }

    /**
     * Get请求
     */
    public static CloseableHttpResponse doGetAndReturnResponse(CloseableHttpClient httpClient, HttpGet httpGet,
                                                               List<NameValuePair> params) {
        try {
            // 设置参数
            String str = EntityUtils.toString(new UrlEncodedFormEntity(params));
            httpGet.setURI(new URI(httpGet.getURI().toString() + "?" + str));
            // 发送请求
            return httpClient.execute(httpGet);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

}
