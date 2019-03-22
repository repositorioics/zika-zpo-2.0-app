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
    private static final String TOTAL_TASK = "22";

    private List<ZpoDatosEmbarazada> mDatosEmb = null;
    private List<Zpo00Screening> mTamizajes = null;
    private List<ZpoEstadoEmbarazada> mStatus = null;
    private List<ZpoInfantData> mInfantData = null;
    private List<ZpoEstadoInfante> mEstadoInfante = null;
    private List<ZpoV2Mullen> mMullen = null;
    private List<ZpoV2RecoleccionMuestra> mMuestras = null;
    private List<ZpoV2InfantOphthalmologicEvaluation> mOphthaEvals= null;
    private List<ZpoV2InfantPsychologicalEvaluation> mPsychoEvals = null;
    private List<ZpoV2InfantOtoacousticEmissions> mOtoacusEms = null;

    public static final int DAT_MADRE = 1;
    public static final int ESTADO = 2;
    public static final int DAT_INFANTE = 3;
    public static final int ESTADO_INFANTE = 4;
    public static final int TAMIZAJE = 5;
    public static final int INGRESO1 = 6;
    public static final int INGRESO2 = 7;
    public static final int INGRESO3 = 8;
    public static final int EXTENDED1 = 9;
    public static final int EXTENDED2 = 10;
    public static final int EXTENDED3 = 11;
    public static final int PARTO = 12;
    public static final int EVAL_INFANTE = 13;
    public static final int OPHTH_RESULTS = 14;
    public static final int AUDIO_RESULTS = 15;
    public static final int IMAGE_STUDIES = 16;
    public static final int BAYLEY_SCALES = 17;
    public static final int MUESTRAS = 18;
    public static final int CONSSAL = 19;
    public static final int CONSREC = 20;
    public static final int SALIDA = 21;
    public static final int VISITA_FALL = 22;
    public static final int OTOEMI = 23;
    public static final int EXTENDEDAF = 24;
    public static final int MULLEN = 25;
    public static final int OFTA_EVAL = 25;
    public static final int PSICO_EVAL = 26;
    
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
        zpoA.borrarZpoControlConsentimientosSalida();
        zpoA.borrarZpoControlConsentimientosRecepcion();
        zpoA.borrarZpoVisitaFallida();
        zpoA.borrarZpoInfantData();
        zpoA.borrarZpoEstadoInfante();
        zpoA.borrarZpoV2RecoleccionMuestra();
        zpoA.borrarZpoV2Mullen();
        zpoA.borrarZpoInfantOtoacousticE();
        zpoA.borrarZpoV2InfantOphthalmologicEvaluation();
        zpoA.borrarZpoV2InfantPsychologicalEvaluation();

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

            if (mOtoacusEms != null){
                v = mOtoacusEms.size();
                ListIterator<ZpoV2InfantOtoacousticEmissions> iter = mOtoacusEms.listIterator();
                while (iter.hasNext()){
                    zpoA.crearZpoInfantOtoacousticEm(iter.next());
                    publishProgress("Insertando eval emisiones otoac en la base de datos...", Integer.valueOf(iter.nextIndex()).toString(), Integer
                            .valueOf(v).toString());
                }
            }

            if (mOphthaEvals != null){
                v = mOphthaEvals.size();
                ListIterator<ZpoV2InfantOphthalmologicEvaluation> iter = mOphthaEvals.listIterator();
                while (iter.hasNext()){
                    zpoA.crearZpoV2InfantOphthalmologicEvaluation(iter.next());
                    publishProgress("Insertando eval oftalmologicas en la base de datos...", Integer.valueOf(iter.nextIndex()).toString(), Integer
                            .valueOf(v).toString());
                }
            }

            if (mPsychoEvals != null){
                v = mPsychoEvals.size();
                ListIterator<ZpoV2InfantPsychologicalEvaluation> iter = mPsychoEvals.listIterator();
                while (iter.hasNext()){
                    zpoA.crearZpoV2InfantPsychologicalEvaluation(iter.next());
                    publishProgress("Insertando eval psicologicas en la base de datos...", Integer.valueOf(iter.nextIndex()).toString(), Integer
                            .valueOf(v).toString());
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
            urlRequest = url + "/movil/zpoMullens/{username}";
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

            //Descargar emisiones otoacusticas
            urlRequest = url + "/movil/zpoInfantOtoacousticEms";
            publishProgress("Solicitando eval emisiones otoacusticas",String.valueOf(OTOEMI),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<ZpoV2InfantOtoacousticEmissions[]> responseEntityZpoOto = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    ZpoV2InfantOtoacousticEmissions[].class, username);
            // convert the array to a list and return it
            mOtoacusEms = Arrays.asList(responseEntityZpoOto.getBody());

            //Descargar evaluaciones oftalmológicas
            urlRequest = url + "/movil/zpoV2InfantOphthaEvals";
            publishProgress("Solicitando eval oftalmologicas",String.valueOf(OFTA_EVAL),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<ZpoV2InfantOphthalmologicEvaluation[]> responseEntityZpoOfta = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    ZpoV2InfantOphthalmologicEvaluation[].class, username);
            // convert the array to a list and return it
            mOphthaEvals = Arrays.asList(responseEntityZpoOfta.getBody());

            //Descargar evaluaciones psicologicas
            urlRequest = url + "/movil/zpoV2InfantPsychoEvals";
            publishProgress("Solicitando eval psicologicas",String.valueOf(PSICO_EVAL),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<ZpoV2InfantPsychologicalEvaluation[]> responseEntityZpoPsico = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    ZpoV2InfantPsychologicalEvaluation[].class, username);
            // convert the array to a list and return it
            mPsychoEvals = Arrays.asList(responseEntityZpoPsico.getBody());

            return null;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getLocalizedMessage();
        }
    }
}
