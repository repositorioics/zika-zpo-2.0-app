package ni.org.ics.zpo.v2.appmovil.parsers;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.util.Date;

/**
 * Created by FIRSTICT on 10/10/2016.
 * V1.0
 */
public class ZpoV2RecoleccionMuestraXml {

    @Element(required=false)
    private Date bscDov;
    @Element(required=false)
    private String bscVisit;
    @Element(required=false)
    private String bscMatBldCol1;
    @Element(required=false)
    private String bscMatBldId1;
    @Element(required=false)
    private Double bscMatBldVol1;
    @Element(required=false)
    private String bscMatBldRsn1;
    @Element(required=false)
    private String bscMatBldRsnOther1;
    @Element(required=false)
    private String bscMatBldCol2;
    @Element(required=false)
    private String bscMatBldId2;
    @Element(required=false)
    private Double bscMatBldVol2;
    @Element(required=false)
    private String bscMatBldRsn2;
    @Element(required=false)
    private String bscMatBldRsnOther2;
    @Element(required=false)
    private String bscPhlebotomist;

    @Element(required=false)
    private String note1;
    @Element(required=false)
    private String question1;
    @Element(required=false)
    private String text1;
    @Element(required=false)
    private String barcode1;
    @Element(required=false)
    private String question2;
    @Element(required=false)
    private String text2;
    @Element(required=false)
    private String barcode2;

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

    public Double getBscMatBldVol1() {
        return bscMatBldVol1;
    }

    public void setBscMatBldVol1(Double bscMatBldVol1) {
        this.bscMatBldVol1 = bscMatBldVol1;
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

    public Double getBscMatBldVol2() {
        return bscMatBldVol2;
    }

    public void setBscMatBldVol2(Double bscMatBldVol2) {
        this.bscMatBldVol2 = bscMatBldVol2;
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

    public String getBscPhlebotomist() {
        return bscPhlebotomist;
    }

    public void setBscPhlebotomist(String bscPhlebotomist) {
        this.bscPhlebotomist = bscPhlebotomist;
    }

    public String getNote1() {
        return note1;
    }

    public void setNote1(String note1) {
        this.note1 = note1;
    }

    public String getQuestion1() {
        return question1;
    }

    public void setQuestion1(String question1) {
        this.question1 = question1;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getBarcode1() {
        return barcode1;
    }

    public void setBarcode1(String barcode1) {
        this.barcode1 = barcode1;
    }

    public String getQuestion2() {
        return question2;
    }

    public void setQuestion2(String question2) {
        this.question2 = question2;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getBarcode2() {
        return barcode2;
    }

    public void setBarcode2(String barcode2) {
        this.barcode2 = barcode2;
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
