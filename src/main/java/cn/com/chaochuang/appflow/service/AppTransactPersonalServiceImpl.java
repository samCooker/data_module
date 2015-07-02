/*
 * FileName:    AppTransactPersonalServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年7月1日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.appflow.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.appflow.domain.AppFlowNodeInfo;
import cn.com.chaochuang.appflow.domain.AppItemApply;
import cn.com.chaochuang.appflow.domain.AppTransactPersonal;
import cn.com.chaochuang.appflow.repository.AppTransactPersonalRepository;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.user.domain.SysUser;
import cn.com.chaochuang.common.user.service.SysUserService;

import com.sun.star.uno.RuntimeException;

/**
 * @author Shicx
 *
 */
@Service
@Transactional
public class AppTransactPersonalServiceImpl extends SimpleLongIdCrudRestService<AppTransactPersonal> implements AppTransactPersonalService {

    @Autowired
    private AppTransactPersonalRepository repository;
    @Autowired
    private SysUserService                userService;

    @Override
    public SimpleDomainRepository<AppTransactPersonal, Long> getRepository() {
        return repository;
    }

    @Override
    public void saveAppTransactPersonalByFlowNodes(List<AppFlowNodeInfo> nodeInfoList, AppItemApply apply) {
        if (nodeInfoList == null || apply == null) {
            return;
        }
        for (AppFlowNodeInfo nodeInfo : nodeInfoList) {
            if (nodeInfo.getTransactId() != null) {// 不是发送给企业
                AppTransactPersonal preTransaction = repository.findByRmItemApplyIdAndTransactId(nodeInfo.getItemApplyId(), nodeInfo.getTransactId());
                if (preTransaction == null) {
                    // 新建经办记录
                    AppTransactPersonal newTransaction = new AppTransactPersonal();
                    SysUser user = userService.findByRmUserInfoId(nodeInfo.getTransactId());
                    if (user == null) {
                        throw new RuntimeException("找不到RmUserInfo=" + nodeInfo.getTransactId() + "的用户。");
                    }
                    newTransaction.setTitle(apply.getPrjName() + "   " + apply.getApplyInfo());
                    newTransaction.setRmItemApplyId(nodeInfo.getItemApplyId());
                    newTransaction.setTransactTime(nodeInfo.getArriveTime());
                    newTransaction.setUnitOrgId(user.getDepartment() != null ? user.getDepartment().getAncestorDep() : null);
                    newTransaction.setTransactId(nodeInfo.getTransactId());
                    newTransaction.setTransactDeptId(nodeInfo.getTransactDeptId());
                    repository.save(newTransaction);
                }
            }
        }
    }

}
