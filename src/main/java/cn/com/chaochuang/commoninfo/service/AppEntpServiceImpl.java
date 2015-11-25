/*
 * FileName:    DepLinkmanServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月26日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.commoninfo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.util.HttpClientHelper;
import cn.com.chaochuang.common.util.JsonMapper;
import cn.com.chaochuang.common.util.NullBeanUtils;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.commoninfo.bean.AppEntpUpdataInfo;
import cn.com.chaochuang.commoninfo.domain.AppEntp;
import cn.com.chaochuang.commoninfo.repository.AppEntpRepository;
import cn.com.chaochuang.datacenter.domain.SysDataChange;
import cn.com.chaochuang.task.MobileAppDataTaskService;

/**
 * @author LLM
 *
 */
@Service
@Transactional
public class AppEntpServiceImpl extends SimpleLongIdCrudRestService<AppEntp> implements AppEntpService {
    @Autowired
    private AppEntpRepository repository;
    @Value("${supervise.baseUrl}")
    private String            baseUrl;
    @Value("${supervise.entpChangeInfoUrl}")
    private String            entpChangeInfoUrl;

    @Override
    public SimpleDomainRepository<AppEntp, Long> getRepository() {
        return repository;
    }

    @Override
    public AppEntp findByRmEntpId(Long rmEntpId) {
        if (rmEntpId == null) {
            return null;
        }

        return repository.findByRmEntpId(rmEntpId);
    }

    /**
     * (non-Javadoc)
     *
     * @see cn.com.chaochuang.commoninfo.service.AppEntpService#insertOrUpdataEntp(cn.com.chaochuang.datacenter.domain.SysDataChange)
     */
    @Override
    public void insertOrUpdataEntp(SysDataChange dataChange) {
        if (dataChange == null || Tools.isEmptyString(dataChange.getChangeScript())) {
            return;
        }
        // 获取要操作的ID
        String[] items = dataChange.getChangeScript().split("=");
        if (items == null || items.length != 2) {
            return;
        }
        JsonMapper mapper = JsonMapper.getInstance();
        // 参数设置
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("entpId", items[1]));
        // 获取与行政审批相同的httpClient
        String updataInfo = HttpClientHelper.doPost(MobileAppDataTaskService.getHttpClient(), baseUrl
                        + entpChangeInfoUrl, params, HttpClientHelper.ENCODE_GBK);
        if (StringUtils.isBlank(updataInfo) || HttpClientHelper.RE_LOGIN.equals(updataInfo)) {
            return;// 获取失败或者需要用户登录才能获取信息（此处不进行登录，登录的设置在MobileAppDataTaskService.java中）
        }
        AppEntpUpdataInfo updataEntp = mapper.readValue(updataInfo, AppEntpUpdataInfo.class);
        AppEntp entp = findByRmEntpId(new Long(items[1]));
        if (entp == null) {
            // 为空则保存新的企业信息
            entp = new AppEntp();
            entp.setInputDate(new Date());
        }
        NullBeanUtils.copyProperties(entp, updataEntp);
        repository.save(entp);

    }
}
