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
import cn.com.chaochuang.common.util.NullBeanUtils;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.datacenter.domain.SysDataChange;
import cn.com.chaochuang.datacenter.domain.SysDataChangeApp;
import cn.com.chaochuang.datacenter.domain.SysDataChangeErr;
import cn.com.chaochuang.datacenter.domain.SysDataChangeOa;
import cn.com.chaochuang.datacenter.domain.SysDataChangeSystem;
import cn.com.chaochuang.datacenter.domain.SysDataChangeVoice;
import cn.com.chaochuang.datacenter.reference.DataChangeTable;
import cn.com.chaochuang.datacenter.repository.SysDataChangeAppRepository;
import cn.com.chaochuang.datacenter.repository.SysDataChangeErrRepository;
import cn.com.chaochuang.datacenter.repository.SysDataChangeOaRepository;
import cn.com.chaochuang.datacenter.repository.SysDataChangeRepository;
import cn.com.chaochuang.datacenter.repository.SysDataChangeSystemRepository;
import cn.com.chaochuang.datacenter.repository.SysDataChangeVoiceRepository;

/**
 * @author LLM
 *
 */
@Service
@Transactional
public class SysDataChangeServiceImpl extends SimpleLongIdCrudRestService<SysDataChange> implements
                SysDataChangeService {
    /** 默认的数据变更表 */
    @Autowired
    private SysDataChangeRepository       repository;
    /** OA类的数据变更表 */
    @Autowired
    private SysDataChangeOaRepository     oaRepository;
    /** 行政审批类的数据变更表 */
    @Autowired
    private SysDataChangeAppRepository    appRepository;
    /** 舆情类的数据变更表 */
    @Autowired
    private SysDataChangeVoiceRepository  voiceRepository;
    /** 舆情类的数据变更表 */
    @Autowired
    private SysDataChangeSystemRepository systemRepository;
    /** 数据变更失败信息表 */
    @Autowired
    private SysDataChangeErrRepository    errRepository;

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
            if (Tools.isEmptyString(item.getChangeTableName())) {
                continue;
            }
            try {
                if (DataChangeTable.公文办结.getKey().equals(item.getChangeTableName())
                                || DataChangeTable.公文待办.getKey().equals(item.getChangeTableName())) {
                    SysDataChangeOa oaData = new SysDataChangeOa();
                    NullBeanUtils.copyProperties(oaData, item);
                    this.oaRepository.save(oaData);
                } else if (DataChangeTable.审批待办.getKey().equals(item.getChangeTableName())
                                || DataChangeTable.审批材料清单.getKey().equals(item.getChangeTableName())
                                || DataChangeTable.执业药师.getKey().equals(item.getChangeTableName())
                                || DataChangeTable.企业信息.getKey().equals(item.getChangeTableName())
                                || DataChangeTable.许可证信息.getKey().equals(item.getChangeTableName())) {
                    SysDataChangeApp appData = new SysDataChangeApp();
                    NullBeanUtils.copyProperties(appData, item);
                    this.appRepository.save(appData);
                } else if (DataChangeTable.舆情信息.getKey().equals(item.getChangeTableName())
                                || DataChangeTable.舆情事件.getKey().equals(item.getChangeTableName())
                                || DataChangeTable.舆情事件内容.getKey().equals(item.getChangeTableName())
                                || DataChangeTable.舆情事件办理.getKey().equals(item.getChangeTableName())
                                || DataChangeTable.舆情事件办理意见.getKey().equals(item.getChangeTableName())
                                || DataChangeTable.舆情事件处理意见.getKey().equals(item.getChangeTableName())) {
                    SysDataChangeVoice voiceData = new SysDataChangeVoice();
                    NullBeanUtils.copyProperties(voiceData, item);
                    this.voiceRepository.save(voiceData);
                } else if (DataChangeTable.人员.getKey().equals(item.getChangeTableName())
                                || DataChangeTable.组织结构.getKey().equals(item.getChangeTableName())
                                || DataChangeTable.通讯录.getKey().equals(item.getChangeTableName())) {
                    SysDataChangeSystem sysData = new SysDataChangeSystem();
                    NullBeanUtils.copyProperties(sysData, item);
                    this.systemRepository.save(sysData);
                } else {
                    this.repository.save(item);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * @see cn.com.chaochuang.datacenter.service.SysDataChangeService#selectOAPendingItem(org.springframework.data.domain.Pageable)
     */
    @Override
    public List<SysDataChangeOa> selectOAPendingItem(Pageable page) {
        // 查询公文待办变更记录
        return this.oaRepository.findByChangeTableName(DataChangeTable.公文待办.getKey(), page);
    }

    /**
     * @see cn.com.chaochuang.datacenter.service.SysDataChangeService#selectOAHisnoItem(org.springframework.data.domain.Pageable)
     */
    @Override
    public List<SysDataChangeOa> selectOAHisnoItem(Pageable page) {
        // 查询公文办结变更记录
        return this.oaRepository.findByChangeTableName(DataChangeTable.公文办结.getKey(), page);
    }

    /**
     * @see cn.com.chaochuang.datacenter.service.SysDataChangeService#selectSystemItem(org.springframework.data.domain.Pageable)
     */
    @Override
    public List<SysDataChangeSystem> selectSystemItem(Pageable page) {
        return this.systemRepository.findByPageOrderById(page);
    }

    /**
     * @see cn.com.chaochuang.datacenter.service.SysDataChangeService#selectSuperviseFordo(org.springframework.data.domain.Pageable)
     */
    @Override
    public List<SysDataChangeApp> selectSuperviseFordo(Pageable page) {
        return this.appRepository.findByChangeTableName(DataChangeTable.审批待办.getKey(), page);
    }

    /**
     * @see cn.com.chaochuang.datacenter.service.SysDataChangeService#selectAppItem(org.springframework.data.domain.Pageable)
     */
    @Override
    public List<SysDataChangeApp> selectAppItem(Pageable page) {
        List tableNames = Tools.getListFromArray(new String[] { DataChangeTable.审批材料清单.getKey(),
                        DataChangeTable.执业药师.getKey(), DataChangeTable.企业信息.getKey(), DataChangeTable.许可证信息.getKey() });
        return this.appRepository.findByChangeTableNameIn(tableNames, page);
    }

    /**
     * @see cn.com.chaochuang.datacenter.service.SysDataChangeService#selectVoiceItem(org.springframework.data.domain.Pageable)
     */
    @Override
    public List<SysDataChangeVoice> selectVoiceItem(Pageable page) {
        List tableNames = Tools.getListFromArray(new String[] { DataChangeTable.舆情事件.getKey(),
                        DataChangeTable.舆情事件内容.getKey(), DataChangeTable.舆情事件办理.getKey(),
                        DataChangeTable.舆情事件办理意见.getKey(), DataChangeTable.舆情事件处理意见.getKey(),
                        DataChangeTable.舆情事件办理意见.getKey() });
        return this.voiceRepository.findByChangeTableNameIn(tableNames, page);
    }

    /**
     * @see cn.com.chaochuang.datacenter.service.SysDataChangeService#selectVoiceInfoItem(org.springframework.data.domain.Pageable)
     */
    @Override
    public List<SysDataChangeVoice> selectVoiceInfoItem(Pageable page) {
        return this.voiceRepository.findByChangeTableName(DataChangeTable.舆情信息.getKey(), page);
    }

    /**
     * @see cn.com.chaochuang.datacenter.service.SysDataChangeService#deleteChangeData(cn.com.chaochuang.datacenter.domain.SysDataChange,
     *      java.lang.String)
     */
    @Override
    public void deleteChangeData(SysDataChange dataChange, String errMsg) {
        // 有错误信息先写入错误信息表
        if (!Tools.isEmptyString(errMsg)) {
            SysDataChangeErr err = new SysDataChangeErr();
            NullBeanUtils.copyProperties(err, dataChange);
            err.setId(null);
            err.setErrorMsg(errMsg);
            this.errRepository.save(err);
        }
        this.repository.delete(dataChange.getId());
    }

    /**
     * @see cn.com.chaochuang.datacenter.service.SysDataChangeService#deleteOAChangeData(cn.com.chaochuang.datacenter.domain.SysDataChangeOa,
     *      java.lang.String)
     */
    @Override
    public void deleteOAChangeData(SysDataChangeOa dataChange, String errMsg) {
        // 有错误信息先写入错误信息表
        if (!Tools.isEmptyString(errMsg)) {
            SysDataChangeErr err = new SysDataChangeErr();
            NullBeanUtils.copyProperties(err, dataChange);
            err.setId(null);
            err.setErrorMsg(errMsg);
            this.errRepository.save(err);
        }
        this.oaRepository.delete(dataChange.getId());
    }

    /**
     * @see cn.com.chaochuang.datacenter.service.SysDataChangeService#deleteAppChangeData(cn.com.chaochuang.datacenter.domain.SysDataChangeApp,
     *      java.lang.String)
     */
    @Override
    public void deleteAppChangeData(SysDataChangeApp dataChange, String errMsg) {
        // 有错误信息先写入错误信息表
        if (!Tools.isEmptyString(errMsg)) {
            SysDataChangeErr err = new SysDataChangeErr();
            NullBeanUtils.copyProperties(err, dataChange);
            err.setId(null);
            err.setErrorMsg(errMsg);
            this.errRepository.save(err);
        }
        this.appRepository.delete(dataChange.getId());
    }

    /**
     * @see cn.com.chaochuang.datacenter.service.SysDataChangeService#deleteSystemChangeData(cn.com.chaochuang.datacenter.domain.SysDataChangeSystem,
     *      java.lang.String)
     */
    @Override
    public void deleteSystemChangeData(SysDataChangeSystem dataChange, String errMsg) {
        // 有错误信息先写入错误信息表
        if (!Tools.isEmptyString(errMsg)) {
            SysDataChangeErr err = new SysDataChangeErr();
            NullBeanUtils.copyProperties(err, dataChange);
            err.setId(null);
            err.setErrorMsg(errMsg);
            this.errRepository.save(err);
        }
        this.systemRepository.delete(dataChange.getId());
    }

    /**
     * @see cn.com.chaochuang.datacenter.service.SysDataChangeService#deleteVoiceChangeData(cn.com.chaochuang.datacenter.domain.SysDataChangeVoice,
     *      java.lang.String)
     */
    @Override
    public void deleteVoiceChangeData(SysDataChangeVoice dataChange, String errMsg) {
        // 有错误信息先写入错误信息表
        if (!Tools.isEmptyString(errMsg)) {
            SysDataChangeErr err = new SysDataChangeErr();
            NullBeanUtils.copyProperties(err, dataChange);
            err.setId(null);
            err.setErrorMsg(errMsg);
            this.errRepository.save(err);
        }
        this.voiceRepository.delete(dataChange.getId());
    }

    /**
     * @see cn.com.chaochuang.datacenter.service.SysDataChangeService#findByPageOrderById(org.springframework.data.domain.Pageable)
     */
    @Override
    public List<SysDataChange> findByPageOrderById(Pageable page) {
        return repository.findByPageOrderById(page);
    }

}
