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
import ni.org.ics.zpo.v2.appmovil.domain.ZpoV2CuestSaludInfantil;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoV2CuestionarioSaludMaterna;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoV2RecoleccionMuestra;
import ni.org.ics.zpo.v2.appmovil.parsers.ZpoV2CuestSaludInfantilXml;
import ni.org.ics.zpo.v2.appmovil.parsers.ZpoV2CuestSaludMaternaXml;
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
public class NewZpoV2CuestSaludMaternaActivity extends AbstractAsyncActivity {

    protected static final String TAG = NewZpoV2CuestSaludMaternaActivity.class.getSimpleName();

    private ZpoAdapter zpoA;
    private static ZpoV2CuestionarioSaludMaterna mZpoV2CuestSaludMat = new ZpoV2CuestionarioSaludMaterna();

    public static final int ADD_ZPO_CSM_ODK = 1;
    public static final int EDIT_ZPO_CSM_ODK = 2;

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
        zpoA = new ZpoAdapter(this.getApplicationContext(),mPass,false,false);
        mRecordId = getIntent().getExtras().getString(Constants.RECORDID);
        event = getIntent().getExtras().getString(Constants.EVENT);
        mZpoV2CuestSaludMat = (ZpoV2CuestionarioSaludMaterna) getIntent().getExtras().getSerializable(Constants.OBJECT_CUEST_SA_MAT);
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
        if (mZpoV2CuestSaludMat != null) {
            message.setText(getString(R.string.edit) + " " + getString(R.string.maternal_b_1) + "?");
        } else {
            message.setText(getString(R.string.add) + " " + getString(R.string.maternal_b_1) + "?");
        }

        //add some action to the buttons

