/*
 * FileName:    ApkUploadController.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年7月20日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.sysmanage.apkmanage.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.chaochuang.common.upload.support.PluploadController;
import cn.com.chaochuang.common.util.AttachUtils;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.sysmanage.apkmanage.bean.SysApkInfoCheckShow;
import cn.com.chaochuang.sysmanage.apkmanage.domain.SysApkInfo;
import cn.com.chaochuang.sysmanage.apkmanage.service.SysApkInfoService;

/**
 * @author LLM
 *
 */
@Controller
@RequestMapping("/apkattach")
public class ApkUploadController extends PluploadController {
    @Value(value = "${apkinfo.attach.path}")
    private String              appId;
    @Autowired
    protected SysApkInfoService apkInfoService;

    @Override
    protected String getAppId(HttpServletRequest request) {
        return appId;
    }

    /**
     * 获取APK文件信息（版本号、文件）
     *
     * @param request
     * @param id
     * @param name
     * @return
     */
    @RequestMapping("/getapkinfo.json")
    @ResponseBody
    public SysApkInfoCheckShow getAPKFileInfo(HttpServletRequest request, String id, String name) {
        SysApkInfoCheckShow info = new SysApkInfoCheckShow();
        // 从id获取上传文件记录
        if (Tools.isEmptyString(id)) {
            info.setErrorMessage("无效的APK文件！");
            return info;
        }
        SysApkInfo apkInfo = this.apkInfoService.findOne(Long.valueOf(id));
        if (apkInfo == null) {
            info.setErrorMessage("无效的APK文件！");
            return info;
        }
        // 获取上传文件的MD5码
        String md5 = AttachUtils.getFileMD5(apkInfo.getFilePath());
        if (Tools.isEmptyString(md5)) {
            info.setErrorMessage("无效的APK文件！");
            this.apkInfoService.deleteApkFile(Long.valueOf(id));
            return info;
        }
        // 仅比较MD5码的后8位
        md5 = md5.substring(md5.length() - 8);
        // 对文件的名字进行分解，文件名组成：安装包名_{版本号_MD5后8位}.apk
        int st = name.indexOf("{");
        int ed = name.indexOf("}");
        if (st <= 0 || ed <= 0) {
            info.setErrorMessage("无效的APK文件命名！");
            this.apkInfoService.deleteApkFile(Long.valueOf(id));
            return info;
        }
        String[] fileItem = name.substring(st + 1, ed).split("_");
        if (fileItem.length != 2) {
            info.setErrorMessage("无效的APK文件命名！");
            this.apkInfoService.deleteApkFile(Long.valueOf(id));
            return info;
        }
        // 对符合规范的文件进行MD5校验，若MD5码与文件计算的MD5不一致则为非法文件
        if (!md5.equalsIgnoreCase(fileItem[1])) {
            info.setErrorMessage("上传的APK文件MD5校验值不一致！");
            this.apkInfoService.deleteApkFile(Long.valueOf(id));
            return info;
        }
        info.setPackageName(name.substring(0, name.indexOf("_")));
        info.setVersion(fileItem[0]);
        return info;
    }
}
