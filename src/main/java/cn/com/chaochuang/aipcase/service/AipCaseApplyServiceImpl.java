/*
 * FileName:    DocFileServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月30日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.aipcase.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.aipcase.bean.AipCaseShowData;
import cn.com.chaochuang.aipcase.domain.AipCaseApply;
import cn.com.chaochuang.aipcase.repository.AipCaseApplyRepository;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.util.Tools;

/**
 * @author LJX
 *
 */
@Service
@Transactional
public class AipCaseApplyServiceImpl extends SimpleLongIdCrudRestService<AipCaseApply> implements AipCaseApplyService {
    @PersistenceContext
    private EntityManager          entityManager;

    @Value("${getdata.timeinterval}")
    private String                 timeInterval;

    @Autowired
    private AipCaseApplyRepository repository;

    @Override
    public SimpleDomainRepository<AipCaseApply, Long> getRepository() {
        return repository;
    }

    /**
     * @see cn.com.chaochuang.aipcase.service.AipCaseApplyService#selectAipCaseMaxInputDate()
     */
    @Override
    public String selectAipCaseMaxInputDate() {
        StringBuffer sql = new StringBuffer(" select Max(createDate) from ").append(AipCaseApply.class.getName());
        Query query = this.entityManager.createQuery(sql.toString());
        List datas = (ArrayList) query.getResultList();
        if (Tools.isNotEmptyList(datas)) {
            for (Object o : datas) {
                if (o != null) {
                    return o.toString();
                }
                Date sendTime = Tools.diffDate(new Date(), new Integer(timeInterval));
                return Tools.DATE_TIME_FORMAT.format(sendTime);
            }
        }
        return "";
    }

    /**
     * @see cn.com.chaochuang.aipcase.service.AipCaseApplyService#saveAipCaseApply(java.util.List)
     */
    @Override
    public void saveAipCaseApply(List<AipCaseShowData> datas) {
        // 保存webservice获取的案件基本数据，先检查是否有重复数据
        for (AipCaseShowData data : datas) {
            if (this.repository.findByRmCaseApplyId(data.getRmCaseApplyId()) == null) {
                // this.repository.save(apply);
            }
        }
    }

}
