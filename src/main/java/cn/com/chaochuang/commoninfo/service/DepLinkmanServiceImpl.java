/*
 * FileName:    DepLinkmanServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月26日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.commoninfo.service;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.util.JsonMapper;
import cn.com.chaochuang.common.util.NullBeanUtils;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.commoninfo.domain.DepLinkman;
import cn.com.chaochuang.commoninfo.repository.DepLinkmanRepository;
import cn.com.chaochuang.datacenter.domain.SysDataChange;
import cn.com.chaochuang.datacenter.reference.OperationType;
import cn.com.chaochuang.webservice.server.ITransferOAService;

/**
 * @author LLM
 *
 */
@Service
@Transactional
public class DepLinkmanServiceImpl extends SimpleLongIdCrudRestService<DepLinkman> implements DepLinkmanService {
    /** webservice 函数库 */
    @Autowired
    private ITransferOAService   transferOAService;

    @Autowired
    private DepLinkmanRepository repository;

    @Override
    public SimpleDomainRepository<DepLinkman, Long> getRepository() {
        return repository;
    }

    /**
     * @see cn.com.chaochuang.commoninfo.service.DepLinkmanService#analysisDataChange(cn.com.chaochuang.datacenter.domain.SysDataChange)
     */
    @Override
    public void analysisDataChange(SysDataChange dataChange) {
        // 分析要修改的类型，若修改类型是update或add，需要通过webservice获取变更数据；若类型为delete则直接删除指定的记录
        if (OperationType.修改.getKey().equals(dataChange.getOperationType())
                        || OperationType.新增.getKey().equals(dataChange.getOperationType())) {
            // 若SysDataChange等于null或dataChange.getChangeScript()等于空则
            if (dataChange == null || Tools.isEmptyString(dataChange.getChangeScript())) {
                return;
            }
            Map<String, String> items = Tools.splitData(dataChange.getChangeScript());
            Long linkmanId = null;
            try {
                linkmanId = Long.valueOf(items.get("oa_address_info_id"));
            } catch (Exception ex) {
                ex.printStackTrace();
                return;
            }
            if (linkmanId == null) {
                return;
            }
            // 从webservice获取json字符串
            String json = this.transferOAService.getOaAddressInfo(linkmanId);
            if (Tools.isEmptyString(json)) {
                return;
            }
            // 将json转成department对象
            JsonMapper mapper = JsonMapper.getInstance();
            DepLinkman newLinkman = mapper.readValue(json, DepLinkman.class);
            if (newLinkman == null) {
                return;
            }
            // 根据原系统通讯录编号查询变更数据是否存在
            DepLinkman curLinkman = this.repository.findByRmLinkmanId(newLinkman.getRmLinkmanId());
            if (curLinkman == null) {
                curLinkman = new DepLinkman();
            } else {
                // 保证编号在BeanUtils.copyProperties后不被刷掉
                newLinkman.setId(curLinkman.getId());
            }
            // 变更数据存在则获取对象后覆盖再保存
            NullBeanUtils.copyProperties(curLinkman, newLinkman);
            this.repository.save(curLinkman);
        } else if (OperationType.删除.getKey().equals(dataChange.getOperationType())) {
            String[] items = dataChange.getChangeScript().split("=");
            if (items != null && items.length == 2) {
                // 根据原系统编号找出要删除的对象进行删除
                DepLinkman curLinkman = this.repository.findByRmLinkmanId(Long.valueOf(items[1]));
                if (curLinkman != null) {
                    this.repository.delete(curLinkman);
                }
            }
        }

    }

}
