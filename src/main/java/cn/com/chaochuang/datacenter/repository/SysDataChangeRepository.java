/*
 * FileName:    SysDataChangeRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年4月8日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.datacenter.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.datacenter.domain.SysDataChange;

/**
 * @author LLM
 *
 */
public interface SysDataChangeRepository extends SimpleDomainRepository<SysDataChange, Long> {
    /**
     * 查询指定内容的变更数据
     * 
     * @param changeTableName
     * @param page
     * @return
     */
    @Query("select d from SysDataChange d where d.changeTableName=:ch order by d.id")
    public List<SysDataChange> findByChangeTableName(@Param("ch") String changeTableName, Pageable page);
}
