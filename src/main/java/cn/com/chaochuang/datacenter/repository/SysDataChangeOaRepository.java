/*
 * FileName:    SysDataChangeOaRepository.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2016
 * History:     2016年1月12日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.datacenter.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.datacenter.domain.SysDataChangeOa;

/**
 * @author LLM
 *
 */
public interface SysDataChangeOaRepository extends SimpleDomainRepository<SysDataChangeOa, Long> {

    /**
     * 查询指定内容的变更数据
     *
     * @param changeTableName
     * @param page
     * @return
     */
    @Query("select d from SysDataChangeOa d where d.changeTableName=:ch order by d.id")
    public List<SysDataChangeOa> findByChangeTableName(@Param("ch") String changeTableName, Pageable page);

}
