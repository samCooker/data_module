/*
 * FileName:    LocalDataSourceService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年8月21日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.synchdata.service;

import java.sql.Connection;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.stereotype.Component;

/**
 * @author LLM
 *
 */
@Component
public class LocalDataSourceService implements SynchDataSource {
    /** 数据源驱动名 */
    private String          driver;
    /** 数据源连接 */
    private String          url;
    /** 数据源用户名 */
    private String          userName;
    /** 数据源密码 */
    private String          passwd;
    /**  */
    private Integer         minIdle;
    /**  */
    private Integer         maxIdle;
    /**  */
    private Integer         maxActive;

    /** 基础数据源 */
    private BasicDataSource baseDataSource = null;
    /** 数据源连接 */
    private Connection      connection     = null;

    /**
     * @see cn.com.chaochuang.synchdata.service.SynchDataSource#getConnection()
     */
    @Override
    public Connection getConnection() {
        try {
            baseDataSource = new BasicDataSource();
            baseDataSource.setMinIdle(minIdle);
            baseDataSource.setMaxIdle(maxIdle);
            baseDataSource.setMaxActive(maxActive);
            baseDataSource.setDriverClassName(driver);
            baseDataSource.setUsername(userName);
            baseDataSource.setPassword(passwd);
            baseDataSource.setDefaultAutoCommit(false);
            baseDataSource.setUrl(url);
            this.connection = baseDataSource.getConnection();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return this.connection;
    }

    /**
     * @return the driver
     */
    public String getDriver() {
        return driver;
    }

    /**
     * @param driver
     *            the driver to set
     */
    public void setDriver(String driver) {
        this.driver = driver;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     *            the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the passwd
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * @param passwd
     *            the passwd to set
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    /**
     * @return the minIdle
     */
    public Integer getMinIdle() {
        return minIdle;
    }

    /**
     * @param minIdle
     *            the minIdle to set
     */
    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }

    /**
     * @return the maxIdle
     */
    public Integer getMaxIdle() {
        return maxIdle;
    }

    /**
     * @param maxIdle
     *            the maxIdle to set
     */
    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    /**
     * @return the maxActive
     */
    public Integer getMaxActive() {
        return maxActive;
    }

    /**
     * @param maxActive
     *            the maxActive to set
     */
    public void setMaxActive(Integer maxActive) {
        this.maxActive = maxActive;
    }

    /**
     * @return the baseDataSource
     */
    public BasicDataSource getBaseDataSource() {
        return baseDataSource;
    }

    /**
     * @param baseDataSource
     *            the baseDataSource to set
     */
    public void setBaseDataSource(BasicDataSource baseDataSource) {
        this.baseDataSource = baseDataSource;
    }

    /**
     * @param connection
     *            the connection to set
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

}
