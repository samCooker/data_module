/*
 * FileName:    AuditWatcher.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年10月13日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.audit.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;

import cn.com.chaochuang.common.data.domain.LongIdEntity;

/**
 * @author LLM
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "watcher_id")) })
public class AuditWatcher extends LongIdEntity {
    /** 原系统观察员编号 */
    private Long   rmWatcherId;
    /** 原系统审查任务编号 */
    private Long   auditTaskId;
    /** 观察员姓名 */
    private String watcherCheckName;
    /** 承办单位编号 */
    private Long   handleUnitId;
    /** 观察员部门名称 */
    private String watcherCheckDepName;
    /** 观察员单位编号 */
    private Long   watcherCheckUnitId;

    /**
     * @return the rmWatcherId
     */
    public Long getRmWatcherId() {
        return rmWatcherId;
    }

    /**
     * @param rmWatcherId
     *            the rmWatcherId to set
     */
    public void setRmWatcherId(Long rmWatcherId) {
        this.rmWatcherId = rmWatcherId;
    }

    /**
     * @return the auditTaskId
     */
    public Long getAuditTaskId() {
        return auditTaskId;
    }

    /**
     * @param auditTaskId
     *            the auditTaskId to set
     */
    public void setAuditTaskId(Long auditTaskId) {
        this.auditTaskId = auditTaskId;
    }

    /**
     * @return the watcherCheckName
     */
    public String getWatcherCheckName() {
        return watcherCheckName;
    }

    /**
     * @param watcherCheckName
     *            the watcherCheckName to set
     */
    public void setWatcherCheckName(String watcherCheckName) {
        this.watcherCheckName = watcherCheckName;
    }

    /**
     * @return the handleUnitId
     */
    public Long getHandleUnitId() {
        return handleUnitId;
    }

    /**
     * @param handleUnitId
     *            the handleUnitId to set
     */
    public void setHandleUnitId(Long handleUnitId) {
        this.handleUnitId = handleUnitId;
    }

    /**
     * @return the watcherCheckDepName
     */
    public String getWatcherCheckDepName() {
        return watcherCheckDepName;
    }

    /**
     * @param watcherCheckDepName
     *            the watcherCheckDepName to set
     */
    public void setWatcherCheckDepName(String watcherCheckDepName) {
        this.watcherCheckDepName = watcherCheckDepName;
    }

    /**
     * @return the watcherCheckUnitId
     */
    public Long getWatcherCheckUnitId() {
        return watcherCheckUnitId;
    }

    /**
     * @param watcherCheckUnitId
     *            the watcherCheckUnitId to set
     */
    public void setWatcherCheckUnitId(Long watcherCheckUnitId) {
        this.watcherCheckUnitId = watcherCheckUnitId;
    }

}
