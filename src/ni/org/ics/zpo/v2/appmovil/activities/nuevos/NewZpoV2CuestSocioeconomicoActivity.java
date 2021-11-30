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
import ni.org.ics.zpo.v2.appmovil.domain.ZpoV2CuestionarioSocioeconomico;
import ni.org.ics.zpo.v2.appmovil.parsers.ZpoV2CuestSocioeconomicoXml;
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
public class NewZpoV2CuestSocioeconomicoActivity extends AbstractAsyncActivity {

    protected static final String TAG = NewZpoV2CuestSocioeconomicoActivity.class.getSimpleName();

    private ZpoAdapter zpoA;
    private static ZpoV2CuestionarioSocioeconomico mZpoV2CuestSocioec = new ZpoV2CuestionarioSocioeconomico();

    public static final int ADD_ZPOCSOC_ODK = 1;
    public static final int EDIT_ZPOCSOC_ODK = 2;

    Dialog dialogInit;
    private SharedPreferences settings;
    private String username;
    private String mRecordId = "";
    private Integer accion = 0;
    private String event;

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
        mZpoV2CuestSocioec = (ZpoV2CuestionarioSocioeconomico) getIntent().getExtras().getSerializable(Constants.OBJECT_CUEST_SOE);
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
        if (mZpoV2CuestSocioec != null) {
            message.setText(getString(R.string.edit) + " " + getString(R.string.maternal_b_4) + "?");
        } else {
            message.setText(getString(R.string.add) + " " + getString(R.string.maternal_b_4) + "?");
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
        if (requestCode == ADD_ZPOCSOC_ODK || requestCode == EDIT_ZPOCSOC_ODK) {
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
            if (mZpoV2CuestSocioec == null) {
                //campos de proveedor de collect
                String[] projection = new String[]{
                        "_id", "jrFormId", "displayName"};
                //cursor que busca el formulario
                Cursor c = getContentResolver().query(Constants.CONTENT_URI, projection,
                        "jrFormId = 'ZpoV2_Cuestionario_Socioeconomico' and displayName = 'Continuacion Estudio ZPO Cuestionario Socioeconomico del Hogar'", null, null);
                c.moveToFirst();
                //captura el id del formulario
                Integer id = Integer.parseInt(c.getString(0));
                //cierra el cursor
                if (c != null) {
                    c.close();
                }
                formUri = ContentUris.withAppendedId(Constants.CONTENT_URI, id);
                accion = ADD_ZPOCSOC_ODK;
            } else {
                //forma el uri para la instancia en ODK Collect
                Integer id = mZpoV2CuestSocioec.getIdInstancia();
                formUri = ContentUris.withAppendedId(Constants.CONTENT_URI_I, id);
                accion = EDIT_ZPOCSOC_ODK;
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
            ZpoV2CuestSocioeconomicoXml zpoV2CDEMXml = new ZpoV2CuestSocioeconomicoXml();
            zpoV2CDEMXml = serializer.read(ZpoV2CuestSocioeconomicoXml.class, source);
            if (accion== ADD_ZPOCSOC_ODK) mZpoV2CuestSocioec = new ZpoV2CuestionarioSocioeconomico();
            mZpoV2CuestSocioec.setRecordId(mRecordId);
            mZpoV2CuestSocioec.setEventName(event);
            mZpoV2CuestSocioec.setFechaHoySes(zpoV2CDEMXml.getFechaHoySes());
            mZpoV2CuestSocioec.setParedesCasaSes(zpoV2CDEMXml.getParedesCasaSes());
            mZpoV2CuestSocioec.setParedesCasaOtraSes(zpoV2CDEMXml.getParedesCasaOtraSes());
            mZpoV2CuestSocioec.setFuenteAguaSes(zpoV2CDEMXml.getFuenteAguaSes());
            mZpoV2CuestSocioec.setFuenteAguaOtraSes(zpoV2CDEMXml.getFuenteAguaOtraSes());
            mZpoV2CuestSocioec.setAguaIntermitenteSes(zpoV2CDEMXml.getAguaIntermitenteSes());
            mZpoV2CuestSocioec.setGuardadoAguaSes(zpoV2CDEMXml.getGuardadoAguaSes());
            mZpoV2CuestSocioec.setTipoBanoSes(zpoV2CDEMXml.getTipoBanoSes());
            mZpoV2CuestSocioec.setPisoSes(zpoV2CDEMXml.getPisoSes());
            mZpoV2CuestSocioec.setPisoOtroSes(zpoV2CDEMXml.getPisoOtroSes());
            mZpoV2CuestSocioec.setElectricidadSes(zpoV2CDEMXml.getElectricidadSes());
            mZpoV2CuestSocioec.setAireAcondicionadoSes(zpoV2CDEMXml.getAireAcondicionadoSes());
            mZpoV2CuestSocioec.setAbanicoSes(zpoV2CDEMXml.getAbanicoSes());
            mZpoV2CuestSocioec.setMosquiterosSes(zpoV2CDEMXml.getMosquiterosSes());
            mZpoV2CuestSocioec.setAnimalesSes(zpoV2CDEMXml.getAnimalesSes());
            mZpoV2CuestSocioec.setDormitoriosSes(zpoV2CDEMXml.getDormitoriosSes());
            mZpoV2CuestSocioec.setCuantosDuermenSes(zpoV2CDEMXml.getCuantosDuermenSes());
            mZpoV2CuestSocioec.setCuantosAdultosSes(zpoV2CDEMXml.getCuantosAdultosSes());
            mZpoV2CuestSocioec.setCuantosNinosSes(zpoV2CDEMXml.getCuantosNinosSes());
            mZpoV2CuestSocioec.setPersona1NombreSes(zpoV2CDEMXml.getPersona1NombreSes());
            mZpoV2CuestSocioec.setPersona1EdadSes(zpoV2CDEMXml.getPersona1EdadSes());
            mZpoV2CuestSocioec.setPersona1GradoSes(zpoV2CDEMXml.getPersona1GradoSes());
            mZpoV2CuestSocioec.setPersona1OcupacionSes(zpoV2CDEMXml.getPersona1OcupacionSes());
            mZpoV2CuestSocioec.setPersona2NombreSes(zpoV2CDEMXml.getPersona2NombreSes());
            mZpoV2CuestSocioec.setPersona2EdadSes(zpoV2CDEMXml.getPersona2EdadSes());
            mZpoV2CuestSocioec.setPersona2GradoSes(zpoV2CDEMXml.getPersona2GradoSes());
            mZpoV2CuestSocioec.setPersona2OcupacionSes(zpoV2CDEMXml.getPersona2OcupacionSes());
            mZpoV2CuestSocioec.setPersona3NombreSes(zpoV2CDEMXml.getPersona3NombreSes());
            mZpoV2CuestSocioec.setPersona3EdadSes(zpoV2CDEMXml.getPersona3EdadSes());
            mZpoV2CuestSocioec.setPersona3GradoSes(zpoV2CDEMXml.getPersona3GradoSes());
            mZpoV2CuestSocioec.setPersona3OcupacionSes(zpoV2CDEMXml.getPersona3OcupacionSes());
            mZpoV2CuestSocioec.setPersona4NombreSes(zpoV2CDEMXml.getPersona4NombreSes());
            mZpoV2CuestSocioec.setPersona4EdadSes(zpoV2CDEMXml.getPersona4EdadSes());
            mZpoV2CuestSocioec.setPersona4GradoSes(zpoV2CDEMXml.getPersona4GradoSes());
            mZpoV2CuestSocioec.setPersona4OcupacionSes(zpoV2CDEMXml.getPersona4OcupacionSes());
            mZpoV2CuestSocioec.setPersona5NombreSes(zpoV2CDEMXml.getPersona5NombreSes());
            mZpoV2CuestSocioec.setPersona5EdadSes(zpoV2CDEMXml.getPersona5EdadSes());
            mZpoV2CuestSocioec.setPersona5GradoSes(zpoV2CDEMXml.getPersona5GradoSes());
            mZpoV2CuestSocioec.setPersona5OcupacionSes(zpoV2CDEMXml.getPersona5OcupacionSes());
            mZpoV2CuestSocioec.setPersona6NombreSes(zpoV2CDEMXml.getPersona6NombreSes());
            mZpoV2CuestSocioec.setPersona6EdadSes(zpoV2CDEMXml.getPersona6EdadSes());
            mZpoV2CuestSocioec.setPersona6GradoSes(zpoV2CDEMXml.getPersona6GradoSes());
            mZpoV2CuestSocioec.setPersona6OcupacionSes(zpoV2CDEMXml.getPersona6OcupacionSes());
            mZpoV2CuestSocioec.setPersona7NombreSes(zpoV2CDEMXml.getPersona7NombreSes());
            mZpoV2CuestSocioec.setPersona7EdadSes(zpoV2CDEMXml.getPersona7EdadSes());
            mZpoV2CuestSocioec.setPersona7GradoSes(zpoV2CDEMXml.getPersona7GradoSes());
            mZpoV2CuestSocioec.setPersona7OcupacionSes(zpoV2CDEMXml.getPersona7OcupacionSes());
            mZpoV2CuestSocioec.setPersona8NombreSes(zpoV2CDEMXml.getPersona8NombreSes());
            mZpoV2CuestSocioec.setPersona8EdadSes(zpoV2CDEMXml.getPersona8EdadSes());
            mZpoV2CuestSocioec.setPersona8GradoSes(zpoV2CDEMXml.getPersona8GradoSes());
            mZpoV2CuestSocioec.setPersona8OcupacionSes(zpoV2CDEMXml.getPersona8OcupacionSes());
            mZpoV2CuestSocioec.setNombreEncuestadorSes(zpoV2CDEMXml.getNombreEncuestadorSes());
            mZpoV2CuestSocioec.setPreescolarSes(zpoV2CDEMXml.getPreescolarSes());
            mZpoV2CuestSocioec.setCuandoPreescolarSes(zpoV2CDEMXml.getCuandoPreescolarSes());
            mZpoV2CuestSocioec.setAmbosPadresSes(zpoV2CDEMXml.getAmbosPadresSes());

            mZpoV2CuestSocioec.setRecordDate(new Date());
            mZpoV2CuestSocioec.setRecordUser(username);
            mZpoV2CuestSocioec.setIdInstancia(idInstancia);
            mZpoV2CuestSocioec.setInstancePath(instanceFilePath);
            mZpoV2CuestSocioec.setEstado(Constants.STATUS_NOT_SUBMITTED);
            mZpoV2CuestSocioec.setStart( zpoV2CDEMXml.getStart());
            mZpoV2CuestSocioec.setEnd( zpoV2CDEMXml.getEnd());
            mZpoV2CuestSocioec.setDeviceid( zpoV2CDEMXml.getDeviceid());
            mZpoV2CuestSocioec.setSimserial( zpoV2CDEMXml.getSimserial());
            mZpoV2CuestSocioec.setPhonenumber( zpoV2CDEMXml.getPhonenumber());
            mZpoV2CuestSocioec.setToday( zpoV2CDEMXml.getToday());
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
                if (accionaRealizar == ADD_ZPOCSOC_ODK){
                    zpoA.crearZpoV2CuestSocioeco(mZpoV2CuestSocioec);
                }
                else{
                    zpoA.editarZpoV2CuestSocioeco(mZpoV2CuestSocioec);
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
