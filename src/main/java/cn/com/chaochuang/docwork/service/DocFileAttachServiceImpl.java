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

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.docwork.domain.DocFileAttach;
import cn.com.chaochuang.docwork.repository.DocFileAttachRepository;
import cn.com.chaochuang.task.bean.DocFileAttachInfo;

/**
 * @author Shicx
 *
 */
@Service
public class DocFileAttachServiceImpl extends SimpleLongIdCrudRestService<DocFileAttach> implements
                DocFileAttachService {

    @Autowired
    private DocFileAttachRepository repository;

    @Override
    public SimpleDomainRepository<DocFileAttach, Long> getRepository() {
        return repository;
    }

    @Override
    public void saveRemoteDocFileAttach(List<DocFileAttachInfo> datas, Long fileId) throws Exception {
        if (datas == null) {
            return;
        }
        List<DocFileAttach> attachmentsList = new ArrayList<DocFileAttach>();
        for (DocFileAttachInfo attachmentInfo : datas) {
            DocFileAttach attachment = new DocFileAttach();
            BeanUtils.copyProperties(attachment, attachmentInfo);
            attachment.setFileId(fileId);
            attachmentsList.add(attachment);
        }
        repository.save(attachmentsList);

    }

}
