package ni.org.ics.zpo.v2.appmovil.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import ni.org.ics.zpo.v2.appmovil.R;
import ni.org.ics.zpo.v2.appmovil.domain.ZpoInfantData;
import ni.org.ics.zpo.v2.appmovil.utils.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ZpoInfantDataAdapter extends ArrayAdapter<ZpoInfantData> {

	private SimpleDateFormat mDateFormat = new SimpleDateFormat("MMM dd, yyyy");

	public ZpoInfantDataAdapter(Context context, int textViewResourceId,
                                List<ZpoInfantData> items) {
		super(context, textViewResourceId, items);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.complex_list_item, null);
		}
		ZpoInfantData p = getItem(position);
		if (p != null) {

			TextView textView = (TextView) v.findViewById(R.id.name_text);
			if (textView != null) {
				textView.setText(this.getContext().getString(R.string.study_id) + ": " + p.getRecordId());
			}

			textView = (TextView) v.findViewById(R.id.identifier_text);
			if (textView != null) {
				try {
					textView.setText(this.getContext().getString(R.string.inf_dob) + ": " + (p.getInfantBirthDate()!=null?mDateFormat.format(DateUtil.StringToDate(p.getInfantBirthDate(), "dd/MM/yyyy")):"ND"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
			ImageView imageView = (ImageView) v.findViewById(R.id.image);
			if (imageView != null) {
				imageView.setImageResource(R.drawable.ic_baby);
			}
		}
		return v;
	}
}
