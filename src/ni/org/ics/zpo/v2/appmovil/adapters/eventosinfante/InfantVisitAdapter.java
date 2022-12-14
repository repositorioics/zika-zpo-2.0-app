package ni.org.ics.zpo.v2.appmovil.adapters.eventosinfante;

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

public class InfantVisitAdapter extends ArrayAdapter<String> {

	private final Context context;
	private final String[] values;
    private final ZpoV2RecoleccionMuestra mZpoV2Muestra;
    private final ZpoV2Mullen mZpoMullen;
    private final ZpoV2IndCuidadoFamilia mZpoICF;
    private final ZpoV2CuestionarioDemografico mZpoCDemo;
    private final ZpoV2CuestSaludInfantil mZpoCuestSaInf;
    private final ZpoV2FormAudicion mZpoEvAuditiva;
    private final ZpoV2EvaluacionVisual mZpoEvalVisual;
    private final ZpoV2ExamenFisicoInfante mZpoExFisInf;

	public InfantVisitAdapter(Context context, int textViewResourceId,
                              String[] values,
                              ZpoV2RecoleccionMuestra zpoMuestra,
                              ZpoV2Mullen mZpoMullen,
                              ZpoV2IndCuidadoFamilia mZpoICF,
                              ZpoV2CuestionarioDemografico zpoCDemo,
                              ZpoV2CuestSaludInfantil zpoCSI,
                              ZpoV2FormAudicion zpoEAudi,
                              ZpoV2EvaluacionVisual zpoEvalVisual,
                              ZpoV2ExamenFisicoInfante zpoEFI) {
		super(context, textViewResourceId, values);
		this.context = context;
		this.values = values;
        this.mZpoV2Muestra = zpoMuestra;
        this.mZpoMullen = mZpoMullen;
        this.mZpoICF = mZpoICF;
        this.mZpoCDemo = zpoCDemo;
        this.mZpoCuestSaInf = zpoCSI;
        this.mZpoEvAuditiva = zpoEAudi;
        this.mZpoEvalVisual = zpoEvalVisual;
        this.mZpoExFisInf = zpoEFI;
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
		switch (position) {
            case 0:
                if (mZpoCDemo!=null){
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.done));
                }else{
                    textView.setTextColor(Color.RED);
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.pending));
                }
                img=getContext().getResources().getDrawable( R.drawable.ic_demo_update);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                break;
            case 1:
                if(mZpoCuestSaInf!=null){
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.done));
                }
                else{
                    textView.setTextColor(Color.RED);
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.pending));
                }
                img=getContext().getResources().getDrawable( R.drawable.ic_child_health_upd);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                break;

            case 2:
                if(mZpoICF!=null){
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.done));
                }
                else{
                    textView.setTextColor(Color.RED);
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.pending));
                }
                img=getContext().getResources().getDrawable( R.drawable.ic_icf);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                break;
            case 3:
                if(mZpoMullen!=null){
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.done));
                }
                else{
                    textView.setTextColor(Color.RED);
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.pending));
                }
                img=getContext().getResources().getDrawable( R.drawable.ic_mullen);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                break;
            case 4:
                if (mZpoV2Muestra != null) {
                    textView.setText(textView.getText() + "\n" + context.getResources().getString(R.string.done));
                } else {
                    textView.setTextColor(Color.RED);
                    textView.setText(textView.getText() + "\n" + context.getResources().getString(R.string.pending));
                }
                img = getContext().getResources().getDrawable(R.drawable.ic_sample);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                break;

            case 5:
                if (mZpoEvAuditiva != null) {
                    textView.setText(textView.getText() + "\n" + context.getResources().getString(R.string.done));
                } else {
                    textView.setTextColor(Color.RED);
                    textView.setText(textView.getText() + "\n" + context.getResources().getString(R.string.pending));
                }
                img = getContext().getResources().getDrawable(R.drawable.ic_audi);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                break;

            case 6:
                if (mZpoEvalVisual != null) {
                    textView.setText(textView.getText() + "\n" + context.getResources().getString(R.string.done));
                } else {
                    textView.setTextColor(Color.RED);
                    textView.setText(textView.getText() + "\n" + context.getResources().getString(R.string.pending));
                }
                img = getContext().getResources().getDrawable(R.drawable.ic_visual);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                break;

            case 7:
                if (mZpoExFisInf != null) {
                    textView.setText(textView.getText() + "\n" + context.getResources().getString(R.string.done));
                } else {
                    textView.setTextColor(Color.RED);
                    textView.setText(textView.getText() + "\n" + context.getResources().getString(R.string.pending));
                }
                img = getContext().getResources().getDrawable(R.drawable.ic_inf_phys_exam);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                break;

            default:
                img = getContext().getResources().getDrawable(R.drawable.logo);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                break;
        }

		return v;
	}
}
