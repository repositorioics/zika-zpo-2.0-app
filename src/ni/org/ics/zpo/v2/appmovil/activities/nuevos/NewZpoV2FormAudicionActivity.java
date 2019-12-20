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
import ni.org.ics.zpo.v2.appmovil.domain.ZpoV2ExamenFisicoInfante;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoV2FormAudicion;
import ni.org.ics.zpo.v2.appmovil.parsers.ZpoV2ExamFisicoInfanteXml;
import ni.org.ics.zpo.v2.appmovil.parsers.ZpoV2FormAudicionXml;
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
public class NewZpoV2FormAudicionActivity extends AbstractAsyncActivity {

    protected static final String TAG = NewZpoV2FormAudicionActivity.class.getSimpleName();

    private ZpoAdapter zpoA;
    private static ZpoV2FormAudicion mZpoV2FormAudicion = new ZpoV2FormAudicion();

    public static final int ADD_ZPO_FAUDI_ODK = 1;
    public static final int EDIT_ZPO_FAUDI_ODK = 2;

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
        mZpoV2FormAudicion = (ZpoV2FormAudicion) getIntent().getExtras().getSerializable(Constants.OBJECT_FORM_AUDI);
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
        if (mZpoV2FormAudicion != null) {
            message.setText(getString(R.string.edit) + " " + getString(R.string.infant_b_5) + "?");
        } else {
            message.setText(getString(R.string.add) + " " + getString(R.string.infant_b_5) + "?");
        }

        //add some action to the buttons

