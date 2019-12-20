package ni.org.ics.zpo.v2.appmovil.utils;

import android.net.Uri;


/**
 * Constantes usadas en multiples clases de la aplicacion
 * 
 * @author William Aviles
 * 
 */
public class Constants {
	
	
	// status for records
    public static final String STATUS_SUBMITTED = "enviado";
    public static final String STATUS_NOT_SUBMITTED = "no enviado";
    public static final String STATUS_NOT_COMPLETED = "incompleto";
    
    
    //Providers
	public static final String AUTHORITY = "org.odk.collect.android.provider.odk.forms";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/forms");
	public static final String AUTHORITY_I = "org.odk.collect.android.provider.odk.instances";
	public static final Uri CONTENT_URI_I = Uri.parse("content://" + AUTHORITY_I + "/instances");
	
	//nombres de objetos
	public static final String TITLE = "titulo";
	public static final String OBJECTO_ZP00 = "zp00";
    public static final String OBJECTO_ZP02 = "zp02";
    public static final String OBJECTO_ZP07 = "zp07";
	public static final String OBJECTO_ZPO07OtoE = "zpo07OtoE";
	public static final String OBJECTO_ZPOMULLEN = "zpoMullen";
	public static final String OBJECT_ZPOEDADESETAPAS = "zpoEdadesEtapas";
	public static final String OBJECT_INDCUIFAM = "zpoIndCuidadoFamilia";
	public static final String OBJECT_CUEST_DEMO = "zpoCuestDemografico";
	public static final String OBJECT_CUEST_SA_INF = "zpoCuestSaludInfantil";
	public static final String OBJECT_CUEST_SA_MAT = "zpoCuestSaludMaterna";
	public static final String OBJECT_CUEST_SOE = "zpoCuestSocioeconomico";
	public static final String OBJECT_EVAL_PSICO = "zpoEvaluacionPsicologica";
	public static final String OBJECT_EX_FIS_INF = "zpoExamenFisicoInfante";
	public static final String OBJECT_FORM_AUDI = "zpoFormAudicion";
	public static final String OBJECT_EVAL_VIS = "zpoEvaluacionVisual";

	public static final String OBJECTO_ZPEST = "zpestado";
	public static final String OBJECTO_ZPDATA = "zpdatos";
	public static final String DONE = "hecho";
	
	public static final String OBJECTO_ZPINFDATA = "zpdatosinfante";
	public static final String OBJECTO_ZPESTINF = "zpestadoinfante";
	
	//Eventos
	public static final String EVENT = "event";
	public static final String SCREENING = "screening_arm_1";
	public static final String ENTRY = "entry_arm_1";
	public static final String EXIT = "study_exit_arm_1";
	
	public static final String MONTH24 = "24_months_visit";
    public static final String MONTH36 = "36_months_visit";
    public static final String MONTH48 = "48_months_visit";
    public static final String MONTH60 = "60_months_visit";
    public static final String MONTH72 = "72_months_visit";
    public static final String MONTH84 = "84_months_visit";
    //llamadas
    public static final String MONTH30 = "30_months_call";
    public static final String MONTH42 = "42_months_call";
    public static final String MONTH54 = "54_months_call";
    public static final String MONTH66 = "66_months_call";
    public static final String MONTH78 = "78_months_call";

    public static final String RECORDID = "recordId";
	public static final String UNSHED1 = "unscheduled_visit_1";
	public static final String UNSHED2 = "unscheduled_visit_2";
	public static final String UNSHED3 = "unscheduled_visit_3";

}
