package cn.com.chaochuang.data.bean;

/**
 * Created by Cookie on 2016/5/5.
 */
public class SaveBeanPdfPara {

    private Long id;
    /***/
    private Long pageZoom;
    /***/
    private Long currentPageNo;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the currentPageNo
     */
    public Long getCurrentPageNo() {
        return currentPageNo;
    }

    /**
     * @param currentPageNo the currentPageNo to set
     */
    public void setCurrentPageNo(Long currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    /**
     * @return the pageZoom
     */
    public Long getPageZoom() {
        return pageZoom;
    }

    /**
     * @param pageZoom the pageZoom to set
     */
    public void setPageZoom(Long pageZoom) {
        this.pageZoom = pageZoom;
    }
}
