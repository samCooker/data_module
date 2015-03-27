package cn.com.chaochuang.common.user.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.chaochuang.common.data.web.SimpleCrudController;
import cn.com.chaochuang.common.user.domain.SysUser;
import cn.com.chaochuang.common.user.service.SysUserService;
import cn.com.chaochuang.common.util.HashUtil;

@Controller
@RequestMapping("sysuser")
public class SysUserController extends SimpleCrudController<SysUser, SysUserService> {

    @Autowired
    private SysUserService service;

    @Value("${user.initpassword}")
    private String         initPasswordPlainText;

    @RequestMapping("/getuser")
    public String getCurrentUser() {
        return "interaction/getuser";
    }

    private Boolean checkBeforeSave(SysUser user, List<String> messages) {
        if (StringUtils.isBlank(user.getUserName())) {
            messages.add("姓名不能为空！");
        } else if (user.getUserName().length() > 10) {
            messages.add("姓名不能超过10个字符！");
        }
        if (StringUtils.isBlank(user.getAccount())) {
            messages.add("登录账号不能为空！");
        } else if (user.getAccount().length() > 20) {
            messages.add("登录账号不能超过20个字符！");
        }
        if (!(user.getDepartment() != null)) {
            messages.add("所属部门必选！");
        }
        return messages.isEmpty();
    }

    @Override
    public ModelAndView save(SysUser entity, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("sysuser/edit");
        List<String> messages = new ArrayList<String>();
        if (isNotRepeat(entity.getAccount(), entity.getId())) {
            if (checkBeforeSave(entity, messages)) {
                return super.save(service.convert(entity), bindingResult, request);
            } else {
                mav.addObject("object", entity);
                mav.addObject("messages", messages);
                return mav;
            }
        } else {
            messages.add("账号已存在！");
            mav.addObject("messages", messages);
            mav.addObject("object", entity);
            return mav;
        }
    }

    @Override
    protected void beforeSave(ModelAndView mav, SysUser entity, BindingResult bindingResult, HttpServletRequest request) {
        super.beforeSave(mav, entity, bindingResult, request);
        if (entity.isNew() || StringUtils.isBlank(entity.getPassword())) {
            if (StringUtils.isNotBlank(initPasswordPlainText)) {
                entity.setPassword(HashUtil.md5Text(initPasswordPlainText));
                entity.setRegDate(new Date());
            }
        }
    }

    @RequestMapping("/checkaccount.json")
    public @ResponseBody String checkaccount(@RequestParam(value = "account", required = false) String account,

    @RequestParam(value = "id", required = false) Long id) {
        String result = "";
        if (isNotRepeat(account, id)) {
            result = "ok";
        } else {
            result = "error";
        }

        return result;
    }

    private Boolean isNotRepeat(String account, Long id) {
        Boolean flag = false;
        if (account != null) {
            List<SysUser> list = service.findByAccount(account.trim());
            if (!(list != null && list.size() > 0)) {
                flag = true;
            }
            if (id != null) {
                SysUser u = service.findOne(id);
                if (u != null && u.getAccount().equals(account)) {
                    flag = true;
                }
            }

        }

        return flag;
    }

    public String getInitPasswordPlainText() {
        return initPasswordPlainText;
    }

    public void setInitPasswordPlainText(String initPasswordPlainText) {
        this.initPasswordPlainText = initPasswordPlainText;
    }

}
