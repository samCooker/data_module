/*
 * FileName:    RegisterBeean.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年7月1日 (LJX) 1.0 Create
 */

package cn.com.chaochuang.sysmanage.registerapply.bean;

import cn.com.chaochuang.sysmanage.registerapply.reference.AppAuthStatus;

/**
 * @author LJX
 *
 */
public class RegisterInfo {
    private Long[]        ids;

    private AppAuthStatus status;

    /**
     * @return the ids
     */
    public Long[] getIds() {
        return ids;
    }

    /**
     * @param ids
     *            the ids to set
     */
    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    /**
     * @return the status
     */
    public AppAuthStatus getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(AppAuthStatus status) {
        this.status = status;
    }

}
