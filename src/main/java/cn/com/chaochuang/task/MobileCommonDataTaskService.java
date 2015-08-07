/*
 * FileName:    MobileDataTaskService.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年3月21日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.task;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.com.chaochuang.common.fdfordo.service.CommonPendingHandleService;
import cn.com.chaochuang.common.user.service.SysDepartmentService;
import cn.com.chaochuang.common.user.service.SysUserService;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.commoninfo.service.AppEntpService;
import cn.com.chaochuang.commoninfo.service.DepLinkmanService;
import cn.com.chaochuang.datacenter.domain.SysDataChange;
import cn.com.chaochuang.datacenter.reference.DataChangeTable;
import cn.com.chaochuang.datacenter.service.SysDataChangeService;
import cn.com.chaochuang.docwork.service.DocFileService;
import cn.com.chaochuang.webservice.server.ITransferOAService;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author LLM
 *
 */
@Component
public class MobileCommonDataTaskService {
    /** webservice 函数库 */
    @Autowired
    private ITransferOAService         transferOAService;

    /** fdFordoService */
    @Autowired
    private CommonPendingHandleService commonFordoService;

    @Autowired
    private DocFileService             fileService;

    @Autowired
    private SysDataChangeService       dataChangeService;

    @Autowired
    private SysDepartmentService       departmentService;

    @Autowired
    private SysUserService             userService;

    @Autowired
    private DepLinkmanService          depLinkmanService;

    @Autowired
    private AppEntpService             appEntpService;

    /** 获取公告阻塞标识 */
    private static boolean             isGetSysDataChangeRunning  = false;
    /** 获取处理系统数据更改阻塞标识 */
    private static boolean             isDealSysDataChangeRunning = false;

    /**
     * 获取远程系统修改记录数据
     */
    // @Scheduled(cron = "10/6 * * * * ?")
    public void getOADataChange() {
        if (isGetSysDataChangeRunning) {
            return;
        }
        isGetSysDataChangeRunning = true;
        try {
            String json = this.transferOAService.getDataChange();
            if (Tools.isEmptyString(json)) {
                return;
            }
            ObjectMapper mapper = new ObjectMapper();
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, SysDataChange.class);
            List<SysDataChange> datas = (List<SysDataChange>) mapper.readValue(json, javaType);
            this.dataChangeService.saveSysDataChange(datas);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            isGetSysDataChangeRunning = false;
        }
    }

    /**
     * 处理远程系统更改数据
     */
    @Scheduled(cron = "5/5 * * * * ?")
    public void dealDataChange() {
        if (isDealSysDataChangeRunning) {
            return;
        }
        isDealSysDataChangeRunning = true;
        try {
            Page page = this.dataChangeService.findAllByPage(1, 10);
            List<SysDataChange> datas = page.getContent();
            for (SysDataChange item : datas) {
                try {
                    if (item.getChangeTableName() != null
                                    && item.getChangeTableName().contains(DataChangeTable.待办事宜.getKey())) {
                        // 如果处理的表表名包含pending_handle，则调用公用的待办方法分析处理过程
                        this.commonFordoService.analysisDataChange(item);
                    } else if (DataChangeTable.公文办结.getKey().equals(item.getChangeTableName())) {
                        // 如果处理的表为wf_flo_hisno 则将相关公文的公文状态改为办结（不包括通报）
                        String[] items = item.getChangeScript().split("=");
                        String json = this.transferOAService.getOAHistoryNodes(new Long(items[1]));
                        this.fileService.finishDocFile(json);
                    } else if (DataChangeTable.组织结构.getKey().equals(item.getChangeTableName())) {
                        // 组织机构发生变更
                        this.departmentService.analysisDataChange(item);
                    } else if (DataChangeTable.人员.getKey().equals(item.getChangeTableName())) {
                        // 人员数据发生变更
                        this.userService.analysisDataChange(item);
                    } else if (DataChangeTable.通讯录.getKey().equals(item.getChangeTableName())) {
                        // 通讯录数据发生变更
                        this.depLinkmanService.analysisDataChange(item);
                    } else if (DataChangeTable.新闻公告.getKey().equals(item.getChangeTableName())) {
                        // 新闻公告数据发生变更
                    } else if (DataChangeTable.企业信息.getKey().equals(item.getChangeTableName())) {
                        // 企业信息的添加和更新
                        this.appEntpService.insertOrUpdataEntp(item);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    // 抛出异常则记录该记录的异常信息，并继续循环，不影响其他数据
                    continue;
                }
                // 删除变更数据
                this.dataChangeService.delete(item.getId());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            isDealSysDataChangeRunning = false;
        }
    }
}
