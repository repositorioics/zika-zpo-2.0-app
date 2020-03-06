package ni.org.ics.zpo.v2.appmovil.utils;

/**
 * @author ics
 */
public class ZpoV2IndCuidadoFamConstants {

    //Tabla ZpoV2IndicadoreCuidadoFamilia
    public static final String IND_CFAM_TABLE = "zpo_ind_cuidado_fam";

    //Campos comunes
    public static final String recordId = "recordId";
    public static final String eventName = "eventName";

    //Campos ZpoV2IndicadoreCuidadoFamilia
    public static final String fechaHoyFci = "fechaHoyFci";
    public static final String cuantosLibrosFci = "cuantosLibrosFci";
    public static final String cuantasRevistasFci = "cuantasRevistasFci";
    public static final String materialesJugarFci = "materialesJugarFci";
    public static final String variedadJugarFci = "variedadJugarFci";
    public static final String actividadesJugarFci = "actividadesJugarFci";
    public static final String encuestadorFci = "encuestadorFci";

    //Crear tabla ZpoV2Mullen

    public static final String CREATE_ICFAM_TABLE = "create table if not exists "
            + IND_CFAM_TABLE + " ("
            + recordId + " text not null, "
            + eventName + " text, "
            + fechaHoyFci + " date, "
            + cuantosLibrosFci + " text, "
            + cuantasRevistasFci + " text, "
            + materialesJugarFci + " text, "
            + variedadJugarFci + " text, "
            + actividadesJugarFci + " text, "
            + encuestadorFci + " text, "
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



