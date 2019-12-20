package ni.org.ics.zpo.v2.appmovil.parsers;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.util.Date;

/**
 * @author ics
 */
public class ZpoV2EvalPsicologicaXml {

    @Element(required = false)
    private Date fechaPsych;
   /* @Element(required = false)
    private String trabajarPsych;
    @Element(required = false)
    private String cocinarPsych;
    @Element(required = false)
    private String mercadoPsych;
    @Element(required = false)
    private String banarHijoPsych;
    @Element(required = false)
    private String vestirHijoPsych;
    @Element(required = false)
    private String limpiarPsych;
    @Element(required = false)
    private String lavarRopaPsych;
    @Element(required = false)
    private String banarsePsych;
    @Element(required = false)
    private String cuidarCabelloPsych;
    @Element(required = false)
    private String atenderVisitaPsych;
    @Element(required = false)
    private String lavarDientesPsych;
    @Element(required = false)
    private String usarRopaLimpiaPsych;
    @Element(required = false)
    private String juntarMujeresPsych;
    @Element(required = false)
    private String ayudarAmigasPsych;
    @Element(required = false)
    private String compartirInfoPsych;
    @Element(required = false)
    private String tareasSaludPsych;
    @Element(required = false)
    private Integer funcionamientoPuntajePsych;*/
    @Element(required = false)
    private String sinEnergiaPsych;
    @Element(required = false)
    private String culparseMismaPsych;
    @Element(required = false)
    private String llorarPsych;
    @Element(required = false)
    private String probConcentPsych;
    @Element(required = false)
    private String faltaApetitoPsych;
    @Element(required = false)
    private String difficulDormirPsych;
    @Element(required = false)
    private String sinEsperanzaPsych;
    @Element(required = false)
    private String tristePsych;
    @Element(required = false)
    private String solaPsych;
    @Element(required = false)
    private String acabarVidaPsych;
    @Element(required = false)
    private String preocuparsePsych;
    @Element(required = false)
    private String noInteresPsych;
    @Element(required = false)
    private String todoEsfuerzoPsych;
    @Element(required = false)
    private String sienteNoValePsych;
    @Element(required = false)
    private String noInteresSexoPsych;
    @Element(required = false)
    private String asustaPsych;
    @Element(required = false)
    private String sienteMiedoPsych;
    @Element(required = false)
    private String debilidadPsych;
    @Element(required = false)
    private String nerviosPsych;
    @Element(required = false)
    private String palpitacionesPsych;
    @Element(required = false)
    private String tiemblaPsych;
    @Element(required = false)
    private String sienteTensaPsych;
    @Element(required = false)
    private String dolorCabezaPsych;
    @Element(required = false)
    private String panicoPsych;
    @Element(required = false)
    private String inquietudPsych;
    @Element(required = false)
    private Double sintomasPuntajePsych;
    @Element(required = false)
    private Double scoreDepressionPsych;
    @Element(required = false)
    private String examinadorPsych;

    @Element(required=false)
    private String group1;
    @Element(required=false)
    private String group2;
    @Element(required=false)
    private String note1;
    @Element(required=false)
    private String note2;
    @Element(required=false)
    private String note3;
    @Element(required=false)
    private String note4;
    @Element(required=false)
    private String note5;
    @Element(required=false)
    private String note6;


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

    public Date getFechaPsych() {
        return fechaPsych;
    }

    public String getSinEnergiaPsych() {
        return sinEnergiaPsych;
    }

    public String getCulparseMismaPsych() {
        return culparseMismaPsych;
    }

    public String getLlorarPsych() {
        return llorarPsych;
    }

    public String getProbConcentPsych() {
        return probConcentPsych;
    }

    public String getFaltaApetitoPsych() {
        return faltaApetitoPsych;
    }

    public String getDifficulDormirPsych() {
        return difficulDormirPsych;
    }

    public String getSinEsperanzaPsych() {
        return sinEsperanzaPsych;
    }

    public String getTristePsych() {
        return tristePsych;
    }

    public String getSolaPsych() {
        return solaPsych;
    }

    public String getAcabarVidaPsych() {
        return acabarVidaPsych;
    }

    public String getPreocuparsePsych() {
        return preocuparsePsych;
    }

    public String getNoInteresPsych() {
        return noInteresPsych;
    }

    public String getTodoEsfuerzoPsych() {
        return todoEsfuerzoPsych;
    }

    public String getSienteNoValePsych() {
        return sienteNoValePsych;
    }

    public String getNoInteresSexoPsych() {
        return noInteresSexoPsych;
    }

    public String getAsustaPsych() {
        return asustaPsych;
    }

    public String getSienteMiedoPsych() {
        return sienteMiedoPsych;
    }

    public String getDebilidadPsych() {
        return debilidadPsych;
    }

    public String getNerviosPsych() {
        return nerviosPsych;
    }

    public String getPalpitacionesPsych() {
        return palpitacionesPsych;
    }

    public String getTiemblaPsych() {
        return tiemblaPsych;
    }

    public String getSienteTensaPsych() {
        return sienteTensaPsych;
    }

    public String getDolorCabezaPsych() {
        return dolorCabezaPsych;
    }

    public String getPanicoPsych() {
        return panicoPsych;
    }

    public String getInquietudPsych() {
        return inquietudPsych;
    }

    public String getExaminadorPsych() {
        return examinadorPsych;
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

    public String getGroup1() {
        return group1;
    }

    public String getGroup2() {
        return group2;
    }

    public String getNote1() {
        return note1;
    }

    public String getNote2() {
        return note2;
    }

    public String getNote3() {
        return note3;
    }

    public String getNote4() {
        return note4;
    }

    public String getNote5() {
        return note5;
    }

    public String getNote6() {
        return note6;
    }

    public Double getSintomasPuntajePsych() {
        return sintomasPuntajePsych;
    }

    public Double getScoreDepressionPsych() {
        return scoreDepressionPsych;
    }
}
