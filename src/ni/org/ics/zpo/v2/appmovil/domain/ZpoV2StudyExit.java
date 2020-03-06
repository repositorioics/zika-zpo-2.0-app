package ni.org.ics.zpo.v2.appmovil.domain;

import java.util.Date;

/**
 * @author ics
 */
public class ZpoV2StudyExit extends BaseMetaData{

    private String recordId;
    private String eventName;
    private Date fechaHoyDiscont;
    private String razonPorDiscont;
    private String otraRazonDiscontin;
    private String encuestadorDiscont;

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

    public Date getFechaHoyDiscont() {
        return fechaHoyDiscont;
    }

    public void setFechaHoyDiscont(Date fechaHoyDiscont) {
        this.fechaHoyDiscont = fechaHoyDiscont;
    }

    public String getRazonPorDiscont() {
        return razonPorDiscont;
    }

    public void setRazonPorDiscont(String razonPorDiscont) {
        this.razonPorDiscont = razonPorDiscont;
    }

    public String getOtraRazonDiscontin() {
        return otraRazonDiscontin;
    }

    public void setOtraRazonDiscontin(String otraRazonDiscontin) {
        this.otraRazonDiscontin = otraRazonDiscontin;
    }

    public String getEncuestadorDiscont() {
        return encuestadorDiscont;
    }

    public void setEncuestadorDiscont(String encuestadorDiscont) {
        this.encuestadorDiscont = encuestadorDiscont;
    }
}
