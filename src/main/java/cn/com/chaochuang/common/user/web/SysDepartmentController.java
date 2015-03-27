/*
 * FileName:    SysDepartmentController.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2014
 * History:     2014年7月18日 (LaoZhiYong) 1.0 Create
 */

package cn.com.chaochuang.common.user.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.com.chaochuang.common.user.service.SysDepartmentService;

/**
 * @author LaoZhiYong
 *
 */
@Controller
@RequestMapping("sysdeparment")
public class SysDepartmentController {

    @Autowired
    private SysDepartmentService Service;

    @RequestMapping("/departmentTree")
    public ModelAndView test() {
        ModelAndView mav = new ModelAndView("common/department/selectDepartment");
        List list = Service.getDepartmentTree();
        mav.addObject("departmentTree", list);
        return mav;
    }
}
