package cn.com.chaochuang.common.user.web;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.com.chaochuang.common.data.bean.SearchResult;
import cn.com.chaochuang.common.data.persistence.SearchBuilder;
import cn.com.chaochuang.common.data.web.SimpleCrudController;
import cn.com.chaochuang.common.user.domain.SysDepartment;
import cn.com.chaochuang.common.user.service.SysDepartmentService;

@Controller
@RequestMapping("sysdep")
public class SysDepController extends SimpleCrudController<SysDepartment, SysDepartmentService> {

    @Override
    protected void beforeDel(ModelAndView mav, String[] idset) {
        super.beforeDel(mav, idset);
        for (String id : idset) {
            SysDepartment d = getCrudRestService().findOne(Long.parseLong(id));
            d.setParentDep(null);
            getCrudRestService().persist(d);

        }
    }

    @Override
    protected void beforeSave(ModelAndView mav, SysDepartment entity, BindingResult bindingResult,
                    HttpServletRequest request) {
        super.beforeSave(mav, entity, bindingResult, request);

        if (null != entity) {
            boolean isRecursion = false;
            Set<Long> checkParentRecursion = new HashSet<Long>();
            checkParentRecursion.add(entity.getId());

            Long pdid = entity.getParentDep();
            while (null != pdid && !isRecursion) {
                if (checkParentRecursion.contains(pdid)) {
                    isRecursion = true;
                    break;
                }

                checkParentRecursion.add(pdid);

                pdid = getCrudRestService().findOne(pdid).getParentDep();
            }

            if (isRecursion) {
                throw new RuntimeException("设置的父部门不能是 [当前部门] 或者是 [当前部门] 的子部门");
            }
        }

    }

    @Override
    protected SearchResult beforeList(ModelAndView mav, Integer pageNum, Integer numPerPage, String orderField,
                    String orderDirection, SearchBuilder<SysDepartment, Long> searchBuilder,
                    HttpServletRequest request, Boolean isPageBreak) {

        if (!searchBuilder.hasSort()) {
            searchBuilder.appendSort("orderNum");
        }

        return super.beforeList(mav, pageNum, numPerPage, orderField, orderDirection, searchBuilder, request,
                        isPageBreak);
    }
}
