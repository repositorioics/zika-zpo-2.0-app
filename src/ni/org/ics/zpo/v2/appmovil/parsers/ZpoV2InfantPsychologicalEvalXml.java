package ni.org.ics.zpo.v2.appmovil.parsers;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.util.Date;

/**
 * Created by FIRSTICT on 2/2/2017.
 * V1.0
 */
public class ZpoV2InfantPsychologicalEvalXml {

    @Element(required = false)
    private Date infantVisitDate3;
    @Element(required = false)
    private String infantStatus3;
    @Element(required = false)
    private Date infantDeathDt3;
    @Element(required = false)
    private String infantVisit3;
    @Element(required=false)
    private String infantEvaluation;
    @Element(required=false)
    private String infantNeuroAsq;
    @Element(required=false)
    private Float infantAsqCommuni;
    @Element(required=false)
    private Float infantAsqGross;
    @Element(required=false)
    private Float infantAsqFine;
    @Element(required=false)
    private Float infantAsqProblem;
    @Element(required=false)
    private Float infantAsqPersonal;
    @Element(required=false)
    private String infantNeuroBisd;
    @Element(required=false)
    private Float infantCgScore;
    @Element(required=false)
    private String infantCgRisk;
    @Element(required=false)
    private Float infantRpScore;
    @Element(required=false)
    private String infantRpRisk;
    @Element(required=false)
    private Float infantEpScore;
    @Element(required=false)
    private String infantEpRisk;
    @Element(required=false)
    private Float infantFmScore;
    @Element(required=false)
    private String infantFmRisk;
    @Element(required=false)
    private Float infantGmScore;
    @Element(required=false)
    private String infantGmRisk;
    @Element(required=false)
    private String infantNeuroOther;
    @Element(required=false)
    private String infantOtherName;
    @Element(required=false)
    private Float infantOtherScore;
    @Element(required=false)
    private String infantResultScreening;
    @Element(required=false)
    private String infantReferTesting;
    @Element(required=false)
    private String infantCommentsYn3;
    @Element(required=false)
    private String infantComments2_3;


    @Element(required=false)
    private String group1;
    @Element(required=false)
    private String group7;
    @Element(required=false)
    private String group8;
    @Element(required=false)
    private String group22;


    @Element(required=false)
    private String note1;
    @Element(required=false)
    private String note5;

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

    public Date getInfantVisitDate3() {
        return infantVisitDate3;
    }

    public void setInfantVisitDate3(Date infantVisitDate3) {
        this.infantVisitDate3 = infantVisitDate3;
    }

    public String getInfantStatus3() {
        return infantStatus3;
    }

    public void setInfantStatus3(String infantStatus3) {
        this.infantStatus3 = infantStatus3;
    }

    public Date getInfantDeathDt3() {
        return infantDeathDt3;
    }

    public void setInfantDeathDt3(Date infantDeathDt3) {
        this.infantDeathDt3 = infantDeathDt3;
    }

    public String getInfantVisit3() {
        return infantVisit3;
    }

    public void setInfantVisit3(String infantVisit3) {
        this.infantVisit3 = infantVisit3;
    }

    public String getInfantEvaluation() {
        return infantEvaluation;
    }

    public void setInfantEvaluation(String infantEvaluation) {
        this.infantEvaluation = infantEvaluation;
    }

    public String getInfantNeuroAsq() {
        return infantNeuroAsq;
    }

    public void setInfantNeuroAsq(String infantNeuroAsq) {
        this.infantNeuroAsq = infantNeuroAsq;
    }

    public Float getInfantAsqCommuni() {
        return infantAsqCommuni;
    }

    public void setInfantAsqCommuni(Float infantAsqCommuni) {
        this.infantAsqCommuni = infantAsqCommuni;
    }

    public Float getInfantAsqGross() {
        return infantAsqGross;
    }

    public void setInfantAsqGross(Float infantAsqGross) {
        this.infantAsqGross = infantAsqGross;
    }

    public Float getInfantAsqFine() {
        return infantAsqFine;
    }

    public void setInfantAsqFine(Float infantAsqFine) {
        this.infantAsqFine = infantAsqFine;
    }

    public Float getInfantAsqProblem() {
        return infantAsqProblem;
    }

    public void setInfantAsqProblem(Float infantAsqProblem) {
        this.infantAsqProblem = infantAsqProblem;
    }

