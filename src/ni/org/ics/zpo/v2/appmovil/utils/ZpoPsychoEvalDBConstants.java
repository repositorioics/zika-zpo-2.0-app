package ni.org.ics.zpo.v2.appmovil.utils;

/**
 * Created by FIRSTICT on 2/1/2017.
 * V1.0
 */
public class ZpoPsychoEvalDBConstants {

    //Tabla Zp07InfantAssessmentVisit
    public static final String INFANT_PSYCHOLOGICALEVAL_TABLE = "zpo_psychological_evaluation";

    //Campos Zp07InfantAssessmentVisit
    public static final String recordId = "recordId";
    public static final String eventName = "eventName";
    public static final String infantVisitDate = "infantVisitDate";
    public static final String infantStatus = "infantStatus";
    public static final String infantDeathDt = "infantDeathDt";
    public static final String infantVisit = "infantVisit";
    public static final String infantEvaluation = "infantEvaluation";
    public static final String infantNeuroAsq = "infantNeuroAsq";
    public static final String infantAsqCommuni = "infantAsqCommuni";
    public static final String infantAsqGross = "infantAsqGross";
    public static final String infantAsqFine = "infantAsqFine";
    public static final String infantAsqProblem = "infantAsqProblem";
    public static final String infantAsqPersonal = "infantAsqPersonal";
    public static final String infantNeuroBisd = "infantNeuroBisd";
    public static final String infantCgScore = "infantCgScore";
    public static final String infantCgRisk = "infantCgRisk";
    public static final String infantRpScore = "infantRpScore";
    public static final String infantRpRisk = "infantRpRisk";
    public static final String infantEpScore = "infantEpScore";
    public static final String infantEpRisk = "infantEpRisk";
    public static final String infantFmScore = "infantFmScore";
    public static final String infantFmRisk = "infantFmRisk";
    public static final String infantGmScore = "infantGmScore";
    public static final String infantGmRisk = "infantGmRisk";
    public static final String infantNeuroOther = "infantNeuroOther";
    public static final String infantOtherName = "infantOtherName";
    public static final String infantOtherScore= "infantOtherScore";
    public static final String infantResultScreening ="infantResultScreening";
    public static final String infantReferTesting = "infantReferTesting";
    public static final String infantCommentsYn = "infantCommentsYn";
    public static final String infantComments = "infantComments";


    //Crear tabla Zp07InfantAssessmentVisit
    public static final String CREATE_INFANT_PSYCHOLOGICALEVAL_TABLE = "create table if not exists "
            + INFANT_PSYCHOLOGICALEVAL_TABLE + " ("
            + recordId + " text not null, "
            + eventName + " text, "
            + infantVisitDate + " date, "
            + infantStatus + " text, "
            + infantDeathDt + " date, "
            + infantVisit + " text, "
            + infantEvaluation + " text, "
            + infantNeuroAsq + " text, "
            + infantAsqCommuni + " text, "
            + infantAsqGross + " text, "
            + infantAsqFine + " text, "
            + infantAsqProblem + " text, "
            + infantAsqPersonal + " text, "
            + infantNeuroBisd + " text, "
            + infantCgScore + " real, "
            + infantCgRisk + " text, "
            + infantRpScore + " real, "
            + infantRpRisk + " text, "
            + infantEpScore + " real, "
            + infantEpRisk + " text, "
            + infantFmScore + " real, "
            + infantFmRisk + " text, "
            + infantGmScore + " real, "
            + infantGmRisk + " text, "
            + infantNeuroOther + " text, "
            + infantOtherName + " text, "
            + infantOtherScore+ " real, "
            + infantResultScreening + " text, "
            + infantReferTesting + " text, "
            + infantCommentsYn + " text, "
            + infantComments + " text, "
            + MainDBConstants.recordDate + " date, "
            + MainDBConstants.recordUser + " text, "
            + MainDBConstants.pasive + " text, "
            + MainDBConstants.ID_INSTANCIA + " integer,"
            + MainDBConstants.FILE_PATH + " text,"
            + MainDBConstants.STATUS + " text not null, "
            + MainDBConstants.START  + " text, "
            + MainDBConstants.END  + " text, "
            + MainDBConstants.DEVICE_ID  + " text, "
            + MainDBConstants.SIM_SERIAL + " text, "
            + MainDBConstants.PHONE_NUMBER  + " text, "
            + MainDBConstants.TODAY  + " date, "
            + "primary key (" + recordId + ", "+ eventName +"));";

}
