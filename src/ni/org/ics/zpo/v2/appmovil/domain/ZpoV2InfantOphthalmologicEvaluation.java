package ni.org.ics.zpo.v2.appmovil.domain;

import java.util.Date;

/**
 * Created by FIRSTICT on 1/31/2017.
 * V1.0
 */

public class ZpoV2InfantOphthalmologicEvaluation extends BaseMetaData{

    private static final long serialVersionUID = 1L;
    private String recordId;
    private String eventName;
    //ZS07_Infant_Assessment_Ophtalmologist
    private Date infantVisitDate;
    private String infantStatus;
    private Date infantDeathDt;
    private String infantVisit;
    private String infantOphth;
    private String infantOphthType;
    private String infantOphthAbno;
    private String infantCommentsYn; //nuevo
    private String infantComments; //nuevo

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getInfantVisitDate() {
        return infantVisitDate;
    }

    public void setInfantVisitDate(Date infantVisitDate) {
        this.infantVisitDate = infantVisitDate;
    }

    public String getInfantStatus() {
        return infantStatus;
    }

    public void setInfantStatus(String infantStatus) {
        this.infantStatus = infantStatus;
    }

    public Date getInfantDeathDt() {
        return infantDeathDt;
    }

    public void setInfantDeathDt(Date infantDeathDt) {
        this.infantDeathDt = infantDeathDt;
    }

    public String getInfantVisit() {
        return infantVisit;
    }

    public void setInfantVisit(String infantVisit) {
        this.infantVisit = infantVisit;
    }

    public String getInfantOphth() {
        return infantOphth;
    }

    public void setInfantOphth(String infantOphth) {
        this.infantOphth = infantOphth;
    }

    public String getInfantOphthType() {
        return infantOphthType;
    }

    public void setInfantOphthType(String infantOphthType) {
        this.infantOphthType = infantOphthType;
    }

    public String getInfantOphthAbno() {
        return infantOphthAbno;
    }

    public void setInfantOphthAbno(String infantOphthAbno) {
        this.infantOphthAbno = infantOphthAbno;
    }

    public String getInfantCommentsYn() {
        return infantCommentsYn;
    }

    public void setInfantCommentsYn(String infantCommentsYn) {
        this.infantCommentsYn = infantCommentsYn;
    }

    public String getInfantComments() {
        return infantComments;
    }

    public void setInfantComments(String infantComments) {
        this.infantComments = infantComments;
    }

    @Override
    public String toString() {
        return "ZPOv2OphtEval{" + recordId + ", " + eventName + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ZpoV2InfantOphthalmologicEvaluation)) return false;

        ZpoV2InfantOphthalmologicEvaluation that = (ZpoV2InfantOphthalmologicEvaluation) o;

        if (!recordId.equals(that.recordId)) return false;
        if (!eventName.equals(that.eventName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = recordId.hashCode();
        result = 31 * result + eventName.hashCode();
        return result;
    }
}
