/*
 * FileName:    DepLinkmanServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月26日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.commoninfo.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.commoninfo.bean.AppEntpUpdataInfo;
import cn.com.chaochuang.commoninfo.domain.AppEntp;
import cn.com.chaochuang.commoninfo.repository.AppEntpRepository;
import cn.com.chaochuang.datacenter.domain.SysDataChange;
import cn.com.chaochuang.webservice.server.SuperviseWebService;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author LLM
 *
 */
@Service
@Transactional
public class AppEntpServiceImpl extends SimpleLongIdCrudRestService<AppEntp> implements AppEntpService {
    @Autowired
    private AppEntpRepository   repository;
    @Autowired
    private SuperviseWebService superviseWebService;

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
        ObjectMapper mapper = new ObjectMapper();
        String updataInfo = superviseWebService.getChangeEntpInfo(new Long(items[1]));
        try {
            if (StringUtils.isNotEmpty(updataInfo)) {
                AppEntpUpdataInfo updataEntp = mapper.readValue(updataInfo, AppEntpUpdataInfo.class);
                AppEntp entp = findByRmEntpId(new Long(items[1]));
                if (entp == null) {
                    // 为空则保存新的企业信息
                    entp = new AppEntp();
                    entp.setInputDate(new Date());
                }
                BeanUtils.copyProperties(entp, updataEntp);
                repository.save(entp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
