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
import ni.org.ics.zpo.v2.appmovil.parsers.ZpoV2ExamFisicoInfanteXml;
import ni.org.ics.zpo.v2.appmovil.preferences.PreferencesActivity;
import ni.org.ics.zpo.v2.appmovil.utils.Constants;
import ni.org.ics.zpo.v2.appmovil.utils.DateUtil;
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
public class NewZpoV2ExamFisicoInfanteActivity extends AbstractAsyncActivity {

    protected static final String TAG = NewZpoV2ExamFisicoInfanteActivity.class.getSimpleName();

    private ZpoAdapter zpoA;
    private static ZpoV2ExamenFisicoInfante mZpoV2ExamFisicoInfante = new ZpoV2ExamenFisicoInfante();

    public static final int ADD_ZPOEXFI_ODK = 1;
    public static final int EDIT_ZPOEXFI_ODK = 2;

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
        mZpoV2ExamFisicoInfante = (ZpoV2ExamenFisicoInfante) getIntent().getExtras().getSerializable(Constants.OBJECT_EX_FIS_INF);
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
        if (mZpoV2ExamFisicoInfante != null) {
            message.setText(getString(R.string.edit) + " " + getString(R.string.infant_b_2) + "?");
        } else {
            message.setText(getString(R.string.add) + " " + getString(R.string.infant_b_2) + "?");
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
        if (requestCode == ADD_ZPOEXFI_ODK || requestCode == EDIT_ZPOEXFI_ODK) {
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
            if (mZpoV2ExamFisicoInfante == null) {
                //campos de proveedor de collect
                String[] projection = new String[]{
                        "_id", "jrFormId", "displayName"};
                //cursor que busca el formulario
                Cursor c = getContentResolver().query(Constants.CONTENT_URI, projection,
                        "jrFormId = 'ZpoV2_Examen_Fisico_Infante' and displayName = 'Continuacion Estudio ZPO Examen Fisico Infante'", null, null);
                c.moveToFirst();
                //captura el id del formulario
                Integer id = Integer.parseInt(c.getString(0));
                //cierra el cursor
                if (c != null) {
                    c.close();
                }
                formUri = ContentUris.withAppendedId(Constants.CONTENT_URI, id);
                accion = ADD_ZPOEXFI_ODK;
            } else {
                //forma el uri para la instancia en ODK Collect
                Integer id = mZpoV2ExamFisicoInfante.getIdInstancia();
                formUri = ContentUris.withAppendedId(Constants.CONTENT_URI_I, id);
                accion = EDIT_ZPOEXFI_ODK;
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
            ZpoV2ExamFisicoInfanteXml zpoV2ExamFisicoInfanteXml = new ZpoV2ExamFisicoInfanteXml();
            zpoV2ExamFisicoInfanteXml = serializer.read(ZpoV2ExamFisicoInfanteXml.class, source);
            if (accion== ADD_ZPOEXFI_ODK) {
                mZpoV2ExamFisicoInfante = new ZpoV2ExamenFisicoInfante();

                //calcular edad en meses del infante basado en la fecha de registro del formulario
                Date fechaNac = null;
                if (infantData != null) fechaNac = DateUtil.StringToDate(infantData.getInfantBirthDate(), "dd/MM/yyyy");

            if (zpoV2ExamFisicoInfanteXml.getChildExamFecha() != null)
                meses = getEdadMeses( fechaNac, zpoV2ExamFisicoInfanteXml.getChildExamFecha() );
                if (meses != null) mZpoV2ExamFisicoInfante.setChildExamAge( meses );
            }

            mZpoV2ExamFisicoInfante.setRecordId(mRecordId);
            mZpoV2ExamFisicoInfante.setEventName(event);
            mZpoV2ExamFisicoInfante.setChildExamFecha(zpoV2ExamFisicoInfanteXml.getChildExamFecha());
            mZpoV2ExamFisicoInfante.setChildExamPeso(zpoV2ExamFisicoInfanteXml.getChildExamPeso());
            mZpoV2ExamFisicoInfante.setChildExamHeight(zpoV2ExamFisicoInfanteXml.getChildExamHeight());
            mZpoV2ExamFisicoInfante.setChildExamCircumference(zpoV2ExamFisicoInfanteXml.getChildExamCircumference());
            mZpoV2ExamFisicoInfante.setChildExamScarring(zpoV2ExamFisicoInfanteXml.getChildExamScarring());
            mZpoV2ExamFisicoInfante.setChildExamAbdominalDist(zpoV2ExamFisicoInfanteXml.getChildExamAbdominalDist());
            mZpoV2ExamFisicoInfante.setChildExamAbnormalExam(zpoV2ExamFisicoInfanteXml.getChildExamAbnormalExam());
            mZpoV2ExamFisicoInfante.setChildExamDescribeAnomaly(zpoV2ExamFisicoInfanteXml.getChildExamDescribeAnomaly());
            mZpoV2ExamFisicoInfante.setChildExamBloodSample(zpoV2ExamFisicoInfanteXml.getChildExamBloodSample());
            mZpoV2ExamFisicoInfante.setChildExamVolume(zpoV2ExamFisicoInfanteXml.getChildExamVolume());
            mZpoV2ExamFisicoInfante.setChildExamIrritability(zpoV2ExamFisicoInfanteXml.getChildExamIrritability());
            mZpoV2ExamFisicoInfante.setChildExamLethary(zpoV2ExamFisicoInfanteXml.getChildExamLethary());
            mZpoV2ExamFisicoInfante.setChildExamSeizures(zpoV2ExamFisicoInfanteXml.getChildExamSeizures());
            mZpoV2ExamFisicoInfante.setChildExamApnea(zpoV2ExamFisicoInfanteXml.getChildExamApnea());
            mZpoV2ExamFisicoInfante.setChildExamLowTone(zpoV2ExamFisicoInfanteXml.getChildExamLowTone());
            mZpoV2ExamFisicoInfante.setChildExamAssymetry(zpoV2ExamFisicoInfanteXml.getChildExamAssymetry());
            mZpoV2ExamFisicoInfante.setChildExamProbEyeMovt(zpoV2ExamFisicoInfanteXml.getChildExamProbEyeMovt());
            mZpoV2ExamFisicoInfante.setChildExamPromMovement(zpoV2ExamFisicoInfanteXml.getChildExamPromMovement());
            mZpoV2ExamFisicoInfante.setChildExamDysphagia(zpoV2ExamFisicoInfanteXml.getChildExamDysphagia());
            mZpoV2ExamFisicoInfante.setChildExamContCrying(zpoV2ExamFisicoInfanteXml.getChildExamContCrying());
            mZpoV2ExamFisicoInfante.setChildExamArthrogryposis(zpoV2ExamFisicoInfanteXml.getChildExamArthrogryposis());
            mZpoV2ExamFisicoInfante.setChildExamHypertonia(zpoV2ExamFisicoInfanteXml.getChildExamHypertonia());
            mZpoV2ExamFisicoInfante.setChildExamHypotonia(zpoV2ExamFisicoInfanteXml.getChildExamHypotonia());
            mZpoV2ExamFisicoInfante.setChildExamOae(zpoV2ExamFisicoInfanteXml.getChildExamOae());
            mZpoV2ExamFisicoInfante.setChildExamCircumFailed(zpoV2ExamFisicoInfanteXml.getChildExamCircumFailed());
            mZpoV2ExamFisicoInfante.setChildExamCircumstanceDes(zpoV2ExamFisicoInfanteXml.getChildExamCircumstanceDes());
            mZpoV2ExamFisicoInfante.setChildExamCircumstances(zpoV2ExamFisicoInfanteXml.getChildExamCircumstances());
            mZpoV2ExamFisicoInfante.setChildExamOphthalmology(zpoV2ExamFisicoInfanteXml.getChildExamOphthalmology());
            mZpoV2ExamFisicoInfante.setChildExamOpthoFiding(zpoV2ExamFisicoInfanteXml.getChildExamOpthoFiding());
            mZpoV2ExamFisicoInfante.setChildExamLeftEyeFinds(zpoV2ExamFisicoInfanteXml.getChildExamLeftEyeFinds());
            mZpoV2ExamFisicoInfante.setChildExamRightEyeFinds(zpoV2ExamFisicoInfanteXml.getChildExamRightEyeFinds());
            mZpoV2ExamFisicoInfante.setChildExamReferral(zpoV2ExamFisicoInfanteXml.getChildExamReferral());
            mZpoV2ExamFisicoInfante.setChildExamReferralType(zpoV2ExamFisicoInfanteXml.getChildExamReferralType());
            mZpoV2ExamFisicoInfante.setChildExamPersonal(zpoV2ExamFisicoInfanteXml.getChildExamPersonal());

            mZpoV2ExamFisicoInfante.setRecordDate(new Date());
            mZpoV2ExamFisicoInfante.setRecordUser(username);
            mZpoV2ExamFisicoInfante.setIdInstancia(idInstancia);
            mZpoV2ExamFisicoInfante.setInstancePath(instanceFilePath);
            mZpoV2ExamFisicoInfante.setEstado(Constants.STATUS_NOT_SUBMITTED);
            mZpoV2ExamFisicoInfante.setStart( zpoV2ExamFisicoInfanteXml.getStart());
            mZpoV2ExamFisicoInfante.setEnd( zpoV2ExamFisicoInfanteXml.getEnd());
            mZpoV2ExamFisicoInfante.setDeviceid( zpoV2ExamFisicoInfanteXml.getDeviceid());
            mZpoV2ExamFisicoInfante.setSimserial( zpoV2ExamFisicoInfanteXml.getSimserial());
            mZpoV2ExamFisicoInfante.setPhonenumber( zpoV2ExamFisicoInfanteXml.getPhonenumber());
            mZpoV2ExamFisicoInfante.setToday( zpoV2ExamFisicoInfanteXml.getToday());
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
                if (accionaRealizar == ADD_ZPOEXFI_ODK){
                   zpoA.crearZpoV2ExaFisicoInfante(mZpoV2ExamFisicoInfante);
                }
                else{
                    zpoA.editarZpoV2ExamFisicoInfante(mZpoV2ExamFisicoInfante);
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
