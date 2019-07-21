package ni.org.ics.zpo.v2.appmovil.activities.nuevos;

import android.app.Dialog;
import android.content.*;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.*;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import ni.org.ics.zpo.v2.appmovil.AbstractAsyncActivity;
import ni.org.ics.zpo.v2.appmovil.MainActivity;
import ni.org.ics.zpo.v2.appmovil.MyZpoApplication;
import ni.org.ics.zpo.v2.appmovil.R;
import ni.org.ics.zpo.v2.appmovil.database.ZpoAdapter;
import ni.org.ics.zpo.v2.appmovil.parsers.ZpoV2MullenXml;
import ni.org.ics.zpo.v2.appmovil.preferences.PreferencesActivity;
import ni.org.ics.zpo.v2.appmovil.utils.Constants;
import ni.org.ics.zpo.v2.appmovil.utils.FileUtils;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoV2Mullen;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.util.Date;

/**
 * @author ics
 */
public class NewZpoV2MullenActivity extends AbstractAsyncActivity {

    protected static final String TAG = NewZpoV2MullenActivity.class.getSimpleName();

    private ZpoAdapter zpoA;
    private static ZpoV2Mullen mZpoV2Mullen = new ZpoV2Mullen();

    public static final int ADD_ZPOM_ODK = 1;
    public static final int EDIT_ZPOM_ODK = 2;

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
        zpoA = new ZpoAdapter(this.getApplicationContext(), mPass, false, false);
        mRecordId = getIntent().getExtras().getString(Constants.RECORDID);
        event = getIntent().getExtras().getString(Constants.EVENT);
        mZpoV2Mullen = (ZpoV2Mullen) getIntent().getExtras().getSerializable(Constants.OBJECTO_ZPOMULLEN );
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
        if (mZpoV2Mullen != null) {
            message.setText(getString(R.string.edit) + " " + getString(R.string.infant_b_7) + "?");
        } else {
            message.setText(getString(R.string.add) + " " + getString(R.string.infant_b_7) + "?");
        }

        //add some action to the buttons

