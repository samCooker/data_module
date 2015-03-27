/*
 * FileName:    FordoController.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月22日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.docwork.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.chaochuang.docwork.domain.FdFordo;
import cn.com.chaochuang.docwork.service.FdFordoService;

/**
 * @author LLM
 *
 */
@Controller
@RequestMapping("docwork/fdfordo")
public class FordoController {

    @Autowired
    private FdFordoService fdFordoService;

    /**
     * 获取用户的待办事宜列表
     *
     * @return
     */
    @RequestMapping("/list.json")
    @ResponseBody
    public Page getList() {
        return fdFordoService.findAllByPage(1, 10);
    }

    /**
     * 获取待办事宜的详细信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/getDetail.json")
    @ResponseBody
    public FdFordo getList(Long id) {
        return fdFordoService.findOne(id);
    }

}
