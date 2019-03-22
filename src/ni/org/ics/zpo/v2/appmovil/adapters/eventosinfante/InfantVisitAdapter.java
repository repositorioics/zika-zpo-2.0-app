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
import ni.org.ics.zpo.v2.appmovil.domain.ZpoV2InfantOtoacousticEmissions;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoV2InfantOphthalmologicEvaluation;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoV2RecoleccionMuestra;

public class InfantVisitAdapter extends ArrayAdapter<String> {

	private final Context context;
	private final String[] values;
    private final ZpoV2RecoleccionMuestra mZpoV2Muestra;
    private final ZpoV2InfantOtoacousticEmissions mZpoOtoE;
    private final ZpoV2InfantOphthalmologicEvaluation mZpoV2IOE;

	public InfantVisitAdapter(Context context, int textViewResourceId,
                              String[] values,
                              ZpoV2RecoleccionMuestra zpoMuestra,
                              ZpoV2InfantOtoacousticEmissions mZpo07OtoE,
                              ZpoV2InfantOphthalmologicEvaluation mZpoV2IOE) {
		super(context, textViewResourceId, values);
		this.context = context;
		this.values = values;
        this.mZpoV2Muestra = zpoMuestra;
        this.mZpoOtoE = mZpo07OtoE;
        this.mZpoV2IOE = mZpoV2IOE;
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

            case 3:
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
            case 4:
                if (mZpoOtoE != null) {
                    textView.setText(textView.getText() + "\n" + context.getResources().getString(R.string.done));
                } else {
                    textView.setTextColor(Color.RED);
                    textView.setText(textView.getText() + "\n" + context.getResources().getString(R.string.pending));
                }
                img = getContext().getResources().getDrawable(R.drawable.ic_oae);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                break;
            case 5:
                if(mZpoV2IOE!=null){
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.done));
                }
                else{
                    textView.setTextColor(Color.RED);
                    textView.setText(textView.getText()+"\n"+ context.getResources().getString(R.string.pending));
                }
                img=getContext().getResources().getDrawable( R.drawable.ic_monthly);
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
