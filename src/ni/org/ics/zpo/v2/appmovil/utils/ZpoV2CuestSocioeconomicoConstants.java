package ni.org.ics.zpo.v2.appmovil.utils;

/**
 * @author ics
 */
public class ZpoV2CuestSocioeconomicoConstants {

    //Tabla ZpoV2CuestSocioeconomico
    public static final String CUEST_SOE_TABLE = "zpo_cuest_socioeconomico";

    //Campos comunes
    public static final String recordId = "recordId";
    public static final String eventName = "eventName";

    //Campos ZpoV2CuestSocioeconomico
    public static final String fechaHoySes = "fechaHoySes";
    public static final String paredesCasaSes = "paredesCasaSes";
    public static final String paredesCasaOtraSes = "paredesCasaOtraSes";
    public static final String fuenteAguaSes = "fuenteAguaSes";
    public static final String fuenteAguaOtraSes = "fuenteAguaOtraSes";
    public static final String aguaIntermitenteSes = "aguaIntermitenteSes";
    public static final String guardadoAguaSes = "guardadoAguaSes";
    public static final String tipoBanoSes = "tipoBanoSes";
    public static final String pisoSes = "pisoSes";
    public static final String pisoOtroSes = "pisoOtroSes";
    public static final String electricidadSes = "electricidadSes";
    public static final String aireAcondicionadoSes = "aireAcondicionadoSes";
    public static final String abanicoSes = "abanicoSes";
    public static final String mosquiterosSes = "mosquiterosSes";
    public static final String animalesSes = "animalesSes";
    public static final String dormitoriosSes = "dormitoriosSes";
    public static final String cuantosDuermenSes = "cuantosDuermenSes";
    public static final String cuantosAdultosSes = "cuantosAdultosSes";
    public static final String cuantosNinosSes = "cuantosNinosSes";
    public static final String persona1NombreSes = "persona1NombreSes";
    public static final String persona1EdadSes = "persona1EdadSes";
    public static final String persona1GradoSes = "persona1GradoSes";
    public static final String persona1OcupacionSes = "persona1OcupacionSes";
    public static final String persona2NombreSes = "persona2NombreSes";
    public static final String persona2EdadSes = "persona2EdadSes";
    public static final String persona2GradoSes = "persona2GradoSes";
    public static final String persona2OcupacionSes = "persona2OcupacionSes";
    public static final String persona3NombreSes = "persona3NombreSes";
    public static final String persona3EdadSes = "persona3EdadSes";
    public static final String persona3GradoSes = "persona3GradoSes";
    public static final String persona3OcupacionSes = "persona3OcupacionSes";
    public static final String persona4NombreSes = "persona4NombreSes";
    public static final String persona4EdadSes = "persona4EdadSes";
    public static final String persona4GradoSes = "persona4GradoSes";
    public static final String persona4OcupacionSes = "persona4OcupacionSes";
    public static final String persona5NombreSes = "persona5NombreSes";
    public static final String persona5EdadSes = "persona5EdadSes";
    public static final String persona5GradoSes = "persona5GradoSes";
    public static final String persona5OcupacionSes = "persona5OcupacionSes";
    public static final String persona6NombreSes = "persona6NombreSes";
    public static final String persona6EdadSes = "persona6EdadSes";
    public static final String persona6GradoSes = "persona6GradoSes";
    public static final String persona6OcupacionSes = "persona6OcupacionSes";
    public static final String persona7NombreSes = "persona7NombreSes";
    public static final String persona7EdadSes = "persona7EdadSes";
    public static final String persona7GradoSes = "persona7GradoSes";
    public static final String persona7OcupacionSes = "persona7OcupacionSes";
    public static final String persona8NombreSes = "persona8NombreSes";
    public static final String persona8EdadSes = "persona8EdadSes";
    public static final String persona8GradoSes = "persona8GradoSes";
    public static final String persona8OcupacionSes = "persona8OcupacionSes";
    public static final String nombreEncuestadorSes = "nombreEncuestadorSes";
    public static final String preescolarSes = "preescolarSes";
    public static final String cuandoPreescolarSes = "cuandoPreescolarSes";
    public static final String ambosPadresSes = "ambosPadresSes";


    //Crear tabla ZpoV2CuestSocioeconomico

    public static final String CREATE_CSOE_TABLE = "create table if not exists "
            + CUEST_SOE_TABLE + " ("
            + recordId + " text not null, "
            + eventName + " text, "
            + fechaHoySes + " date, "
            + paredesCasaSes + " text, "
            + paredesCasaOtraSes + " text, "
            + fuenteAguaSes + " text, "
            + fuenteAguaOtraSes + " text, "
            + aguaIntermitenteSes + " text, "
            + guardadoAguaSes + " text, "
            + tipoBanoSes + " text, "
            + pisoSes + " text, "
            + pisoOtroSes + " text, "
            + electricidadSes + " text, "
            + aireAcondicionadoSes + " text, "
            + abanicoSes + " text, "
            + mosquiterosSes + " text, "
            + animalesSes + " text, "
            + dormitoriosSes + " text, "
            + cuantosDuermenSes + " text, "
            + cuantosAdultosSes + " text, "
            + cuantosNinosSes + " text, "
            + persona1NombreSes + " text, "
            + persona1EdadSes + " text, "
            + persona1GradoSes + " text, "
            + persona1OcupacionSes + " text, "
            + persona2NombreSes + " text, "
            + persona2EdadSes + " text, "
            + persona2GradoSes + " text, "
            + persona2OcupacionSes + " text, "
            + persona3NombreSes + " text, "
            + persona3EdadSes + " text, "
            + persona3GradoSes + " text, "
            + persona3OcupacionSes + " text, "
            + persona4NombreSes + " text, "
            + persona4EdadSes + " text, "
            + persona4GradoSes + " text, "
            + persona4OcupacionSes + " text, "
            + persona5NombreSes + " text, "
            + persona5EdadSes + " text, "
            + persona5GradoSes + " text, "
            + persona5OcupacionSes + " text, "
            + persona6NombreSes + " text, "
            + persona6EdadSes + " text, "
            + persona6GradoSes + " text, "
            + persona6OcupacionSes + " text, "
            + persona7NombreSes + " text, "
            + persona7EdadSes + " text, "
            + persona7GradoSes + " text, "
            + persona7OcupacionSes + " text, "
            + persona8NombreSes + " text, "
            + persona8EdadSes + " text, "
            + persona8GradoSes + " text, "
            + persona8OcupacionSes + " text, "
            + nombreEncuestadorSes + " text, "
            + preescolarSes + " text, "
            + cuandoPreescolarSes + " text, "
            + ambosPadresSes + " text, "

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
