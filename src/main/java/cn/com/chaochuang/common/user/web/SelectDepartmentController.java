/*
 * FileName:    SelectDepartmentController.java
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

import cn.com.chaochuang.common.user.domain.SysDepartment;
import cn.com.chaochuang.common.user.service.SysDepartmentService;
import cn.com.chaochuang.common.util.ListMapBuilder;

/**
 * @author guig
 *
 */
@Controller
@RequestMapping("selectdata")
public class SelectDepartmentController {

    @Autowired
    private SysDepartmentService service;

    @RequestMapping("/choosedepartment")
    public ModelAndView chooseDepartment() {
        ModelAndView mav = new ModelAndView("selectdata/choosedepartment");
        List list = service.getAllDepartment();
        mav.addObject("departmentList", list);
        return mav;
    }

    @RequestMapping("/subdeplist")
    public @ResponseBody List<Map<String, Object>> getSubDepartment(Long parentid) {
        if (null != parentid) {
            List<SysDepartment> userList = service.getSubDepartmentByParentId(parentid);
            return new ListMapBuilder().addFieldMap("id", "id").addFieldMap("depName", "name")
                            .convertToListMap(userList);
        }
        return null;
    }
}
