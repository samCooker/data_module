/*
 * FileName:    PersonalSetController.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2014
 * History:     2014年11月25日 (ljx) 1.0 Create
 */

package cn.com.chaochuang.common.user.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.chaochuang.common.data.web.SimpleCrudController;
import cn.com.chaochuang.common.security.util.UserTool;
import cn.com.chaochuang.common.user.domain.SysUser;
import cn.com.chaochuang.common.user.service.SysUserService;

import com.google.common.collect.Maps;

/**
 * @author ljx
 *
 */
@Controller
@RequestMapping("user/personal")
public class SysPersonalSetController extends SimpleCrudController<SysUser, SysUserService> {

    @Autowired
    private SysUserService service;

    @RequestMapping("/set")
    public ModelAndView set() {
        ModelAndView mav = new ModelAndView("user/personal/set");
        mav.addObject("object", service.findOne(Long.parseLong(UserTool.getInstance().getCurrentUserId())));
        return mav;
    }

    @RequestMapping("/saveperset")
    public ModelAndView savePerset(@Valid SysUser user, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("user/personal/set");
        List<String> messages = new ArrayList<String>();
        if (isCurrUser(user)) {
            if (checkBeforeSave(user, messages)) {
                service.saveCurrUser(user);
                mav = new ModelAndView("redirect:/user/personal/set");
            } else {
                mav.addObject("object", user);
                mav.addObject("messages", messages);
            }
        } else {
            messages.add("设置失败");
            mav.addObject("messages", messages);
            mav.addObject("object", user);
        }
        return mav;
    }

    @RequestMapping("/checkaccount.json")
    public @ResponseBody Map<String, Object> checkSelfPassword(
                    @RequestParam(value = "account", required = false) String account) {
        Map<String, Object> result = Maps.newHashMap();

        if (isNotRepeat(account)) {
            result.put("ok", "");
        } else {
            result.put("error", "账号已存在");
        }

        return result;
    }

    private Boolean checkBeforeSave(SysUser user, List<String> messages) {
        if (StringUtils.isBlank(user.getUserName())) {
            messages.add("姓名不能为空！");
        } else if (user.getUserName().length() > 10) {
            messages.add("姓名不能超过10个字符！");
        }
        // if (StringUtils.isBlank(user.getAccount())) {
        // messages.add("登录账号不能为空！");
        // } else if (user.getAccount().length() > 20) {
        // messages.add("登录账号不能超过20个字符！");
        // }
        // if (!(user.getDepartment() != null)) {
        // messages.add("所属部门必选！");
        // }
        if (user.getAddress() != null && user.getAddress().length() > 100) {
            messages.add("通讯地址不能超过100个字符！");
        }
        if (user.getMobile() != null && user.getMobile().length() > 11) {
            messages.add("联系电话不能超过11个字符！");
        }
        if (user.getEmail() != null && user.getEmail().length() > 30) {
            messages.add("Email不能超过30个字符！");
        }
        return messages.isEmpty();
    }

    private Boolean isNotRepeat(String account) {
        Boolean flag = false;
        if (UserTool.getInstance().getCurrentUserAccount().equals(account)) {
            flag = true;
        }
        if (account != null) {
            if (service.findByAccount(account) == null || service.findByAccount(account).size() <= 0) {
                flag = true;
            }
        }
        return flag;
    }

    private Boolean isCurrUser(SysUser u) {
        SysUser cu = (SysUser) UserTool.getInstance().getCurrentUser();
        if (cu.equals(u)) {
            return true;
        } else {
            return false;
        }
    }

}
