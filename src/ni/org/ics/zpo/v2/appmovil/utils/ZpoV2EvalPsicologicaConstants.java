package ni.org.ics.zpo.v2.appmovil.utils;

/**
 * @author ics
 */
public class ZpoV2EvalPsicologicaConstants {

    //Tabla ZpoV2EvaluacionPsicologica
    public static final String EVAL_PSICO_TABLE = "zpo_eval_psicologica";

    //Campos comunes
    public static final String recordId = "recordId";
    public static final String eventName = "eventName";

    //Campos ZpoV2EvaluacionPsicologica
    public static final String fechaPsych = "fechaPsych";
    public static final String trabajarPsych = "trabajarPsych";
    public static final String cocinarPsych = "cocinarPsych";
    public static final String mercadoPsych = "mercadoPsych";
    public static final String banarHijoPsych = "banarHijoPsych";
    public static final String vestirHijoPsych = "vestirHijoPsych";
    public static final String limpiarPsych = "limpiarPsych";
    public static final String lavarRopaPsych = "lavarRopaPsych";
    public static final String banarsePsych = "banarsePsych";
    public static final String cuidarCabelloPsych = "cuidarCabelloPsych";
    public static final String atenderVisitaPsych = "atenderVisitaPsych";
    public static final String lavarDientesPsych = "lavarDientesPsych";
    public static final String usarRopaLimpiaPsych = "usarRopaLimpiaPsych";
    public static final String juntarMujeresPsych = "juntarMujeresPsych";
    public static final String ayudarAmigasPsych = "ayudarAmigasPsych";
    public static final String compartirInfoPsych = "compartirInfoPsych";
    public static final String tareasSaludPsych = "tareasSaludPsych";
    public static final String funcionamientoPuntajePsych = "funcionamientoPuntajePsych";
    public static final String sinEnergiaPsych = "sinEnergiaPsych";
    public static final String culparseMismaPsych = "culparseMismaPsych";
    public static final String llorarPsych = "llorarPsych";
    public static final String probConcentPsych = "probConcentPsych";
    public static final String faltaApetitoPsych = "faltaApetitoPsych";
    public static final String difficulDormirPsych = "difficulDormirPsych";
    public static final String sinEsperanzaPsych = "sinEsperanzaPsych";
    public static final String tristePsych = "tristePsych";
    public static final String solaPsych = "solaPsych";
    public static final String acabarVidaPsych = "acabarVidaPsych";
    public static final String preocuparsePsych = "preocuparsePsych";
    public static final String noInteresPsych = "noInteresPsych";
    public static final String todoEsfuerzoPsych = "todoEsfuerzoPsych";
    public static final String sienteNoValePsych = "sienteNoValePsych";
    public static final String noInteresSexoPsych = "noInteresSexoPsych";
    public static final String asustaPsych = "asustaPsych";
    public static final String sienteMiedoPsych = "sienteMiedoPsych";
    public static final String debilidadPsych = "debilidadPsych";
    public static final String nerviosPsych = "nerviosPsych";
    public static final String palpitacionesPsych = "palpitacionesPsych";
    public static final String tiemblaPsych = "tiemblaPsych";
    public static final String sienteTensaPsych = "sienteTensaPsych";
    public static final String dolorCabezaPsych = "dolorCabezaPsych";
    public static final String panicoPsych = "panicoPsych";
    public static final String inquietudPsych = "inquietudPsych";
    public static final String sintomasPuntajePsych = "sintomasPuntajePsych";
    public static final String scoreDepressionPsych = "scoreDepressionPsych";
    public static final String examinadorPsych = "examinadorPsych";

    //Crear tabla ZpoV2EvaluacionPsicologica
    public static final String CREATE_CEVPSICO_TABLE = "create table if not exists "
            + EVAL_PSICO_TABLE + " ("
            + recordId + " text not null, "
            + eventName + " text, "
            + fechaPsych + " date, "
            + trabajarPsych + " text, "
            + cocinarPsych + " text, "
            + mercadoPsych + " text, "
            + banarHijoPsych + " text, "
            + vestirHijoPsych + " text, "
            + limpiarPsych + " text, "
            + lavarRopaPsych + " text, "
            + banarsePsych + " text, "
            + cuidarCabelloPsych + " text, "
            + atenderVisitaPsych + " text, "
            + lavarDientesPsych + " text, "
            + usarRopaLimpiaPsych + " text, "
            + juntarMujeresPsych + " text, "
            + ayudarAmigasPsych + " text, "
            + compartirInfoPsych + " text, "
            + tareasSaludPsych + " text, "
            + funcionamientoPuntajePsych + " integer,"
            + sinEnergiaPsych + " text, "
            + culparseMismaPsych + " text, "
            + llorarPsych + " text, "
            + probConcentPsych + " text, "
            + faltaApetitoPsych + " text, "
            + difficulDormirPsych + " text, "
            + sinEsperanzaPsych + " text, "
            + tristePsych + " text, "
            + solaPsych + " text, "
            + acabarVidaPsych + " text, "
            + preocuparsePsych + " text, "
            + noInteresPsych + " text, "
            + todoEsfuerzoPsych + " text, "
            + sienteNoValePsych + " text, "
            + noInteresSexoPsych + " text, "
            + asustaPsych + " text, "
            + sienteMiedoPsych + " text, "
            + debilidadPsych + " text, "
            + nerviosPsych + " text, "
            + palpitacionesPsych + " text, "
            + tiemblaPsych + " text, "
            + sienteTensaPsych + " text, "
            + dolorCabezaPsych + " text, "
            + panicoPsych + " text, "
            + inquietudPsych + " text, "
            + sintomasPuntajePsych + " integer,"
            + scoreDepressionPsych + " real,"
            + examinadorPsych + " text, "

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
