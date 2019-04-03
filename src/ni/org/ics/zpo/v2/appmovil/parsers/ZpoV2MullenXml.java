package ni.org.ics.zpo.v2.appmovil.parsers;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.util.Date;

/**
 * @author ics
 */
public class ZpoV2MullenXml {

    @Element(required=false)
    private String sexMsel;
    @Element(required=false)
    private String raNameMsel;
    @Element(required=false)
    private String visitMonthsMsel;
    @Element(required=false)
    private String visProbMsel;
    @Element(required=false)
    private String desVisProbMsel;
    @Element(required=false)
    private String hearProbMsel;
    @Element(required=false)
    private String desHearProbMsel;
    @Element(required=false)
    private Date testingDateMsel;
    @Element(required=false)
    private Date eddMsel;
    @Element(required=false)
    private String adjAgeMsel;
    @Element(required=false)
    private Date actDobMsel;
    @Element(required=false)
    private String gmRaw;
    @Element(required=false)
    private String gmTScore;
    @Element(required=false)
    private String gmBoe;
    @Element(required=false)
    private String gmPerRank;
    @Element(required=false)
    private String gmDesCat;
    @Element(required=false)
    private String gmAgeEqu;
    @Element(required=false)
    private String vrRaw;
    @Element(required=false)
    private String vrTScore;
    @Element(required=false)
    private String vrBoe;
    @Element(required=false)
    private String vrPerRank;
    @Element(required=false)
    private String vrDesCat;
    @Element(required=false)
    private String vrAgeEqu;
    @Element(required=false)
    private String fmRaw;
    @Element(required=false)
    private String fmTScore;
    @Element(required=false)
    private String fmBoe;
    @Element(required=false)
    private String fmPerRank;
    @Element(required=false)
    private String fmDesCat;
    @Element(required=false)
    private String fmAgeEqu;
    @Element(required=false)
    private String rlRaw;
    @Element(required=false)
    private String rlTScore;
    @Element(required=false)
    private String rlBoe;
    @Element(required=false)
    private String rlPerRank;
    @Element(required=false)
    private String rlDesCat;
    @Element(required=false)
    private String rlAgeEqu;
    @Element(required=false)
    private String elRaw;
    @Element(required=false)
    private String elTScore;
    @Element(required=false)
    private String elBoe;
    @Element(required=false)
    private String elPerRank;
    @Element(required=false)
    private String elDesCat;
    @Element(required=false)
    private String elAgeEqu;
    @Element(required=false)
    private String cognTScoreSum;
    @Element(required=false)
    private String elcStandScore;
    @Element(required=false)
    private String elcBoe;
    @Element(required=false)
    private String elcPerRank;
    @Element(required=false)
    private String elcDesCat;
    @Element(required=false)
    private String mselComment;


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
