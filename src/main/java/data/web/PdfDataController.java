package data.web;

import data.bean.SaveBeanPdfPara;
import data.domain.PdfParameter;
import data.service.PdfParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Cookie on 2016/5/5.
 */
@Controller
@RequestMapping("pdfdata")
public class PdfDataController {

    @Autowired
    private PdfParameterService pdfParameterService;

    @RequestMapping("/save.action")
    @ResponseBody
    public boolean getDataJson(@RequestBody SaveBeanPdfPara saveBean){
        return pdfParameterService.savePdfParameter(saveBean);
    }

    @RequestMapping("/saveother.action")
    @ResponseBody
    public boolean getDataOther(SaveBeanPdfPara saveBean){
        return pdfParameterService.savePdfParameter(saveBean);
    }

    @RequestMapping("/list.action")
    @ResponseBody
    public List<PdfParameter> getData(){
        return pdfParameterService.getPdfParameterData();
    }
}
