package dbdata.aipcase.bean;

import org.dozer.Mapping;
import utils.Tools;

import java.util.Date;

/**
 * 2017/1/30
 *
 * @author Shicx
 */
public class CaseQueryData {

    private Long id;
    @Mapping("aipCaseType.caseTypeName")
    private String caseTypeName;
    /** 当事企业法人代表 */
    private String fillEntpCorpname;
    /** 当事人 */
    private String fillEntpContacter;
    private Date recordTime;
    private String recordTimeFmt;

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
     * @return the caseTypeName
     */
    public String getCaseTypeName() {
        return caseTypeName;
    }

    /**
     * @param caseTypeName the caseTypeName to set
     */
    public void setCaseTypeName(String caseTypeName) {
        this.caseTypeName = caseTypeName;
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
     * @return the recordTime
     */
    public Date getRecordTime() {
        return recordTime;
    }

    /**
     * @param recordTime the recordTime to set
     */
    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    /**
     * @return the recordTimeFmt
     */
    public String getRecordTimeFmt() {
        if(recordTime != null){
            return Tools.DATE_MINUTE_FORMAT.format(recordTime);
        }
        return null;
    }

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
}
