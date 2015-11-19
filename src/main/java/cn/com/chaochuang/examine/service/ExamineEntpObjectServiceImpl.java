/*
 * FileName:    ExamineEntpObjectServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年11月18日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.examine.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.common.data.repository.SimpleDomainRepository;
import cn.com.chaochuang.common.data.service.SimpleLongIdCrudRestService;
import cn.com.chaochuang.common.util.HttpClientHelper;
import cn.com.chaochuang.common.util.JsonMapper;
import cn.com.chaochuang.common.util.NullBeanUtils;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.datacenter.domain.SysDataChange;
import cn.com.chaochuang.datacenter.reference.OperationType;
import cn.com.chaochuang.examine.domain.ExamineEntpObject;
import cn.com.chaochuang.examine.repository.ExamineEntpObjectRepository;

/**
 * @author LLM
 *
 */
@Service
@Transactional
public class ExamineEntpObjectServiceImpl extends SimpleLongIdCrudRestService<ExamineEntpObject> implements
                ExamineEntpObjectService {
    @Value("${supervise.userName}")
    private String                      userName;
    @Value("${supervise.pwd}")
    private String                      pwd;
    @Value("${supervise.baseUrl}")
    private String                      baseUrl;
    @Value("${supervise.loginUrl}")
    private String                      loginUrl;
    @Value("${examine.examineDataUrl}")
    private String                      examineDataUrl;
    /** 创建httpClient对象 */
    private static HttpClientHelper     httpClientHelper = HttpClientHelper.newHttpClientHelper();

    @Autowired
    private ExamineEntpObjectRepository repository;

    @Override
    public SimpleDomainRepository<ExamineEntpObject, Long> getRepository() {
        return repository;
    }

    /**
     * @see cn.com.chaochuang.examine.service.ExamineEntpObjectService#saveExamineEntpObject(cn.com.chaochuang.datacenter.domain.SysDataChange)
     */
    @Override
    public void saveExamineEntpObject(SysDataChange dataChange) {
        // 分解变更内容
        if (dataChange == null || Tools.isEmptyString(dataChange.getChangeScript())) {
            return;
        }
        // 获取要操作的事件审批编号
        String[] items = dataChange.getChangeScript().split("=");
        if (items == null || items.length != 2) {
            return;
        }
        Long entpObjectId = Long.valueOf(items[1]);
        try {
            ExamineEntpObject examine = this.repository.findByRmEntpObjectId(entpObjectId);
            // 参数设置
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("entpObjectId", entpObjectId.toString()));
            String json = httpClientHelper.doPost(new HttpPost(baseUrl + this.examineDataUrl), params,
                            HttpClientHelper.ENCODE_GBK);
            if (StringUtils.isNotBlank(json)) {
                if (HttpClientHelper.RE_LOGIN.equals(json)) {
                    loginSuperviseSys();
                    throw new RuntimeException("连接失败！");
                }
            }
            JsonMapper mapper = JsonMapper.getInstance();
            if (OperationType.新增.getKey().equals(dataChange.getOperationType())) {
                if (examine != null) {
                    return;
                }
                examine = new ExamineEntpObject();
                examine = mapper.readValue(json, ExamineEntpObject.class);
                this.repository.save(examine);
            } else if (OperationType.修改.getKey().equals(dataChange.getOperationType())) {
                ExamineEntpObject examineInfo = mapper.readValue(json, ExamineEntpObject.class);
                Long id = examine.getId();
                NullBeanUtils.copyProperties(examine, examineInfo);
                examine.setId(id);
                this.repository.save(examine);
            } else if (OperationType.删除.getKey().equals(dataChange.getOperationType())) {
                if (examine == null) {
                    return;
                }
                this.repository.delete(examine);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /**
     * 登录系统
     */
    private void loginSuperviseSys() {
        try {
            httpClientHelper.loginSuperviseSys(userName, pwd, new HttpPost(baseUrl + loginUrl));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
