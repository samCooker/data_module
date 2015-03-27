/*
 * FileName:    SysDepartmentRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2014
 * History:     2014年7月18日 (LaoZhiYong) 1.0 Create
 */

package cn.com.chaochuang.common.user.repository;

import java.util.List;

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
}
