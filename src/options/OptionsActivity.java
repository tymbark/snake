package options;

import java.util.ArrayList;
import com.example.snake.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class OptionsActivity extends Activity implements OnItemClickListener {

	ListView listview;
	ArrayAdapter<Option> adapter;
	ArrayList<Option> options;
	Intent i = new Intent();
	Bundle carry = new Bundle();
	private final static String TAG = "OptionsActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_options);
		prepareListData();
		listview = (ListView) findViewById(R.id.listView1);
		listview.setOnItemClickListener(this);
		adapter = new OptionsAdapter(this, options);
		listview.setAdapter(adapter);

	}

	// @Override
	// public boolean onChildClick(ExpandableListView parent, View v,
	// int groupPosition, int childPosition, long id) {
	// Log.d(TAG,"onClickChild");
	//
	// boolean classic, arc, bless, nyan;
	// arc = bless = nyan = false;
	// classic = true;
	// CheckBox cb = (CheckBox)v.findViewById(R.id.list_item1);
	// //cb.toggle();
	// cb.setBackgroundColor(Color.BLUE);
	// Log.d("OPTACT","CLicked");
	//
	// String first = headers.get(groupPosition);
	// String second =
	// options.get(headers.get(groupPosition)).get(childPosition);
	//
	// //switch string adapter :D //
	//
	// if(first.contentEquals(headers.get(0))){
	// if (second.contentEquals(options.get(headers.get(0)).get(0))){
	// Log.d(first,second);
	//
	// classic = true;
	// arc = false;
	// nyan = false;
	// }else if(second.contentEquals(options.get(headers.get(0)).get(1))){
	// Log.d(first,second);
	// classic = false;
	// arc = true;
	// nyan = false;
	// }else if(second.contentEquals(options.get(headers.get(0)).get(2))){
	// Log.d(first,second);
	// classic = false;
	// arc = false;
	// nyan = true;
	// }
	// }else if(first.contentEquals(headers.get(1))){
	// if (second.contentEquals(options.get(headers.get(1)).get(0))){
	// Log.d(first,second);
	// bless = false;
	// }else if(second.contentEquals(options.get(headers.get(1)).get(1))){
	// Log.d(first,second);
	// bless = true;
	// }
	// }else if(first.contentEquals(headers.get(2))){
	// if (second.contentEquals(options.get(headers.get(2)).get(0))){
	// Log.d(first,second);
	//
	// HiScoreTable hst = new HiScoreTable(this);
	// hst.open();
	// hst.deleteAll();
	// hst.close();
	// }else if(second.contentEquals(options.get(headers.get(2)).get(1))){
	// Log.d(first,second);
	// //////////////
	// }
	// }
	//
	// carry.putBoolean("arcade", arc);
	// carry.putBoolean("nyancat", nyan);
	// carry.putBoolean("buttonless", bless);
	// carry.putBoolean("classicgame", classic);
	//
	// i.putExtras(carry);
	// setResult(RESULT_OK,i);
	// return false;
	// }

	private void prepareListData() {
		options = new ArrayList<Option>();
		options.add(new Option("1", false, true));
		options.add(new Option("2", false, false));
		options.add(new Option("3", false, false));
		options.add(new Option("4", false, true));
		options.add(new Option("5", false, false));
		options.add(new Option("6", false, false));
		options.add(new Option("7", false, false));
		options.add(new Option("8", false, false));
		options.add(new Option("9", false, false));
		options.add(new Option("10", false, false));
		options.add(new Option("11", false, false));
		options.add(new Option("12", false, false));
		options.add(new Option("13", false, false));
		options.add(new Option("14", false, false));
		options.add(new Option("15", false, false));
		options.add(new Option("16", false, false));
		options.add(new Option("17", false, false));
		options.add(new Option("18", false, false));
		options.add(new Option("19", false, false));
		options.add(new Option("20", false, false));
		options.add(new Option("21", false, false));
		options.add(new Option("22", false, false));
		options.add(new Option("23", false, false));
		options.add(new Option("24", false, false));
		options.add(new Option("25", false, false));
		options.add(new Option("26", false, false));
		options.add(new Option("27", false, false));
		options.add(new Option("28", false, false));
		options.add(new Option("29", false, false));
		options.add(new Option("30", false, false));
		options.add(new Option("31", false, false));
		options.add(new Option("32", false, false));
		
		for(Option o : options){
			o.print();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		OptionsViewHolder ovh;

		Log.d(TAG, "clicked" + position + ", id:" + id);
		
		Option opt = adapter.getItem(position);
		ovh = (OptionsViewHolder) view.getTag();
		if (opt.isCheckbox()) {
			opt.toggle();
			ovh.getCheckbox().setChecked(opt.getState());
		}
	}
}
