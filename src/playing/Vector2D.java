package playing;

import android.graphics.Point;
import android.util.Log;

public class Vector2D {
	public float x1, y1, x2, y2;

	public Vector2D() {
		x1 = x2 = y1 = y2 = 0;
	}

	public Vector2D(Point p1, Point p2) {
		x1 = p1.x;
		x2 = p2.x;
		y1 = p1.y;
		y2 = p2.y;
	}

	public Vector2D(float _x1, float _y1, float _x2, float _y2) {
		x1 = _x1;
		y1 = _y1;
		x2 = _x2;
		y2 = _y2;
	}

	public int adjust() {
		float tg = ((x2 - x1) / (y1 - y2)); // zamienilem y
		int result = 0;
		Log.d("Vector", "tan=" + tg);
		
		if (tg < -1 || tg > 1) {
			if (x2 > x1) {
				Log.d("RESULT", "RIGHT");
				result = 4;
			} else {
				Log.d("RESULT", "LEFT");
				result = 3;
			}
		}

		if (tg > -1 && tg < 1) {
			if (y2 > y1) {
				Log.d("RESULT", "DOWN");
				result = 2;
			} else {
				Log.d("RESULT", "UP");
				result = 1;
			}
		}

		return result;
	}

	public void print() {
		Log.d("Vector", "x1:" + x1 + " y1:" + y1 + " x2:" + x2 + " y2:" + y2);
	}
}
