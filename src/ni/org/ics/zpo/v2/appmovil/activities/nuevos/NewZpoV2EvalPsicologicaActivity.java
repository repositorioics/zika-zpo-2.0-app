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
import ni.org.ics.zpo.v2.appmovil.domain.ZpoV2CuestionarioDemografico;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoV2EvaluacionPsicologica;
import ni.org.ics.zpo.v2.appmovil.parsers.ZpoV2CuestDemograficoXml;
import ni.org.ics.zpo.v2.appmovil.parsers.ZpoV2EvalPsicologicaXml;
import ni.org.ics.zpo.v2.appmovil.preferences.PreferencesActivity;
import ni.org.ics.zpo.v2.appmovil.utils.Constants;
import ni.org.ics.zpo.v2.appmovil.utils.FileUtils;
import ni.org.ics.zpo.v2.appmovil.utils.MainDBConstants;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.util.Date;

/**
 * @author ics
 */
public class NewZpoV2EvalPsicologicaActivity extends AbstractAsyncActivity {

    protected static final String TAG = NewZpoV2EvalPsicologicaActivity.class.getSimpleName();

    private ZpoAdapter zpoA;
    private static ZpoV2EvaluacionPsicologica mZpoV2EvalPsico = new ZpoV2EvaluacionPsicologica();

    public static final int ADD_ZPOEPSIC_ODK = 1;
    public static final int EDIT_ZPOEPSIC_ODK = 2;

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
        mZpoV2EvalPsico = (ZpoV2EvaluacionPsicologica) getIntent().getExtras().getSerializable(Constants.OBJECT_EVAL_PSICO);
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
        if (mZpoV2EvalPsico != null) {
            message.setText(getString(R.string.edit) + " " + getString(R.string.infant_b_19) + "?");
        } else {
            message.setText(getString(R.string.add) + " " + getString(R.string.infant_b_19) + "?");
        }

        //add some action to the buttons

