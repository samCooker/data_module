/*
 * FileName:    SysDataChangeSystemRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2016
 * History:     2016年1月12日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.datacenter.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.datacenter.domain.SysDataChangeSystem;

/**
 * @author LLM
 *
 */
public interface SysDataChangeSystemRepository extends SimpleDomainRepository<SysDataChangeSystem, Long> {
    /**
     * 查询指定内容的变更数据
     *
     * @param changeTableNames
     * @param page
     * @return
     */
    public List<SysDataChangeSystem> findByChangeTableNameIn(List<String> changeTableNames, Pageable page);

    /**
     * 分页查询记录并按id排序
     *
     * @param page
     */
    @Query("select d from SysDataChangeSystem d order by d.id")
    public List<SysDataChangeSystem> findByPageOrderById(Pageable page);
}
