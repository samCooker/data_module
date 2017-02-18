/*
 * FileName:    CaseStatus.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年1月7日 (LLM) 1.0 Create
 */

package dbdata.aipcase.reference;

/**
 * @author LLM
 *
 */
public enum CaseStatus   {
    暂存("00"), 案件受理("01"), 现场检查("02"), 整改("03"), 行政处理("04"), 立案("05"), 抽验检查("06"), 调查取证("07"), 案件调查终结("08"), 案件讨论("09"), 告知审批(
                    "10"), 听证或事先告知("11"), 陈述和申辩("12"), 合议处罚("13"), 送达执行("14"), 听证("15"), 结案("16"), 行政复议("17"), 法院受理(
                    "18"), 不予受理("19"), 撤案("20"), 未发现违法行为("21"), 案件移送("22"), 涉嫌犯罪移送("23"), 简易程序处理("24");

    private String key;
    private String value;

    /**
     * @param key
     */
    private CaseStatus(String key) {
        this(key, null);
    }

    /**
     * @param key
     * @param value
     */
    private CaseStatus(String key, String value) {
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
     */
    public String toString() {
        return this.key;
    }

}
