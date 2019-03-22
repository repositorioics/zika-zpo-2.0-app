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
    private static final String TOTAL_TASK = "25";
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

	private String url = null;
	private String username = null;
	private String password = null;
	private String error = null;
	protected UploadListener mStateListener;

    public static final int TAMIZAJE = 1;
    public static final int ESTADO = 2;
    public static final int DAT_INFANTE = 3;
    public static final int ESTADO_INFANTE = 4;
    public static final int INGRESO1 = 5;
    public static final int INGRESO2 = 6;
    public static final int INGRESO3 = 7;
    public static final int EXTENDED1 = 8;
    public static final int EXTENDED2 = 9;
    public static final int EXTENDED3 = 10;
    public static final int PARTO = 11;
    public static final int EVAL_INFANTE = 12;
    public static final int OPHTH_RESULTS = 13;
    public static final int AUDIO_RESULTS = 14;
    public static final int IMAGE_STUDIES = 15;
    public static final int BAYLEY_SCALES = 16;
    public static final int MUESTRAS = 17;
    public static final int CONSSAL = 18;
    public static final int CONSREC = 19;
    public static final int SALIDA = 20;
    public static final int VISITA_FALL = 21;
    public static final int OTO_EMI = 22;
    public static final int EXTENDEDAF = 23;
    public static final int MULLEN = 24;
    public static final int OFTA_EVAL = 24;
    public static final int PSICO_EVAL = 25;

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

        if(opcion==MULLEN){
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
        if(opcion==MUESTRAS){
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
        if(opcion==OTO_EMI){
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
        if(opcion==OFTA_EVAL){
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
        if(opcion==PSICO_EVAL){
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

}