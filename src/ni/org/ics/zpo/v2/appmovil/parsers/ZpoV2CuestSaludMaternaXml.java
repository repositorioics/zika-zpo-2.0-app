package ni.org.ics.zpo.v2.appmovil.parsers;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.util.Date;

/**
 * @author ics
 */
public class ZpoV2CuestSaludMaternaXml {

    @Element(required = false)
    private Date fechaHoyMaternal;
    @Element(required = false)
    private String probFueraEmbarMaternal;
    @Element(required = false)
    private String otroProblemaMaternal;
    @Element(required = false)
    private String medicamActualMaternal;
    @Element(required = false)
    private String otroMedicamMaternal;
    @Element(required = false)
    private String fumaCigarrosMaternal;
    @Element(required = false)
    private String fumoEmbaraMaternal;
    @Element(required = false)
    private String tomaAlcoholMaternal;
    @Element(required = false)
    private String alcoholEmbarMaternal;
    @Element(required = false)
    private String frecuenciaAlcoholMaternal;
    @Element(required = false)
    private String vecesEmbarazadaMaternal;
    @Element(required = false)
    private String hijosVivosMaternal;
    @Element(required = false)
    private String defectosGeneticosMaternal;
    @Element(required = false)
    private String defectoGenetico1Maternal;
    @Element(required = false)
    private String quienDefecto1Maternal;
    @Element(required = false)
    private String otroDefectoMaternal;
    @Element(required = false)
    private String defectoGenetico2Maternal;
    @Element(required = false)
    private String quienDefecto2Maternal;
    @Element(required = false)
    private String nombreEncuestadorMaternal;

    //variables update
    @Element(required = false)
    private String embarazadaVisitaMaternalUpd;
    @Element(required = false)
    private String dadoLuzMaternalUpd;

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

    public Date getFechaHoyMaternal() {
        return fechaHoyMaternal;
    }

    public String getProbFueraEmbarMaternal() {
        return probFueraEmbarMaternal;
    }

    public String getOtroProblemaMaternal() {
        return otroProblemaMaternal;
    }

    public String getMedicamActualMaternal() {
        return medicamActualMaternal;
    }

    public String getOtroMedicamMaternal() {
        return otroMedicamMaternal;
    }

    public String getFumaCigarrosMaternal() {
        return fumaCigarrosMaternal;
    }

    public String getFumoEmbaraMaternal() {
        return fumoEmbaraMaternal;
    }

    public String getTomaAlcoholMaternal() {
        return tomaAlcoholMaternal;
    }

    public String getAlcoholEmbarMaternal() {
        return alcoholEmbarMaternal;
    }

    public String getFrecuenciaAlcoholMaternal() {
        return frecuenciaAlcoholMaternal;
    }

    public String getVecesEmbarazadaMaternal() {
        return vecesEmbarazadaMaternal;
    }

    public String getHijosVivosMaternal() {
        return hijosVivosMaternal;
    }

    public String getDefectosGeneticosMaternal() {
        return defectosGeneticosMaternal;
    }

    public String getDefectoGenetico1Maternal() {
        return defectoGenetico1Maternal;
    }

    public String getQuienDefecto1Maternal() {
        return quienDefecto1Maternal;
    }

    public String getOtroDefectoMaternal() {
        return otroDefectoMaternal;
    }

    public String getDefectoGenetico2Maternal() {
        return defectoGenetico2Maternal;
    }

    public String getQuienDefecto2Maternal() {
        return quienDefecto2Maternal;
    }

    public String getNombreEncuestadorMaternal() {
        return nombreEncuestadorMaternal;
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

    public String getEmbarazadaVisitaMaternalUpd() {
        return embarazadaVisitaMaternalUpd;
    }

    public String getDadoLuzMaternalUpd() {
        return dadoLuzMaternalUpd;
    }
}
