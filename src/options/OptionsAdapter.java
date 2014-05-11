package options;

import java.util.List;

import com.example.snake.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class OptionsAdapter extends ArrayAdapter<Option> {

	private LayoutInflater inflater;
	private final String TAG = "OptionsAdapter";

	public OptionsAdapter(Context context, List<Option> objects) {
		super(context, R.layout.options_listview, R.id.optTextview, objects);
		inflater = LayoutInflater.from(context);
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		CheckBox checkbox;
		TextView textview;
		OptionsViewHolder ovh;
		final Option opt = this.getItem(position); // may need cast (Option)
		//ovh.no = position;
		opt.print();
		if (opt.isCheckbox()) {
			if (convertView == null) {
				Log.d(TAG,"convertview:NULL");
				convertView = inflater
						.inflate(R.layout.row_text_checkbox, null);

				checkbox = (CheckBox) convertView
						.findViewById(R.id.optCheckbox);
				textview = (TextView) convertView
						.findViewById(R.id.optTextview);

				ovh = new OptionsViewHolder(checkbox, textview);
				convertView.setTag(ovh);

				checkbox.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						opt.toggle();
					}
				});
			} else {
				Log.d(TAG,"convertview:NOT NULL");
				ovh = (OptionsViewHolder) convertView.getTag();
				checkbox = ovh.getCheckbox();
				textview = ovh.getTextview();
			}
			checkbox.setChecked(opt.getState());
			textview.setText(opt.getName());
		} else {
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.row_text, null);

				textview = (TextView) convertView
						.findViewById(R.id.optTextview);

				ovh = new OptionsViewHolder(null, textview);
				convertView.setTag(ovh);
			} else {
				ovh = (OptionsViewHolder) convertView.getTag();
				textview = ovh.getTextview();
			}
			textview.setText(opt.getName());
		}

		return convertView;
	}
	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 2;
	}
	@Override
	public int getItemViewType(int position) {
		final Option opt = this.getItem(position);
		if(opt.isCheckbox()){
			
		}else{
			
		}
		return super.getItemViewType(position);
	}
	
	
}
