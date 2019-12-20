package ni.org.ics.zpo.v2.appmovil.parsers;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.util.Date;

/**
 * @author ics
 */
public class ZpoV2FormAudicionXml {

    @Element(required=false)
    private Date fechaDeRealizacionDePr;
    @Element(required=false)
    private String haPadecidoDe;
    @Element(required=false)
    private String supuracionDeCualOido;
    @Element(required=false)
    private String sangradoDeCualOido;
    @Element(required=false)
    private String infeccionDeCualOido;
    @Element(required=false)
    private String haPadecidoDeAlgunaDeL;
    @Element(required=false)
    private String especifiqueOtra;
    @Element(required=false)
    private String haRecibidoTratamientoCo;
    @Element(required=false)
    private Integer paraTbEspecifiqueSemana;
    @Element(required=false)
    private String antecedentesFamiliaresDe;
    @Element(required=false)
    private String tipoDeSordera;
    @Element(required=false)
    private String haRecibidoTratamNino;
    @Element(required=false)
    private Integer paraTbNinoSemana;
    @Element(required=false)
    private String consideraQueSuNinoEscu;
    @Element(required=false)
    private String desdeHaceCuandoNotaQue;
    @Element(required=false)
    private String conductoAuditivoExterno;
    @Element(required=false)
    private String integridad;
    @Element(required=false)
    private String coloracion;
    @Element(required=false)
    private String contorno;
    @Element(required=false)
    private String movilidad;
    @Element(required=false)
    private String conductoAuditivoExtIzqu;
    @Element(required=false)
    private String integridadMembranaTimp;
    @Element(required=false)
    private String coloracionMembranaTimp;
    @Element(required=false)
    private String contornoMembranaTimp;
    @Element(required=false)
    private String movilidadMembranaTimp;
    @Element(required=false)
    private String odOas;
    @Element(required=false)
    private String oiOas;
    @Element(required=false)
    private String odPasa;
    @Element(required=false)
    private String oiPasa;
    @Element(required=false)
    private String resultadoDeOea;
    @Element(required=false)
    private String nombreDelMedicoEvaluado;

    @Element(required=false)
    private String group1;
    @Element(required=false)
    private String group2;
    @Element(required=false)
    private String group3;
    @Element(required=false)
    private String group4;
    @Element(required=false)
    private String group5;
    @Element(required=false)
    private String group6;
    @Element(required=false)
    private String group7;
    @Element(required=false)
    private String group8;

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

    public Date getFechaDeRealizacionDePr() {
        return fechaDeRealizacionDePr;
    }

    public String getHaPadecidoDe() {
        return haPadecidoDe;
    }

    public String getSupuracionDeCualOido() {
        return supuracionDeCualOido;
    }

    public String getSangradoDeCualOido() {
        return sangradoDeCualOido;
    }

    public String getInfeccionDeCualOido() {
        return infeccionDeCualOido;
    }

    public String getHaPadecidoDeAlgunaDeL() {
        return haPadecidoDeAlgunaDeL;
    }

    public String getEspecifiqueOtra() {
        return especifiqueOtra;
    }

    public String getHaRecibidoTratamientoCo() {
        return haRecibidoTratamientoCo;
    }

    public Integer getParaTbEspecifiqueSemana() {
        return paraTbEspecifiqueSemana;
    }

    public String getAntecedentesFamiliaresDe() {
        return antecedentesFamiliaresDe;
    }

    public String getTipoDeSordera() {
        return tipoDeSordera;
    }

    public String getHaRecibidoTratamNino() {
        return haRecibidoTratamNino;
    }

    public Integer getParaTbNinoSemana() {
        return paraTbNinoSemana;
    }

    public String getConsideraQueSuNinoEscu() {
        return consideraQueSuNinoEscu;
    }

    public String getDesdeHaceCuandoNotaQue() {
        return desdeHaceCuandoNotaQue;
    }

    public String getConductoAuditivoExterno() {
        return conductoAuditivoExterno;
    }

    public String getIntegridad() {
        return integridad;
    }

    public String getColoracion() {
        return coloracion;
    }

    public String getContorno() {
        return contorno;
    }

    public String getMovilidad() {
        return movilidad;
    }

    public String getConductoAuditivoExtIzqu() {
        return conductoAuditivoExtIzqu;
    }

    public String getIntegridadMembranaTimp() {
        return integridadMembranaTimp;
    }

    public String getColoracionMembranaTimp() {
        return coloracionMembranaTimp;
    }

    public String getContornoMembranaTimp() {
        return contornoMembranaTimp;
    }

    public String getMovilidadMembranaTimp() {
        return movilidadMembranaTimp;
    }

    public String getOdOas() {
        return odOas;
    }

    public String getOiOas() {
        return oiOas;
    }

    public String getOdPasa() {
        return odPasa;
    }

    public String getOiPasa() {
        return oiPasa;
    }

    public String getResultadoDeOea() {
        return resultadoDeOea;
    }

    public String getNombreDelMedicoEvaluado() {
        return nombreDelMedicoEvaluado;
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
