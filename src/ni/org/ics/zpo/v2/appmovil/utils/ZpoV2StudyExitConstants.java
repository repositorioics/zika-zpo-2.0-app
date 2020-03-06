package ni.org.ics.zpo.v2.appmovil.utils;

/**
 * @author ics
 */
public class ZpoV2StudyExitConstants {

    //Tabla ZpoV2StudyExit
    public static final String STUDY_EXIT_TABLE = "zpo_study_exit";

    //Campos comunes
    public static final String recordId = "recordId";
    public static final String eventName = "eventName";

    //Campos ZpoV2StudyExit
    public static final String fechaHoyDiscont = "fechaHoyDiscont";
    public static final String razonPorDiscont = "razonPorDiscont";
    public static final String otraRazonDiscontin = "otraRazonDiscontin";
    public static final String encuestadorDiscont = "encuestadorDiscont";


    //Crear tablaZpoV2StudyExit

    public static final String CREATE_STUDYEX_ADD_TABLE = "create table if not exists "
            + STUDY_EXIT_TABLE + " ("
            + recordId + " text not null, "
            + eventName + " text, "
            + fechaHoyDiscont + " date, "
            + razonPorDiscont + " text, "
            + otraRazonDiscontin + " text, "
            + encuestadorDiscont + " text, "
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
            + "primary key (" + recordId + "));";


}