/*
 * FileName:    AppTransactPersonal.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年7月1日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.appflow.repository;

import cn.com.chaochuang.appflow.domain.AppTransactPersonal;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;

/**
 * @author Shicx
 *
 */
public interface AppTransactPersonalRepository extends SimpleDomainRepository<AppTransactPersonal, Long> {

    /**
     * 查询经办列表记录(返回的结果应该是唯一的)
     *
     * @param AppItemApply
     *            审批事项
     * @param transactId
     *            经办人id
     * @return
     */
    public AppTransactPersonal findByRmItemApplyIdAndTransactId(Long rmItemApplyId, Long transactId);
}
