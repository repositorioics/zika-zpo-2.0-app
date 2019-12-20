package ni.org.ics.zpo.v2.appmovil.activities.nuevos;

import android.app.Dialog;
import android.content.ContentUris;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import ni.org.ics.zpo.v2.appmovil.AbstractAsyncActivity;
import ni.org.ics.zpo.v2.appmovil.MainActivity;
import ni.org.ics.zpo.v2.appmovil.MyZpoApplication;
import ni.org.ics.zpo.v2.appmovil.R;
import ni.org.ics.zpo.v2.appmovil.database.ZpoAdapter;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoInfantData;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoV2EvaluacionVisual;
import ni.org.ics.zpo.v2.appmovil.parsers.ZpoV2EvalVisualXml;
import ni.org.ics.zpo.v2.appmovil.preferences.PreferencesActivity;
import ni.org.ics.zpo.v2.appmovil.utils.Constants;
import ni.org.ics.zpo.v2.appmovil.utils.FileUtils;
import ni.org.ics.zpo.v2.appmovil.utils.MainDBConstants;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ics
 */
public class NewZpoV2EvalVisualActivity extends AbstractAsyncActivity {

    protected static final String TAG = NewZpoV2EvalVisualActivity.class.getSimpleName();

    private ZpoAdapter zpoA;
    private static ZpoV2EvaluacionVisual mZpoV2EvalVisual = new ZpoV2EvaluacionVisual();

    public static final int ADD_ZPOEVIS_ODK = 1;
    public static final int EDIT_ZPOEVIS_ODK = 2;

