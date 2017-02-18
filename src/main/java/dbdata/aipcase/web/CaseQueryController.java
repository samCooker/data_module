/*
 * FileName:    ModulesController.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2017
 * History:     2017年01月16日 (Shicx) 1.0 Create
 */

package dbdata.aipcase.web;

import cn.com.chaochuang.common.data.persistence.SearchBuilder;
import common.beancopy.BeanCopyBuilder;
import common.utils.SearchListHelper;
import dbdata.aipcase.bean.CaseQueryBean;
import dbdata.aipcase.bean.CaseQueryData;
import dbdata.aipcase.domain.AipCaseApply;
import dbdata.aipcase.repository.AipCaseApplyRepository;
import dbdata.common.bean.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Shicx
 */
@Controller
@RequestMapping("case/query")
public class CaseQueryController {

    @Autowired
    private AipCaseApplyRepository aipCaseApplyRepository;

    /**
     * 首页
     * @return
     */
    @RequestMapping("index.do")
    public ModelAndView toCaseQueryPage(){
        ModelAndView modelAndView = new ModelAndView("/aipcase/index");

        return modelAndView;
    }

    @RequestMapping("caselist.json")
    @ResponseBody
    public Page queryCaseListData(HttpServletRequest request,CaseQueryBean queryBean){
        SearchBuilder<AipCaseApply, Long> searchBuilder = new SearchBuilder<AipCaseApply, Long>();
        searchBuilder.clearSearchBuilder().findSearchParam(request);

        searchBuilder.getFilterBuilder().like("fillEntpCorpname",queryBean.getFillEntpCorpname());
        searchBuilder.getFilterBuilder().like("fillEntpContacter",queryBean.getFillEntpContacter());
        searchBuilder.getFilterBuilder().greaterThanOrEqualTo("recordTime",queryBean.getRecordTimeFrom());
        searchBuilder.getFilterBuilder().lessThanOrEqualTo("recordTime",queryBean.getRecordTimeTo());
        searchBuilder.appendSort(Sort.Direction.DESC, "id");
        SearchListHelper<AipCaseApply> listhelper = new SearchListHelper<AipCaseApply>();
        listhelper.execute(searchBuilder, aipCaseApplyRepository, queryBean.getPageNo(), queryBean.getPageSize());

        Page p = new Page();
        p.setRows(BeanCopyBuilder.buildList(listhelper.getList(), CaseQueryData.class));
        p.setTotal(listhelper.getCount());
        return p;

    }

    @RequestMapping("delete.json")
    @ResponseBody
    public boolean deleteCaseApply(){
        return false;
    }

}

