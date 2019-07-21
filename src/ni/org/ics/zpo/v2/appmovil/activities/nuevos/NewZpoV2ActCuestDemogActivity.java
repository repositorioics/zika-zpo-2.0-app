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
import ni.org.ics.zpo.v2.appmovil.parsers.ZpoV2CuestDemograficoXml;
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
public class NewZpoV2ActCuestDemogActivity extends AbstractAsyncActivity {

    protected static final String TAG = NewZpoV2ActCuestDemogActivity.class.getSimpleName();

    private ZpoAdapter zpoA;
    private static ZpoV2CuestionarioDemografico mZpoV2CuestDemog = new ZpoV2CuestionarioDemografico();

    public static final int ADD_ZPOCDEM_ODK = 1;
    public static final int EDIT_ZPOCDEM_ODK = 2;

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
        mZpoV2CuestDemog = (ZpoV2CuestionarioDemografico) getIntent().getExtras().getSerializable(Constants.OBJECT_CUEST_DEMO);
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
        if (mZpoV2CuestDemog != null) {
            message.setText(getString(R.string.edit) + " " + getString(R.string.infant_b_16) + "?");
        } else {
            message.setText(getString(R.string.add) + " " + getString(R.string.infant_b_16) + "?");
        }

        //add some action to the buttons

        Button yes = (Button) dialogInit.findViewById(R.id.yesnoYes);
        yes.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                dialogInit.dismiss();
                addZpoCuestDemografico();
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
        if (requestCode == ADD_ZPOCDEM_ODK || requestCode == EDIT_ZPOCDEM_ODK) {
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

    private void addZpoCuestDemografico() {
        try {
            Uri formUri;
            if (mZpoV2CuestDemog == null) {
                //campos de proveedor de collect
                String[] projection = new String[]{
                        "_id", "jrFormId", "displayName"};
                //cursor que busca el formulario
                Cursor c = getContentResolver().query(Constants.CONTENT_URI, projection,
                        "jrFormId = 'ZpoV2_ActCuestDemografico' and displayName = 'Continuacion Estudio ZPO Actualizacion Cuestionario Demografico'", null, null);
                c.moveToFirst();
                //captura el id del formulario
                Integer id = Integer.parseInt(c.getString(0));
                //cierra el cursor
                if (c != null) {
                    c.close();
                }
                formUri = ContentUris.withAppendedId(Constants.CONTENT_URI, id);
                accion = ADD_ZPOCDEM_ODK;
            } else {
                //forma el uri para la instancia en ODK Collect
                Integer id = mZpoV2CuestDemog.getIdInstancia();
                formUri = ContentUris.withAppendedId(Constants.CONTENT_URI_I, id);
                accion = EDIT_ZPOCDEM_ODK;
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
            ZpoV2CuestDemograficoXml zpoV2CDEMXml = new ZpoV2CuestDemograficoXml();
            zpoV2CDEMXml = serializer.read(ZpoV2CuestDemograficoXml.class, source);
            if (accion== ADD_ZPOCDEM_ODK) mZpoV2CuestDemog = new ZpoV2CuestionarioDemografico();
            mZpoV2CuestDemog.setRecordId(mRecordId);
            mZpoV2CuestDemog.setEventName(event);
            mZpoV2CuestDemog.setFechaHoy(zpoV2CDEMXml.getFechaHoy());
            mZpoV2CuestDemog.setDirecBarrioDemogr(zpoV2CDEMXml.getDirecBarrioDemogr());
            mZpoV2CuestDemog.setDirecExactaDemogr(zpoV2CDEMXml.getDirecExactaDemogr());
            mZpoV2CuestDemog.setDirecColorCasaDemogr(zpoV2CDEMXml.getDirecColorCasaDemogr());
            mZpoV2CuestDemog.setNrosTelefonicosDemogr(zpoV2CDEMXml.getNrosTelefonicosDemogr());
            mZpoV2CuestDemog.setNroCelularDemogr(zpoV2CDEMXml.getNroCelularDemogr());;
            mZpoV2CuestDemog.setEstadoCivilDemogr(zpoV2CDEMXml.getEstadoCivilDemogr());
            mZpoV2CuestDemog.setNombreEncuestadorDemogr(zpoV2CDEMXml.getNombreEncuestadorDemogr());
            mZpoV2CuestDemog.setRecordDate(new Date());
            mZpoV2CuestDemog.setRecordUser(username);
            mZpoV2CuestDemog.setIdInstancia(idInstancia);
            mZpoV2CuestDemog.setInstancePath(instanceFilePath);
            mZpoV2CuestDemog.setEstado(Constants.STATUS_NOT_SUBMITTED);
            mZpoV2CuestDemog.setStart( zpoV2CDEMXml.getStart());
            mZpoV2CuestDemog.setEnd( zpoV2CDEMXml.getEnd());
            mZpoV2CuestDemog.setDeviceid( zpoV2CDEMXml.getDeviceid());
            mZpoV2CuestDemog.setSimserial( zpoV2CDEMXml.getSimserial());
            mZpoV2CuestDemog.setPhonenumber( zpoV2CDEMXml.getPhonenumber());
            mZpoV2CuestDemog.setToday( zpoV2CDEMXml.getToday());
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
                if (accionaRealizar == ADD_ZPOCDEM_ODK){
                    zpoA.crearZpoV2CuestDemografico(mZpoV2CuestDemog);
                }
                else{
                    zpoA.editarZpoV2CuestDemo(mZpoV2CuestDemog);
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
