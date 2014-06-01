package playing;

import java.util.ArrayList;

import tymbark.snake.R;
import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;

public class DrawActivity extends Activity implements OnTouchListener {
	DrawView dv;

	ArrayList<Point> points = new ArrayList<Point>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.draw_view);
		dv = (DrawView) findViewById(R.id.drawView1);
		dv.setOnTouchListener(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		Point p1 = new Point();
		Point p2 = new Point();
		boolean start = false;

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (start)
				break;
			p1.x = (int) event.getX();
			p1.y = (int) event.getY();
			points.add(p1);
			start = true;
			break;
		case MotionEvent.ACTION_UP:
			p2.x = (int) event.getX();
			p2.y = (int) event.getY();
			points.add(p2);
			dv.redraw(points);
			Vector2D v1 = new Vector2D(points.get(0), points.get(1));
			v1.print();
			v1.adjust();
			break;
		}
		return true;
	}
}
