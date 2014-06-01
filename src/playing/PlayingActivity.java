package playing;

import tymbark.snake.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class PlayingActivity extends Activity implements OnClickListener,
		OnTouchListener {

	private static final String TAG = "PlayingActivity";
	SnakeCage viewSC;
	Vector2D direction;
	static boolean arc;
	boolean nyan, justStarted;
	boolean bless;
	boolean[] savedOptions;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle b = getIntent().getExtras();

		direction = new Vector2D();
		if (b != null) {

			Log.d("PlayingActivity.class", "intent not empty");

			savedOptions = getIntent().getExtras().getBooleanArray("options");
			arc = savedOptions[1];
		}
		
		Log.d(TAG,"arc="+arc);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.playing_view);
		viewSC = (SnakeCage) findViewById(R.id.snakeCage1);
		viewSC.setOnTouchListener(this);


	}

	@Override
	protected void onPause() {
		super.onPause();
		viewSC.pause();
	}

	@Override
	protected void onResume() {
		Log.d("PlayingActivity", "onResume()");
		super.onResume();
		viewSC.resume();
	}

	@Override
	public void onClick(View view) {
		Log.d(TAG, "OnClick");
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		boolean vectorStarted = false;

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (vectorStarted)
				break;
			direction.x1 = event.getX();
			direction.y1 = event.getY();
			vectorStarted = true;
			break;
		case MotionEvent.ACTION_UP:
			direction.x2 = event.getX();
			direction.y2 = event.getY();
			viewSC.redraw(direction.adjust());
			break;
		}
		return true;
	}
}