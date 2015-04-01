/*
 * FileName:    JPushController.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月31日 (LaoZhiYong) 1.0 Create
 */

package cn.com.chaochuang.common.jpush.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.chaochuang.common.user.domain.SysUser;
import cn.com.chaochuang.common.user.service.SysUserService;
import cn.com.chaochuang.common.util.UserHelper;

/**
 * @author LaoZhiYong
 *
 */
@Controller
@RequestMapping("Jpush")
public class JPushController {
    @Autowired
    private SysUserService userSevice;

    @RequestMapping("/saveRegistrationID")
    @ResponseBody
    public String saveRegistrationID(String registrationID) {
        SysUser user = UserHelper.getCurrentUser();
        user.setRegistrationId(registrationID);
        userSevice.saveCurrUser(user);
        return "success";
    }
}
