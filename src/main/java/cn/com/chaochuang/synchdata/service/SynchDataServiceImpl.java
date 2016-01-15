/*
 * FileName:    SynchDataServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年8月14日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.synchdata.service;

import java.io.Writer;
import java.lang.reflect.Method;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.aipcase.reference.LocalData;
import cn.com.chaochuang.appflow.repository.AppLicenceRepository;
import cn.com.chaochuang.common.user.domain.SysDepartment;
import cn.com.chaochuang.common.user.domain.SysUser;
import cn.com.chaochuang.common.user.repository.SysDepartmentRepository;
import cn.com.chaochuang.common.user.repository.SysUserRepository;
import cn.com.chaochuang.common.user.service.SysUserService;
import cn.com.chaochuang.common.util.NullBeanUtils;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.commoninfo.repository.AppEntpRepository;
import cn.com.chaochuang.datacenter.reference.DataChangeTable;
import cn.com.chaochuang.docwork.reference.FordoStatus;
import cn.com.chaochuang.synchdata.domain.SysSynchdataTask;
import cn.com.chaochuang.synchdata.reference.SynchDataStatus;
import cn.com.chaochuang.synchdata.repository.SysSynchdataTaskRepository;
import cn.com.chaochuang.voice.repository.VoiceInfoAttachRepository;
import cn.com.chaochuang.voice.repository.VoiceInfoRepository;

/**
 * @author LLM
 *
 */
@Service
@Transactional
public class SynchDataServiceImpl implements SynchDataService {
    @Autowired
    private SynchDataSource            appSynchDataSourceService;
    @Autowired
    private SynchDataSource            localDataSourceService;
    @Autowired
    private SynchDataSource            voiceSynchDataSourceService;
    @Autowired
    private SysSynchdataTaskRepository taskRepository;
    @Autowired
    private AppEntpRepository          entpRepository;
    @Autowired
    private AppLicenceRepository       licenceRepository;
    @Autowired
    private VoiceInfoRepository        voiceInfoRepository;
    @Autowired
    private VoiceInfoAttachRepository  voiceInfoAttachRepository;
    @Autowired
    private SysDepartmentRepository    departmentRepository;
    @Autowired
    private SysUserService             userService;
    @Autowired
    private SysUserRepository          userRepository;
    @Autowired
    private SynchDataSource            superviseDataSourceService;
    @Autowired
    private SynchDataSource            auditDataSourceService;
    @Autowired
    private SysDepartmentRepository    deptRepository;
    @Value("${target.user.findsql}")
    private String                     userFindsql;
    // 行政审批
    @Value("${supervise.fetch.interval}")
    private String                     fdDataInterval;
    @Value("${supervise.fd.countsql}")
    private String                     fdDataCountsql;
    @Value("${supervise.fd.datasql}")
    private String                     fdDatasql;
    @Value("${supervise.target.fd.findsql}")
    private String                     targetDataFindsql;
    @Value("${supervise.target.fd.insertsql}")
    private String                     targetDataInsertsql;
    @Value("${supervise.target.fd.updatesql}")
    private String                     targetDataUpdatasql;
    // 审评查验
    @Value("${audit.fetch.interval}")
    private String                     auditFdDataInterval;
    @Value("${audit.fd.countsql}")
    private String                     auditFdDataCountsql;
    @Value("${audit.fd.datasql}")
    private String                     auditFdDatasql;
    @Value("${audit.target.fd.findsql}")
    private String                     auditTargetDataFindsql;
    @Value("${audit.target.fd.insertsql}")
    private String                     auditTargetDataInsertsql;
    @Value("${audit.target.fd.updatesql}")
    private String                     auditTargetDataUpdatasql;
    // 系统数据库
    @Autowired
    private SynchDataSource            sysSynchDataSourceService;
    // 企业数据
    @Value("${app.entp.countsql}")
    private String                     entpCountSQL;
    @Value("${app.entp.datasql}")
    private String                     entpDataSQL;
    @Value("${app.entp.bussql}")
    private String                     entpBusSQL;
    @Value("${app.entp.addrsql}")
    private String                     entpAddrSQL;
    @Value("${app.entp.insertsql}")
    private String                     entpInsertSQL;
    @Value("${app.entp.updatesql}")
    private String                     entpUpdateSQL;
    @Value("${app.entp.licencesql}")
    private String                     entpLicenceSQL;
    @Value("${app.entp.licenceinsertsql}")
    private String                     entpLicenceInsertSQL;
    @Value("${app.entp.getmaxsql}")
    private String                     entpMaxIdSQL;
    @Value("${sequencesql}")
    private String                     sequenceSQL;
    @Value("${synchtasksql}")
    private String                     taskUpdateSQL;
    @Value("${voice.info.countsql}")
    private String                     infoCountSQL;
    @Value("${voice.info.datasql}")
    private String                     infoDataSQL;
    @Value("${voice.info.insertsql}")
    private String                     infoInsertSQL;
    @Value("${voice.info.affixsql}")
    private String                     affixDataSQL;
    @Value("${voice.info.affixinsertsql}")
    private String                     affixInsertSQL;
    @Value("${voice.info.rulesql}")
    private String                     infoRuleSQL;
    @Value("${sys.datachange.countsql}")
    private String                     dataChangeUserCountSQL;
    @Value("${sys.sysuser.select}")
    private String                     sysUserSelectSQL;
    @Value("${sys.datachange.deletesql}")
    private String                     sysUserDeleteSQL;
    @Value("${sys.sysdept.select}")
    private String                     sysDeptSelectSQL;
    /** 每次处理数据块记录数 */
    @Value("${datablock}")
    private Integer                    dataBlock = 500;

    /**
     * @see cn.com.chaochuang.synchdata.service.SynchDataService#synchAppEntpData(cn.com.chaochuang.synchdata.domain.SysSynchdataTask)
     */
    @Override
    public void synchAppEntpData(SysSynchdataTask task) {
        this.synchAppEntpDataCommon(task, false);
    }

