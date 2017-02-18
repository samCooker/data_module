/*
 * FileName:    CaseFlag.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年1月7日 (LLM) 1.0 Create
 */

package dbdata.aipcase.reference;

/**
 * The <code>CaseFlag.java</code>
 *
 * @version 1.0, 2015年1月7日
 */
public enum CaseFlag {
    未立案("0"), 已立案("1");

    private String key;
    private String value;

    /**
     * @param key
     */
    private CaseFlag(String key) {
        this(key, null);
    }

    /**
     * @param key
     * @param value
     */
    private CaseFlag(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return (null == value) ? name() : value;
    }

    public Object getObject() {
        return this;
    }

    public String toString() {
        return this.key;
    }
}
