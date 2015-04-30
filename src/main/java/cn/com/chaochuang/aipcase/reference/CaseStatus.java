/*
 * FileName:    CaseStatus.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年1月7日 (LLM) 1.0 Create
 */

package cn.com.chaochuang.aipcase.reference;

import cn.com.chaochuang.common.dictionary.IDictionary;
import cn.com.chaochuang.common.dictionary.support.DictionaryRefresher;

/**
 * @author LLM
 *
 */
public enum CaseStatus implements IDictionary {
    暂存("00"), 案件受理("01"), 现场检查("02"), 整改("03"), 行政处理("04"), 立案("05"), 抽验检查("06"), 调查取证("07"), 案件调查终结("08"), 案件讨论("09"), 告知审批(
                    "10"), 听证或事先告知("11"), 陈述和申辩("12"), 合议处罚("13"), 送达执行("14"), 听证("15"), 结案("16"), 行政复议("17"), 法院受理(
                    "18");

    private String key;
    private String value;

    /**
     * @param key
     */
    private CaseStatus(String key) {
        this(key, null);
        DictionaryRefresher.getInstance().refreshIDictionary(this);
    }

    /**
     * @param key
     * @param value
     */
    private CaseStatus(String key, String value) {
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
