package ni.org.ics.zpo.v2.appmovil.activities.paginas.eventosinfante;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import ni.org.ics.zpo.v2.appmovil.AbstractAsyncActivity;
import ni.org.ics.zpo.v2.appmovil.MainActivity;
import ni.org.ics.zpo.v2.appmovil.MyZpoApplication;
import ni.org.ics.zpo.v2.appmovil.R;
import ni.org.ics.zpo.v2.appmovil.activities.nuevos.*;
import ni.org.ics.zpo.v2.appmovil.adapters.eventosinfante.InfantEntryAdapter;
import ni.org.ics.zpo.v2.appmovil.database.ZpoAdapter;
import ni.org.ics.zpo.v2.appmovil.domain.*;
import ni.org.ics.zpo.v2.appmovil.utils.Constants;
import ni.org.ics.zpo.v2.appmovil.utils.DateUtil;
import ni.org.ics.zpo.v2.appmovil.utils.MainDBConstants;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author ics
 */
public class InfantEntryActivity extends AbstractAsyncActivity {
    private ZpoAdapter zipA;
    private static ZpoInfantData zpInfante = new ZpoInfantData();
    private static ZpoEstadoInfante zpEstado = new ZpoEstadoInfante();
    private static ZpoV2CuestionarioDemografico zpoCDemo = null;
    private static ZpoV2CuestSaludInfantil zpoCuestSaInf = null;
    private static ZpoV2IndCuidadoFamilia zpoICF = null;
    private static ZpoV2RecoleccionMuestra zpoV2Muestra = null;
    private static ZpoV2Mullen zpoMullen = null;
    private static ZpoV2ExamenFisicoInfante zpoV2ExFisInf = null;

