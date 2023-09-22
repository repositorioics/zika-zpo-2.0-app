package ni.org.ics.zpo.v2.appmovil.activities.paginas.eventosmadre;

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
import ni.org.ics.zpo.v2.appmovil.adapters.eventosmadre.VisitAdapter;
import ni.org.ics.zpo.v2.appmovil.database.ZpoAdapter;
import ni.org.ics.zpo.v2.appmovil.domain.*;
import ni.org.ics.zpo.v2.appmovil.utils.Constants;
import ni.org.ics.zpo.v2.appmovil.utils.MainDBConstants;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.provider.Settings.System.DATE_FORMAT;

public class MotherVisitActivity extends AbstractAsyncActivity {
	private ZpoAdapter zpoA;
	private static Zpo00Screening zp00 = new Zpo00Screening();
	private static ZpoEstadoEmbarazada zpEstado = new ZpoEstadoEmbarazada();
    private static ZpoV2RecoleccionMuestra zpoV2Muestra= null;
	private static ZpoV2CuestionarioSaludMaterna zpoV2CuestSaMat= null;
	private static ZpoV2CuestionarioSocioeconomico zpoV2CuestSoe = null;
	private static ZpoV2EvaluacionPsicologica zpoV2EvPsico = null;
	private static ZpoV2CuestVisitaTerreno zpoV2CuestVisitaTerreno;


