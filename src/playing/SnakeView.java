package playing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import tymbark.snake.R;

public class SnakeView extends View {

    private static final String TAG = "SnakeView";
    SnakeField[][] board;
    ArrayList<SnakeField> snake;
    private static int SIZE_X;
    private static int SIZE_Y;
    Bitmap head, ant, tailEmpty, tailCurve;
    //private Canvas canvas;

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
        tailCurve = BitmapFactory.decodeResource(getResources(),
                R.drawable.tail_curve);
        tailEmpty = BitmapFactory.decodeResource(getResources(),
                R.drawable.tail_empty);
        ant = BitmapFactory
                .decodeResource(getResources(), R.drawable.ant_small);
        head = BitmapFactory.decodeResource(getResources(), R.drawable.head);
    }

    @Override
    public void onDraw(Canvas canvas) {
        Log.d(TAG, "redrawing");

        if (canvas == null) Log.d(TAG, "CANVAS IS NULL");
        for (SnakeField sf : snake) {
            drawField(sf, canvas);
        }
        Log.d(TAG,"redrawing complete");
    }

    public static Bitmap RotateBitmap(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(),
                source.getHeight(), matrix, true);
    }

    private void drawField(SnakeField sf, Canvas canvas) {
        int jumpX = getWidth() / SIZE_X;
        int jumpY = getHeight() / SIZE_Y;
        Rect rect = new Rect(sf.y * jumpY, sf.x * jumpX, (sf.y * jumpY) + jumpY,
                (sf.x * jumpX) + jumpX);
        
        int prevDir = snake.get(0).dir;
        if (sf.head) {

            switch (sf.dir) {
                case 0:
                    canvas.drawBitmap(head, null, rect, null);
                    break;
                case 1: // up
                    canvas.drawBitmap(head, null, rect, null);
                    break;
                case 2: // down
                    canvas.drawBitmap(RotateBitmap(head, 180), null, rect,
                            null);
                    break;
                case 3: // left
                    canvas.drawBitmap(RotateBitmap(head, 270), null, rect,
                            null);
                    break;
                case 4: // right
                    canvas.drawBitmap(RotateBitmap(head, 90), null, rect,
                            null);
                    break;
            }
        } else {
            if (prevDir != sf.dir) {
                if ((prevDir == 1 && sf.dir == 4)
                        || (prevDir == 3 && sf.dir == 2)) {
                    canvas.drawBitmap(RotateBitmap(tailCurve, 270), null,
                            rect, null);
                } else if ((prevDir == 4 && sf.dir == 2)
                        || (prevDir == 1 && sf.dir == 3)) {
                    canvas.drawBitmap(tailCurve, null, rect, null);
                } else if ((prevDir == 2 && sf.dir == 4)
                        || (prevDir == 3 && sf.dir == 1)) {
                    canvas.drawBitmap(RotateBitmap(tailCurve, 180), null,
                            rect, null);
                } else if ((prevDir == 2 && sf.dir == 3)
                        || (prevDir == 4 && sf.dir == 1)) {
                    canvas.drawBitmap(RotateBitmap(tailCurve, 90), null,
                            rect, null);

                }
            } else if (sf.dir == 1 || sf.dir == 2) {
                if (tailEmpty == null) Log.d(TAG, "bitmap NULL");
                if (canvas == null) Log.d(TAG, "canvas NULL");
                if (sf == null) Log.d(TAG, "snakefield NULL");
                if (rect == null) Log.d(TAG, "rect NULL");
                canvas.drawBitmap(tailEmpty, null, rect, null);
            } else {
                canvas.drawBitmap(RotateBitmap(tailEmpty, 90), null,
                        rect, null);
            }

        }
        prevDir = sf.dir;
    }
}
