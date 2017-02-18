package dbdata.aipcase.bean;

import dbdata.common.bean.QueryBean;

import java.util.Date;

/**
 * 2017/1/30
 *
 * @author Shicx
 */
public class CaseQueryBean  extends QueryBean{

    /** 当事企业法人代表 */
    private String fillEntpCorpname;
    /** 当事人 */
    private String fillEntpContacter;

    private Date recordTimeFrom;
    private Date recordTimeTo;

    /**
     * @return the fillEntpCorpname
     */
    public String getFillEntpCorpname() {
        return fillEntpCorpname;
    }

    /**
     * @param fillEntpCorpname the fillEntpCorpname to set
     */
    public void setFillEntpCorpname(String fillEntpCorpname) {
        this.fillEntpCorpname = fillEntpCorpname;
    }

    /**
     * @return the fillEntpContacter
     */
    public String getFillEntpContacter() {
        return fillEntpContacter;
    }

    /**
     * @param fillEntpContacter the fillEntpContacter to set
     */
    public void setFillEntpContacter(String fillEntpContacter) {
        this.fillEntpContacter = fillEntpContacter;
    }

    /**
     * @return the recordTimeFrom
     */
    public Date getRecordTimeFrom() {
        return recordTimeFrom;
    }

    /**
     * @param recordTimeFrom the recordTimeFrom to set
     */
    public void setRecordTimeFrom(Date recordTimeFrom) {
        this.recordTimeFrom = recordTimeFrom;
    }

    /**
     * @return the recordTimeTo
     */
    public Date getRecordTimeTo() {
        return recordTimeTo;
    }

    /**
     * @param recordTimeTo the recordTimeTo to set
     */
    public void setRecordTimeTo(Date recordTimeTo) {
        this.recordTimeTo = recordTimeTo;
    }
}
