package ni.org.ics.zpo.v2.appmovil.parsers;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.util.Date;

/**
 * @author ics
 */
public class ZpoV2CuestSaludInfantilXml {

    @Element(required = false)
    private Date fechaHoyNino;
    @Element(required = false)
    private String pesoNacerNino;
    @Element(required = false)
    private String tallaNacerNino;
    @Element(required = false)
    private String circunferenciaNacerNino;
    @Element(required = false)
    private Integer edadGestacionalNino;
    @Element(required = false)
    private String partoMultipleNino;
    @Element(required = false)
    private String probEmbarazoNino;
    @Element(required = false)
    private String probEmbarazoOtroNino;
    @Element(required = false)
    private String ocurrioEmbarazoNino;
    @Element(required = false)
    private String problemasBebeNino;
    @Element(required = false)
    private String problemasExtremNino;
    @Element(required = false)
    private String problemasLactanciaNino;
    @Element(required = false)
    private String visionProbNino;
    @Element(required = false)
    private String visionDescribaNino;
    @Element(required = false)
    private String audicionProbNino;
    @Element(required = false)
    private String audicionDescribaNino;
    @Element(required = false)
    private String neuroProbNino;
    @Element(required = false)
    private String medicamentoNino;
    @Element(required = false)
    private String medicamentoListaNino;
    @Element(required = false)
    private String amamantandoNino;
    @Element(required = false)
    private Date fechaAmamantarNino;
    @Element(required = false)
    private String tiempoFueraNino;
    @Element(required = false)
    private String parteDiaAfueraNino;
    @Element(required = false)
    private String quienCuidaNino;
    @Element(required = false)
    private String mayoriaTiempoNino;
    @Element(required = false)
    private String picadurasNino;
    @Element(required = false)
    private String mosquiteroDormirNino;
    @Element(required = false)
    private String mosquiteroFrecuenciaNino;
    @Element(required = false)
    private String repelenteInsectosNino;
    @Element(required = false)
    private String repelenteFrecuenciaNino;
    @Element(required = false)
    private String ministerioFueraNino;
    @Element(required = false)
    private String ultimaVezFueraNino;
    @Element(required = false)
    private String ministerioDentroNino;
    @Element(required = false)
    private String ultimaVezDentroNino;
    @Element(required = false)
    private String aplicaAbateNino;
    @Element(required = false)
    private String ultimaVezAbateNino;
    @Element(required = false)
    private String insecticidaAmbientalNino;
    @Element(required = false)
    private String ultimaVezInsecticidaNino;
    @Element(required = false)
    private String fiebreAmarillaNino;
    @Element(required = false)
    private Date fechaFiebreAmarillaNino;
    @Element(required = false)
    private String transfusionSangreNino;
    @Element(required = false)
    private Date fechaTransfusionNino;
    @Element(required = false)
    private String paisesFueraNino;
    @Element(required = false)
    private String dondePaisAfueraNino;
    @Element(required = false)
    private String fueraManaguaNino;
    @Element(required = false)
    private String adondeFueraManaguaNino;
    @Element(required = false)
    private String vistoMedicoNino;
    @Element(required = false)
    private String motivoMedicoNino;
    @Element(required = false)
    private String visitaEnfermedadNino;
    @Element(required = false)
    private String problemasNino;
    @Element(required = false)
    private String problemasOtroNino;
    @Element(required = false)
    private String diagnosticadoZikaNino;
    @Element(required = false)
    private Date fechaZikaNino;
    @Element(required = false)
    private String diagnosChikungunyaNino;
    @Element(required = false)
    private Date fechaChikungunyaNino;
    @Element(required = false)
    private String diagnosticadoDengueNino;
    @Element(required = false)
    private Date fechaDengueNino;
    @Element(required = false)
    private String nombreEncuestadorNino;
    @Element(required = false)
    private String descripcionProbNeuroNinoUpd;
    @Element(required = false)
    private String problemaComerNinoUpd;

    @Element(required=false)
    private String group1;
    @Element(required=false)
    private String group2;
    @Element(required=false)
    private String group3;


    public Date getFechaHoyNino() {
        return fechaHoyNino;
    }

    public String getPesoNacerNino() {
        return pesoNacerNino;
    }

    public String getTallaNacerNino() {
        return tallaNacerNino;
    }

    public String getCircunferenciaNacerNino() {
        return circunferenciaNacerNino;
    }

    public Integer getEdadGestacionalNino() {
        return edadGestacionalNino;
    }

    public String getPartoMultipleNino() {
        return partoMultipleNino;
    }

    public String getProbEmbarazoNino() {
        return probEmbarazoNino;
    }