    public Float getInfantAsqPersonal() {
        return infantAsqPersonal;
    }

    public void setInfantAsqPersonal(Float infantAsqPersonal) {
        this.infantAsqPersonal = infantAsqPersonal;
    }

    public String getInfantNeuroBisd() {
        return infantNeuroBisd;
    }

    public void setInfantNeuroBisd(String infantNeuroBisd) {
        this.infantNeuroBisd = infantNeuroBisd;
    }

    public Float getInfantCgScore() {
        return infantCgScore;
    }

    public void setInfantCgScore(Float infantCgScore) {
        this.infantCgScore = infantCgScore;
    }

    public String getInfantCgRisk() {
        return infantCgRisk;
    }

    public void setInfantCgRisk(String infantCgRisk) {
        this.infantCgRisk = infantCgRisk;
    }

    public Float getInfantRpScore() {
        return infantRpScore;
    }

    public void setInfantRpScore(Float infantRpScore) {
        this.infantRpScore = infantRpScore;
    }

    public String getInfantRpRisk() {
        return infantRpRisk;
    }

    public void setInfantRpRisk(String infantRpRisk) {
        this.infantRpRisk = infantRpRisk;
    }

    public Float getInfantEpScore() {
        return infantEpScore;
    }

    public void setInfantEpScore(Float infantEpScore) {
        this.infantEpScore = infantEpScore;
    }

    public String getInfantEpRisk() {
        return infantEpRisk;
    }

    public void setInfantEpRisk(String infantEpRisk) {
        this.infantEpRisk = infantEpRisk;
    }

    public Float getInfantFmScore() {
        return infantFmScore;
    }

    public void setInfantFmScore(Float infantFmScore) {
        this.infantFmScore = infantFmScore;
    }

    public String getInfantFmRisk() {
        return infantFmRisk;
    }

    public void setInfantFmRisk(String infantFmRisk) {
        this.infantFmRisk = infantFmRisk;
    }

    public Float getInfantGmScore() {
        return infantGmScore;
    }

    public void setInfantGmScore(Float infantGmScore) {
        this.infantGmScore = infantGmScore;
    }

    public String getInfantGmRisk() {
        return infantGmRisk;
    }

    public void setInfantGmRisk(String infantGmRisk) {
        this.infantGmRisk = infantGmRisk;
    }

    public String getInfantNeuroOther() {
        return infantNeuroOther;
    }

    public void setInfantNeuroOther(String infantNeuroOther) {
        this.infantNeuroOther = infantNeuroOther;
    }

    public String getInfantOtherName() {
        return infantOtherName;
    }

    public void setInfantOtherName(String infantOtherName) {
        this.infantOtherName = infantOtherName;
    }

    public Float getInfantOtherScore() {
        return infantOtherScore;
    }

    public void setInfantOtherScore(Float infantOtherScore) {
        this.infantOtherScore = infantOtherScore;
    }

    public String getInfantResultScreening() {
        return infantResultScreening;
    }

    public void setInfantResultScreening(String infantResultScreening) {
        this.infantResultScreening = infantResultScreening;
    }

    public String getInfantReferTesting() {
        return infantReferTesting;
    }

    public void setInfantReferTesting(String infantReferTesting) {
        this.infantReferTesting = infantReferTesting;
    }

    public String getInfantCommentsYn3() {
        return infantCommentsYn3;
    }

    public void setInfantCommentsYn3(String infantCommentsYn3) {
        this.infantCommentsYn3 = infantCommentsYn3;
    }

    public String getInfantComments2_3() {
        return infantComments2_3;
    }

    public void setInfantComments2_3(String infantComments2_3) {
        this.infantComments2_3 = infantComments2_3;
    }

    public String getGroup1() {
        return group1;
    }

    public void setGroup1(String group1) {
        this.group1 = group1;
    }

    public String getGroup7() {
        return group7;
    }

    public void setGroup7(String group7) {
        this.group7 = group7;
    }

    public String getGroup8() {
        return group8;
    }

    public void setGroup8(String group8) {
        this.group8 = group8;
    }

    public String getGroup22() {
        return group22;
    }

    public void setGroup22(String group22) {
        this.group22 = group22;
    }

    public String getNote1() {
        return note1;
    }

    public void setNote1(String note1) {
        this.note1 = note1;
    }

    public String getNote5() {
        return note5;
    }

    public void setNote5(String note5) {
        this.note5 = note5;
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
