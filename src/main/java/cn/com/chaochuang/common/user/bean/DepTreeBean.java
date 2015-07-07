/*
 * FileName:    depTreeBean.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年7月5日 (LJX) 1.0 Create
 */

package cn.com.chaochuang.common.user.bean;

/**
 * @author LJX
 *
 */
public class DepTreeBean {
    private String  text;
    private Long    id;
    /** 是否是最后的叶子节点 */
    private boolean lastLeaf = false;
    /** 是否有叶子节点 */
    private boolean hasChild = false;
    /** 级别 */
    private Integer level    = Integer.valueOf(1);
    /**  */
    private boolean leaf;

    /**
     * 构造函数
     */
    public DepTreeBean() {
        super();
    }

    /**
     * 构造函数
     *
     * @param id
     * @param text
     */
    public DepTreeBean(Long id, String text, boolean leaf) {
        super();
        this.text = text;
        this.id = id;
        this.leaf = leaf;
    }

    /**
     * 构造函数
     *
     * @param id
     * @param text
     * @param level
     */
    public DepTreeBean(Long id, String text, boolean leaf, Integer level) {
        super();
        this.id = id;
        this.text = text;
        this.level = level;
        this.leaf = leaf;
    }

    /**
     * @return the leaf
     */
    public boolean isLeaf() {
        return leaf;
    }

    /**
     * @param leaf
     *            the leaf to set
     */
    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    /**
     * @return the state
     */
    public String getState() {
        if (leaf) {
            return "open";
        }
        return "closed";
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

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the lastLeaf
     */
    public boolean isLastLeaf() {
        return lastLeaf;
    }

    /**
     * @param lastLeaf
     *            the lastLeaf to set
     */
    public void setLastLeaf(boolean lastLeaf) {
        this.lastLeaf = lastLeaf;
    }

    /**
     * @return the hasChild
     */
    public boolean isHasChild() {
        return hasChild;
    }

    /**
     * @param hasChild
     *            the hasChild to set
     */
    public void setHasChild(boolean hasChild) {
        this.hasChild = hasChild;
    }

    /**
     * @return the level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * @param level
     *            the level to set
     */
    public void setLevel(Integer level) {
        this.level = level;
    }
}
