package main;

import com.example.snake.R;

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

	private boolean[] savedOptions; // readme.txt
	private static Bundle b;
	private static Intent i;
	private final static String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "OnCreate");
		initOptions();
		b = new Bundle();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button StartGame = (Button) findViewById(R.id.pNewGame);
		Button Options = (Button) findViewById(R.id.pOptions);
		Button HiScore = (Button) findViewById(R.id.pHiScore);
		Button Close = (Button) findViewById(R.id.pExit);

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
		switch (view.getId()) {

		case R.id.pNewGame:
			i = new Intent(MainActivity.this, PlayingActivity.class);
			b.putBooleanArray("options", savedOptions);
			i.putExtras(b);
			startActivity(i);
			break;

		case R.id.pOptions:
			Log.d(TAG, "Options:START");
			i = new Intent(MainActivity.this, OptionsActivity.class);
			b.putBooleanArray("options", savedOptions);
			i.putExtras(b);
			startActivityForResult(i, 0);
			break;

		case R.id.pHiScore:
			Log.d(TAG, "Hiscores:START");
			i = new Intent(MainActivity.this, HiScoreActivity.class);
			startActivity(i);
			break;

		case R.id.pExit:
			finish();
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			Bundle b = data.getExtras();
			this.savedOptions = b.getBooleanArray("options");
		}
	}

	private void initOptions() {
		boolean z = false;
		boolean o = true;
		savedOptions = new boolean[] { o, z, z, z, z, o, z, z, z, z };
	}
}
