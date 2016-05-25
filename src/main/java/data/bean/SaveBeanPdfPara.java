package data.bean;

/**
 * Created by Cookie on 2016/5/5.
 */
public class SaveBeanPdfPara {

    private Long id;
    /***/
    private Float pageZoom;

    /***/
    private Integer currentPageNo;

    /**
     * @return the currentPageNo
     */
    public Integer getCurrentPageNo() {
        return currentPageNo;
    }

    /**
     * @param currentPageNo the currentPageNo to set
     */
    public void setCurrentPageNo(Integer currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

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
     * @return the pageZoom
     */
    public Float getPageZoom() {
        return pageZoom;
    }

    /**
     * @param pageZoom the pageZoom to set
     */
    public void setPageZoom(Float pageZoom) {
        this.pageZoom = pageZoom;
    }
}
