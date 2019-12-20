package ni.org.ics.zpo.v2.appmovil.utils;

/**
 * @author ics
 */
public class ZpoV2FormAudicionConstants {

    //Tabla ZpoV2FormAudicion
    public static final String FORM_AUDI_TABLE = "zpo_eval_auditiva";

    //Campos comunes
    public static final String recordId = "recordId";
    public static final String eventName = "eventName";

    //Campos ZpoV2FormAudicion
    public static final String fechaDeRealizacionDePr = "fechaDeRealizacionDePr";
    public static final String haPadecidoDe = "haPadecidoDe";
    public static final String supuracionDeCualOido = "supuracionDeCualOido";
    public static final String sangradoDeCualOido = "sangradoDeCualOido";
    public static final String infeccionDeCualOido = "infeccionDeCualOido";
    public static final String haPadecidoDeAlgunaDeL = "haPadecidoDeAlgunaDeL";
    public static final String especifiqueOtra = "especifiqueOtra";
    public static final String haRecibidoTratamientoCo = "haRecibidoTratamientoCo";
    public static final String paraTbEspecifiqueSemana = "paraTbEspecifiqueSemana";
    public static final String antecedentesFamiliaresDe = "antecedentesFamiliaresDe";
    public static final String tipoDeSordera = "tipoDeSordera";
    public static final String haRecibidoTratamNino = "haRecibidoTratamNino";
    public static final String paraTbNinoSemana = "paraTbNinoSemana";
    public static final String consideraQueSuNinoEscu = "consideraQueSuNinoEscu";
    public static final String desdeHaceCuandoNotaQue = "desdeHaceCuandoNotaQue";
    public static final String conductoAuditivoExterno = "conductoAuditivoExterno";
    public static final String integridad = "integridad";
    public static final String coloracion = "coloracion";
    public static final String contorno = "contorno";
    public static final String movilidad = "movilidad";
    public static final String conductoAuditivoExtIzqu = "conductoAuditivoExtIzqu";
    public static final String integridadMembranaTimp = "integridadMembranaTimp";
    public static final String coloracionMembranaTimp = "coloracionMembranaTimp";
    public static final String contornoMembranaTimp = "contornoMembranaTimp";
    public static final String movilidadMembranaTimp = "movilidadMembranaTimp";
    public static final String odOas = "odOas";
    public static final String oiOas = "oiOas";
    public static final String odPasa = "odPasa";
    public static final String oiPasa = "oiPasa";
    public static final String resultadoDeOea = "resultadoDeOea";
    public static final String nombreDelMedicoEvaluado = "nombreDelMedicoEvaluado";

    //Crear tabla ZpoV2FormAudicion
    public static final String CREATE_FORM_AUDI_TABLE = "create table if not exists "
            + FORM_AUDI_TABLE + " ("
            + recordId + " text not null, "
            + eventName + " text, "
            + fechaDeRealizacionDePr + " date, "
            + haPadecidoDe + " text, "
            + supuracionDeCualOido + " text, "
            + sangradoDeCualOido + " text, "
            + infeccionDeCualOido + " text, "
            + haPadecidoDeAlgunaDeL + " text, "
            + especifiqueOtra + " text, "
            + haRecibidoTratamientoCo + " text, "
            + paraTbEspecifiqueSemana + " integer,"
            + antecedentesFamiliaresDe + " text, "
            + tipoDeSordera + " text, "
            + haRecibidoTratamNino + " text, "
            + paraTbNinoSemana + " integer,"
            + consideraQueSuNinoEscu + " text, "
            + desdeHaceCuandoNotaQue + " text, "
            + conductoAuditivoExterno + " text, "
            + integridad + " text, "
            + coloracion + " text, "
            + contorno + " text, "
            + movilidad + " text, "
            + conductoAuditivoExtIzqu + " text, "
            + integridadMembranaTimp + " text, "
            + coloracionMembranaTimp + " text, "
            + contornoMembranaTimp + " text, "
            + movilidadMembranaTimp + " text, "
            + odOas + " text, "
            + oiOas + " text, "
            + odPasa + " text, "
            + oiPasa + " text, "
            + resultadoDeOea + " text, "
            + nombreDelMedicoEvaluado + " text, "

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
