package ni.org.ics.zpo.v2.appmovil.domain;

import java.util.Date;

/**
 * Created by Miguel Salinas on 19/03/2019.
 * V1.0
 */
public class ZpoV2RecoleccionMuestra extends BaseMetaData {

    private static final long serialVersionUID = 1L;
    private String recordId;
    private String eventName;
    private Date bscDov;
    private String bscVisit;
    private String bscMatBldCol1;
    private String bscMatBldId1;
    private String bscMatBldRsn1;
    private String bscMatBldRsnOther1;
    private Double bscMatBldVol1;
    private String bscMatBldCol2;
    private String bscMatBldId2;
    private String bscMatBldRsn2;
    private String bscMatBldRsnOther2;
    private Double bscMatBldVol2;
    private String bscPhlebotomist;

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

    public Date getBscDov() {
        return bscDov;
    }

    public void setBscDov(Date bscDov) {
        this.bscDov = bscDov;
    }

    public String getBscVisit() {
        return bscVisit;
    }

    public void setBscVisit(String bscVisit) {
        this.bscVisit = bscVisit;
    }

    public String getBscMatBldCol1() {
        return bscMatBldCol1;
    }

    public void setBscMatBldCol1(String bscMatBldCol1) {
        this.bscMatBldCol1 = bscMatBldCol1;
    }

    public String getBscMatBldId1() {
        return bscMatBldId1;
    }

    public void setBscMatBldId1(String bscMatBldId1) {
        this.bscMatBldId1 = bscMatBldId1;
    }

    public String getBscMatBldRsn1() {
        return bscMatBldRsn1;
    }

    public void setBscMatBldRsn1(String bscMatBldRsn1) {
        this.bscMatBldRsn1 = bscMatBldRsn1;
    }

    public String getBscMatBldRsnOther1() {
        return bscMatBldRsnOther1;
    }

    public void setBscMatBldRsnOther1(String bscMatBldRsnOther1) {
        this.bscMatBldRsnOther1 = bscMatBldRsnOther1;
    }

    public Double getBscMatBldVol1() {
        return bscMatBldVol1;
    }

    public void setBscMatBldVol1(Double bscMatBldVol1) {
        this.bscMatBldVol1 = bscMatBldVol1;
    }

    public String getBscMatBldCol2() {
        return bscMatBldCol2;
    }

    public void setBscMatBldCol2(String bscMatBldCol2) {
        this.bscMatBldCol2 = bscMatBldCol2;
    }

    public String getBscMatBldId2() {
        return bscMatBldId2;
    }

    public void setBscMatBldId2(String bscMatBldId2) {
        this.bscMatBldId2 = bscMatBldId2;
    }

    public String getBscMatBldRsn2() {
        return bscMatBldRsn2;
    }

    public void setBscMatBldRsn2(String bscMatBldRsn2) {
        this.bscMatBldRsn2 = bscMatBldRsn2;
    }

    public String getBscMatBldRsnOther2() {
        return bscMatBldRsnOther2;
    }

    public void setBscMatBldRsnOther2(String bscMatBldRsnOther2) {
        this.bscMatBldRsnOther2 = bscMatBldRsnOther2;
    }

    public Double getBscMatBldVol2() {
        return bscMatBldVol2;
    }

    public void setBscMatBldVol2(Double bscMatBldVol2) {
        this.bscMatBldVol2 = bscMatBldVol2;
    }

    public String getBscPhlebotomist() {
        return bscPhlebotomist;
    }

    public void setBscPhlebotomist(String bscPhlebotomist) {
        this.bscPhlebotomist = bscPhlebotomist;
    }

    @Override
    public String toString() {
        return "ZpoV2RecoleccionMuestra{" + recordId + "," + eventName + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ZpoV2RecoleccionMuestra)) return false;

        ZpoV2RecoleccionMuestra that = (ZpoV2RecoleccionMuestra) o;

        if (!eventName.equals(that.eventName)) return false;
        if (!recordId.equals(that.recordId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = recordId.hashCode();
        result = 31 * result + eventName.hashCode();
        return result;
    }
}
