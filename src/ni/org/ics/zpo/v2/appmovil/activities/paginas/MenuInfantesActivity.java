package ni.org.ics.zpo.v2.appmovil.activities.paginas;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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
import ni.org.ics.zpo.v2.appmovil.activities.nuevos.NewZpoV2StudyExitActivity;
import ni.org.ics.zpo.v2.appmovil.activities.paginas.eventosinfante.*;
import ni.org.ics.zpo.v2.appmovil.adapters.MenuInfantesAdapter;
import ni.org.ics.zpo.v2.appmovil.database.ZpoAdapter;
import ni.org.ics.zpo.v2.appmovil.domain.Zpo00Screening;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoEstadoInfante;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoInfantData;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoV2StudyExit;
import ni.org.ics.zpo.v2.appmovil.utils.Constants;
import ni.org.ics.zpo.v2.appmovil.utils.DateUtil;
import ni.org.ics.zpo.v2.appmovil.utils.MainDBConstants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MenuInfantesActivity extends AbstractAsyncActivity {

    private static ZpoInfantData zpInfante = new ZpoInfantData();
    private static ZpoEstadoInfante zpEstado = new ZpoEstadoInfante();
    private static Zpo00Screening screening = new Zpo00Screening();
    private static ZpoV2StudyExit zpoSalida= new ZpoV2StudyExit();
    private GridView gridView;
    private TextView textView;
    private SimpleDateFormat mDateFormat = new SimpleDateFormat("MMM dd, yyyy");
    private ZpoAdapter zipA;
    private String[] menu_infante_info;
    private String filtro;
    private Calendar fechaIngreso;
    private Date fechaEvento;
    private Date todayDate;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private AlertDialog alertDialog;
    private static final int FUERA = 1;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_infante);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        textView = (TextView) findViewById(R.id.label);
        gridView = (GridView) findViewById(R.id.gridView1);
        String mPass = ((MyZpoApplication) this.getApplication()).getPassApp();
        zipA = new ZpoAdapter(this.getApplicationContext(),mPass,false,false);
        zpInfante = (ZpoInfantData) getIntent().getExtras().getSerializable(Constants.OBJECTO_ZPINFDATA);
        filtro = MainDBConstants.recordId + "='" + zpInfante.getRecordId() + "'";
        new FetchDataInfanteTask().execute(filtro);
        menu_infante_info = getResources().getStringArray(R.array.menu_infant);
        try {
            this.todayDate = formatter.parse(formatter.format(new Date()));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.fechaIngreso = Calendar.getInstance();
        if (zpInfante.getInfantBirthDate()!=null) {
            try {
                fechaIngreso.setTime(DateUtil.StringToDate(zpInfante.getInfantBirthDate(), "dd/MM/yyyy"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        gridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                long diff =0;
                boolean habilitado = true;
                switch (position){
                  /*  case 0:
                        fechaEvento = zpInfante.getRecordDate();
                        diff = getDateDiff(fechaEvento,todayDate,TimeUnit.DAYS);
                        if(diff>15) habilitado = false;
                        break;*/
                    case 0:
                        fechaIngreso.add(Calendar.MONTH, 30);fechaEvento = fechaIngreso.getTime();
                        fechaIngreso.add(Calendar.MONTH, -30);
                        diff = getDateDiff(fechaEvento,todayDate,TimeUnit.DAYS);
                        if(diff<-7||diff>7) habilitado = false;
                        break;
                    case 1:
                        fechaIngreso.add(Calendar.MONTH, 36);fechaEvento = fechaIngreso.getTime();
                        fechaIngreso.add(Calendar.MONTH, -36);
                        diff = getDateDiff(fechaEvento,todayDate,TimeUnit.DAYS);
                        if(diff<-7||diff>7) habilitado = false;
                        break;
                    case 2:
                        fechaIngreso.add(Calendar.MONTH, 42);fechaEvento = fechaIngreso.getTime();
                        fechaIngreso.add(Calendar.MONTH, -42);
                        diff = getDateDiff(fechaEvento,todayDate,TimeUnit.DAYS);
                        if(diff<-7||diff>7) habilitado = false;
                        break;
                    case 3:
                        fechaIngreso.add(Calendar.MONTH, 48);fechaEvento = fechaIngreso.getTime();
                        fechaIngreso.add(Calendar.MONTH, -48);
                        diff = getDateDiff(fechaEvento,todayDate,TimeUnit.DAYS);
                        if(diff<-7||diff>7) habilitado = false;
                        break;
                    case 4:
                        fechaIngreso.add(Calendar.MONTH, 54);fechaEvento = fechaIngreso.getTime();
                        fechaIngreso.add(Calendar.MONTH, -54);
                        diff = getDateDiff(fechaEvento,todayDate,TimeUnit.DAYS);
                        if(diff<-7||diff>7) habilitado = false;
                        break;
                    case 5:
                        fechaIngreso.add(Calendar.MONTH, 60);fechaEvento = fechaIngreso.getTime();
                        fechaIngreso.add(Calendar.MONTH, -60);
                        diff = getDateDiff(fechaEvento,todayDate,TimeUnit.DAYS);
                        if(diff<-7||diff>7) habilitado = false;
                        break;
                    case 6:
                        fechaIngreso.add(Calendar.MONTH, 66);fechaEvento = fechaIngreso.getTime();
                        fechaIngreso.add(Calendar.MONTH, -66);
                        diff = getDateDiff(fechaEvento,todayDate,TimeUnit.DAYS);
                        if(diff<-7||diff>7) habilitado = false;
                        break;
                    case 7:
                        fechaIngreso.add(Calendar.MONTH, 72);fechaEvento = fechaIngreso.getTime();
                        fechaIngreso.add(Calendar.MONTH, -72);
                        diff = getDateDiff(fechaEvento,todayDate,TimeUnit.DAYS);
                        if(diff<-7||diff>7) habilitado = false;
                        break;
                    case 8:
                        fechaIngreso.add(Calendar.MONTH, 78);fechaEvento = fechaIngreso.getTime();
                        fechaIngreso.add(Calendar.MONTH, -78);
                        diff = getDateDiff(fechaEvento,todayDate,TimeUnit.DAYS);
                        if(diff<-7||diff>7) habilitado = false;
                        break;
                    case 9:
                        fechaIngreso.add(Calendar.MONTH, 84);fechaEvento = fechaIngreso.getTime();
                        fechaIngreso.add(Calendar.MONTH, -84);
                        diff = getDateDiff(fechaEvento,todayDate,TimeUnit.DAYS);
                        if(diff<-7||diff>7) habilitado = false;
                        break;
                    default:
                        habilitado = true;
                        break;
                }
                if(!habilitado){
                    createDialog(FUERA,position);
                }
                else{
                    entrarPantalla(position);
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
    }

    @Override
    protected void onResume() {
        new FetchDataInfanteTask().execute(filtro);
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.MENU_BACK:
                finish();
                return true;
            case R.id.MENU_HOME:
                i = new Intent(getApplicationContext(),
                        MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void createDialog(int dialog,final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        switch(dialog){
            case FUERA:
                builder.setTitle(this.getString(R.string.confirm));
                builder.setMessage(this.getString(R.string.out_of_time));
                builder.setPositiveButton(this.getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        entrarPantalla(position);
                    }
                });
                builder.setNegativeButton(this.getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                        dialog.dismiss();
                    }
                });
                break;
            default:
                break;
        }
        alertDialog = builder.create();
        alertDialog.show();
    }

    private void entrarPantalla(int position){
        Bundle arguments = new Bundle();
        Intent i;
        switch(position){

            case 0:
                i = new Intent(getApplicationContext(),
                        InfantEntryActivity.class);
                //Aca se pasa evento, infante y estado
                if(position==0)	arguments.putString(Constants.EVENT, Constants.MONTH24);
                if (zpInfante!=null) arguments.putSerializable(Constants.OBJECTO_ZPINFDATA , zpInfante);
                if (zpEstado!=null) arguments.putSerializable(Constants.OBJECTO_ZPESTINF , zpEstado);
                i.putExtras(arguments);
                startActivity(i);
                break;
            case 1:
                i = new Intent(getApplicationContext(),
                        InfantVisitActivity.class);
                //Aca se pasa evento, infante y estado
                if(position==1)	arguments.putString(Constants.EVENT, Constants.MONTH36);
                if (zpInfante!=null) arguments.putSerializable(Constants.OBJECTO_ZPINFDATA , zpInfante);
                if (zpEstado!=null) arguments.putSerializable(Constants.OBJECTO_ZPESTINF , zpEstado);
                i.putExtras(arguments);
                startActivity(i);
                break;
            case 3:case 5:
                i = new Intent(getApplicationContext(),
                        InfantVisit4860Activity.class);
                //Aca se pasa evento, infante y estado
                if(position==3)	arguments.putString(Constants.EVENT, Constants.MONTH48);
                if(position==5)	arguments.putString(Constants.EVENT, Constants.MONTH60);
                if (zpInfante!=null) arguments.putSerializable(Constants.OBJECTO_ZPINFDATA , zpInfante);
                if (zpEstado!=null) arguments.putSerializable(Constants.OBJECTO_ZPESTINF , zpEstado);
                i.putExtras(arguments);
                startActivity(i);
                break;

            case 7:case 9:
                i = new Intent(getApplicationContext(),
                        InfantVisit7284Activity.class);
                //Aca se pasa evento, infante y estado
                if(position==7)	arguments.putString(Constants.EVENT, Constants.MONTH72);
                if(position==9) arguments.putString(Constants.EVENT, Constants.MONTH84);
                if (zpInfante!=null) arguments.putSerializable(Constants.OBJECTO_ZPINFDATA , zpInfante);
                if (zpEstado!=null) arguments.putSerializable(Constants.OBJECTO_ZPESTINF , zpEstado);
                i.putExtras(arguments);
                startActivity(i);
                break;

            case 2: case 4: case 6:
                i = new Intent(getApplicationContext(),
                        InfantCallActivity.class);
                //Aca se pasa evento, tamizaje y estado
                if(position==2)	arguments.putString(Constants.EVENT, Constants.MONTH42);
                if(position==4)	arguments.putString(Constants.EVENT, Constants.MONTH54);
                if(position==6)	arguments.putString(Constants.EVENT, Constants.MONTH66);
              //  if(position==8)	arguments.putString(Constants.EVENT, Constants.MONTH78);
                if (zpInfante!=null) arguments.putSerializable(Constants.OBJECTO_ZPINFDATA , zpInfante);
                if (zpEstado!=null) arguments.putSerializable(Constants.OBJECTO_ZPESTINF , zpEstado);
                i.putExtras(arguments);
                startActivity(i);
                break;

            case 10:
                i = new Intent(getApplicationContext(),
                        NewZpoV2StudyExitActivity.class);
                //Aca se pasa evento, tamizaje y estado
                if (screening!=null) arguments.putSerializable(Constants.OBJECTO_ZP00 , screening);
                arguments.putString(Constants.RECORDID, screening.getRecordId());
                i.putExtras(arguments);
                startActivity(i);
                break;

            case 8:
                i = new Intent(getApplicationContext(),
                        InfantCall78Activity.class);
                //Aca se pasa evento, tamizaje y estado
                if(position==8)	arguments.putString(Constants.EVENT, Constants.MONTH78);
                if (zpInfante!=null) arguments.putSerializable(Constants.OBJECTO_ZPINFDATA , zpInfante);
                if (zpEstado!=null) arguments.putSerializable(Constants.OBJECTO_ZPESTINF , zpEstado);
                i.putExtras(arguments);
                startActivity(i);
                break;
            default:
                break;
        }
    }

    /**
     * Get a diff between two dates
     * @param date1 the oldest date
     * @param date2 the newest date
     * @param timeUnit the unit in which you want the diff
     * @return the diff value, in the provided unit
     */
    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }

    // ***************************************
    // Private classes
    // ***************************************
    private class FetchDataInfanteTask extends AsyncTask<String, Void, String> {
        private String filtro = null;
        @Override
        protected void onPreExecute() {
            // before the request begins, show a progress indicator
            showLoadingProgressDialog();
        }

        @Override
        protected String doInBackground(String... values) {
            filtro = values[0];
            try {
                zipA.open();
                zpEstado = zipA.getZpoEstadoInfante(filtro, MainDBConstants.recordId);
                screening = zipA.getZpo00Screening(filtro, MainDBConstants.recordId);
                zpoSalida = zipA.getZpoV2StudyExit(filtro, null);
                //zpSalida = zipA.getZpo08StudyExit(filtro, null);
                //entryD = zipA.getZpo01StudyEntrySectionDtoF(MainDBConstants.recordId + "='" + zpInfante.getPregnantId() + "'", null);
                zipA.close();
            } catch (Exception e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return "error";
            }
            return "exito";
        }

        protected void onPostExecute(String resultado) {
            // after the network request completes, hide the progress indicator
            textView.setText("");
            textView.setTextColor(Color.BLUE);
            try {
                textView.setText(getString(R.string.infant_events)+"\n"+
                        getString(R.string.inf_id)+": "+zpInfante.getRecordId()+"\n"+
                        getString(R.string.inf_dob)+": "+ (zpInfante.getInfantBirthDate()!=null?mDateFormat.format(DateUtil.StringToDate(zpInfante.getInfantBirthDate(), "dd/MM/yyyy")):"ND"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                gridView.setAdapter(new MenuInfantesAdapter(getApplicationContext(), R.layout.menu_item_2, menu_infante_info, zpInfante, zpEstado, screening, zpoSalida));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (zpoSalida != null){
                textView.setTextColor(Color.RED);
                textView.setText(textView.getText()+"\n"+getString(R.string.inf_retired)
                        +"\n"+getString(R.string.inf_exit)+": "+ mDateFormat.format(zpoSalida.getFechaHoyDiscont()));
            }
            dismissProgressDialog();
        }

    }


}

