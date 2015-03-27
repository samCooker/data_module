/*
 * FileName:    SelectUserController.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2014
 * History:     2014年11月16日 (guig) 1.0 Create
 */

package cn.com.chaochuang.common.user.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.chaochuang.common.user.domain.SysUser;
import cn.com.chaochuang.common.user.service.SysDepartmentService;
import cn.com.chaochuang.common.user.service.SysUserService;
import cn.com.chaochuang.common.util.ListMapBuilder;

/**
 * @author guig
 *
 */
@Controller
@RequestMapping("selectdata")
public class SelectUserController {

    @Autowired
    private SysDepartmentService depService;

    @Autowired
    private SysUserService       userService;

    @RequestMapping("/chooseuser")
    public ModelAndView chooseUser() {
        ModelAndView mav = new ModelAndView("selectdata/chooseuser");
        List list = depService.getAllDepartment();
        mav.addObject("departmentList", list);
        return mav;
    }

    @RequestMapping("/choosemutiuser")
    public ModelAndView chooseMutiUser() {
        ModelAndView mav = new ModelAndView("selectdata/choosemutiuser");
        List list = depService.getAllDepartment();
        mav.addObject("departmentList", list);
        return mav;
    }

    @RequestMapping("/depuserlist")
    public @ResponseBody List<Map<String, Object>> getUserInDepartment(Long depid) {
        if (null != depid) {
            List<SysUser> userList = userService.findByDepartmentId(depid);
            return new ListMapBuilder().addFieldMap("id", "id").addFieldMap("userName", "name")
                            .convertToListMap(userList);
        }
        return null;
    }
}