    private SimpleDateFormat mDateFormat = new SimpleDateFormat("MMM dd, yyyy");
    private static String evento;
    private GridView gridView;
    private TextView textView;
    private AlertDialog alertDialog;
    private static final int EXIT = 1;
    private boolean mExitShowing;
    private boolean pendiente = false;
    private static final String EXIT_SHOWING = "exitshowing";
    String[] menu_infante_info;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_maternal);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(EXIT_SHOWING)) {
                mExitShowing = savedInstanceState.getBoolean(EXIT_SHOWING, false);
            }
        }
        String mPass = ((MyZpoApplication) this.getApplication()).getPassApp();
        zipA = new ZpoAdapter(this.getApplicationContext(),mPass,false,false);
        /*Aca se recupera evento, tamizaje y estado*/
        evento = getIntent().getStringExtra(Constants.EVENT);
        zpInfante = (ZpoInfantData) getIntent().getExtras().getSerializable(Constants.OBJECTO_ZPINFDATA);
        zpEstado = (ZpoEstadoInfante) getIntent().getExtras().getSerializable(Constants.OBJECTO_ZPESTINF);
        //Aca se recupera los datos de los formularios para ver si estan realizados o no...
        new FetchVisitInfanteTask().execute(evento);
        textView = (TextView) findViewById(R.id.label);
        try {
            textView.setText(getString(R.string.forms)+"\n"+
                    getString(R.string.inf_id)+": "+zpInfante.getRecordId()+"\n"+
                    getString(R.string.inf_dob)+": "+ (zpInfante.getInfantBirthDate()!=null?mDateFormat.format(DateUtil.StringToDate(zpInfante.getInfantBirthDate(), "dd/MM/yyyy")):"ND"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        menu_infante_info = getResources().getStringArray(R.array.menu_infant_entry);
        gridView = (GridView) findViewById(R.id.gridView1);
        gridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Bundle arguments = new Bundle();
                Intent i;
                arguments.putString(Constants.EVENT, evento);
                arguments.putString(Constants.RECORDID, zpInfante.getRecordId());
                switch (position) {
                    case 0:  //CUESTIONARIO DEMOGRAFICO
                        i = new Intent( getApplicationContext(),
                                NewZpoV2CuestDemograficoActivity.class );
                        if (zpoCDemo != null) arguments.putSerializable( Constants.OBJECT_CUEST_DEMO, zpoCDemo );
                        i.putExtras( arguments );
                        startActivity( i );
                        break;

                    case 1: //CUESTIONARIO SALUD INFANTIL
                        i = new Intent( getApplicationContext(),
                                NewZpoV2CuestSaludInfantilActivity.class );
                        if (zpoCuestSaInf != null)
                            arguments.putSerializable( Constants.OBJECT_CUEST_SA_INF, zpoCuestSaInf );
                        i.putExtras( arguments );
                        startActivity( i );
                        break;
                    //INDICADORES DEL CUIDADO DE LA FAMILIA
                    case 2:
                        i = new Intent( getApplicationContext(), NewZpoV2IndCuidadoFamiliaActivity.class );
                        if (zpoICF != null) arguments.putSerializable( Constants.OBJECT_INDCUIFAM, zpoICF );
                        i.putExtras( arguments );
                        startActivity( i );
                        break;
                    case 3: //EVALUACION MULLEN
                        i = new Intent( getApplicationContext(),
                                NewZpoV2MullenActivity.class );
                        if (zpoMullen != null) arguments.putSerializable( Constants.OBJECTO_ZPOMULLEN, zpoMullen );
                        i.putExtras( arguments );
                        startActivity( i );
                        break;
                    case 4: //MUESTRAS
                        i = new Intent( getApplicationContext(),
                                NewZpoV2RecoleccionMuestraActivity.class );
                        if (zpoV2Muestra != null) arguments.putSerializable( Constants.OBJECTO_ZP02, zpoV2Muestra );
                        arguments.putBoolean( "ES_MADRE", false );
                        i.putExtras( arguments );
                        startActivity( i );
                        break;

                    case 5: // EXAMEN FISICO INFANTE
                        i = new Intent(getApplicationContext(),
                                NewZpoV2ExamFisicoInfanteActivity.class);
                        if (zpoV2ExFisInf != null) arguments.putSerializable(Constants.OBJECT_EX_FIS_INF, zpoV2ExFisInf);
                        i.putExtras(arguments);
                        startActivity(i);
                        break;
                    default:
                        break;
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.general, menu);
        return true;
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(EXIT_SHOWING, mExitShowing);
    }

    @Override
    protected void onResume() {
        //getParticipanteData();
        if (mExitShowing) {
            createDialog(EXIT);
        }
        new FetchVisitInfanteTask().execute(evento);
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.MENU_BACK:
                if (pendiente){
                    createDialog(EXIT);
                }
                else{
                    finish();
                }
                return true;
            case R.id.MENU_HOME:
                if (pendiente){
                    createDialog(EXIT);
                }
                else{
                    i = new Intent(getApplicationContext(),
                            MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed (){
        if (pendiente){
            createDialog(EXIT);
        }
        else{
            finish();
        }
    }

    private void createDialog(int dialog) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        switch(dialog){
            case EXIT:
                builder.setTitle(this.getString(R.string.confirm));
                builder.setMessage(this.getString(R.string.ok));
                builder.setPositiveButton(this.getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Finish app
                        dialog.dismiss();
                        mExitShowing=false;
                    }

                });
                builder.setNegativeButton(this.getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                        dialog.dismiss();
                        mExitShowing=false;
                    }
                });
                mExitShowing=true;
                break;
            default:
                break;
        }
        alertDialog = builder.create();
        alertDialog.show();
    }

    // ***************************************
    // Private classes
    // ***************************************
    private class FetchVisitInfanteTask extends AsyncTask<String, Void, String> {
        private String eventoaFiltrar = null;
        private String filtro = null;
        @Override
        protected void onPreExecute() {
            // before the request begins, show a progress indicator
            showLoadingProgressDialog();
        }

        @Override
        protected String doInBackground(String... values) {
            eventoaFiltrar = values[0];
				try {
					zipA.open();
					filtro = MainDBConstants.recordId + "='" + zpInfante.getRecordId() + "' and " + MainDBConstants.eventName + "='" + eventoaFiltrar +"'";
					zpoCDemo = zipA.getZpoV2CuestDemo(filtro, MainDBConstants.recordId);
					zpoCuestSaInf = zipA.getZpoV2CuestSaludInf(filtro, MainDBConstants.recordId);
                    zpoV2Muestra = zipA.getZpoV2RecoleccionMuestra(filtro, MainDBConstants.recordId);
                    zpoMullen = zipA.getZpoV2Mullen(filtro, MainDBConstants.recordId);
                    zpoICF = zipA.getZpoV2IndCuidadoFam(filtro,MainDBConstants.recordId);
                    zpoV2ExFisInf = zipA.getZpoV2ExamFisicoInfante(filtro, MainDBConstants.recordId );

					if (zpoCDemo !=null && zpoCuestSaInf != null && zpoICF!= null && zpoMullen!= null && zpoV2Muestra!=null && zpoV2ExFisInf != null){

						if(eventoaFiltrar.matches(Constants.MONTH24)){
							zpEstado.setMes24('1');
						}
						zipA.editarZpoEstadoInfante(zpEstado);
					}
					zipA.close();
				} catch (Exception e) {
					Log.e(TAG, e.getLocalizedMessage(), e);
					return "Error";
				}
            return "Exito";
        }

        protected void onPostExecute(String resultado) {
            // after the network request completes, hide the progress indicator
            gridView.setAdapter(new InfantEntryAdapter(getApplicationContext(), R.layout.menu_item_2, menu_infante_info, zpoCDemo, zpoCuestSaInf, zpoICF, zpoMullen, zpoV2Muestra, zpoV2ExFisInf));
            dismissProgressDialog();
        }

    }

}

