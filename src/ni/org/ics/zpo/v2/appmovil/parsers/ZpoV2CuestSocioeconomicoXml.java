package ni.org.ics.zpo.v2.appmovil.parsers;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.util.Date;

/**
 * @author ics
 */
public class ZpoV2CuestSocioeconomicoXml {

    @Element(required=false)
    private Date fechaHoySes;
    @Element(required=false)
    private String paredesCasaSes;
    @Element(required=false)
    private String paredesCasaOtraSes;
    @Element(required=false)
    private String fuenteAguaSes;
    @Element(required=false)
    private String fuenteAguaOtraSes;
    @Element(required=false)
    private String aguaIntermitenteSes;
    @Element(required=false)
    private String guardadoAguaSes;
    @Element(required=false)
    private String tipoBanoSes;
    @Element(required=false)
    private String pisoSes;
    @Element(required=false)
    private String pisoOtroSes;
    @Element(required=false)
    private String electricidadSes;
    @Element(required=false)
    private String aireAcondicionadoSes;
    @Element(required=false)
    private String abanicoSes;
    @Element(required=false)
    private String mosquiterosSes;
    @Element(required=false)
    private String animalesSes;
    @Element(required=false)
    private String dormitoriosSes;
    @Element(required=false)
    private String cuantosDuermenSes;
    @Element(required=false)
    private String cuantosAdultosSes;
    @Element(required=false)
    private String cuantosNinosSes;
    @Element(required=false)
    private String persona1NombreSes;
    @Element(required=false)
    private String persona1EdadSes;
    @Element(required=false)
    private String persona1GradoSes;
    @Element(required=false)
    private String persona1OcupacionSes;
    @Element(required=false)
    private String persona2NombreSes;
    @Element(required=false)
    private String persona2EdadSes;
    @Element(required=false)
    private String persona2GradoSes;
    @Element(required=false)
    private String persona2OcupacionSes;
    @Element(required=false)
    private String persona3NombreSes;
    @Element(required=false)
    private String persona3EdadSes;
    @Element(required=false)
    private String persona3GradoSes;
    @Element(required=false)
    private String persona3OcupacionSes;
    @Element(required=false)
    private String persona4NombreSes;
    @Element(required=false)
    private String persona4EdadSes;
    @Element(required=false)
    private String persona4GradoSes;
    @Element(required=false)
    private String persona4OcupacionSes;
    @Element(required=false)
    private String persona5NombreSes;
    @Element(required=false)
    private String persona5EdadSes;
    @Element(required=false)
    private String persona5GradoSes;
    @Element(required=false)
    private String persona5OcupacionSes;
    @Element(required=false)
    private String persona6NombreSes;
    @Element(required=false)
    private String persona6EdadSes;
    @Element(required=false)
    private String persona6GradoSes;
    @Element(required=false)
    private String persona6OcupacionSes;
    @Element(required=false)
    private String persona7NombreSes;
    @Element(required=false)
    private String persona7EdadSes;
    @Element(required=false)
    private String persona7GradoSes;
    @Element(required=false)
    private String persona7OcupacionSes;
    @Element(required=false)
    private String persona8NombreSes;
    @Element(required=false)
    private String persona8EdadSes;
    @Element(required=false)
    private String persona8GradoSes;
    @Element(required=false)
    private String persona8OcupacionSes;
    @Element(required=false)
    private String nombreEncuestadorSes;
    @Element(required=false)
    private String preescolarSes;
    @Element(required=false)
    private String cuandoPreescolarSes;
    @Element(required=false)
    private String ambosPadresSes;

    @Element(required=false)
    private String pri;
    @Element(required=false)
    private String sec;
    @Element(required=false)
    private String third;
    @Element(required=false)
    private String schCovid;
    @Element(required=false)
    private String primarySch;

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

    public Date getFechaHoySes() {
        return fechaHoySes;
    }

    public String getParedesCasaSes() {
        return paredesCasaSes;
    }

    public String getParedesCasaOtraSes() {
        return paredesCasaOtraSes;
    }

    public String getFuenteAguaSes() {
        return fuenteAguaSes;
    }

    public String getFuenteAguaOtraSes() {
        return fuenteAguaOtraSes;
    }

    public String getAguaIntermitenteSes() {
        return aguaIntermitenteSes;
    }

    public String getGuardadoAguaSes() {
        return guardadoAguaSes;
    }

