/*
 * FileName:    DepLinkmanController.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月26日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.commoninfo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.chaochuang.commoninfo.service.DepLinkmanService;

/**
 * @author LLM
 *
 */
@Controller
@RequestMapping("commoninfo/deplinkman")
public class DepLinkmanController {

    @Autowired
    private DepLinkmanService depLinkmanService;

}
