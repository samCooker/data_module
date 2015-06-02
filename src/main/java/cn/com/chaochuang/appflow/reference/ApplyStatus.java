/*
 * FileName:    ApplyStatus.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年6月2日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.appflow.reference;

import cn.com.chaochuang.common.dictionary.IDictionary;
import cn.com.chaochuang.common.dictionary.support.DictionaryRefresher;

/**
 * @author LLM
 *
 */
public enum ApplyStatus implements IDictionary {
    暂存("00"), 网上收件("01"), 受理("02"), 初审("03"), 不予受理("04"), 补正材料("05"), 现场查看("06"), 整改("07"), 审批中("08"), 审批通过("09"), 不予许可办结(
                    "10"), 可发证("11"), 已发证("12"), 注销("13"), 办结归档("14"), 办结注销("15"), 上级单位签批("16"), 指派审查("17"), 审查完成("18"), 审评中心抽验督办(
                    "19"), 抽验督办完成("20"), 预设受理环节作废可恢复申请("21"), 流程审批过程中不可恢复申请("22");

    private String key;
    private String value;

    /**
     * @param key
     */
    private ApplyStatus(String key) {
        this(key, null);
        DictionaryRefresher.getInstance().refreshIDictionary(this);
    }

    /**
     * @param key
     * @param value
     */
    private ApplyStatus(String key, String value) {
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
