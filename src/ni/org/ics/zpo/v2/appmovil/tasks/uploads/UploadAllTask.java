package ni.org.ics.zpo.v2.appmovil.tasks.uploads;

import android.content.Context;
import android.util.Log;
import ni.org.ics.zpo.v2.appmovil.database.ZpoAdapter;
import ni.org.ics.zpo.v2.appmovil.domain.*;
import ni.org.ics.zpo.v2.appmovil.listeners.UploadListener;
import ni.org.ics.zpo.v2.appmovil.tasks.UploadTask;
import ni.org.ics.zpo.v2.appmovil.utils.Constants;
import ni.org.ics.zpo.v2.appmovil.utils.MainDBConstants;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class UploadAllTask extends UploadTask {
	
	private final Context mContext;

	public UploadAllTask(Context context) {
		mContext = context;
	}

	protected static final String TAG = UploadAllTask.class.getSimpleName();
    private static final String TOTAL_TASK = "17";
	private ZpoAdapter zpoA = null;

    private List<Zpo00Screening> mTamizajes = new ArrayList<Zpo00Screening>();
    private List<ZpoEstadoEmbarazada> mStatus = new ArrayList<ZpoEstadoEmbarazada>();
    private List<ZpoInfantData> mInfantData = new ArrayList<ZpoInfantData>();
    private List<ZpoEstadoInfante> mEstadoInfante = new ArrayList<ZpoEstadoInfante>();
    private List<ZpoV2Mullen> mMullen = new ArrayList<ZpoV2Mullen>();
    private List<ZpoV2RecoleccionMuestra> mMuestras = new ArrayList<ZpoV2RecoleccionMuestra>();
    private List<ZpoV2InfantOphthalmologicEvaluation> mOphthaEvals= new ArrayList<ZpoV2InfantOphthalmologicEvaluation>();
    private List<ZpoV2InfantPsychologicalEvaluation> mPsychoEvals = new ArrayList<ZpoV2InfantPsychologicalEvaluation>();
    private List<ZpoV2InfantOtoacousticEmissions> mOtoacusEms = new ArrayList<ZpoV2InfantOtoacousticEmissions>();
    private List<ZpoV2InfantOphtResults> mAInfantOphtResults = new ArrayList<ZpoV2InfantOphtResults>();
    private List<ZpoControlConsentimientosRecepcion> mRecepcionesCons = new ArrayList<ZpoControlConsentimientosRecepcion>();
    private List<ZpoV2EdadesEtapas> mEdadesEtapas = new ArrayList<ZpoV2EdadesEtapas>();
    private List<ZpoV2IndCuidadoFamilia> mIndCuidadoFam = new ArrayList<ZpoV2IndCuidadoFamilia>();
    private List<ZpoV2CuestionarioDemografico> mCuestDemo = new ArrayList<ZpoV2CuestionarioDemografico>();
    private List<ZpoV2CuestSaludInfantil> mCuestSaInf = new ArrayList<ZpoV2CuestSaludInfantil>();
    private List<ZpoV2CuestionarioSaludMaterna> mCuestSaMat = new ArrayList<ZpoV2CuestionarioSaludMaterna>();
    private List<ZpoV2CuestionarioSocioeconomico> mCuestSocie = new ArrayList<ZpoV2CuestionarioSocioeconomico>();

	private String url = null;
	private String username = null;
	private String password = null;
	private String error = null;
	protected UploadListener mStateListener;

    public static final int TAMIZAJE = 1;
    public static final int ESTADO = 2;
    public static final int DAT_INFANTE = 3;
    public static final int ESTADO_INFANTE = 4;
    public static final int OPHTH_RESULTS = 5;
    public static final int MUESTRAS = 6;
    public static final int CONSREC = 7;
    public static final int OTO_EMI = 8;
    public static final int MULLEN = 9;
    public static final int OFTA_EVAL = 10;
    public static final int PSICO_EVAL = 11;
    public static final int EDADES_ETAPAS = 12;
    public static final int IND_CUIDADO_FAM = 13;
    public static final int CUEST_DEMO = 14;
    public static final int CUEST_SA_INF = 15;
    public static final int CUEST_SA_MAT = 16;
    public static final int CUEST_SOE = 17;


    @Override
	protected String doInBackground(String... values) {
		url = values[0];
		username = values[1];
		password = values[2];

		try {
			publishProgress("Obteniendo registros de la base de datos", "1", "2");
			zpoA = new ZpoAdapter(mContext, password, false,false);
			zpoA.open();

            String filtro = MainDBConstants.STATUS + "='" + Constants.STATUS_NOT_SUBMITTED + "'";            
            mTamizajes = zpoA.getZpo00Screenings(filtro, MainDBConstants.recordId);
            mStatus = zpoA.getZpoEstadoMadres(filtro, MainDBConstants.recordId);
            mInfantData = zpoA.getZpoInfantDatas(filtro,null);
            mEstadoInfante = zpoA.getZpoEstadoInfantes(filtro, MainDBConstants.recordId);
            mMullen = zpoA.getZpoV2Mullens(filtro,MainDBConstants.recordId);
            mMuestras = zpoA.getZpoV2RecoleccionMuestras(filtro, MainDBConstants.recordId);
            mOtoacusEms = zpoA.getZpoInfantOtoacousticEms(filtro, MainDBConstants.recordId);
            mOphthaEvals = zpoA.getZpoV2InfantOphthalmologicEvaluations(filtro, MainDBConstants.recordId);
            mPsychoEvals = zpoA.getZpoV2InfantPsychologicalEvaluations(filtro, MainDBConstants.recordId);
            mAInfantOphtResults = zpoA.getZpoV2InfantOphtResults(filtro, MainDBConstants.recordId);
            mRecepcionesCons = zpoA.getZpoControlConsentimientosRecepciones(filtro, null);
            mEdadesEtapas = zpoA.getZpoV2EEs(filtro,null);
            mIndCuidadoFam = zpoA.getZpoV2IndCuidadoFams(filtro, null);
            mCuestDemo = zpoA.getZpoV2CuestDemograficos( filtro, null);
            mCuestSaInf = zpoA.getZpoV2CuestSaludInfantils( filtro, null);
            mCuestSaMat = zpoA.getZpoV2CuestSaludMats( filtro, null);
            mCuestSocie = zpoA.getZpoV2CuestSocioecos(filtro, null);

			publishProgress("Datos completos!", "2", "2");
            actualizarBaseDatos(Constants.STATUS_SUBMITTED, TAMIZAJE);
            error = cargarTamizajes(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, TAMIZAJE);
                return error;
            }
            actualizarBaseDatos(Constants.STATUS_SUBMITTED, ESTADO);
            error = uploadStatusPreg(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, ESTADO);
                return error;
            }
            actualizarBaseDatos(Constants.STATUS_SUBMITTED, DAT_INFANTE);
            error = uploadInfantData(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, DAT_INFANTE);
                return error;
            }
            actualizarBaseDatos(Constants.STATUS_SUBMITTED, ESTADO_INFANTE);
            error = uploadInfantStatus(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, ESTADO_INFANTE);
                return error;
            }
            actualizarBaseDatos(Constants.STATUS_SUBMITTED, MULLEN);
            error = cargarMullens(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, MULLEN);
                return error;
            }
            actualizarBaseDatos(Constants.STATUS_SUBMITTED, MUESTRAS);
            error = uploadMuestras(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, MUESTRAS);
                return error;
            }
            actualizarBaseDatos(Constants.STATUS_SUBMITTED, OTO_EMI);
            error = uploadOtoacousticEmissions(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, OTO_EMI);
                return error;
            }
            actualizarBaseDatos(Constants.STATUS_SUBMITTED, OFTA_EVAL);
            error = uploadInfantOphthaEvals(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, OFTA_EVAL);
                return error;
            }
            actualizarBaseDatos(Constants.STATUS_SUBMITTED, PSICO_EVAL);
            error = uploadInfantPsychoEvals(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, PSICO_EVAL);
                return error;
            }
            actualizarBaseDatos(Constants.STATUS_SUBMITTED, OPHTH_RESULTS);
            error = uploadInfantOphtResults(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, OPHTH_RESULTS);
                return error;
            }
            actualizarBaseDatos(Constants.STATUS_SUBMITTED, CONSREC);
            error = uploadControlConsentimientosRecepcion(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, CONSREC);
                return error;
            }
            actualizarBaseDatos(Constants.STATUS_SUBMITTED, EDADES_ETAPAS);
            error = uploadEdadesEtapas(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, EDADES_ETAPAS);
                return error;
            }
            actualizarBaseDatos(Constants.STATUS_SUBMITTED, IND_CUIDADO_FAM);
            error = uploadIndCuidadoFam(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, IND_CUIDADO_FAM);
                return error;
            }
            actualizarBaseDatos(Constants.STATUS_SUBMITTED, CUEST_DEMO);
            error = uploadCuestDemo(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, CUEST_DEMO);
                return error;
            }

            actualizarBaseDatos(Constants.STATUS_SUBMITTED, CUEST_SA_INF);
            error = uploadCuestSaInf(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, CUEST_SA_INF);
                return error;
            }

            actualizarBaseDatos(Constants.STATUS_SUBMITTED, CUEST_SA_MAT);
            error = uploadCuestSaMat(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, CUEST_SA_MAT);
                return error;
            }

            actualizarBaseDatos(Constants.STATUS_SUBMITTED, CUEST_SOE);
            error = uploadCuestSocioeco(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, CUEST_SOE);
                return error;
            }

            zpoA.close();
		} catch (Exception e1) {
			zpoA.close();
			e1.printStackTrace();
			return e1.getLocalizedMessage();
		}
		return error;
	}

    private void actualizarBaseDatos(String estado, int opcion) {
        int c;
       if(opcion==TAMIZAJE){
            c = mTamizajes.size();
            if(c>0){
                for (Zpo00Screening tamizaje : mTamizajes) {
                    tamizaje.setEstado(estado);
                    zpoA.editarZpo00Screening(tamizaje);
                    publishProgress("Actualizando tamizajes en base de datos local", Integer.valueOf(mTamizajes.indexOf(tamizaje)).toString(), Integer
                            .valueOf(c).toString());
                }
            }
        }

        else if(opcion==MULLEN){
            c = mMullen.size();
            if(c>0){
                for (ZpoV2Mullen mullen : mMullen) {
                    mullen.setEstado(estado);
                    zpoA.editarZpoV2Mullen(mullen);
                    publishProgress("Actualizando Evaluaciones Mullen base de datos local", Integer.valueOf(mMullen.indexOf(mullen)).toString(), Integer
                            .valueOf(c).toString());
                }
            }
        }
        else if(opcion==MUESTRAS){
            c = mMuestras.size();
            if(c>0){
                for (ZpoV2RecoleccionMuestra muestra : mMuestras) {
                    muestra.setEstado(estado);
                    zpoA.editarZpoV2RecoleccionMuestra(muestra);
                    publishProgress("Actualizando muestras en base de datos local", Integer.valueOf(mMuestras.indexOf(muestra)).toString(), Integer
                            .valueOf(c).toString());
                }
            }
        }
        else if(opcion==OTO_EMI){
            c = mOtoacusEms.size();
            if(c>0){
                for (ZpoV2InfantOtoacousticEmissions otoacousticEmissions : mOtoacusEms) {
                    otoacousticEmissions.setEstado(estado);
                    zpoA.editarZpoInfantOtoacousticEm(otoacousticEmissions);
                    publishProgress("Actualizando eval otoacústicas en base de datos local", Integer.valueOf(mOtoacusEms.indexOf(otoacousticEmissions)).toString(), Integer
                            .valueOf(c).toString());
                }
            }
        }
        else if(opcion==OFTA_EVAL){
            c = mOphthaEvals.size();
            if(c>0){
                for (ZpoV2InfantOphthalmologicEvaluation ophthalmologicEvaluation : mOphthaEvals) {
                    ophthalmologicEvaluation.setEstado(estado);
                    zpoA.editarZpoV2InfantOphthalmologicEvaluation(ophthalmologicEvaluation);
                    publishProgress("Actualizando eval oftalmológicas en base de datos local", Integer.valueOf(mOphthaEvals.indexOf(ophthalmologicEvaluation)).toString(), Integer
                            .valueOf(c).toString());
                }
            }
        }
        else if(opcion==PSICO_EVAL){
            c = mPsychoEvals.size();
            if(c>0){
                for (ZpoV2InfantPsychologicalEvaluation psychologicalEvaluation : mPsychoEvals) {
                    psychologicalEvaluation.setEstado(estado);
                    zpoA.editarZpoV2InfantPsychologicalEvaluation(psychologicalEvaluation);
                    publishProgress("Actualizando eval psicológicas en base de datos local", Integer.valueOf(mPsychoEvals.indexOf(psychologicalEvaluation)).toString(), Integer
                            .valueOf(c).toString());
                }
            }
        }
        else if(opcion==OPHTH_RESULTS){
            c = mAInfantOphtResults.size();
            if(c>0){
                for (ZpoV2InfantOphtResults aInfantOphtResult : mAInfantOphtResults) {
                    aInfantOphtResult.setEstado(estado);
                    zpoA.editarZpoV2InfantOphtResults(aInfantOphtResult);
                    publishProgress("Actualizando resultados oftalmologicos de infantes de base de datos local", Integer.valueOf(mAInfantOphtResults.indexOf(aInfantOphtResult)).toString(), Integer
                            .valueOf(c).toString());
                }
            }
        }
       else if(opcion==EDADES_ETAPAS){
           c = mEdadesEtapas.size();
           if(c>0){
               for (ZpoV2EdadesEtapas ee : mEdadesEtapas) {
                   ee.setEstado(estado);
                   zpoA.editarZpoV2EdadesEtapas(ee);
                   publishProgress("Actualizando Edades y Etapas de base de datos local", Integer.valueOf(mEdadesEtapas.indexOf(ee)).toString(), Integer
                           .valueOf(c).toString());
               }
           }
       }
       else if(opcion==IND_CUIDADO_FAM){
           c = mIndCuidadoFam.size();
           if(c>0){
               for (ZpoV2IndCuidadoFamilia icf : mIndCuidadoFam) {
                   icf.setEstado(estado);
                   zpoA.editarZpoV2IndCuidadoFam(icf);
                   publishProgress("Actualizando Encuestas de Indicadores de la Familia de base de datos local", Integer.valueOf(mIndCuidadoFam.indexOf(icf)).toString(), Integer
                           .valueOf(c).toString());
               }
           }
       }

       else if(opcion==CUEST_DEMO){
           c = mCuestDemo.size();
           if(c>0){
               for (ZpoV2CuestionarioDemografico cDemo : mCuestDemo) {
                   cDemo.setEstado(estado);
                   zpoA.editarZpoV2CuestDemo(cDemo);
                   publishProgress("Actualizando Cuestionarios Demográficos de base de datos local", Integer.valueOf(mCuestDemo.indexOf(cDemo)).toString(), Integer
                           .valueOf(c).toString());
               }
           }
       }


       else if(opcion==CUEST_SA_INF){
           c = mCuestSaInf.size();
           if(c>0){
               for (ZpoV2CuestSaludInfantil cSaInf : mCuestSaInf) {
                   cSaInf.setEstado(estado);
                   zpoA.editarZpoV2CuestSaludInfantil(cSaInf);
                   publishProgress("Actualizando Cuestionarios de Salud Infantil de base de datos local", Integer.valueOf(mCuestSaInf.indexOf(cSaInf)).toString(), Integer
                           .valueOf(c).toString());
               }
           }
       }


       else if(opcion==CUEST_SA_MAT){
           c = mCuestSaMat.size();
           if(c>0){
               for (ZpoV2CuestionarioSaludMaterna cSaMat : mCuestSaMat) {
                   cSaMat.setEstado(estado);
                   zpoA.editarZpoV2CuestSaludMaterna(cSaMat);
                   publishProgress("Actualizando Cuestionarios de Salud Materna de base de datos local", Integer.valueOf( mCuestSaMat.indexOf(cSaMat)).toString(), Integer
                           .valueOf(c).toString());
               }
           }
       }

       else if(opcion==CUEST_SOE){
           c = mCuestSocie.size();
           if(c>0){
               for (ZpoV2CuestionarioSocioeconomico cSoe : mCuestSocie) {
                   cSoe.setEstado(estado);
                   zpoA.editarZpoV2CuestSocioeco(cSoe);
                   publishProgress("Actualizando Cuestionarios Socioeconomicos de base de datos local", Integer.valueOf( mCuestSocie.indexOf(cSoe)).toString(), Integer
                           .valueOf(c).toString());
               }
           }
       }

    }


    /***************************************************/
    /********************* Zp00Tamizajes ************************/
    /***************************************************/
    // url, username, password
    protected String cargarTamizajes(String url, String username,
                                     String password) throws Exception {
        try {
            if(mTamizajes.size()>0){
                publishProgress("Enviando tamizajes!", String.valueOf(TAMIZAJE), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/zpo00Screenings";
                Zpo00Screening[] envio = mTamizajes.toArray(new Zpo00Screening[mTamizajes.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<Zpo00Screening[]> requestEntity =
                        new HttpEntity<Zpo00Screening[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la vivienda y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }


    /***************************************************/
    /********************* ZpEstadosEmbarazadas ************************/
    /***************************************************/
    // url, username, password
    protected String uploadStatusPreg(String url, String username,
                                      String password) throws Exception {
        try {
            if(mStatus.size()>0){
                publishProgress("Enviando estados madres!", String.valueOf(ESTADO), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/zpoEstadoEmb";
                ZpoEstadoEmbarazada[] envio = mStatus.toArray(new ZpoEstadoEmbarazada[mStatus.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<ZpoEstadoEmbarazada[]> requestEntity =
                        new HttpEntity<ZpoEstadoEmbarazada[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la vivienda y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }


    /*******INFANTES*********/

    /***************************************************/
    /********************* ZpoInfantData******/
    /***************************************************/
    // url, username, password
    protected String uploadInfantData(String url, String username,
                                      String password) throws Exception {
        try {
            if(mInfantData.size()>0){
                publishProgress("Enviando datos de infantes!", String.valueOf(DAT_INFANTE), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/zpoInfants";
                ZpoInfantData[] envio = mInfantData.toArray(new ZpoInfantData[mInfantData.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<ZpoInfantData[]> requestEntity =
                        new HttpEntity<ZpoInfantData[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la vivienda y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }

    /***************************************************/
    /********************* ZpoEstadoInfante******/
    /***************************************************/
    // url, username, password
    protected String uploadInfantStatus(String url, String username,
                                        String password) throws Exception {
        try {
            if(mEstadoInfante.size()>0){
                publishProgress("Enviando datos de estado de infantes!", String.valueOf(ESTADO_INFANTE), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/zpoEstadoInfantes";
                ZpoEstadoInfante[] envio = mEstadoInfante.toArray(new ZpoEstadoInfante[mEstadoInfante.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<ZpoEstadoInfante[]> requestEntity =
                        new HttpEntity<ZpoEstadoInfante[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la vivienda y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }

    /***************************************************/
    /********************* ZpoV2RecoleccionMuestra******/
    /***************************************************/
    // url, username, password
    protected String uploadMuestras(String url, String username,
                                        String password) throws Exception {
        try {
            if(mMuestras.size()>0){
                publishProgress("Enviando muestras!", String.valueOf(MUESTRAS), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/zpoV2Muestras";
                ZpoV2RecoleccionMuestra[] envio = mMuestras.toArray(new ZpoV2RecoleccionMuestra[mMuestras.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<ZpoV2RecoleccionMuestra[]> requestEntity =
                        new HttpEntity<ZpoV2RecoleccionMuestra[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la vivienda y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }

    /***************************************************/
    /********************* ZpoV2InfantOtoacousticEmissions******/
    /***************************************************/
    // url, username, password
    protected String uploadOtoacousticEmissions(String url, String username,
                                    String password) throws Exception {
        try {
            if(mOtoacusEms.size()>0){
                publishProgress("Enviando evaluación emisiones otoacústicas!", String.valueOf(OTO_EMI), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/zpoInfantOtoacousticEms";
                ZpoV2InfantOtoacousticEmissions[] envio = mOtoacusEms.toArray(new ZpoV2InfantOtoacousticEmissions[mOtoacusEms.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<ZpoV2InfantOtoacousticEmissions[]> requestEntity =
                        new HttpEntity<ZpoV2InfantOtoacousticEmissions[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la vivienda y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }

    /***************************************************/
    /********************* ZpoV2InfantOphthalmologicEvaluation******/
    /***************************************************/
    // url, username, password
    protected String uploadInfantOphthaEvals(String url, String username,
                                                String password) throws Exception {
        try {
            if(mOphthaEvals.size()>0){
                publishProgress("Enviando evaluaciones oftalmológicas!", String.valueOf(OFTA_EVAL), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/zpoV2InfantOphthaEvals";
                ZpoV2InfantOphthalmologicEvaluation[] envio = mOphthaEvals.toArray(new ZpoV2InfantOphthalmologicEvaluation[mOphthaEvals.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<ZpoV2InfantOphthalmologicEvaluation[]> requestEntity =
                        new HttpEntity<ZpoV2InfantOphthalmologicEvaluation[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la vivienda y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }

    /***************************************************/
    /********************* ZpoV2InfantPsychologicalEvaluation******/
    /***************************************************/
    // url, username, password
    protected String uploadInfantPsychoEvals(String url, String username,
                                             String password) throws Exception {
        try {
            if(mPsychoEvals.size()>0){
                publishProgress("Enviando evaluaciones oftalmológicas!", String.valueOf(PSICO_EVAL), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/zpoV2InfantPsychoEvals";
                ZpoV2InfantPsychologicalEvaluation[] envio = mPsychoEvals.toArray(new ZpoV2InfantPsychologicalEvaluation[mPsychoEvals.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<ZpoV2InfantPsychologicalEvaluation[]> requestEntity =
                        new HttpEntity<ZpoV2InfantPsychologicalEvaluation[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la vivienda y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }

    /***************************************************/
    /********************* ZpoV2Mullen ************************/
    /***************************************************/
    // url, username, password
    protected String cargarMullens(String url, String username,
                                     String password) throws Exception {
        try {
            if(mMullen.size()>0){
                publishProgress("Enviando Evaluaciones Mullen!", String.valueOf(MULLEN), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/zpoMullens";
                ZpoV2Mullen[] envio = mMullen.toArray(new ZpoV2Mullen[mMullen.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<ZpoV2Mullen[]> requestEntity =
                        new HttpEntity<ZpoV2Mullen[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la vivienda y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }

    /***************************************************/
    /********************* ZpoV2InfantOphtResults******/
    /***************************************************/
    // url, username, password
    protected String uploadInfantOphtResults(String url, String username,
                                             String password) throws Exception {
        try {
            if( mAInfantOphtResults.size()>0){
                publishProgress("Enviando resultado oftalmologico infantes!", String.valueOf(OPHTH_RESULTS), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/zpoV2InfantOphtResults";
                ZpoV2InfantOphtResults[] envio = mAInfantOphtResults.toArray(new ZpoV2InfantOphtResults[mAInfantOphtResults.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<ZpoV2InfantOphtResults[]> requestEntity =
                        new HttpEntity<ZpoV2InfantOphtResults[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la vivienda y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }

    /***************************************************/
    /********************* ZpoControlConsentimientosRecepcion******/
    /***************************************************/
    // url, username, password
    protected String uploadControlConsentimientosRecepcion(String url, String username,
                                                           String password) throws Exception {
        try {
            if(mRecepcionesCons.size()>0){
                publishProgress("Enviando recepciones de consentimientos!", String.valueOf(CONSREC), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/zpoRecepcionCons";
                ZpoControlConsentimientosRecepcion[] envio = mRecepcionesCons.toArray(new ZpoControlConsentimientosRecepcion[mRecepcionesCons.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<ZpoControlConsentimientosRecepcion[]> requestEntity =
                        new HttpEntity<ZpoControlConsentimientosRecepcion[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la vivienda y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }

    /***************************************************/
    /********************* ZpoV2EdadesEtapas******/
    /***************************************************/
    // url, username, password
    protected String uploadEdadesEtapas(String url, String username,
                                                           String password) throws Exception {
        try {
            if(mEdadesEtapas.size()>0){
                publishProgress("Enviando Tamizaje de Edades y Etapas!", String.valueOf(EDADES_ETAPAS), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/zpoV2EE";
                ZpoV2EdadesEtapas[] envio = mEdadesEtapas.toArray(new ZpoV2EdadesEtapas[mEdadesEtapas.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<ZpoV2EdadesEtapas[]> requestEntity =
                        new HttpEntity<ZpoV2EdadesEtapas[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la vivienda y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }


    /***************************************************/
    /********************* ZpoV2IndicadoresCuidadoFamilia******/
    /***************************************************/
    // url, username, password
    protected String uploadIndCuidadoFam(String url, String username,
                                        String password) throws Exception {
        try {
            if(mIndCuidadoFam.size()>0){
                publishProgress("Enviando Encuestas de Indicadores del Cuidado de la Familia!", String.valueOf(IND_CUIDADO_FAM), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/saveZpoIndCuidadoFams";
                ZpoV2IndCuidadoFamilia[] envio = mIndCuidadoFam.toArray(new ZpoV2IndCuidadoFamilia[mIndCuidadoFam.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<ZpoV2IndCuidadoFamilia[]> requestEntity =
                        new HttpEntity<ZpoV2IndCuidadoFamilia[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la vivienda y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }


    /***************************************************/
    /********************* ZpoV2CuestionarioDemografico******/
    /***************************************************/
    // url, username, password
    protected String uploadCuestDemo(String url, String username,
                                         String password) throws Exception {
        try {
            if(mCuestDemo.size()>0){
                publishProgress("Enviando Cuestionarios Demográficos!", String.valueOf(CUEST_DEMO), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/saveCuestDemograficos";
                ZpoV2CuestionarioDemografico[] envio = mCuestDemo.toArray(new ZpoV2CuestionarioDemografico[mCuestDemo.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<ZpoV2CuestionarioDemografico[]> requestEntity =
                        new HttpEntity<ZpoV2CuestionarioDemografico[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la vivienda y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }


    /***************************************************/
    /********************* ZpoV2CuestSaludInfantil******/
    /***************************************************/
    // url, username, password
    protected String uploadCuestSaInf(String url, String username,
                                     String password) throws Exception {
        try {
            if(mCuestSaInf.size()>0){
                publishProgress("Enviando Cuestionarios de Salud Infantil!", String.valueOf(CUEST_SA_INF), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/saveZpoCuestSaludInfantil";
                ZpoV2CuestSaludInfantil[] envio = mCuestSaInf.toArray(new ZpoV2CuestSaludInfantil[mCuestSaInf.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<ZpoV2CuestSaludInfantil[]> requestEntity =
                        new HttpEntity<ZpoV2CuestSaludInfantil[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la vivienda y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }


    /***************************************************/
    /********************* ZpoV2CuestionarioSaludMaterna******/
    /***************************************************/
    // url, username, password
    protected String uploadCuestSaMat(String url, String username,
                                      String password) throws Exception {
        try {
            if(mCuestSaMat.size()>0){
                publishProgress("Enviando Cuestionarios de Salud Materna!", String.valueOf(CUEST_SA_MAT), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/saveZpoCuestSaludMaterna";
                ZpoV2CuestionarioSaludMaterna[] envio = mCuestSaMat.toArray(new ZpoV2CuestionarioSaludMaterna[mCuestSaMat.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<ZpoV2CuestionarioSaludMaterna[]> requestEntity =
                        new HttpEntity<ZpoV2CuestionarioSaludMaterna[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la vivienda y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }

    /***************************************************/
    /********************* ZpoV2CuestionarioSocioeconomico******/
    /***************************************************/
    // url, username, password
    protected String uploadCuestSocioeco(String url, String username,
                                      String password) throws Exception {
        try {
            if(mCuestSocie.size()>0){
                publishProgress("Enviando Cuestionarios Socieconomicos!", String.valueOf(CUEST_SOE), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/saveCuestSocioecs";
                ZpoV2CuestionarioSocioeconomico[] envio = mCuestSocie.toArray(new ZpoV2CuestionarioSocioeconomico[mCuestSocie.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<ZpoV2CuestionarioSocioeconomico[]> requestEntity =
                        new HttpEntity<ZpoV2CuestionarioSocioeconomico[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la vivienda y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }

}