    public String getProbEmbarazoOtroNino() {
        return probEmbarazoOtroNino;
    }

    public String getOcurrioEmbarazoNino() {
        return ocurrioEmbarazoNino;
    }

    public String getProblemasBebeNino() {
        return problemasBebeNino;
    }

    public String getProblemasExtremNino() {
        return problemasExtremNino;
    }

    public String getProblemasLactanciaNino() {
        return problemasLactanciaNino;
    }

    public String getVisionProbNino() {
        return visionProbNino;
    }

    public String getVisionDescribaNino() {
        return visionDescribaNino;
    }

    public String getAudicionProbNino() {
        return audicionProbNino;
    }

    public String getAudicionDescribaNino() {
        return audicionDescribaNino;
    }

    public String getNeuroProbNino() {
        return neuroProbNino;
    }

    public String getMedicamentoNino() {
        return medicamentoNino;
    }

    public String getMedicamentoListaNino() {
        return medicamentoListaNino;
    }

    public String getAmamantandoNino() {
        return amamantandoNino;
    }

    public Date getFechaAmamantarNino() {
        return fechaAmamantarNino;
    }

    public String getTiempoFueraNino() {
        return tiempoFueraNino;
    }

    public String getParteDiaAfueraNino() {
        return parteDiaAfueraNino;
    }

    public String getQuienCuidaNino() {
        return quienCuidaNino;
    }

    public String getMayoriaTiempoNino() {
        return mayoriaTiempoNino;
    }

    public String getPicadurasNino() {
        return picadurasNino;
    }

    public String getMosquiteroDormirNino() {
        return mosquiteroDormirNino;
    }

    public String getMosquiteroFrecuenciaNino() {
        return mosquiteroFrecuenciaNino;
    }

    public String getRepelenteInsectosNino() {
        return repelenteInsectosNino;
    }

    public String getRepelenteFrecuenciaNino() {
        return repelenteFrecuenciaNino;
    }

    public String getMinisterioFueraNino() {
        return ministerioFueraNino;
    }

    public String getUltimaVezFueraNino() {
        return ultimaVezFueraNino;
    }

    public String getMinisterioDentroNino() {
        return ministerioDentroNino;
    }

    public String getUltimaVezDentroNino() {
        return ultimaVezDentroNino;
    }

    public String getAplicaAbateNino() {
        return aplicaAbateNino;
    }

    public String getUltimaVezAbateNino() {
        return ultimaVezAbateNino;
    }

    public String getInsecticidaAmbientalNino() {
        return insecticidaAmbientalNino;
    }

    public String getUltimaVezInsecticidaNino() {
        return ultimaVezInsecticidaNino;
    }

    public String getFiebreAmarillaNino() {
        return fiebreAmarillaNino;
    }

    public Date getFechaFiebreAmarillaNino() {
        return fechaFiebreAmarillaNino;
    }

    public String getTransfusionSangreNino() {
        return transfusionSangreNino;
    }

    public Date getFechaTransfusionNino() {
        return fechaTransfusionNino;
    }

    public String getPaisesFueraNino() {
        return paisesFueraNino;
    }

    public String getDondePaisAfueraNino() {
        return dondePaisAfueraNino;
    }

    public String getFueraManaguaNino() {
        return fueraManaguaNino;
    }

    public String getAdondeFueraManaguaNino() {
        return adondeFueraManaguaNino;
    }

    public String getVistoMedicoNino() {
        return vistoMedicoNino;
    }

    public String getMotivoMedicoNino() {
        return motivoMedicoNino;
    }

    public String getVisitaEnfermedadNino() {
        return visitaEnfermedadNino;
    }

    public String getProblemasNino() {
        return problemasNino;
    }

    public String getProblemasOtroNino() {
        return problemasOtroNino;
    }

    public String getDiagnosticadoZikaNino() {
        return diagnosticadoZikaNino;
    }

    public Date getFechaZikaNino() {
        return fechaZikaNino;
    }

    public String getDiagnosChikungunyaNino() {
        return diagnosChikungunyaNino;
    }

    public Date getFechaChikungunyaNino() {
        return fechaChikungunyaNino;
    }

    public String getDiagnosticadoDengueNino() {
        return diagnosticadoDengueNino;
    }

    public Date getFechaDengueNino() {
        return fechaDengueNino;
    }

    public String getNombreEncuestadorNino() {
        return nombreEncuestadorNino;
    }

    public String getDescripcionProbNeuroNinoUpd() {
        return descripcionProbNeuroNinoUpd;
    }

    public String getProblemaComerNinoUpd() {
        return problemaComerNinoUpd;
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
