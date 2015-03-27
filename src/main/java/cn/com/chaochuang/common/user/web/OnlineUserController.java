package cn.com.chaochuang.common.user.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.com.chaochuang.common.security.service.OnlineUserInfo;

@Controller
@RequestMapping("onlineuser")
public class OnlineUserController {

    @Autowired(required = false)
    private OnlineUserInfo onlineUserInfo;

    // 踢在线用户
    @RequestMapping("/del")
    public ModelAndView del(String[] names) {
        ModelAndView mav = new ModelAndView("redirect:list");

        if ((null != onlineUserInfo) && (null != names) && (names.length > 0)) {
            for (String username : names) {
                onlineUserInfo.kickUser(username);
            }
        }

        return mav;
    }

    // 在线用户列表
    @RequestMapping("/list")
    public ModelAndView list(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("onlineuser/list");

        if (null != onlineUserInfo) {
            List<?> dataList = onlineUserInfo.getOnlineUserList();
            mav.addObject("page", new PageImpl(dataList));
        }

        return mav;
    }

}
