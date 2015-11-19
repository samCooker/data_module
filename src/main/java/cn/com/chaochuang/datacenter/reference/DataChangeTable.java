/*
 * FileName:    DataChangeTable.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年4月8日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.datacenter.reference;

import cn.com.chaochuang.common.dictionary.IDictionary;
import cn.com.chaochuang.common.dictionary.support.DictionaryRefresher;

/**
 * @author LLM
 *
 */
public enum DataChangeTable implements IDictionary {
    公文待办("oa_pending_handle_dts"), 审批待办("supervise_pending_handle"), 办案待办("aipcase_pending_handle"), 公文办结(
                    "oa_wf_flo_hisno"), 组织结构("sys_department"), 人员("sys_user"), 通讯录("oa_address_info"), 新闻公告(
                    "oa_pub_board"), 企业信息("gxfda_app_app_entp"), 行政处罚信息("aip_punish_entp"), 投诉举报待办(
                    "case_complaint_pending_handle"), 舆情信息("voice_info"), 舆情事件("voice_event"), 舆情事件内容(
                    "voice_event_content"), 舆情事件办理("voice_event_handle_man"), 舆情事件办理意见("voice_event_handle_approve"), 许可证信息(
                    "app_item_licence"), 日常检查("examine_entp_object");

    private String key;
    private String value;

    /**
     * @param key
     */
    private DataChangeTable(String key) {
        this(key, null);
        DictionaryRefresher.getInstance().refreshIDictionary(this);
    }

    /**
     * @param key
     * @param value
     */
    private DataChangeTable(String key, String value) {
        this.key = key;
        this.value = value;
        DictionaryRefresher.getInstance().refreshIDictionary(this);
    }

    /**
     * @see cn.com.chaochuang.common.dictionary.IDictionary#getKey()
     */
    public String getKey() {
        return key;
    }

    /**
     * @see cn.com.chaochuang.common.dictionary.IDictionary#getValue()
     */
    public String getValue() {
        return (null == value) ? name() : value;
    }

    /**
     * @see cn.com.chaochuang.common.dictionary.IDictionary#getObject()
     */
    public Object getObject() {
        return this;
    }

    /**
     * @see java.lang.Enum#toString()
     */
    public String toString() {
        return this.key;
    }
}
