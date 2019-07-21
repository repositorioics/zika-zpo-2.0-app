package ni.org.ics.zpo.v2.appmovil.utils;

/**
 * @author ics
 */
public class ZpoV2CuestSaludMaternaConstants {

    //Tabla ZpoV2CuestSaludMaterna
    public static final String CUEST_SAL_MAT_TABLE = "zpo_cuest_salud_materna";

    //Campos comunes
    public static final String recordId = "recordId";
    public static final String eventName = "eventName";

    //Campos ZpoV2CuestSaludMaterna
    public static final String fechaHoyMaternal = "fechaHoyMaternal";
    public static final String probFueraEmbarMaternal = "probFueraEmbarMaternal";
    public static final String otroProblemaMaternal = "otroProblemaMaternal";
    public static final String medicamActualMaternal = "medicamActualMaternal";
    public static final String otroMedicamMaternal = "otroMedicamMaternal";
    public static final String fumaCigarrosMaternal = "fumaCigarrosMaternal";
    public static final String fumoEmbaraMaternal = "fumoEmbaraMaternal";
    public static final String tomaAlcoholMaternal = "tomaAlcoholMaternal";
    public static final String alcoholEmbarMaternal = "alcoholEmbarMaternal";
    public static final String frecuenciaAlcoholMaternal = "frecuenciaAlcoholMaternal";
    public static final String vecesEmbarazadaMaternal = "vecesEmbarazadaMaternal";
    public static final String hijosVivosMaternal = "hijosVivosMaternal";
    public static final String defectosGeneticosMaternal = "defectosGeneticosMaternal";
    public static final String defectoGenetico1Maternal = "defectoGenetico1Maternal";
    public static final String quienDefecto1Maternal = "quienDefecto1Maternal";
    public static final String otroDefectoMaternal = "otroDefectoMaternal";
    public static final String defectoGenetico2Maternal = "defectoGenetico2Maternal";
    public static final String quienDefecto2Maternal = "quienDefecto2Maternal";
    public static final String nombreEncuestadorMaternal = "nombreEncuestadorMaternal";

    //variables update
    public static final String embarazadaVisitaMaternalUpd = "embarazadaVisitaMaternalUpd";
    public static final String dadoLuzMaternalUpd = "dadoLuzMaternalUpd";


    //Crear tabla ZpoV2CuestSaludMaterna

    public static final String CREATE_CSM_TABLE = "create table if not exists "
            + CUEST_SAL_MAT_TABLE + " ("
            + recordId + " text not null, "
            + eventName + " text, "
            + fechaHoyMaternal + " date, "
            + probFueraEmbarMaternal + " text, "
            + otroProblemaMaternal + " text, "
            + medicamActualMaternal + " text, "
            + otroMedicamMaternal + " text, "
            + fumaCigarrosMaternal + " text, "
            + fumoEmbaraMaternal + " text, "
            + tomaAlcoholMaternal + " text, "
            + alcoholEmbarMaternal + " text, "
            + frecuenciaAlcoholMaternal + " text, "
            + vecesEmbarazadaMaternal + " text, "
            + hijosVivosMaternal + " text, "
            + defectosGeneticosMaternal + " text, "
            + defectoGenetico1Maternal + " text, "
            + quienDefecto1Maternal + " text, "
            + otroDefectoMaternal + " text, "
            + defectoGenetico2Maternal + " text, "
            + quienDefecto2Maternal + " text, "
            + nombreEncuestadorMaternal + " text, "
            + embarazadaVisitaMaternalUpd + " text, "
            + dadoLuzMaternalUpd + " text, "

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
