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
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.com.chaochuang.common.util.Tools;
import cn.com.chaochuang.commoninfo.repository.AppEntpRepository;
import cn.com.chaochuang.synchdata.domain.SysSynchdataTask;
import cn.com.chaochuang.synchdata.reference.SynchDataStatus;
import cn.com.chaochuang.synchdata.repository.SysSynchdataTaskRepository;
import cn.com.chaochuang.voice.repository.VoiceEventRepository;

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
    private SynchDataSource            voiceSynchDataSourceService;
    @Autowired
    private SynchDataSource            localDataSourceService;
    @Autowired
    private SysSynchdataTaskRepository taskRepository;
    @Autowired
    private AppEntpRepository          entpRepository;
    @Autowired
    private VoiceEventRepository       voiceEventRepository;
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
    @Value("${voice.event.countsql}")
    private String                     eventCountSQL;
    @Value("${voice.event.datasql}")
    private String                     eventDataSQL;
    @Value("${voice.event.insertsql}")
    private String                     eventInsertSQL;
    @Value("${sequencesql}")
    private String                     sequenceSQL;
    @Value("${synchtasksql}")
    private String                     taskUpdateSQL;
    /** 每次处理数据块记录数 */
    @Value("${datablock}")
    private Integer                    dataBlock = 2000;

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
        ResultSet result = null;
        ResultSet busResult = null;
        ResultSet addrResult = null;
        ResultSet seqResult = null;
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
            // 插入和更新SQL
            pinsertstat = localConn.prepareStatement(this.entpInsertSQL);
            pupdatestat = localConn.prepareStatement(this.entpUpdateSQL);
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
            Map<String, Object> dataMap = new HashMap();
            int idx = 0;
            // entp_id, entp_name, entp_province, contact, contact_duty, cell_phone, contact_address,
            // contact_postal_code, tel, register_address, register_fund, business_license, business_license_date,
            // handle_unit_name, city_name, fax, email
            // 另外处理字段：
            // rm_entp_id, licence_date_script, bus_name, longitude, latitude, input_date
            while (curId < maxId) {
                pentpstat.setObject(1, minId);
                pentpstat.setObject(2, minId + (this.dataBlock - 1));
                result = pentpstat.executeQuery();
                while (result.next()) {
                    idx++;

                    curId = result.getLong("entp_id");
                    dataMap.put("rm_entp_id", curId);
                    dataMap.put("entp_name", result.getObject("entp_name"));
                    dataMap.put("entp_province", result.getObject("entp_province"));
                    dataMap.put("contact", result.getObject("contact"));
                    dataMap.put("contact_duty", result.getObject("contact_duty"));
                    dataMap.put("cell_phone", result.getObject("cell_phone"));
                    dataMap.put("contact_address", result.getObject("contact_address"));
                    dataMap.put("contact_postal_code", result.getObject("contact_postal_code"));
                    dataMap.put("tel", result.getObject("tel"));
                    dataMap.put("register_address", result.getObject("register_address"));
                    dataMap.put("register_fund", result.getObject("register_fund"));
                    dataMap.put("business_license", result.getObject("business_license"));
                    dataMap.put("business_license_date", result.getObject("business_license_date"));
                    dataMap.put("handle_unit_name", result.getObject("handle_unit_name"));
                    dataMap.put("city_name", result.getObject("city_name"));
                    pbusstat.setObject(1, curId);
                    busResult = pbusstat.executeQuery();
                    busName = new StringBuilder("");
                    while (busResult.next()) {
                        if (!Tools.isEmptyString(busResult.getString("bus_name"))) {
                            busName.append(busResult.getString("entp_type_name")).append("：")
                                            .append(busResult.getString("bus_name")).append("<br>");
                        }
                    }
                    dataMap.put("bus_name", busName.toString());
                    paddrstat.setObject(1, curId);
                    addrResult = paddrstat.executeQuery();
                    while (addrResult.next()) {
                        dataMap.put("longitude", addrResult.getObject("longitude"));
                        dataMap.put("latitude", addrResult.getObject("latitude"));
                        break;
                    }
                    dataMap.put("input_date", new Date());
                    // 查询当前编号的企业数据是否已经存在
                    if (this.entpRepository.findByRmEntpId(curId) == null) {
                        seqResult = pseqstat.executeQuery();
                        seqResult.next();
                        System.out.println("new entp_id=" + seqResult.getLong(1));
                        dataMap.put("entp_id", seqResult.getLong(1));
                        // insert操作
                        this.setPrepareStatementData(pinsertstat, entpInsertSQL, dataMap, true);
                    } else {
                        // update操作
                        this.setPrepareStatementData(pupdatestat, entpUpdateSQL, dataMap, false);
                    }
                }
                pinsertstat.executeBatch();
                pupdatestat.executeBatch();

                pinsertstat.clearBatch();
                pupdatestat.clearBatch();

                task.setFinishSynch(Long.valueOf(idx));
                this.updateTaskInfo(task, ptaskstat);
                minId += this.dataBlock;
            }
            // localConn.commit();
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
     * @see cn.com.chaochuang.synchdata.service.SynchDataService#synchVoiceEventData(cn.com.chaochuang.synchdata.domain.SysSynchdataTask)
     */
    @Override
    public void synchVoiceEventData(SysSynchdataTask task) {
        Connection eventConn = null;
        Connection localConn = null;
        Statement stat = null;
        PreparedStatement peventstat = null;
        PreparedStatement pinsertstat = null;
        PreparedStatement pseqstat = null;
        PreparedStatement ptaskstat = null;
        ResultSet result = null;
        ResultSet seqResult = null;
        // 获取相对人库的企业数据
        try {
            eventConn = this.voiceSynchDataSourceService.getConnection();
            localConn = this.localDataSourceService.getConnection();
            if (eventConn == null) {
                task.setMemo("无法连接目的服务器同步失败！");
                task.setStatus(SynchDataStatus.同步完成);
                this.taskRepository.save(task);
                return;
            }
            stat = eventConn.createStatement();
            // 获取本次同步任务需要同步的数据量
            result = stat.executeQuery(this.eventCountSQL);
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
            peventstat = eventConn.prepareStatement(this.eventDataSQL);
            // 插入和更新SQL
            pinsertstat = localConn.prepareStatement(this.eventInsertSQL);
            pseqstat = localConn.prepareStatement(this.sequenceSQL);
            ptaskstat = localConn.prepareStatement(this.taskUpdateSQL.replaceAll("@ID", task.getId().toString()));

            peventstat.setMaxRows(this.dataBlock);
            peventstat.setFetchSize(this.dataBlock);
            // 重置任务的信息
            task.setNeedSynch(Long.valueOf(count));
            task.setStatus(SynchDataStatus.同步中);
            task.setBeginTime(new Date());
            this.updateTaskInfo(task, ptaskstat);
            Map<String, Object> dataMap = new HashMap();
            int idx = 0;
            // event_id, grade, title, create_time, creater_id, status, creater_name
            // 另外处理字段：
            // rm_event_id, local_new_data
            while (curId < maxId) {
                peventstat.setObject(1, minId);
                peventstat.setObject(2, minId + (this.dataBlock - 1));
                result = peventstat.executeQuery();
                while (result.next()) {
                    idx++;

                    curId = result.getLong("event_id");
                    dataMap.put("rm_event_id", curId);
                    dataMap.put("grade", result.getObject("grade_id"));
                    dataMap.put("title", result.getObject("title"));
                    dataMap.put("create_time", result.getObject("create_time"));
                    dataMap.put("create_id", result.getObject("create_id"));
                    dataMap.put("status", result.getObject("status"));
                    dataMap.put("creater_name", result.getObject("creater_name"));
                    // 查询当前编号的企业数据是否已经存在
                    if (this.entpRepository.findByRmEntpId(curId) == null) {
                        seqResult = pseqstat.executeQuery();
                        seqResult.next();
                        dataMap.put("event_id", seqResult.getLong(1));
                        // insert操作
                        this.setPrepareStatementData(pinsertstat, entpInsertSQL, dataMap, true);
                    }
                }
                pinsertstat.executeBatch();
                pinsertstat.clearBatch();

                task.setFinishSynch(Long.valueOf(idx));
                this.updateTaskInfo(task, ptaskstat);
                minId += this.dataBlock;
            }
            // localConn.commit();
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
                if (stat != null) {
                    stat.close();
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
     * 设置Statement参数
     *
     * @param stat
     * @param sql
     * @param dataMap
     * @param insert
     */
    private void setPrepareStatementData(PreparedStatement stat, String sql, Map dataMap, boolean insert) {
        Map<String, Integer> fields = new HashMap();
        if (insert) {
            fields = this.buildInsertFieldMap(sql);
        } else {
            fields = this.buildUpdateFieldMap(sql);
        }
        try {
            for (Iterator it = dataMap.entrySet().iterator(); it.hasNext();) {
                Entry entry = (Entry) it.next();
                if (fields.containsKey(entry.getKey())) {
                    stat.setObject(fields.get(entry.getKey()), this.changeDataType(entry.getValue()));
                    fields.remove(entry.getKey());
                }
            }
            for (Iterator it = fields.entrySet().iterator(); it.hasNext();) {
                Entry entry = (Entry) it.next();
                stat.setObject(((Integer) entry.getValue()).intValue(), null);
            }
            stat.addBatch();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /**
     * 数据转换
     *
     * @param source
     * @return
     */
    private Object changeDataType(Object source) {
        if (source == null) {
            return null;
        }
        if (source.getClass().getName().indexOf("java.util.Date") >= 0) {
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
            stat.setObject(fields.get("need_synch"), this.changeDataType(task.getNeedSynch()));
            stat.setObject(fields.get("finish_synch"), this.changeDataType(task.getFinishSynch()));
            stat.setObject(fields.get("begin_time"), this.changeDataType(task.getBeginTime()));
            stat.setObject(fields.get("finish_time"), this.changeDataType(task.getFinishTime()));
            stat.setObject(fields.get("status"), this.changeDataType(task.getStatus().getKey()));
            stat.setObject(fields.get("memo"), this.changeDataType(task.getMemo()));
            stat.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}
