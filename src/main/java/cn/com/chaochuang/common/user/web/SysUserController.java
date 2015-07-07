/*
 * FileName:    SysUserController.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年7月5日 (LJX) 1.0 Create
 */

package cn.com.chaochuang.common.user.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.chaochuang.common.bean.EasyUIPage;
import cn.com.chaochuang.common.beancopy.BeanCopyBuilder;
import cn.com.chaochuang.common.data.persistence.SearchBuilder;
import cn.com.chaochuang.common.user.bean.UserShowBean;
import cn.com.chaochuang.common.user.domain.SysUser;
import cn.com.chaochuang.common.user.service.SysUserService;
import cn.com.chaochuang.common.util.SearchBuilderHelper;
import cn.com.chaochuang.common.util.SearchListHelper;

/**
 * @author LJX
 *
 */
@Controller
@RequestMapping("user")
public class SysUserController {

    @Autowired
    protected SysUserService    userService;

    @Autowired
    protected ConversionService conversionService;

    @RequestMapping("list.json")
    @ResponseBody
    public EasyUIPage list(Integer page, Integer rows, Long parentId, HttpServletRequest request) {
        SearchBuilder<SysUser, Long> searchBuilder = SearchBuilderHelper.bindSearchBuilder(conversionService, request);
        SearchListHelper<SysUser> listhelper = new SearchListHelper<SysUser>();
        searchBuilder.getFilterBuilder().equal("rmDepId", parentId);
        listhelper.execute(searchBuilder, userService.getRepository(), page, rows);
        EasyUIPage p = new EasyUIPage();
        p.setRows(BeanCopyBuilder.buildList(listhelper.getList(), UserShowBean.class));
        p.setTotal(listhelper.getCount());
        return p;
    }

}
