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
import ni.org.ics.zpo.v2.appmovil.domain.ZpoV2CuestionarioDemografico;
import ni.org.ics.zpo.v2.appmovil.parsers.ZpoV2CuestDemograficoXml;
import ni.org.ics.zpo.v2.appmovil.parsers.ZpoV2CuestSaludInfantilXml;
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
public class NewZpoV2CuestSaludInfantilActivity extends AbstractAsyncActivity {

    protected static final String TAG = NewZpoV2CuestSaludInfantilActivity.class.getSimpleName();

    private ZpoAdapter zpoA;
    private static ZpoV2CuestSaludInfantil mZpoV2CuestSaludInf = new ZpoV2CuestSaludInfantil();

    public static final int ADD_ZPO_CSI_ODK = 1;
    public static final int EDIT_ZPO_CSI_ODK = 2;

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
        mZpoV2CuestSaludInf = (ZpoV2CuestSaludInfantil) getIntent().getExtras().getSerializable(Constants.OBJECT_CUEST_SA_INF);
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
        if (mZpoV2CuestSaludInf != null) {
            message.setText(getString(R.string.edit) + " " + getString(R.string.infant_b_17) + "?");
        } else {
            message.setText(getString(R.string.add) + " " + getString(R.string.infant_b_17) + "?");
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
        if (requestCode == ADD_ZPO_CSI_ODK || requestCode == EDIT_ZPO_CSI_ODK) {
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
            if (mZpoV2CuestSaludInf == null) {
                //campos de proveedor de collect
                String[] projection = new String[]{
                        "_id", "jrFormId", "displayName"};
                //cursor que busca el formulario
                Cursor c = getContentResolver().query(Constants.CONTENT_URI, projection,
                        "jrFormId = 'ZpoV2_Cuestionario_Salud_Infantil' and displayName = 'Continuacion Estudio ZPO Cuestionario Salud Infantil'", null, null);
                c.moveToFirst();
                //captura el id del formulario
                Integer id = Integer.parseInt(c.getString(0));
                //cierra el cursor
                if (c != null) {
                    c.close();
                }
                formUri = ContentUris.withAppendedId(Constants.CONTENT_URI, id);
                accion = ADD_ZPO_CSI_ODK;
            } else {
                //forma el uri para la instancia en ODK Collect
                Integer id = mZpoV2CuestSaludInf.getIdInstancia();
                formUri = ContentUris.withAppendedId(Constants.CONTENT_URI_I, id);
                accion = EDIT_ZPO_CSI_ODK;
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
            ZpoV2CuestSaludInfantilXml zpoV2CSIXml = new ZpoV2CuestSaludInfantilXml();
            zpoV2CSIXml = serializer.read(ZpoV2CuestSaludInfantilXml.class, source);
            if (accion== ADD_ZPO_CSI_ODK) mZpoV2CuestSaludInf = new ZpoV2CuestSaludInfantil();
            mZpoV2CuestSaludInf.setRecordId(mRecordId);
            mZpoV2CuestSaludInf.setEventName(event);
            mZpoV2CuestSaludInf.setFechaHoyNino(zpoV2CSIXml.getFechaHoyNino());
            mZpoV2CuestSaludInf.setPesoNacerNino(zpoV2CSIXml.getPesoNacerNino());
            mZpoV2CuestSaludInf.setTallaNacerNino(zpoV2CSIXml.getTallaNacerNino());
            mZpoV2CuestSaludInf.setCircunferenciaNacerNino(zpoV2CSIXml.getCircunferenciaNacerNino());
            mZpoV2CuestSaludInf.setEdadGestacionalNino(zpoV2CSIXml.getEdadGestacionalNino());
            mZpoV2CuestSaludInf.setPartoMultipleNino(zpoV2CSIXml.getPartoMultipleNino());
            mZpoV2CuestSaludInf.setProbEmbarazoNino(zpoV2CSIXml.getProbEmbarazoNino());
            mZpoV2CuestSaludInf.setProbEmbarazoOtroNino(zpoV2CSIXml.getProbEmbarazoOtroNino());
            mZpoV2CuestSaludInf.setOcurrioEmbarazoNino(zpoV2CSIXml.getOcurrioEmbarazoNino());
            mZpoV2CuestSaludInf.setProblemasBebeNino(zpoV2CSIXml.getProblemasBebeNino());
            mZpoV2CuestSaludInf.setProblemasExtremNino(zpoV2CSIXml.getProblemasExtremNino());
            mZpoV2CuestSaludInf.setProblemasLactanciaNino(zpoV2CSIXml.getProblemasLactanciaNino());
            mZpoV2CuestSaludInf.setVisionProbNino(zpoV2CSIXml.getVisionProbNino());
            mZpoV2CuestSaludInf.setVisionDescribaNino(zpoV2CSIXml.getVisionDescribaNino());
            mZpoV2CuestSaludInf.setAudicionProbNino(zpoV2CSIXml.getAudicionProbNino());
            mZpoV2CuestSaludInf.setAudicionDescribaNino(zpoV2CSIXml.getAudicionDescribaNino());
            mZpoV2CuestSaludInf.setNeuroProbNino(zpoV2CSIXml.getNeuroProbNino());
            mZpoV2CuestSaludInf.setMedicamentoNino(zpoV2CSIXml.getMedicamentoNino());
            mZpoV2CuestSaludInf.setMedicamentoListaNino(zpoV2CSIXml.getMedicamentoListaNino());
            mZpoV2CuestSaludInf.setAmamantandoNino(zpoV2CSIXml.getAmamantandoNino());
            mZpoV2CuestSaludInf.setFechaAmamantarNino(zpoV2CSIXml.getFechaAmamantarNino());
            mZpoV2CuestSaludInf.setTiempoFueraNino(zpoV2CSIXml.getTiempoFueraNino());
            mZpoV2CuestSaludInf.setParteDiaAfueraNino(zpoV2CSIXml.getParteDiaAfueraNino());
            mZpoV2CuestSaludInf.setQuienCuidaNino(zpoV2CSIXml.getQuienCuidaNino());
            mZpoV2CuestSaludInf.setMayoriaTiempoNino(zpoV2CSIXml.getMayoriaTiempoNino());
            mZpoV2CuestSaludInf.setPicadurasNino(zpoV2CSIXml.getPicadurasNino());
            mZpoV2CuestSaludInf.setMosquiteroDormirNino(zpoV2CSIXml.getMosquiteroDormirNino());
            mZpoV2CuestSaludInf.setMosquiteroFrecuenciaNino(zpoV2CSIXml.getMosquiteroFrecuenciaNino());
            mZpoV2CuestSaludInf.setRepelenteInsectosNino(zpoV2CSIXml.getRepelenteInsectosNino());
            mZpoV2CuestSaludInf.setRepelenteFrecuenciaNino(zpoV2CSIXml.getRepelenteFrecuenciaNino());
            mZpoV2CuestSaludInf.setMinisterioFueraNino(zpoV2CSIXml.getMinisterioFueraNino());
            mZpoV2CuestSaludInf.setUltimaVezFueraNino(zpoV2CSIXml.getUltimaVezFueraNino());
            mZpoV2CuestSaludInf.setMinisterioDentroNino(zpoV2CSIXml.getMinisterioDentroNino());
            mZpoV2CuestSaludInf.setUltimaVezDentroNino(zpoV2CSIXml.getUltimaVezDentroNino());
            mZpoV2CuestSaludInf.setAplicaAbateNino(zpoV2CSIXml.getAplicaAbateNino());
            mZpoV2CuestSaludInf.setUltimaVezAbateNino(zpoV2CSIXml.getUltimaVezAbateNino());
            mZpoV2CuestSaludInf.setInsecticidaAmbientalNino(zpoV2CSIXml.getInsecticidaAmbientalNino());
            mZpoV2CuestSaludInf.setUltimaVezInsecticidaNino(zpoV2CSIXml.getUltimaVezInsecticidaNino());
            mZpoV2CuestSaludInf.setFiebreAmarillaNino(zpoV2CSIXml.getFiebreAmarillaNino());
            mZpoV2CuestSaludInf.setFechaFiebreAmarillaNino(zpoV2CSIXml.getFechaFiebreAmarillaNino());
            mZpoV2CuestSaludInf.setTransfusionSangreNino(zpoV2CSIXml.getTransfusionSangreNino());
            mZpoV2CuestSaludInf.setFechaTransfusionNino(zpoV2CSIXml.getFechaTransfusionNino());
            mZpoV2CuestSaludInf.setPaisesFueraNino(zpoV2CSIXml.getPaisesFueraNino());
            mZpoV2CuestSaludInf.setDondePaisAfueraNino(zpoV2CSIXml.getDondePaisAfueraNino());
            mZpoV2CuestSaludInf.setFueraManaguaNino(zpoV2CSIXml.getFueraManaguaNino());
            mZpoV2CuestSaludInf.setAdondeFueraManaguaNino(zpoV2CSIXml.getAdondeFueraManaguaNino());
            mZpoV2CuestSaludInf.setVistoMedicoNino(zpoV2CSIXml.getVistoMedicoNino());
            mZpoV2CuestSaludInf.setMotivoMedicoNino(zpoV2CSIXml.getMotivoMedicoNino());
            mZpoV2CuestSaludInf.setVisitaEnfermedadNino(zpoV2CSIXml.getVisitaEnfermedadNino());
            mZpoV2CuestSaludInf.setProblemasNino(zpoV2CSIXml.getProblemasNino());
            mZpoV2CuestSaludInf.setProblemasOtroNino(zpoV2CSIXml.getProblemasOtroNino());
            mZpoV2CuestSaludInf.setDiagnosticadoZikaNino(zpoV2CSIXml.getDiagnosticadoZikaNino());
            mZpoV2CuestSaludInf.setFechaZikaNino(zpoV2CSIXml.getFechaZikaNino());
            mZpoV2CuestSaludInf.setDiagnosChikungunyaNino(zpoV2CSIXml.getDiagnosChikungunyaNino());
            mZpoV2CuestSaludInf.setFechaChikungunyaNino(zpoV2CSIXml.getFechaChikungunyaNino());
            mZpoV2CuestSaludInf.setDiagnosticadoDengueNino(zpoV2CSIXml.getDiagnosticadoDengueNino());
            mZpoV2CuestSaludInf.setFechaDengueNino(zpoV2CSIXml.getFechaDengueNino());
            mZpoV2CuestSaludInf.setNombreEncuestadorNino(zpoV2CSIXml.getNombreEncuestadorNino());

            mZpoV2CuestSaludInf.setRecordDate(new Date());
            mZpoV2CuestSaludInf.setRecordUser(username);
            mZpoV2CuestSaludInf.setIdInstancia(idInstancia);
            mZpoV2CuestSaludInf.setInstancePath(instanceFilePath);
            mZpoV2CuestSaludInf.setEstado(Constants.STATUS_NOT_SUBMITTED);
            mZpoV2CuestSaludInf.setStart( zpoV2CSIXml.getStart());
            mZpoV2CuestSaludInf.setEnd( zpoV2CSIXml.getEnd());
            mZpoV2CuestSaludInf.setDeviceid( zpoV2CSIXml.getDeviceid());
            mZpoV2CuestSaludInf.setSimserial( zpoV2CSIXml.getSimserial());
            mZpoV2CuestSaludInf.setPhonenumber( zpoV2CSIXml.getPhonenumber());
            mZpoV2CuestSaludInf.setToday( zpoV2CSIXml.getToday());
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
                if (accionaRealizar == ADD_ZPO_CSI_ODK){
                    zpoA.crearZpoV2CuestSaludInfantil( mZpoV2CuestSaludInf );
                }
                else{
                    zpoA.editarZpoV2CuestSaludInfantil( mZpoV2CuestSaludInf );
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
