/*
 * FileName:    SysPasswordController.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2014
 * History:     2014年10月10日 (guig) 1.0 Create
 */

package cn.com.chaochuang.common.user.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.chaochuang.common.security.util.UserTool;
import cn.com.chaochuang.common.user.domain.SysUser;
import cn.com.chaochuang.common.user.service.SysUserService;
import cn.com.chaochuang.common.util.HashUtil;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @author guig
 *
 */
@Controller
@RequestMapping("userpwd")
public class SysPasswordController {

    private final static int    PASSWORD_MIN_LENGTH = 10;

    private final static char[] digits              = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

    private final static char[] specialChar         = UserTool.specialChar;
    private final static String specialString       = new String(specialChar);

    @Autowired
    private SysUserService      service;

    /**
     * 检查是否包含至少一个大写字母
     *
     * @param password
     * @return
     */
    private boolean hasUpperChar(String password) {
        return !password.equals(password.toLowerCase());
    }

    /**
     * 检查是否包含至少一个小写字母
     *
     * @param password
     * @return
     */
    private boolean hasLowerChar(String password) {
        return !password.equals(password.toUpperCase());
    }

    /**
     * 检查是否包含至少一个数字字符
     *
     * @param password
     * @return
     */
    private boolean hasNumberChar(String password) {
        return StringUtils.indexOfAny(password, digits) >= 0;
    }

    private boolean hasSpecialChar(String password) {
        return StringUtils.indexOfAny(password, specialChar) >= 0;
    }

    private boolean passwordIsOk(String password, List<String> messages, final String oldPassword) {
        if (StringUtils.isNotBlank(oldPassword) && oldPassword.equals(password)) {
            messages.add("新密码不能和原密码相同");
        }
        if (StringUtils.isBlank(password)) {
            messages.add("密码不能为空");
        }
        if (password.length() < PASSWORD_MIN_LENGTH) {
            messages.add("密码长度必须大于等于" + Integer.toString(PASSWORD_MIN_LENGTH) + "个字符");
        }
        if (!hasLowerChar(password)) {
            messages.add("密码要有至少一个小写字母");
        }
        if (!hasUpperChar(password)) {
            messages.add("密码要有至少一个大写字母");
        }
        if (!hasNumberChar(password)) {
            messages.add("密码要有至少一个数字");
        }
        if (!hasSpecialChar(password)) {
            messages.add("密码至少有一个如下的特殊字符" + specialString);
        }
        return messages.isEmpty();
    }

    private boolean isOldPassword(String password) {
        String uid = UserTool.getInstance().getCurrentUserId();
        if (StringUtils.isNotBlank(uid)) {
            SysUser user = service.findOne(Long.valueOf(uid));
            if (null != user) {
                String md5 = null;
                if (null != password && !password.isEmpty())
                    md5 = HashUtil.md5Text(password);

                return user.getPassword().equals(md5);
            }
        }

        return false;
    }

    private boolean doChangePassword(List<String> messages, String oldpwd, String newpwd1, String newpwd2) {
        if (isOldPassword(oldpwd)) {
            if (StringUtils.isNotBlank(newpwd1)) {
                if (newpwd1.equals(newpwd2)) {
                    if (passwordIsOk(newpwd1, messages, oldpwd)) {
                        SysUser currentUser = (SysUser) UserTool.getInstance().getCurrentUser();
                        if (null != currentUser) {
                            SysUser user = service.findOne(currentUser.getId());
                            if (null != user) {
                                user.setPassword(HashUtil.md5Text(newpwd1));
                                user.setLastPasswdDate(new Date());
                                service.persist(user);
                                currentUser.setLastPasswdDate(user.getLastPasswdDate());
                                return true;
                            }
                        }
                    }
                } else {
                    messages.add("新密码和确认新密码不相同！");
                }
            } else {
                messages.add("新密码不能为空！");
            }
        } else {
            messages.add("原密码不正确！");
        }

        return false;
    }

    @RequestMapping(value = "/chgpwd", method = RequestMethod.GET)
    public ModelAndView changePasswordForm() {
        ModelAndView mav = new ModelAndView("user/pwd/chgpwd");
        return mav;
    }

    @RequestMapping(value = "/chgpwd", method = RequestMethod.POST)
    public ModelAndView changePassword(@RequestParam(value = "oldpwd", required = false) String oldpwd,
                    @RequestParam(value = "newpwd1", required = false) String newpwd1,
                    @RequestParam(value = "newpwd2", required = false) String newpwd2) {
        ModelAndView mav = new ModelAndView("user/pwd/chgpwd");

        List<String> messages = new ArrayList<String>();

        if (doChangePassword(messages, oldpwd, newpwd1, newpwd2)) {
            mav = new ModelAndView("user/pwd/chgpwdok");
        }

        if (!messages.isEmpty()) {
            mav.addObject("messages", messages);
        }

        return mav;
    }

    private void addInfo(ModelAndView mav) {
        SysUser user = (SysUser) UserTool.getInstance().getCurrentUser();
        List<String> info = Lists.newArrayList();

        if (null == user.getLastPasswdDate()) {
            info.add("首次登录，必须修改密码。");
        } else {
            info.add("密码过期，请设置新的密码。");
        }

        mav.addObject("info", info);
    }

    @RequestMapping(value = "/needchgpwd", method = RequestMethod.GET)
    public ModelAndView needChangePasswordForm() {
        ModelAndView mav = changePasswordForm();
        addInfo(mav);
        return mav;
    }

    @RequestMapping(value = "/needchgpwd", method = RequestMethod.POST)
    public ModelAndView needChangePassword(String oldpwd, String newpwd1, String newpwd2, HttpServletRequest request) {
        ModelAndView mav = changePassword(oldpwd, newpwd1, newpwd2);

        if (!mav.getModel().isEmpty()) {
            addInfo(mav);
        }

        return mav;
    }

    @RequestMapping("/checkselfpassword.json")
    public @ResponseBody Map<String, Object> checkSelfPassword(
                    @RequestParam(value = "password", required = false) String password) {

        Map<String, Object> result = Maps.newHashMap();

        if (isOldPassword(password)) {
            result.put("ok", "");
        } else {
            result.put("error", "密码错误");
        }

        return result;

    }

}
