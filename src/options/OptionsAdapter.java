package options;

import java.util.ArrayList;

import com.example.snake.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

public class OptionsAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater;
	private ArrayList<Option> options;
	private final String TAG = "OptionsAdapter";
	private static final int type_textBox = 0;
	private static final int type_checkBox = 1;
	private static final int type_separator = 2;
	private static final int type_radioButton = 3;

	public OptionsAdapter(Context context, ArrayList<Option> options) {
		this.context = context;
		this.options = options;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getItemViewType(int position) {
		final Option opt = this.getItem(position);
		return opt.getMode();
	}

	@Override
	public int getCount() {
		return options.size();
	}

	@Override
	public int getViewTypeCount() {
		return 4;
	}

	@Override
	public Option getItem(int position) {
		return options.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public boolean isEnabled(int position) {
		return options.get(position).getMode() == 2 ? false : true;
	}
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final OptionsViewHolder ovh;
		int type = getItemViewType(position);
		final Option opt = this.getItem(position);

		if (convertView == null) {
			ovh = new OptionsViewHolder();

			convertView = inflater.inflate(R.layout.row_text_checkbox, null);
			ovh.setTextview((TextView) convertView
					.findViewById(R.id.opt_row_text));
			ovh.setCheckbox((CheckBox) convertView
					.findViewById(R.id.opt_row_cb));
			ovh.setRadiobutton((RadioButton) convertView
					.findViewById(R.id.opt_row_rb));
			ovh.getTextview().setText(opt.getName());
			ovh.getCheckbox().setText("");

			switch (type) {
			case type_checkBox:
				ovh.getRadiobutton().setVisibility(View.GONE);
				ovh.getCheckbox().setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						opt.toggle();
						notifyDataSetChanged();
					}
				});
				break;
			case type_textBox:
				ovh.getRadiobutton().setVisibility(View.GONE);
				ovh.getCheckbox().setVisibility(View.GONE);
				break;
			case type_separator:
				ovh.getRadiobutton().setVisibility(View.GONE);
				ovh.getCheckbox().setVisibility(View.GONE);
				TextView tv = ovh.getTextview();
				tv.setTextSize(12);
				tv.setAllCaps(true);
				tv.setTextColor(Color.GRAY);
				tv.setGravity(Gravity.CENTER);
				tv.setPadding(10, 30, 10, 5);
				break;
			case type_radioButton:
				ovh.getCheckbox().setVisibility(View.GONE);
				ovh.getRadiobutton().setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Log.d(TAG,"radioB:"+position);
						opt.radioOptionSelect();
						notifyDataSetChanged();
					}
				});
				break;
			}
			convertView.setTag(ovh);

		} else {
			ovh = (OptionsViewHolder) convertView.getTag();
		}
		ovh.getTextview().setText(opt.getName());
		ovh.getRadiobutton().setChecked(opt.getRbState());
		ovh.getCheckbox().setChecked(opt.getCbState());
		return convertView;
	}

}
