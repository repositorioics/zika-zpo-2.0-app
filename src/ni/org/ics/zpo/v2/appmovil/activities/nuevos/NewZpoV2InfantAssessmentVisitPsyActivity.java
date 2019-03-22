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
import ni.org.ics.zpo.v2.appmovil.domain.ZpoV2InfantPsychologicalEvaluation;
import ni.org.ics.zpo.v2.appmovil.parsers.ZpoV2InfantPsychologicalEvalXml;
import ni.org.ics.zpo.v2.appmovil.preferences.PreferencesActivity;
import ni.org.ics.zpo.v2.appmovil.utils.Constants;
import ni.org.ics.zpo.v2.appmovil.utils.FileUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.util.Date;

/**
 * Created by FIRSTICT on 2/2/2017.
 * V1.0
 */
public class NewZpoV2InfantAssessmentVisitPsyActivity extends AbstractAsyncActivity {
    protected static final String TAG = NewZpoV2InfantAssessmentVisitPsyActivity.class.getSimpleName();

    private ZpoAdapter zipA;
    private static ZpoV2InfantPsychologicalEvaluation mInfantAssessment = null;

    public static final int ADD_ZP07_ODK = 1;
    public static final int EDIT_ZP07_ODK = 2;

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
            Toast toast = Toast.makeText(getApplicationContext(),getString(R.string.error, R.string.storage_error),Toast.LENGTH_LONG);
            toast.show();
            finish();
        }
        settings =
                PreferenceManager.getDefaultSharedPreferences(this);
        username =
                settings.getString(PreferencesActivity.KEY_USERNAME,
                        null);
        String mPass = ((MyZpoApplication) this.getApplication()).getPassApp();
        zipA = new ZpoAdapter(this.getApplicationContext(),mPass,false,false);
        mRecordId = getIntent().getExtras().getString(Constants.RECORDID);
        mInfantAssessment = (ZpoV2InfantPsychologicalEvaluation) getIntent().getExtras().getSerializable(Constants.OBJECTO_ZP07);
        event = getIntent().getExtras().getString(Constants.EVENT);
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
        TextView message =(TextView) dialogInit.findViewById(R.id.yesnotext);
        if (mInfantAssessment !=null){
            message.setText(getString(R.string.edit)+ " " + getString(R.string.infant_b_11)+"?");
        }
        else{
            message.setText(getString(R.string.add)+ " " + getString(R.string.infant_b_11)+"?");
        }

        //add some action to the buttons

        Button yes = (Button) dialogInit.findViewById(R.id.yesnoYes);
        yes.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                dialogInit.dismiss();
                addZpo07InfantAssessmentVisit();
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
        if(item.getItemId()==R.id.MENU_BACK){
            finish();
            return true;
        }
        else if(item.getItemId()==R.id.MENU_HOME){
            Intent i = new Intent(getApplicationContext(),
                    MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        if(requestCode == ADD_ZP07_ODK ||requestCode == EDIT_ZP07_ODK) {
            if(resultCode == RESULT_OK) {
                Uri instanceUri = intent.getData();
                //Busca la instancia resultado
                String[] projection = new String[] {
                        "_id","instanceFilePath", "status","displaySubtext"};
                Cursor c = getContentResolver().query(instanceUri, projection,
                        null, null, null);
                c.moveToFirst();
                //Captura la id de la instancia y la ruta del archivo para agregarlo al participante
                Integer idInstancia3 = c.getInt(c.getColumnIndex("_id"));
                String instanceFilePath = c.getString(c.getColumnIndex("instanceFilePath"));
                String complete = c.getString(c.getColumnIndex("status"));
                //cierra el cursor
                if (c != null) {
                    c.close();
                }
                if (complete.matches("complete")){
                    //Parsear el resultado obteniendo un tamizaje si esta completo
                    parseZpo07InfantAssessmentVisit(idInstancia3, instanceFilePath, accion);
                }
                else{
                    Toast.makeText(getApplicationContext(),	getString(R.string.err_not_completed), Toast.LENGTH_LONG).show();
                }
            }
            else{
            	finish();
            }
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }

    private void addZpo07InfantAssessmentVisit() {
        try{
            Uri formUri;
            if(mInfantAssessment ==null){
                //campos de proveedor de collect
                String[] projection = new String[] {
                        "_id","jrFormId","displayName"};
                //cursor que busca el formulario
                Cursor c = getContentResolver().query(Constants.CONTENT_URI, projection,
                		"jrFormId = 'zpov2_infant_assessment_psychologist' and displayName = 'Continuacion Estudio ZPO Evaluacion psicologica al infante'", null, null);
                c.moveToFirst();
                //captura el id del formulario
                Integer id = Integer.parseInt(c.getString(0));
                //cierra el cursor
                if (c != null) {
                    c.close();
                }
                //forma el uri para ODK Collect
                formUri = ContentUris.withAppendedId(Constants.CONTENT_URI, id);
                accion = ADD_ZP07_ODK;
            }
            else{

                    //forma el uri para la instancia en ODK Collect
                    Integer id = mInfantAssessment.getIdInstancia();
                    formUri = ContentUris.withAppendedId(Constants.CONTENT_URI_I,id);
                    accion = EDIT_ZP07_ODK;


            }
            //Arranca la actividad ODK Collect en busca de resultado
            Intent odkA =  new Intent(Intent.ACTION_EDIT,formUri);
            startActivityForResult(odkA, accion);
        }
        catch(Exception e){
            //No existe el formulario en el equipo
            Log.e(TAG, e.getMessage(), e);
            Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void parseZpo07InfantAssessmentVisit(Integer idInstancia3, String instanceFilePath, Integer accion) {
        Serializer serializer = new Persister();
        File source = new File(instanceFilePath);
        try {
            ZpoV2InfantPsychologicalEvalXml zp07Xml = serializer.read(ZpoV2InfantPsychologicalEvalXml.class, source);
            if (accion== ADD_ZP07_ODK) mInfantAssessment = new ZpoV2InfantPsychologicalEvaluation();
            mInfantAssessment.setRecordId(mRecordId);
            mInfantAssessment.setEventName(event);
            mInfantAssessment.setInfantVisitDate(zp07Xml.getInfantVisitDate3());
            mInfantAssessment.setInfantStatus(zp07Xml.getInfantStatus3());
            mInfantAssessment.setInfantDeathDt(zp07Xml.getInfantDeathDt3());
            mInfantAssessment.setInfantVisit(zp07Xml.getInfantVisit3());
            mInfantAssessment.setInfantEvaluation(zp07Xml.getInfantEvaluation());
            mInfantAssessment.setInfantNeuroAsq(zp07Xml.getInfantNeuroAsq());
            mInfantAssessment.setInfantAsqCommuni(zp07Xml.getInfantAsqCommuni());
            mInfantAssessment.setInfantAsqGross(zp07Xml.getInfantAsqGross());
            mInfantAssessment.setInfantAsqFine(zp07Xml.getInfantAsqFine());
            mInfantAssessment.setInfantAsqProblem(zp07Xml.getInfantAsqProblem());
            mInfantAssessment.setInfantAsqPersonal(zp07Xml.getInfantAsqPersonal());
            mInfantAssessment.setInfantNeuroBisd(zp07Xml.getInfantNeuroBisd());
            mInfantAssessment.setInfantCgScore(zp07Xml.getInfantCgScore());
            mInfantAssessment.setInfantCgRisk(zp07Xml.getInfantCgRisk());
            mInfantAssessment.setInfantRpScore(zp07Xml.getInfantRpScore());
            mInfantAssessment.setInfantRpRisk(zp07Xml.getInfantRpRisk());
            mInfantAssessment.setInfantEpScore(zp07Xml.getInfantEpScore());
            mInfantAssessment.setInfantEpRisk(zp07Xml.getInfantEpRisk());
            mInfantAssessment.setInfantFmScore(zp07Xml.getInfantFmScore());
            mInfantAssessment.setInfantFmRisk(zp07Xml.getInfantFmRisk());
            mInfantAssessment.setInfantGmScore(zp07Xml.getInfantGmScore());
            mInfantAssessment.setInfantGmRisk(zp07Xml.getInfantGmRisk());
            mInfantAssessment.setInfantNeuroOther(zp07Xml.getInfantNeuroOther());
            mInfantAssessment.setInfantOtherName(zp07Xml.getInfantOtherName());
            mInfantAssessment.setInfantOtherScore(zp07Xml.getInfantOtherScore());
            mInfantAssessment.setInfantResultScreening(zp07Xml.getInfantResultScreening());
            mInfantAssessment.setInfantReferTesting(zp07Xml.getInfantReferTesting());
            mInfantAssessment.setInfantCommentsYn(zp07Xml.getInfantCommentsYn3());
            mInfantAssessment.setInfantComments(zp07Xml.getInfantComments2_3());

            mInfantAssessment.setRecordDate(new Date());
            mInfantAssessment.setRecordUser(username);
            mInfantAssessment.setInstancePath(instanceFilePath);
            mInfantAssessment.setEstado(Constants.STATUS_NOT_SUBMITTED);
            mInfantAssessment.setStart(zp07Xml.getStart());
            mInfantAssessment.setEnd(zp07Xml.getEnd());
            mInfantAssessment.setDeviceid(zp07Xml.getDeviceid());
            mInfantAssessment.setSimserial(zp07Xml.getSimserial());
            mInfantAssessment.setPhonenumber(zp07Xml.getPhonenumber());
            mInfantAssessment.setToday(zp07Xml.getToday());

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
            try {
                accionaRealizar = values[0];
                try {
                    zipA.open();
                    if (accionaRealizar == ADD_ZP07_ODK){
                        zipA.crearZpoV2InfantPsychologicalEvaluation(mInfantAssessment);
                    }
                    else{
                        zipA.crearZpoV2InfantPsychologicalEvaluation(mInfantAssessment);
                    }
                    zipA.close();
                } catch (Exception e) {
                    Log.e(TAG, e.getLocalizedMessage(), e);
                    return "error";
                }
                return "exito";
            } catch (Exception e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return "error";
            }
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
