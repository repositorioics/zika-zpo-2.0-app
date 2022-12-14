package ni.org.ics.zpo.v2.appmovil.domain;

import java.util.Date;

/**
 * Created by FIRSTICT on 24/10/2017.
 * V1.0
 */
public class ZpoInfantData extends BaseMetaData {

    private static final long serialVersionUID = 1L;
    private String recordId;
    private String pregnantId;
    private String infantBirthDate;
    private String infantMode;
    private String infantDeliveryWho;
    private String infantDeliveryOccur;
    private String infantHospitalId;
    private String infantClinicId;
    private String infantDeliveryOther;
    private String infantNumBirth;
    private String infantFetalOutcome;
    private String infantCauseDeath;
    private String infantSexBaby;
    private String infantConsentInfant;
    private String infantReasonNoconsent;
    private String infantNoconsentOther;

    
    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    
    public String getPregnantId() {
        return pregnantId;
    }

    public void setPregnantId(String pregnantId) {
        this.pregnantId = pregnantId;
    }

    public String getInfantBirthDate() {
        return infantBirthDate;
    }

    public void setInfantBirthDate(String infantBirthDate) {
        this.infantBirthDate = infantBirthDate;
    }

    public String getInfantMode() {
        return infantMode;
    }

    public void setInfantMode(String infantMode) {
        this.infantMode = infantMode;
    }

    
    public String getInfantDeliveryWho() {
        return infantDeliveryWho;
    }

    public void setInfantDeliveryWho(String infantDeliveryWho) {
        this.infantDeliveryWho = infantDeliveryWho;
    }

    
    public String getInfantDeliveryOccur() {
        return infantDeliveryOccur;
    }

    public void setInfantDeliveryOccur(String infantDeliveryOccur) {
        this.infantDeliveryOccur = infantDeliveryOccur;
    }

    
    public String getInfantHospitalId() {
        return infantHospitalId;
    }

    public void setInfantHospitalId(String infantHospitalId) {
        this.infantHospitalId = infantHospitalId;
    }

    
    public String getInfantClinicId() {
        return infantClinicId;
    }

    public void setInfantClinicId(String infantClinicId) {
        this.infantClinicId = infantClinicId;
    }

    
    public String getInfantDeliveryOther() {
        return infantDeliveryOther;
    }

    public void setInfantDeliveryOther(String infantDeliveryOther) {
        this.infantDeliveryOther = infantDeliveryOther;
    }

    
    public String getInfantNumBirth() {
        return infantNumBirth;
    }

    public void setInfantNumBirth(String infantNumBirth) {
        this.infantNumBirth = infantNumBirth;
    }

    
    public String getInfantFetalOutcome() {
        return infantFetalOutcome;
    }

    public void setInfantFetalOutcome(String infantFetalOutcome1) {
        this.infantFetalOutcome = infantFetalOutcome1;
    }

    
    public String getInfantCauseDeath() {
        return infantCauseDeath;
    }

    public void setInfantCauseDeath(String infantCauseDeath) {
        this.infantCauseDeath = infantCauseDeath;
    }

    
    public String getInfantSexBaby() {
        return infantSexBaby;
    }

    public void setInfantSexBaby(String infantSexBaby) {
        this.infantSexBaby = infantSexBaby;
    }

    
    public String getInfantConsentInfant() {
        return infantConsentInfant;
    }

    public void setInfantConsentInfant(String infantConsentInfant) {
        this.infantConsentInfant = infantConsentInfant;
    }

    
    public String getInfantReasonNoconsent() {
        return infantReasonNoconsent;
    }

    public void setInfantReasonNoconsent(String infantReasonNoconsent) {
        this.infantReasonNoconsent = infantReasonNoconsent;
    }

    
    public String getInfantNoconsentOther() {
        return infantNoconsentOther;
    }

    public void setInfantNoconsentOther(String infantNoconsentOther) {
        this.infantNoconsentOther = infantNoconsentOther;
    }


    @Override
    public String toString() {
        return recordId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ZpoInfantData)) return false;

        ZpoInfantData zpoInfantData = (ZpoInfantData) o;

        if (!recordId.equals(zpoInfantData.recordId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return recordId.hashCode();
    }
}
