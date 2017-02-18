/*
 * FileName:    DesktopController.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2017
 * History:     2017年01月26日 (Shicx) 1.0 Create
 */

package dbdata.common.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Shicx
 */
@Controller
@RequestMapping("menu")
public class DesktopController {

    @RequestMapping("desktop.do")
    public ModelAndView toDesktopPage(){
        ModelAndView modelAndView = new ModelAndView("/desktop");

        return modelAndView;
    }

}

