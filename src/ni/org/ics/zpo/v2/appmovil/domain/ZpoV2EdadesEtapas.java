package ni.org.ics.zpo.v2.appmovil.domain;

import java.util.Date;

/**
 * @author ics
 */
public class ZpoV2EdadesEtapas extends BaseMetaData {

    private String recordId;
    private String eventName;
    private Date visitDate;
    private float comunicacion4Meses;
    private float motoraGruesa4Meses;
    private float motoraFina4Meses;
    private float resProb4Meses;
    private float socioInd4Meses;
    private String abnormalResults;
    private String areaComunicacion;
    private String areaMotoraGruesa;
    private String areaMotoraFina;
    private String areaSolucionProblemas;
    private String areaSocioIndividual;
    private String comGenObs4Meses;
    private String idCompleted;


    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

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
}
