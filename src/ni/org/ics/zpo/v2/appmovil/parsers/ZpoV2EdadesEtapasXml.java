package ni.org.ics.zpo.v2.appmovil.parsers;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.util.Date;

/**
 * @author ics
 */
public class ZpoV2EdadesEtapasXml {

    @Element(required=false)
    private Date visitDate;
    @Element(required=false)
    private float comunicacion4Meses;
    @Element(required=false)
    private float motoraGruesa4Meses;
    @Element(required=false)
    private float motoraFina4Meses;
    @Element(required=false)
    private float resProb4Meses;
    @Element(required=false)
    private float socioInd4Meses;
    @Element(required=false)
    private String abnormalResults;
    @Element(required=false)
    private String areaComunicacion;
    @Element(required=false)
    private String areaMotoraGruesa;
    @Element(required=false)
    private String areaMotoraFina;
    @Element(required=false)
    private String areaSolucionProblemas;
    @Element(required=false)
    private String areaSocioIndividual;
    @Element(required=false)
    private String comGenObs4Meses;
    @Element(required=false)
    private String idCompleted;

    @Element(required=false)
    private String group1;
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

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public float getComunicacion4Meses() {
        return comunicacion4Meses;
    }

    public void setComunicacion4Meses(float comunicacion4Meses) {
        this.comunicacion4Meses = comunicacion4Meses;
    }

    public float getMotoraGruesa4Meses() {
        return motoraGruesa4Meses;
    }

    public void setMotoraGruesa4Meses(float motoraGruesa4Meses) {
        this.motoraGruesa4Meses = motoraGruesa4Meses;
    }

    public float getMotoraFina4Meses() {
        return motoraFina4Meses;
    }

    public void setMotoraFina4Meses(float motoraFina4Meses) {
        this.motoraFina4Meses = motoraFina4Meses;
    }

    public float getResProb4Meses() {
        return resProb4Meses;
    }

    public void setResProb4Meses(float resProb4Meses) {
        this.resProb4Meses = resProb4Meses;
    }

    public float getSocioInd4Meses() {
        return socioInd4Meses;
    }

    public void setSocioInd4Meses(float socioInd4Meses) {
        this.socioInd4Meses = socioInd4Meses;
    }

    public String getAbnormalResults() {
        return abnormalResults;
    }

    public void setAbnormalResults(String abnormalResults) {
        this.abnormalResults = abnormalResults;
    }

    public String getAreaComunicacion() {
        return areaComunicacion;
    }

    public void setAreaComunicacion(String areaComunicacion) {
        this.areaComunicacion = areaComunicacion;
    }

    public String getAreaMotoraGruesa() {
        return areaMotoraGruesa;
    }

    public void setAreaMotoraGruesa(String areaMotoraGruesa) {
        this.areaMotoraGruesa = areaMotoraGruesa;
    }

    public String getAreaMotoraFina() {
        return areaMotoraFina;
    }

    public void setAreaMotoraFina(String areaMotoraFina) {
        this.areaMotoraFina = areaMotoraFina;
    }

    public String getAreaSolucionProblemas() {
        return areaSolucionProblemas;
    }

    public void setAreaSolucionProblemas(String areaSolucionProblemas) {
        this.areaSolucionProblemas = areaSolucionProblemas;
    }

    public String getAreaSocioIndividual() {
        return areaSocioIndividual;
    }

    public void setAreaSocioIndividual(String areaSocioIndividual) {
        this.areaSocioIndividual = areaSocioIndividual;
    }

    public String getComGenObs4Meses() {
        return comGenObs4Meses;
    }

    public void setComGenObs4Meses(String comGenObs4Meses) {
        this.comGenObs4Meses = comGenObs4Meses;
    }

    public String getIdCompleted() {
        return idCompleted;
    }

    public void setIdCompleted(String idCompleted) {
        this.idCompleted = idCompleted;
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
