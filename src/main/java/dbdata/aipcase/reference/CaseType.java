/*
 * FileName:    CaseType.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年1月23日 (LLM) 1.0 Create
 */

package dbdata.aipcase.reference;

/**
 * @author LLM
 *
 */
public enum CaseType  {
    药("药"), 食("食"), 保("保"), 妆("妆"), 械("械");

    private String key;
    private String value;

    /**
     * @param key
     */
    private CaseType(String key) {
        this(key, null);
    }

    /**
     * @param key
     * @param value
     */
    private CaseType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     */
    public String getKey() {
        return key;
    }

    /**
     */
    public String getValue() {
        return (null == value) ? name() : value;
    }

    /**
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