    /**
     * 通用企业数据同步服务
     *
     * @param task
     * @param travers
     */
    private void synchAppEntpDataCommon(SysSynchdataTask task, boolean travers) {
        Connection entpConn = null;
        Connection localConn = null;
        Statement maxstat = null;
        PreparedStatement stat = null;
        PreparedStatement pentpstat = null;
        PreparedStatement pbusstat = null;
        PreparedStatement paddrstat = null;
        PreparedStatement pinsertstat = null;
        PreparedStatement pupdatestat = null;
        PreparedStatement pseqstat = null;
        PreparedStatement ptaskstat = null;

        ResultSet result = null;
        ResultSet busResult = null;
        ResultSet addrResult = null;
        ResultSet seqResult = null;

        Map<String, Integer> entpInsertSQLItem = this.buildInsertFieldMap(this.entpInsertSQL);
        // Map<String, Integer> entpLicenceInsertSQLItem = this.buildInsertFieldMap(this.entpLicenceInsertSQL);
        // 获取相对人库的企业数据
        try {
            entpConn = this.appSynchDataSourceService.getConnection();
            localConn = this.localDataSourceService.getConnection();
            if (entpConn == null) {
                task.setMemo("无法连接目的服务器同步失败！");
                task.setStatus(SynchDataStatus.同步完成);
                this.taskRepository.save(task);
                return;
            }
            Long startEntpId = Long.valueOf(0);
            stat = entpConn.prepareStatement(this.entpCountSQL);
            // 若要递增式同步数据则查询当前最大企业编号，从最大编号后同步
            if (!travers) {
                maxstat = localConn.createStatement();
                result = maxstat.executeQuery(this.entpMaxIdSQL);
                while (result.next()) {
                    startEntpId = result.getLong(1);
                }
            }
            result.close();
            // 获取本次同步任务需要同步的数据量
            stat.setLong(1, startEntpId);
            result = stat.executeQuery();
            result.next();
            // 需要同步的记录数
            Long count = result.getLong(1), minId = result.getLong(2), curId = result.getLong(2), maxId = result
                            .getLong(3);
            if (count <= 0) {
                task.setMemo("本次需同步数据记录数为0！");
                task.setStatus(SynchDataStatus.同步完成);
                this.taskRepository.save(task);
                return;
            }
            localConn.setAutoCommit(true);
            // 获取SQL数据
            pentpstat = entpConn.prepareStatement(this.entpDataSQL);
            pbusstat = entpConn.prepareStatement(this.entpBusSQL);
            paddrstat = entpConn.prepareStatement(this.entpAddrSQL);
            // plicencestat = entpConn.prepareStatement(this.entpLicenceSQL);
            // 插入和更新SQL
            pinsertstat = localConn.prepareStatement(this.entpInsertSQL);
            pupdatestat = localConn.prepareStatement(this.entpUpdateSQL);
            // plicenceinsertstat = localConn.prepareStatement(this.entpLicenceInsertSQL);
            pseqstat = localConn.prepareStatement(this.sequenceSQL);
            ptaskstat = localConn.prepareStatement(this.taskUpdateSQL.replaceAll("@ID", task.getId().toString()));

            pentpstat.setMaxRows(this.dataBlock);
            pentpstat.setFetchSize(this.dataBlock);
            // 重置任务的信息
            task.setNeedSynch(Long.valueOf(count));
            task.setStatus(SynchDataStatus.同步中);
            task.setBeginTime(new Date());
            this.updateTaskInfo(task, ptaskstat);
            StringBuilder busName = new StringBuilder();
            int idx = 0, countIdx = 0;
            // entp_id, entp_name, entp_province, contact, contact_duty, cell_phone, contact_address,
            // contact_postal_code, tel, register_address, register_fund, business_license, business_license_date,
            // handle_unit_name, city_name, fax, email
            // 另外处理字段：
            // rm_entp_id, licence_date_script, bus_name, longitude, latitude, input_date
            while (curId < maxId) {
                pentpstat.setObject(1, minId);
                pentpstat.setObject(2, minId + (this.dataBlock - 1));
                System.out.println("get entp data before: " + Tools.DATE_TIME_FORMAT.format(new Date()));
                result = pentpstat.executeQuery();
                System.out.println("get entp data after: " + Tools.DATE_TIME_FORMAT.format(new Date()));
                while (result.next()) {
                    idx++;

                    curId = result.getLong("entp_id");
                    // 查询当前编号的企业数据是否已经存在
                    if (this.entpRepository.findByRmEntpId(curId) == null) {
                        countIdx++;
                        // entp_id,rm_entp_id, entp_name, entp_province, contact, contact_duty, cell_phone,
                        // contact_address, contact_postal_code, tel, register_address, register_fund, business_license,
                        // business_license_date, city_name, handle_unit_name, longitude, latitude, fax, email,
                        // bus_name,input_date
                        pinsertstat.setObject(entpInsertSQLItem.get("rm_entp_id"), curId);
                        pinsertstat.setObject(entpInsertSQLItem.get("entp_name"), result.getObject("entp_name"));
                        pinsertstat.setObject(entpInsertSQLItem.get("entp_province"), result.getObject("entp_province"));
                        pinsertstat.setObject(entpInsertSQLItem.get("contact"), result.getObject("contact"));
                        pinsertstat.setObject(entpInsertSQLItem.get("contact_duty"), result.getObject("contact_duty"));
                        pinsertstat.setObject(entpInsertSQLItem.get("cell_phone"), result.getObject("cell_phone"));
                        pinsertstat.setObject(entpInsertSQLItem.get("contact_address"),
                                        result.getObject("contact_address"));
                        pinsertstat.setObject(entpInsertSQLItem.get("contact_postal_code"),
                                        result.getObject("contact_postal_code"));
                        pinsertstat.setObject(entpInsertSQLItem.get("tel"), result.getObject("tel"));
                        pinsertstat.setObject(entpInsertSQLItem.get("register_address"),
                                        result.getObject("register_address"));
                        pinsertstat.setObject(entpInsertSQLItem.get("register_fund"), result.getObject("register_fund"));
                        pinsertstat.setObject(entpInsertSQLItem.get("business_license"),
                                        result.getObject("business_license"));
                        pinsertstat.setObject(entpInsertSQLItem.get("business_license_date"),
                                        result.getObject("business_license_date"));
                        pinsertstat.setObject(entpInsertSQLItem.get("handle_unit_name"),
                                        result.getObject("handle_unit_name"));
                        pinsertstat.setObject(entpInsertSQLItem.get("city_name"), result.getObject("city_name"));
                        pinsertstat.setObject(entpInsertSQLItem.get("longitude"), null);
                        pinsertstat.setObject(entpInsertSQLItem.get("latitude"), null);
                        pbusstat.setObject(1, curId);
                        busResult = pbusstat.executeQuery();
                        busName = new StringBuilder("");
                        while (busResult.next()) {
                            if (!Tools.isEmptyString(busResult.getString("bus_name"))) {
                                busName.append(busResult.getString("entp_type_name")).append("：")
                                                .append(busResult.getString("bus_name")).append("<br>");
                            }
                        }
                        pinsertstat.setObject(entpInsertSQLItem.get("bus_name"), busName.toString());
                        paddrstat.setObject(1, curId);
                        addrResult = paddrstat.executeQuery();
                        while (addrResult.next()) {
                            pinsertstat.setObject(entpInsertSQLItem.get("longitude"), addrResult.getObject("longitude"));
                            pinsertstat.setObject(entpInsertSQLItem.get("latitude"), addrResult.getObject("latitude"));
                            break;
                        }
                        pinsertstat.setObject(entpInsertSQLItem.get("fax"), result.getObject("fax"));
                        pinsertstat.setObject(entpInsertSQLItem.get("email"), result.getObject("email"));
                        pinsertstat.setObject(entpInsertSQLItem.get("input_date"), new Timestamp(new Date().getTime()));
                        seqResult = pseqstat.executeQuery();
                        seqResult.next();
                        pinsertstat.setObject(entpInsertSQLItem.get("entp_id"), seqResult.getLong(1));
                        pinsertstat.addBatch();

                        // plicencestat.setObject(1, curId);
                        // licenceResult = plicencestat.executeQuery();
                        // while (licenceResult.next()) {
                        // seqResult = pseqstat.executeQuery();
                        // seqResult.next();
                        // if (this.licenceRepository.findByRmLicenceId(Long.valueOf(licenceResult.getObject(
                        // "rm_licence_id").toString())) != null) {
                        // continue;
                        // }
                        // try {
                        // plicenceinsertstat.setObject(entpLicenceInsertSQLItem.get("licence_id"),
                        // seqResult.getLong(1));
                        // plicenceinsertstat.setObject(entpLicenceInsertSQLItem.get("rm_licence_id"),
                        // licenceResult.getObject("rm_licence_id"));
                        // plicenceinsertstat.setObject(entpLicenceInsertSQLItem.get("rm_entp_id"),
                        // licenceResult.getObject("rm_entp_id"));
                        // plicenceinsertstat.setObject(entpLicenceInsertSQLItem.get("entp_type_name"),
                        // licenceResult.getObject("entp_type_name"));
                        // plicenceinsertstat.setObject(entpLicenceInsertSQLItem.get("licence_no"),
                        // licenceResult.getObject("licence_no"));
                        // plicenceinsertstat.setObject(entpLicenceInsertSQLItem.get("from_date"),
                        // this.changeDataType(licenceResult.getObject("from_date"), true));
                        // plicenceinsertstat.setObject(entpLicenceInsertSQLItem.get("to_date"),
                        // this.changeDataType(licenceResult.getObject("to_date"), true));
                        // plicenceinsertstat.setObject(entpLicenceInsertSQLItem.get("licence_time"),
                        // this.changeDataType(licenceResult.getObject("licence_time"), true));
                        // plicenceinsertstat.addBatch();
                        // } catch (Exception ex) {
                        // ex.printStackTrace();
                        // continue;
                        // }
                        // }
                    }
                }
                System.out.println("insert entp data before: " + Tools.DATE_TIME_FORMAT.format(new Date()));
                pinsertstat.executeBatch();
                System.out.println("insert entp data after: " + Tools.DATE_TIME_FORMAT.format(new Date()));
                pupdatestat.executeBatch();
                System.out.println("update entp data after: " + Tools.DATE_TIME_FORMAT.format(new Date()));

                pinsertstat.clearBatch();
                pupdatestat.clearBatch();

                pinsertstat.close();
                pupdatestat.close();

                System.out.println("insert licence data before: " + Tools.DATE_TIME_FORMAT.format(new Date()));
                // plicenceinsertstat.executeBatch();
                System.out.println("insert licence data after: " + Tools.DATE_TIME_FORMAT.format(new Date()));
                // plicenceinsertstat.clearBatch();
                // plicenceinsertstat.close();
                pinsertstat = localConn.prepareStatement(this.entpInsertSQL);
                pupdatestat = localConn.prepareStatement(this.entpUpdateSQL);
                // plicenceinsertstat = localConn.prepareStatement(this.entpLicenceInsertSQL);

                task.setFinishSynch(Long.valueOf(idx));
                this.updateTaskInfo(task, ptaskstat);
                minId += this.dataBlock;
            }
            task.setStatus(SynchDataStatus.同步完成);
            task.setFinishTime(new Date());
            task.setMemo("完成数据同步！");
            this.updateTaskInfo(task, ptaskstat);
        } catch (Exception ex) {
            ex.printStackTrace();
            task.setStatus(SynchDataStatus.同步完成);
            task.setFinishTime(new Date());
            task.setMemo("数据同步失败：" + ex.getMessage());
            // 备注内容最大是500汉字
            if (task.getMemo().length() > 500) {
                task.setMemo(task.getMemo().substring(0, 500));
            }
            this.updateTaskInfo(task, ptaskstat);
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (busResult != null) {
                    busResult.close();
                }
                if (addrResult != null) {
                    addrResult.close();
                }
                if (stat != null) {
                    stat.close();
                }
                if (pentpstat != null) {
                    pentpstat.close();
                }
                if (pbusstat != null) {
                    pbusstat.close();
                }
                if (paddrstat != null) {
                    paddrstat.close();
                }
                if (pinsertstat != null) {
                    pinsertstat.close();
                }
                if (pupdatestat != null) {
                    pupdatestat.close();
                }
                if (pseqstat != null) {
                    pseqstat.close();
                }
                if (ptaskstat != null) {
                    ptaskstat.close();
                }
                if (maxstat != null) {
                    maxstat.close();
                }
                if (localConn != null) {
                    localConn.close();
                }
                if (entpConn != null) {
                    entpConn.close();
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex.getMessage());
            }
        }
    }

    /**
     * 将insert的sql语句分解字段，update同样按这样的顺序
     *
     * @param insertSQL
     * @return
     */
    private Map<String, Integer> buildInsertFieldMap(String insertSQL) {
        Map<String, Integer> result = new HashMap();
        int sidx = insertSQL.indexOf("("), eidx = insertSQL.indexOf(")");
        String field = insertSQL.substring(sidx + 1, eidx);
        String[] fields = field.split(",");
        for (int i = 0; i < fields.length; i++) {
            result.put(fields[i].toLowerCase().trim(), i + 1);
        }
        return result;
    }

    /**
     * 将update的sql语句分解字段，update同样按这样的顺序
     *
     * @param updateSQL
     * @return
     */
    private Map<String, Integer> buildUpdateFieldMap(String updateSQL) {
        Map<String, Integer> result = new HashMap();
        String tmp = updateSQL.replaceAll("where", ","), tmpField = "";
        int sidx = tmp.indexOf("set");
        String field = tmp.substring(sidx + 3);
        String[] fields = field.split(",");
        for (int i = 0; i < fields.length; i++) {
            tmpField = fields[i].replaceAll("=", "");
            tmpField = tmpField.replaceAll("\\?", "");
            result.put(tmpField.toLowerCase().trim(), i + 1);
        }
        return result;
    }

    /**
     * 数据转换
     *
     * @param source
     * @return
     */
    private Object changeDataType(Object source, boolean toString) {
        if (source == null) {
            return null;
        }
        if (source.getClass().getName().toLowerCase().indexOf("timestamp") >= 0
                        || source.getClass().getName().toLowerCase().indexOf("date") >= 0) {
            if (toString) {
                try {
                    String formatDate = Tools.DATE_TIME_FORMAT.format(new Timestamp(((Date) source).getTime()));
                    if (Tools.isEmptyString(formatDate) || formatDate.length() != 19) {
                        return null;
                    }
                    if (Integer.valueOf(formatDate.substring(0, 4)) >= 2050) {
                        return null;
                    }
                    return formatDate;
                } catch (Exception ex) {
                    return null;
                }
            }
            return new Timestamp(((Date) source).getTime());
        }
        System.out.println("date type:" + source.getClass().getName() + "  datevalue=" + source);
        return source;
    }

    /**
     * 更新数据同步任务信息
     *
     * @param task
     * @param stat
     */
    private void updateTaskInfo(SysSynchdataTask task, PreparedStatement stat) {
        Map<String, Integer> fields = new HashMap();
        fields = this.buildUpdateFieldMap(this.taskUpdateSQL.substring(0, this.taskUpdateSQL.indexOf("where")));
        try {
            stat.setObject(fields.get("need_synch"), task.getNeedSynch());
            stat.setObject(fields.get("finish_synch"), task.getFinishSynch());
            stat.setObject(fields.get("begin_time"), this.changeDataType(task.getBeginTime(), false));
            stat.setObject(fields.get("finish_time"), this.changeDataType(task.getFinishTime(), false));
            stat.setObject(fields.get("status"), task.getStatus().getKey());
            stat.setObject(fields.get("memo"), task.getMemo());
            stat.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /**
     * @see cn.com.chaochuang.synchdata.service.SynchDataService#synchVoiceInfoData(cn.com.chaochuang.synchdata.domain.SysSynchdataTask)
     */
    @Override
    public void synchVoiceInfoData(SysSynchdataTask task) {
        Connection voiceConn = null;
        Connection localConn = null;
        Statement stat = null;
        PreparedStatement pvoicestat = null;
        PreparedStatement pinsertstat = null;
        PreparedStatement pvoiceaffixstat = null;
        PreparedStatement paffixinsertstat = null;
        PreparedStatement prulestat = null;
        PreparedStatement pseqstat = null;
        PreparedStatement ptaskstat = null;

        ResultSet result = null;
        ResultSet affixResult = null;
        ResultSet ruleResult = null;
        ResultSet seqResult = null;

        Map<String, Integer> infoInsertSQLItem = this.buildInsertFieldMap(this.infoInsertSQL);
        Map<String, Integer> affixInsertSQLItem = this.buildInsertFieldMap(this.affixInsertSQL);
        Map<Long, String> voiceRules = new HashMap();
        // 获取相对人库的企业数据
        try {
            voiceConn = this.voiceSynchDataSourceService.getConnection();
            localConn = this.localDataSourceService.getConnectionByClassName();
            if (voiceConn == null) {
                task.setMemo("无法连接目的服务器同步失败！");
                task.setStatus(SynchDataStatus.同步完成);
                this.taskRepository.save(task);
                return;
            }
            stat = voiceConn.createStatement();
            // 获取本次同步任务需要同步的数据量
            result = stat.executeQuery(this.infoCountSQL);
            result.next();
            // 需要同步的记录数
            Long count = result.getLong(1), minId = result.getLong(2), curId = result.getLong(2), maxId = result
                            .getLong(3);
            if (count <= 0) {
                task.setMemo("本次需同步数据记录数为0！");
                task.setStatus(SynchDataStatus.同步完成);
                this.taskRepository.save(task);
                return;
            }
            localConn.setAutoCommit(true);
            // 获取舆情SQL数据
            pvoicestat = voiceConn.prepareStatement(this.infoDataSQL);
            // 插入舆情记录SQL
            pinsertstat = localConn.prepareStatement(this.infoInsertSQL);
            // 获取舆情附件SQL
            pvoiceaffixstat = voiceConn.prepareStatement(this.affixDataSQL);
            // 插入舆情附件SQL
            paffixinsertstat = localConn.prepareStatement(this.affixInsertSQL);
            // 舆情规则查询
            prulestat = voiceConn.prepareStatement(this.infoRuleSQL);
            pseqstat = localConn.prepareStatement(this.sequenceSQL);
            ptaskstat = localConn.prepareStatement(this.taskUpdateSQL.replaceAll("@ID", task.getId().toString()));

            pvoicestat.setMaxRows(this.dataBlock);
            pvoicestat.setFetchSize(this.dataBlock);
            // 重置任务的信息
            task.setNeedSynch(Long.valueOf(count));
            task.setStatus(SynchDataStatus.同步中);
            task.setBeginTime(new Date());
            this.updateTaskInfo(task, ptaskstat);
            int idx = 0, countIdx = 0;
            // info_id, rule_name, voice_info_is_home, voice_info_title, voice_info_content, voice_info_source,
            // voice_info_source_url, voice_info_author, voice_info_issue_time, voice_info_status,
            // voice_info_discover_time,
            // voice_info_discover_user, voice_info_nature, voice_info_is_local, meta_type, transmitconut,
            // voice_info_is_sensitive,
            // clickcount, rm_affix_id, rm_info_id, local_data, content_area, rule_id, unit_org_id
            while (curId < maxId) {
                pvoicestat.setObject(1, minId);
                pvoicestat.setObject(2, minId + (this.dataBlock - 1));
                result = pvoicestat.executeQuery();
                while (result.next()) {
                    idx++;

                    curId = result.getLong("info_id");
                    // 查询当前编号的企业数据是否已经存在
                    if (this.voiceInfoRepository.findByRmInfoId(curId) == null) {
                        countIdx++;
                        pinsertstat.setObject(infoInsertSQLItem.get("voice_info_discover_user"), null);
                        pinsertstat.setObject(infoInsertSQLItem.get("unit_org_id"), null);
                        if (result.getObject("voice_info_discover_user") != null) {
                            SysUser user = this.userService.findByRmUserInfoId(Long.valueOf(result.getObject(
                                            "voice_info_discover_user").toString()));
                            if (user != null) {
                                pinsertstat.setObject(infoInsertSQLItem.get("voice_info_discover_user"),
                                                user.getRmUserId());
                                pinsertstat.setObject(infoInsertSQLItem.get("unit_org_id"), user.getDepartment()
                                                .getAncestorDep());
                            }
                        }
                        // 获取舆情规则名称
                        pinsertstat.setObject(infoInsertSQLItem.get("rule_name"), null);
                        if (result.getObject("rule_id") != null) {
                            prulestat.setObject(1, result.getObject("rule_id"));
                            ruleResult = prulestat.executeQuery();
                            if (!voiceRules.containsKey(result.getObject("rule_id"))) {
                                while (ruleResult.next()) {
                                    voiceRules.put(result.getLong("rule_id"), ruleResult.getString("rule_name"));
                                    continue;
                                }
                            }
                            pinsertstat.setObject(infoInsertSQLItem.get("rule_name"),
                                            voiceRules.get(result.getObject("rule_id")));
                        }
                        pinsertstat.setObject(infoInsertSQLItem.get("rm_info_id"), curId);
                        pinsertstat.setObject(infoInsertSQLItem.get("voice_info_is_home"),
                                        result.getObject("voice_info_is_home"));
                        pinsertstat.setObject(infoInsertSQLItem.get("voice_info_title"),
                                        result.getObject("voice_info_title"));
                        // 设置Clob类型字段
                        Clob voiceclob = result.getClob("voice_info_content");
                        Clob clob = (Clob) createOracleLob(localConn, "oracle.sql.CLOB");
                        if (result.getClob("voice_info_content") != null) {
                            pinsertstat.setClob(
                                            infoInsertSQLItem.get("voice_info_content"),
                                            strToClob(voiceclob.getSubString(Long.valueOf(1),
                                                            Long.valueOf(voiceclob.length()).intValue()), clob));
                        } else {
                            pinsertstat.setClob(infoInsertSQLItem.get("voice_info_content"), strToClob("", clob));
                        }
                        pinsertstat.setObject(infoInsertSQLItem.get("voice_info_source"),
                                        result.getObject("voice_info_source"));
                        pinsertstat.setObject(infoInsertSQLItem.get("voice_info_source_url"),
                                        result.getObject("voice_info_source_url"));
                        pinsertstat.setObject(infoInsertSQLItem.get("voice_info_author"),
                                        result.getObject("voice_info_author"));
                        pinsertstat.setObject(infoInsertSQLItem.get("voice_info_issue_time"),
                                        result.getObject("voice_info_issue_time"));
                        pinsertstat.setObject(infoInsertSQLItem.get("voice_info_status"),
                                        result.getObject("voice_info_status"));
                        pinsertstat.setObject(infoInsertSQLItem.get("voice_info_discover_time"),
                                        result.getObject("voice_info_discover_time"));
                        pinsertstat.setObject(infoInsertSQLItem.get("voice_info_nature"),
                                        result.getObject("voice_info_nature"));
                        pinsertstat.setObject(infoInsertSQLItem.get("voice_info_is_local"),
                                        result.getObject("voice_info_is_local"));
                        pinsertstat.setObject(infoInsertSQLItem.get("meta_type"), result.getObject("meta_type"));
                        pinsertstat.setObject(infoInsertSQLItem.get("transmitconut"), result.getObject("transmitconut"));
                        pinsertstat.setObject(infoInsertSQLItem.get("voice_info_is_sensitive"),
                                        result.getObject("voice_info_is_sensitive"));
                        pinsertstat.setObject(infoInsertSQLItem.get("clickcount"), result.getObject("clickcount"));
                        pinsertstat.setObject(infoInsertSQLItem.get("rm_affix_id"), result.getObject("affix_id"));

                        pinsertstat.setObject(infoInsertSQLItem.get("content_area"), result.getObject("content_area"));
                        pinsertstat.setObject(infoInsertSQLItem.get("rule_id"), result.getObject("rule_id"));
                        pinsertstat.setObject(infoInsertSQLItem.get("local_data"), LocalData.非本地数据.getKey());
                        seqResult = pseqstat.executeQuery();
                        seqResult.next();
                        pinsertstat.setObject(infoInsertSQLItem.get("info_id"), seqResult.getLong(1));
                        pinsertstat.addBatch();
                        // 获取舆情附件记录
                        if (result.getObject("affix_id") != null) {
                            pvoiceaffixstat.setObject(1, result.getObject("affix_id"));
                            affixResult = pvoiceaffixstat.executeQuery();
                            while (affixResult.next()) {
                                seqResult = pseqstat.executeQuery();
                                seqResult.next();
                                try {
                                    // 若本地数据库数据不存在则插入数据，否则跳过
                                    if (this.voiceInfoAttachRepository.findByRmAttachId(affixResult.getLong("file_id")) != null) {
                                        continue;
                                    }
                                    // file_id, affix_id, true_name, file_size, is_image, save_path, pdf_flag, grounp_id
                                    // attach_id, true_name, file_size, is_image, save_path, rm_attach_id, rm_affix_id,
                                    // local_data
                                    paffixinsertstat.setObject(affixInsertSQLItem.get("attach_id"),
                                                    seqResult.getLong(1));
                                    paffixinsertstat.setObject(affixInsertSQLItem.get("true_name"),
                                                    affixResult.getObject("true_name"));
                                    paffixinsertstat.setObject(affixInsertSQLItem.get("file_size"),
                                                    affixResult.getObject("file_size"));
                                    paffixinsertstat.setObject(affixInsertSQLItem.get("is_image"),
                                                    affixResult.getObject("is_image"));
                                    paffixinsertstat.setObject(affixInsertSQLItem.get("save_path"),
                                                    affixResult.getObject("save_path"));
                                    paffixinsertstat.setObject(affixInsertSQLItem.get("true_name"),
                                                    affixResult.getObject("true_name"));
                                    paffixinsertstat.setObject(affixInsertSQLItem.get("rm_attach_id"),
                                                    affixResult.getObject("file_id"));
                                    paffixinsertstat.setObject(affixInsertSQLItem.get("rm_affix_id"),
                                                    affixResult.getObject("affix_id"));
                                    paffixinsertstat.setObject(affixInsertSQLItem.get("local_data"),
                                                    LocalData.非本地数据.getKey());
                                    paffixinsertstat.addBatch();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                    continue;
                                }
                            }
                        }
                    }
                }
                pinsertstat.executeBatch();
                paffixinsertstat.executeBatch();

                pinsertstat.clearBatch();
                paffixinsertstat.clearBatch();

                // pinsertstat.close();
                // paffixinsertstat.close();
                //
                // pinsertstat = localConn.prepareStatement(this.infoInsertSQL);
                // paffixinsertstat = localConn.prepareStatement(this.affixInsertSQL);
                task.setFinishSynch(Long.valueOf(idx));
                this.updateTaskInfo(task, ptaskstat);
                minId += this.dataBlock;
            }
            localConn.commit();
            task.setStatus(SynchDataStatus.同步完成);
            task.setFinishTime(new Date());
            task.setMemo("完成数据同步！");
            this.updateTaskInfo(task, ptaskstat);
        } catch (Exception ex) {
            ex.printStackTrace();
            task.setStatus(SynchDataStatus.同步完成);
            task.setFinishTime(new Date());
            task.setMemo("数据同步失败：" + ex.getMessage());
            // 备注内容最大是500汉字
            if (task.getMemo().length() > 500) {
                task.setMemo(task.getMemo().substring(0, 500));
            }
            this.updateTaskInfo(task, ptaskstat);
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (affixResult != null) {
                    affixResult.close();
                }
                if (seqResult != null) {
                    seqResult.close();
                }
                if (stat != null) {
                    stat.close();
                }
                if (pvoicestat != null) {
                    pvoicestat.close();
                }
                if (pvoiceaffixstat != null) {
                    pvoiceaffixstat.close();
                }
                if (paffixinsertstat != null) {
                    paffixinsertstat.close();
                }
                if (pinsertstat != null) {
                    pinsertstat.close();
                }
                if (pseqstat != null) {
                    pseqstat.close();
                }
                if (ptaskstat != null) {
                    ptaskstat.close();
                }
                if (localConn != null) {
                    localConn.close();
                }
                if (voiceConn != null) {
                    voiceConn.close();
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex.getMessage());
            }
        }
    }

    @Override
    public void synchSuperviseFdData(SysSynchdataTask task) {
        Connection superviseConn = null;
        Connection localConn = null;
        PreparedStatement ptaskstat = null;

        PreparedStatement countPreSta = null;
        PreparedStatement dataPreSta = null;
        PreparedStatement findPreSta = null;
        PreparedStatement insertPreSta = null;
        PreparedStatement updatePreSta = null;
        PreparedStatement pseqstat = null;
        PreparedStatement userFindPreSta = null;

        ResultSet userRes = null;
        ResultSet countRes = null;
        ResultSet findRes = null;
        ResultSet fdDataRes = null;
        ResultSet seqResult = null;
        try {
            superviseConn = this.superviseDataSourceService.getConnection();
            localConn = this.localDataSourceService.getConnection();
            if (superviseConn == null) {
                task.setMemo("无法连接目的服务器同步失败！");
                task.setStatus(SynchDataStatus.同步完成);
                this.taskRepository.save(task);
                return;
            }
            localConn.setAutoCommit(true);
            Date startTime = Tools.diffDate(new Date(), new Integer(this.fdDataInterval));
            String fmtTime = Tools.DATE_FORMAT.format(startTime);
            java.sql.Date nowDate = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
            // 1.查找大于指定时间的记录数
            countPreSta = superviseConn.prepareStatement(this.fdDataCountsql);
            countPreSta.setObject(1, fmtTime);
            countRes = countPreSta.executeQuery();
            countRes.next();
            Long totalCount = countRes.getLong(1);// 总记录数据
            Long maxId = countRes.getLong(2);// 最大id数
            Long curId = 0L;
            Long finishQty = 0L;
            if (totalCount <= 0) {
                task.setMemo("本次需同步数据记录数为0！");
                task.setStatus(SynchDataStatus.同步完成);
                this.taskRepository.save(task);
                return;
            }
            ptaskstat = localConn.prepareStatement(this.taskUpdateSQL.replaceAll("@ID", task.getId().toString()));
            // 重置任务的信息
            task.setNeedSynch(totalCount);
            task.setStatus(SynchDataStatus.同步中);
            task.setBeginTime(new Date());
            this.updateTaskInfo(task, ptaskstat);
            // 2.获取大于指定时间的数据
            dataPreSta = superviseConn.prepareStatement(this.fdDatasql);
            findPreSta = localConn.prepareStatement(this.targetDataFindsql);
            Map<String, Integer> insertSQLItem = this.buildInsertFieldMap(this.targetDataInsertsql);
            Map<String, Integer> updateSQLItem = this.buildUpdateFieldMap(this.targetDataUpdatasql);
            insertPreSta = localConn.prepareStatement(this.targetDataInsertsql);
            updatePreSta = localConn.prepareStatement(this.targetDataUpdatasql);
            // 获取序列号
            pseqstat = localConn.prepareStatement(this.sequenceSQL);
            // 查询用户rmUserId
            userFindPreSta = localConn.prepareStatement(this.userFindsql);
            Object rmPendingId = null;
            while (curId < maxId) {
                dataPreSta.setObject(1, fmtTime);
                dataPreSta.setObject(2, curId);
                dataPreSta.setMaxRows(this.dataBlock);
                dataPreSta.setFetchSize(this.dataBlock);
                fdDataRes = dataPreSta.executeQuery();
                while (fdDataRes.next()) {
                    // 3.循环数据项，先通过原待办id查询本地数据是否存在，存在则更新，否则新增
                    rmPendingId = fdDataRes.getObject(1);
                    findPreSta.setObject(1, rmPendingId);
                    findRes = findPreSta.executeQuery();
                    if (findRes.next()) {
                        // 4.更新
                        updatePreSta.setObject(updateSQLItem.get("rm_pending_id"),
                                        fdDataRes.getObject("pending_handle_id"));
                        updatePreSta.setObject(updateSQLItem.get("title"), fdDataRes.getObject("title"));
                        updatePreSta.setObject(updateSQLItem.get("url"), fdDataRes.getObject("url"));
                        updatePreSta.setObject(updateSQLItem.get("sender_id"), fdDataRes.getObject("sender_id"));
                        updatePreSta.setObject(updateSQLItem.get("send_time"), fdDataRes.getObject("send_time"));
                        updatePreSta.setObject(updateSQLItem.get("sender_name"), fdDataRes.getObject("sender_name"));
                        updatePreSta.setObject(updateSQLItem.get("read_time"), fdDataRes.getObject("read_time"));
                        updatePreSta.setObject(updateSQLItem.get("emergency_level"),
                                        fdDataRes.getObject("emergency_flag"));
                        updatePreSta.setObject(updateSQLItem.get("sender_dept_name"),
                                        fdDataRes.getObject("receiver_dep_name"));
                        updatePreSta.setObject(updateSQLItem.get("node_status"), fdDataRes.getObject("status"));
                        updatePreSta.setObject(updateSQLItem.get("node_id"), fdDataRes.getObject("node_id"));
                        updatePreSta.setObject(updateSQLItem.get("item_apply_id"), fdDataRes.getObject("business_key"));
                        updatePreSta.setObject(updateSQLItem.get("node_code"), fdDataRes.getObject("node_code"));
                        updatePreSta.setObject(updateSQLItem.get("flow_inst_id"), fdDataRes.getObject("flow_inst_id"));
                        updatePreSta.setObject(updateSQLItem.get("local_data"), LocalData.非本地数据.getKey());
                        updatePreSta.setObject(updateSQLItem.get("rm_pending_id"),
                                        fdDataRes.getObject("pending_handle_id"));
                        // 将用户的rmUserInfoId转成rmUserId
                        userFindPreSta.setObject(1, fdDataRes.getObject("receiver_id"));
                        userRes = userFindPreSta.executeQuery();
                        if (userRes.next()) {
                            updatePreSta.setObject(updateSQLItem.get("recipient_id"), userRes.getObject(1));
                        } else {
                            System.out.println("找不到id为：" + fdDataRes.getObject("receiver_id") + " 的用户");
                            updatePreSta.setObject(updateSQLItem.get("recipient_id"), 0);
                        }
                        updatePreSta.addBatch();
                    } else {
                        // 4.新增
                        insertPreSta.setObject(insertSQLItem.get("local_data"), LocalData.非本地数据.getKey());
                        insertPreSta.setObject(insertSQLItem.get("status"), FordoStatus.未读.getKey());
                        insertPreSta.setObject(insertSQLItem.get("input_date"), nowDate);
                        insertPreSta.setObject(insertSQLItem.get("rm_pending_id"),
                                        fdDataRes.getObject("pending_handle_id"));
                        insertPreSta.setObject(insertSQLItem.get("title"), fdDataRes.getObject("title"));
                        insertPreSta.setObject(insertSQLItem.get("url"), fdDataRes.getObject("url"));
                        insertPreSta.setObject(insertSQLItem.get("sender_id"), fdDataRes.getObject("sender_id"));
                        insertPreSta.setObject(insertSQLItem.get("send_time"), fdDataRes.getObject("send_time"));
                        insertPreSta.setObject(insertSQLItem.get("sender_name"), fdDataRes.getObject("sender_name"));
                        insertPreSta.setObject(insertSQLItem.get("read_time"), fdDataRes.getObject("read_time"));
                        insertPreSta.setObject(insertSQLItem.get("emergency_level"),
                                        fdDataRes.getObject("emergency_flag"));
                        insertPreSta.setObject(insertSQLItem.get("sender_dept_name"),
                                        fdDataRes.getObject("receiver_dep_name"));
                        insertPreSta.setObject(insertSQLItem.get("node_status"), fdDataRes.getObject("status"));
                        insertPreSta.setObject(insertSQLItem.get("node_id"), fdDataRes.getObject("node_id"));
                        insertPreSta.setObject(insertSQLItem.get("item_apply_id"), fdDataRes.getObject("business_key"));
                        insertPreSta.setObject(insertSQLItem.get("node_code"), fdDataRes.getObject("node_code"));
                        insertPreSta.setObject(insertSQLItem.get("flow_inst_id"), fdDataRes.getObject("flow_inst_id"));
                        // 将用户的rmUserInfoId转成rmUserId
                        userFindPreSta.setObject(1, fdDataRes.getObject("receiver_id"));
                        userRes = userFindPreSta.executeQuery();
                        if (userRes.next()) {
                            insertPreSta.setObject(insertSQLItem.get("recipient_id"), userRes.getObject(1));
                        } else {
                            System.out.println("找不到id为：" + fdDataRes.getObject("receiver_id") + " 的用户");
                            insertPreSta.setObject(insertSQLItem.get("recipient_id"), 0);
                        }
                        seqResult = pseqstat.executeQuery();
                        seqResult.next();
                        insertPreSta.setObject(insertSQLItem.get("fordo_id"), seqResult.getLong(1));
                        insertPreSta.addBatch();
                    }

                }
                curId = new Long(rmPendingId.toString());
                int updateQty = updatePreSta.executeBatch().length;
                int insertQty = insertPreSta.executeBatch().length;
                updatePreSta.clearBatch();
                insertPreSta.clearBatch();
                finishQty = finishQty + updateQty + insertQty;
                task.setFinishSynch(finishQty);
                this.updateTaskInfo(task, ptaskstat);
            }
            localConn.commit();
            task.setStatus(SynchDataStatus.同步完成);
            task.setFinishTime(new Date());
            task.setFinishSynch(finishQty);
            task.setMemo("完成数据同步！");
            this.updateTaskInfo(task, ptaskstat);
        } catch (Exception ex) {
            ex.printStackTrace();
            task.setStatus(SynchDataStatus.同步完成);
            task.setFinishTime(new Date());
            task.setMemo("数据同步失败：" + ex.getMessage());
            // 备注内容最大是500汉字
            if (task.getMemo().length() > 500) {
                task.setMemo(task.getMemo().substring(0, 500));
            }
            this.updateTaskInfo(task, ptaskstat);
        } finally {
            try {
                if (countRes != null) {
                    countRes.close();
                }
                if (findRes != null) {
                    findRes.close();
                }
                if (fdDataRes != null) {
                    fdDataRes.close();
                }
                if (seqResult != null) {
                    seqResult.close();
                }
                if (userRes != null) {
                    userRes.close();
                }
                if (userFindPreSta != null) {
                    userFindPreSta.close();
                }
                if (ptaskstat != null) {
                    ptaskstat.close();
                }
                if (countPreSta != null) {
                    countPreSta.close();
                }
                if (dataPreSta != null) {
                    dataPreSta.close();
                }
                if (findPreSta != null) {
                    findPreSta.close();
                }
                if (insertPreSta != null) {
                    insertPreSta.close();
                }
                if (updatePreSta != null) {
                    updatePreSta.close();
                }
                if (pseqstat != null) {
                    pseqstat.close();
                }
                if (superviseConn != null) {
                    superviseConn.close();
                }
                if (localConn != null) {
                    localConn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @see cn.com.chaochuang.synchdata.service.SynchDataService#synchAuditFdData(cn.com.chaochuang.synchdata.domain.SysSynchdataTask)
     */
    @Override
    public void synchAuditFdData(SysSynchdataTask task) {
        Connection auditConn = null;
        Connection localConn = null;
        PreparedStatement ptaskstat = null;

        PreparedStatement countPreSta = null;
        PreparedStatement dataPreSta = null;
        PreparedStatement findPreSta = null;
        PreparedStatement insertPreSta = null;
        PreparedStatement updatePreSta = null;
        PreparedStatement pseqstat = null;
        PreparedStatement userFindPreSta = null;

        ResultSet userRes = null;
        ResultSet countRes = null;
        ResultSet findRes = null;
        ResultSet fdDataRes = null;
        ResultSet seqResult = null;
        try {
            auditConn = this.auditDataSourceService.getConnection();
            localConn = this.localDataSourceService.getConnection();
            if (auditConn == null) {
                task.setMemo("无法连接目的服务器同步失败！");
                task.setStatus(SynchDataStatus.同步完成);
                this.taskRepository.save(task);
                return;
            }
            localConn.setAutoCommit(true);
            Date startTime = Tools.diffDate(new Date(), new Integer(this.auditFdDataInterval));
            String fmtTime = Tools.DATE_FORMAT.format(startTime);
            java.sql.Date nowDate = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
            // 1.查找大于指定时间的记录数
            countPreSta = auditConn.prepareStatement(this.auditFdDataCountsql);
            countPreSta.setObject(1, fmtTime);
            countRes = countPreSta.executeQuery();
            countRes.next();
            Long totalCount = countRes.getLong(1);// 总记录数据
            Long maxId = countRes.getLong(2);// 最大id数
            Long curId = 0L;
            Long finishQty = 0L;
            if (totalCount <= 0) {
                task.setMemo("本次需同步数据记录数为0！");
                task.setStatus(SynchDataStatus.同步完成);
                this.taskRepository.save(task);
                return;
            }
            ptaskstat = localConn.prepareStatement(this.taskUpdateSQL.replaceAll("@ID", task.getId().toString()));
            // 重置任务的信息
            task.setNeedSynch(totalCount);
            task.setStatus(SynchDataStatus.同步中);
            task.setBeginTime(new Date());
            this.updateTaskInfo(task, ptaskstat);
            // 2.获取大于指定时间的数据
            dataPreSta = auditConn.prepareStatement(this.auditFdDatasql);
            findPreSta = localConn.prepareStatement(this.auditTargetDataFindsql);
            Map<String, Integer> insertSQLItem = this.buildInsertFieldMap(this.auditTargetDataInsertsql);
            Map<String, Integer> updateSQLItem = this.buildUpdateFieldMap(this.auditTargetDataUpdatasql);
            insertPreSta = localConn.prepareStatement(this.auditTargetDataInsertsql);
            updatePreSta = localConn.prepareStatement(this.auditTargetDataUpdatasql);
            // 获取序列号
            pseqstat = localConn.prepareStatement(this.sequenceSQL);
            // 查询用户rmUserId
            userFindPreSta = localConn.prepareStatement(this.userFindsql);
            Object rmPendingId = null;
            while (curId < maxId) {
                dataPreSta.setObject(1, fmtTime);
                dataPreSta.setObject(2, curId);
                dataPreSta.setMaxRows(this.dataBlock);
                dataPreSta.setFetchSize(this.dataBlock);
                fdDataRes = dataPreSta.executeQuery();
                while (fdDataRes.next()) {
                    // 3.循环数据项，先通过原待办id查询本地数据是否存在，存在则更新，否则新增
                    rmPendingId = fdDataRes.getObject(1);
                    findPreSta.setObject(1, rmPendingId);
                    findRes = findPreSta.executeQuery();
                    if (findRes.next()) {
                        // 4.更新
                        updatePreSta.setObject(updateSQLItem.get("rm_pending_id"),
                                        fdDataRes.getObject("pending_handle_id"));
                        updatePreSta.setObject(updateSQLItem.get("title"), fdDataRes.getObject("title"));
                        updatePreSta.setObject(updateSQLItem.get("url"), fdDataRes.getObject("url"));
                        updatePreSta.setObject(updateSQLItem.get("sender_id"), fdDataRes.getObject("sender_id"));
                        updatePreSta.setObject(updateSQLItem.get("send_time"), fdDataRes.getObject("send_time"));
                        updatePreSta.setObject(updateSQLItem.get("sender_name"), fdDataRes.getObject("sender_name"));
                        updatePreSta.setObject(updateSQLItem.get("read_time"), fdDataRes.getObject("read_time"));
                        updatePreSta.setObject(updateSQLItem.get("emergency_level"),
                                        fdDataRes.getObject("emergency_flag"));
                        updatePreSta.setObject(updateSQLItem.get("sender_dept_name"),
                                        fdDataRes.getObject("receiver_dep_name"));
                        updatePreSta.setObject(updateSQLItem.get("node_status"), fdDataRes.getObject("status"));
                        updatePreSta.setObject(updateSQLItem.get("node_id"), fdDataRes.getObject("node_id"));
                        updatePreSta.setObject(updateSQLItem.get("task_id"), fdDataRes.getObject("business_key"));
                        updatePreSta.setObject(updateSQLItem.get("node_code"), fdDataRes.getObject("node_code"));
                        updatePreSta.setObject(updateSQLItem.get("flow_inst_id"), fdDataRes.getObject("flow_inst_id"));
                        updatePreSta.setObject(updateSQLItem.get("local_data"), LocalData.非本地数据.getKey());
                        updatePreSta.setObject(updateSQLItem.get("rm_pending_id"),
                                        fdDataRes.getObject("pending_handle_id"));
                        // 将用户的rmUserInfoId转成rmUserId
                        userFindPreSta.setObject(1, fdDataRes.getObject("receiver_id"));
                        userRes = userFindPreSta.executeQuery();
                        if (userRes.next()) {
                            updatePreSta.setObject(updateSQLItem.get("recipient_id"), userRes.getObject(1));
                        } else {
                            System.out.println("找不到id为：" + fdDataRes.getObject("receiver_id") + " 的用户");
                            updatePreSta.setObject(updateSQLItem.get("recipient_id"), 0);
                        }
                        updatePreSta.addBatch();
                    } else {
                        // 4.新增
                        insertPreSta.setObject(insertSQLItem.get("local_data"), LocalData.非本地数据.getKey());
                        insertPreSta.setObject(insertSQLItem.get("status"), FordoStatus.未读.getKey());
                        insertPreSta.setObject(insertSQLItem.get("input_date"), nowDate);
                        insertPreSta.setObject(insertSQLItem.get("rm_pending_id"),
                                        fdDataRes.getObject("pending_handle_id"));
                        insertPreSta.setObject(insertSQLItem.get("title"), fdDataRes.getObject("title"));
                        insertPreSta.setObject(insertSQLItem.get("url"), fdDataRes.getObject("url"));
                        insertPreSta.setObject(insertSQLItem.get("sender_id"), fdDataRes.getObject("sender_id"));
                        insertPreSta.setObject(insertSQLItem.get("send_time"), fdDataRes.getObject("send_time"));
                        insertPreSta.setObject(insertSQLItem.get("sender_name"), fdDataRes.getObject("sender_name"));
                        insertPreSta.setObject(insertSQLItem.get("read_time"), fdDataRes.getObject("read_time"));
                        insertPreSta.setObject(insertSQLItem.get("emergency_level"),
                                        fdDataRes.getObject("emergency_flag"));
                        insertPreSta.setObject(insertSQLItem.get("sender_dept_name"),
                                        fdDataRes.getObject("receiver_dep_name"));
                        insertPreSta.setObject(insertSQLItem.get("node_status"), fdDataRes.getObject("status"));
                        insertPreSta.setObject(insertSQLItem.get("node_id"), fdDataRes.getObject("node_id"));
                        insertPreSta.setObject(insertSQLItem.get("task_id"), fdDataRes.getObject("business_key"));
                        insertPreSta.setObject(insertSQLItem.get("node_code"), fdDataRes.getObject("node_code"));
                        insertPreSta.setObject(insertSQLItem.get("flow_inst_id"), fdDataRes.getObject("flow_inst_id"));
                        // 将用户的rmUserInfoId转成rmUserId
                        userFindPreSta.setObject(1, fdDataRes.getObject("receiver_id"));
                        userRes = userFindPreSta.executeQuery();
                        if (userRes.next()) {
                            insertPreSta.setObject(insertSQLItem.get("recipient_id"), userRes.getObject(1));
                        } else {
                            System.out.println("找不到id为：" + fdDataRes.getObject("receiver_id") + " 的用户");
                            insertPreSta.setObject(insertSQLItem.get("recipient_id"), 0);
                        }
                        seqResult = pseqstat.executeQuery();
                        seqResult.next();
                        insertPreSta.setObject(insertSQLItem.get("fordo_id"), seqResult.getLong(1));
                        insertPreSta.addBatch();
                    }

                }
                curId = new Long(rmPendingId.toString());
                int updateQty = updatePreSta.executeBatch().length;
                int insertQty = insertPreSta.executeBatch().length;
                updatePreSta.clearBatch();
                insertPreSta.clearBatch();
                finishQty = finishQty + updateQty + insertQty;
                task.setFinishSynch(finishQty);
                this.updateTaskInfo(task, ptaskstat);
            }
            task.setStatus(SynchDataStatus.同步完成);
            task.setFinishTime(new Date());
            task.setFinishSynch(finishQty);
            task.setMemo("完成数据同步！");
            this.updateTaskInfo(task, ptaskstat);
        } catch (Exception ex) {
            ex.printStackTrace();
            task.setStatus(SynchDataStatus.同步完成);
            task.setFinishTime(new Date());
            task.setMemo("数据同步失败：" + ex.getMessage());
            // 备注内容最大是500汉字
            if (task.getMemo().length() > 500) {
                task.setMemo(task.getMemo().substring(0, 500));
            }
            this.updateTaskInfo(task, ptaskstat);
        } finally {
            try {
                if (countRes != null) {
                    countRes.close();
                }
                if (findRes != null) {
                    findRes.close();
                }
                if (fdDataRes != null) {
                    fdDataRes.close();
                }
                if (seqResult != null) {
                    seqResult.close();
                }
                if (userRes != null) {
                    userRes.close();
                }
                if (userFindPreSta != null) {
                    userFindPreSta.close();
                }
                if (ptaskstat != null) {
                    ptaskstat.close();
                }
                if (countPreSta != null) {
                    countPreSta.close();
                }
                if (dataPreSta != null) {
                    dataPreSta.close();
                }
                if (findPreSta != null) {
                    findPreSta.close();
                }
                if (insertPreSta != null) {
                    insertPreSta.close();
                }
                if (updatePreSta != null) {
                    updatePreSta.close();
                }
                if (pseqstat != null) {
                    pseqstat.close();
                }
                if (auditConn != null) {
                    auditConn.close();
                }
                if (localConn != null) {
                    localConn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 创建Oracle Lob类型
     *
     * @param conn
     * @param lobClassName
     * @return
     * @throws Exception
     */
    public Object createOracleLob(Connection conn, String lobClassName) throws Exception {
        Class lobClass = conn.getClass().getClassLoader().loadClass(lobClassName);
        final Integer DURATION_SESSION = new Integer(lobClass.getField("DURATION_SESSION").getInt(null));
        final Integer MODE_READWRITE = new Integer(lobClass.getField("MODE_READWRITE").getInt(null));
        Method createTemporary = lobClass.getMethod("createTemporary", new Class[] { Connection.class, boolean.class,
                        int.class });
        Object lob = createTemporary.invoke(null, new Object[] { conn, false, DURATION_SESSION });
        Method open = lobClass.getMethod("open", new Class[] { int.class });
        open.invoke(lob, new Object[] { MODE_READWRITE });
        return lob;
    }

    /**
     * 字符串转成CLOB
     *
     * @param str
     * @param lob
     * @return
     * @throws Exception
     */
    public Clob strToClob(String str, Clob lob) throws Exception {
        Method methodToInvoke = lob.getClass().getMethod("getCharacterOutputStream", (Class[]) null);
        Writer writer = (Writer) methodToInvoke.invoke(lob, (Object[]) null);
        writer.write(str);
        writer.close();
        return lob;
    }

    /**
     * @see cn.com.chaochuang.synchdata.service.SynchDataService#synchSysDataChangeOfUserData(cn.com.chaochuang.synchdata.domain.SysSynchdataTask)
     */
    @Override
    public void synchSysDataChangeOfUserData(SysSynchdataTask task) {
        Connection sysConn = null;
        Connection localConn = null;
        PreparedStatement ptaskstat = null;

        PreparedStatement countPreSta = null;
        PreparedStatement dataPreSta = null;
        PreparedStatement deleteSta = null;
        ResultSet userRes = null;
        ResultSet countRes = null;
        try {
            sysConn = this.sysSynchDataSourceService.getConnection();
            localConn = this.localDataSourceService.getConnection();
            if (sysConn == null) {
                task.setMemo("无法连接目的服务器同步失败！");
                task.setStatus(SynchDataStatus.同步完成);
                this.taskRepository.save(task);
                return;
            }
            ptaskstat = localConn.prepareStatement(this.taskUpdateSQL.replaceAll("@ID", task.getId().toString()));
            localConn.setAutoCommit(true);
            countPreSta = sysConn.prepareStatement(this.dataChangeUserCountSQL);
            countRes = countPreSta.executeQuery();
            int count = 0;
            Long changeId, userId;
            String opType, changeScript;
            dataPreSta = sysConn.prepareStatement(this.sysUserSelectSQL);
            deleteSta = localConn.prepareStatement(this.sysUserDeleteSQL);
            SysUser newUser;
            // b.user_info_id,b.user_id,b.org_id,b.name,b.sex,b.work_phone,a.account,a.password,b.phone,a.del_flag
            while (countRes.next()) {
                changeId = countRes.getLong("sys_data_change_id");
                opType = countRes.getString("operation_type");
                changeScript = countRes.getString("change_script");
                if (!opType.equals("insert") && !opType.equals("update")) {
                    continue;
                }
                userId = Long.valueOf(changeScript.split("=")[1]);
                dataPreSta.setObject(1, userId);
                dataPreSta.setObject(2, userId);
                userRes = dataPreSta.executeQuery();
                while (userRes.next()) {
                    newUser = new SysUser();
                    newUser.setRmUserInfoId(userRes.getLong("user_info_id"));
                    newUser.setRmUserId(userRes.getLong("user_id"));
                    newUser.setRmDepId(userRes.getLong("org_id"));
                    newUser.setUserName(userRes.getString("name"));
                    newUser.setSex(userRes.getString("sex"));
                    if (!Tools.isEmptyString(newUser.getSex())) {
                        if (newUser.getSex().equals("男")) {
                            newUser.setSex("1");
                        } else if (newUser.getSex().equals("女")) {
                            newUser.setSex("2");
                        } else {
                            newUser.setSex("0");
                        }
                    }
                    newUser.setWorkPhone(userRes.getString("work_phone"));
                    newUser.setAccount(userRes.getString("account"));
                    newUser.setPassword(userRes.getString("password"));
                    newUser.setMobile(userRes.getString("phone"));
                    newUser.setDelFlag(userRes.getString("del_flag"));
                    try {
                        // 根据原系统部门编号查询变更数据是否存在
                        SysUser curUser = this.userService.findByRmUserId(newUser.getRmUserId());
                        if (curUser == null) {
                            curUser = new SysUser();
                            // 新增人员要设置人员的本地部门编号（depId）
                            SysDepartment dept = this.departmentRepository.findByRmDepId(newUser.getRmDepId());
                            if (dept == null) {
                                continue;
                            }
                            newUser.setDepartment(dept);
                        } else {
                            // 保证编号在BeanUtils.copyProperties后不被刷掉
                            newUser.setId(curUser.getId());
                        }
                        // 变更数据存在则获取对象后覆盖再保存
                        NullBeanUtils.copyProperties(curUser, newUser);
                        this.userRepository.save(curUser);
                    } catch (Exception ex) {
                        return;
                    }
                    break;
                }
                deleteSta.setObject(1, changeId);
                deleteSta.addBatch();
                count++;
            }
            deleteSta.executeBatch();
            task.setStatus(SynchDataStatus.同步完成);
            task.setFinishTime(new Date());
            task.setFinishSynch(Long.valueOf(count));
            task.setMemo("完成数据同步！");
            this.updateTaskInfo(task, ptaskstat);
        } catch (Exception ex) {
            ex.printStackTrace();
            task.setStatus(SynchDataStatus.同步完成);
            task.setFinishTime(new Date());
            task.setMemo("数据同步失败：" + ex.getMessage());
            // 备注内容最大是500汉字
            if (task.getMemo().length() > 500) {
                task.setMemo(task.getMemo().substring(0, 500));
            }
            this.updateTaskInfo(task, ptaskstat);
        } finally {
            try {
                if (countRes != null) {
                    countRes.close();
                }

                if (userRes != null) {
                    userRes.close();
                }
                if (ptaskstat != null) {
                    ptaskstat.close();
                }
                if (deleteSta != null) {
                    deleteSta.close();
                }
                if (countPreSta != null) {
                    countPreSta.close();
                }
                if (dataPreSta != null) {
                    dataPreSta.close();
                }
                if (sysConn != null) {
                    sysConn.close();
                }
                if (localConn != null) {
                    localConn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @see cn.com.chaochuang.synchdata.service.SynchDataService#synchSysDataChangeOfDeptData(cn.com.chaochuang.synchdata.domain.SysSynchdataTask)
     */
    @Override
    public void synchSysDataChangeOfDeptData(SysSynchdataTask task) {
        Connection sysConn = null;
        Connection localConn = null;
        PreparedStatement ptaskstat = null;

        PreparedStatement countPreSta = null;
        PreparedStatement dataPreSta = null;
        PreparedStatement deleteSta = null;
        ResultSet deptRes = null;
        ResultSet countRes = null;
        try {
            sysConn = this.sysSynchDataSourceService.getConnection();
            localConn = this.localDataSourceService.getConnection();
            if (sysConn == null) {
                task.setMemo("无法连接目的服务器同步失败！");
                task.setStatus(SynchDataStatus.同步完成);
                this.taskRepository.save(task);
                return;
            }
            ptaskstat = localConn.prepareStatement(this.taskUpdateSQL.replaceAll("@ID", task.getId().toString()));
            localConn.setAutoCommit(true);
            countPreSta = sysConn.prepareStatement(this.dataChangeUserCountSQL);
            countPreSta.setObject(1, DataChangeTable.组织结构.getKey());
            countRes = countPreSta.executeQuery();
            int count = 0;
            Long changeId, deptId;
            String opType, changeScript;
            dataPreSta = sysConn.prepareStatement(this.sysDeptSelectSQL);
            deleteSta = localConn.prepareStatement(this.sysUserDeleteSQL);
            SysDepartment newDept;
            // b.user_info_id,b.user_id,b.org_id,b.name,b.sex,b.work_phone,a.account,a.password,b.phone,a.del_flag
            while (countRes.next()) {
                changeId = countRes.getLong("sys_data_change_id");
                opType = countRes.getString("operation_type");
                changeScript = countRes.getString("change_script");
                if (!opType.equals("insert") && !opType.equals("update")) {
                    continue;
                }
                deptId = Long.valueOf(changeScript.split("=")[1]);
                dataPreSta.setObject(1, deptId);
                deptRes = dataPreSta.executeQuery();
                while (deptRes.next()) {
                    newDept = new SysDepartment();
                    // org_id,name,parent_org_id,unit_org_id,sort,director,org_rank,del_flag
                    newDept.setRmDepId(deptRes.getLong("org_id"));
                    newDept.setDepName(deptRes.getString("name"));
                    newDept.setParentDep(deptRes.getLong("parent_org_id"));
                    newDept.setAncestorDep(deptRes.getLong("unit_org_id"));
                    newDept.setOrderNum(deptRes.getLong("sort"));
                    newDept.setDepAlias(deptRes.getString("name"));
                    newDept.setDepLead(deptRes.getString("director"));
                    newDept.setDepRank(deptRes.getString("org_rank"));
                    newDept.setDelFlag(deptRes.getString("del_flag"));
                    // delFlag不为1时valid都是0
                    if (!Tools.isEmptyString(newDept.getDelFlag()) && !newDept.getDelFlag().equals("1")) {
                        newDept.setValid(0);
                    } else {
                        newDept.setValid(1);
                    }
                    try {
                        // 根据原系统部门编号查询变更数据是否存在
                        SysDepartment curDept = this.deptRepository.findByRmDepId(newDept.getRmDepId());
                        if (curDept == null) {
                            curDept = new SysDepartment();
                        } else {
                            // 保证编号在BeanUtils.copyProperties后不被刷掉
                            newDept.setId(curDept.getId());
                        }
                        // 变更数据存在则获取对象后覆盖再保存
                        NullBeanUtils.copyProperties(curDept, newDept);
                        this.deptRepository.save(curDept);
                    } catch (Exception ex) {
                        return;
                    }
                    break;
                }
                deleteSta.setObject(1, changeId);
                deleteSta.addBatch();
                count++;
            }
            // deleteSta.executeBatch();
            task.setStatus(SynchDataStatus.同步完成);
            task.setFinishTime(new Date());
            task.setFinishSynch(Long.valueOf(count));
            task.setMemo("完成数据同步！");
            this.updateTaskInfo(task, ptaskstat);
        } catch (Exception ex) {
            ex.printStackTrace();
            task.setStatus(SynchDataStatus.同步完成);
            task.setFinishTime(new Date());
            task.setMemo("数据同步失败：" + ex.getMessage());
            // 备注内容最大是500汉字
            if (task.getMemo().length() > 500) {
                task.setMemo(task.getMemo().substring(0, 500));
            }
            this.updateTaskInfo(task, ptaskstat);
        } finally {
            try {
                if (countRes != null) {
                    countRes.close();
                }
                if (deptRes != null) {
                    deptRes.close();
                }
                if (ptaskstat != null) {
                    ptaskstat.close();
                }
                if (deleteSta != null) {
                    deleteSta.close();
                }
                if (countPreSta != null) {
                    countPreSta.close();
                }
                if (dataPreSta != null) {
                    dataPreSta.close();
                }
                if (sysConn != null) {
                    sysConn.close();
                }
                if (localConn != null) {
                    localConn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
