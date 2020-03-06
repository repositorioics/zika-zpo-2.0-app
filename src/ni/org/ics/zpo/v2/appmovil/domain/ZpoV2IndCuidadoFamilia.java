package ni.org.ics.zpo.v2.appmovil.domain;

import java.util.Date;

/**
 * @author ics
 */
public class ZpoV2IndCuidadoFamilia extends BaseMetaData {

    private String recordId;
    private String eventName;
    private Date fechaHoyFci;
    private String cuantosLibrosFci;
    private String cuantasRevistasFci;
    private String materialesJugarFci;
    private String variedadJugarFci;
    private String actividadesJugarFci;
    private String encuestadorFci;


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

    public Date getFechaHoyFci() {
        return fechaHoyFci;
    }

    public void setFechaHoyFci(Date fechaHoyFci) {
        this.fechaHoyFci = fechaHoyFci;
    }

    public String getCuantosLibrosFci() {
        return cuantosLibrosFci;
    }

    public void setCuantosLibrosFci(String cuantosLibrosFci) {
        this.cuantosLibrosFci = cuantosLibrosFci;
    }


    public String getMaterialesJugarFci() {
        return materialesJugarFci;
    }

    public void setMaterialesJugarFci(String materialesJugarFci) {
        this.materialesJugarFci = materialesJugarFci;
    }

    public String getVariedadJugarFci() {
        return variedadJugarFci;
    }

    public void setVariedadJugarFci(String variedadJugarFci) {
        this.variedadJugarFci = variedadJugarFci;
    }

    public String getCuantasRevistasFci() {
        return cuantasRevistasFci;
    }

    public void setCuantasRevistasFci(String cuantasRevistasFci) {
        this.cuantasRevistasFci = cuantasRevistasFci;
    }

    public String getActividadesJugarFci() {
        return actividadesJugarFci;
    }

    public void setActividadesJugarFci(String actividadesJugarFci) {
        this.actividadesJugarFci = actividadesJugarFci;
    }

    public String getEncuestadorFci() {
        return encuestadorFci;
    }

    public void setEncuestadorFci(String encuestadorFci) {
        this.encuestadorFci = encuestadorFci;
    }
}