        Button yes = (Button) dialogInit.findViewById(R.id.yesnoYes);
        yes.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                dialogInit.dismiss();
                addZpoEvalPsicologica();
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
        if (requestCode == ADD_ZPOEPSIC_ODK || requestCode == EDIT_ZPOEPSIC_ODK) {
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
                    parseZpoEvalPsicologica(idInstancia, instanceFilePath, accion);
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.err_not_completed), Toast.LENGTH_LONG).show();
                }
            } else {
                finish();
            }
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }

    private void addZpoEvalPsicologica() {
        try {
            Uri formUri;
            if (mZpoV2EvalPsico == null) {
                //campos de proveedor de collect
                String[] projection = new String[]{
                        "_id", "jrFormId", "displayName"};
                //cursor que busca el formulario
                Cursor c = getContentResolver().query(Constants.CONTENT_URI, projection,
                        "jrFormId = 'ZpoV2_Evaluacion_Psicologica' and displayName = 'Continuacion Estudio ZPO Evaluacion Psicologica'", null, null);
                c.moveToFirst();
                //captura el id del formulario
                Integer id = Integer.parseInt(c.getString(0));
                //cierra el cursor
                if (c != null) {
                    c.close();
                }
                formUri = ContentUris.withAppendedId(Constants.CONTENT_URI, id);
                accion = ADD_ZPOEPSIC_ODK;
            } else {
                //forma el uri para la instancia en ODK Collect
                Integer id = mZpoV2EvalPsico.getIdInstancia();
                formUri = ContentUris.withAppendedId(Constants.CONTENT_URI_I, id);
                accion = EDIT_ZPOEPSIC_ODK;
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

    private void parseZpoEvalPsicologica(Integer idInstancia, String instanceFilePath, Integer accion) {
        Serializer serializer = new Persister();
        File source = new File(instanceFilePath);
        try {
            ZpoV2EvalPsicologicaXml zpoV2EvPsicoXml = new ZpoV2EvalPsicologicaXml();
            zpoV2EvPsicoXml = serializer.read(ZpoV2EvalPsicologicaXml.class, source);
            if (accion== ADD_ZPOEPSIC_ODK) mZpoV2EvalPsico = new ZpoV2EvaluacionPsicologica();
            mZpoV2EvalPsico.setRecordId(mRecordId);
            mZpoV2EvalPsico.setEventName(event);
            mZpoV2EvalPsico.setFechaPsych(zpoV2EvPsicoXml.getFechaPsych());
          /*  mZpoV2EvalPsico.setTrabajarPsych(zpoV2EvPsicoXml.getTrabajarPsych());
            mZpoV2EvalPsico.setCocinarPsych(zpoV2EvPsicoXml.getCocinarPsych());
            mZpoV2EvalPsico.setMercadoPsych(zpoV2EvPsicoXml.getMercadoPsych());
            mZpoV2EvalPsico.setBanarHijoPsych(zpoV2EvPsicoXml.getBanarHijoPsych());
            mZpoV2EvalPsico.setVestirHijoPsych(zpoV2EvPsicoXml.getVestirHijoPsych());
            mZpoV2EvalPsico.setLimpiarPsych(zpoV2EvPsicoXml.getLimpiarPsych());
            mZpoV2EvalPsico.setLavarRopaPsych(zpoV2EvPsicoXml.getLavarRopaPsych());
            mZpoV2EvalPsico.setBanarsePsych(zpoV2EvPsicoXml.getBanarsePsych());
            mZpoV2EvalPsico.setCuidarCabelloPsych(zpoV2EvPsicoXml.getCuidarCabelloPsych());
            mZpoV2EvalPsico.setAtenderVisitaPsych(zpoV2EvPsicoXml.getAtenderVisitaPsych());
            mZpoV2EvalPsico.setLavarDientesPsych(zpoV2EvPsicoXml.getLavarDientesPsych());
            mZpoV2EvalPsico.setUsarRopaLimpiaPsych(zpoV2EvPsicoXml.getUsarRopaLimpiaPsych());
            mZpoV2EvalPsico.setJuntarMujeresPsych(zpoV2EvPsicoXml.getJuntarMujeresPsych());
            mZpoV2EvalPsico.setAyudarAmigasPsych(zpoV2EvPsicoXml.getAyudarAmigasPsych());
            mZpoV2EvalPsico.setCompartirInfoPsych(zpoV2EvPsicoXml.getCompartirInfoPsych());
            mZpoV2EvalPsico.setTareasSaludPsych(zpoV2EvPsicoXml.getTareasSaludPsych());
            mZpoV2EvalPsico.setFuncionamientoPuntajePsych(zpoV2EvPsicoXml.getFuncionamientoPuntajePsych());*/
            mZpoV2EvalPsico.setSinEnergiaPsych(zpoV2EvPsicoXml.getSinEnergiaPsych());
            mZpoV2EvalPsico.setCulparseMismaPsych(zpoV2EvPsicoXml.getCulparseMismaPsych());
            mZpoV2EvalPsico.setLlorarPsych(zpoV2EvPsicoXml.getLlorarPsych());
            mZpoV2EvalPsico.setProbConcentPsych(zpoV2EvPsicoXml.getProbConcentPsych());
            mZpoV2EvalPsico.setFaltaApetitoPsych(zpoV2EvPsicoXml.getFaltaApetitoPsych());
            mZpoV2EvalPsico.setDifficulDormirPsych(zpoV2EvPsicoXml.getDifficulDormirPsych());
            mZpoV2EvalPsico.setSinEsperanzaPsych(zpoV2EvPsicoXml.getSinEsperanzaPsych());
            mZpoV2EvalPsico.setTristePsych(zpoV2EvPsicoXml.getTristePsych());
            mZpoV2EvalPsico.setSolaPsych(zpoV2EvPsicoXml.getSolaPsych());
            mZpoV2EvalPsico.setAcabarVidaPsych(zpoV2EvPsicoXml.getAcabarVidaPsych());
            mZpoV2EvalPsico.setPreocuparsePsych(zpoV2EvPsicoXml.getPreocuparsePsych());
            mZpoV2EvalPsico.setNoInteresPsych(zpoV2EvPsicoXml.getNoInteresPsych());
            mZpoV2EvalPsico.setTodoEsfuerzoPsych(zpoV2EvPsicoXml.getTodoEsfuerzoPsych());
            mZpoV2EvalPsico.setSienteNoValePsych(zpoV2EvPsicoXml.getSienteNoValePsych());
            mZpoV2EvalPsico.setNoInteresSexoPsych(zpoV2EvPsicoXml.getNoInteresSexoPsych());
            mZpoV2EvalPsico.setAsustaPsych(zpoV2EvPsicoXml.getAsustaPsych());
            mZpoV2EvalPsico.setSienteMiedoPsych(zpoV2EvPsicoXml.getSienteMiedoPsych());
            mZpoV2EvalPsico.setDebilidadPsych(zpoV2EvPsicoXml.getDebilidadPsych());
            mZpoV2EvalPsico.setNerviosPsych(zpoV2EvPsicoXml.getNerviosPsych());
            mZpoV2EvalPsico.setPalpitacionesPsych(zpoV2EvPsicoXml.getPalpitacionesPsych());
            mZpoV2EvalPsico.setTiemblaPsych(zpoV2EvPsicoXml.getTiemblaPsych());
            mZpoV2EvalPsico.setSienteTensaPsych(zpoV2EvPsicoXml.getSienteTensaPsych());
            mZpoV2EvalPsico.setDolorCabezaPsych(zpoV2EvPsicoXml.getDolorCabezaPsych());
            mZpoV2EvalPsico.setPanicoPsych(zpoV2EvPsicoXml.getPanicoPsych());
            mZpoV2EvalPsico.setInquietudPsych(zpoV2EvPsicoXml.getInquietudPsych());
            mZpoV2EvalPsico.setSintomasPuntajePsych(zpoV2EvPsicoXml.getSintomasPuntajePsych());
            mZpoV2EvalPsico.setScoreDepressionPsych(zpoV2EvPsicoXml.getScoreDepressionPsych());
            mZpoV2EvalPsico.setExaminadorPsych(zpoV2EvPsicoXml.getExaminadorPsych());

            mZpoV2EvalPsico.setRecordDate(new Date());
            mZpoV2EvalPsico.setRecordUser(username);
            mZpoV2EvalPsico.setIdInstancia(idInstancia);
            mZpoV2EvalPsico.setInstancePath(instanceFilePath);
            mZpoV2EvalPsico.setEstado(Constants.STATUS_NOT_SUBMITTED);
            mZpoV2EvalPsico.setStart( zpoV2EvPsicoXml.getStart());
            mZpoV2EvalPsico.setEnd( zpoV2EvPsicoXml.getEnd());
            mZpoV2EvalPsico.setDeviceid( zpoV2EvPsicoXml.getDeviceid());
            mZpoV2EvalPsico.setSimserial( zpoV2EvPsicoXml.getSimserial());
            mZpoV2EvalPsico.setPhonenumber( zpoV2EvPsicoXml.getPhonenumber());
            mZpoV2EvalPsico.setToday( zpoV2EvPsicoXml.getToday());
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
                if (accionaRealizar == ADD_ZPOEPSIC_ODK){
                   zpoA.crearZpoV2EvalPsicologica(mZpoV2EvalPsico);
                }
                else{
                    zpoA.editarZpoV2EvalPsicologica(mZpoV2EvalPsico);
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
