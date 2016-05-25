/*
 * FileName:    FdFordoDocServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2016
 * History:     2016年4月26日 (LLM) 1.0 Create
 */

package data.service;


import data.bean.SaveBeanPdfPara;
import data.domain.PdfParameter;
import data.repository.PdfParameterRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springcommon.jpa.SimpleDomainRepository;
import springcommon.jpa.SimpleLongIdCrudRestService;
import utils.NullBeanUtils;

import java.util.List;

/**
 * @author LLM
 *
 */
@Service
@Transactional
public class PdfParameterServiceImpl extends SimpleLongIdCrudRestService<PdfParameter> implements PdfParameterService {

    private static Logger logger = Logger.getLogger("app.debug.log.logger");

    @Autowired
    private PdfParameterRepository pdfParameterRepository;

    public SimpleDomainRepository<PdfParameter, Long> getRepository() {
        return pdfParameterRepository;
    }

    @Override
    public boolean savePdfParameter(SaveBeanPdfPara saveBean) {
        if(saveBean==null){
            return false;
        }
        PdfParameter pdfParameter = null;
        if(saveBean.getId()!=null){
            pdfParameter = pdfParameterRepository.findOne(saveBean.getId());
            if(pdfParameter==null){
                // 根据id在数据库中没有找到，不能保存
                logger.debug("找不到id为"+saveBean.getId());
                return false;
            }
        }
        if(pdfParameter==null){
            pdfParameter = new PdfParameter();
        }
        NullBeanUtils.copyProperties(pdfParameter,saveBean);
        pdfParameterRepository.saveAndFlush(pdfParameter);
        logger.debug("保存成功。id="+pdfParameter.getId());
        return true;
    }

    @Override
    public List<PdfParameter> getPdfParameterData() {
        return pdfParameterRepository.findAll();
    }
}
