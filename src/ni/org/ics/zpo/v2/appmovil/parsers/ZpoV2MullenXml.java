package ni.org.ics.zpo.v2.appmovil.parsers;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.util.Date;

/**
 * @author ics
 */
public class ZpoV2MullenXml {

    @Element(required=true)
    private String recordId;
    @Element(required=true)
    private String eventName;
    @Element(required=true)
    private String sexMsel;
    @Element(required=true)
    private String raNameMsel;
    @Element(required=true)
    private String visitMonthsMsel;
    @Element(required=true)
    private String visProbMsel;
    @Element(required=true)
    private String desVisProbMsel;
    @Element(required=true)
    private String hearProbMsel;
    @Element(required=true)
    private String desHearProbMsel;
    @Element(required=true)
    private Date testingDateMsel;
    @Element(required=true)
    private Date eddMsel;
    @Element(required=true)
    private String adjAgeMsel;
    @Element(required=true)
    private Date actDobMsel;
    @Element(required=true)
    private String gmRaw;
    @Element(required=true)
    private String gmTScore;
    @Element(required=true)
    private String gmBoe;
    @Element(required=true)
    private String gmPerRank;
    @Element(required=true)
    private String gmDesCat;
    @Element(required=true)
    private String gmAgeEqu;
    @Element(required=true)
    private String vrRaw;
    @Element(required=true)
    private String vrTScore;
    @Element(required=true)
    private String vrBoe;
    @Element(required=true)
    private String vrPerRank;
    @Element(required=true)
    private String vrDesCat;
    @Element(required=true)
    private String vrAgeEqu;
    @Element(required=true)
    private String fmRaw;
    @Element(required=true)
    private String fmTScore;
    @Element(required=true)
    private String fmBoe;
    @Element(required=true)
    private String fmPerRank;
    @Element(required=true)
    private String fmDesCat;
    @Element(required=true)
    private String fmAgeEqu;
    @Element(required=true)
    private String rlRaw;
    @Element(required=true)
    private String rlTScore;
    @Element(required=true)
    private String rlBoe;
    @Element(required=true)
    private String rlPerRank;
    @Element(required=true)
    private String rlDesCat;
    @Element(required=true)
    private String rlAgeEqu;
    @Element(required=true)
    private String elRaw;
    @Element(required=true)
    private String elTScore;
    @Element(required=true)
    private String elBoe;
    @Element(required=true)
    private String elPerRank;
    @Element(required=true)
    private String elDesCat;
    @Element(required=true)
    private String elAgeEqu;
    @Element(required=true)
    private String cognTScoreSum;
    @Element(required=true)
    private String elcStandScore;
    @Element(required=true)
    private String elcBoe;
    @Element(required=true)
    private String elcPerRank;
    @Element(required=true)
    private String elcDesCat;
    @Element(required=true)
    private String mselComment;

    public String getRecordId() {
        return recordId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getSexMsel() {
        return sexMsel;
    }

    public String getRaNameMsel() {
        return raNameMsel;
    }

    public String getVisitMonthsMsel() {
        return visitMonthsMsel;
    }

    public String getVisProbMsel() {
        return visProbMsel;
    }

    public String getDesVisProbMsel() {
        return desVisProbMsel;
    }

    public String getHearProbMsel() {
        return hearProbMsel;
    }

    public String getDesHearProbMsel() {
        return desHearProbMsel;
    }

    public Date getTestingDateMsel() {
        return testingDateMsel;
    }

    public Date getEddMsel() {
        return eddMsel;
    }

    public String getAdjAgeMsel() {
        return adjAgeMsel;
    }

    public Date getActDobMsel() {
        return actDobMsel;
    }

    public String getGmRaw() {
        return gmRaw;
    }

    public String getGmTScore() {
        return gmTScore;
    }

    public String getGmBoe() {
        return gmBoe;
    }

    public String getGmPerRank() {
        return gmPerRank;
    }

    public String getGmDesCat() {
        return gmDesCat;
    }

    public String getGmAgeEqu() {
        return gmAgeEqu;
    }

    public String getVrRaw() {
        return vrRaw;
    }

    public String getVrTScore() {
        return vrTScore;
    }

    public String getVrBoe() {
        return vrBoe;
    }

    public String getVrPerRank() {
        return vrPerRank;
    }

    public String getVrDesCat() {
        return vrDesCat;
    }

    public String getVrAgeEqu() {
        return vrAgeEqu;
    }

    public String getFmRaw() {
        return fmRaw;
    }

    public String getFmTScore() {
        return fmTScore;
    }

    public String getFmBoe() {
        return fmBoe;
    }

    public String getFmPerRank() {
        return fmPerRank;
    }

    public String getFmDesCat() {
        return fmDesCat;
    }

    public String getFmAgeEqu() {
        return fmAgeEqu;
    }

    public String getRlRaw() {
        return rlRaw;
    }

    public String getRlTScore() {
        return rlTScore;
    }

    public String getRlBoe() {
        return rlBoe;
    }

    public String getRlPerRank() {
        return rlPerRank;
    }

    public String getRlDesCat() {
        return rlDesCat;
    }

    public String getRlAgeEqu() {
        return rlAgeEqu;
    }

    public String getElRaw() {
        return elRaw;
    }

    public String getElTScore() {
        return elTScore;
    }

    public String getElBoe() {
        return elBoe;
    }

    public String getElPerRank() {
        return elPerRank;
    }

    public String getElDesCat() {
        return elDesCat;
    }

    public String getElAgeEqu() {
        return elAgeEqu;
    }

    public String getCognTScoreSum() {
        return cognTScoreSum;
    }

    public String getElcStandScore() {
        return elcStandScore;
    }

    public String getElcBoe() {
        return elcBoe;
    }

    public String getElcPerRank() {
        return elcPerRank;
    }

    public String getElcDesCat() {
        return elcDesCat;
    }

    public String getMselComment() {
        return mselComment;
    }

    @Attribute
    private String id;
    @Element(required=false)
    private Meta meta;

    @Element(required=false)
    private String start;
    @Element(required=false)
    private String end;
    @Element(required=false)
    private String deviceid;
    @Element(required=false)
    private String simserial;
    @Element(required=false)
    private String phonenumber;
    @Element(required=false)
    private String imei;
    @Element(required=false)
    private Date today;
    @Attribute(required = false)
    private String version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getSimserial() {
        return simserial;
    }

    public void setSimserial(String simserial) {
        this.simserial = simserial;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
