/*
 * FileName:    SysRegisterApplyController.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年6月29日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.sysmanage.registerapply.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.chaochuang.common.bean.EasyUIPage;
import cn.com.chaochuang.common.beancopy.BeanCopyBuilder;
import cn.com.chaochuang.common.data.persistence.SearchBuilder;
import cn.com.chaochuang.common.user.domain.SysUser;
import cn.com.chaochuang.common.util.SearchListHelper;
import cn.com.chaochuang.sysmanage.registerapply.bean.SysRegisterApplyShowBean;
import cn.com.chaochuang.sysmanage.registerapply.domain.SysRegisterApply;
import cn.com.chaochuang.sysmanage.registerapply.reference.AppAuthStatus;
import cn.com.chaochuang.sysmanage.registerapply.service.SysRegisterApplyService;

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

    @RequestMapping("list")
    public ModelAndView toAuthorityPage() {
        ModelAndView mav = new ModelAndView("/registerapply/list");
        return mav;
    }

    @RequestMapping("list.json")
    @ResponseBody
    public EasyUIPage getAuthorityData(Integer page, Integer rows, SysUser user, HttpServletRequest request) {
        SearchBuilder<SysRegisterApply, Long> searchBuilder = new SearchBuilder<SysRegisterApply, Long>(
                        conversionService);
        searchBuilder.clearSearchBuilder().findSearchParam(request);
        searchBuilder.appendSort(Direction.DESC, "applyTime");
        SearchListHelper<SysRegisterApply> listhelper = new SearchListHelper<SysRegisterApply>();
        listhelper.execute(searchBuilder, registerApplyService.getRepository(), page, rows);
        EasyUIPage p = new EasyUIPage();
        p.setRows(BeanCopyBuilder.buildList(listhelper.getList(), SysRegisterApplyShowBean.class));
        p.setTotal(listhelper.getCount());
        return p;
    }

    @RequestMapping("apply.json")
    @ResponseBody
    public boolean toRegisterAppAuthority(Long userId, String imeiCode) {
        try {
            return registerApplyService.toRegisterAppAuthority(userId, imeiCode);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 改变各申请状态
     *
     * @param ids
     *            各申请项id
     * @param status
     *            状态号
     * @return
     */
    @RequestMapping("changeinbatch.json")
    @ResponseBody
    public boolean changeApplicationStatusInBatch(@RequestParam(value = "ids[]") Long[] ids, AppAuthStatus status) {
        try {
            return registerApplyService.changeApplicationStatusInBatch(ids, status);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 后台直接注册
     *
     * @param id
     * @param imeiCode
     * @return
     */
    @RequestMapping("register.json")
    @ResponseBody
    public boolean register(Long id, String imeiCode) {
        try {
            return registerApplyService.register(id, imeiCode);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 注销
     *
     * @param id
     * @param imeiCode
     * @return
     */
    @RequestMapping("unregister.json")
    @ResponseBody
    public boolean unRegister(Long id) {
        try {
            return registerApplyService.unRregister(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除多个申请项
     *
     * @param ids
     * @return
     */
    @RequestMapping("deleteinbatch.json")
    @ResponseBody
    public boolean deleteApplication(@RequestParam(value = "ids[]") Long[] ids) {
        try {
            return registerApplyService.deleteApplicationInBatch(ids);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
