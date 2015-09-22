/*
 * FileName:    VoiceInfoAttach.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年9月21日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.voice.service;

import java.util.List;

import cn.com.chaochuang.common.data.service.CrudRestService;
import cn.com.chaochuang.voice.domain.VoiceInfoAttach;

/**
 * @author LLM
 *
 */
public interface VoiceInfoAttachService extends CrudRestService<VoiceInfoAttach, Long> {

    /**
     * 获取未下载到本地的附件记录
     *
     * @return
     */
    List<VoiceInfoAttach> selectUnLocalAttach();

    /**
     * 修改指定附件的路径和本地标识
     *
     * @param attachId
     * @param newFilePath
     */
    void saveVoiceInfoAttachForLocal(Long attachId, String newFilePath);
}
