/*
 * FileName:   CaseComplaintOpinionServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年09月10日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.casecomplaint.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.casecomplaint.bean.BaseCaseComplaintInfo;
import cn.com.chaochuang.casecomplaint.bean.FlowInstNodeApprove;
import cn.com.chaochuang.casecomplaint.domain.CaseComplaintOpinion;
import cn.com.chaochuang.casecomplaint.repository.CaseComplaintOpinionRepository;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.user.domain.SysUser;
import cn.com.chaochuang.common.user.service.SysUserService;
import cn.com.chaochuang.common.util.NullBeanUtils;

/**
 * @author Shicx
 *
 */
@Service
@Transactional
public class CaseComplaintOpinionServiceImpl extends SimpleLongIdCrudRestService<CaseComplaintOpinion> implements
                CaseComplaintOpinionService {
    @Autowired
    private CaseComplaintOpinionRepository repository;
    @Autowired
    private SysUserService                 sysUserService;

    @Override
    public SimpleDomainRepository<CaseComplaintOpinion, Long> getRepository() {
        return repository;
    }

    @Override
    public void saveComplaintOpinion(BaseCaseComplaintInfo baseInfo) {
        if (baseInfo != null && baseInfo.getApproveList() != null) {
            for (FlowInstNodeApprove nodeApprove : baseInfo.getApproveList()) {
                CaseComplaintOpinion opinion = repository.findByRmOpinionId(nodeApprove.getApproveId());
                if (opinion == null) {
                    opinion = new CaseComplaintOpinion();
                }
                NullBeanUtils.copyProperties(opinion, nodeApprove);
                opinion.setRmOpinionId(nodeApprove.getApproveId());
                // 意见填写人信息
                SysUser approver = sysUserService.findByRmUserInfoId(nodeApprove.getApproverId());
                if (approver != null) {
                    opinion.setApproverName(approver.getUserName());
                    opinion.setApproverDeptId(approver.getDepartment().getId());
                    opinion.setApproverDeptName(approver.getDepartment().getDepName());
                }
                repository.save(opinion);
            }
        }

    }

}
