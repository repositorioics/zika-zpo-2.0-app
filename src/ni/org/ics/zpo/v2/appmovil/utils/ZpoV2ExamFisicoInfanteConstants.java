package ni.org.ics.zpo.v2.appmovil.utils;

/**
 * @author ics
 */
public class ZpoV2ExamFisicoInfanteConstants {

    //Tabla ZpoV2EvaluacionPsicologica
    public static final String EXAM_FIS_INF_TABLE = "zpo_examen_fisico_infante";

    //Campos comunes
    public static final String recordId = "recordId";
    public static final String eventName = "eventName";

    //Campos ZpoV2ExamenFisicoInfante
    public static final String childExamFecha = "childExamFecha";
    public static final String childExamAge = "childExamAge";
    public static final String childExamPeso = "childExamPeso";
    public static final String childExamHeight = "childExamHeight";
    public static final String childExamCircumference = "childExamCircumference";
    public static final String childExamScarring = "childExamScarring";
    public static final String childExamAbdominalDist = "childExamAbdominalDist";
    public static final String childExamAbnormalExam = "childExamAbnormalExam";
    public static final String childExamDescribeAnomaly = "childExamDescribeAnomaly";
    public static final String childExamBloodSample = "childExamBloodSample";
    public static final String childExamVolume = "childExamVolume";
    public static final String childExamIrritability = "childExamIrritability";
    public static final String childExamLethary = "childExamLethary";
    public static final String childExamSeizures = "childExamSeizures";
    public static final String childExamApnea = "childExamApnea";
    public static final String childExamLowTone = "childExamLowTone";
    public static final String childExamAssymetry = "childExamAssymetry";
    public static final String childExamProbEyeMovt = "childExamProbEyeMovt";
    public static final String childExamPromMovement = "childExamPromMovement";
    public static final String childExamDysphagia = "childExamDysphagia";
    public static final String childExamContCrying = "childExamContCrying";
    public static final String childExamArthrogryposis = "childExamArthrogryposis";
    public static final String childExamHypertonia = "childExamHypertonia";
    public static final String childExamHypotonia = "childExamHypotonia";
    public static final String childExamOae = "childExamOae";
    public static final String childExamCircumFailed = "childExamCircumFailed";
    public static final String childExamCircumstanceDes = "childExamCircumstanceDes";
    public static final String childExamCircumstances = "childExamCircumstances";
    public static final String childExamOphthalmology = "childExamOphthalmology";
    public static final String childExamOpthoFiding = "childExamOpthoFiding";
    public static final String childExamLeftEyeFinds = "childExamLeftEyeFinds";
    public static final String childExamRightEyeFinds = "childExamRightEyeFinds";
    public static final String childExamReferral = "childExamReferral";
    public static final String childExamReferralType = "childExamReferralType";
    public static final String childExamPersonal = "childExamPersonal";

    //Crear tabla ZpoV2ExamenFisicoInfante
    public static final String CREATE_EXFISINF_TABLE = "create table if not exists "
            + EXAM_FIS_INF_TABLE + " ("
            + recordId + " text not null, "
            + eventName + " text, "
            + childExamFecha + " date, "
            + childExamAge + " integer,"
            + childExamPeso + " real,"
            + childExamHeight + " real,"
            + childExamCircumference + " real,"
            + childExamScarring + " text, "
            + childExamAbdominalDist + " text, "
            + childExamAbnormalExam + " text, "
            + childExamDescribeAnomaly + " text, "
            + childExamBloodSample + " text, "
            + childExamVolume + " real,"
            + childExamIrritability + " text, "
            + childExamLethary + " text, "
            + childExamSeizures + " text, "
            + childExamApnea + " text, "
            + childExamLowTone + " text, "
            + childExamAssymetry + " text, "
            + childExamProbEyeMovt + " text, "
            + childExamPromMovement + " text, "
            + childExamDysphagia + " text, "
            + childExamContCrying + " text, "
            + childExamArthrogryposis + " text, "
            + childExamHypertonia + " text, "
            + childExamHypotonia + " text, "
            + childExamOae + " text, "
            + childExamCircumFailed + " text, "
            + childExamCircumstanceDes + " text, "
            + childExamCircumstances + " text, "
            + childExamOphthalmology + " text, "
            + childExamOpthoFiding + " text, "
            + childExamLeftEyeFinds + " text, "
            + childExamRightEyeFinds + " text, "
            + childExamReferral + " text, "
            + childExamReferralType + " text, "
            + childExamPersonal + " text, "

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
