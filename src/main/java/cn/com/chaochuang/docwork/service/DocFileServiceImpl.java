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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.docwork.domain.DocFile;
import cn.com.chaochuang.docwork.domain.FlowNodeInfo;
import cn.com.chaochuang.docwork.reference.DocStatus;
import cn.com.chaochuang.docwork.repository.DocFileRepository;
import cn.com.chaochuang.task.bean.DocFileInfo;
import cn.com.chaochuang.task.bean.FlowNodeBeanInfo;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Shicx
 *
 */
@Service
@Transactional
public class DocFileServiceImpl extends SimpleLongIdCrudRestService<DocFile> implements DocFileService {

    @Autowired
    private DocFileRepository           repository;

    @Autowired
    private DocFileAttachService        attachmentsService;

    @Autowired
    private FlowNodeInfoService         flowNodeInfoService;

    @Autowired
    private FlowNodeOpinionsService     flowNodeOpinionsService;

    @Autowired
    private FlowTransactPersonalService flowTransactPersonalService;

    @PersistenceContext
    private EntityManager               entityManager;

    @Value("${getdata.timeinterval}")
    private String                      timeInterval;

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
            file.setDocStatus(DocStatus.在办);
            file = repository.save(file);
            // 保存附件信息
            attachmentsService.saveRemoteDocFileAttach(fileInfo.getRemoteDocfileAttach(), file.getId());
            // 保存流程信息
            flowNodeInfoService.saveRemoteFlowNodeInfo(fileInfo.getRemoteFlowNodes(), file.getId());
            // 保存意见信息
            flowNodeOpinionsService.saveRemoteFlowNodeOpinions(fileInfo.getRemoteFlowOpinions(), file.getId());
            // 保存公文个人办理记录
            flowTransactPersonalService.saveFlowTransactPersonalInfo(fileInfo.getRemoteFlowNodes(), file);
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
                    Date sendTime = Tools.diffDate(new Date(), new Integer(timeInterval));
                    return Tools.DATE_TIME_FORMAT.format(sendTime);
                }
            }
        }
        return null;
    }

    @Override
    public void finishDocFile(String hisNoJsonStr) throws Exception {
        if (Tools.isEmptyString(hisNoJsonStr)) {
            return;
        }
        ObjectMapper mapper = new ObjectMapper();
        DocFileInfo fileInfo = (DocFileInfo) mapper.readValue(hisNoJsonStr, DocFileInfo.class);

        if (fileInfo != null) {
            String rmInstanceId = fileInfo.getRmInstanceId();
            List<FlowNodeInfo> preFlowNodesList = flowNodeInfoService.findByRmInstanceId(rmInstanceId);
            // 将公文状态修改为已办
            DocFile file = repository.findByRmInstanceId(rmInstanceId);
            // 若file为空，说明数据还没导入到mobile数据库
            if (file != null) {
                file.setDocStatus(DocStatus.办结);
                repository.save(file);
                // 删除原节点信息
                flowNodeInfoService.getRepository().delete(preFlowNodesList);
                // 将oa的历史节点信息保存
                flowNodeInfoService.saveRemoteFlowNodeInfo(fileInfo.getRemoteFlowNodes(), file.getId());
                // 跟新公文意见记录
                flowNodeOpinionsService.saveRemoteFlowNodeOpinions(fileInfo.getRemoteFlowOpinions(), file.getId());
                // 保存公文个人办理记录
                flowTransactPersonalService.saveFlowTransactPersonalInfo(fileInfo.getRemoteFlowNodes(), file);
            }
        }

    }

}
