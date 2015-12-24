/*
 * FileName:    SynchDataController.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年8月19日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.synchdata.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.chaochuang.common.bean.EasyUIPage;
import cn.com.chaochuang.common.bean.Result;
import cn.com.chaochuang.common.beancopy.BeanCopyBuilder;
import cn.com.chaochuang.common.data.persistence.SearchBuilder;
import cn.com.chaochuang.common.util.SearchBuilderHelper;
import cn.com.chaochuang.common.util.SearchListHelper;
import cn.com.chaochuang.synchdata.bean.SynchDataParams;
import cn.com.chaochuang.synchdata.bean.SysSynchdataTaskShowBean;
import cn.com.chaochuang.synchdata.domain.SysSynchdataTask;
import cn.com.chaochuang.synchdata.service.SysSynchdataTaskService;

/**
 * @author LLM
 *
 */
@Controller
@RequestMapping("synchdata")
public class SynchDataController {
    @Autowired
    private SysSynchdataTaskService synchdataTaskService;
    @Autowired
    protected ConversionService     conversionService;

    /**
     * 进入数据同步管理界面
     *
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView synchAppEntp() {
        ModelAndView mav = new ModelAndView("/synchdata/list");

        return mav;
    }

    /**
     * 查询数据同步任务记录列表
     *
     * @param page
     * @param rows
     * @param request
     * @return
     */
    @RequestMapping("/list.json")
    @ResponseBody
    public EasyUIPage synchdataList(Integer page, Integer rows, HttpServletRequest request) {
        SearchBuilder<SysSynchdataTask, Long> searchBuilder = SearchBuilderHelper.bindSearchBuilder(conversionService,
                        request);
        searchBuilder.appendSort(Direction.DESC, "id");
        SearchListHelper<SysSynchdataTask> listhelper = new SearchListHelper<SysSynchdataTask>();
        listhelper.execute(searchBuilder, this.synchdataTaskService.getRepository(), page, rows);

        EasyUIPage p = new EasyUIPage();
        p.setRows(BeanCopyBuilder.buildList(listhelper.getList(), SysSynchdataTaskShowBean.class));
        p.setTotal(listhelper.getCount());
        return p;
    }

    /**
     * 保存数据同步记录
     *
     * @param synchDataFlag
     * @param clearFlag
     * @return
     */
    @RequestMapping("/savesynchdatatask.json")
    @ResponseBody
    public Result saveSynchdataTask(SynchDataParams params) {
        this.synchdataTaskService.saveSynchdataTask(params);
        return new Result("成功添加数据同步任务！", null);
    }
}
