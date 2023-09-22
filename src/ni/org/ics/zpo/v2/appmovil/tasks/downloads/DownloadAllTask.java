package ni.org.ics.zpo.v2.appmovil.tasks.downloads;

import android.content.Context;
import android.util.Log;
import ni.org.ics.zpo.v2.appmovil.database.ZpoAdapter;
import ni.org.ics.zpo.v2.appmovil.domain.*;
import ni.org.ics.zpo.v2.appmovil.tasks.DownloadTask;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;


public class DownloadAllTask extends DownloadTask {
	
	private final Context mContext;
	
	public DownloadAllTask(Context context) {
		mContext = context;
	}
	
	protected static final String TAG = DownloadAllTask.class.getSimpleName();
	private ZpoAdapter zpoA = null;
    private static final String TOTAL_TASK = "19";

    private List<ZpoDatosEmbarazada> mDatosEmb = null;
    private List<Zpo00Screening> mTamizajes = null;
    private List<ZpoEstadoEmbarazada> mStatus = null;
    private List<ZpoInfantData> mInfantData = null;
    private List<ZpoEstadoInfante> mEstadoInfante = null;
    private List<ZpoV2Mullen> mMullen = null;
    private List<ZpoV2RecoleccionMuestra> mMuestras = null;
    private List<ZpoV2EdadesEtapas> mEdadesEtapas = null;
    private List<ZpoV2IndCuidadoFamilia> mIndCuidadoFam = null;
    private List<ZpoV2CuestionarioDemografico> mCuestDemo = null;
    private List<ZpoV2CuestSaludInfantil> mCuestSaInf = null;
    private List<ZpoV2CuestionarioSaludMaterna> mCuestSaMat = null;
    private List<ZpoV2CuestionarioSocioeconomico> mCuestSocioeco = null;
    private List<ZpoV2EvaluacionPsicologica> mEvPsico = null;
    private List<ZpoV2ExamenFisicoInfante> mExFisInf = null;
    private List<ZpoV2FormAudicion> mEvAudi = null;
    private List<ZpoV2EvaluacionVisual> mEvalVisual = null;
    private List<ZpoV2StudyExit> mStudyExit = null;
    private List<ZpoV2CuestVisitaTerreno> mVisTer = null;

    public static final int DAT_MADRE = 1;
    public static final int ESTADO = 2;
    public static final int DAT_INFANTE = 3;
    public static final int ESTADO_INFANTE = 4;
    public static final int TAMIZAJE = 5;
    public static final int MUESTRAS = 6;
    public static final int MULLEN = 7;
    public static final int EDADES_ETAPAS = 8;
    public static final int IND_CUI_FAM = 9;
    public static final int CUEST_DEMO = 10;
    public static final int CUEST_SA_INF = 11;
    public static final int CUEST_SA_MAT = 12;
    public static final int CUEST_SOE = 13;
    public static final int EVAL_PSICO = 14;
    public static final int EX_FIS_INF = 15;
    public static final int EV_AUDI = 16;
    public static final int EV_VIS = 17;
    public static final int ST_EX = 18;
    public static final int VIS_TER = 19;
    
	private String error = null;
	private String url = null;
	private String username = null;
	private String password = null;
    private int v =0;
	
