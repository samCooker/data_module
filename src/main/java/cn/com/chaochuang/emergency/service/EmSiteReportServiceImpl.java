/*
 * FileName:    EmSiteReportServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年12月3日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.emergency.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.user.domain.SysUser;
import cn.com.chaochuang.common.user.repository.SysUserRepository;
import cn.com.chaochuang.common.util.AttachUtils;
import cn.com.chaochuang.common.util.JsonMapper;
import cn.com.chaochuang.common.util.NullBeanUtils;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.datacenter.domain.SysDataChange;
import cn.com.chaochuang.datacenter.reference.OperationType;
import cn.com.chaochuang.emergency.bean.EmSiteReportInfo;
import cn.com.chaochuang.emergency.domain.EmAffix;
import cn.com.chaochuang.emergency.domain.EmSiteReport;
import cn.com.chaochuang.emergency.repository.EmAffixRepository;
import cn.com.chaochuang.emergency.repository.EmSiteReportRepository;
import cn.com.chaochuang.webservice.server.ITransferEmService;

/**
 * @author LLM
 *
 */
@Service
@Transactional
public class EmSiteReportServiceImpl extends SimpleLongIdCrudRestService<EmSiteReport> implements EmSiteReportService {
    @Autowired
    private EmSiteReportRepository repository;
    @Autowired
    private EmAffixRepository      affixRepository;
    @Autowired
    private SysUserRepository      userRepository;
    @Autowired
    private ITransferEmService     transferEmService;

    @Override
    public SimpleDomainRepository<EmSiteReport, Long> getRepository() {
        return repository;
    }

    /**
     * @see cn.com.chaochuang.emergency.service.EmSiteReportService#saveEmSiteReport(cn.com.chaochuang.datacenter.domain.SysDataChange)
     */
    @Override
    public void saveEmSiteReport(SysDataChange dataChange) {
        // 分解变更内容
        if (dataChange == null || Tools.isEmptyString(dataChange.getChangeScript())) {
            return;
        }
        // 获取要操作的事件审批编号
        String[] items = dataChange.getChangeScript().split("=");
        if (items == null || items.length != 2) {
            return;
        }
        Long reportId = Long.valueOf(items[1]);
        try {
            String json = "";
            EmSiteReport emSiteReport = this.repository.findByRmReportId(reportId), newReport = new EmSiteReport();
            EmSiteReportInfo reportInfo = null;
            if (!OperationType.删除.getKey().equals(dataChange.getOperationType())) {
                json = this.transferEmService.selectEmSiteReport(reportId);
                if (!Tools.isEmptyString(json)) {
                    JsonMapper mapper = JsonMapper.getInstance();
                    reportInfo = mapper.readValue(json, EmSiteReportInfo.class);
                    if (reportInfo == null) {
                        return;
                    }
                    NullBeanUtils.copyProperties(newReport, reportInfo);
                    if (newReport.getPromerId() != null) {
                        SysUser user = userRepository.findByRmUserId(newReport.getPromerId());
                        if (user != null) {
                            newReport.setPromerName(user.getUserName());
                        }
                    }
                } else {
                    return;
                }
            }
            if (OperationType.新增.getKey().equals(dataChange.getOperationType())) {
                if (emSiteReport != null) {
                    return;
                }
                // 保存汇报记录
                this.repository.save(newReport);
                // 保存附件记录
                if (Tools.isNotEmptyList(reportInfo.getAffixs())) {
                    for (EmAffix affix : reportInfo.getAffixs()) {
                        // 若附件记录不存在则保存附件
                        if (this.affixRepository.findByFileId(affix.getFileId()) == null) {
                            this.affixRepository.save(affix);
                        }
                    }
                }
            } else if (OperationType.修改.getKey().equals(dataChange.getOperationType())) {
                if (emSiteReport == null) {
                    return;
                }
                NullBeanUtils.copyProperties(emSiteReport, newReport);
                // 保存汇报记录
                this.repository.save(emSiteReport);
                // 保存附件记录
                if (Tools.isNotEmptyList(reportInfo.getAffixs())) {
                    for (EmAffix affix : reportInfo.getAffixs()) {
                        // 若附件记录不存在则保存附件
                        if (this.affixRepository.findByFileId(affix.getFileId()) == null) {
                            this.affixRepository.save(affix);
                        }
                    }
                }
            } else if (OperationType.删除.getKey().equals(dataChange.getOperationType())) {
                if (emSiteReport == null) {
                    return;
                }
                this.repository.delete(emSiteReport);
                this.deleteEmAffix(emSiteReport);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /**
     * 删除情况汇报附件
     *
     * @param emSiteReport
     */
    private void deleteEmAffix(EmSiteReport emSiteReport) {
        if (emSiteReport == null || Tools.isEmptyString(emSiteReport.getRmAffixId())) {
            return;
        }
        List<EmAffix> affixs = this.affixRepository.findByRmAffixId(emSiteReport.getRmAffixId());
        for (EmAffix affix : affixs) {
            AttachUtils.removeFile(affix.getSavePath() + "/" + affix.getSaveName());
        }
        this.affixRepository.delete(affixs);
    }
}
