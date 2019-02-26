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

public class VisitAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] values;

    public VisitAdapter(Context context, int textViewResourceId,
                        String[] values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
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
                img=getContext().getResources().getDrawable( R.drawable.ic_sample);
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
