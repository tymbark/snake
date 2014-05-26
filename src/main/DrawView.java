package main;

import java.util.ArrayList;

import com.example.snake.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Canvas.VertexMode;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver.OnTouchModeChangeListener;
import android.widget.Toast;

public class DrawView extends View implements OnTouchListener {
	private static final String TAG = "DrawView";
	ArrayList<Point> points = new ArrayList<Point>();
	Paint paint = new Paint();
	Bitmap arrow_right = BitmapFactory.decodeResource(getResources(),
			R.drawable.arrow_right);

	public DrawView(Context context) {
		super(context);
		init();
	}

	public DrawView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public void init() {

		setFocusable(true);
		setFocusableInTouchMode(true);
		this.setOnTouchListener(this);
		paint.setColor(Color.BLACK);
	}

	@Override
	public void onDraw(Canvas canvas) {
		canvas.drawBitmap(arrow_right, 100, 100, paint);
		if (points.isEmpty())
			return;
		canvas.drawLine(points.get(0).x, points.get(0).y, points.get(1).x,
				points.get(1).y, paint);
		points.clear();
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
			invalidate();
			Vector v1 = new Vector(points.get(0), points.get(1));
			v1.print();
			v1.adjust(4);
			break;
		}
		return true;
	}

}
