package ni.org.ics.zpo.v2.appmovil.utils;

import java.util.Date;

public class ZpoV2CuestVisitaTerrenoConstants {

    //Tabla ZpoV2CuestSocioeconomico
    public static final String CUEST_VIS_TER_TABLE = "zpo_cuest_visita_terreno";

    //Campos comunes
    public static final String recordId = "recordId";
    public static final String eventName = "eventName";

    //Campos ZpoV2CuestVisitaTerreno
    public static final String fechaVisita = "fechaVisita";
    public static final String areaCS = "areaCS";
    public static final String resultadoVisita = "resultadoVisita";
    public static final String otroResultadoVisita = "otroResultadoVisita";
    public static final String fechaCita = "fechaCita";
    public static final String horaCita = "horaCita";
    public static final String persCitaEntregada = "persCitaEntregada";
    public static final String persCompletaForm = "persCompletaForm";

    public static final String CREATE_CVT_TABLE = "create table if not exists "
            + CUEST_VIS_TER_TABLE + " ("
            + recordId + " text not null, "
            + eventName + " text, "
            + fechaVisita + " date, "
            + areaCS + " text, "
            + resultadoVisita + " text, "
            + otroResultadoVisita + " text, "
            + fechaCita + " date, "
            + horaCita + " text, "
            + persCitaEntregada + " text, "
            + persCompletaForm + " text, "

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
            + "primary key (" + recordId + ", "+ eventName + ", "+ fechaVisita +"));";

}
