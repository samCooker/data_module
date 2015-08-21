/*
 * FileName:    AipPunishEntpServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年8月11日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.aipcase.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.aipcase.domain.AipTransactPersonal;
import cn.com.chaochuang.aipcase.domain.FdFordoAipcase;
import cn.com.chaochuang.aipcase.repository.AipTransactPersonalRepository;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.user.domain.SysUser;
import cn.com.chaochuang.common.user.service.SysUserService;

/**
 * @author LLM
 *
 */
@Service
@Transactional
public class AipTransactPersonalServiceImpl extends SimpleLongIdCrudRestService<AipTransactPersonal> implements
                AipTransactPersonalService {

    @Autowired
    private AipTransactPersonalRepository repository;
    @Autowired
    private SysUserService                sysUserService;

    @Override
    public SimpleDomainRepository<AipTransactPersonal, Long> getRepository() {
        return repository;
    }

    @Override
    public void saveTransactPersonalRecord(FdFordoAipcase fdFordo) {
        if (fdFordo != null) {
            // 查找接收人
            SysUser receiver = sysUserService.findByRmUserId(fdFordo.getRecipientId());
            if (receiver == null) {
                return;
            }
            // 查找经办记录，若没有则添加
            AipTransactPersonal personalRecord = repository.findByRmCaseApplyIdAndTransactId(fdFordo.getCaseApplyId(),
                            fdFordo.getRecipientId());
            if (personalRecord == null) {
                personalRecord = new AipTransactPersonal();
                personalRecord.setRmCaseApplyId(fdFordo.getCaseApplyId());
                personalRecord.setTransactId(fdFordo.getRecipientId());
                personalRecord.setTransactDeptId(receiver.getDepartment().getRmDepId());
                personalRecord.setUnitOrgId(receiver.getDepartment().getAncestorDep());
                personalRecord.setTransactTime(new Date());
            }
            // 显示的标题跟待办一致
            personalRecord.setTitle(fdFordo.getTitle());
            repository.save(personalRecord);
        }
    }

}