	@Override
	protected String doInBackground(String... values) {
		url = values[0];
		username = values[1];
		password = values[2];

		try {
			error = descargarDatos();
			if (error!=null) return error;
		} catch (Exception e) {
			// Regresa error al descargar
			e.printStackTrace();
			return e.getLocalizedMessage();
		}
		publishProgress("Abriendo base de datos...","1","1");
		zpoA = new ZpoAdapter(mContext, password, false,false);
		zpoA.open();
        //Borrar los datos de la base de datos
        zpoA.borrarZpoDatosEmbarazada();
        zpoA.borrarZpo00Screening();
        zpoA.borrarZpoEstadoMadre();
        zpoA.borrarZpoControlConsentimientosRecepcion();
        zpoA.borrarZpoVisitaFallida();
        zpoA.borrarZpoInfantData();
        zpoA.borrarZpoEstadoInfante();
        zpoA.borrarZpoV2RecoleccionMuestra();
        zpoA.borrarZpoV2Mullen();
        zpoA.borrarZpoV2EdadesEtapas();
        zpoA.borrarZpoV2IndCuidadoFam();
        zpoA.borrarZpoV2CuestDemo();
        zpoA.borrarZpoV2CuestSaludInfantil();
        zpoA.borrarZpoV2CuestSaludMaterna();
        zpoA.borrarZpoV2CuestSocioeco();
        zpoA.borrarZpoV2EvalPsicologica();
        zpoA.borrarZpoV2ExamFisicoInfante();
        zpoA.borrarZpoV2FormAudicion();
        zpoA.borrarZpoV2EvalVisual();
        zpoA.borrarZpoV2StudyExit();
        zpoA.borrarZpoV2CuestVisitaTerreno();
        try {

            if (mDatosEmb != null){
                v = mDatosEmb.size();
                ListIterator<ZpoDatosEmbarazada> iter = mDatosEmb.listIterator();
                while (iter.hasNext()){
                    zpoA.crearZpoDatosEmbarazada(iter.next());
                    publishProgress("Insertando datos de las madres en la base de datos...", Integer.valueOf(iter.nextIndex()).toString(), Integer
                            .valueOf(v).toString());
                }
            }
            if (mStatus != null){
                v = mStatus.size();
                ListIterator<ZpoEstadoEmbarazada> iter = mStatus.listIterator();
                while (iter.hasNext()){
                    zpoA.crearZpoEstadoMadre(iter.next());
                    publishProgress("Insertando estado de las madres en la base de datos...", Integer.valueOf(iter.nextIndex()).toString(), Integer
                            .valueOf(v).toString());
                }
            }
            if (mInfantData != null){
                v = mInfantData.size();
                ListIterator<ZpoInfantData> iter = mInfantData.listIterator();
                while (iter.hasNext()){
                    zpoA.crearZpoInfantData(iter.next());
                    publishProgress("Insertando datos de infantes en la base de datos...", Integer.valueOf(iter.nextIndex()).toString(), Integer
                            .valueOf(v).toString());
                }
            }
            if (mEstadoInfante != null){
                v = mEstadoInfante.size();
                ListIterator<ZpoEstadoInfante> iter = mEstadoInfante.listIterator();
                while (iter.hasNext()){
                    zpoA.crearZpoEstadoInfante(iter.next());
                    publishProgress("Insertando datos de estado de infantes en la base de datos...", Integer.valueOf(iter.nextIndex()).toString(), Integer
                            .valueOf(v).toString());
                }
            }
            if (mTamizajes != null){
                v = mTamizajes.size();
                ListIterator<Zpo00Screening> iter = mTamizajes.listIterator();
                while (iter.hasNext()){
                    zpoA.crearZpo00Screening(iter.next());
                    publishProgress("Insertando tamizajes en la base de datos...", Integer.valueOf(iter.nextIndex()).toString(), Integer
                            .valueOf(v).toString());
                }
            }
            if (mMullen != null){
                v = mMullen.size();
                ListIterator<ZpoV2Mullen> iter = mMullen.listIterator();
                while (iter.hasNext()){
                    zpoA.crearZpoV2Mullen(iter.next());
                    publishProgress("Insertando Evaluaciones Mullen en la base de datos...", Integer.valueOf(iter.nextIndex()).toString(), Integer
                            .valueOf(v).toString());
                }
            }
            if (mMuestras != null){
                v = mMuestras.size();
                ListIterator<ZpoV2RecoleccionMuestra> iter = mMuestras.listIterator();
                while (iter.hasNext()){
                    zpoA.crearZpoV2RecoleccionMuestra(iter.next());
                    publishProgress("Insertando muestras en la base de datos...", Integer.valueOf(iter.nextIndex()).toString(), Integer
                            .valueOf(v).toString());
                }
            }


            if (mEdadesEtapas != null) {
                v = mEdadesEtapas.size();
                ListIterator<ZpoV2EdadesEtapas> iter = mEdadesEtapas.listIterator();
                while (iter.hasNext()) {
                    zpoA.crearZpoV2EdadesEtapas( iter.next() );
                    publishProgress( "Insertando tamizaje Edades y Etapas...", Integer.valueOf( iter.nextIndex() ).toString(), Integer
                            .valueOf( v ).toString() );
                }
            }

            if (mIndCuidadoFam != null) {
                v = mIndCuidadoFam.size();
                ListIterator<ZpoV2IndCuidadoFamilia> iter = mIndCuidadoFam.listIterator();
                while (iter.hasNext()) {
                    zpoA.crearZpoV2IndCuidadoFamilia(iter.next());
                    publishProgress( "Insertando Encuestas de Indicadores del Cuidado de la Familia...", Integer.valueOf( iter.nextIndex() ).toString(), Integer
                            .valueOf( v ).toString() );
                }
            }

            if (mCuestDemo != null) {
                v = mCuestDemo.size();
                ListIterator<ZpoV2CuestionarioDemografico> iter = mCuestDemo.listIterator();
                while (iter.hasNext()) {
                    zpoA.crearZpoV2CuestDemografico(iter.next());
                    publishProgress( "Insertando Cuestionarios Demográficos...", Integer.valueOf( iter.nextIndex() ).toString(), Integer
                            .valueOf( v ).toString() );
                }
            }

            if (mCuestSaInf != null) {
                v = mCuestSaInf.size();
                ListIterator<ZpoV2CuestSaludInfantil> iter = mCuestSaInf.listIterator();
                while (iter.hasNext()) {
                    zpoA.crearZpoV2CuestSaludInfantil(iter.next());
                    publishProgress( "Insertando Cuestionarios de Salud Infantil...", Integer.valueOf( iter.nextIndex() ).toString(), Integer
                            .valueOf( v ).toString() );
                }
            }

            if (mCuestSaMat != null) {
                v = mCuestSaMat.size();
                ListIterator<ZpoV2CuestionarioSaludMaterna> iter = mCuestSaMat.listIterator();
                while (iter.hasNext()) {
                    zpoA.crearZpoV2CuestSaludMaterna(iter.next());
                    publishProgress( "Insertando Cuestionarios de Salud Materna...", Integer.valueOf( iter.nextIndex() ).toString(), Integer
                            .valueOf( v ).toString() );
                }
            }


            if (mCuestSocioeco != null) {
                v = mCuestSocioeco.size();
                ListIterator<ZpoV2CuestionarioSocioeconomico> iter = mCuestSocioeco.listIterator();
                while (iter.hasNext()) {
                    zpoA.crearZpoV2CuestSocioeco(iter.next());
                    publishProgress( "Insertando Cuestionarios Socioeconomicos...", Integer.valueOf( iter.nextIndex() ).toString(), Integer
                            .valueOf( v ).toString() );
                }
            }

            if (mEvPsico != null) {
                v = mEvPsico.size();
                ListIterator<ZpoV2EvaluacionPsicologica> iter = mEvPsico.listIterator();
                while (iter.hasNext()) {
                    zpoA.crearZpoV2EvalPsicologica(iter.next());
                    publishProgress( "Insertando Evaluaciones Psicológicas...", Integer.valueOf( iter.nextIndex() ).toString(), Integer
                            .valueOf( v ).toString() );
                }
            }


            if (mExFisInf != null) {
                v = mExFisInf.size();
                ListIterator<ZpoV2ExamenFisicoInfante> iter = mExFisInf.listIterator();
                while (iter.hasNext()) {
                    zpoA.crearZpoV2ExaFisicoInfante(iter.next());
                    publishProgress( "Insertando Exámenes Físicos Infantes...", Integer.valueOf( iter.nextIndex() ).toString(), Integer
                            .valueOf( v ).toString() );
                }
            }

            if (mEvAudi != null) {
                v = mEvAudi.size();
                ListIterator<ZpoV2FormAudicion> iter = mEvAudi.listIterator();
                while (iter.hasNext()) {
                    zpoA.crearZpoV2FormAudicion(iter.next());
                    publishProgress( "Insertando Evaluaciones Auditivas...", Integer.valueOf( iter.nextIndex() ).toString(), Integer
                            .valueOf( v ).toString() );
                }
            }

            if (mEvalVisual != null) {
                v = mEvalVisual.size();
                ListIterator<ZpoV2EvaluacionVisual> iter = mEvalVisual.listIterator();
                while (iter.hasNext()) {
                    zpoA.crearZpoV2EvalVisual(iter.next());
                    publishProgress( "Insertando Evaluaciones Visuales...", Integer.valueOf( iter.nextIndex() ).toString(), Integer
                            .valueOf( v ).toString() );
                }
            }

            if (mStudyExit != null) {
                v = mStudyExit.size();
                ListIterator<ZpoV2StudyExit> iter = mStudyExit.listIterator();
                while (iter.hasNext()) {
                    zpoA.crearZpoV2StudyExit(iter.next());
                    publishProgress( "Insertando Salidas de Estudio...", Integer.valueOf( iter.nextIndex() ).toString(), Integer
                            .valueOf( v ).toString() );
                }
            }

            if (mVisTer != null) {
                v = mVisTer.size();
                ListIterator<ZpoV2CuestVisitaTerreno> iter = mVisTer.listIterator();
                while (iter.hasNext()) {
                    zpoA.crearZpoV2CuestVisitaTerreno(iter.next());
                    publishProgress( "Insertando Visitas de Terreno...", Integer.valueOf( iter.nextIndex() ).toString(), Integer
                            .valueOf( v ).toString() );
                }
            }

        } catch (Exception e) {
            // Regresa error al insertar
            e.printStackTrace();
            zpoA.close();
            return e.getLocalizedMessage();
        }
        zpoA.close();
        return error;
    }

