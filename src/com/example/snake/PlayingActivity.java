package com.example.snake;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;


public class PlayingActivity extends Activity implements OnClickListener{
	
	SnakeCage v;
	static boolean arc;
	boolean nyan, justStarted;
	boolean bless;
	Button snakeGoUp;
	Button snakeGoDown;
	Button snakeGoLeft;
	Button snakeGoRight;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    
        
        Bundle b = getIntent().getExtras();
	    if(b != null){
		    
	    	Log.d("PlayingActivity.class","intent not empty");
	    	
	    	arc = getIntent().getExtras().getBoolean("arcade");
	    	nyan = getIntent().getExtras().getBoolean("nyancat");
	    	bless = getIntent().getExtras().getBoolean("buttonless");
	    }
	    
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.frame_layout);
        v = (SnakeCage)findViewById(R.id.sView);
        
        snakeGoUp = (Button)findViewById(R.id.snakeUp);
        snakeGoDown = (Button)findViewById(R.id.snakeDown);
        snakeGoLeft = (Button)findViewById(R.id.snakeLeft);
        snakeGoRight = (Button)findViewById(R.id.snakeRight);

        snakeGoUp.setOnClickListener(this);
        snakeGoDown.setOnClickListener(this);
        snakeGoLeft.setOnClickListener(this);
        snakeGoRight.setOnClickListener(this);
        
    }
    


    
	@Override
	protected void onPause() {
		super.onPause();
		v.pause();
	}


	@Override
	protected void onResume() {
		super.onResume();
		v.resume();
		Log.d("PlayingActivity","onResume()");
	}


	@Override
	public void onClick(View view) {
		switch(view.getId()){
		
		case R.id.snakeUp:
			v.move(1);
			break;
			
		case R.id.snakeDown:
			v.move(2);
			break;
			
		case R.id.snakeLeft:
			v.move(3);
			break;
			
		case R.id.snakeRight:
			v.move(4);
			break;
		}
		
	}
}