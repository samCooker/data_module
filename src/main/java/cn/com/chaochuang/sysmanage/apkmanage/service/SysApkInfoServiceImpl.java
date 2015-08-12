/*
 * FileName:    SysApkInfoServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年7月20日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.sysmanage.apkmanage.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.upload.support.UploadFileInfoPersistence;
import cn.com.chaochuang.common.upload.support.UploadFileItem;
import cn.com.chaochuang.common.user.domain.SysUser;
import cn.com.chaochuang.common.util.AttachUtils;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.common.util.UserHelper;
import cn.com.chaochuang.sysmanage.apkmanage.bean.SysApkInfoShowBean;
import cn.com.chaochuang.sysmanage.apkmanage.domain.SysApkInfo;
import cn.com.chaochuang.sysmanage.apkmanage.reference.ApkDataStatus;
import cn.com.chaochuang.sysmanage.apkmanage.repository.SysApkInfoRepository;

/**
 * @author LLM
 *
 */
@Service
@Transactional
public class SysApkInfoServiceImpl extends SimpleLongIdCrudRestService<SysApkInfo> implements SysApkInfoService,
                UploadFileInfoPersistence {
    @Value(value = "${apkinfo.attach.path}")
    private String               appid;
    @Value(value = "${upload.rootpath}")
    private String               root;
    @Autowired
    private SysApkInfoRepository repository;

    @Override
    public SimpleDomainRepository<SysApkInfo, Long> getRepository() {
        return repository;
    }

    /**
     * @see cn.com.chaochuang.common.upload.support.UploadFileInfoPersistence#getAppId()
     */
    @Override
    public String getAppId() {
        return appid;
    }

    /**
     * @see cn.com.chaochuang.sysmanage.apkmanage.service.SysApkInfoService#deleteApkFile(java.lang.Long)
     */
    @Override
    public void deleteApkFile(Long id) {
        if (id == null) {
            return;
        }
        SysApkInfo apkInfo = this.repository.findOne(id);
        if (apkInfo == null) {
            return;
        }
        AttachUtils.removeFile(apkInfo.getFilePath());
        this.repository.delete(apkInfo);
    }

    /**
     * @see cn.com.chaochuang.sysmanage.apkmanage.service.SysApkInfoService#saveSysApkInfo(cn.com.chaochuang.sysmanage.apkmanage.bean.SysApkInfoShowBean)
     */
    @Override
    public SysApkInfo saveSysApkInfo(SysApkInfoShowBean info) {
        SysApkInfo apkInfo = new SysApkInfo();
        if (Tools.isEmptyString(info.getAttach())) {
            return apkInfo;
        }
        apkInfo = this.repository.findOne(Long.valueOf(info.getAttach()));
        if (apkInfo == null) {
            return apkInfo;
        }
        SysUser user = UserHelper.getCurrentUser();
        apkInfo.setStatus(ApkDataStatus.正常数据);
        apkInfo.setPackageName(info.getPackageName());
        apkInfo.setVersion(info.getVersion());
        apkInfo.setPackageScript(info.getPackageScript());
        apkInfo.setUpdateFlag(info.getUpdateFlag());
        apkInfo.setUploadId(user.getId());
        apkInfo.setUploadName(user.getUserName());
        apkInfo.setUploadTime(new Date());
        this.repository.save(apkInfo);
        return apkInfo;
    }

    /**
     * @see cn.com.chaochuang.common.upload.support.UploadFileInfoPersistence#saveUploadFileInfo(cn.com.chaochuang.common.upload.support.UploadFileItem)
     */
    @Override
    public String saveUploadFileInfo(UploadFileItem fileItem) {
        SysApkInfo info = new SysApkInfo();
        info.setTrueName(fileItem.getTrueName());
        info.setFilePath(this.root + "/" + fileItem.getSavePath() + "/" + fileItem.getSaveName());
        info.setFileSize(fileItem.getFileSize());
        info.setStatus(ApkDataStatus.附件上传);
        info.setMdfCode(AttachUtils.getFileMD5(info.getFilePath()));
        this.repository.save(info);
        return info.getId().toString();
    }

    /**
     * @see cn.com.chaochuang.common.upload.support.UploadFileInfoPersistence#getUploadFileInfo(java.lang.String)
     */
    @Override
    public UploadFileItem getUploadFileInfo(String id) {
        // TODO Auto-generated method stub
        return null;
    }

}
