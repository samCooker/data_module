/*
 * FileName:    MailController.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年7月3日 (LJX) 1.0 Create
 */

package cn.com.chaochuang.commoninfo.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.chaochuang.common.bean.EasyUIPage;
import cn.com.chaochuang.common.beancopy.BeanCopyBuilder;
import cn.com.chaochuang.common.data.persistence.SearchBuilder;
import cn.com.chaochuang.common.util.SearchBuilderHelper;
import cn.com.chaochuang.common.util.SearchListHelper;
import cn.com.chaochuang.commoninfo.bean.MailUserShowBean;
import cn.com.chaochuang.commoninfo.domain.MailOrg;
import cn.com.chaochuang.commoninfo.domain.MailUser;
import cn.com.chaochuang.commoninfo.service.MailOrgService;
import cn.com.chaochuang.commoninfo.service.MailUserService;

/**
 * @author LJX
 *
 */
@Controller
@RequestMapping("mail/")
public class MailController {

    @Autowired
    protected MailUserService   mailUserService;

    @Autowired
    protected MailOrgService    mailOrgService;

    @Autowired
    protected ConversionService conversionService;

    @RequestMapping("userlist")
    public ModelAndView mailUser() {
        ModelAndView mav = new ModelAndView("/mail/userlist");
        return mav;
    }

    @RequestMapping("userlist.json")
    @ResponseBody
    public EasyUIPage toAipLawListDate(Integer page, Integer rows, HttpServletRequest request) {
        SearchBuilder<MailUser, Long> searchBuilder = SearchBuilderHelper.bindSearchBuilder(conversionService, request);
        searchBuilder.appendSort(Direction.DESC, "id");

        SearchListHelper<MailUser> listhelper = new SearchListHelper<MailUser>();
        listhelper.execute(searchBuilder, mailUserService.getRepository(), page, rows);

        EasyUIPage p = new EasyUIPage();
        p.setRows(BeanCopyBuilder.buildList(listhelper.getList(), MailUserShowBean.class));
        p.setTotal(listhelper.getCount());
        return p;
    }

    @RequestMapping("edituser")
    public ModelAndView addUser(Long id) {
        ModelAndView mav = new ModelAndView("/mail/edituser");
        if (id != null) {
            MailUser mailUser = this.mailUserService.findOne(id);
            mav.addObject("mailUser", mailUser);
        }
        return mav;
    }

    @RequestMapping("saveuser.json")
    @ResponseBody
    public Boolean saveUser(MailUser info) {
        if (info.getAccount() == null || info.getDomain() == null) {
            return false;
        }
        try {
            this.mailUserService.saveUser(info);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @RequestMapping("deluser.json")
    @ResponseBody
    public Boolean delUser(Long id) {
        try {
            this.mailUserService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @RequestMapping("orglist.json")
    @ResponseBody
    public EasyUIPage orgList(Integer page, Integer rows, HttpServletRequest request) {
        SearchBuilder<MailOrg, Long> searchBuilder = SearchBuilderHelper.bindSearchBuilder(conversionService, request);
        searchBuilder.appendSort(Direction.DESC, "id");

        SearchListHelper<MailOrg> listhelper = new SearchListHelper<MailOrg>();
        listhelper.execute(searchBuilder, mailOrgService.getRepository(), page, rows);

        EasyUIPage p = new EasyUIPage();
        p.setRows(BeanCopyBuilder.buildList(listhelper.getList(), MailOrg.class));
        p.setTotal(listhelper.getCount());
        return p;
    }

}
