package ni.org.ics.zpo.v2.appmovil.parsers;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.util.Date;

/**
 * @author ics
 */
public class ZpoV2ExamFisicoInfanteXml {

    @Element(required=false)
    private Date childExamFecha;
    @Element(required=false)
    private Integer childExamAge;
    @Element(required=false)
    private Float childExamPeso;
    @Element(required=false)
    private Float childExamHeight;
    @Element(required=false)
    private Float childExamCircumference;
    @Element(required=false)
    private String childExamScarring;
    @Element(required=false)
    private String childExamAbdominalDist;
    @Element(required=false)
    private String childExamAbnormalExam;
    @Element(required=false)
    private String childExamDescribeAnomaly;
    @Element(required=false)
    private String childExamBloodSample;
    @Element(required=false)
    private Float childExamVolume;
    @Element(required=false)
    private String childExamIrritability;
    @Element(required=false)
    private String childExamLethary;
    @Element(required=false)
    private String childExamSeizures;
    @Element(required=false)
    private String childExamApnea;
    @Element(required=false)
    private String childExamLowTone;
    @Element(required=false)
    private String childExamAssymetry;
    @Element(required=false)
    private String childExamProbEyeMovt;
    @Element(required=false)
    private String childExamPromMovement;
    @Element(required=false)
    private String childExamDysphagia;
    @Element(required=false)
    private String childExamContCrying;
    @Element(required=false)
    private String childExamArthrogryposis;
    @Element(required=false)
    private String childExamHypertonia;
    @Element(required=false)
    private String childExamHypotonia;
    @Element(required=false)
    private String childExamOae;
    @Element(required=false)
    private String childExamCircumFailed;
    @Element(required=false)
    private String childExamCircumstanceDes;
    @Element(required=false)
    private String childExamCircumstances;
    @Element(required=false)
    private String childExamOphthalmology;
    @Element(required=false)
    private String childExamOpthoFiding;
    @Element(required=false)
    private String childExamLeftEyeFinds;
    @Element(required=false)
    private String childExamRightEyeFinds;
    @Element(required=false)
    private String childExamReferral;
    @Element(required=false)
    private String childExamReferralType;
    @Element(required=false)
    private String childExamPersonal;

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


    public Date getChildExamFecha() {
        return childExamFecha;
    }

    public Integer getChildExamAge() {
        return childExamAge;
    }

    public Float getChildExamPeso() {
        return childExamPeso;
    }

    public Float getChildExamHeight() {
        return childExamHeight;
    }

    public Float getChildExamCircumference() {
        return childExamCircumference;
    }

    public String getChildExamScarring() {
        return childExamScarring;
    }

    public String getChildExamAbdominalDist() {
        return childExamAbdominalDist;
    }

    public String getChildExamAbnormalExam() {
        return childExamAbnormalExam;
    }

    public String getChildExamDescribeAnomaly() {
        return childExamDescribeAnomaly;
    }

    public String getChildExamBloodSample() {
        return childExamBloodSample;
    }

    public Float getChildExamVolume() {
        return childExamVolume;
    }

    public String getChildExamIrritability() {
        return childExamIrritability;
    }

    public String getChildExamLethary() {
        return childExamLethary;
    }

    public String getChildExamSeizures() {
        return childExamSeizures;
    }

    public String getChildExamApnea() {
        return childExamApnea;
    }

    public String getChildExamLowTone() {
        return childExamLowTone;
    }

    public String getChildExamAssymetry() {
        return childExamAssymetry;
    }

    public String getChildExamProbEyeMovt() {
        return childExamProbEyeMovt;
    }

    public String getChildExamPromMovement() {
        return childExamPromMovement;
    }

    public String getChildExamDysphagia() {
        return childExamDysphagia;
    }

    public String getChildExamContCrying() {
        return childExamContCrying;
    }

    public String getChildExamArthrogryposis() {
        return childExamArthrogryposis;
    }

    public String getChildExamHypertonia() {
        return childExamHypertonia;
    }

    public String getChildExamHypotonia() {
        return childExamHypotonia;
    }

    public String getChildExamOae() {
        return childExamOae;
    }

    public String getChildExamCircumFailed() {
        return childExamCircumFailed;
    }

    public String getChildExamCircumstanceDes() {
        return childExamCircumstanceDes;
    }

    public String getChildExamCircumstances() {
        return childExamCircumstances;
    }

    public String getChildExamOphthalmology() {
        return childExamOphthalmology;
    }

    public String getChildExamOpthoFiding() {
        return childExamOpthoFiding;
    }

    public String getChildExamLeftEyeFinds() {
        return childExamLeftEyeFinds;
    }

    public String getChildExamRightEyeFinds() {
        return childExamRightEyeFinds;
    }

    public String getChildExamReferral() {
        return childExamReferral;
    }

    public String getChildExamReferralType() {
        return childExamReferralType;
    }

    public String getChildExamPersonal() {
        return childExamPersonal;
    }

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