        Button yes = (Button) dialogInit.findViewById(R.id.yesnoYes);
        yes.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                dialogInit.dismiss();
                addZpoMullen();
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
        if (requestCode == ADD_ZPOM_ODK || requestCode == EDIT_ZPOM_ODK) {
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
                    parseZpoMullen(idInstancia, instanceFilePath, accion);
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.err_not_completed), Toast.LENGTH_LONG).show();
                }
            } else {
                finish();
            }
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }

    private void addZpoMullen() {
        try {
            Uri formUri;
            if (mZpoV2Mullen == null) {
                //campos de proveedor de collect
                String[] projection = new String[]{
                        "_id", "jrFormId", "displayName"};
                //cursor que busca el formulario
                Cursor c = getContentResolver().query(Constants.CONTENT_URI, projection,
                        "jrFormId = 'ZPoV2_Mullen' and displayName = 'Continuacion Estudio ZPO Escala Mullen de Aprendizaje Temprano'", null, null);
                c.moveToFirst();
                //captura el id del formulario
                Integer id = Integer.parseInt(c.getString(0));
                //cierra el cursor
                if (c != null) {
                    c.close();
                }
                formUri = ContentUris.withAppendedId(Constants.CONTENT_URI, id);
                accion = ADD_ZPOM_ODK;
            } else {
                //forma el uri para la instancia en ODK Collect
                Integer id = mZpoV2Mullen.getIdInstancia();
                formUri = ContentUris.withAppendedId(Constants.CONTENT_URI_I, id);
                accion = EDIT_ZPOM_ODK;
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

    private void parseZpoMullen(Integer idInstancia, String instanceFilePath, Integer accion) {
        Serializer serializer = new Persister();
        File source = new File(instanceFilePath);
        try {
            ZpoV2MullenXml zpoV2MullenXml = new ZpoV2MullenXml();
            zpoV2MullenXml = serializer.read(ZpoV2MullenXml.class, source);
            if (accion==ADD_ZPOM_ODK) mZpoV2Mullen = new ZpoV2Mullen();
            mZpoV2Mullen.setRecordId(mRecordId);
            mZpoV2Mullen.setEventName(event);
            mZpoV2Mullen.setSexMsel( zpoV2MullenXml.getSexMsel());
            mZpoV2Mullen.setRaNameMsel( zpoV2MullenXml.getRaNameMsel());
            mZpoV2Mullen.setVisitMonthsMsel( zpoV2MullenXml.getVisitMonthsMsel());
            mZpoV2Mullen.setVisProbMsel( zpoV2MullenXml.getVisProbMsel());
            mZpoV2Mullen.setDesVisProbMsel( zpoV2MullenXml.getDesVisProbMsel());
            mZpoV2Mullen.setHearProbMsel(zpoV2MullenXml.getHearProbMsel());
            mZpoV2Mullen.setDesHearProbMsel(zpoV2MullenXml.getDesHearProbMsel());
            mZpoV2Mullen.setTestingDateMsel(zpoV2MullenXml.getTestingDateMsel());
            mZpoV2Mullen.setEddMsel(zpoV2MullenXml.getEddMsel());
            mZpoV2Mullen.setAdjAgeMsel(zpoV2MullenXml.getAdjAgeMsel());
            mZpoV2Mullen.setActDobMsel(zpoV2MullenXml.getActDobMsel());
            mZpoV2Mullen.setGmRaw(zpoV2MullenXml.getGmRaw());
            mZpoV2Mullen.setGmTScore(zpoV2MullenXml.getGmTScore());
            mZpoV2Mullen.setGmBoe(zpoV2MullenXml.getGmBoe());
            mZpoV2Mullen.setGmPerRank(zpoV2MullenXml.getGmPerRank());
            mZpoV2Mullen.setGmDesCat(zpoV2MullenXml.getGmDesCat());
            mZpoV2Mullen.setGmAgeEqu(zpoV2MullenXml.getGmAgeEqu());
            mZpoV2Mullen.setVrRaw(zpoV2MullenXml.getVrRaw());
            mZpoV2Mullen.setVrTScore(zpoV2MullenXml.getVrTScore());
            mZpoV2Mullen.setVrBoe(zpoV2MullenXml.getVrBoe());
            mZpoV2Mullen.setVrPerRank(zpoV2MullenXml.getVrPerRank());
            mZpoV2Mullen.setVrDesCat(zpoV2MullenXml.getVrDesCat());
            mZpoV2Mullen.setVrAgeEqu(zpoV2MullenXml.getVrAgeEqu());
            mZpoV2Mullen.setFmRaw(zpoV2MullenXml.getFmRaw());
            mZpoV2Mullen.setFmTScore(zpoV2MullenXml.getFmTScore());
            mZpoV2Mullen.setFmBoe(zpoV2MullenXml.getFmBoe());
            mZpoV2Mullen.setFmPerRank(zpoV2MullenXml.getFmPerRank());
            mZpoV2Mullen.setFmDesCat(zpoV2MullenXml.getFmDesCat());
            mZpoV2Mullen.setFmAgeEqu(zpoV2MullenXml.getFmAgeEqu());
            mZpoV2Mullen.setRlRaw(zpoV2MullenXml.getRlRaw());
            mZpoV2Mullen.setRlTScore(zpoV2MullenXml.getRlTScore());
            mZpoV2Mullen.setRlBoe(zpoV2MullenXml.getRlBoe());
            mZpoV2Mullen.setRlPerRank(zpoV2MullenXml.getRlPerRank());
            mZpoV2Mullen.setRlDesCat(zpoV2MullenXml.getRlDesCat());
            mZpoV2Mullen.setRlAgeEqu(zpoV2MullenXml.getRlAgeEqu());
            mZpoV2Mullen.setElRaw(zpoV2MullenXml.getElRaw());
            mZpoV2Mullen.setElTScore(zpoV2MullenXml.getElTScore());
            mZpoV2Mullen.setElBoe(zpoV2MullenXml.getElBoe());
            mZpoV2Mullen.setElPerRank(zpoV2MullenXml.getElPerRank());
            mZpoV2Mullen.setElDesCat(zpoV2MullenXml.getElDesCat());
            mZpoV2Mullen.setElAgeEqu(zpoV2MullenXml.getElAgeEqu());
            mZpoV2Mullen.setCognTScoreSum(zpoV2MullenXml.getCognTScoreSum());
            mZpoV2Mullen.setElcStandScore(zpoV2MullenXml.getElcStandScore());
            mZpoV2Mullen.setElcBoe(zpoV2MullenXml.getElcBoe());
            mZpoV2Mullen.setElcPerRank(zpoV2MullenXml.getElcPerRank());
            mZpoV2Mullen.setElcDesCat(zpoV2MullenXml.getElcDesCat());
            mZpoV2Mullen.setMselComment(zpoV2MullenXml.getMselComment());

            mZpoV2Mullen.setRecordDate(new Date());
            mZpoV2Mullen.setRecordUser(username);
            mZpoV2Mullen.setIdInstancia(idInstancia);
            mZpoV2Mullen.setInstancePath(instanceFilePath);
            mZpoV2Mullen.setEstado(Constants.STATUS_NOT_SUBMITTED);
            mZpoV2Mullen.setStart( zpoV2MullenXml.getStart());
            mZpoV2Mullen.setEnd( zpoV2MullenXml.getEnd());
            mZpoV2Mullen.setDeviceid( zpoV2MullenXml.getDeviceid());
            mZpoV2Mullen.setSimserial( zpoV2MullenXml.getSimserial());
            mZpoV2Mullen.setPhonenumber( zpoV2MullenXml.getPhonenumber());
            mZpoV2Mullen.setToday( zpoV2MullenXml.getToday());
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
                if (accionaRealizar == ADD_ZPOM_ODK){
                    zpoA.crearZpoV2Mullen(mZpoV2Mullen);
                }
                else{
                    zpoA.editarZpoV2Mullen(mZpoV2Mullen);
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