    public String getTipoBanoSes() {
        return tipoBanoSes;
    }

    public String getPisoSes() {
        return pisoSes;
    }

    public String getPisoOtroSes() {
        return pisoOtroSes;
    }

    public String getElectricidadSes() {
        return electricidadSes;
    }

    public String getAireAcondicionadoSes() {
        return aireAcondicionadoSes;
    }

    public String getAbanicoSes() {
        return abanicoSes;
    }

    public String getMosquiterosSes() {
        return mosquiterosSes;
    }

    public String getAnimalesSes() {
        return animalesSes;
    }

    public String getDormitoriosSes() {
        return dormitoriosSes;
    }

    public String getCuantosDuermenSes() {
        return cuantosDuermenSes;
    }

    public String getCuantosAdultosSes() {
        return cuantosAdultosSes;
    }

    public String getCuantosNinosSes() {
        return cuantosNinosSes;
    }

    public String getPersona1NombreSes() {
        return persona1NombreSes;
    }

    public String getPersona1EdadSes() {
        return persona1EdadSes;
    }

    public String getPersona1GradoSes() {
        return persona1GradoSes;
    }

    public String getPersona1OcupacionSes() {
        return persona1OcupacionSes;
    }

    public String getPersona2NombreSes() {
        return persona2NombreSes;
    }

    public String getPersona2EdadSes() {
        return persona2EdadSes;
    }

    public String getPersona2GradoSes() {
        return persona2GradoSes;
    }

    public String getPersona2OcupacionSes() {
        return persona2OcupacionSes;
    }

    public String getPersona3NombreSes() {
        return persona3NombreSes;
    }

    public String getPersona3EdadSes() {
        return persona3EdadSes;
    }

    public String getPersona3GradoSes() {
        return persona3GradoSes;
    }

    public String getPersona3OcupacionSes() {
        return persona3OcupacionSes;
    }

    public String getPersona4NombreSes() {
        return persona4NombreSes;
    }

    public String getPersona4EdadSes() {
        return persona4EdadSes;
    }

    public String getPersona4GradoSes() {
        return persona4GradoSes;
    }

    public String getPersona4OcupacionSes() {
        return persona4OcupacionSes;
    }

    public String getPersona5NombreSes() {
        return persona5NombreSes;
    }

    public String getPersona5EdadSes() {
        return persona5EdadSes;
    }

    public String getPersona5GradoSes() {
        return persona5GradoSes;
    }

    public String getPersona5OcupacionSes() {
        return persona5OcupacionSes;
    }

    public String getPersona6NombreSes() {
        return persona6NombreSes;
    }

    public String getPersona6EdadSes() {
        return persona6EdadSes;
    }

    public String getPersona6GradoSes() {
        return persona6GradoSes;
    }

    public String getPersona6OcupacionSes() {
        return persona6OcupacionSes;
    }

    public String getPersona7NombreSes() {
        return persona7NombreSes;
    }

    public String getPersona7EdadSes() {
        return persona7EdadSes;
    }

    public String getPersona7GradoSes() {
        return persona7GradoSes;
    }

    public String getPersona7OcupacionSes() {
        return persona7OcupacionSes;
    }

    public String getPersona8NombreSes() {
        return persona8NombreSes;
    }

    public String getPersona8EdadSes() {
        return persona8EdadSes;
    }

    public String getPersona8GradoSes() {
        return persona8GradoSes;
    }

    public String getPersona8OcupacionSes() {
        return persona8OcupacionSes;
    }

    public String getNombreEncuestadorSes() {
        return nombreEncuestadorSes;
    }

    public String getPreescolarSes() {
        return preescolarSes;
    }

    public String getCuandoPreescolarSes() {
        return cuandoPreescolarSes;
    }

    public String getAmbosPadresSes() {
        return ambosPadresSes;
    }

    public String getPri() {
        return pri;
    }

    public void setPri(String pri) {
        this.pri = pri;
    }

    public String getSec() {
        return sec;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }

    public String getThird() {
        return third;
    }

    public void setThird(String third) {
        this.third = third;
    }

    public String getSchCovid() {
        return schCovid;
    }

    public void setSchCovid(String schCovid) {
        this.schCovid = schCovid;
    }

    public String getPrimarySch() {
        return primarySch;
    }

    public void setPrimarySch(String primarySch) {
        this.primarySch = primarySch;
    }
}