	private SimpleDateFormat mDateFormat = new SimpleDateFormat("MMM dd, yyyy");
	private static String evento;
	private GridView gridView;
	private TextView textView;
	private AlertDialog alertDialog;
	private static final int EXIT = 1;
	private boolean mExitShowing;
	private boolean pendiente = false;
	private static final String EXIT_SHOWING = "exitshowing";
	String[] menu_maternal_info;

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
		zpoA = new ZpoAdapter(this.getApplicationContext(),mPass,false,false);
		/*Aca se recupera evento, tamizaje y estado*/
		evento = getIntent().getStringExtra(Constants.EVENT);
		zp00 = (Zpo00Screening) getIntent().getExtras().getSerializable(Constants.OBJECTO_ZP00);
		zpEstado = (ZpoEstadoEmbarazada) getIntent().getExtras().getSerializable(Constants.OBJECTO_ZPEST);
		//Aca se recupera los datos de los formularios para ver si estan realizados o no...
		new FetchDataIngresoTask().execute(evento);
		textView = (TextView) findViewById(R.id.label);
		textView.setText(getString(R.string.forms)+"\n"+
				getString(R.string.mat_id)+": "+zp00.getRecordId()+"\n"+
						getString(R.string.mat_fec)+": "+ mDateFormat.format(zp00.getScrVisitDate()));
		menu_maternal_info = getResources().getStringArray(R.array.menu_maternal_visit);
		gridView = (GridView) findViewById(R.id.gridView1);
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Bundle arguments = new Bundle();
				Intent i;
				arguments.putString(Constants.EVENT, evento);
                arguments.putString(Constants.RECORDID, zp00.getRecordId());
				switch(position){
					case 0: //ACTUALIZACION CUESTIONARIO SALUD MATERNA
						i = new Intent(getApplicationContext(),
								NewZpoV2ActCuestSaludMaternaActivity.class);
						if (zpoV2CuestSaMat!=null) arguments.putSerializable(Constants.OBJECT_CUEST_SA_MAT , zpoV2CuestSaMat);
						i.putExtras(arguments);
						startActivity(i);
						break;

					case 1: // CUESTIONARIO SOCIECONOMIGO DEL HOGAR
						i = new Intent(getApplicationContext(),
								NewZpoV2CuestSocioeconomicoActivity.class);
						if (zpoV2CuestSoe!=null) arguments.putSerializable(Constants.OBJECT_CUEST_SOE, zpoV2CuestSoe);
						i.putExtras(arguments);
						startActivity(i);
						break;
                    case 2: //MUESTRAS
                        i = new Intent(getApplicationContext(),
                                NewZpoV2RecoleccionMuestraActivity.class);
                        if (zpoV2Muestra!=null) arguments.putSerializable(Constants.OBJECTO_ZP02 , zpoV2Muestra);
                        arguments.putBoolean("ES_MADRE", true);
                        i.putExtras(arguments);
                        startActivity(i);
                        break;

					case 3: //EVALUACION PSICOLOGICA
						i = new Intent(getApplicationContext(),
								NewZpoV2EvalPsicologicaActivity.class);
						if (zpoV2EvPsico!=null) arguments.putSerializable(Constants.OBJECT_EVAL_PSICO , zpoV2EvPsico);
						i.putExtras(arguments);
						startActivity(i);
						break;

					case 4: //CUESTIONARIO VISITA TERRENO
						i = new Intent(getApplicationContext(),
								NewZpoV2CuestVisitaTerrenoActivity.class);
						if (zpoV2CuestVisitaTerreno!=null) arguments.putSerializable(Constants.OBJECT_VIS_TER , zpoV2CuestVisitaTerreno);
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
		new FetchDataIngresoTask().execute(evento);
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
		private class FetchDataIngresoTask extends AsyncTask<String, Void, String> {
			private String eventoaFiltrar = null;
			private String filtro = null;
			private Date todayWithZeroTime = null;


		private String filtro2 = null;
			@Override
			protected void onPreExecute() {
				// before the request begins, show a progress indicator
				showLoadingProgressDialog();
			}

			@Override
			protected String doInBackground(String... values) {
				eventoaFiltrar = values[0];
				try {
					/*DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					String date = formatter.format(new Date());*/

					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					Date today = new Date();

					zpoA.open();
					todayWithZeroTime =formatter.parse(formatter.format(today));
					String date = "1689185713092";

					//Establecemos la fecha que deseamos en un Calendario
					Calendar cal = Calendar.getInstance();
					cal.setTime(todayWithZeroTime);

					//Desplegamos la fecha
					Date tempDate = cal.getTime();

					//Le cambiamos la hora y minutos
					cal.set(Calendar.HOUR, cal.get(Calendar.HOUR)+ 23);
					cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE)+ 59);
					tempDate = cal.getTime();

					filtro = MainDBConstants.recordId + "='" + zp00.getRecordId() + "' and " + MainDBConstants.eventName + "='" + eventoaFiltrar +"'";
					filtro2 = MainDBConstants.recordId + "='" + zp00.getRecordId() + "' and " + MainDBConstants.recordDate + " BETWEEN '" + todayWithZeroTime.getTime() + "' and '" + tempDate.getTime()  + "' and " + MainDBConstants.eventName + "='" + eventoaFiltrar +"'";
					zpoV2CuestSaMat = zpoA.getZpoV2CuestSaludMat(filtro, MainDBConstants.recordId);
					zpoV2CuestSoe = zpoA.getZpoV2CuestSocieco(filtro, MainDBConstants.recordId);
					zpoV2Muestra = zpoA.getZpoV2RecoleccionMuestra(filtro, MainDBConstants.recordId);
					zpoV2EvPsico = zpoA.getZpoV2EvalPsico(filtro, MainDBConstants.recordId);
					zpoV2CuestVisitaTerreno = zpoA.getZpoV2CuestVisitaTerreno(filtro2, MainDBConstants.recordId);

					if (zpoV2CuestSaMat!=null && zpoV2CuestSoe!=null && zpoV2Muestra!= null && zpoV2EvPsico!=null && zpoV2CuestVisitaTerreno!=null){

                        if(eventoaFiltrar.matches(Constants.MONTH36)){
                            zpEstado.setMes36('1');
                        }
						if(eventoaFiltrar.matches(Constants.MONTH48)){
							zpEstado.setMes48('1');
						}

						if(eventoaFiltrar.matches(Constants.MONTH60)){
							zpEstado.setMes60('1');
						}

						if(eventoaFiltrar.matches(Constants.MONTH72)){
							zpEstado.setMes72('1');
						}

						if(eventoaFiltrar.matches(Constants.MONTH84)){
							zpEstado.setMes84('1');
						}


						zpoA.editarZpoEstadoMadre(zpEstado);
					}
					zpoA.close();
				} catch (Exception e) {
					Log.e(TAG, e.getLocalizedMessage(), e);
					return "Error";
				}
				return "Exito";
			}

			protected void onPostExecute(String resultado) {
				// after the network request completes, hide the progress indicator
				gridView.setAdapter(new VisitAdapter(getApplicationContext(), R.layout.menu_item_2, menu_maternal_info, zpoV2CuestSaMat, zpoV2CuestSoe, zpoV2Muestra, zpoV2EvPsico,zpoV2CuestVisitaTerreno));
				dismissProgressDialog();
			}

		}

}
	
