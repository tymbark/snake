package main;

import android.graphics.Point;
import android.util.Log;

public class Vector {
	public float x1, y1, x2, y2;

	public Vector() {
		x1 = x2 = y1 = y2 = 0;
	}

	public Vector(Point p1, Point p2) {
		x1 = p1.x;
		x2 = p2.x;
		y1 = p1.y;
		y2 = p2.y;
	}

	public Vector(float _x1, float _y1, float _x2, float _y2) {
		x1 = _x1;
		y1 = _y1;
		x2 = _x2;
		y2 = _y2;
	}

	public Vector adjust(int axis) {
		Vector aV = new Vector();
		
		float tg = ((x2 - x1) / (y1 - y2)); // zamienilem y
		Log.d("Vector", "tan=" + tg);

		if (tg < -1 || tg > 1) {
			if (x2 > x1)
				Log.d("RESULT", "RIGHT");
			else
				Log.d("RESULT", "LEFT");
		}

		if (tg > -1 && tg < 1) {
			if (y2 > y1)
				Log.d("RESULT", "DOWN");
			else
				Log.d("RESULT", "UP");
		}

		return aV;
	}

	public void print() {
		Log.d("Vector", "x1:" + x1 + " y1:" + y1 + " x2:" + x2 + " y2:" + y2);
	}
}
