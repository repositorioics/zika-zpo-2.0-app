package ni.org.ics.zpo.v2.appmovil.parsers;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.util.Date;

/**
 * Created by FIRSTICT on 2/2/2017.
 * V1.0
 */
public class ZpoV2InfantOphthalmologicEvalXml {

    @Element(required = false)
    private Date infantVisitDate2;
    @Element(required = false)
    private String infantStatus2;
    @Element(required = false)
    private Date infantDeathDt2;
    @Element(required = false)
    private String infantVisit2;
    @Element(required=false)
    private String infantOphth;
    @Element(required=false)
    private String infantOphthType;
    @Element(required=false)
    private String infantOphthAbno;
    @Element(required=false)
    private String infantCommentsYn2;
    @Element(required=false)
    private String infantComments2_2;

    @Element(required=false)
    private String group1;
    @Element(required=false)
    private String group2;
    @Element(required=false)
    private String group3;

    @Element(required=false)
    private String note1;
    @Element(required=false)
    private String note2;

    @Attribute
    private String id;
    @Attribute(required = false)
    private String version;
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

    public Date getInfantVisitDate2() {
        return infantVisitDate2;
    }

    public void setInfantVisitDate2(Date infantVisitDate2) {
        this.infantVisitDate2 = infantVisitDate2;
    }

    public String getInfantStatus2() {
        return infantStatus2;
    }

    public void setInfantStatus2(String infantStatus2) {
        this.infantStatus2 = infantStatus2;
    }

    public Date getInfantDeathDt2() {
        return infantDeathDt2;
    }

    public void setInfantDeathDt2(Date infantDeathDt2) {
        this.infantDeathDt2 = infantDeathDt2;
    }

    public String getInfantVisit2() {
        return infantVisit2;
    }

    public void setInfantVisit2(String infantVisit2) {
        this.infantVisit2 = infantVisit2;
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

    public String getInfantCommentsYn2() {
        return infantCommentsYn2;
    }

    public void setInfantCommentsYn2(String infantCommentsYn2) {
        this.infantCommentsYn2 = infantCommentsYn2;
    }

    public String getInfantComments2_2() {
        return infantComments2_2;
    }

    public void setInfantComments2_2(String infantComments2_2) {
        this.infantComments2_2 = infantComments2_2;
    }

    public String getGroup1() {
        return group1;
    }

    public void setGroup1(String group1) {
        this.group1 = group1;
    }

    public String getGroup2() {
        return group2;
    }

    public void setGroup2(String group2) {
        this.group2 = group2;
    }

    public String getGroup3() {
        return group3;
    }

    public void setGroup3(String group3) {
        this.group3 = group3;
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
