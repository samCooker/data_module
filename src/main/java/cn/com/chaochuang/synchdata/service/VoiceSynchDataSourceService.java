/*
 * FileName:    VoiceSynchDataSourceService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月14日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.synchdata.service;

import java.sql.Connection;

import org.springframework.stereotype.Component;

/**
 * @author LLM
 *
 */
@Component
public class VoiceSynchDataSourceService extends LocalDataSourceService implements SynchDataSource {
    /**
     * @see cn.com.chaochuang.synchdata.service.SynchDataSource#getConnection()
     */
    @Override
    public Connection getConnection() {
        return super.getConnection();
    }
}
