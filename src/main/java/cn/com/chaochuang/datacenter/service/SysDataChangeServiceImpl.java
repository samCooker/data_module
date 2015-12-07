/*
 * FileName:    SysDataChangeServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年4月8日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.datacenter.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.datacenter.domain.SysDataChange;
import cn.com.chaochuang.datacenter.reference.DataChangeTable;
import cn.com.chaochuang.datacenter.repository.SysDataChangeRepository;

/**
 * @author LLM
 *
 */
@Service
@Transactional
public class SysDataChangeServiceImpl extends SimpleLongIdCrudRestService<SysDataChange> implements
                SysDataChangeService {
    @Autowired
    private SysDataChangeRepository repository;

    @Override
    public SimpleDomainRepository<SysDataChange, Long> getRepository() {
        return repository;
    }

    /**
     * @see cn.com.chaochuang.datacenter.service.SysDataChangeService#saveSysDataChange(java.util.List)
     */
    @Override
    public void saveSysDataChange(List<SysDataChange> sysDataChanges) {
        if (!Tools.isNotEmptyList(sysDataChanges)) {
            return;
        }
        for (SysDataChange item : sysDataChanges) {
            try {
                this.repository.save(item);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * @see cn.com.chaochuang.datacenter.service.SysDataChangeService#selectOAPendingItem(org.springframework.data.domain.Pageable)
     */
    @Override
    public List<SysDataChange> selectOAPendingItem(Pageable page) {
        // 查询公文待办变更记录
        return this.repository.findByChangeTableName(DataChangeTable.公文待办.getKey(), page);
    }

}
