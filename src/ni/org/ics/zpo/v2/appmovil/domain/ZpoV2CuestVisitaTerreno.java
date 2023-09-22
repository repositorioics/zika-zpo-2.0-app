package ni.org.ics.zpo.v2.appmovil.domain;

import java.util.Date;

public class ZpoV2CuestVisitaTerreno extends BaseMetaData {

    private String recordId;
    private String eventName;
    private Date fechaVisita;
    private String areaCS;
    private String resultadoVisita;
    private String otroResultadoVisita;
    private Date fechaCita;
    private String horaCita;
    private String persCitaEntregada;
    private String persCompletaForm;


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
}
