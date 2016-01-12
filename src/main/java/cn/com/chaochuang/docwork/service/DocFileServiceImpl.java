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

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.util.JsonMapper;
import cn.com.chaochuang.common.util.NullBeanUtils;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.datacenter.domain.SysDataChangeOa;
import cn.com.chaochuang.datacenter.service.DataUpdateService;
import cn.com.chaochuang.docwork.domain.DocFile;
import cn.com.chaochuang.docwork.domain.FdFordo;
import cn.com.chaochuang.docwork.reference.DocStatus;
import cn.com.chaochuang.docwork.repository.DocFileRepository;
import cn.com.chaochuang.docwork.repository.FdFordoRepository;
import cn.com.chaochuang.task.bean.DocFileInfo;
import cn.com.chaochuang.task.bean.FlowNodeOpinionsInfo;
import cn.com.chaochuang.webservice.server.ITransferOAService;

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

    @Autowired
    private DataUpdateService           dataUpdateService;
    @Autowired
    private FdFordoService              fdFordoService;
    @Autowired
    private FdFordoRepository           fdFordoRepository;
    /** webservice 函数库 */
    @Autowired
    private ITransferOAService          transferOAService;

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
    public void saveDocFilesDatas(List<DocFileInfo> datas, List<FdFordo> fordoData) {
        if (datas == null) {
            return;
        }
        for (DocFileInfo fileInfo : datas) {
            DocFile file = repository.findByRmInstanceId(fileInfo.getRmInstanceId());
            if (file == null) {
                // 该公文记录不已存在，则添加
                file = new DocFile();
            }
            NullBeanUtils.copyProperties(file, fileInfo);
            file.setDocStatus(DocStatus.在办);
            file = repository.saveAndFlush(file);
            // 保存附件信息
            attachmentsService.saveRemoteDocFileAttach(fileInfo.getRemoteDocfileAttach(), file.getId());
            // 保存流程信息
            flowNodeInfoService.saveRemoteFlowNodeInfo(fileInfo.getRemoteFlowNodes(), file.getId());
            // 保存意见信息
            flowNodeOpinionsService.saveRemoteFlowNodeOpinions(fileInfo.getRemoteFlowOpinions(), file.getId());
            // 保存公文个人办理记录
            flowTransactPersonalService.saveFlowTransactPersonalInfo(fileInfo.getRemoteFlowNodes(), file, fileInfo.getRedactDeptId());
        }
        for (FdFordo fordo : fordoData) {
            List<FdFordo> fordoList = fdFordoRepository.findByRmInstanceIdAndLocalData(fordo.getRmInstanceId(), LocalData.非本地数据);
            if (Tools.isNotEmptyList(fordoList)) {
                for (FdFordo dfordo : fordoList) {
                    dfordo.setLocalData(LocalData.有本地数据);
                    fdFordoRepository.saveAndFlush(dfordo);
                }
            }
        }
    }

    /**
     * @see cn.com.chaochuang.docwork.service.DocFileService#saveDocFilesDatas(cn.com.chaochuang.task.bean.DocFileInfo,
     *      cn.com.chaochuang.docwork.domain.FdFordo)
     */
    @Override
    public void saveDocFilesDatas(DocFileInfo fileInfo, FdFordo fordo) {
        if (fileInfo == null || fordo == null) {
            return;
        }
        DocFile file = repository.findByRmInstanceId(fileInfo.getRmInstanceId());
        if (file == null) {
            // 该公文记录不已存在，则添加
            file = new DocFile();
        }
        NullBeanUtils.copyProperties(file, fileInfo);
        file.setDocStatus(DocStatus.在办);
        file = repository.save(file);
        // 保存附件信息
        attachmentsService.saveRemoteDocFileAttach(fileInfo.getRemoteDocfileAttach(), file.getId());
        // 保存流程信息
        flowNodeInfoService.saveRemoteFlowNodeInfo(fileInfo.getRemoteFlowNodes(), file.getId());
        // 保存意见信息
        flowNodeOpinionsService.saveRemoteFlowNodeOpinions(fileInfo.getRemoteFlowOpinions(), file.getId());
        // 保存公文个人办理记录
        flowTransactPersonalService.saveFlowTransactPersonalInfo(fileInfo.getRemoteFlowNodes(), file, fileInfo.getRedactDeptId());
        fordo.setLocalData(LocalData.有本地数据);
        fdFordoRepository.save(fordo);
    }

    @Override
    public FlowNodeOpinionsInfo getDocFileMaxInputDate() {
        FlowNodeOpinionsInfo result = new FlowNodeOpinionsInfo();
        // 查询没有办结的公文的最大流程节点号
        String sql = "SELECT max(RM_INSTANCE_ID) FROM DOC_FILE";
        Query query = this.entityManager.createNativeQuery(sql);
        List datas = (ArrayList) query.getResultList();
        if (Tools.isNotEmptyList(datas)) {
            for (Object o : datas) {
                if (o != null) {
                    result.setGetDataNoId(o.toString());
                    result.setGetDataTime("");
                    break;
                }
            }
        }
        if (result.getGetDataNoId() == null) {
            Date sendTime = Tools.diffDate(new Date(), new Integer(timeInterval));
            result.setGetDataTime(Tools.DATE_TIME_FORMAT.format(sendTime));
            result.setGetDataNoId("");
        }
        return result;
    }

    @Override
    public void finishDocFile(String hisNoJsonStr) {
        if (Tools.isEmptyString(hisNoJsonStr)) {
            return;
        }
        JsonMapper mapper = JsonMapper.getInstance();
        DocFileInfo fileInfo = (DocFileInfo) mapper.readValue(hisNoJsonStr, DocFileInfo.class);

        if (fileInfo != null) {
            String rmInstanceId = fileInfo.getRmInstanceId();
            // List<FlowNodeInfo> preFlowNodesList = flowNodeInfoService.findByRmInstanceId(rmInstanceId);
            // 将公文状态修改为已办
            DocFile file = repository.findByRmInstanceId(rmInstanceId);
            // 若file为空，说明数据还没导入到mobile数据库,不进行处理
            if (file != null) {
                file.setDocStatus(DocStatus.办结);
                repository.save(file);
                // 删除原节点信息
                // flowNodeInfoService.getRepository().delete(preFlowNodesList);
                // 将oa的历史节点信息保存
                flowNodeInfoService.saveRemoteFlowNodeInfo(fileInfo.getRemoteFlowNodes(), file.getId());
                // 更新公文意见记录
                flowNodeOpinionsService.saveRemoteFlowNodeOpinions(fileInfo.getRemoteFlowOpinions(), file.getId());
                // 保存公文个人办理记录 (已修改为从oa直接获取)
                // flowTransactPersonalService.saveFlowTransactPersonalInfo(fileInfo.getRemoteFlowNodes(), file,
                // fileInfo.getRedactDeptId());
            }
        }

    }

    /**
     * @see cn.com.chaochuang.docwork.service.DocFileService#findByRmInstanceId(java.lang.String)
     */
    @Override
    public DocFile findByRmInstanceId(String rmInstanceId) {

        return repository.findByRmInstanceId(rmInstanceId);
    }

    /**
     * (non-Javadoc)
     * 
     * @see cn.com.chaochuang.docwork.service.DocFileService#finishDocFile(SysDataChangeOa)
     */
    @Override
    public void finishDocFile(SysDataChangeOa item) {
        String[] items = item.getChangeScript().split("=");
        DocFile docFile = repository.findByRmInstanceId(items[1]);
        if (docFile != null) {
            String json = this.transferOAService.getOAHistoryNodes(new Long(items[1]));
            if (StringUtils.isNotBlank(json) && !"FALSE".equals(json)) {
                this.finishDocFile(json);
            }
        }
    }

}
