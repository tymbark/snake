package com.example.snake;

import hiscore.HiScoreActivity;
import hiscore.PopupHiScoreActivity;
import options.OptionsActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {
	
	boolean arc, bless, nyan;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		arc = bless = nyan = false;
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button StartGame = (Button)findViewById(R.id.pNewGame);	
		Button Options = (Button)findViewById(R.id.pOptions);
		Button HiScore = (Button)findViewById(R.id.pHiScore);
	    Button Close = (Button)findViewById(R.id.pExit);
	    
	    StartGame.setOnClickListener(this);
	    Options.setOnClickListener(this);
	    HiScore.setOnClickListener(this);
	    Close.setOnClickListener(this);	
	    
	    
	    	
	}

	@Override
	protected void onResume() {
		super.onResume();
		PopupHiScoreActivity.setNewScoreSaved(false);
	}

	@Override
	public void onClick(View view) {
		switch(view.getId()){
		
		case R.id.pNewGame:
			Intent intent1 = new Intent(MainActivity.this, PlayingActivity.class);
			
			intent1.putExtra("arcade", arc);
			intent1.putExtra("nyancat", nyan);
			intent1.putExtra("buttonless", bless);
			
            startActivity(intent1); 
			break;
			
		case R.id.pOptions:
			Log.d("MainActivity","Options:START");
			Intent intent2 = new Intent(MainActivity.this, OptionsActivity.class);
            startActivityForResult(intent2, 0);
			Log.d("MainActivity","Options:STOP");
			break;			
			
		case R.id.pHiScore:
			Log.d("MainActivity","Hiscores:START");
            Intent intent3 = new Intent(MainActivity.this, HiScoreActivity.class);
            startActivity(intent3);
            Log.d("MainActivity","Hiscores:STOP");
			break;
			
		case R.id.pExit:
			finish(); 
			break;
		}
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK){
			Bundle b = data.getExtras();
			arc = b.getBoolean("arcade");
			bless = b.getBoolean("buttonless");
			nyan = b.getBoolean("nyancat");
		}
	}
}