    // url, username, password
    protected String descargarDatos() throws Exception {
        try {
            // The URL for making the GET request
            String urlRequest;
            // Set the Accept header for "application/json"
            HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
            HttpHeaders requestHeaders = new HttpHeaders();
            List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
            acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
            requestHeaders.setAccept(acceptableMediaTypes);
            requestHeaders.setAuthorization(authHeader);
            // Populate the headers in an HttpEntity object to use for the request
            HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
            // Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            //Descargar datos de infantes
            urlRequest = url + "/movil/zpDatosEmb";
            publishProgress("Solicitando datos de madres",String.valueOf(DAT_MADRE),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<ZpoDatosEmbarazada[]> responseZpoEmbData = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    ZpoDatosEmbarazada[].class, username);
            // convert the array to a list and return it
            mDatosEmb = Arrays.asList(responseZpoEmbData.getBody());

            //Descargar estado de embarazadas
            urlRequest = url + "/movil/zpoEstadoEmb/{username}";
            publishProgress("Solicitando estado de embarazadas",String.valueOf(ESTADO),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<ZpoEstadoEmbarazada[]> responseZpoEstadoEmbarazada = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    ZpoEstadoEmbarazada[].class, username);
            // convert the array to a list and return it
            mStatus = Arrays.asList(responseZpoEstadoEmbarazada.getBody());

            //Descargar datos de infantes
            urlRequest = url + "/movil/zpoInfants";
            publishProgress("Solicitando datos de infantes",String.valueOf(DAT_INFANTE),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<ZpoInfantData[]> responseZpoInfantData = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    ZpoInfantData[].class, username);
            // convert the array to a list and return it
            mInfantData = Arrays.asList(responseZpoInfantData.getBody());

            //Descargar datos de estado de infantes
            urlRequest = url + "/movil/zpoEstadoInfantes/{username}";
            publishProgress("Solicitando datos de estado de infantes",String.valueOf(ESTADO_INFANTE),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<ZpoEstadoInfante[]> responseZpoEstadoInfante = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    ZpoEstadoInfante[].class, username);
            // convert the array to a list and return it
            mEstadoInfante = Arrays.asList(responseZpoEstadoInfante.getBody());

            //Descargar tamizajes
            urlRequest = url + "/movil/zpo00Screenings/{username}";
            publishProgress("Solicitando tamizajes",String.valueOf(TAMIZAJE),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<Zpo00Screening[]> responseEntityZpo00Screening = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    Zpo00Screening[].class, username);
            // convert the array to a list and return it
            mTamizajes = Arrays.asList(responseEntityZpo00Screening.getBody());

            //Descargar Mullens
            urlRequest = url + "/movil/zpoMullens/";
            publishProgress("Solicitando Evaluaciones Mullen",String.valueOf(MULLEN),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<ZpoV2Mullen[]> responseEntityZpoMullen = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    ZpoV2Mullen[].class, username);
            // convert the array to a list and return it
            mMullen = Arrays.asList(responseEntityZpoMullen.getBody());
            //Descargar muestras
            urlRequest = url + "/movil/zpoV2Muestras";
            publishProgress("Solicitando muestras",String.valueOf(MUESTRAS),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<ZpoV2RecoleccionMuestra[]> responseEntityZpoMx = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    ZpoV2RecoleccionMuestra[].class, username);
            // convert the array to a list and return it
            mMuestras = Arrays.asList(responseEntityZpoMx.getBody());

            //Descargar edades y etapas
            urlRequest = url + "/movil/zpoV2EdadesEtapas";
            publishProgress("Solicitando Tamizaje de Edades y Etapas",String.valueOf(EDADES_ETAPAS),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<ZpoV2EdadesEtapas[]> responseZpoEE = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    ZpoV2EdadesEtapas[].class, username);
            // convert the array to a list and return it
            mEdadesEtapas = Arrays.asList(responseZpoEE.getBody());

            //Descargar encuestas de indicadores de cuidado de familia
            urlRequest = url + "/movil/zpoIndCuidadoFams";
            publishProgress("Solicitando Encuestas de Indicadores de Cuidado de la Familia",String.valueOf(IND_CUI_FAM),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<ZpoV2IndCuidadoFamilia[]> responseZpoIndCuidadoFam = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    ZpoV2IndCuidadoFamilia[].class, username);
            // convert the array to a list and return it
            mIndCuidadoFam = Arrays.asList(responseZpoIndCuidadoFam.getBody());

            //Descargar cuestionarios demograficos
            urlRequest = url + "/movil/zpoCuestDemograficos";
            publishProgress("Solicitando Cuestionarios Demográficos ",String.valueOf(CUEST_DEMO),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<ZpoV2CuestionarioDemografico[]> responseZpoCuestDemo = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    ZpoV2CuestionarioDemografico[].class, username);
            // convert the array to a list and return it
            mCuestDemo = Arrays.asList(responseZpoCuestDemo.getBody());


            //Descargar cuestionarios de Salud Infantil
            urlRequest = url + "/movil/zpoCuestSaludInfantil";
            publishProgress("Solicitando Cuestionarios de Salud Infantil ",String.valueOf(CUEST_SA_INF),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<ZpoV2CuestSaludInfantil[]> responseZpoCuestSaInf = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    ZpoV2CuestSaludInfantil[].class, username);
            // convert the array to a list and return it
            mCuestSaInf = Arrays.asList(responseZpoCuestSaInf.getBody());

            //Descargar cuestionarios de Salud Materna
            urlRequest = url + "/movil/zpoCuestSaludMaterna";
            publishProgress("Solicitando Cuestionarios de Salud Materna ",String.valueOf(CUEST_SA_MAT),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<ZpoV2CuestionarioSaludMaterna[]> responseZpoCuestSaMat = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    ZpoV2CuestionarioSaludMaterna[].class, username);
            // convert the array to a list and return it
            mCuestSaMat = Arrays.asList(responseZpoCuestSaMat.getBody());

            //Descargar cuestionarios socioeconomicos
            urlRequest = url + "/movil/zpoCuestSocioecs";
            publishProgress("Solicitando Cuestionarios Socioeconomicos ",String.valueOf(CUEST_SOE),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<ZpoV2CuestionarioSocioeconomico[]> responseZpoCuestSocioeco = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    ZpoV2CuestionarioSocioeconomico[].class, username);
            // convert the array to a list and return it
            mCuestSocioeco = Arrays.asList(responseZpoCuestSocioeco.getBody());

            //Descargar evaluaciones psicologicas
            urlRequest = url + "/movil/zpoEvalPsicologicas";
            publishProgress("Solicitando Evaluaciones Psicológicas",String.valueOf(EVAL_PSICO),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<ZpoV2EvaluacionPsicologica[]> responseZpoEvalPsico = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    ZpoV2EvaluacionPsicologica[].class, username);
            // convert the array to a list and return it
            mEvPsico = Arrays.asList(responseZpoEvalPsico.getBody());

            //Descargar examenes fisico infantes
            urlRequest = url + "/movil/zpoExamFisicoInfantes";
            publishProgress("Solicitando Exámenes Físicos Infantes",String.valueOf(EX_FIS_INF),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<ZpoV2ExamenFisicoInfante[]> responseZpoExFisInf = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    ZpoV2ExamenFisicoInfante[].class, username);
            // convert the array to a list and return it
            mExFisInf = Arrays.asList(responseZpoExFisInf.getBody());


            //Descargar evaluaciones auditivas
            urlRequest = url + "/movil/zpoFormAudiciones";
            publishProgress("Solicitando Evaluaciones Auditivas",String.valueOf(EV_AUDI),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<ZpoV2FormAudicion[]> responseZpoEvAudi = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    ZpoV2FormAudicion[].class, username);
            // convert the array to a list and return it
            mEvAudi = Arrays.asList(responseZpoEvAudi.getBody());

            //Descargar evaluaciones visuales
            urlRequest = url + "/movil/zpoEvalVisuales";
            publishProgress("Solicitando Evaluaciones Visuales",String.valueOf(EV_VIS),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<ZpoV2EvaluacionVisual[]> responseZpoEvalVis = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    ZpoV2EvaluacionVisual[].class, username);
            // convert the array to a list and return it
            mEvalVisual = Arrays.asList(responseZpoEvalVis.getBody());

            //Descargar salidas de estudio
            urlRequest = url + "/movil/zpoStudyExits";
            publishProgress("Solicitando Salidas de Estudio",String.valueOf(ST_EX),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<ZpoV2StudyExit[]> responseZpoStudyExit = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    ZpoV2StudyExit[].class, username);
            // convert the array to a list and return it
            mStudyExit = Arrays.asList(responseZpoStudyExit.getBody());

            //Descargar visitas de terreno
            urlRequest = url + "/movil/zpoCuestVisitasTerreno";
            publishProgress("Solicitando Visitas de Terreno",String.valueOf(VIS_TER),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<ZpoV2CuestVisitaTerreno[]> responseZpoV2VisTer = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    ZpoV2CuestVisitaTerreno[].class, username);
            // convert the array to a list and return it
            mVisTer = Arrays.asList(responseZpoV2VisTer.getBody());


            return null;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getLocalizedMessage();
        }
    }
}
