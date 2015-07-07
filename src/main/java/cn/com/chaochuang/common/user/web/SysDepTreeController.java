/*
 * FileName:    SysDepController.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年7月5日 (LJX) 1.0 Create
 */

package cn.com.chaochuang.common.user.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.chaochuang.common.user.bean.DepTreeBean;
import cn.com.chaochuang.common.user.service.SysDepartmentService;

/**
 * @author LJX
 *
 */
@Controller
@RequestMapping("deptree")
public class SysDepTreeController {
    @Autowired
    protected SysDepartmentService depService;

    @RequestMapping("/show")
    public ModelAndView show() {
        ModelAndView mav = new ModelAndView("/dep/dep");
        return mav;
    }

    @RequestMapping("/deptree.json")
    @ResponseBody
    public List<DepTreeBean> depTree(Long id) {
        return this.depService.getDepTree(id);
    }

}
