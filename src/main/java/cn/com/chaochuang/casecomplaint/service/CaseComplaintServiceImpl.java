/*
 * FileName:   CaseComplaintServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年09月10日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.casecomplaint.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.casecomplaint.bean.BaseCaseComplaintInfo;
import cn.com.chaochuang.casecomplaint.domain.CaseComplainedObject;
import cn.com.chaochuang.casecomplaint.domain.CaseComplaint;
import cn.com.chaochuang.casecomplaint.domain.CaseComplaintObject;
import cn.com.chaochuang.casecomplaint.repository.CaseComplainedObjectRepository;
import cn.com.chaochuang.casecomplaint.repository.CaseComplaintObjectRepository;
import cn.com.chaochuang.casecomplaint.repository.CaseComplaintRepository;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.util.JsonMapper;
import cn.com.chaochuang.common.util.NullBeanUtils;

import com.fasterxml.jackson.databind.JavaType;

/**
 * @author Shicx
 *
 */
@Service
@Transactional
public class CaseComplaintServiceImpl extends SimpleLongIdCrudRestService<CaseComplaint> implements
                CaseComplaintService {
    @Autowired
    private CaseComplaintRepository        repository;
    @Autowired
    private FdFordoCaseService             fdFordoCaseService;
    @Autowired
    private CaseComplaintObjectRepository  complaintObjectRepository;
    @Autowired
    private CaseComplainedObjectRepository complainedObjRepository;
    @Autowired
    private CaseComplaintNodeInfoService   caseComplaintNodeInfoService;
    @Autowired
    private CaseComplaintOpinionService    caseComplaintOpinionService;
    @Autowired
    private CaseComplaintAttachService     caseComplaintAttachService;

    @Override
    public SimpleDomainRepository<CaseComplaint, Long> getRepository() {
        return repository;
    }

    @Override
    public void saveCaseComplaintInfo(String returnInfo, List<String> pendingIds) {
        if (StringUtils.isBlank(returnInfo)) {
            return;
        }
        JsonMapper mapper = JsonMapper.getInstance();
        JavaType javaType = mapper.constructParametricType(ArrayList.class, BaseCaseComplaintInfo.class);
        List<BaseCaseComplaintInfo> data = (List<BaseCaseComplaintInfo>) mapper.readValue(returnInfo, javaType);
        for (BaseCaseComplaintInfo baseInfo : data) {
            // 保存投诉举报基本信息
            this.saveBaseCaseComplaint(baseInfo);
            // 节点信息
            caseComplaintNodeInfoService.saveComplaintNodeInfo(baseInfo);
            // 意见信息
            caseComplaintOpinionService.saveComplaintOpinion(baseInfo);
            // 附件信息
            caseComplaintAttachService.saveComplaintAttach(baseInfo);
        }
        for (String pendingId : pendingIds) {
            fdFordoCaseService.changeFordoLocalData(pendingId, LocalData.有本地数据);
        }
    }

    /**
     * 保存投诉举报基本信息
     * 
     * @param baseInfo
     */
    private void saveBaseCaseComplaint(BaseCaseComplaintInfo baseInfo) {
        if (baseInfo == null) {
            return;
        }
        CaseComplaint complaint = repository.findByRmComplaintId(baseInfo.getComplaintId());
        if (complaint == null) {
            complaint = new CaseComplaint();
        }
        NullBeanUtils.copyProperties(complaint, baseInfo);
        complaint.setRmComplaintId(baseInfo.getComplaintId());
        repository.save(complaint);
        // 投诉对象信息
        CaseComplaintObject complaintObj = complaintObjectRepository.findByRmComplaintId(baseInfo.getComplaintId());
        if (complaintObj == null) {
            complaintObj = new CaseComplaintObject();
        }
        NullBeanUtils.copyProperties(complaintObj, baseInfo.getComplaintObjInfo());
        complaintObj.setRmComplaintId(baseInfo.getComplaintId());
        complaintObj.setRmComplaintObjectId(baseInfo.getComplaintObjectId());
        complaintObjectRepository.save(complaintObj);
        // 被投诉对象信息
        CaseComplainedObject complainedObj = complainedObjRepository.findByRmComplaintId(baseInfo.getComplaintId());
        if (complainedObj == null) {
            complainedObj = new CaseComplainedObject();
        }
        NullBeanUtils.copyProperties(complainedObj, baseInfo.getComplainedObjInfo());
        complainedObj.setRmComplaintId(baseInfo.getComplaintId());
        complainedObj.setRmComplainedObjectId(baseInfo.getComplainedObjectId());
        complainedObjRepository.save(complainedObj);
    }

}
