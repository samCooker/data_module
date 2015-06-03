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

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.user.domain.SysDepartment;
import cn.com.chaochuang.common.user.repository.SysDepartmentRepository;
import cn.com.chaochuang.common.user.tree.DepartmentTreeNode;
import cn.com.chaochuang.datacenter.domain.SysDataChange;
import cn.com.chaochuang.datacenter.reference.OperationType;
import cn.com.chaochuang.webservice.server.ITransferOAService;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author LaoZhiYong
 *
 */
@Service
@Transactional
public class SysDepartmentServiceImpl extends SimpleLongIdCrudRestService<SysDepartment> implements SysDepartmentService {

    @Autowired
    private SysDepartmentRepository repository;

    /** webservice 函数库 */
    @Autowired
    private ITransferOAService      transferOAService;

    /**
     * @see cn.com.chaochuang.common.user.service.SysDepartmentService#getAllDepartment()
     */
    @Override
    public List<SysDepartment> getAllDepartment() {
        return repository.findByValidOrderByOrderNumAsc(SysDepartment.VALID);
    }

    /**
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

    /**
     * @see cn.com.chaochuang.common.user.service.SysDepartmentService#analysisDataChange(cn.com.chaochuang.datacenter.domain.SysDataChange)
     */
    @Override
    public void analysisDataChange(SysDataChange dataChange) {
        try {
            // 分析要修改的类型，若修改类型是update或add，需要通过webservice获取变更数据；若类型为delete则直接删除指定的记录
            if (OperationType.修改.getKey().equals(dataChange.getOperationType()) || OperationType.新增.getKey().equals(dataChange.getOperationType())) {
                // 从webservice获取json字符串
                String json = this.transferOAService.getChangeDepartment(dataChange.getChangeScript());

                // 将json转成department对象
                ObjectMapper mapper = new ObjectMapper();
                SysDepartment newDept = mapper.readValue(json, SysDepartment.class);
                // 根据原系统部门编号查询变更数据是否存在
                SysDepartment curDept = this.repository.findByRmDepId(newDept.getRmDepId());
                if (curDept == null) {
                    curDept = new SysDepartment();
                } else {
                    // 保证编号在BeanUtils.copyProperties后不被刷掉
                    newDept.setId(curDept.getId());
                }
                // 变更数据存在则获取对象后覆盖再保存
                BeanUtils.copyProperties(curDept, newDept);
                this.repository.save(curDept);
            } else if (OperationType.删除.getKey().equals(dataChange.getOperationType())) {
                String[] items = dataChange.getChangeScript().split("=");
                if (items != null && items.length == 2) {
                    // 根据原系统编号找出要删除的对象进行删除
                    SysDepartment curDept = this.repository.findByRmDepId(Long.valueOf(items[1]));
                    if (curDept != null) {
                        this.repository.delete(curDept);
                    }
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public SimpleDomainRepository<SysDepartment, Long> getRepository() {
        return repository;
    }

    /**
     * (non-Javadoc)
     * 
     * @see cn.com.chaochuang.common.user.service.SysDepartmentService#findByRmDepId(java.lang.Long)
     */
    @Override
    public SysDepartment findByRmDepId(Long rmDepId) {
        if (rmDepId == null) {
            return null;
        }
        return repository.findByRmDepId(rmDepId);
    }

}
