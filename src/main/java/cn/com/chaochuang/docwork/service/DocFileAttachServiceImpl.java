/*
 * FileName:    DocFileServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月30日 (Shicx) 1.0 Create
 */

package cn.com.chaochuang.docwork.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.util.NullBeanUtils;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.docwork.domain.DocFileAttach;
import cn.com.chaochuang.docwork.repository.DocFileAttachRepository;
import cn.com.chaochuang.task.bean.DocFileAttachInfo;

/**
 * @author Shicx
 *
 */
@Service
@Transactional
public class DocFileAttachServiceImpl extends SimpleLongIdCrudRestService<DocFileAttach> implements
                DocFileAttachService {

    @Autowired
    private DocFileAttachRepository repository;

    @Override
    public SimpleDomainRepository<DocFileAttach, Long> getRepository() {
        return repository;
    }

    /** 保存从远程取出的附件信息 */
    @Override
    public void saveRemoteDocFileAttach(List<DocFileAttachInfo> datas, Long fileId) {
        if (datas == null) {
            return;
        }
        List<DocFileAttach> attachmentsList = new ArrayList<DocFileAttach>();
        List<DocFileAttach> preAttachList = repository.findByDocId(fileId);
        for (DocFileAttachInfo attachmentInfo : datas) {
            DocFileAttach attachment = repository.findByRmAttachId(attachmentInfo.getRmAttachId());
            if (attachment == null) {
                // 为空说明本地数据库无此附件信息，应添加
                attachment = new DocFileAttach();
                NullBeanUtils.copyProperties(attachment, attachmentInfo);
                attachment.setDocId(fileId);
                attachment.setLocalData(LocalData.非本地数据);
                attachmentsList.add(attachment);
            } else {
                if (Tools.isNotEmptyList(preAttachList)) {
                    preAttachList.remove(attachment);
                }
            }
        }
        // 保存新附件
        repository.save(attachmentsList);
        // 删除不存在的附件信息
        if (Tools.isNotEmptyList(preAttachList)) {
            repository.delete(preAttachList);
        }
    }

    /**
     * @see cn.com.chaochuang.docwork.service.DocFileAttachService#selectUnLocalAttach()
     */
    @Override
    public List<DocFileAttach> selectUnLocalAttach() {
        return this.repository.findByLocalData(LocalData.非本地数据);
    }

    /**
     * @see cn.com.chaochuang.docwork.service.DocFileAttachService#saveDocFileAttachForLocal(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void saveAttachForLocal(DocFileAttach attach, LocalData localData, String localFileName) {
        if (attach != null) {
            if (StringUtils.isNotBlank(localFileName)) {
                attach.setLocalData(LocalData.有本地数据);
                attach.setSavePath(localFileName);
            } else {
                attach.setLocalData(localData);
            }
            this.repository.save(attach);
        }
    }

}