    Dialog dialogInit;
    private SharedPreferences settings;
    private String username;
    private String mRecordId = "";
    private Integer accion = 0;
    private String event;
    private ZpoInfantData infantData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!FileUtils.storageReady()) {
            Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.error ) + "," + getString(R.string.storage_error), Toast.LENGTH_LONG);
            toast.show();
            finish();
        }
        settings =
                PreferenceManager.getDefaultSharedPreferences(this);
        username =
                settings.getString(PreferencesActivity.KEY_USERNAME,
                        null);
        String mPass = ((MyZpoApplication) this.getApplication()).getPassApp();
        zpoA = new ZpoAdapter(this.getApplicationContext(), mPass, false, false);
        mRecordId = getIntent().getExtras().getString(Constants.RECORDID);
        event = getIntent().getExtras().getString(Constants.EVENT);
        zpoA.open();
        infantData = zpoA.getZpoInfantData( MainDBConstants.recordId + "='" + mRecordId + "'", null);
        zpoA.close();
        mZpoV2EvalVisual = (ZpoV2EvaluacionVisual) getIntent().getExtras().getSerializable(Constants.OBJECT_EVAL_VIS);
        createInitDialog();
    }

    /**
     * Presenta dialogo inicial
     */

    private void createInitDialog() {
        dialogInit = new Dialog(this, R.style.FullHeightDialog);
        dialogInit.setContentView(R.layout.yesno);
        dialogInit.setCancelable(false);

        //to set the message
        TextView message = (TextView) dialogInit.findViewById(R.id.yesnotext);
        if (mZpoV2EvalVisual != null) {
            message.setText(getString(R.string.edit) + " " + getString(R.string.infant_b_6) + "?");
        } else {
            message.setText(getString(R.string.add) + " " + getString(R.string.infant_b_6) + "?");
        }

        //add some action to the buttons

        Button yes = (Button) dialogInit.findViewById(R.id.yesnoYes);
        yes.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                dialogInit.dismiss();
                addZpoExamFisicoInfante();
            }
        });

        Button no = (Button) dialogInit.findViewById(R.id.yesnoNo);
        no.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Cierra
                dialogInit.dismiss();
                finish();
            }
        });
        dialogInit.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.general, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.MENU_BACK) {
            finish();
            return true;
        } else if (item.getItemId() == R.id.MENU_HOME) {
            Intent i = new Intent(getApplicationContext(),
                    MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        if (requestCode == ADD_ZPOEVIS_ODK || requestCode == EDIT_ZPOEVIS_ODK) {
            if (resultCode == RESULT_OK) {
                Uri instanceUri = intent.getData();
                //Busca la instancia resultado
                String[] projection = new String[]{
                        "_id", "instanceFilePath", "status", "displaySubtext"};
                Cursor c = getContentResolver().query(instanceUri, projection,
                        null, null, null);
                c.moveToFirst();
                //Captura la id de la instancia y la ruta del archivo para agregarlo al participante
                Integer idInstancia = c.getInt(c.getColumnIndex("_id"));
                String instanceFilePath = c.getString(c.getColumnIndex("instanceFilePath"));
                String complete = c.getString(c.getColumnIndex("status"));
                //cierra el cursor
                if (c != null) {
                    c.close();
                }
                if (complete.matches("complete")) {
                    //Parsear el resultado obteniendo un tamizaje si esta completo
                    parseZpoExamFisicoInfante(idInstancia, instanceFilePath, accion);
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.err_not_completed), Toast.LENGTH_LONG).show();
                }
            } else {
                finish();
            }
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }

    private void addZpoExamFisicoInfante() {
        try {
            Uri formUri;
            if (mZpoV2EvalVisual == null) {
                //campos de proveedor de collect
                String[] projection = new String[]{
                        "_id", "jrFormId", "displayName"};
                //cursor que busca el formulario
                Cursor c = getContentResolver().query(Constants.CONTENT_URI, projection,
                        "jrFormId = 'ZpoV2_Evaluacion_Visual' and displayName = 'Continuacion Estudio ZPO Evaluacion Visual'", null, null);
                c.moveToFirst();
                //captura el id del formulario
                Integer id = Integer.parseInt(c.getString(0));
                //cierra el cursor
                if (c != null) {
                    c.close();
                }
                formUri = ContentUris.withAppendedId(Constants.CONTENT_URI, id);
                accion = ADD_ZPOEVIS_ODK;
            } else {
                //forma el uri para la instancia en ODK Collect
                Integer id = mZpoV2EvalVisual.getIdInstancia();
                formUri = ContentUris.withAppendedId(Constants.CONTENT_URI_I, id);
                accion = EDIT_ZPOEVIS_ODK;
            }
            Intent odkA = new Intent(Intent.ACTION_EDIT, formUri);
            //Arranca la actividad proveedor de instancias de ODK Collect en busca de resultado
            startActivityForResult(odkA, accion);
        } catch (Exception e) {
            //No existe el formulario en el equipo
            Log.e(TAG, e.getMessage(), e);
            Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public Integer getEdadMeses(Date fechaNac, Date fecha){
        Calendar inicio = Calendar.getInstance();
        Calendar fin = Calendar.getInstance();
        inicio.setTime(fechaNac);
        fin.setTime(fecha);
        int difA = fin.get(Calendar.YEAR) - inicio.get(Calendar.YEAR);
        int difM = difA * 12 + fin.get(Calendar.MONTH) - inicio.get(Calendar.MONTH);
        int difD = fin.get(Calendar.DAY_OF_MONTH) - inicio.get(Calendar.DAY_OF_MONTH);
        //aun no ha cumplido mes, restar 1
        if (difD < 0) difM -=1;
        return difM;
    }


    private void parseZpoExamFisicoInfante(Integer idInstancia, String instanceFilePath, Integer accion) {
        Serializer serializer = new Persister();
        File source = new File(instanceFilePath);
        try {
            Integer meses = null;
            ZpoV2EvalVisualXml zpoV2EvalVisualXml = new ZpoV2EvalVisualXml();
            zpoV2EvalVisualXml = serializer.read(ZpoV2EvalVisualXml.class, source);
            if (accion== ADD_ZPOEVIS_ODK) {
                mZpoV2EvalVisual = new ZpoV2EvaluacionVisual();

                //calcular edad en meses del infante basado en la fecha de registro del formulario
                Date fechaNac = null;
                if (infantData != null) {
                    fechaNac = infantData.getInfantBirthDate();
                    mZpoV2EvalVisual.setFechaNacimientoOptho(fechaNac);
                }

            if (zpoV2EvalVisualXml.getFechaExamenOpto() != null)
                meses = getEdadMeses( fechaNac, zpoV2EvalVisualXml.getFechaExamenOpto() );
                if (meses != null) mZpoV2EvalVisual.setEdadOptho( meses );
            }

            mZpoV2EvalVisual.setRecordId(mRecordId);
            mZpoV2EvalVisual.setEventName(event);
            mZpoV2EvalVisual.setFechaExamenOpto(zpoV2EvalVisualXml.getFechaExamenOpto());
            mZpoV2EvalVisual.setEdadAjustadaOptho(zpoV2EvalVisualXml.getEdadAjustadaOptho());
            mZpoV2EvalVisual.setEdadGestacionalOptho(zpoV2EvalVisualXml.getEdadGestacionalOptho());
            mZpoV2EvalVisual.setExaminadorOptho(zpoV2EvalVisualXml.getExaminadorOptho());
            mZpoV2EvalVisual.setNombreOptho(zpoV2EvalVisualXml.getNombreOptho());
            mZpoV2EvalVisual.setCodigoIdentiOptho(zpoV2EvalVisualXml.getCodigoIdentiOptho());
            mZpoV2EvalVisual.setNombreMadreOptho(zpoV2EvalVisualXml.getNombreMadreOptho());
            mZpoV2EvalVisual.setSexoOptho(zpoV2EvalVisualXml.getSexoOptho());
            mZpoV2EvalVisual.setHistoriaOcularOptho(zpoV2EvalVisualXml.getHistoriaOcularOptho());
            mZpoV2EvalVisual.setMicroencefaliaOptho(zpoV2EvalVisualXml.getMicroencefaliaOptho());
            mZpoV2EvalVisual.setMicroencefaliaQuienOptho(zpoV2EvalVisualXml.getMicroencefaliaQuienOptho());
            mZpoV2EvalVisual.setCatarataCongenitaOptho(zpoV2EvalVisualXml.getCatarataCongenitaOptho());
            mZpoV2EvalVisual.setCatarataQuienOptho(zpoV2EvalVisualXml.getCatarataQuienOptho());
            mZpoV2EvalVisual.setGlaucomaCongenitaOptho(zpoV2EvalVisualXml.getGlaucomaCongenitaOptho());
            mZpoV2EvalVisual.setGlaucomaQuienOptho(zpoV2EvalVisualXml.getGlaucomaQuienOptho());
            mZpoV2EvalVisual.setCegueraOptho(zpoV2EvalVisualXml.getCegueraOptho());
            mZpoV2EvalVisual.setCegueraQuienOptho(zpoV2EvalVisualXml.getCegueraQuienOptho());
            mZpoV2EvalVisual.setMyopiaOptho(zpoV2EvalVisualXml.getMyopiaOptho());
            mZpoV2EvalVisual.setMyopiaQuienOptho(zpoV2EvalVisualXml.getMyopiaQuienOptho());
            mZpoV2EvalVisual.setOtrosAntecedentesOptho(zpoV2EvalVisualXml.getOtrosAntecedentesOptho());
            mZpoV2EvalVisual.setCambioMiradaOptho(zpoV2EvalVisualXml.getCambioMiradaOptho());
            mZpoV2EvalVisual.setIntentoAgarrarOptho(zpoV2EvalVisualXml.getIntentoAgarrarOptho());
            mZpoV2EvalVisual.setReaccionPalaOptho(zpoV2EvalVisualXml.getReaccionPalaOptho());
            mZpoV2EvalVisual.setContrasteHeidiOptho(zpoV2EvalVisualXml.getContrasteHeidiOptho());
            mZpoV2EvalVisual.setDistanciaContrasteOptho(zpoV2EvalVisualXml.getDistanciaContrasteOptho());
            mZpoV2EvalVisual.setOtraDistanciaOptho(zpoV2EvalVisualXml.getOtraDistanciaOptho());
            mZpoV2EvalVisual.setPioPalpacionOptho(zpoV2EvalVisualXml.getPioPalpacionOptho());
            mZpoV2EvalVisual.setMotilidadOptho(zpoV2EvalVisualXml.getMotilidadOptho());
            mZpoV2EvalVisual.setAlineacionOptho(zpoV2EvalVisualXml.getAlineacionOptho());
            mZpoV2EvalVisual.setPdOptho(zpoV2EvalVisualXml.getPdOptho());
            mZpoV2EvalVisual.setMetodoPdOptho(zpoV2EvalVisualXml.getMetodoPdOptho());
            mZpoV2EvalVisual.setNistagmoOptho(zpoV2EvalVisualXml.getNistagmoOptho());
            mZpoV2EvalVisual.setTipoNystagmoOptho(zpoV2EvalVisualXml.getTipoNystagmoOptho());
            mZpoV2EvalVisual.setOuOptho(zpoV2EvalVisualXml.getOuOptho());
            mZpoV2EvalVisual.setOdOptho(zpoV2EvalVisualXml.getOdOptho());
            mZpoV2EvalVisual.setOsOptho(zpoV2EvalVisualXml.getOsOptho());
            mZpoV2EvalVisual.setIncapazNivelJovenOptho(zpoV2EvalVisualXml.getIncapazNivelJovenOptho());
            mZpoV2EvalVisual.setSemanasOptho(zpoV2EvalVisualXml.getSemanasOptho());
            mZpoV2EvalVisual.setMeses3Optho(zpoV2EvalVisualXml.getMeses3Optho());
            mZpoV2EvalVisual.setMeses5Optho(zpoV2EvalVisualXml.getMeses5Optho());
            mZpoV2EvalVisual.setMeses7Optho(zpoV2EvalVisualXml.getMeses7Optho());
            mZpoV2EvalVisual.setMeses12Optho(zpoV2EvalVisualXml.getMeses12Optho());
            mZpoV2EvalVisual.setLeaGratingOptho(zpoV2EvalVisualXml.getLeaGratingOptho());
            mZpoV2EvalVisual.setOtraLeaDistanciaOptho(zpoV2EvalVisualXml.getOtraLeaDistanciaOptho());
            mZpoV2EvalVisual.setOdCpcmOptho(zpoV2EvalVisualXml.getOdCpcmOptho());
            mZpoV2EvalVisual.setOsCpcmOptho(zpoV2EvalVisualXml.getOsCpcmOptho());
            mZpoV2EvalVisual.setOuCpcmOptho(zpoV2EvalVisualXml.getOuCpcmOptho());
            mZpoV2EvalVisual.setOdCpdOptho(zpoV2EvalVisualXml.getOdCpdOptho());
            mZpoV2EvalVisual.setOsCpdOptho(zpoV2EvalVisualXml.getOsCpdOptho());
            mZpoV2EvalVisual.setOuCpdOptho(zpoV2EvalVisualXml.getOuCpdOptho());
            mZpoV2EvalVisual.setReparaYSigueOptho(zpoV2EvalVisualXml.getReparaYSigueOptho());
            mZpoV2EvalVisual.setReaccionaLuzOptho(zpoV2EvalVisualXml.getReaccionaLuzOptho());
            mZpoV2EvalVisual.setReflejoAcomodativoOptho(zpoV2EvalVisualXml.getReflejoAcomodativoOptho());
            mZpoV2EvalVisual.setConvergenciaOptho(zpoV2EvalVisualXml.getConvergenciaOptho());
            mZpoV2EvalVisual.setMiosisOptho(zpoV2EvalVisualXml.getMiosisOptho());
            mZpoV2EvalVisual.setRetinoscopiaOptho(zpoV2EvalVisualXml.getRetinoscopiaOptho());
            mZpoV2EvalVisual.setExamenOjosExtOptho(zpoV2EvalVisualXml.getExamenOjosExtOptho());
            mZpoV2EvalVisual.setDescribaAnormalExtOptho(zpoV2EvalVisualXml.getDescribaAnormalExtOptho());
            mZpoV2EvalVisual.setMicroftalmiaAnoftOptho(zpoV2EvalVisualXml.getMicroftalmiaAnoftOptho());
            mZpoV2EvalVisual.setPupilasOptho(zpoV2EvalVisualXml.getPupilasOptho());
            mZpoV2EvalVisual.setDescribaAnormlPupilOptho(zpoV2EvalVisualXml.getDescribaAnormlPupilOptho());
            mZpoV2EvalVisual.setExamSegmentoAntOptho(zpoV2EvalVisualXml.getExamSegmentoAntOptho());
            mZpoV2EvalVisual.setDescribaSegmAntAnormOptho(zpoV2EvalVisualXml.getDescribaSegmAntAnormOptho());
            mZpoV2EvalVisual.setPstosisOptho(zpoV2EvalVisualXml.getPstosisOptho());
            mZpoV2EvalVisual.setIrisColobomaOptho(zpoV2EvalVisualXml.getIrisColobomaOptho());
            mZpoV2EvalVisual.setLenteCatarataSubluOptho(zpoV2EvalVisualXml.getLenteCatarataSubluOptho());
            mZpoV2EvalVisual.setOtroLenteOptho(zpoV2EvalVisualXml.getOtroLenteOptho());
            mZpoV2EvalVisual.setOtroCualOjoOptho(zpoV2EvalVisualXml.getOtroCualOjoOptho());
            mZpoV2EvalVisual.setDilatacionTiempoOptho(zpoV2EvalVisualXml.getDilatacionTiempoOptho());
            mZpoV2EvalVisual.setReflejoRojoOptho(zpoV2EvalVisualXml.getReflejoRojoOptho());
            mZpoV2EvalVisual.setCrxOdOpthoSphere(zpoV2EvalVisualXml.getCrxOdOpthoSphere());
            mZpoV2EvalVisual.setOpthoOdCyl(zpoV2EvalVisualXml.getOpthoOdCyl());
            mZpoV2EvalVisual.setOpthoOdAxis(zpoV2EvalVisualXml.getOpthoOdAxis());
            mZpoV2EvalVisual.setCrxOsOpthoSphere(zpoV2EvalVisualXml.getCrxOsOpthoSphere());
            mZpoV2EvalVisual.setOpthoOsCyl(zpoV2EvalVisualXml.getOpthoOsCyl());
            mZpoV2EvalVisual.setOpthoOsAxis(zpoV2EvalVisualXml.getOpthoOsAxis());
            mZpoV2EvalVisual.setVitreoOptho(zpoV2EvalVisualXml.getVitreoOptho());
            mZpoV2EvalVisual.setDescribaAnormalOptho(zpoV2EvalVisualXml.getDescribaAnormalOptho());
            mZpoV2EvalVisual.setNervioOpticoOptho(zpoV2EvalVisualXml.getNervioOpticoOptho());
            mZpoV2EvalVisual.setCopaOpticoOptho(zpoV2EvalVisualXml.getCopaOpticoOptho());
            mZpoV2EvalVisual.setcDRatioOdOsOptho(zpoV2EvalVisualXml.getcDRatioOdOsOptho());
            mZpoV2EvalVisual.setAtrofiaDelNervioOptho(zpoV2EvalVisualXml.getAtrofiaDelNervioOptho());
            mZpoV2EvalVisual.setNervioHipoplasiaOptho(zpoV2EvalVisualXml.getNervioHipoplasiaOptho());
            mZpoV2EvalVisual.setColobomaNervioOptho(zpoV2EvalVisualXml.getColobomaNervioOptho());
            mZpoV2EvalVisual.setRetinaOptho(zpoV2EvalVisualXml.getRetinaOptho());
            mZpoV2EvalVisual.setMoteadoPigmenFocalOptho(zpoV2EvalVisualXml.getMoteadoPigmenFocalOptho());
            mZpoV2EvalVisual.setAtrofiaCoriorretinalOptho(zpoV2EvalVisualXml.getAtrofiaCoriorretinalOptho());
            mZpoV2EvalVisual.setLesionColobomaOptho(zpoV2EvalVisualXml.getLesionColobomaOptho());
            mZpoV2EvalVisual.setLesionHipopigmentOptho(zpoV2EvalVisualXml.getLesionHipopigmentOptho());
            mZpoV2EvalVisual.setReflejoNovelaOptho(zpoV2EvalVisualXml.getReflejoNovelaOptho());
            mZpoV2EvalVisual.setCalcificIntracularOptho(zpoV2EvalVisualXml.getCalcificIntracularOptho());
            mZpoV2EvalVisual.setVasosSanguineosOdOptho(zpoV2EvalVisualXml.getVasosSanguineosOdOptho());
            mZpoV2EvalVisual.setVasosSanguineosOsOptho(zpoV2EvalVisualXml.getVasosSanguineosOsOptho());
            mZpoV2EvalVisual.setCambiosPerivascOptho(zpoV2EvalVisualXml.getCambiosPerivascOptho());
            mZpoV2EvalVisual.setFuncionVisualOptho(zpoV2EvalVisualXml.getFuncionVisualOptho());
            mZpoV2EvalVisual.setRefraccionApropiadoOptho(zpoV2EvalVisualXml.getRefraccionApropiadoOptho());
            mZpoV2EvalVisual.setHallazgosOcularesOptho(zpoV2EvalVisualXml.getHallazgosOcularesOptho());
            mZpoV2EvalVisual.setHallazgosZikaOptho(zpoV2EvalVisualXml.getHallazgosZikaOptho());
            mZpoV2EvalVisual.setSeguimientoDoctorOptho(zpoV2EvalVisualXml.getSeguimientoDoctorOptho());
            mZpoV2EvalVisual.setGafasOptho(zpoV2EvalVisualXml.getGafasOptho());
            mZpoV2EvalVisual.setIntervencionOptho(zpoV2EvalVisualXml.getIntervencionOptho());
            mZpoV2EvalVisual.setSeguimientoOftalOptho(zpoV2EvalVisualXml.getSeguimientoOftalOptho());
            mZpoV2EvalVisual.setEntreComentariosOptho(zpoV2EvalVisualXml.getEntreComentariosOptho());


            mZpoV2EvalVisual.setRecordDate(new Date());
            mZpoV2EvalVisual.setRecordUser(username);
            mZpoV2EvalVisual.setIdInstancia(idInstancia);
            mZpoV2EvalVisual.setInstancePath(instanceFilePath);
            mZpoV2EvalVisual.setEstado(Constants.STATUS_NOT_SUBMITTED);
            mZpoV2EvalVisual.setStart( zpoV2EvalVisualXml.getStart());
            mZpoV2EvalVisual.setEnd( zpoV2EvalVisualXml.getEnd());
            mZpoV2EvalVisual.setDeviceid( zpoV2EvalVisualXml.getDeviceid());
            mZpoV2EvalVisual.setSimserial( zpoV2EvalVisualXml.getSimserial());
            mZpoV2EvalVisual.setPhonenumber( zpoV2EvalVisualXml.getPhonenumber());
            mZpoV2EvalVisual.setToday( zpoV2EvalVisualXml.getToday());
            new SaveDataTask().execute(accion);

        } catch (Exception e) {
            // Presenta el error al parsear el xml
            Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
            finish();
        }
    }

    // ***************************************
    // Private classes
    // ***************************************
    private class SaveDataTask extends AsyncTask<Integer, Void, String> {
        private Integer accionaRealizar = null;
        @Override
        protected void onPreExecute() {
            // before the request begins, show a progress indicator
            showLoadingProgressDialog();
        }

        @Override
        protected String doInBackground(Integer... values) {
            accionaRealizar = values[0];
            try {
                zpoA.open();
                if (accionaRealizar == ADD_ZPOEVIS_ODK){
                   zpoA.crearZpoV2EvalVisual(mZpoV2EvalVisual);
                }
                else{
                    zpoA.editarZpoV2EvalVisual(mZpoV2EvalVisual);
                }
                zpoA.close();
            } catch (Exception e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return "error";
            }
            return "exito";
        }

        protected void onPostExecute(String resultado) {
            // after the network request completes, hide the progress indicator
            dismissProgressDialog();
            showResult(resultado);
        }

    }

    // ***************************************
    // Private methods
    // ***************************************
    private void showResult(String resultado) {
        Toast.makeText(getApplicationContext(),	resultado, Toast.LENGTH_LONG).show();
        finish();
    }
}
