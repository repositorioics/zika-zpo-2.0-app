package ni.org.ics.zpo.v2.appmovil.utils;

/**
 * @author ics
 */
public class ZpoV2EdadesEtapasConstants {

    //Tabla ZpoEdadesEtapas
    public static final String EDADES_ETAPAS_TABLE = "zpo_edades_etapas";

    //Campos ZpoEdadesEtapas
    public static final String recordId = "recordId";
    public static final String eventName = "eventName";
    public static final String visitDate = "visitDate";
    public static final String comunicacion4Meses = "comunicacion4Meses";
    public static final String motoraGruesa4Meses = "motoraGruesa4Meses";
    public static final String motoraFina4Meses = "motoraFina4Meses";
    public static final String resProb4Meses = "resProb4Meses";
    public static final String socioInd4Meses = "socioInd4Meses";
    public static final String abnormalResults = "abnormalResults";
    public static final String areaComunicacion = "areaComunicacion";
    public static final String areaMotoraGruesa = "areaMotoraGruesa";
    public static final String areaMotoraFina = "areaMotoraFina";
    public static final String areaSolucionProblemas = "areaSolucionProblemas";
    public static final String areaSocioIndividual = "areaSocioIndividual";
    public static final String comGenObs4Meses = "comGenObs4Meses";
    public static final String idCompleted = "idCompleted";

    //Crear tabla ZpoEdadesEtapas
    public static final String CREATE_EDADES_ETAPA_TABLE = "create table if not exists "
            + EDADES_ETAPAS_TABLE + " ("
            + recordId + " text not null, "
            + eventName + " text, "
            +  visitDate + " date, "
            + comunicacion4Meses + " real, "
            + motoraGruesa4Meses + " real, "
            + motoraFina4Meses + " real, "
            + resProb4Meses + " real, "
            + socioInd4Meses + " real, "
            + abnormalResults + " text, "
            + areaComunicacion + " text, "
            + areaMotoraGruesa + " text, "
            + areaMotoraFina + " text, "
            + areaSolucionProblemas + " text, "
            + areaSocioIndividual + " text, "
            + comGenObs4Meses + " text, "
            + idCompleted + " text, "

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
