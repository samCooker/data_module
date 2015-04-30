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
public enum LocalData implements IDictionary {
    非本地数据("0"), 有本地数据("1");

    private String key;
    private String value;

    /**
     * @param key
     */
    private LocalData(String key) {
        this(key, null);
        DictionaryRefresher.getInstance().refreshIDictionary(this);
    }

    /**
     * @param key
     * @param value
     */
    private LocalData(String key, String value) {
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
