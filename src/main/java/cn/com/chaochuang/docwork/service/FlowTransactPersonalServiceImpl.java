/*
 * FileName:    FlowTransactPersonalServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年4月30日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.docwork.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.user.domain.SysDepartment;
import cn.com.chaochuang.common.user.domain.SysUser;
import cn.com.chaochuang.common.user.service.SysDepartmentService;
import cn.com.chaochuang.common.user.service.SysUserService;
import cn.com.chaochuang.docwork.domain.DocFile;
import cn.com.chaochuang.docwork.domain.FlowTransactPersonal;
import cn.com.chaochuang.docwork.reference.ShareFlag;
import cn.com.chaochuang.docwork.repository.FlowTransactPersonalRepository;
import cn.com.chaochuang.task.bean.FlowNodeBeanInfo;

/**
 * @author Shicx
 *
 */
@Service
@Transactional
public class FlowTransactPersonalServiceImpl extends SimpleLongIdCrudRestService<FlowTransactPersonal> implements FlowTransactPersonalService {

    @Autowired
    private SysUserService                 userService;

    @Autowired
    private SysDepartmentService           departmentService;

    @Autowired
    private FlowTransactPersonalRepository repository;

    @Override
    public SimpleDomainRepository<FlowTransactPersonal, Long> getRepository() {
        return repository;
    }

    /**
     * 根据公文的不同共享标识保存公文的经办列表
     */
    @Override
    public void saveFlowTransactPersonalInfo(List<FlowNodeBeanInfo> flowNodeInfoList, DocFile file, Long redactDeptId) throws Exception {
        if (flowNodeInfoList != null) {
            if (ShareFlag.本单位共享.getKey().equals(file.getShareFlag())) {
                // 单位共享，创建人所在单位的所以人员都可以查询到。同一个单位创建的同一个公文，其经办列表只保存一份
                FlowTransactPersonal personalRecord = repository.findByDocFileAndUnitOrgIdAndShareFlag(file, file.getUnitOrgId(), ShareFlag.本单位共享.getKey());
                savePersonalRecord(personalRecord, file, flowNodeInfoList.get(0), null);
            } else if (ShareFlag.本部门共享.getKey().equals(file.getShareFlag())) {
                // 部门共享，创建人所在部门的所以人员都可以查询到。同一个部门创建的同一个公文，其经办列表只保存一份
                FlowTransactPersonal personalRecord = repository.findByDocFileAndRedactDeptIdAndShareFlag(file, file.getCreatorDeptId(), ShareFlag.本部门共享.getKey());
                savePersonalRecord(personalRecord, file, flowNodeInfoList.get(0), redactDeptId);
            } else {
                for (FlowNodeBeanInfo nodeInfo : flowNodeInfoList) {
                    // 循环办理节点，添加经办人的办理记录，经办人可以查询到已经办理的公文，但不与单位共享和部门共享重复
                    SysUser user = userService.findByRmUserId(nodeInfo.getTransactId());
                    if (user != null) {
                        SysDepartment department = user.getDepartment();
                        FlowTransactPersonal personalRecord = repository.findByDocFileAndUnitOrgIdAndShareFlag(file, department.getAncestorDep(), ShareFlag.本单位共享.getKey());
                        if (personalRecord == null) {
                            personalRecord = repository.findByDocFileAndRedactDeptIdAndShareFlag(file, department.getRmDepId(), ShareFlag.本部门共享.getKey());
                        }
                        if (personalRecord == null) {
                            personalRecord = repository.findByDocFileAndTransactIdAndShareFlag(file, user.getRmUserId(), ShareFlag.不共享.getKey());
                        }
                        savePersonalRecord(personalRecord, file, nodeInfo, null);
                    }
                }
            }
        }
    }

    /**
     * 本单位共享和本部门共享 保存经办列表
     */
    private void savePersonalRecord(FlowTransactPersonal personalRecord, DocFile file, FlowNodeBeanInfo nodeInfo, Long redactDeptId) {
        // 记录不能重复，为空才添加
        if (personalRecord == null) {
            personalRecord = new FlowTransactPersonal();
            personalRecord.setDocFile(file);
            personalRecord.setRmInstanceId(nodeInfo.getRmInstanceId());
            personalRecord.setShareFlag(file.getShareFlag());
            if (ShareFlag.本单位共享.getKey().equals(file.getShareFlag())) {
                personalRecord.setUnitOrgId(file.getUnitOrgId());
            } else if (ShareFlag.本部门共享.getKey().equals(file.getShareFlag())) {
                personalRecord.setRedactDeptId(redactDeptId);
            } else {// 不共享
                personalRecord.setTransactId(nodeInfo.getTransactId());
            }
        } else {
            // 取最近的办理时间
            personalRecord.setTransactTime(nodeInfo.getArriveTime());
        }
        repository.save(personalRecord);
    }

}
