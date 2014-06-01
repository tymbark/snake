package playing;

import android.graphics.Paint;
import android.graphics.Rect;

public class SnakeField {
	public int x, y, dir;
	public boolean empty, snake, dinner, head, butt;
	public Paint paint;
	public Rect rect;

	public SnakeField() {
	}

	public SnakeField(int _x, int _y, Paint _paint, Rect _rect) {
		x = _x;
		y = _y;
		paint = _paint;
		rect = _rect;
		empty = true;
		dinner = snake = head = butt = false;
	}
}