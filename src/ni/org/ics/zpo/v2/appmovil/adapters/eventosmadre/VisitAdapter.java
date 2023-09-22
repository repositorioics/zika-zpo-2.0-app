package ni.org.ics.zpo.v2.appmovil.adapters.eventosmadre;

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
import ni.org.ics.zpo.v2.appmovil.domain.*;

public class VisitAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] values;
    private final ZpoV2RecoleccionMuestra mZpoV2Muestra;
    private final ZpoV2CuestionarioSaludMaterna mZpoV2CuestSaMat;
    private final ZpoV2CuestionarioSocioeconomico mZpoV2CuestSoe;
    private final ZpoV2EvaluacionPsicologica mZpoV2EvPsico;
    private final ZpoV2CuestVisitaTerreno mZpoV2VisTerreno;


    public VisitAdapter(Context context, int textViewResourceId,
                        String[] values, ZpoV2CuestionarioSaludMaterna zpoV2CuestSaMat,
                        ZpoV2CuestionarioSocioeconomico zpoV2CuestSoe,
                        ZpoV2RecoleccionMuestra zpoMuestra,
                        ZpoV2EvaluacionPsicologica zpoEvPsico,
                        ZpoV2CuestVisitaTerreno zpoV2CuestVisitaTerreno) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
        this.mZpoV2CuestSaMat = zpoV2CuestSaMat;
        this.mZpoV2CuestSoe = zpoV2CuestSoe;
        this.mZpoV2Muestra = zpoMuestra;
        this.mZpoV2EvPsico = zpoEvPsico;
        this.mZpoV2VisTerreno = zpoV2CuestVisitaTerreno;

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
        textView.setTextColor(Color.BLACK);
        textView.setText(values[position]);

        // Change icon based on position
        Drawable img = null;
        switch (position){
            case 0:
                if(mZpoV2CuestSaMat!=null){
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.done));
                }
                else{
                    textView.setTextColor(Color.RED);
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.pending));
                }
                img=getContext().getResources().getDrawable( R.drawable.ic_wom_health_upd);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                break;

            case 1:
                if (mZpoV2CuestSoe!=null){
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.done));
                }else{
                    textView.setTextColor(Color.RED);
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.pending));
                }
                img=getContext().getResources().getDrawable( R.drawable.ic_socioec);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                break;
            case 2:
                if(mZpoV2Muestra!=null){
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.done));
                }
                else{
                    textView.setTextColor(Color.RED);
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.pending));
                }
                img=getContext().getResources().getDrawable( R.drawable.ic_sample);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                break;

            case 3:
                if(mZpoV2EvPsico!=null){
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.done));
                }
                else{
                    textView.setTextColor(Color.RED);
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.pending));
                }
                img=getContext().getResources().getDrawable( R.drawable.ic_mental_health);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                break;

            case 4:
                if(mZpoV2VisTerreno!=null){
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.done));
                }
                else{
                    textView.setTextColor(Color.RED);
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.pending));
                }
                img=getContext().getResources().getDrawable( R.drawable.ic_door);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                break;

            default:
                img=getContext().getResources().getDrawable( R.drawable.logo);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                break;
        }

        return v;
    }
}
