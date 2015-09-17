/*
 * FileName:    FdFordoCaseRepository
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年09月10日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.casecomplaint.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import cn.com.chaochuang.casecomplaint.domain.FdFordoCase;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;

/**
 * @author Shicx
 *
 */
public interface FdFordoCaseRepository extends SimpleDomainRepository<FdFordoCase, Long> {

    /**
     * 查找最大的远程待办id
     * 
     * @return
     */
    @Query("select max(fd.rmPendingId) from FdFordoCase fd")
    public String findMaxRmPendingId();

    /**
     * 
     * @return
     */
    @Query("select fd.rmPendingId from FdFordoCase fd where fd.localData=0 group by fd.rmPendingId")
    public List<String> findRmPendingIdListByLocalData(Pageable page);

    /**
     * 通过rmPendingId和接收人id查询已有的待办
     * 
     * @param pendingHandleId
     * @param recipientId
     * @return
     */
    public FdFordoCase findByRmPendingIdAndRecipientId(String pendingHandleId, Long recipientId);

    /**
     * 通过远程待办id查找待办信息，由于同一个待办id可为多人接收(坐席待办)，因此返回一个集合
     * 
     * @param pendingId
     */
    public List<FdFordoCase> findByRmPendingId(String pendingId);

    /**
     * 根据nodeId查找待办，坐席的待办可能有多个
     * 
     * @param nodeId
     * @return
     */
    public List<FdFordoCase> findByNodeId(String nodeId);

}
