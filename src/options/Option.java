package options;

import android.util.Log;

public class Option {
	private String name;
	private boolean state;
	private boolean checkbox;

	public Option(String _name, boolean _state, boolean _checkbox) {
		setName(_name);
		setState(_state);
		setCheckbox(_checkbox);
	}

	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void toggle() {
		this.state = !this.state;
	}

	public boolean isCheckbox() {
		return checkbox;
	}

	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}

	public void print() {
		if (checkbox && state)
			Log.d("", "NAME:" + this.name + " STATE:true" + " CHECKBOX:true");
		if (!checkbox && state)
			Log.d("", "NAME:" + this.name + " STATE:true" + " CHECKBOX:false");
		if (checkbox && !state)
			Log.d("", "NAME:" + this.name + " STATE:false" + " CHECKBOX:true");
		if (!checkbox && !state)
			Log.d("", "NAME:" + this.name + " STATE:false" + " CHECKBOX:false");
	}
}
