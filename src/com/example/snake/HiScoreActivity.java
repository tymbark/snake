package com.example.snake;

import java.util.ArrayList;
import java.util.SortedMap;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HiScoreActivity extends Activity implements OnClickListener {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hiscore);

		Button Back = (Button) findViewById(R.id.pBack);
		TextView ResultsName = (TextView) findViewById(R.id.hisResultName);
		TextView ResultsScore = (TextView) findViewById(R.id.hisResultScore);
		TextView ResultsLP = (TextView) findViewById(R.id.hisLP);
		Back.setOnClickListener(this);

		HiScoreTable highScoreTable = new HiScoreTable(this);
		/*
		 * highScoreTable.open(); String dataName =
		 * highScoreTable.getDataName(); String dataScore =
		 * highScoreTable.getDataScore(); String dataLP =
		 * highScoreTable.getDataLP(); highScoreTable.close();
		 * ResultsName.setText(dataName); ResultsScore.setText(dataScore);
		 * ResultsLP.setText(dataLP);
		 */
		highScoreTable.open();
		//Log.d("dupa", "chuj");
		ArrayList<HighScoreRow> array = new ArrayList<HighScoreRow>();
		array = highScoreTable.getData();
		
		array = sort(array);
		String names = "";
		String results = "";
		String lps = "";
		int i = 0;
		//Log.d("sorted", "chuj");
		for(HighScoreRow a : array){
			i++;
			//Log.d("item",""+a.getResult());
			names = names + a.getName() + "\n";
			results = results + a.getResult() + "\n";
			lps = lps + i + "\n";
		}
		highScoreTable.close();
		ResultsName.setText(names);
		ResultsScore.setText(results);
		ResultsLP.setText(lps);
	}

	private ArrayList<HighScoreRow> sort(ArrayList<HighScoreRow> array) {
		
		ArrayList<HighScoreRow> sortedArray = new ArrayList<HighScoreRow>();
		HighScoreRow max;
		int d = 9;
		int aSize = array.size();
		while(d>0){
			//d--;
			max = new HighScoreRow(0, "", -1);
			for(HighScoreRow a : array){
				//Log.d("sort array","item "+a.getmResult());
				if(max.getResult()<=a.getResult()){
					max = a;
				}
			}
			//Log.d("sort array","max= "+max.getmResult());
			sortedArray.add(max);
			for(HighScoreRow a : sortedArray){
			//Log.d("sarr",""+a.getmResult());
		}
			if(aSize == sortedArray.size()){
				break;
			}
			
			array.remove(max);
		}
		return sortedArray;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.pBack:
			finish();
			break;
		}

	}
}