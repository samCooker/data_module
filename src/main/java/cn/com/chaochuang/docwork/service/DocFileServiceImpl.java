/*
 * FileName:    DocFileServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月30日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.docwork.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.docwork.domain.DocFile;
import cn.com.chaochuang.docwork.repository.DocFileRepository;
import cn.com.chaochuang.task.bean.DocFileInfo;

/**
 * @author Shicx
 *
 */
@Service
@Transactional
public class DocFileServiceImpl extends SimpleLongIdCrudRestService<DocFile> implements DocFileService {

    @Autowired
    private DocFileRepository       repository;

    @Autowired
    private DocFileAttachService    attachmentsService;

    @Autowired
    private FlowNodeInfoService     flowNodeInfoService;

    @Autowired
    private FlowNodeOpinionsService flowNodeOpinionsService;

    @PersistenceContext
    private EntityManager           entityManager;

    @Override
    public SimpleDomainRepository<DocFile, Long> getRepository() {
        return repository;
    }

    /**
     *
     */
    @Override
    public void saveDocFilesDatas(List<DocFileInfo> datas) throws Exception {
        if (datas == null) {
            return;
        }
        for (DocFileInfo fileInfo : datas) {

            DocFile file = repository.findByRmInstanceId(fileInfo.getRmInstanceId());
            if (file == null) {
                // 该公文记录不已存在，则添加
                file = new DocFile();
            }
            BeanUtils.copyProperties(file, fileInfo);
            file = repository.save(file);
            // 保存附件信息
            attachmentsService.saveRemoteDocFileAttach(fileInfo.getRemoteDocfileAttach(), file.getId());
            // 保存流程信息
            flowNodeInfoService.saveRemoteFlowNodeInfo(fileInfo.getRemoteFlowNodes(), file.getId());
            // 保存意见信息
            flowNodeOpinionsService.saveRemoteFlowNodeOpinions(fileInfo.getRemoteFlowOpinions(), file.getId());

        }

    }

    @Override
    public String getDocFileMaxInputDate() {
        StringBuffer sql = new StringBuffer(" select Max(createDate) from ").append(DocFile.class.getName());
        Query query = this.entityManager.createQuery(sql.toString());
        List datas = (ArrayList) query.getResultList();
        if (Tools.isNotEmptyList(datas)) {
            for (Object o : datas) {
                if (o != null) {
                    return o.toString();
                } else {
                    Date sendTime = Tools.diffDate(new Date(), -30);
                    return Tools.DATE_TIME_FORMAT.format(sendTime);
                }
            }
        }
        return null;
    }

}
