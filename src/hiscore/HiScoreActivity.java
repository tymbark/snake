package hiscore;

import java.util.ArrayList;

import tymbark.snake.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

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

		// getting and showing results, also sorting them before that
		highScoreTable.open();
		ArrayList<HighScoreRow> array = new ArrayList<HighScoreRow>();
		array = highScoreTable.getData();

		array = sort(array);
		String names = "";
		String results = "";
		String lps = "";
		int i = 0;
		for (HighScoreRow a : array) {
			// a.setId(i);
			i++;
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
		// int d = 9;
		int aSize = array.size();
		while (true) {
			// d--;
			max = new HighScoreRow(0, "", -1);
			for (HighScoreRow a : array) {
				// Log.d("sort array","item "+a.getmResult());
				if (max.getResult() <= a.getResult()) {
					max = a;
				}
			}
			// Log.d("sort array","max= "+max.getmResult());
			sortedArray.add(max);
			for (HighScoreRow a : sortedArray) {
				// Log.d("sarr",""+a.getmResult());
			}
			if (aSize == sortedArray.size()) {
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