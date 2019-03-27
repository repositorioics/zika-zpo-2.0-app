package ni.org.ics.zpo.v2.appmovil.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import ni.org.ics.zpo.v2.appmovil.R;
import ni.org.ics.zpo.v2.appmovil.domain.Zpo00Screening;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoEstadoInfante;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoInfantData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MenuInfantesAdapter extends ArrayAdapter<String> {

    private final String[] values;
    private final Calendar fechaIngreso;
    private final Context context;

    private final ZpoInfantData mZpInfante;
    private final ZpoEstadoInfante mZpEstado;
    private final Zpo00Screening mScreening;

    private Date fechaEvento;
    private Date todayDate;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public MenuInfantesAdapter(Context context, int textViewResourceId,
                               String[] values, ZpoInfantData zp00, ZpoEstadoInfante zpEstado, Zpo00Screening screening) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
        this.mZpInfante = zp00;
        this.mZpEstado = zpEstado;
        this.mScreening = screening;
        try {
            this.todayDate = formatter.parse(formatter.format(new Date()));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.fechaIngreso = Calendar.getInstance();
        if (mZpInfante.getInfantBirthDate()!=null)
            fechaIngreso.setTime(mZpInfante.getInfantBirthDate());
    }


    @Override
    public boolean isEnabled(int position) {
        // Disable the first item of GridView
        boolean habilitado = true;
        return habilitado;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.menu_item_2, null);
        }
        TextView textView = (TextView) v.findViewById(R.id.label);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setText(values[position]);
        // Change icon based on position
        Drawable img = null;
        switch (position){
            //ingreso
            case 0:
                fechaEvento = mScreening.getScrVisitDate();
                if(String.valueOf(mZpEstado.getIngreso()).equals("0")){
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.pending));
                    textView.setTextColor(Color.BLUE);
                    long dif = getDateDiff(fechaEvento,todayDate,TimeUnit.DAYS);
                    if(dif>15){
                        textView.setTextColor(Color.RED);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.delayed));
                    }
                    else if(dif<=3){
                        textView.setTextColor(Color.BLUE);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.ontime));
                    }
                    else {
                        textView.setTextColor(Color.RED);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.delayed));
                    }
                }
                else{
                    textView.setTextColor(Color.BLACK);
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.done)+"\n\n");
                }
                img=getContext().getResources().getDrawable( R.drawable.ic_enroll);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                break;
            //visitas presenciales
            case 1:
                fechaIngreso.add(Calendar.MONTH, 24);
                fechaEvento = fechaIngreso.getTime();
                if(String.valueOf(mZpEstado.getMes24()).equals("0")){
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.pending));
                    textView.setTextColor(Color.BLUE);
                    long dif = getDateDiff(fechaEvento,todayDate,TimeUnit.DAYS);
                    if(dif<-7){
                        textView.setTextColor(Color.GRAY);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.programmed)+": "+ formatter.format(fechaEvento));
                    }
                    else if(dif>7){
                        textView.setTextColor(Color.RED);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.delayed));
                    }
                    else if(dif<=0){
                        textView.setTextColor(Color.BLUE);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.ontime));
                    }
                    else{
                        textView.setTextColor(Color.RED);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.delayed));
                    }
                }
                else{
                    textView.setTextColor(Color.BLACK);
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.done)+"\n\n");
                }
                img=getContext().getResources().getDrawable( R.drawable.ic_calendar);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                fechaIngreso.add(Calendar.MONTH, -24);
                break;
            case 3:
                fechaIngreso.add(Calendar.MONTH, 36);
                fechaEvento = fechaIngreso.getTime();
                if(String.valueOf(mZpEstado.getMes36()).equals("0")){
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.pending));
                    textView.setTextColor(Color.BLUE);
                    long dif = getDateDiff(fechaEvento,todayDate,TimeUnit.DAYS);
                    if(dif<-7){
                        textView.setTextColor(Color.GRAY);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.programmed)+": "+ formatter.format(fechaEvento));
                    }
                    else if(dif>7){
                        textView.setTextColor(Color.RED);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.delayed));
                    }
                    else if(dif<=0){
                        textView.setTextColor(Color.BLUE);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.ontime));
                    }
                    else{
                        textView.setTextColor(Color.RED);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.delayed));
                    }
                }
                else{
                    textView.setTextColor(Color.BLACK);
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.done)+"\n\n");
                }
                img=getContext().getResources().getDrawable( R.drawable.ic_calendar);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                fechaIngreso.add(Calendar.MONTH, -36);
                break;
            case 5:
                fechaIngreso.add(Calendar.MONTH, 48);
                fechaEvento = fechaIngreso.getTime();
                if(String.valueOf(mZpEstado.getMes48()).equals("0")){
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.pending));
                    textView.setTextColor(Color.BLUE);
                    long dif = getDateDiff(fechaEvento,todayDate,TimeUnit.DAYS);
                    if(dif<-7){
                        textView.setTextColor(Color.GRAY);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.programmed)+": "+ formatter.format(fechaEvento));
                    }
                    else if(dif>7){
                        textView.setTextColor(Color.RED);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.delayed));
                    }
                    else if(dif<=0){
                        textView.setTextColor(Color.BLUE);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.ontime));
                    }
                    else{
                        textView.setTextColor(Color.RED);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.delayed));
                    }
                }
                else{
                    textView.setTextColor(Color.BLACK);
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.done)+"\n\n");
                }
                img=getContext().getResources().getDrawable( R.drawable.ic_calendar);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                fechaIngreso.add(Calendar.MONTH, -48);
                break;
            case 7:
                fechaIngreso.add(Calendar.MONTH, 60);
                fechaEvento = fechaIngreso.getTime();
                if(String.valueOf(mZpEstado.getMes60()).equals("0")){
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.pending));
                    textView.setTextColor(Color.BLUE);
                    long dif = getDateDiff(fechaEvento,todayDate,TimeUnit.DAYS);
                    if(dif<-7){
                        textView.setTextColor(Color.GRAY);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.programmed)+": "+ formatter.format(fechaEvento));
                    }
                    else if(dif>7){
                        textView.setTextColor(Color.RED);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.delayed));
                    }
                    else if(dif<=0){
                        textView.setTextColor(Color.BLUE);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.ontime));
                    }
                    else{
                        textView.setTextColor(Color.RED);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.delayed));
                    }
                }
                else{
                    textView.setTextColor(Color.BLACK);
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.done)+"\n\n");
                }
                img=getContext().getResources().getDrawable( R.drawable.ic_calendar);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                fechaIngreso.add(Calendar.MONTH, -60);
                break;
            case 9:
                fechaIngreso.add(Calendar.MONTH, 72);
                fechaEvento = fechaIngreso.getTime();
                if(String.valueOf(mZpEstado.getMes72()).equals("0")){
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.pending));
                    textView.setTextColor(Color.BLUE);
                    long dif = getDateDiff(fechaEvento,todayDate,TimeUnit.DAYS);
                    if(dif<-7){
                        textView.setTextColor(Color.GRAY);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.programmed)+": "+ formatter.format(fechaEvento));
                    }
                    else if(dif>7){
                        textView.setTextColor(Color.RED);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.delayed));
                    }
                    else if(dif<=0){
                        textView.setTextColor(Color.BLUE);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.ontime));
                    }
                    else{
                        textView.setTextColor(Color.RED);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.delayed));
                    }
                }
                else{
                    textView.setTextColor(Color.BLACK);
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.done)+"\n\n");
                }
                img=getContext().getResources().getDrawable( R.drawable.ic_calendar);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                fechaIngreso.add(Calendar.MONTH, -72);
                break;
           case 11:
                fechaIngreso.add(Calendar.MONTH, 84);
                fechaEvento = fechaIngreso.getTime();
                if(String.valueOf(mZpEstado.getMes84()).equals("0")){
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.pending));
                    textView.setTextColor(Color.BLUE);
                    long dif = getDateDiff(fechaEvento,todayDate,TimeUnit.DAYS);
                    if(dif<-7){
                        textView.setTextColor(Color.GRAY);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.programmed)+": "+ formatter.format(fechaEvento));
                    }
                    else if(dif>7){
                        textView.setTextColor(Color.RED);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.delayed));
                    }
                    else if(dif<=0){
                        textView.setTextColor(Color.BLUE);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.ontime));
                    }
                    else{
                        textView.setTextColor(Color.RED);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.delayed));
                    }
                }
                else{
                    textView.setTextColor(Color.BLACK);
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.done)+"\n\n");
                }
                img=getContext().getResources().getDrawable( R.drawable.ic_calendar);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                fechaIngreso.add(Calendar.MONTH, -84);
                break;
           //llamadas
            case 2:
                fechaIngreso.add(Calendar.MONTH, 30);
                fechaEvento = fechaIngreso.getTime();
                if(String.valueOf(mZpEstado.getMes30()).equals("0")){
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.pending));
                    textView.setTextColor(Color.BLUE);
                    long dif = getDateDiff(fechaEvento,todayDate,TimeUnit.DAYS);
                    if(dif<-7){
                        textView.setTextColor(Color.GRAY);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.programmed)+": "+ formatter.format(fechaEvento));
                    }
                    else if(dif>7){
                        textView.setTextColor(Color.RED);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.delayed));
                    }
                    else if(dif<=0){
                        textView.setTextColor(Color.BLUE);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.ontime));
                    }
                    else{
                        textView.setTextColor(Color.RED);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.delayed));
                    }
                }
                else{
                    textView.setTextColor(Color.BLACK);
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.done)+"\n\n");
                }
                img=getContext().getResources().getDrawable( R.drawable.ic_visit_call);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                fechaIngreso.add(Calendar.MONTH, -30);
                break;
            case 4:
                fechaIngreso.add(Calendar.MONTH, 42);
                fechaEvento = fechaIngreso.getTime();
                if(String.valueOf(mZpEstado.getMes42()).equals("0")){
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.pending));
                    textView.setTextColor(Color.BLUE);
                    long dif = getDateDiff(fechaEvento,todayDate,TimeUnit.DAYS);
                    if(dif<-7){
                        textView.setTextColor(Color.GRAY);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.programmed)+": "+ formatter.format(fechaEvento));
                    }
                    else if(dif>7){
                        textView.setTextColor(Color.RED);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.delayed));
                    }
                    else if(dif<=0){
                        textView.setTextColor(Color.BLUE);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.ontime));
                    }
                    else{
                        textView.setTextColor(Color.RED);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.delayed));
                    }
                }
                else{
                    textView.setTextColor(Color.BLACK);
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.done)+"\n\n");
                }
                img=getContext().getResources().getDrawable( R.drawable.ic_visit_call);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                fechaIngreso.add(Calendar.MONTH, -42);
                break;
            case 6:
                fechaIngreso.add(Calendar.MONTH, 54);
                fechaEvento = fechaIngreso.getTime();
                if(String.valueOf(mZpEstado.getMes54()).equals("0")){
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.pending));
                    textView.setTextColor(Color.BLUE);
                    long dif = getDateDiff(fechaEvento,todayDate,TimeUnit.DAYS);
                    if(dif<-7){
                        textView.setTextColor(Color.GRAY);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.programmed)+": "+ formatter.format(fechaEvento));
                    }
                    else if(dif>7){
                        textView.setTextColor(Color.RED);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.delayed));
                    }
                    else if(dif<=0){
                        textView.setTextColor(Color.BLUE);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.ontime));
                    }
                    else{
                        textView.setTextColor(Color.RED);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.delayed));
                    }
                }
                else{
                    textView.setTextColor(Color.BLACK);
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.done)+"\n\n");
                }
                img=getContext().getResources().getDrawable( R.drawable.ic_visit_call);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                fechaIngreso.add(Calendar.MONTH, -54);
                break;
            case 8:
                fechaIngreso.add(Calendar.MONTH, 66);
                fechaEvento = fechaIngreso.getTime();
                if(String.valueOf(mZpEstado.getMes66()).equals("0")){
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.pending));
                    textView.setTextColor(Color.BLUE);
                    long dif = getDateDiff(fechaEvento,todayDate,TimeUnit.DAYS);
                    if(dif<-7){
                        textView.setTextColor(Color.GRAY);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.programmed)+": "+ formatter.format(fechaEvento));
                    }
                    else if(dif>7){
                        textView.setTextColor(Color.RED);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.delayed));
                    }
                    else if(dif<=0){
                        textView.setTextColor(Color.BLUE);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.ontime));
                    }
                    else{
                        textView.setTextColor(Color.RED);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.delayed));
                    }
                }
                else{
                    textView.setTextColor(Color.BLACK);
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.done)+"\n\n");
                }
                img=getContext().getResources().getDrawable( R.drawable.ic_visit_call);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                fechaIngreso.add(Calendar.MONTH, -66);
                break;
            case 10:
                fechaIngreso.add(Calendar.MONTH, 78);
                fechaEvento = fechaIngreso.getTime();
                if(String.valueOf(mZpEstado.getMes78()).equals("0")){
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.pending));
                    textView.setTextColor(Color.BLUE);
                    long dif = getDateDiff(fechaEvento,todayDate,TimeUnit.DAYS);
                    if(dif<-7){
                        textView.setTextColor(Color.GRAY);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.programmed)+": "+ formatter.format(fechaEvento));
                    }
                    else if(dif>7){
                        textView.setTextColor(Color.RED);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.delayed));
                    }
                    else if(dif<=0){
                        textView.setTextColor(Color.BLUE);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.ontime));
                    }
                    else{
                        textView.setTextColor(Color.RED);
                        textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.delayed));
                    }
                }
                else{
                    textView.setTextColor(Color.BLACK);
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.done)+"\n\n");
                }
                img=getContext().getResources().getDrawable( R.drawable.ic_visit_call);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                fechaIngreso.add(Calendar.MONTH, -78);
                break;
            case 12:
                img=getContext().getResources().getDrawable( R.drawable.ic_exit);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                textView.setTextColor(Color.BLACK);
                break;
            default:
                img=getContext().getResources().getDrawable( R.drawable.logo);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                textView.setTextColor(Color.BLACK);
                break;
        }

        return v;
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
}