        Button yes = (Button) dialogInit.findViewById(R.id.yesnoYes);
        yes.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                dialogInit.dismiss();
                addZpoFormAudicion();
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
        if (requestCode == ADD_ZPO_FAUDI_ODK || requestCode == EDIT_ZPO_FAUDI_ODK) {
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
                    parseZpoFormAudi(idInstancia, instanceFilePath, accion);
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.err_not_completed), Toast.LENGTH_LONG).show();
                }
            } else {
                finish();
            }
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }

    private void addZpoFormAudicion() {
        try {
            Uri formUri;
            if (mZpoV2FormAudicion == null) {
                //campos de proveedor de collect
                String[] projection = new String[]{
                        "_id", "jrFormId", "displayName"};
                //cursor que busca el formulario
                Cursor c = getContentResolver().query(Constants.CONTENT_URI, projection,
                        "jrFormId = 'ZpoV2_Evaluacion_Auditiva' and displayName = 'Continuacion Estudio ZPO Evaluacion Auditiva'", null, null);
                c.moveToFirst();
                //captura el id del formulario
                Integer id = Integer.parseInt(c.getString(0));
                //cierra el cursor
                if (c != null) {
                    c.close();
                }
                formUri = ContentUris.withAppendedId(Constants.CONTENT_URI, id);
                accion = ADD_ZPO_FAUDI_ODK;
            } else {
                //forma el uri para la instancia en ODK Collect
                Integer id = mZpoV2FormAudicion.getIdInstancia();
                formUri = ContentUris.withAppendedId(Constants.CONTENT_URI_I, id);
                accion = EDIT_ZPO_FAUDI_ODK;
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


    private void parseZpoFormAudi(Integer idInstancia, String instanceFilePath, Integer accion) {
        Serializer serializer = new Persister();
        File source = new File(instanceFilePath);
        try {
            ZpoV2FormAudicionXml zpoV2FormAudiXml = new ZpoV2FormAudicionXml();
            zpoV2FormAudiXml = serializer.read(ZpoV2FormAudicionXml.class, source);
            if (accion== ADD_ZPO_FAUDI_ODK) mZpoV2FormAudicion = new ZpoV2FormAudicion();
            mZpoV2FormAudicion.setRecordId(mRecordId);
            mZpoV2FormAudicion.setEventName(event);
            mZpoV2FormAudicion.setFechaDeRealizacionDePr(zpoV2FormAudiXml.getFechaDeRealizacionDePr());
            mZpoV2FormAudicion.setHaPadecidoDe(zpoV2FormAudiXml.getHaPadecidoDe());
            mZpoV2FormAudicion.setSupuracionDeCualOido(zpoV2FormAudiXml.getSupuracionDeCualOido());
            mZpoV2FormAudicion.setSangradoDeCualOido(zpoV2FormAudiXml.getSangradoDeCualOido());
            mZpoV2FormAudicion.setInfeccionDeCualOido(zpoV2FormAudiXml.getInfeccionDeCualOido());
            mZpoV2FormAudicion.setHaPadecidoDeAlgunaDeL(zpoV2FormAudiXml.getHaPadecidoDeAlgunaDeL());
            mZpoV2FormAudicion.setEspecifiqueOtra(zpoV2FormAudiXml.getEspecifiqueOtra());
            mZpoV2FormAudicion.setHaRecibidoTratamientoCo(zpoV2FormAudiXml.getHaRecibidoTratamientoCo());
            mZpoV2FormAudicion.setParaTbEspecifiqueSemana(zpoV2FormAudiXml.getParaTbEspecifiqueSemana());
            mZpoV2FormAudicion.setAntecedentesFamiliaresDe(zpoV2FormAudiXml.getAntecedentesFamiliaresDe());
            mZpoV2FormAudicion.setTipoDeSordera(zpoV2FormAudiXml.getTipoDeSordera());
            mZpoV2FormAudicion.setHaRecibidoTratamNino(zpoV2FormAudiXml.getHaRecibidoTratamNino());
            mZpoV2FormAudicion.setParaTbNinoSemana(zpoV2FormAudiXml.getParaTbNinoSemana());
            mZpoV2FormAudicion.setConsideraQueSuNinoEscu(zpoV2FormAudiXml.getConsideraQueSuNinoEscu());
            mZpoV2FormAudicion.setDesdeHaceCuandoNotaQue(zpoV2FormAudiXml.getDesdeHaceCuandoNotaQue());
            mZpoV2FormAudicion.setConductoAuditivoExterno(zpoV2FormAudiXml.getConductoAuditivoExterno());
            mZpoV2FormAudicion.setIntegridad(zpoV2FormAudiXml.getIntegridad());
            mZpoV2FormAudicion.setColoracion(zpoV2FormAudiXml.getColoracion());
            mZpoV2FormAudicion.setContorno(zpoV2FormAudiXml.getContorno());
            mZpoV2FormAudicion.setMovilidad(zpoV2FormAudiXml.getMovilidad());
            mZpoV2FormAudicion.setConductoAuditivoExtIzqu(zpoV2FormAudiXml.getConductoAuditivoExtIzqu());
            mZpoV2FormAudicion.setIntegridadMembranaTimp(zpoV2FormAudiXml.getIntegridadMembranaTimp());
            mZpoV2FormAudicion.setColoracionMembranaTimp(zpoV2FormAudiXml.getColoracionMembranaTimp());
            mZpoV2FormAudicion.setContornoMembranaTimp(zpoV2FormAudiXml.getContornoMembranaTimp());
            mZpoV2FormAudicion.setMovilidadMembranaTimp(zpoV2FormAudiXml.getMovilidadMembranaTimp());
            mZpoV2FormAudicion.setOdOas(zpoV2FormAudiXml.getOdOas());
            mZpoV2FormAudicion.setOiOas(zpoV2FormAudiXml.getOiOas());
            mZpoV2FormAudicion.setOdPasa(zpoV2FormAudiXml.getOdPasa());
            mZpoV2FormAudicion.setOiPasa(zpoV2FormAudiXml.getOiPasa());
            mZpoV2FormAudicion.setResultadoDeOea(zpoV2FormAudiXml.getResultadoDeOea());
            mZpoV2FormAudicion.setNombreDelMedicoEvaluado(zpoV2FormAudiXml.getNombreDelMedicoEvaluado());

            mZpoV2FormAudicion.setRecordDate(new Date());
            mZpoV2FormAudicion.setRecordUser(username);
            mZpoV2FormAudicion.setIdInstancia(idInstancia);
            mZpoV2FormAudicion.setInstancePath(instanceFilePath);
            mZpoV2FormAudicion.setEstado(Constants.STATUS_NOT_SUBMITTED);
            mZpoV2FormAudicion.setStart(zpoV2FormAudiXml.getStart());
            mZpoV2FormAudicion.setEnd(zpoV2FormAudiXml.getEnd());
            mZpoV2FormAudicion.setDeviceid(zpoV2FormAudiXml.getDeviceid());
            mZpoV2FormAudicion.setSimserial(zpoV2FormAudiXml.getSimserial());
            mZpoV2FormAudicion.setPhonenumber(zpoV2FormAudiXml.getPhonenumber());
            mZpoV2FormAudicion.setToday(zpoV2FormAudiXml.getToday());
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
                if (accionaRealizar == ADD_ZPO_FAUDI_ODK){
                    zpoA.crearZpoV2FormAudicion(mZpoV2FormAudicion);
                }
                else{
                    zpoA.editarZpoV2FormAudicion(mZpoV2FormAudicion);
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
