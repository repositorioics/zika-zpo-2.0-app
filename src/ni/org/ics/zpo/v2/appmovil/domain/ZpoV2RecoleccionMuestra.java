package ni.org.ics.zpo.v2.appmovil.domain;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by Miguel Salinas on 19/03/2019.
 * V1.0
 */
public class ZpoV2RecoleccionMuestra extends BaseMetaData {

    private static final long serialVersionUID = 1L;
    private String recordId;
    private String eventName;
    private Date bloodTodaysDate;
    private String bloodSampleCollected;
    private String bloodWhichPerson;
    private Date bloodMomSampleDate;
    private Integer bloodMomTubes;
    private String bloodMomType;
    private Date bloodChildSampleDate;
    private Integer bloodChildTubes;
    private String bloodChildType;
    private String bloodPersonnel;

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

    public Date getBloodTodaysDate() {
        return bloodTodaysDate;
    }

    public void setBloodTodaysDate(Date bloodTodaysDate) {
        this.bloodTodaysDate = bloodTodaysDate;
    }

    public String getBloodSampleCollected() {
        return bloodSampleCollected;
    }

    public void setBloodSampleCollected(String bloodSampleCollected) {
        this.bloodSampleCollected = bloodSampleCollected;
    }

    public String getBloodWhichPerson() {
        return bloodWhichPerson;
    }

    public void setBloodWhichPerson(String bloodWhichPerson) {
        this.bloodWhichPerson = bloodWhichPerson;
    }

    public Integer getBloodMomTubes() {
        return bloodMomTubes;
    }

    public void setBloodMomTubes(Integer bloodMomTubes) {
        this.bloodMomTubes = bloodMomTubes;
    }

    public String getBloodMomType() {
        return bloodMomType;
    }

    public void setBloodMomType(String bloodMomType) {
        this.bloodMomType = bloodMomType;
    }

    public Integer getBloodChildTubes() {
        return bloodChildTubes;
    }

    public void setBloodChildTubes(Integer bloodChildTubes) {
        this.bloodChildTubes = bloodChildTubes;
    }

    public String getBloodChildType() {
        return bloodChildType;
    }

    public void setBloodChildType(String bloodChildType) {
        this.bloodChildType = bloodChildType;
    }

    public String getBloodPersonnel() {
        return bloodPersonnel;
    }

    public void setBloodPersonnel(String bloodPersonnel) {
        this.bloodPersonnel = bloodPersonnel;
    }

    public Date getBloodMomSampleDate() {
        return bloodMomSampleDate;
    }

    public void setBloodMomSampleDate(Date bloodMomSampleDate) {
        this.bloodMomSampleDate = bloodMomSampleDate;
    }

    public Date getBloodChildSampleDate() {
        return bloodChildSampleDate;
    }

    public void setBloodChildSampleDate(Date bloodChildSampleDate) {
        this.bloodChildSampleDate = bloodChildSampleDate;
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
