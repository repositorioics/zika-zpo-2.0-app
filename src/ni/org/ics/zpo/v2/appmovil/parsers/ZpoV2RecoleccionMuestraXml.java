package ni.org.ics.zpo.v2.appmovil.parsers;

import org.joda.time.DateTime;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.util.Date;

/**
 * Created by FIRSTICT on 10/10/2016.
 * V1.0
 */
public class ZpoV2RecoleccionMuestraXml {

    @Element(required=false)
    private Date bloodTodaysDate;
    @Element(required=false)
    private String bloodSampleCollected;
    @Element(required=false)
    private String bloodWhichPerson;
    @Element(required=false)
    private String bloodMomSampleDate;
    @Element(required=false)
    private Integer bloodMomTubes;
    @Element(required=false)
    private String bloodMomType;
    @Element(required=false)
    private String bloodChildSampleDate;
    @Element(required=false)
    private Integer bloodChildTubes;
    @Element(required=false)
    private String bloodChildType;
    @Element(required=false)
    private String bloodPersonnel;
    @Element(required=false)
    private String date1;
    @Element(required=false)
    private String time1;
    @Element(required=false)
    private String date2;
    @Element(required=false)
    private String time2;



    @Element(required=false)
    private String note1;
    @Element(required=false)
    private String group1;
    @Element(required=false)
    private String group2;

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

    public Date getBloodTodaysDate() {
        return bloodTodaysDate;
    }

    public String getBloodSampleCollected() {
        return bloodSampleCollected;
    }

    public String getBloodWhichPerson() {
        return bloodWhichPerson;
    }

    public Integer getBloodMomTubes() {
        return bloodMomTubes;
    }

    public String getBloodMomType() {
        return bloodMomType;
    }

    public Integer getBloodChildTubes() {
        return bloodChildTubes;
    }

    public String getBloodChildType() {
        return bloodChildType;
    }

    public String getBloodPersonnel() {
        return bloodPersonnel;
    }

    public String getBloodMomSampleDate() {
        return bloodMomSampleDate;
    }

    public String getBloodChildSampleDate() {
        return bloodChildSampleDate;
    }

    public String getNote1() {
        return note1;
    }

    public void setNote1(String note1) {
        this.note1 = note1;
    }

    public String getDate2() {
        return date2;
    }

    public String getTime2() {
        return time2;
    }

    public String getGroup1() {
        return group1;
    }

    public String getGroup2() {
        return group2;
    }

    public String getDate1() {
        return date1;
    }

    public String getTime1() {
        return time1;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
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



}
