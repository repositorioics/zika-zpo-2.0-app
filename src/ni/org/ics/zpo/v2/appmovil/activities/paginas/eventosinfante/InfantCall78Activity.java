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
import ni.org.ics.zpo.v2.appmovil.activities.nuevos.NewZpoV2ActCuestSaludInfantilActivity;
import ni.org.ics.zpo.v2.appmovil.adapters.eventosinfante.InfantCall78Adapter;
import ni.org.ics.zpo.v2.appmovil.database.ZpoAdapter;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoEstadoInfante;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoInfantData;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoV2CuestSaludInfantil;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoV2EdadesEtapas;
import ni.org.ics.zpo.v2.appmovil.utils.Constants;
import ni.org.ics.zpo.v2.appmovil.utils.DateUtil;
import ni.org.ics.zpo.v2.appmovil.utils.MainDBConstants;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class InfantCall78Activity extends AbstractAsyncActivity {
	private ZpoAdapter zipA;
	private static ZpoInfantData zpInfante = new ZpoInfantData();
	private static ZpoEstadoInfante zpEstado = new ZpoEstadoInfante();

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

    private static ZpoV2EdadesEtapas zpoEE = null;
    private static ZpoV2CuestSaludInfantil zpoCuestSaInf;

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
		new FetchUnscheduledVisitInfanteTask().execute(evento);
		textView = (TextView) findViewById(R.id.label);
		try {
			textView.setText(getString(R.string.forms)+"\n"+
					getString(R.string.inf_id)+": "+zpInfante.getRecordId()+"\n"+
							getString(R.string.inf_dob)+": "+ (zpInfante.getInfantBirthDate()!=null?mDateFormat.format(DateUtil.StringToDate(zpInfante.getInfantBirthDate(), "dd/MM/yyyy")):"ND"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		menu_infante_info = getResources().getStringArray(R.array.menu_infant_call78);
		gridView = (GridView) findViewById(R.id.gridView1);
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Bundle arguments = new Bundle();
				Intent i;
				arguments.putString(Constants.EVENT, evento);
                arguments.putString(Constants.RECORDID, zpInfante.getRecordId());
				switch(position){
                case 0: //ACTUALIZACION CUESTIONARIO SALUD INFANTIL
                	i = new Intent(getApplicationContext(),
                			NewZpoV2ActCuestSaludInfantilActivity.class);
                    if (zpoCuestSaInf!=null) arguments.putSerializable(Constants.OBJECT_CUEST_SA_INF , zpoCuestSaInf);
					i.putExtras(arguments);
					startActivity(i);
					break;
				/*	case 1: //EVALUACION OFTALMOLOGICA
						i = new Intent(getApplicationContext(),
								NewZpo07InfantAssessmentVisitOphtActivity.class);
						if (zp07 != null) arguments.putSerializable(Constants.OBJECTO_ZP07, zp07);
						i.putExtras(arguments);
						startActivity(i);
						break;*/

				/*	case 1: //EDADES Y ETAPAS
						i = new Intent(getApplicationContext(),
								NewZpoV2EdadesEtapasActivity.class);
						if (zpoEE != null) arguments.putSerializable(Constants.OBJECT_ZPOEDADESETAPAS, zpoEE);
						i.putExtras(arguments);
						startActivity(i);
						break;*/
/*					case 4: //RESULTADOS OFTALMOLOGICOS
						i = new Intent(getApplicationContext(),
								NewZpoV2InfantOphtResultsActivity.class);
						if (zp07a != null) arguments.putSerializable(Constants.OBJECTO_ZP07A, zp07a);
						i.putExtras(arguments);
						startActivity(i);
						break;
					case 5: //RESULTADOS AUDIOLOGICOS
						i = new Intent(getApplicationContext(),
								NewZpo07bInfantAudioResultsActivity.class);
						if (zp07b != null) arguments.putSerializable(Constants.OBJECTO_ZP07B, zp07b);
						i.putExtras(arguments);
						startActivity(i);
						break;
					case 6: //ESTUDIOS DE IMAGENES
						i = new Intent(getApplicationContext(),
								NewZpo07cInfantImageStudiesActivity.class);
						if (zp07c != null) arguments.putSerializable(Constants.OBJECTO_ZP07C, zp07c);
						i.putExtras(arguments);
						startActivity(i);
						break;
					case 7: //ESCALA BAYLEY
						i = new Intent(getApplicationContext(),
								NewZpo07dInfantBayleyScalesActivity.class);
						if (zp07d != null) arguments.putSerializable(Constants.OBJECTO_ZP07D, zp07d);
						i.putExtras(arguments);
						startActivity(i);
						break;
					case 9: //FACTORES DE RIESGO
						i = new Intent(getApplicationContext(),
								NewZpo04ExtendedSectionAtoFActivity.class);
						if (zp04AF != null) arguments.putSerializable(Constants.OBJECTO_ZP04AF, zp04AF);
						i.putExtras(arguments);
						startActivity(i);
						break;*/
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
		new FetchUnscheduledVisitInfanteTask().execute(evento);
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
		private class FetchUnscheduledVisitInfanteTask extends AsyncTask<String, Void, String> {
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
					zpoEE = zipA.getZpoV2EdadesEtapas(filtro, MainDBConstants.recordId);
					zpoCuestSaInf = zipA.getZpoV2CuestSaludInf(filtro, MainDBConstants.recordId);

					if (zpoCuestSaInf != null){
						if(eventoaFiltrar.matches(Constants.MONTH30)){
							zpEstado.setMes30('1');
						}
						if(eventoaFiltrar.matches(Constants.MONTH42)){
							zpEstado.setMes42('1');
						}
                        if(eventoaFiltrar.matches(Constants.MONTH54)){
                            zpEstado.setMes54('1');
                        }
                        if(eventoaFiltrar.matches(Constants.MONTH66)){
                            zpEstado.setMes66('1');
                        }
                        if(eventoaFiltrar.matches(Constants.MONTH78)){
                            zpEstado.setMes78('1');
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
				gridView.setAdapter(new InfantCall78Adapter(getApplicationContext(), R.layout.menu_item_2, menu_infante_info, zpoCuestSaInf));
				dismissProgressDialog();
			}

		}

}
	
