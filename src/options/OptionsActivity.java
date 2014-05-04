package options;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.snake.R;
import com.example.snake.R.layout;

import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;
 
public class OptionsActivity extends ExpandableListActivity implements OnChildClickListener, OnItemClickListener{
 
    ExpandableList listAdapter;
    ExpandableListView expListView;
    ArrayList<String> headers;
    HashMap<String, ArrayList<Option>> options;
    int lastExpandedGroupPosition;
    Intent i = new Intent();
    Bundle carry = new Bundle();
    private final static String TAG = "OptionsActivity";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	Log.d(TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        prepareListData();   
        listAdapter = new ExpandableList(headers, options);
        listAdapter.setInflater((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE), this);
        
        expListView = (ExpandableListView) findViewById(android.R.id.list);
        expListView.setGroupIndicator(null);
        expListView.setOnItemClickListener(this);

        expListView.setClickable(true);
        expListView.setAdapter(listAdapter);
        
        //setListAdapter(listAdapter);
    }
 
	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		Log.d(TAG,"onClickChild");
		
//		boolean classic, arc, bless, nyan;
//		arc = bless = nyan = false;
//		classic = true;
//		CheckBox cb = (CheckBox)v.findViewById(R.id.list_item1);
//		//cb.toggle();
//		cb.setBackgroundColor(Color.BLUE);
//		Log.d("OPTACT","CLicked");
//		
//		String first = headers.get(groupPosition);
//		String second = options.get(headers.get(groupPosition)).get(childPosition);
//		
//		//switch string adapter :D // 
//		
//		if(first.contentEquals(headers.get(0))){
//			if (second.contentEquals(options.get(headers.get(0)).get(0))){
//				Log.d(first,second);
//				
//				classic = true;
//				arc = false;
//				nyan = false;
//			}else if(second.contentEquals(options.get(headers.get(0)).get(1))){
//				Log.d(first,second);
//				classic = false;
//				arc = true;
//				nyan = false;
//			}else if(second.contentEquals(options.get(headers.get(0)).get(2))){
//				Log.d(first,second);
//				classic = false;
//				arc = false;
//				nyan = true;
//			}
//		}else if(first.contentEquals(headers.get(1))){
//			if (second.contentEquals(options.get(headers.get(1)).get(0))){
//				Log.d(first,second);
//				bless = false;
//			}else if(second.contentEquals(options.get(headers.get(1)).get(1))){
//				Log.d(first,second);
//				bless = true;
//			}
//		}else if(first.contentEquals(headers.get(2))){
//			if (second.contentEquals(options.get(headers.get(2)).get(0))){
//				Log.d(first,second);
//				
//				HiScoreTable hst = new HiScoreTable(this);
//				hst.open();
//				hst.deleteAll();
//				hst.close();
//			}else if(second.contentEquals(options.get(headers.get(2)).get(1))){
//				Log.d(first,second);
//				//////////////
//			}
//		}
//		
//		carry.putBoolean("arcade", arc);
//		carry.putBoolean("nyancat", nyan);
//		carry.putBoolean("buttonless", bless);
//		carry.putBoolean("classicgame", classic);
//		
//		i.putExtras(carry);
//		setResult(RESULT_OK,i);
		return false;
	}

    private void prepareListData() {
		headers = new ArrayList<String>();
        options = new HashMap<String, ArrayList<Option>>();
 
        headers.add("Tryb gry");
        headers.add("Sterowanie");
        headers.add("Ustawienia");
 
        ArrayList<Option> _trybgry = new ArrayList<Option>();
        _trybgry.add(new Option("Classic",false));
        _trybgry.add(new Option("Arcade",false));
        _trybgry.add(new Option("Nyan Cat",false));
 
        ArrayList<Option> _sterowanie = new ArrayList<Option>();
        _sterowanie.add(new Option("Z przyciskami",false));
        _sterowanie.add(new Option("Bez przycisków",false));
        
        ArrayList<Option> _ustawienia = new ArrayList<Option>();
        _ustawienia.add(new Option("Z przyciskami",false));
        _ustawienia.add(new Option("Bez Przycisków",false));

 
        options.put(headers.get(0), _trybgry);
        options.put(headers.get(1), _sterowanie);
        options.put(headers.get(2), _ustawienia);
    }
    public void dupa(View v){
    	Toast.makeText(this, "DUPA METODA", Toast.LENGTH_LONG).show();
    }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
		Log.d(TAG,"chujchujchujchuj");
		// TODO Auto-generated method stub
		
	}
}