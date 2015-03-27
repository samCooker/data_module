/*
 * FileName:    SysDepartmentEasyUIController.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年1月25日 (LaoZhiYong) 1.0 Create
 */

package cn.com.chaochuang.common.user.web;

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
import cn.com.chaochuang.common.user.bean.SysDepartmentUIShowBean;
import cn.com.chaochuang.common.user.domain.SysDepartment;
import cn.com.chaochuang.common.user.service.SysDepartmentService;
import cn.com.chaochuang.common.util.SearchListHelper;

/**
 * @author LaoZhiYong
 *
 */
@Controller
@RequestMapping("sysdeparment/easyui")
public class SysDepartmentEasyUIController {

    @Autowired
    private SysDepartmentService depService;

    @Autowired
    protected ConversionService  conversionService;

    @RequestMapping("/list")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("/sysdep/easyuilist");
        return mav;
    };

    @RequestMapping("/list.json")
    @ResponseBody
    public EasyUIPage listjson(Integer page, Integer rows, SysDepartment dep, HttpServletRequest request) {
        SearchBuilder<SysDepartment, Long> searchBuilder = new SearchBuilder<SysDepartment, Long>(conversionService);
        searchBuilder.clearSearchBuilder().findSearchParam(request);
        searchBuilder.appendSort(Direction.DESC, "orderNum");

        SearchListHelper<SysDepartment> listhelper = new SearchListHelper<SysDepartment>();
        listhelper.execute(searchBuilder, depService.getRepository(), page, rows);

        EasyUIPage p = new EasyUIPage();
        p.setRows(BeanCopyBuilder.buildList(listhelper.getList(), SysDepartmentUIShowBean.class));
        p.setTotal(listhelper.getCount());
        return p;
    }

    @RequestMapping("/new")
    public ModelAndView newEntity() {
        ModelAndView mav = new ModelAndView("/sysdep/easyuiedit");
        return mav;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(Long id) {
        ModelAndView mav = new ModelAndView("/sysuser/easyuiedit");
        mav.addObject("obj", depService.findOne(id));
        return mav;
    }

    @RequestMapping("/save.json")
    @ResponseBody
    public Result update(SysDepartment dep, HttpServletRequest request, HttpServletResponse response) {
        depService.persist(dep);
        return new Result("success", null);
    }
}
