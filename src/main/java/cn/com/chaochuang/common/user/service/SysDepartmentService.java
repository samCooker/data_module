/*
 * FileName:    SysDepartmentService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2014
 * History:     2014年7月18日 (LaoZhiYong) 1.0 Create
 */

package cn.com.chaochuang.common.user.service;

import java.util.List;

import cn.com.chaochuang.common.data.service.CrudRestService;
import cn.com.chaochuang.common.user.domain.SysDepartment;
import cn.com.chaochuang.common.user.tree.DepartmentTreeNode;
import cn.com.chaochuang.datacenter.domain.SysDataChange;

/**
 * @author LaoZhiYong
 *
 */
public interface SysDepartmentService extends CrudRestService<SysDepartment, Long> {
    public List<DepartmentTreeNode> getDepartmentTree();

    public List<SysDepartment> getAllDepartment();

    public List<SysDepartment> getSubDepartmentByParentId(Long parentId);

    /**
     * 分析系统数据的处理
     *
     * @param dataChange
     */
    void analysisDataChange(SysDataChange dataChange);
}
