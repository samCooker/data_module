/*
 * FileName:    AipPunishEntpServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年8月11日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.aipcase.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.aipcase.domain.AipPunishEntp;
import cn.com.chaochuang.aipcase.repository.AipPunishEntpRepository;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.util.JsonMapper;
import cn.com.chaochuang.common.util.NullBeanUtils;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.datacenter.domain.SysDataChange;
import cn.com.chaochuang.task.bean.AipPunishInfo;
import cn.com.chaochuang.webservice.server.aipcasetransfer.AipCaseWebService;

/**
 * @author LLM
 *
 */
@Service
@Transactional
public class AipPunishEntpServiceImpl extends SimpleLongIdCrudRestService<AipPunishEntp> implements
                AipPunishEntpService {
    @PersistenceContext
    private EntityManager           entityManager;
    @Value("${getPunishdata.timeinterval}")
    private String                  timeInterval;
    @Autowired
    private AipPunishEntpRepository repository;
    @Autowired
    private AipCaseWebService       transferAipCaseService;

    @Override
    public SimpleDomainRepository<AipPunishEntp, Long> getRepository() {
        return repository;
    }

    /**
     * @see cn.com.chaochuang.aipcase.service.AipPunishEntpService#selectMaxInputDate()
     */
    @Override
    public AipPunishInfo selectMaxInputDate() {
        AipPunishInfo result = new AipPunishInfo();
        StringBuffer sql = new StringBuffer(" select Max(rmPunishEntpId) from ").append(AipPunishEntp.class.getName());
        Query query = this.entityManager.createQuery(sql.toString());
        List datas = (ArrayList) query.getResultList();
        if (Tools.isNotEmptyList(datas)) {
            for (Object o : datas) {
                if (o != null) {
                    result.setRmPunishEntpId((Long) o);
                    result.setPunishTime(null);
                    break;
                }
            }
        }
        if (result.getRmPunishEntpId() == null) {
            Date sendTime = Tools.diffDate(new Date(), new Integer(timeInterval));
            result.setPunishTime(sendTime);
            result.setRmPunishEntpId(null);
        }
        return result;
    }

    /**
     * @see cn.com.chaochuang.aipcase.service.AipPunishEntpService#savePunishInfo(cn.com.chaochuang.task.bean.AipPunishInfo)
     */
    @Override
    public void savePunishInfo(AipPunishInfo info) {
        AipPunishEntp punish = this.repository.findByRmPunishEntpId(info.getRmPunishEntpId());
        if (punish == null) {
            punish = new AipPunishEntp();
        }
        NullBeanUtils.copyProperties(punish, info);
        this.repository.save(punish);
    }

    /**
     * @see cn.com.chaochuang.aipcase.service.AipPunishEntpService#savePunishInfo(java.util.List)
     */
    @Override
    public void savePunishInfo(List<AipPunishInfo> datas) {
        if (!Tools.isNotEmptyList(datas)) {
            return;
        }
        for (AipPunishInfo info : datas) {
            this.savePunishInfo(info);
        }
    }

    /**
     * @see cn.com.chaochuang.aipcase.service.AipPunishEntpService#savePunishInfo(cn.com.chaochuang.datacenter.domain.SysDataChange)
     */
    @Override
    public void savePunishInfo(SysDataChange dataChange) {
        if (dataChange == null || Tools.isEmptyString(dataChange.getChangeScript())) {
            return;
        }
        // 获取要操作的ID
        String[] items = dataChange.getChangeScript().split("=");
        if (items == null || items.length != 2) {
            return;
        }
        JsonMapper mapper = JsonMapper.getInstance();
        String updataInfo = this.transferAipCaseService.selectUpdatePunishInfo(new Long(items[1]));
        if (StringUtils.isNotEmpty(updataInfo)) {
            AipPunishInfo updateEntp = mapper.readValue(updataInfo, AipPunishInfo.class);
            AipPunishEntp punish = this.repository.findByRmPunishEntpId(updateEntp.getRmPunishEntpId());
            if (punish == null) {
                // 为空则保存新的处罚信息
                punish = new AipPunishEntp();
            }
            NullBeanUtils.copyProperties(punish, updateEntp);
            repository.save(punish);
        }
    }

}
