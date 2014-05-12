package options;

import java.util.ArrayList;

import com.example.snake.R;

import android.app.Activity;
import android.app.LauncherActivity.ListItem;
import android.content.Intent;
import android.os.Bundle;
import android.test.PerformanceTestCase;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class OptionsActivity extends Activity implements OnItemClickListener {

	ListView listview;
	BaseAdapter adapter;
	ArrayList<Option> options;
	Intent i = new Intent();
	Bundle b = new Bundle();
	private boolean[] savedOptions;
	private final static String TAG = "OptionsActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		savedOptions = getIntent().getExtras().getBooleanArray("options");
		setContentView(R.layout.activity_options);
		prepareListData();
		listview = (ListView) findViewById(R.id.listView1);
		listview.setOnItemClickListener(this);
		adapter = new OptionsAdapter(this, options);
		listview.setAdapter(adapter);

	}

	@Override
	public void finish() {
		Log.d(TAG, "finish");
		rewriteOptions();
		b.putBooleanArray("options", savedOptions);
		i.putExtras(b);
		setResult(RESULT_OK, i);
		super.finish();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		OptionsViewHolder ovh;
		Log.d(TAG, "clicked" + position + ", id:" + id);
		Option opt = (Option) adapter.getItem(position);
		ovh = (OptionsViewHolder) view.getTag();
		switch (opt.getMode()) {
		case 0:
			break;
		case 1:
			opt.toggle();
			ovh.getCheckbox().setChecked(opt.getCbState());
			break;
		case 2:
			break;
		case 3:
			opt.radioOptionSelect();
			ovh.getRadiobutton().setChecked(opt.getRbState());
			adapter.notifyDataSetChanged();
			break;
		}
	}

	private void rewriteOptions() {
		// MODES:
		savedOptions[0] = options.get(3).getRbState();
		savedOptions[1] = options.get(4).getRbState();
		savedOptions[2] = options.get(5).getRbState();
		savedOptions[3] = options.get(6).getRbState();
		savedOptions[4] = options.get(7).getRbState();
		// BUTTONS
		savedOptions[5] = options.get(1).getCbState();
	}

	private void prepareListData() {
		options = new ArrayList<Option>();
		options.add(new Option("general", 2, false, false));
		options.add(new Option("Buttons on screen", 1, savedOptions[5], false));
		options.add(new Option("game mode", 2, false, false));
		options.add(new Option("Classic", 3, false, savedOptions[0]));
		options.add(new Option("Arcade", 3, false, savedOptions[1]));
		options.add(new Option("todo", 2, false, false));
		options.add(new Option("6", 0, false, false));
		options.add(new Option("7", 0, false, false));
		options.add(new Option("8", 0, false, false));
		options.add(new Option("9", 0, false, false));
		options.add(new Option("10", 0, false, false));
		options.add(new Option("11", 0, false, false));
		options.add(new Option("12", 0, false, false));
		options.add(new Option("13", 0, false, false));
		options.add(new Option("14", 0, false, false));
		options.add(new Option("15", 0, false, false));

		options.get(3).addRadioOption(options.get(4));
		options.get(4).addRadioOption(options.get(3));
	}

}
