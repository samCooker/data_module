/*
 * FileName:    ComboxShowBean.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年1月25日 (LaoZhiYong) 1.0 Create
 */

package cn.com.chaochuang.common.bean;

/**
 * @author LaoZhiYong
 *
 */
public class ComboxShowBean {
    private int    id;
    private String text;

    public ComboxShowBean() {

    }

    public ComboxShowBean(int id, String text) {
        this.id = id;
        this.text = text;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text
     *            the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

}
