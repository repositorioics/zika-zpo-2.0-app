package ni.org.ics.zpo.v2.appmovil.parsers;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.util.Date;

/**
 * Created by FIRSTICT on 10/6/2016.
 * V1.0
 */

public class Zpo00ScreeningXml {

    /**
	 * 
	 */
    @Element(required=true)
    private String scrCs;
    @Element(required=true)
    private Date scrVisitDate;
    @Element(required=true)
    private String scrConsentObta;
    @Element(required=false)
    private String scrConsentA; //envio muestras a EEUU
    @Element(required=false)
    private String scrConsentB; //USO FUTORO DE MUESTRAS BIOLOGICAS
    @Element(required=false)
    private String scrConsentC; //ESTUDIOS GENETICOS
    @Element(required=false)
    private String scrWitness;
    @Element(required=false)
    private String scrAssistant;

    @Element(required=false)
    private String scrReasonNot;
    @Element(required=false)
    private String scrReasonOther;

	@Element(required=false)
    private String note1;
    @Element(required=false)
    private String group1;

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
	
	
	//Getters

    public String getScrCs() {
        return scrCs;
    }

    public void setScrCs(String scrCs) {
        this.scrCs = scrCs;
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

    public String getNote1() {
        return note1;
    }

    public void setNote1(String note1) {
        this.note1 = note1;
    }

    public String getGroup1() {
        return group1;
    }

    public void setGroup1(String group1) {
        this.group1 = group1;
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
