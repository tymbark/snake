package playing;

import java.util.ArrayList;

import tymbark.snake.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class SnakeView extends View {

	SnakeField[][] board;
	ArrayList<SnakeField> snake;
	private static int SIZE_X;
	private static int SIZE_Y;
	Bitmap head, ant, tail_epmty, tail_curve;
	private Canvas canvas;

	public SnakeView(Context context) {
		super(context);
		init();
	}

	public SnakeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public void setDrawableObjects(ArrayList<SnakeField> _snake, SnakeField[][] _snakeFields, int sizeX,
			int sizeY) {
		snake = _snake;
		board = _snakeFields;
		SIZE_X = sizeX;
		SIZE_Y = sizeY;
	}

	private void init() {
		setFocusable(true);
		setFocusableInTouchMode(true);
		initBitmaps();
	}

	private void initBitmaps() {
		tail_curve = BitmapFactory.decodeResource(getResources(),
				R.drawable.tail_curve);
		tail_epmty = BitmapFactory.decodeResource(getResources(),
				R.drawable.tail_empty);
		ant = BitmapFactory
				.decodeResource(getResources(), R.drawable.ant_small);
		head = BitmapFactory.decodeResource(getResources(), R.drawable.head);
	}

	public void redraw() {
		for(SnakeField sf : snake){
			drawField(sf);
		}
	}

	public static Bitmap RotateBitmap(Bitmap source, float angle) {
		Matrix matrix = new Matrix();
		matrix.postRotate(angle);
		return Bitmap.createBitmap(source, 0, 0, source.getWidth(),
				source.getHeight(), matrix, true);
	}

	private void drawField(SnakeField sf) {
		int jumpX = getWidth()/SIZE_X;
		int jumpY= getHeight()/SIZE_Y;
		Rect rect = new Rect(sf.y * jumpY, sf.x * jumpX, (sf.y* jumpY) + jumpY,
				(sf.x * jumpX) + jumpX);
		int prevDir = snake.get(0).dir;
		if (sf.head) {
	
			switch (sf.dir) {
			case 0:
				canvas.drawBitmap(head, null, sf.rect, null);
				break;
			case 1: // up
				canvas.drawBitmap(head, null, sf.rect, null);
				break;
			case 2: // down
				canvas.drawBitmap(RotateBitmap(head, 180), null, sf.rect,
						null);
				break;
			case 3: // left
				canvas.drawBitmap(RotateBitmap(head, 270), null, sf.rect,
						null);
				break;
			case 4: // right
				canvas.drawBitmap(RotateBitmap(head, 90), null, sf.rect,
						null);
				break;
			}
		} else {
			if (prevDir != sf.dir) {
				if ((prevDir == 1 && sf.dir == 4)
						|| (prevDir == 3 && sf.dir == 2)) {
					canvas.drawBitmap(RotateBitmap(tail_curve, 270), null,
							sf.rect, null);
				} else if ((prevDir == 4 && sf.dir == 2)
						|| (prevDir == 1 && sf.dir == 3)) {
					canvas.drawBitmap(tail_curve, null, sf.rect, null);
				} else if ((prevDir == 2 && sf.dir == 4)
						|| (prevDir == 3 && sf.dir == 1)) {
					canvas.drawBitmap(RotateBitmap(tail_curve, 180), null,
							sf.rect, null);
				} else if ((prevDir == 2 && sf.dir == 3)
						|| (prevDir == 4 && sf.dir == 1)) {
					canvas.drawBitmap(RotateBitmap(tail_curve, 90), null,
							sf.rect, null);
	
				}
			} else if (sf.dir == 1 || sf.dir == 2) {
	
				canvas.drawBitmap(tail_epmty, null, sf.rect, null);
			} else {
				canvas.drawBitmap(RotateBitmap(tail_epmty, 90), null,
						sf.rect, null);
			}
	
		}
		prevDir = sf.dir;
	}
}
