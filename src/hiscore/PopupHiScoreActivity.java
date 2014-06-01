package hiscore;

import tymbark.snake.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PopupHiScoreActivity extends Activity implements OnClickListener {

	Integer scoreInt;
	Button popOk;
	TextView popTextScore;
	TextView popTextWelcome;
	EditText popEditText;
	boolean newhiscore;
	static boolean newScoreSaved;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setNewScoreSaved(false);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_popup);

		popOk = (Button) findViewById(R.id.popSave);
		popTextScore = (TextView) findViewById(R.id.popTextScore);
		popTextWelcome = (TextView) findViewById(R.id.popText);
		popEditText = (EditText) findViewById(R.id.popEditText);
		newhiscore = false;

		popOk.setOnClickListener(this);

		Bundle b = getIntent().getExtras();
		if (b != null) {

			Log.d("his", "intent not empty");
			scoreInt = getIntent().getExtras().getInt("key");

			HiScoreTable hst = new HiScoreTable(this);
			hst.open();
			int LastScore = hst.getLastHiScore();
			hst.close();
			popTextScore.setText("" + scoreInt);
			Log.d("popup", "ostatni = " + LastScore);
			if (scoreInt > LastScore) {
				// new hiscore
				newhiscore = true;

			} else {
				newhiscore = false;
				popTextWelcome.setText("Nie ma rekordu");
				popEditText.setText("postaraj sie bardziej");
				popOk.setText("To smutne");
				popEditText.setEnabled(false);
			}

		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.popSave:

			if (!newhiscore) {
				Log.d("popup", "nie zapisuje rekordu");
				setNewScoreSaved(true);
				finish();
			} else {

				boolean works = true;
				try {
					String name = popEditText.getText().toString();
					// String score = scoreInt.toString();
					int score = scoreInt;

					Log.d("PopupHiScoreActivity", "saving... " + score + " : "
							+ name);

					HiScoreTable entry = new HiScoreTable(
							PopupHiScoreActivity.this);
					entry.open();
					entry.createEntry(name, score);
					entry.close();
				} catch (Exception e) {
					works = false;
				} finally {
					if (works)
						Log.d("PopupHiScoreActivity", "WORKS!");
				}
				setNewScoreSaved(true);
				finish();
				break;
			}
		}

	}

	public static boolean isNewScoreSaved() {
		return newScoreSaved;
	}

	public static void setNewScoreSaved(boolean newScoreSaved) {
		PopupHiScoreActivity.newScoreSaved = newScoreSaved;
	}

}
