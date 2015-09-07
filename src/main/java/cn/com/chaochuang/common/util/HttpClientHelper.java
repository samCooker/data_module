/*
 * FileName:    HttpClientHelper.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年8月31日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * @author Shicx
 *
 */
public class HttpClientHelper {

    /**
     * 重新登录系统标识
     */
    public static final String  RE_LOGIN = "relogin";

    private CloseableHttpClient httpClient;

    public static HttpClientHelper newHttpClientHelper() {
        return new HttpClientHelper();
    }

    public HttpClientHelper() {
        super();
        initHttpClient();
    }

    public void initHttpClient() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        // 将最大连接数增加到50
        cm.setMaxTotal(50);
        // 将每个路由基础的连接增加到10
        cm.setDefaultMaxPerRoute(20);
        // 将目标主机的最大连接数增加到25

        this.httpClient = HttpClients.custom().setConnectionManager(cm).build();
    }

    /**
     * 
     * @param userName
     * @param pwd
     * @param url
     * @return
     * @throws Exception
     */
    public boolean loginSuperviseSys(String userName, String pwd, HttpPost post) throws Exception {
        try {
            if (httpClient == null) {
                throw new RuntimeException("请先初始化HttpClient");
            }
            if (StringUtils.isBlank(userName) || StringUtils.isBlank(pwd)) {
                throw new RuntimeException("用户名或密码为空");
            }
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("account", userName));
            params.add(new BasicNameValuePair("password", pwd));
            // 设置参数
            post.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse httpresponse = httpClient.execute(post);
            // 获得跳转的网址
            Header locationHeader = httpresponse.getFirstHeader("Location");
            if (locationHeader == null) {
                return false;
            } else {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            if (post != null) {
                post.releaseConnection();
            }
        }
    }

    /**
     * 发送post请求
     * 
     * @param httpClient
     * @param url
     * @param params
     * @return
     */
    public String doPost(HttpPost post, List<NameValuePair> params) {
        try {
            // 设置参数
            post.setEntity(new UrlEncodedFormEntity(params, "gbk"));
            CloseableHttpResponse response = httpClient.execute(post);
            if (response == null) {
                return null;
            }
            int statusCode = response.getStatusLine().getStatusCode();
            if (HttpStatus.SC_OK == statusCode) {
                return EntityUtils.toString(response.getEntity());
            } else if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
                return RE_LOGIN;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            post.releaseConnection();
        }
        return null;
    }

    /**
     * Get请求
     * 
     * @param url
     * @param params
     * @return
     */
    public CloseableHttpResponse doGet(HttpGet httpget, List<NameValuePair> params) {
        try {
            // 设置参数
            String str = EntityUtils.toString(new UrlEncodedFormEntity(params));
            httpget.setURI(new URI(httpget.getURI().toString() + "?" + str));
            // 发送请求
            return httpClient.execute(httpget);
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
