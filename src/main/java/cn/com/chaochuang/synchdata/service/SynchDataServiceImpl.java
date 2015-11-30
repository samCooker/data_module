/*
 * FileName:    SynchDataServiceImpl.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年8月14日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.synchdata.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.appflow.repository.AppLicenceRepository;
import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.commoninfo.repository.AppEntpRepository;
import cn.com.chaochuang.synchdata.domain.SysSynchdataTask;
import cn.com.chaochuang.synchdata.reference.SynchDataStatus;
import cn.com.chaochuang.synchdata.repository.SysSynchdataTaskRepository;

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
    private SysSynchdataTaskRepository taskRepository;
    @Autowired
    private AppEntpRepository          entpRepository;
    @Autowired
    private AppLicenceRepository       licenceRepository;
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
    @Value("${sequencesql}")
    private String                     sequenceSQL;
    @Value("${synchtasksql}")
    private String                     taskUpdateSQL;
    /** 每次处理数据块记录数 */
    @Value("${datablock}")
    private Integer                    dataBlock = 500;

    /**
     * @see cn.com.chaochuang.synchdata.service.SynchDataService#synchAppEntpData(cn.com.chaochuang.synchdata.domain.SysSynchdataTask)
     */
    @Override
    public void synchAppEntpData(SysSynchdataTask task) {
        Connection entpConn = null;
        Connection localConn = null;
        Statement stat = null;
        PreparedStatement pentpstat = null;
        PreparedStatement pbusstat = null;
        PreparedStatement paddrstat = null;
        PreparedStatement pinsertstat = null;
        PreparedStatement pupdatestat = null;
        PreparedStatement pseqstat = null;
        PreparedStatement ptaskstat = null;
        PreparedStatement plicencestat = null;
        PreparedStatement plicenceinsertstat = null;

        ResultSet result = null;
        ResultSet busResult = null;
        ResultSet addrResult = null;
        ResultSet seqResult = null;
        ResultSet licenceResult = null;

        Map<String, Integer> entpInsertSQLItem = this.buildInsertFieldMap(this.entpInsertSQL);
        Map<String, Integer> entpLicenceInsertSQLItem = this.buildInsertFieldMap(this.entpLicenceInsertSQL);
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
            stat = entpConn.createStatement();
            // 获取本次同步任务需要同步的数据量
            result = stat.executeQuery(this.entpCountSQL);
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
            plicencestat = entpConn.prepareStatement(this.entpLicenceSQL);
            // 插入和更新SQL
            pinsertstat = localConn.prepareStatement(this.entpInsertSQL);
            pupdatestat = localConn.prepareStatement(this.entpUpdateSQL);
            plicenceinsertstat = localConn.prepareStatement(this.entpLicenceInsertSQL);
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

                        plicencestat.setObject(1, curId);
                        licenceResult = plicencestat.executeQuery();
                        while (licenceResult.next()) {
                            seqResult = pseqstat.executeQuery();
                            seqResult.next();
                            if (this.licenceRepository.findByRmLicenceId(Long.valueOf(licenceResult.getObject(
                                            "rm_licence_id").toString())) != null) {
                                continue;
                            }
                            try {
                                plicenceinsertstat.setObject(entpLicenceInsertSQLItem.get("licence_id"),
                                                seqResult.getLong(1));
                                plicenceinsertstat.setObject(entpLicenceInsertSQLItem.get("rm_licence_id"),
                                                licenceResult.getObject("rm_licence_id"));
                                plicenceinsertstat.setObject(entpLicenceInsertSQLItem.get("rm_entp_id"),
                                                licenceResult.getObject("rm_entp_id"));
                                plicenceinsertstat.setObject(entpLicenceInsertSQLItem.get("entp_type_name"),
                                                licenceResult.getObject("entp_type_name"));
                                plicenceinsertstat.setObject(entpLicenceInsertSQLItem.get("licence_no"),
                                                licenceResult.getObject("licence_no"));
                                plicenceinsertstat.setObject(entpLicenceInsertSQLItem.get("from_date"),
                                                this.changeDataType(licenceResult.getObject("from_date"), true));
                                plicenceinsertstat.setObject(entpLicenceInsertSQLItem.get("to_date"),
                                                this.changeDataType(licenceResult.getObject("to_date"), true));
                                plicenceinsertstat.setObject(entpLicenceInsertSQLItem.get("licence_time"),
                                                this.changeDataType(licenceResult.getObject("licence_time"), true));
                                plicenceinsertstat.addBatch();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                continue;
                            }
                        }
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
                plicenceinsertstat.executeBatch();
                System.out.println("insert licence data after: " + Tools.DATE_TIME_FORMAT.format(new Date()));
                plicenceinsertstat.clearBatch();
                plicenceinsertstat.close();
                pinsertstat = localConn.prepareStatement(this.entpInsertSQL);
                pupdatestat = localConn.prepareStatement(this.entpUpdateSQL);
                plicenceinsertstat = localConn.prepareStatement(this.entpLicenceInsertSQL);

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
                if (busResult != null) {
                    busResult.close();
                }
                if (addrResult != null) {
                    addrResult.close();
                }
                if (licenceResult != null) {
                    licenceResult.close();
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
                if (plicencestat != null) {
                    plicencestat.close();
                }
                if (plicenceinsertstat != null) {
                    plicenceinsertstat.close();
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
                    return Tools.DATE_TIME_FORMAT.format(new Timestamp(((Date) source).getTime()));
                } catch (Exception ex) {
                    return source;
                }
            }
            return new Timestamp(((Date) source).getTime());
        }
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
     * @see cn.com.chaochuang.synchdata.service.SynchDataService#synchLicenceData(cn.com.chaochuang.synchdata.domain.SysSynchdataTask)
     */
    @Override
    public void synchLicenceData(SysSynchdataTask task) {
        // TODO Auto-generated method stub
    }
}
