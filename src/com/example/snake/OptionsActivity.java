package com.example.snake;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ImageView;
import android.widget.Toast;
 
public class OptionsActivity extends Activity implements OnGroupClickListener, 
				OnChildClickListener,OnGroupCollapseListener,OnGroupExpandListener{
 
    ExpandableList listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    int lastExpandedGroupPosition;
    Intent i = new Intent();
    Bundle carry = new Bundle();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
 
        expListView = (ExpandableListView) findViewById(R.id.expandableListView1);
        ImageView iV = (ImageView)findViewById(R.id.imageViewSnake);
        // preparing list data
        prepareListData();
        listAdapter = new ExpandableList(this, listDataHeader, listDataChild);
 

        expListView.setAdapter(listAdapter);

        expListView.setOnGroupClickListener(this);
        expListView.setOnGroupExpandListener(this);
        expListView.setOnChildClickListener(this);
        expListView.setOnGroupCollapseListener(this);
        

    }
 
	@Override
	public boolean onGroupClick(ExpandableListView parent, View v,
			int groupPosition, long id) {
		//Toast.makeText(getApplicationContext(),"Group Clicked " + listDataHeader.get(groupPosition),Toast.LENGTH_SHORT).show();
		return false;
	}
	
	@Override
	public void onGroupExpand(int groupPosition) {
    	if (groupPosition != lastExpandedGroupPosition) {
            expListView.collapseGroup(lastExpandedGroupPosition);

        }
    	lastExpandedGroupPosition = groupPosition;
        //Toast.makeText(getApplicationContext(),listDataHeader.get(groupPosition) + " Expanded",Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void onGroupCollapse(int groupPosition) {
		//Toast.makeText(getApplicationContext(),listDataHeader.get(groupPosition) + " Collapsed",Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		
		boolean classic, arc, bless, nyan;
		arc = bless = nyan = false;
		classic = true;
		
		String first = listDataHeader.get(groupPosition);
		String second = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
		
		//switch string adapter :D // 
		
		if(first.contentEquals(listDataHeader.get(0))){
			if (second.contentEquals(listDataChild.get(listDataHeader.get(0)).get(0))){
				Log.d(first,second);
				classic = true;
				arc = false;
				nyan = false;
			}else if(second.contentEquals(listDataChild.get(listDataHeader.get(0)).get(1))){
				Log.d(first,second);
				classic = false;
				arc = true;
				nyan = false;
			}else if(second.contentEquals(listDataChild.get(listDataHeader.get(0)).get(2))){
				Log.d(first,second);
				classic = false;
				arc = false;
				nyan = true;
			}
		}else if(first.contentEquals(listDataHeader.get(1))){
			if (second.contentEquals(listDataChild.get(listDataHeader.get(1)).get(0))){
				Log.d(first,second);
				bless = false;
			}else if(second.contentEquals(listDataChild.get(listDataHeader.get(1)).get(1))){
				Log.d(first,second);
				bless = true;
			}
		}else if(first.contentEquals(listDataHeader.get(2))){
			if (second.contentEquals(listDataChild.get(listDataHeader.get(2)).get(0))){
				Log.d(first,second);
				
				HiScoreTable hst = new HiScoreTable(this);
				hst.open();
				hst.deleteAll();
				hst.close();
			}else if(second.contentEquals(listDataChild.get(listDataHeader.get(2)).get(1))){
				Log.d(first,second);
				//////////////
			}
		}
		
		carry.putBoolean("arcade", arc);
		carry.putBoolean("nyancat", nyan);
		carry.putBoolean("buttonless", bless);
		carry.putBoolean("classicgame", classic);
		
		i.putExtras(carry);
		setResult(RESULT_OK,i);
		return false;
	}

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
 
        listDataHeader.add("Tryb gry");
        listDataHeader.add("Sterowanie");
        listDataHeader.add("Ustawienia");
 
        List<String> item1 = new ArrayList<String>();
        item1.add("Klasyczny");
        item1.add("Arcade");
        item1.add("Nyan Cat");
 
        List<String> item2 = new ArrayList<String>();
        item2.add("Z przyciskami");
        item2.add("Bez Przycisków");
        
        List<String> item3 = new ArrayList<String>();
        item3.add("Resetuj rekordy");
        item3.add("In_progress");
 
        listDataChild.put(listDataHeader.get(0), item1);
        listDataChild.put(listDataHeader.get(1), item2);
        listDataChild.put(listDataHeader.get(2), item3);
    }

}