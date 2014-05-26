package options;

import java.util.ArrayList;

import android.util.Log;

public class Option {
	private String name;
	private int mode; // 0-textBox, 1-checkBox, 2-separator, 3-radioButton
	private boolean cbState;
	private boolean rbState;
	private ArrayList<Option> radioOptions;

	public Option(String _name, int _mode, boolean _cbState, boolean _rbState) {
		setName(_name);
		setCbState(_cbState);
		setRbState(_rbState);
		setMode(_mode);
	}

	public boolean getCbState() {
		return cbState;
	}

	public void setCbState(boolean state) {
		this.cbState = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void toggle() {
		this.cbState = !this.cbState;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public ArrayList<Option> getRadioOptions() {
		return radioOptions;
	}

	public void setRadioOptions(ArrayList<Option> radioOptions) {
		this.radioOptions = radioOptions;
	}

	public void radioOptionSelect() {
		this.rbState = true;
		for (Option o : radioOptions) {
			o.setRbState(false);
			o.printOption();
		}
	}

	public boolean getRbState() {
		return rbState;
	}

	public void setRbState(boolean rbState) {
		this.rbState = rbState;
	}

	public void addRadioOption(Option o) {
		if (radioOptions == null) {
			radioOptions = new ArrayList<Option>();
		}
		radioOptions.add(o);
	}

	public void printOption() {
		Log.d("OptionPrint", "name:" + name + " mode:" + mode + " cbstate:"
				+ (cbState == true ? "true" : "false") + " rbstate:"
				+ (rbState == true ? "true" : "false") + " rOptions:"
				+ (radioOptions!=null? "yes" : "no"));
	}
}