        Button yes = (Button) dialogInit.findViewById(R.id.yesnoYes);
        yes.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                dialogInit.dismiss();
                addZpoCuestSaludInfantil();
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
        if (requestCode == ADD_ZPO_CSM_ODK || requestCode == EDIT_ZPO_CSM_ODK) {
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
                    parseZpoCuestDemografico(idInstancia, instanceFilePath, accion);
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.err_not_completed), Toast.LENGTH_LONG).show();
                }
            } else {
                finish();
            }
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }

    private void addZpoCuestSaludInfantil() {
        try {
            Uri formUri;
            if (mZpoV2CuestSaludMat == null) {
                //campos de proveedor de collect
                String[] projection = new String[]{
                        "_id", "jrFormId", "displayName"};
                //cursor que busca el formulario
                Cursor c = getContentResolver().query(Constants.CONTENT_URI, projection,
                        "jrFormId = 'ZpoV2_Cuestionario_Salud_Materna' and displayName = 'Continuacion Estudio ZPO Cuestionario Salud Materna'", null, null);
                c.moveToFirst();
                //captura el id del formulario
                Integer id = Integer.parseInt(c.getString(0));
                //cierra el cursor
                if (c != null) {
                    c.close();
                }
                formUri = ContentUris.withAppendedId(Constants.CONTENT_URI, id);
                accion = ADD_ZPO_CSM_ODK;
            } else {
                //forma el uri para la instancia en ODK Collect
                Integer id = mZpoV2CuestSaludMat.getIdInstancia();
                formUri = ContentUris.withAppendedId(Constants.CONTENT_URI_I, id);
                accion = EDIT_ZPO_CSM_ODK;
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

    private void parseZpoCuestDemografico(Integer idInstancia, String instanceFilePath, Integer accion) {
        Serializer serializer = new Persister();
        File source = new File(instanceFilePath);
        try {
            ZpoV2CuestSaludMaternaXml zpoV2CSMXml = new ZpoV2CuestSaludMaternaXml();
            zpoV2CSMXml = serializer.read(ZpoV2CuestSaludMaternaXml.class, source);
            if (accion== ADD_ZPO_CSM_ODK) mZpoV2CuestSaludMat = new ZpoV2CuestionarioSaludMaterna();
            mZpoV2CuestSaludMat.setRecordId(mRecordId);
            mZpoV2CuestSaludMat.setEventName(event);
            mZpoV2CuestSaludMat.setFechaHoyMaternal(zpoV2CSMXml.getFechaHoyMaternal());
            mZpoV2CuestSaludMat.setProbFueraEmbarMaternal(zpoV2CSMXml.getProbFueraEmbarMaternal());
            mZpoV2CuestSaludMat.setOtroProblemaMaternal(zpoV2CSMXml.getOtroProblemaMaternal());
            mZpoV2CuestSaludMat.setMedicamActualMaternal(zpoV2CSMXml.getMedicamActualMaternal());
            mZpoV2CuestSaludMat.setOtroMedicamMaternal(zpoV2CSMXml.getOtroMedicamMaternal());
            mZpoV2CuestSaludMat.setFumaCigarrosMaternal(zpoV2CSMXml.getFumaCigarrosMaternal());
            mZpoV2CuestSaludMat.setFumoEmbaraMaternal(zpoV2CSMXml.getFumoEmbaraMaternal());
            mZpoV2CuestSaludMat.setTomaAlcoholMaternal(zpoV2CSMXml.getTomaAlcoholMaternal());
            mZpoV2CuestSaludMat.setAlcoholEmbarMaternal(zpoV2CSMXml.getAlcoholEmbarMaternal());
            mZpoV2CuestSaludMat.setFrecuenciaAlcoholMaternal(zpoV2CSMXml.getFrecuenciaAlcoholMaternal());
            mZpoV2CuestSaludMat.setVecesEmbarazadaMaternal(zpoV2CSMXml.getVecesEmbarazadaMaternal());
            mZpoV2CuestSaludMat.setHijosVivosMaternal(zpoV2CSMXml.getHijosVivosMaternal());
            mZpoV2CuestSaludMat.setDefectosGeneticosMaternal(zpoV2CSMXml.getDefectosGeneticosMaternal());
            mZpoV2CuestSaludMat.setDefectoGenetico1Maternal(zpoV2CSMXml.getDefectoGenetico1Maternal());
            mZpoV2CuestSaludMat.setQuienDefecto1Maternal(zpoV2CSMXml.getQuienDefecto1Maternal());
            mZpoV2CuestSaludMat.setOtroDefectoMaternal(zpoV2CSMXml.getOtroDefectoMaternal());
            mZpoV2CuestSaludMat.setDefectoGenetico2Maternal(zpoV2CSMXml.getDefectoGenetico2Maternal());
            mZpoV2CuestSaludMat.setQuienDefecto2Maternal(zpoV2CSMXml.getQuienDefecto2Maternal());
            mZpoV2CuestSaludMat.setNombreEncuestadorMaternal(zpoV2CSMXml.getNombreEncuestadorMaternal());

            mZpoV2CuestSaludMat.setRecordDate(new Date());
            mZpoV2CuestSaludMat.setRecordUser(username);
            mZpoV2CuestSaludMat.setIdInstancia(idInstancia);
            mZpoV2CuestSaludMat.setInstancePath(instanceFilePath);
            mZpoV2CuestSaludMat.setEstado(Constants.STATUS_NOT_SUBMITTED);
            mZpoV2CuestSaludMat.setStart( zpoV2CSMXml.getStart());
            mZpoV2CuestSaludMat.setEnd( zpoV2CSMXml.getEnd());
            mZpoV2CuestSaludMat.setDeviceid( zpoV2CSMXml.getDeviceid());
            mZpoV2CuestSaludMat.setSimserial( zpoV2CSMXml.getSimserial());
            mZpoV2CuestSaludMat.setPhonenumber( zpoV2CSMXml.getPhonenumber());
            mZpoV2CuestSaludMat.setToday( zpoV2CSMXml.getToday());
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
                if (accionaRealizar == ADD_ZPO_CSM_ODK){
                    zpoA.crearZpoV2CuestSaludMaterna( mZpoV2CuestSaludMat );
                }
                else{
                    zpoA.editarZpoV2CuestSaludMaterna( mZpoV2CuestSaludMat );
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
