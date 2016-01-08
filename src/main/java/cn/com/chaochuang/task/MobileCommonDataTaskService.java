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
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;

import cn.com.chaochuang.aipcase.service.AipPunishEntpService;
import cn.com.chaochuang.appflow.service.AppItemApplyService;
import cn.com.chaochuang.appflow.service.AppItemAttachService;
import cn.com.chaochuang.appflow.service.AppLicenceService;
import cn.com.chaochuang.common.fdfordo.service.CommonPendingHandleService;
import cn.com.chaochuang.common.user.service.SysDepartmentService;
import cn.com.chaochuang.common.user.service.SysUserService;
import cn.com.chaochuang.common.util.JsonMapper;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.commoninfo.service.AppEntpService;
import cn.com.chaochuang.commoninfo.service.DepLinkmanService;
import cn.com.chaochuang.datacenter.domain.SysDataChange;
import cn.com.chaochuang.datacenter.reference.DataChangeTable;
import cn.com.chaochuang.datacenter.service.SysDataChangeService;
import cn.com.chaochuang.docwork.service.DocFileService;
import cn.com.chaochuang.emergency.service.EmSiteReportService;
import cn.com.chaochuang.examine.service.ExamineEntpObjectService;
import cn.com.chaochuang.voice.service.VoiceEventService;
import cn.com.chaochuang.voice.service.VoiceInfoService;
import cn.com.chaochuang.webservice.server.ITransferOAService;

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
    @Autowired
    private AipPunishEntpService       punishEntpService;
    @Autowired
    private VoiceInfoService           voiceInfoService;
    @Autowired
    private VoiceEventService          voiceEventService;
    @Autowired
    private AppLicenceService          licenceService;
    @Autowired
    private ExamineEntpObjectService   examineService;
    @Autowired
    private EmSiteReportService        emSiteReportService;
    @Autowired
    private AppItemAttachService       appItemAttachService;
    @Autowired
    private AppItemApplyService        appItemApplyService;

    /** 获取公告阻塞标识 */
    private static boolean             isGetSysDataChangeRunning             = false;
    /** 获取处理系统数据更改阻塞标识 */
    private static boolean             isDealSysDataChangeRunning            = false;
    /** 获取OA待办数据更改阻塞标识 */
    private static boolean             isDealOAPendingItemDataChangeRunning  = false;
    /** 行政审批待办更改阻塞标识 */
    private static boolean             isDealSuperviseFordoDataChangeRunning = false;

    /**
     * 获取远程系统修改记录数据
     */
    @Scheduled(cron = "15/15 * * * * ?")
    // //@Scheduled(cron = "2 0/2 * * * ?")
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
            JsonMapper mapper = JsonMapper.getInstance();
            JavaType javaType = mapper.constructParametricType(ArrayList.class, SysDataChange.class);
            List<SysDataChange> datas = mapper.readValue(json, javaType);
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
    @Scheduled(cron = "10/10 * * * * ?")
    // //@Scheduled(cron = "8 0/2 * * * ?")
    public void dealDataChange() {
        if (isDealSysDataChangeRunning) {
            return;
        }
        isDealSysDataChangeRunning = true;
        try {
            List<SysDataChange> datas = this.dataChangeService.findByPageOrderById(new PageRequest(0, 10));
            for (SysDataChange item : datas) {
                try {
                    if (DataChangeTable.办案待办.getKey().equals(item.getChangeTableName())) {
                        // 同步办案系统的待办
                        this.commonFordoService.analysisDataChange(item, DataChangeTable.办案待办);
                    } else if (DataChangeTable.投诉举报待办.getKey().equals(item.getChangeTableName())) {
                        // 同步投诉举报的待办
                        this.commonFordoService.analysisDataChange(item, DataChangeTable.投诉举报待办);
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
                    } else if (DataChangeTable.行政处罚信息.getKey().equals(item.getChangeTableName())) {
                        // 行政处罚信息更新
                        this.punishEntpService.savePunishInfo(item);
                    } else if (DataChangeTable.舆情信息.getKey().equals(item.getChangeTableName())) {
                        this.voiceInfoService.updateVoiceInfo(item);
                    } else if (DataChangeTable.舆情事件内容.getKey().equals(item.getChangeTableName())) {
                        this.voiceEventService.updateVoiceInfoEvent(item);
                    } else if (DataChangeTable.舆情事件.getKey().equals(item.getChangeTableName())) {
                        this.voiceEventService.saveVoiceEvent(item);
                    } else if (DataChangeTable.舆情事件办理.getKey().equals(item.getChangeTableName())) {
                        this.voiceEventService.saveVoiceEventHandleApprove(item);
                    } else if (DataChangeTable.舆情事件处理意见.getKey().equals(item.getChangeTableName())) {
                        this.voiceEventService.saveVoiceEventHandleResult(item);
                    } else if (DataChangeTable.舆情事件办理意见.getKey().equals(item.getChangeTableName())) {
                        this.voiceEventService.saveVoiceEventHandleOpinion(item);
                    } else if (DataChangeTable.许可证信息.getKey().equals(item.getChangeTableName())) {
                        // 许可证信息更新
                        this.licenceService.saveAppLicence(item);
                    } else if (DataChangeTable.日常检查.getKey().equals(item.getChangeTableName())) {
                        // 日常检查信息更新
                        this.examineService.saveExamineEntpObject(item);
                    } else if (DataChangeTable.应急指挥情况汇报.getKey().equals(item.getChangeTableName())) {
                        // 应急指挥情况汇报记录更新
                        this.emSiteReportService.saveEmSiteReport(item);
                    } else if (DataChangeTable.审批材料清单.getKey().equals(item.getChangeTableName())) {
                        // 审批材料清单更新
                        appItemAttachService.updatePrjMaterial(item);
                    } else if (DataChangeTable.执业药师.getKey().equals(item.getChangeTableName())) {
                        // 更新、插入或删除执业药师
                        appItemApplyService.updateOrDelPharmacist(item);
                    }
                    // 删除变更数据
                    this.dataChangeService.delete(item.getId());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    // 抛出异常则记录该记录的异常信息，并继续循环，不影响其他数据
                    continue;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            isDealSysDataChangeRunning = false;
        }
    }

    /**
     * 处理OA待办数据的变更
     */
    // @Scheduled(cron = "15/15 * * * * ?")
    public void dealOAPendingItemDataChange() {
        if (isDealOAPendingItemDataChangeRunning) {
            return;
        }
        isDealOAPendingItemDataChangeRunning = true;
        try {
            List<SysDataChange> datas = this.dataChangeService.selectOAPendingItem(new PageRequest(0, 10));
            for (SysDataChange item : datas) {
                try {
                    if (DataChangeTable.公文待办.getKey().equals(item.getChangeTableName())) {
                        // 同步公文系统的待办
                        this.commonFordoService.updateOADataIfExist(item, DataChangeTable.公文待办);
                    }
                    // 删除变更数据
                    this.dataChangeService.delete(item.getId());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    // 抛出异常则记录该记录的异常信息，并继续循环，不影响其他数据
                    continue;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            isDealOAPendingItemDataChangeRunning = false;
        }
    }

    /**
     * 处理审批待办数据的变更
     */
    // @Scheduled(cron = "15/15 * * * * ?")
    public void dealSuperviseFordoDataChange() {
        if (isDealSuperviseFordoDataChangeRunning) {
            return;
        }
        isDealSuperviseFordoDataChangeRunning = true;
        try {
            List<SysDataChange> datas = this.dataChangeService.selectSuperviseFordo(new PageRequest(0, 10));
            for (SysDataChange item : datas) {
                try {
                    if (DataChangeTable.审批待办.getKey().equals(item.getChangeTableName())) {
                        // 同步审批系统的待办
                        this.commonFordoService.updateSuperviseDataIfExist(item, DataChangeTable.审批待办);
                    }
                    // 删除变更数据
                    this.dataChangeService.delete(item.getId());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    // 抛出异常则记录该记录的异常信息，并继续循环，不影响其他数据
                    continue;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            isDealSuperviseFordoDataChangeRunning = false;
        }
    }
}
