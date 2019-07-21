package ni.org.ics.zpo.v2.appmovil.utils;

/**
 * @author ics
 */
public class ZpoV2CuestDemograficoConstants {

    //Tabla ZpoV2CuestionarioDemografico
    public static final String CUEST_DEMO_TABLE = "zpo_cuest_demografico";

    //Campos comunes
    public static final String recordId = "recordId";
    public static final String eventName = "eventName";

    //Campos ZpoV2CuestionarioDemografico
    public static final String fechaHoy = "fechaHoy";
    public static final String nombreMadreDemogr = "nombreMadreDemogr";
    public static final String codigoMadreDemogr = "codigoMadreDemogr";
    public static final String nombreNinoDemogr = "nombreNinoDemogr";
    public static final String nombrePadreDemogr = "nombrePadreDemogr";
    public static final String fechaNacMadreDemogr = "fechaNacMadreDemogr";
    public static final String fechaNacNinoDemogr = "fechaNacNinoDemogr";
    public static final String fechaNacPadreDemogr = "fechaNacPadreDemogr";
    public static final String sexoDemogr = "sexoDemogr";
    public static final String direcBarrioDemogr = "direcBarrioDemogr";
    public static final String direcExactaDemogr = "direcExactaDemogr";
    public static final String direcColorCasaDemogr = "direcColorCasaDemogr";
    public static final String ubicCasaDemogr = "ubicCasaDemogr";
    public static final String nrosTelefonicosDemogr = "nrosTelefonicosDemogr";
    public static final String nroCelularDemogr = "nroCelularDemogr";
    public static final String etnicidadDemogr = "etnicidadDemogr";
    public static final String razaDemogr = "razaDemogr";
    public static final String estadoCivilDemogr = "estadoCivilDemogr";
    public static final String escolaridadMadreDemogr = "escolaridadMadreDemogr";
    public static final String escolaridadPadreDemogr = "escolaridadPadreDemogr";
    public static final String nombreEncuestadorDemogr = "nombreEncuestadorDemogr";

    //Crear tabla ZpoV2CuestionarioDemografico

    public static final String CREATE_CDEMO_TABLE = "create table if not exists "
            + CUEST_DEMO_TABLE + " ("
            + recordId + " text not null, "
            + eventName + " text, "
            + fechaHoy + " date, "
            + nombreMadreDemogr + " text, "
            + codigoMadreDemogr + " text, "
            + nombreNinoDemogr + " text, "
            + nombrePadreDemogr + " text, "
            + fechaNacMadreDemogr + " date, "
            + fechaNacNinoDemogr + " date, "
            + fechaNacPadreDemogr + " date, "
            + sexoDemogr + " text, "
            + direcBarrioDemogr + " text, "
            + direcExactaDemogr + " text, "
            + direcColorCasaDemogr + " text, "
            + ubicCasaDemogr + " text, "
            + nrosTelefonicosDemogr + " text, "
            + nroCelularDemogr + " integer, "
            + etnicidadDemogr + " text, "
            + razaDemogr + " text, "
            + estadoCivilDemogr + " text, "
            + escolaridadMadreDemogr + " integer, "
            + escolaridadPadreDemogr + " integer, "
            + nombreEncuestadorDemogr + " text, "

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
