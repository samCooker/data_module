package dbdata.aipcase.reference;

/**
 * 2017-2-16
 *
 * @author Shicx
 */
public enum NodeType {

    呈送("send"), 退回("back");

    private String key;
    private String value;

    /**
     * @param key
     */
    private NodeType(String key) {
        this(key, null);
    }

    /**
     * @param key
     * @param value
     */
    private NodeType(String key, String value) {
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
