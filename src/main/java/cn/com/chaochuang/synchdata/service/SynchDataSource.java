/*
 * FileName:    SynchDataSource.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年8月14日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.synchdata.service;

import java.sql.Connection;

/**
 * @author LLM
 *
 */
public interface SynchDataSource {

    /**
     * 获取数据源连接
     *
     * @return
     */
    Connection getConnection();

    /**
     * 用classforname方法获取Connection
     * 
     * @return
     */
    Connection getConnectionByClassName();
}
