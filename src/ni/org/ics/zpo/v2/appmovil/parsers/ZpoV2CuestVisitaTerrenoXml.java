package ni.org.ics.zpo.v2.appmovil.parsers;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.util.Date;

public class ZpoV2CuestVisitaTerrenoXml {

    @Element(required=false)
    private Date fechaVisita;
    @Element(required=false)
    private String areaCS;
    @Element(required=false)
    private String resultadoVisita;
    @Element(required=false)
    private String otroResultadoVisita;
    @Element(required=false)
    private Date fechaCita;
    @Element(required=false)
    private String horaCita;
    @Element(required=false)
    private String persCitaEntregada;
    @Element(required=false)
    private String persCompletaForm;


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

    public Date getFechaVisita() {
        return fechaVisita;
    }

    public void setFechaVisita(Date fechaVisita) {
        this.fechaVisita = fechaVisita;
    }

    public String getAreaCS() {
        return areaCS;
    }

    public void setAreaCS(String areaCS) {
        this.areaCS = areaCS;
    }

    public String getResultadoVisita() {
        return resultadoVisita;
    }

    public void setResultadoVisita(String resultadoVisita) {
        this.resultadoVisita = resultadoVisita;
    }

    public String getOtroResultadoVisita() {
        return otroResultadoVisita;
    }

    public void setOtroResultadoVisita(String otroResultadoVisita) {
        this.otroResultadoVisita = otroResultadoVisita;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(String horaCita) {
        this.horaCita = horaCita;
    }

    public String getPersCitaEntregada() {
        return persCitaEntregada;
    }

    public void setPersCitaEntregada(String persCitaEntregada) {
        this.persCitaEntregada = persCitaEntregada;
    }

    public String getPersCompletaForm() {
        return persCompletaForm;
    }

    public void setPersCompletaForm(String persCompletaForm) {
        this.persCompletaForm = persCompletaForm;
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
