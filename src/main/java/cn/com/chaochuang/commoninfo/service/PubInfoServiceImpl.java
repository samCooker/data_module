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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.commoninfo.domain.PubInfo;
import cn.com.chaochuang.commoninfo.repository.PubInfoRepository;
import cn.com.chaochuang.task.bean.PubInfoBean;

/**
 * @author LLM
 *
 */
@Service
public class PubInfoServiceImpl extends SimpleLongIdCrudRestService<PubInfo> implements PubInfoService {
    @Autowired
    private PubInfoRepository repository;

    @PersistenceContext
    private EntityManager     entityManager;

    @Override
    public SimpleDomainRepository<PubInfo, Long> getRepository() {
        return repository;
    }

    /**
     * @see cn.com.chaochuang.docwork.service.FdFordoService#selectMaxInputDate()
     */
    @Override
    public String selectMaxInputDate() {
        StringBuffer sql = new StringBuffer(" select Max(inputDate) from ").append(PubInfo.class.getName());
        Query query = this.entityManager.createQuery(sql.toString());
        List datas = (ArrayList) query.getResultList();
        if (Tools.isNotEmptyList(datas)) {
            for (Object o : datas) {
                if (o != null) {
                    return o.toString();
                }
            }
        }
        return null;
    }

    @Override
    public void savePubInfoDatas(List<PubInfoBean> datas) throws Exception {
        if (datas == null) {
            return;
        }
        for (PubInfoBean pubInfoBean : datas) {
            PubInfo pubInfo = new PubInfo();
            BeanUtils.copyProperties(pubInfo, pubInfoBean);
            Date date = new Date();
            if (Tools.subtractDateNegative(date, pubInfoBean.getPublishTime()) > 5) {
                pubInfo.setExpireFlag("1");
            } else {
                pubInfo.setExpireFlag("0");
            }
            repository.save(pubInfo);
        }

    }
}
