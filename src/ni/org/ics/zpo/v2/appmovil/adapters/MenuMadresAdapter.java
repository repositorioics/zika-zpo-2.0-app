package ni.org.ics.zpo.v2.appmovil.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import ni.org.ics.zpo.v2.appmovil.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MenuMadresAdapter extends ArrayAdapter<String> {

	private final String[] values;
    private final Calendar fechaIngreso;
	private final Context context;
	private Date fechaEvento;
	private Date todayDate;
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	public MenuMadresAdapter(Context context, int textViewResourceId,
                             String[] values) {
		super(context, textViewResourceId, values);
		this.context = context;
		this.values = values;
		try {
			this.todayDate = formatter.parse(formatter.format(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.fechaIngreso = Calendar.getInstance();
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
            case 0:
                img=getContext().getResources().getDrawable( R.drawable.ic_enroll);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                break;
            default:
                img=getContext().getResources().getDrawable( R.drawable.logo);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
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
