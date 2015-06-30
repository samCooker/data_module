/*
 * FileName:    SysRegisterApplyController.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年6月29日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.registerapply.web;

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
import cn.com.chaochuang.common.user.domain.SysUser;
import cn.com.chaochuang.common.util.SearchListHelper;
import cn.com.chaochuang.common.util.UserHelper;
import cn.com.chaochuang.registerapply.bean.SysRegisterApplyShowBean;
import cn.com.chaochuang.registerapply.domain.SysRegisterApply;
import cn.com.chaochuang.registerapply.service.SysRegisterApplyService;

/**
 * @author Shicx
 *
 */
@Controller
@RequestMapping("registerapp/")
public class SysRegisterApplyController {

    @Autowired
    private SysRegisterApplyService registerApplyService;
    @Autowired
    protected ConversionService     conversionService;

    @RequestMapping("/list")
    public ModelAndView toAuthorityPage() {
        ModelAndView mav = new ModelAndView("/registerapply/list");
        return mav;
    }

    @RequestMapping("/list.json")
    @ResponseBody
    public EasyUIPage getAuthorityData(Integer page, Integer rows, SysUser user, HttpServletRequest request) {
        SearchBuilder<SysRegisterApply, Long> searchBuilder = new SearchBuilder<SysRegisterApply, Long>(
                        conversionService);
        searchBuilder.clearSearchBuilder().findSearchParam(request);
        searchBuilder.appendSort(Direction.DESC, "applyTime");
        registerApplyService.toRegisterAppAuthority(UserHelper.getCurrentUser().getId(), "abc");
        SearchListHelper<SysRegisterApply> listhelper = new SearchListHelper<SysRegisterApply>();
        listhelper.execute(searchBuilder, registerApplyService.getRepository(), page, rows);

        EasyUIPage p = new EasyUIPage();
        p.setRows(BeanCopyBuilder.buildList(listhelper.getList(), SysRegisterApplyShowBean.class));
        p.setTotal(listhelper.getCount());
        return p;
    }

    @RequestMapping("/apply")
    @ResponseBody
    public boolean toRegisterAppAuthority(Long userId, String imeiCode) {
        try {
            return registerApplyService.toRegisterAppAuthority(userId, imeiCode);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
