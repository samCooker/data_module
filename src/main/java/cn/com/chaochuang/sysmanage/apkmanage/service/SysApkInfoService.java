/*
 * FileName:    SysApkInfoService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年7月20日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.sysmanage.apkmanage.service;

import cn.com.chaochuang.common.data.service.CrudRestService;
import cn.com.chaochuang.sysmanage.apkmanage.bean.SysApkInfoShowBean;
import cn.com.chaochuang.sysmanage.apkmanage.domain.SysApkInfo;

/**
 * @author LLM
 *
 */
public interface SysApkInfoService extends CrudRestService<SysApkInfo, Long> {

    /**
     * 保存APK上传信息
     *
     * @param info
     *            APK上传信息
     * @return SysApkInfo
     */
    SysApkInfo saveSysApkInfo(SysApkInfoShowBean info);

    /**
     * 删除APK文件
     * 
     * @param id
     *            apk信息编号
     */
    void deleteApkFile(Long id);
}
