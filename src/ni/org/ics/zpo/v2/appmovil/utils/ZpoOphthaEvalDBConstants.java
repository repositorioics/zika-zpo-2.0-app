package ni.org.ics.zpo.v2.appmovil.utils;

/**
 * Created by FIRSTICT on 2/1/2017.
 * V1.0
 */
public class ZpoOphthaEvalDBConstants {

    //Tabla Zp07InfantAssessmentVisit
    public static final String INFANT_OPHTHALMOLOGICEVAL_TABLE = "zpo_ophthalmologic_evaluation";

    //Campos Zp07InfantAssessmentVisit
    public static final String recordId = "recordId";
    public static final String eventName = "eventName";
    public static final String infantVisitDate = "infantVisitDate";
    public static final String infantStatus = "infantStatus";
    public static final String infantDeathDt = "infantDeathDt";
    public static final String infantVisit = "infantVisit";
    public static final String infantOphth = "infantOphth";
    public static final String infantOphthType = "infantOphthType";
    public static final String infantOphthAbno = "infantOphthAbno";
    public static final String infantCommentsYn = "infantCommentsYn";
    public static final String infantComments = "infantComments";

    //Crear tabla Zp07InfantAssessmentVisit
    public static final String CREATE_INFANT_OPHTHALMOLOGICEVAL_TABLE = "create table if not exists "
            + INFANT_OPHTHALMOLOGICEVAL_TABLE + " ("
            + recordId + " text not null, "
            + eventName + " text, "
            + infantVisitDate + " date, "
            + infantStatus + " text, "
            + infantDeathDt + " date, "
            + infantVisit + " text, "
            + infantOphth + " text, "
            + infantOphthType + " text, "
            + infantOphthAbno + " text, "
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
