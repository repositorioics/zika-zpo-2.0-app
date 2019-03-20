package ni.org.ics.zpo.v2.appmovil.domain;


import java.util.Date;

/**
 * Created by FIRSTICT on 10/6/2016.
 * V1.0
 */
public class Zpo00Screening extends BaseMetaData {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String recordId;
    private String eventName;
    private Date scrVisitDate;
    private String scrConsentObta;
    private String scrConsentA; //envio muestras a EEUU
    private String scrConsentB; //USO FUTORO DE MUESTRAS BIOLOGICAS
    private String scrConsentC; //ESTUDIOS GENETICOS
    private String scrWitness;
    private String scrAssistant;
    private String scrReasonNot;
    private String scrReasonOther;
    private String scrCs;
    private String scrTipo;

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

    public Date getScrVisitDate() {
        return scrVisitDate;
    }

    public void setScrVisitDate(Date scrVisitDate) {
        this.scrVisitDate = scrVisitDate;
    }

    public String getScrConsentObta() {
        return scrConsentObta;
    }

    public void setScrConsentObta(String scrConsentObta) {
        this.scrConsentObta = scrConsentObta;
    }

    public String getScrConsentA() {
        return scrConsentA;
    }

    public void setScrConsentA(String scrConsentA) {
        this.scrConsentA = scrConsentA;
    }

    public String getScrConsentB() {
        return scrConsentB;
    }

    public void setScrConsentB(String scrConsentB) {
        this.scrConsentB = scrConsentB;
    }

    public String getScrConsentC() {
        return scrConsentC;
    }

    public void setScrConsentC(String scrConsentC) {
        this.scrConsentC = scrConsentC;
    }

    public String getScrWitness() {
        return scrWitness;
    }

    public void setScrWitness(String scrWitness) {
        this.scrWitness = scrWitness;
    }

    public String getScrAssistant() {
        return scrAssistant;
    }

    public void setScrAssistant(String scrAssistant) {
        this.scrAssistant = scrAssistant;
    }

    public String getScrReasonNot() {
        return scrReasonNot;
    }

    public void setScrReasonNot(String scrReasonNot) {
        this.scrReasonNot = scrReasonNot;
    }

    public String getScrReasonOther() {
        return scrReasonOther;
    }

    public void setScrReasonOther(String scrReasonOther) {
        this.scrReasonOther = scrReasonOther;
    }

    public String getScrCs() {
        return scrCs;
    }

    public void setScrCs(String scrCs) {
        this.scrCs = scrCs;
    }

    public String getScrTipo() {
        return scrTipo;
    }

    public void setScrTipo(String scrTipo) {
        this.scrTipo = scrTipo;
    }

    @Override
	public String toString(){
		return this.recordId;
	}
	
	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Zpo00Screening))
			return false;
		
		Zpo00Screening castOther = (Zpo00Screening) other;

		return (this.getRecordId().equals(castOther.getRecordId()));
	}
}

