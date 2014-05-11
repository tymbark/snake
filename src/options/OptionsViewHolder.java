package options;

import android.widget.CheckBox;
import android.widget.TextView;

public class OptionsViewHolder {
	private CheckBox checkbox;
	private TextView textview;
	public int no;

	public OptionsViewHolder(CheckBox checkbox, TextView textview) {
		this.setCheckbox(checkbox);
		this.setTextview(textview);
	}

	public OptionsViewHolder() {
	}

	public CheckBox getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(CheckBox checkbox) {
		this.checkbox = checkbox;
	}

	public TextView getTextview() {
		return textview;
	}

	public void setTextview(TextView textview) {
		this.textview = textview;
	}
	
}
