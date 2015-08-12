/*
 * FileName:    ApkManageController.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年7月20日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.sysmanage.apkmanage.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.chaochuang.common.bean.EasyUIPage;
import cn.com.chaochuang.common.beancopy.BeanCopyBuilder;
import cn.com.chaochuang.common.data.persistence.SearchBuilder;
import cn.com.chaochuang.common.util.SearchBuilderHelper;
import cn.com.chaochuang.common.util.SearchListHelper;
import cn.com.chaochuang.sysmanage.apkmanage.bean.SysApkInfoShowBean;
import cn.com.chaochuang.sysmanage.apkmanage.domain.SysApkInfo;
import cn.com.chaochuang.sysmanage.apkmanage.reference.ApkDataStatus;
import cn.com.chaochuang.sysmanage.apkmanage.service.SysApkInfoService;

/**
 * @author LLM
 *
 */
@Controller
@RequestMapping("apkmanage/")
public class ApkManageController {
    @Autowired
    protected SysApkInfoService apkInfoService;
    @Autowired
    protected ConversionService conversionService;

    /**
     * 进入APK文件管理列表界面
     *
     * @return
     */
    @RequestMapping("list")
    public ModelAndView apkInfo() {
        ModelAndView mav = new ModelAndView("/apkmanage/list");
        return mav;
    }

    /**
     * 查询APK文件上传记录列表
     *
     * @param page
     * @param rows
     * @param request
     * @return
     */
    @RequestMapping("list.json")
    @ResponseBody
    public EasyUIPage apkInfoList(Integer page, Integer rows, HttpServletRequest request) {
        SearchBuilder<SysApkInfo, Long> searchBuilder = SearchBuilderHelper.bindSearchBuilder(conversionService,
                        request);
        searchBuilder.appendSort(Direction.DESC, "id");
        searchBuilder.getFilterBuilder().equal("status", ApkDataStatus.正常数据);
        SearchListHelper<SysApkInfo> listhelper = new SearchListHelper<SysApkInfo>();
        listhelper.execute(searchBuilder, apkInfoService.getRepository(), page, rows);

        EasyUIPage p = new EasyUIPage();
        p.setRows(BeanCopyBuilder.buildList(listhelper.getList(), SysApkInfoShowBean.class));
        p.setTotal(listhelper.getCount());
        return p;
    }

    /**
     * 进入APK上传界面
     *
     * @return
     */
    @RequestMapping("uploadapk")
    public ModelAndView apkUpload() {
        ModelAndView mav = new ModelAndView("/apkmanage/edit");
        return mav;
    }

    /**
     * 保存APK上传信息
     *
     * @param info
     * @return
     */
    @RequestMapping("saveapk.json")
    @ResponseBody
    public Boolean saveUser(SysApkInfoShowBean info) {
        this.apkInfoService.saveSysApkInfo(info);
        return true;
    }
}
