package ni.org.ics.zpo.v2.appmovil.parsers;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.util.Date;

/**
 * @author ics
 */
public class ZpoV2CuestDemograficoXml {

    @Element(required=false)
    private Date fechaHoy;
    @Element(required=false)
    private String nombreMadreDemogr;
    @Element(required=false)
    private String codigoMadreDemogr;
    @Element(required=false)
    private String nombreNinoDemogr;
    @Element(required=false)
    private String nombrePadreDemogr;
    @Element(required=false)
    private Date fechaNacMadreDemogr;
    @Element(required=false)
    private Date fechaNacNinoDemogr;
    @Element(required=false)
    private Date fechaNacPadreDemogr;
    @Element(required=false)
    private String sexoDemogr;
    @Element(required=false)
    private String direcBarrioDemogr;
    @Element(required=false)
    private String direcExactaDemogr;
    @Element(required=false)
    private String direcColorCasaDemogr;
    @Element(required=false)
    private String ubicCasaDemogr;
    @Element(required=false)
    private String nrosTelefonicosDemogr;
    @Element(required=false)
    private Integer nroCelularDemogr;
    @Element(required=false)
    private String etnicidadDemogr;
    @Element(required=false)
    private String razaDemogr;
    @Element(required=false)
    private String estadoCivilDemogr;
    @Element(required=false)
    private Integer escolaridadMadreDemogr;
    @Element(required=false)
    private Integer escolaridadPadreDemogr;
    @Element(required=false)
    private String nombreEncuestadorDemogr;


    public Date getFechaHoy() {
        return fechaHoy;
    }

    public String getNombreMadreDemogr() {
        return nombreMadreDemogr;
    }

    public String getCodigoMadreDemogr() {
        return codigoMadreDemogr;
    }

    public String getNombreNinoDemogr() {
        return nombreNinoDemogr;
    }

    public String getNombrePadreDemogr() {
        return nombrePadreDemogr;
    }

    public Date getFechaNacMadreDemogr() {
        return fechaNacMadreDemogr;
    }

    public Date getFechaNacNinoDemogr() {
        return fechaNacNinoDemogr;
    }

    public Date getFechaNacPadreDemogr() {
        return fechaNacPadreDemogr;
    }

    public String getSexoDemogr() {
        return sexoDemogr;
    }

    public String getDirecBarrioDemogr() {
        return direcBarrioDemogr;
    }

    public String getDirecExactaDemogr() {
        return direcExactaDemogr;
    }

    public String getDirecColorCasaDemogr() {
        return direcColorCasaDemogr;
    }

    public String getUbicCasaDemogr() {
        return ubicCasaDemogr;
    }

    public String getNrosTelefonicosDemogr() {
        return nrosTelefonicosDemogr;
    }

    public Integer getNroCelularDemogr() {
        return nroCelularDemogr;
    }

    public String getEtnicidadDemogr() {
        return etnicidadDemogr;
    }

    public String getRazaDemogr() {
        return razaDemogr;
    }

    public String getEstadoCivilDemogr() {
        return estadoCivilDemogr;
    }

    public Integer getEscolaridadMadreDemogr() {
        return escolaridadMadreDemogr;
    }

    public Integer getEscolaridadPadreDemogr() {
        return escolaridadPadreDemogr;
    }

    public String getNombreEncuestadorDemogr() {
        return nombreEncuestadorDemogr;
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
