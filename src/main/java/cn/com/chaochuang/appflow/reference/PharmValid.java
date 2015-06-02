/*
 * FileName:    PharmValid.java
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
public enum PharmValid implements IDictionary {
    有效("1"), 无效("2"), 注销("3"), 执业药师注册审批中("4"), 执业药师注册审批不通过("5");
    private String key;
    private String value;

    /**
     * @param key
     */
    private PharmValid(String key) {
        this(key, null);
        DictionaryRefresher.getInstance().refreshIDictionary(this);
    }

    /**
     * @param key
     * @param value
     */
    private PharmValid(String key, String value) {
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
