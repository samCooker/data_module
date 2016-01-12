/*
 * FileName:    SysDataChangeVoiceRepository.java
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
import cn.com.chaochuang.datacenter.domain.SysDataChangeVoice;

/**
 * @author LLM
 *
 */
public interface SysDataChangeVoiceRepository extends SimpleDomainRepository<SysDataChangeVoice, Long> {
    /**
     * 查询指定内容的变更数据
     *
     * @param changeTableNames
     * @param page
     * @return
     */
    public List<SysDataChangeVoice> findByChangeTableNameIn(List<String> changeTableNames, Pageable page);

    /**
     * 查询指定内容的变更数据
     *
     * @param changeTableName
     * @param page
     * @return
     */
    @Query("select d from SysDataChangeVoice d where d.changeTableName =:ch order by d.id")
    public List<SysDataChangeVoice> findByChangeTableName(@Param("ch") String changeTableName, Pageable page);

    /**
     * 分页查询记录并按id排序
     *
     * @param page
     */
    @Query("select d from SysDataChangeVoice d order by d.id")
    public List<SysDataChangeVoice> findByPageOrderById(Pageable page);
}
