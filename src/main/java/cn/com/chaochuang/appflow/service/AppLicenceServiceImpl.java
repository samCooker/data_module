/*
 * FileName:    AppLicenceServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年11月18日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.appflow.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.appflow.domain.AppLicence;
import cn.com.chaochuang.appflow.repository.AppLicenceRepository;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.util.HttpClientHelper;
import cn.com.chaochuang.common.util.JsonMapper;
import cn.com.chaochuang.common.util.NullBeanUtils;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.datacenter.domain.SysDataChange;
import cn.com.chaochuang.datacenter.reference.OperationType;

/**
 * @author LLM
 *
 */
@Service
@Transactional
public class AppLicenceServiceImpl extends SimpleLongIdCrudRestService<AppLicence> implements AppLicenceService {
    @Value("${supervise.userName}")
    private String                     userName;
    @Value("${supervise.pwd}")
    private String                     pwd;
    @Value("${supervise.baseUrl}")
    private String                     baseUrl;
    @Value("${supervise.loginUrl}")
    private String                     loginUrl;
    @Value("${licence.licenceDataUrl}")
    private String                     licenceDataUrl;
    /** 创建httpClient对象 */
    private static CloseableHttpClient httpClient = HttpClientHelper.initHttpClient();
    @Autowired
    private AppLicenceRepository       repository;

    @Override
    public SimpleDomainRepository<AppLicence, Long> getRepository() {
        return repository;
    }

    /**
     * @see cn.com.chaochuang.appflow.service.AppLicenceService#saveAppLicence(cn.com.chaochuang.datacenter.domain.SysDataChange)
     */
    @Override
    public void saveAppLicence(SysDataChange dataChange) {
        // 分解变更内容
        if (dataChange == null || Tools.isEmptyString(dataChange.getChangeScript())) {
            return;
        }
        // 获取要操作的事件审批编号
        String[] items = dataChange.getChangeScript().split("=");
        if (items == null || items.length != 2) {
            return;
        }
        Long licenceId = Long.valueOf(items[1]);
        try {
            AppLicence licence = this.repository.findByRmLicenceId(licenceId);
            // 参数设置
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("licenceId", licenceId.toString()));
            String json = HttpClientHelper.doPost(httpClient, baseUrl + this.licenceDataUrl, params,
                            HttpClientHelper.ENCODE_GBK);
            if (StringUtils.isNotBlank(json)) {
                if (HttpClientHelper.RE_LOGIN.equals(json)) {
                    loginSuperviseSys();
                    throw new RuntimeException("连接失败！");
                }
            }
            JsonMapper mapper = JsonMapper.getInstance();
            if (OperationType.新增.getKey().equals(dataChange.getOperationType())) {
                if (licence != null) {
                    return;
                }
                licence = new AppLicence();
                licence = mapper.readValue(json, AppLicence.class);
                this.repository.save(licence);
            } else if (OperationType.修改.getKey().equals(dataChange.getOperationType())) {
                AppLicence licenceInfo = mapper.readValue(json, AppLicence.class);
                Long id = licence.getId();
                NullBeanUtils.copyProperties(licence, licenceInfo);
                licence.setId(id);
                this.repository.save(licence);
            } else if (OperationType.删除.getKey().equals(dataChange.getOperationType())) {
                if (licence == null) {
                    return;
                }
                this.repository.delete(licence);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /**
     * 登录系统
     */
    private void loginSuperviseSys() {
        try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("account", userName));
            params.add(new BasicNameValuePair("password", pwd));
            HttpClientHelper.loginSys(httpClient, baseUrl + loginUrl, params, HttpClientHelper.ENCODE_GBK);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
