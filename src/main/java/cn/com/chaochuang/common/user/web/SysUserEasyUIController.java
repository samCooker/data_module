/*
 * FileName:    SysUserEasyUIController.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年1月23日 (LaoZhiYong) 1.0 Create
 */

package cn.com.chaochuang.common.user.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.chaochuang.common.bean.EasyUIPage;
import cn.com.chaochuang.common.bean.Result;
import cn.com.chaochuang.common.beancopy.BeanCopyBuilder;
import cn.com.chaochuang.common.data.persistence.SearchBuilder;
import cn.com.chaochuang.common.user.bean.SysUserEasyUIShowBean;
import cn.com.chaochuang.common.user.domain.SysUser;
import cn.com.chaochuang.common.user.service.SysUserService;
import cn.com.chaochuang.common.util.SearchListHelper;

/**
 * @author LaoZhiYong
 *
 */
@Controller
@RequestMapping("sysuser/easyui")
public class SysUserEasyUIController {
    @Autowired
    private SysUserService      uservice;

    @Autowired
    protected ConversionService conversionService;

    @RequestMapping("/list")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("/sysuser/easyuilist");
        return mav;
    }

    @RequestMapping("/list.json")
    @ResponseBody
    public EasyUIPage listjson(Integer page, Integer rows, SysUser user, HttpServletRequest request) {
        SearchBuilder<SysUser, Long> searchBuilder = new SearchBuilder<SysUser, Long>(conversionService);
        searchBuilder.clearSearchBuilder().findSearchParam(request);
        searchBuilder.appendSort(Direction.DESC, "id");

        SearchListHelper<SysUser> listhelper = new SearchListHelper<SysUser>();
        listhelper.execute(searchBuilder, uservice.getRepository(), page, rows);

        EasyUIPage p = new EasyUIPage();
        p.setRows(BeanCopyBuilder.buildList(listhelper.getList(), SysUserEasyUIShowBean.class));
        p.setTotal(listhelper.getCount());
        return p;
    }

    @RequestMapping("/del.json")
    @ResponseBody
    public Result del(Long id) {
        uservice.delete(id);
        return new Result("success", null);
    }

    @RequestMapping("/new")
    public ModelAndView newEntity() {
        ModelAndView mav = new ModelAndView("/sysuser/easyuiedit");
        return mav;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(Long id) {
        ModelAndView mav = new ModelAndView("/sysuser/easyuiedit");
        mav.addObject("obj", uservice.findOne(id));
        return mav;
    }

    @RequestMapping("/save.json")
    @ResponseBody
    public Result update(SysUser u, HttpServletRequest request, HttpServletResponse response) {
        u.setRegDate(new Date());
        uservice.persist(u);
        return new Result("success", null);
    }

}
