/*
 * FileName:    SysDepartmentServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2014
 * History:     2014年7月18日 (LaoZhiYong) 1.0 Create
 */

package cn.com.chaochuang.common.user.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.user.domain.SysDepartment;
import cn.com.chaochuang.common.user.repository.SysDepartmentRepository;
import cn.com.chaochuang.common.user.tree.DepartmentTreeNode;

/**
 * @author LaoZhiYong
 *
 */
@Service
@Transactional
public class SysDepartmentServiceImpl extends SimpleLongIdCrudRestService<SysDepartment> implements
                SysDepartmentService {

    @Autowired
    private SysDepartmentRepository repository;

    /**
     * (non-Javadoc)
     *
     * @see cn.com.chaochuang.common.user.service.SysDepartmentService#getAllDepartment()
     */
    @Override
    public List<SysDepartment> getAllDepartment() {
        return repository.findByValidOrderByOrderNumAsc(SysDepartment.VALID);
    }

    /**
     * (non-Javadoc)
     *
     * @see cn.com.chaochuang.common.user.service.SysDepartmentService#getSubDepartmentByParentId(java.lang.Long)
     */
    @Override
    public List<SysDepartment> getSubDepartmentByParentId(Long parentId) {
        return repository.findByParentDepAndValidOrderByOrderNumAsc(parentId, SysDepartment.VALID);
    }

    public List<DepartmentTreeNode> getDepartmentTree() {
        List<DepartmentTreeNode> treeRoot = new ArrayList();

        List<SysDepartment> topList = repository.findByAncestorDep(new Long(0));
        for (int i = 0; i < topList.size(); ++i) {
            treeRoot.add(this.createNode(topList.get(i)));
        }

        return treeRoot;
    }

    private DepartmentTreeNode createNode(SysDepartment dep) {
        DepartmentTreeNode node = new DepartmentTreeNode(dep);

        if (!dep.getSubDepartment().isEmpty()) {
            SysDepartment sub;
            Iterator ite = dep.getSubDepartment().iterator();
            while (ite.hasNext()) {
                sub = (SysDepartment) ite.next();
                if (sub.getParentDep().equals(sub.getId())) {
                    continue;
                }
                node.addChild(this.createNode(sub));
            }
        }
        return node;
    }

    @Override
    public SimpleDomainRepository<SysDepartment, Long> getRepository() {
        return repository;
    }

}
