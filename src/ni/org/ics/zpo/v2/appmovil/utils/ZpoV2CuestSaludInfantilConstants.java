package ni.org.ics.zpo.v2.appmovil.utils;

/**
 * @author ics
 */
public class ZpoV2CuestSaludInfantilConstants {

    //Tabla ZpoV2CuestSaludInfantil
    public static final String CUEST_SAL_INF_TABLE = "zpo_cuest_salud_infantil";

    //Campos comunes
    public static final String recordId = "recordId";
    public static final String eventName = "eventName";

    //Campos ZpoV2CuestSaludInfantil
    public static final String fechaHoyNino = "fechaHoyNino";
    public static final String pesoNacerNino = "pesoNacerNino";
    public static final String tallaNacerNino = "tallaNacerNino";
    public static final String circunferenciaNacerNino = "circunferenciaNacerNino";
    public static final String edadGestacionalNino = "edadGestacionalNino";
    public static final String partoMultipleNino = "partoMultipleNino";
    public static final String probEmbarazoNino = "probEmbarazoNino";
    public static final String probEmbarazoOtroNino = "probEmbarazoOtroNino";
    public static final String ocurrioEmbarazoNino = "ocurrioEmbarazoNino";
    public static final String problemasBebeNino = "problemasBebeNino";
    public static final String problemasExtremNino = "problemasExtremNino";
    public static final String problemasLactanciaNino = "problemasLactanciaNino";
    public static final String visionProbNino = "visionProbNino";
    public static final String visionDescribaNino = "visionDescribaNino";
    public static final String audicionProbNino = "audicionProbNino";
    public static final String audicionDescribaNino = "audicionDescribaNino";
    public static final String neuroProbNino = "neuroProbNino";
    public static final String medicamentoNino = "medicamentoNino";
    public static final String medicamentoListaNino = "medicamentoListaNino";
    public static final String amamantandoNino = "amamantandoNino";
    public static final String fechaAmamantarNino = "fechaAmamantarNino";
    public static final String tiempoFueraNino = "tiempoFueraNino";
    public static final String parteDiaAfueraNino = "parteDiaAfueraNino";
    public static final String quienCuidaNino = "quienCuidaNino";
    public static final String mayoriaTiempoNino = "mayoriaTiempoNino";
    public static final String picadurasNino = "picadurasNino";
    public static final String mosquiteroDormirNino = "mosquiteroDormirNino";
    public static final String mosquiteroFrecuenciaNino = "mosquiteroFrecuenciaNino";
    public static final String repelenteInsectosNino = "repelenteInsectosNino";
    public static final String repelenteFrecuenciaNino = "repelenteFrecuenciaNino";
    public static final String ministerioFueraNino = "ministerioFueraNino";
    public static final String ultimaVezFueraNino = "ultimaVezFueraNino";
    public static final String ministerioDentroNino = "ministerioDentroNino";
    public static final String ultimaVezDentroNino = "ultimaVezDentroNino";
    public static final String aplicaAbateNino = "aplicaAbateNino";
    public static final String ultimaVezAbateNino = "ultimaVezAbateNino";
    public static final String insecticidaAmbientalNino = "insecticidaAmbientalNino";
    public static final String ultimaVezInsecticidaNino = "ultimaVezInsecticidaNino";
    public static final String fiebreAmarillaNino = "fiebreAmarillaNino";
    public static final String fechaFiebreAmarillaNino = "fechaFiebreAmarillaNino";
    public static final String transfusionSangreNino = "transfusionSangreNino";
    public static final String fechaTransfusionNino = "fechaTransfusionNino";
    public static final String paisesFueraNino = "paisesFueraNino";
    public static final String dondePaisAfueraNino = "dondePaisAfueraNino";
    public static final String fueraManaguaNino = "fueraManaguaNino";
    public static final String adondeFueraManaguaNino = "adondeFueraManaguaNino";
    public static final String vistoMedicoNino = "vistoMedicoNino";
    public static final String motivoMedicoNino = "motivoMedicoNino";
    public static final String visitaEnfermedadNino = "visitaEnfermedadNino";
    public static final String problemasNino = "problemasNino";
    public static final String problemasOtroNino = "problemasOtroNino";
    public static final String diagnosticadoZikaNino = "diagnosticadoZikaNino";
    public static final String fechaZikaNino = "fechaZikaNino";
    public static final String diagnosChikungunyaNino = "diagnosChikungunyaNino";
    public static final String fechaChikungunyaNino = "fechaChikungunyaNino";
    public static final String diagnosticadoDengueNino = "diagnosticadoDengueNino";
    public static final String fechaDengueNino = "fechaDengueNino";
    public static final String nombreEncuestadorNino = "nombreEncuestadorNino";
    public static final String descripcionProbNeuroNinoUpd = "descripcionProbNeuroNinoUpd";
    public static final String problemaComerNinoUpd = "problemaComerNinoUpd";

