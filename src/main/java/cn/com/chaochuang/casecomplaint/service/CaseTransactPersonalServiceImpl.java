/*
 * FileName:   CaseTransactPersonalServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年09月23日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.casecomplaint.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.casecomplaint.domain.CaseComplaint;
import cn.com.chaochuang.casecomplaint.domain.CaseComplaintNodeInfo;
import cn.com.chaochuang.casecomplaint.domain.CaseTransactPersonal;
import cn.com.chaochuang.casecomplaint.repository.CaseComplaintRepository;
import cn.com.chaochuang.casecomplaint.repository.CaseTransactPersonalRepository;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;

/**
 * @author Shicx
 *
 */
@Service
@Transactional
public class CaseTransactPersonalServiceImpl extends SimpleLongIdCrudRestService<CaseTransactPersonal> implements
                CaseTransactPersonalService {
    @Autowired
    private CaseTransactPersonalRepository repository;
    @Autowired
    private CaseComplaintRepository        caseComplaintRepository;
    @Autowired
    private CaseTransactPersonalRepository caseTransactPersonalRepository;

    @Override
    public SimpleDomainRepository<CaseTransactPersonal, Long> getRepository() {
        return repository;
    }

    /**
     * @see cn.com.chaochuang.casecomplaint.service.CaseTransactPersonalService#saveTransactRecord(cn.com.chaochuang.casecomplaint.domain.FdFordoCase)
     */
    @Override
    public void saveTransactRecordByFlowNode(CaseComplaintNodeInfo nodeInfo) {
        if (nodeInfo == null) {
            return;
        }
        // 投诉举报
        CaseComplaint complaint = caseComplaintRepository.findByRmComplaintId(nodeInfo.getCaseComplaintId());
        if (complaint != null && nodeInfo.getCurMan() != null) {// 坐席curMan==null
            CaseTransactPersonal transactRecord = new CaseTransactPersonal();
            transactRecord.setRmComplaintId(nodeInfo.getUnitId());
            transactRecord.setTitle(complaint.getComplaintTitle());
            transactRecord.setTransactTime(nodeInfo.getDoneTime());
            transactRecord.setTransactId(nodeInfo.getCurMan());
            transactRecord.setTransactDeptId(nodeInfo.getCurDepId());
            transactRecord.setUnitOrgId(nodeInfo.getUnitId());
            caseTransactPersonalRepository.save(transactRecord);
        }
    }

}
