/*
 * FileName:    SysDepartmentRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2014
 * History:     2014年7月18日 (LaoZhiYong) 1.0 Create
 */

package cn.com.chaochuang.common.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.user.domain.SysDepartment;

/**
 * @author LaoZhiYong
 *
 */
public interface SysDepartmentRepository extends SimpleDomainRepository<SysDepartment, Long> {
    public List<SysDepartment> findByAncestorDep(Long ancestorDep);

    public List<SysDepartment> findByValidOrderByOrderNumAsc(Integer valid);

    public List<SysDepartment> findByParentDepAndValidOrderByOrderNumAsc(Long parentId, Integer valid);

    public List<SysDepartment> findByDepNameLike(String depName);

    /**
     * 根据原系统部门编号查找部门对象
     *
     * @param rmDepId
     *            原系统部门编号
     * @return
     */
    public SysDepartment findByRmDepId(Long rmDepId);

    @Query("select d from SysDepartment d where d.rmDepId=d.parentDep and d.rmDepId=d.ancestorDep and d.valid=:va order by d.orderNum")
    public List<SysDepartment> findByRootDepartment(@Param("va") Integer valid);
}