    //Crear tabla ZpoV2CuestSaludInfantil

    public static final String CREATE_CSI_TABLE = "create table if not exists "
            + CUEST_SAL_INF_TABLE + " ("
            + recordId + " text not null, "
            + eventName + " text, "
            + fechaHoyNino + " date, "
            + pesoNacerNino + " text, "
            + tallaNacerNino + " text, "
            + circunferenciaNacerNino + " text, "
            + edadGestacionalNino + " integer, "
            + partoMultipleNino + " text, "
            + probEmbarazoNino + " text, "
            + probEmbarazoOtroNino + " text, "
            + ocurrioEmbarazoNino + " text, "
            + problemasBebeNino + " text, "
            + problemasExtremNino + " text, "
            + problemasLactanciaNino + " text, "
            + visionProbNino + " text, "
            + visionDescribaNino + " text, "
            + audicionProbNino + " text, "
            + audicionDescribaNino + " text, "
            + neuroProbNino + " text, "
            + medicamentoNino + " text, "
            + medicamentoListaNino + " text, "
            + amamantandoNino + " text, "
            + fechaAmamantarNino + " date, "
            + tiempoFueraNino + " text, "
            + parteDiaAfueraNino + " text, "
            + quienCuidaNino + " text, "
            + mayoriaTiempoNino + " text, "
            + picadurasNino + " text, "
            + mosquiteroDormirNino + " text, "
            + mosquiteroFrecuenciaNino + " text, "
            + repelenteInsectosNino + " text, "
            + repelenteFrecuenciaNino + " text, "
            + ministerioFueraNino + " text, "
            + ultimaVezFueraNino + " text, "
            + ministerioDentroNino + " text, "
            + ultimaVezDentroNino + " text, "
            + aplicaAbateNino + " text, "
            + ultimaVezAbateNino + " text, "
            + insecticidaAmbientalNino + " text, "
            + ultimaVezInsecticidaNino + " text, "
            + fiebreAmarillaNino + " text, "
            + fechaFiebreAmarillaNino + " date, "
            + transfusionSangreNino + " text, "
            + fechaTransfusionNino + " date, "
            + paisesFueraNino + " text, "
            + dondePaisAfueraNino + " text, "
            + fueraManaguaNino + " text, "
            + adondeFueraManaguaNino + " text, "
            + vistoMedicoNino + " text, "
            + motivoMedicoNino + " text, "
            + visitaEnfermedadNino + " text, "
            + problemasNino + " text, "
            + problemasOtroNino + " text, "
            + diagnosticadoZikaNino + " text, "
            + fechaZikaNino + " date, "
            + diagnosChikungunyaNino + " text, "
            + fechaChikungunyaNino + " date, "
            + diagnosticadoDengueNino + " text, "
            + fechaDengueNino + " date, "
            + nombreEncuestadorNino + " text, "
            + descripcionProbNeuroNinoUpd + " text, "
            + problemaComerNinoUpd + " text, "

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
