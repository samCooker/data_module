/*
 * FileName:    DocFileController.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月22日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.docwork.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author LLM
 *
 */
@Controller
@RequestMapping("docwork/docfile")
public class DocFileController {

    public ModelAndView editDocFile(Long fordoId) {
        ModelAndView mav = new ModelAndView();

        // 从Fdfordo表中获取待办数据，再得到远程系统的rmPendingId,最后通过webservice获取公文办理所需的数据
        return mav;
    }
}
